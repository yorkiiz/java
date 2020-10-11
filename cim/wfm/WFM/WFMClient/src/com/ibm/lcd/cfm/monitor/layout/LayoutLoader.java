
package com.ibm.lcd.cfm.monitor.layout;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.ibm.lcd.cfm.monitor.util.FastStack;

public final class LayoutLoader extends DefaultHandler 
{
    final String CLASS_NAME = "LayoutLoader";
    boolean DEBUG = false;

    class DeferredElement {
        final String CLASS_NAME = "LayoutLoader$DeferredElement";

        String qName = null;
        Object object = null;
        StringBuffer data = new StringBuffer();
    }

    class ShapeElement {
        final String CLASS_NAME = "LayoutLoader$ShapeElement";

        int top = 0;
        int left = 0;
        int right = 0;
        int bottom = 0;
    }

    private MonitorLayout parsingLayout = null;

    private MonitorLayout parsedLayout = null;

    private FastStack stack = new FastStack();

    public LayoutLoader() {
    }

    public final SAXParser getParser() throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(false);
        factory.setValidating(false);
        SAXParser parser = factory.newSAXParser();
        return parser;
    }


    public final MonitorLayout load(InputSource is) throws ParserConfigurationException, SAXException, IOException {
        SAXParser parser = getParser();
        parser.parse(is, this);
        return getParsedLayout();
    }

    public final MonitorLayout load(InputStream byteStream) throws ParserConfigurationException, SAXException, IOException {
        InputSource is = new InputSource(byteStream);
        return load(is);
    }

    public final MonitorLayout load(String uri) throws ParserConfigurationException, SAXException, IOException {
        InputSource is = new InputSource(uri);
        return load(is);
    }

    public final MonitorLayout getParsedLayout() {
        return parsedLayout;
    }

    private Color getColorAttribute(Attributes attributes, String name) {
        try {
            return new Color(Integer.parseInt(attributes.getValue(name), 16));
        }
        catch (Exception ex) {}
        return null;
    }

    private AreaLayer createAreaLayer(Attributes attributes) throws SAXException {
        String id = attributes.getValue("id");
        if (id == null || id.length() <= 0)
            throw new SAXNotRecognizedException("area: id");
        AreaLayer layer = new AreaLayer(id);
        layer.imageId = attributes.getValue("image");
        this.parsingLayout.addArea(layer);
        return layer;
    }

    private EquipmentLayer createEquipmentLayer(Attributes attributes) throws SAXException {
        AreaLayer parent = null;
        if (!this.stack.empty()) {
            DeferredElement deferred = (DeferredElement)this.stack.peek();
            if (deferred.object instanceof AreaLayer)
                parent = (AreaLayer)deferred.object;
        }
        if (parent == null)
            throw new SAXNotRecognizedException("equipment: parent");

        String str = attributes.getValue("id");
        if (str == null || str.length() <= 0)
            throw new SAXNotRecognizedException("equipment: id");
        //WFM008 enhancement
        if(str.indexOf("_") > 0){
        	str = str.split("_")[0];
        }
        //WFM008
        EquipmentState state = this.parsingLayout.getEquipmentState(str);
        if (state == null) {
            state = new EquipmentState(str);
            this.parsingLayout.addEquipmentState(state);
        }
        EquipmentLayer layer = null;
        str = attributes.getValue("type");
        if ("stocker".equals(str))
            layer = new StockerLayer(state);
        else {
	        String sub_type = attributes.getValue("subtype");		/* L1.1 */
	        if( sub_type !=null) {					/* L1.1 */
	            if ("AT".equals(sub_type))				/* L1.1 */
	                layer = new EquipmentLayer(state, sub_type); 	/* L1.1 */
	            else if ("PEP".equals(sub_type))			/* L1.1 */
	                layer = new EquipmentLayer(state, sub_type);   	/* L1.1 */
	            else if ("CLN".equals(sub_type))			/* L1.2 */
	                layer = new EquipmentLayer(state, sub_type);   	/* L1.2 */
	        }else { 							/* L1.1 */
	             layer = new EquipmentLayer(state);			/* L1.1 */
	        }								/* L1.1 */
        }    								/* L1.1 */

        layer.imageId = attributes.getValue("image");
        layer.setMainLinkURI(attributes.getValue("mainLinkURI"));
        
        parent.addEquipment(layer);
        return layer;
    }

    private UnitLayer createUnitLayer(Attributes attributes) throws SAXException {
        EquipmentLayer parent = null;
        if (!this.stack.empty()) {
            DeferredElement deferred = (DeferredElement)this.stack.peek();
            if (deferred.object instanceof EquipmentLayer)
                parent = (EquipmentLayer)deferred.object;
        }
        if (parent == null)
            throw new SAXNotRecognizedException("unit: parent");

        String id = attributes.getValue("id");
        if (id == null || id.length() <= 0)
            throw new SAXNotRecognizedException("unit: id");
        //WFM008 enhancement
        if(id.indexOf("_") > 0){
        	id = id.split("_")[0];
        }
        //WFM008
        EquipmentState state = this.parsingLayout.getEquipmentState(id);
        if (state == null) {
            state = new EquipmentState(id);
            this.parsingLayout.addEquipmentState(state);
        }

        UnitLayer layer = new UnitLayer(state);
        layer.imageId = attributes.getValue("image");
        layer.setMainLinkURI(attributes.getValue("mainLinkURI"));
        parent.addUnit(layer);
        return layer;
    }

    private PortLayer createPortLayer(Attributes attributes) throws SAXException {
        EquipmentLayer parent = null;
        if (!this.stack.empty()) {
            DeferredElement deferred = (DeferredElement)this.stack.peek();
            if (deferred.object instanceof EquipmentLayer)
                parent = (EquipmentLayer)deferred.object;
        }
        if (parent == null)
            throw new SAXNotRecognizedException("port: parent");

        String id = attributes.getValue("id");
        if (id == null || id.length() <= 0)
            throw new SAXNotRecognizedException("port: id");
        PortState state = parent.state.getPort(id);
        if (state == null) {
            state = new PortState(id);
            parent.state.addPort(state);
        }

        PortLayer layer = new PortLayer(state);
        layer.imageId = attributes.getValue("image");
        parent.addPort(layer);
        return layer;
    }

    private ImageDefinition createImageDefinition(Attributes attributes) throws SAXException {
        String id = attributes.getValue("id");
        if (id == null || id.length() <= 0)
            throw new SAXNotRecognizedException("image: id");
        ImageDefinition definition = new ImageDefinition(id);
        definition.path = attributes.getValue("path");
        definition.baseColor = getColorAttribute(attributes, "base");
        definition.borderColor = getColorAttribute(attributes, "border");
        this.parsingLayout.addImageDefinition(definition);
        return definition;
    }

    private ColorDefinition createColorDefinition(Attributes attributes) throws SAXException {
        String main = attributes.getValue("main");
        //String sub = attributes.getValue("sub");
        String desc = attributes.getValue("desc");
        
//        if (sub == null)
//            sub = "";
        if(desc == null)
        	desc = "";
        
        if (main == null || main.length() <= 0)
            throw new SAXNotRecognizedException("color: main");
        String str = attributes.getValue("type");
        int type = 0;
        if ("status".equals(str))
            type = ColorDefinition.STATUS;
        else if ("mode".equals(str))
            type = ColorDefinition.MODE;
        else if ("portStatus".equals(str))
            type = ColorDefinition.PORT_STATUS;
        
        ColorDefinition definition = new ColorDefinition(type, main, desc);
        definition.color = getColorAttribute(attributes, "rgb");
        
        this.parsingLayout.addColorDefinition(definition);
        return definition;
    }

    private LinkDefinition createLinkDefinition(Attributes attributes) throws SAXException {
        String str = attributes.getValue("id");
        if (str == null || str.length() <= 0)
            throw new SAXNotRecognizedException("link: id");
        LinkDefinition definition = new LinkDefinition(str);
        definition.url = attributes.getValue("url");
        str = attributes.getValue("type");

        if ("equipment".equals(str)) {
            /*definition.type = LayoutType.EQUIPMENT;			   L1.1 disable*/
            String sub_type = attributes.getValue("subtype");		/* L1.1 */
            if("AT".equals(sub_type))					/* L1.1 */
                definition.type = LayoutType.AT;			/* L1.1 */
            else if("PEP".equals(sub_type))				/* L1.1 */
                definition.type = LayoutType.PEP;			/* L1.1 */
            else if("CLN".equals(sub_type))				/* L1.2 */
                definition.type = LayoutType.CLN;			/* L1.2 */
            else if("NOCLN".equals(sub_type))				/* L1.2 */
                definition.type = LayoutType.NOCLN;			/* L1.2 */
            else							/* L1.1 */
                definition.type = LayoutType.EQUIPMENT;			/* L1.1 */
        }
        else if ("stocker".equals(str))
            definition.type = LayoutType.STOCKER;
        else if ("unit".equals(str))
            definition.type = LayoutType.UNIT;
        else if ("port".equals(str))
            definition.type = LayoutType.PORT;
        else if ("carrier".equals(str))
            definition.type = LayoutType.CARRIER;

        this.parsingLayout.addLinkDefinition(definition);
        return definition;
    }

    private LinkParameter createLinkParameter(Attributes attributes) throws SAXException {
        LinkDefinition parent = null;
        if (!this.stack.empty()) {
            DeferredElement deferred = (DeferredElement)this.stack.peek();
            if (deferred.object instanceof LinkDefinition)
                parent = (LinkDefinition)deferred.object;
        }
        if (parent == null)
            throw new SAXNotRecognizedException("link parameter: parent");

        String str = attributes.getValue("name");
        if (str == null || str.length() <= 0)
            throw new SAXNotRecognizedException("link parameter: name");

        LinkParameter parameter = new LinkParameter(str);
        parameter.refer = attributes.getValue("refer");
        parameter.value = attributes.getValue("value");

        parent.addLinkParameter(parameter);
        return parameter;
    }

    private ShapeElement createShapeElement(Attributes attributes){
        ShapeElement element = new ShapeElement();
        return element;
    }

    private void handleShapeElement(ShapeElement shape, DeferredElement deferred) {

        try {
            if ("top".equals(deferred.qName)) {
                shape.top = Integer.parseInt(deferred.data.toString());
            }
            else if ("left".equals(deferred.qName)) {
                shape.left = Integer.parseInt(deferred.data.toString());
            }
            else if ("right".equals(deferred.qName)) {
                shape.right = Integer.parseInt(deferred.data.toString());
            }
            else if ("bottom".equals(deferred.qName)) {
                shape.bottom = Integer.parseInt(deferred.data.toString());
            }
        }
        catch (Exception ex) {}

    }

    private void validateShapeElement(ShapeElement shape) throws SAXException {

        BaseLayer layer = null;
        if (!this.stack.empty()) {
            DeferredElement deferred = (DeferredElement)this.stack.peek();
            if (deferred.object instanceof BaseLayer)
                layer = (BaseLayer)deferred.object;
        }
        if (layer == null)
            throw new SAXNotRecognizedException("shape: parent");
        if (shape.left < 0) shape.left = 0;
        if (shape.right < shape.left) shape.right = shape.left;
        if (shape.top < 0) shape.top = 0;
        if (shape.bottom < shape.top) shape.bottom = shape.top;
        layer.shape = new Rectangle(shape.left, shape.top, shape.right - shape.left, shape.bottom - shape.top);
    }

    public final void startDocument(){
        this.parsedLayout = null;
        this.parsingLayout = new MonitorLayout();
        this.stack.clear();
    }

    public final void endDocument(){

        this.parsedLayout = this.parsingLayout;
        this.parsingLayout = null;
        this.stack.clear();
    }

    public final void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        DeferredElement deferred = new DeferredElement();
        deferred.qName = qName;

        if ("shape".equals(qName)) {
            deferred.object = createShapeElement(attributes);
        }
        else if ("equipment".equals(qName)) {
            deferred.object = createEquipmentLayer(attributes);
        }
        else if ("unit".equals(qName)) {
            deferred.object = createUnitLayer(attributes);
        }
        else if ("port".equals(qName)) {
            deferred.object = createPortLayer(attributes);
        }
        else if ("image".equals(qName)) {
            deferred.object = createImageDefinition(attributes);
        }
        else if ("color".equals(qName)) {
            deferred.object = createColorDefinition(attributes);
        }
        else if ("area".equals(qName)) {
            deferred.object = createAreaLayer(attributes);
        }
        else if ("link".equals(qName)) {
            deferred.object = createLinkDefinition(attributes);
        }
        else if ("parameter".equals(qName)) {
            deferred.object = createLinkParameter(attributes);
        }
        else {
            if (!this.stack.empty()) // for placefolder.
                deferred.object = ((DeferredElement)this.stack.peek()).object;
        }
        this.stack.push(deferred);
    }


    public final void endElement(String uri, String localName, String qName) throws SAXException {

        DeferredElement deferred = (DeferredElement)this.stack.pop();
        ShapeElement shape = null;

        if (!this.stack.empty()) {
            DeferredElement parent = (DeferredElement)this.stack.peek();
            if (parent.object instanceof ShapeElement)
                shape = (ShapeElement)parent.object;
        }
        if (shape != null)
            handleShapeElement(shape, deferred);
        else if (deferred.object instanceof ShapeElement)
            validateShapeElement((ShapeElement)deferred.object);

    }

    public final void characters(char[] chars, int start, int length){

        if (this.stack.empty())
            return;
        DeferredElement deferred = (DeferredElement)this.stack.peek();
        deferred.data.append(new String(chars, start, length));
    }

    public final void warning(SAXParseException ex) throws SAXException {
        throw ex;
    }

    public final void error(SAXParseException ex) throws SAXException {
        throw ex;
    }

    public final void fatalError(SAXParseException ex) throws SAXException {
        throw ex;
    }

}
