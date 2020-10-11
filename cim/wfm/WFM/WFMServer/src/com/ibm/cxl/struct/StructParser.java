/*
 * (C) Copyright IBM Corp. 2003 All rights reserved.
 *
 * US Government Users Restricted Rights Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 *
 * The program is provided "as is" without any warranty express or
 * implied, including the warranty of non-infringement and the implied
 * warranties of merchantibility and fitness for a particular purpose.
 * IBM will not be liable for any damages suffered by you as a result
 * of using the Program. In no event will IBM be liable for any
 * special, indirect or consequential damages or lost profits even if
 * IBM has been advised of the possibility of their occurrence. IBM
 * will not be liable for any third party claims against you.
 */
package com.ibm.cxl.struct;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.ibm.cxl.util.FastStack;

/**
 * This class parses an XML document and generates a structural element.
 * The example of XML format is as follows.
 * <P><UL>
 * <LI>struct [name, encoding]</LI>
 *   <UL>
 *     <LI>field (+) [name, size, encoding]</LI>
 *   </UL>
 *   <UL>
 *     <LI>struct (+) [name, encoding]</LI>
 *     <UL>
 *       <LI>field (+) [name, size, encoding]</LI>
 *     </UL>
 *   </UL>
 *   <UL>
 *     <LI>array (+) [name, size, encoding]</LI>
 *     <UL>
 *       <LI>(any)</LI>
 *     </UL>
 *   </UL>
 * </UL></P>
 * It parses a XML document described with the uniform format and generates the structural element,
 * or parses the raw XML document and converts to the structural element.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public class StructParser extends DefaultHandler {

    /**
     * The DeferredElement is the internal class of the StructParser.
     * This class represents a deferred element and has information about an element.
     */
    class DeferredElement {
        String qName = null;
        String name = null;
        String encoding = null;
        int size = 0;
        int multiplicity = 1;
        StringBuffer data = new StringBuffer();
        StructFolder folder = null;
    } // the end of DeferredElement

    /** the qualified name indicates the structure */
    private final static String QNAME_STRUCT = "struct";

    /** the qualified name indicates the structure field */
    private final static String QNAME_FIELD = "field";

    /** the qualified name indicates the structure array */
    private final static String QNAME_ARRAY = "array";

    /** the key indicates the name attribute */
    private final static String ATTR_NAME = "name";

    /** the key indicates the size attribute */
    private final static String ATTR_SIZE = "size";

    /** the key indicates the multiplicity attribute */
    private final static String ATTR_MULTIPLICITY = "multiplicity";

    /** the key indicates the encoding attribute */
    private final static String ATTR_ENC = "encoding";

    /** the stack pushes deferred element. */
    private FastStack stack_ = new FastStack();

    /** parsed structure. */
    private StructElement parsedElement_ = null;

    /** Whether convert raw XML data. */
    private boolean convertXML_ = false;

    /** Whether use a validating paser. */
    private boolean validating_ = false;

    /** Whether use fields have fixed size. */
    private boolean fixedSize_ = true;

    /** Whether fill fields with the white space. */
    private boolean fillSpace_ = true;

    /**
     * Returns the SAXParser to parse the input stream.
     *
     * @return returns a parser.
     * @exception ParserConfigurationException if a parser cannot be created which satisfies the requested configuration.
     * @exception SAXException If the underlying parser throws a SAXException while parsing.
     */
    public final SAXParser getParser() throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(false);
        factory.setValidating(validating_);
        SAXParser parser = factory.newSAXParser();
        return parser;
    }

    /**
     * Specifies that whether the parser will validate documents as they are parsed.
     *
     * @param flag true if the parser will validate documents; false otherwise.
     */
    public final void setValidating(boolean flag) {
        validating_ = flag;
    }

    /**
     * Specifies that the parser will convert raw XML documents.
     *
     * @param flag true if the parser will convert raw documents; false otherwise.
     */
    public final void setConvertXML(boolean flag) {
        convertXML_ = flag;
    }

    /**
     * Specifies that the parser will use fixed size.
     *
     * @param flag true if the parser will use fixed size; false otherwise.
     */
    public final void setFixedSize(boolean flag) {
        fixedSize_ = flag;
    }

    /**
     * Specifies that the parser will fill the white space.
     *
     * @param flag true if the parser will fill the white space; false otherwise.
     */
    public final void setFillSpace(boolean flag) {
        fillSpace_ = flag;
    }

    /**
     * Parses the content of the given array of bytes as an XML document and returns a parsed StructElement object.
     *
     * @param by an array of bytes containing the content to be parsed.
     * @return a parsed StructElement object.
     * @exception ParserConfigurationException if a parser cannot be created which satisfies the requested configuration.
     * @exception SAXException if any parse errors occur.
     * @exception IOException if any IO errors occur.
     */
    public final StructElement parse(byte[] by) throws ParserConfigurationException, SAXException, IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(by);
        StructElement element = parse(stream);
        stream.close();
        return element;
    }

    /**
     * Parses the content of the given InputStream as an XML document and returns a parsed StructElement object.
     *
     * @param byteStream InputStream containing the content to be parsed.
     * @return a parsed StructElement object.
     * @exception ParserConfigurationException if a parser cannot be created which satisfies the requested configuration.
     * @exception SAXException if any parse errors occur.
     * @exception IOException if any IO errors occur.
     */
    public final StructElement parse(InputStream byteStream) throws ParserConfigurationException, SAXException, IOException {
        InputSource is = new InputSource(byteStream);
        return parse(is);
    }

    /**
     * Parses the content of the given URI as an XML document and returns a parsed StructElement object.
     *
     * @param uri the location of the content to be parsed.
     * @return a parsed StructElement object.
     * @exception ParserConfigurationException if a parser cannot be created which satisfies the requested configuration.
     * @exception SAXException if any parse errors occur.
     * @exception IOException if any IO errors occur.
     */
    public final StructElement parse(String uri) throws ParserConfigurationException, SAXException, IOException {
        InputSource is = new InputSource(uri);
        return parse(is);
    }

    /**
     * Parses the content of the given input source as an XML document and returns a parsed StructElement object.
     *
     * @param is InputSource containing the content to be parsed.
     * @return a parsed StructElement object.
     * @exception ParserConfigurationException if a parser cannot be created which satisfies the requested configuration.
     * @exception SAXException if any parse errors occur.
     * @exception IOException if any IO errors occur.
     */
    public final StructElement parse(InputSource is) throws ParserConfigurationException, SAXException, IOException {
        SAXParser parser = getParser();
        parser.parse(is, this);
        return parsedElement_;
    }

    // --------------------------------------------- Document Handler Methods

    /**
     * Receive notification of the beginning of the document.
     *
     * @exception SAXException any SAX exception, possibly wrapping another exception.
     */
    public final void startDocument(){
        parsedElement_ = null;
        stack_.clear();
    }

    /**
     * Receive notification of the end of the document.
     *
     * @exception SAXException any SAX exception, possibly wrapping another exception.
     */
    public final void endDocument(){
        stack_.clear();
    }

    /**
     * Receives notification of the start of an element.
     *
     * @param uri the namespace URI, or the empty string if the element has no namespace URI or if namespace processing is not being performed.
     * @param localName the local name (without prefix), or the empty string if namespace processing is not being performed.
     * @param qName the qualified name (with prefix), or the empty string if qualified names are not available.
     * @param attributes the attributes attached to the element. if there are no attributes, it shall be an empty Attributes object.
     * @exception SAXException any SAX exception, possibly wrapping another exception.
     */
    public final void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        DeferredElement deferred = new DeferredElement();
        deferred.qName = qName;
        deferred.name = attributes.getValue(ATTR_NAME);
        deferred.encoding = attributes.getValue(ATTR_ENC);
        if (!convertXML_) {
            String str = null;
            if (fixedSize_) {
                if ((str = attributes.getValue(ATTR_SIZE)) != null) {
                    try {
                        deferred.size = Integer.parseInt(str);
                    } catch (NumberFormatException ex) {
                        throw new SAXNotRecognizedException(deferred.qName + ":" + deferred.name + ":<" + ATTR_SIZE + ">");
                    }
                }
            }
            if ((str = attributes.getValue(ATTR_MULTIPLICITY)) != null) {
                try {
                    deferred.multiplicity = Integer.parseInt(str);
                } catch (NumberFormatException ex) {
                    throw new SAXNotRecognizedException(deferred.qName + ":" + deferred.name + ":<" + ATTR_MULTIPLICITY + ">");
                }
            }
        }
        stack_.push(deferred);
    }

    /**
     * Receives notification of the end of an element.
     *
     * @param uri the namespace URI, or the empty string if the element has no namespace URI or if namespace processing is not being performed.
     * @param localName the local name (without prefix), or the empty string if namespace processing is not being performed.
     * @param qName the qualified name (with prefix), or the empty string if qualified names are not available.
     * @exception SAXException any SAX exception, possibly wrapping another exception.
     */
    public final void endElement(String uri, String localName, String qName) throws SAXException {
        StructElement element = null;
        DeferredElement deferred = (DeferredElement)stack_.pop();
        int multiplicity = deferred.multiplicity;

        if (deferred.folder != null) { // This element is folder ?
            element = deferred.folder;
            if (deferred.encoding != null)
                element.setEncoding(deferred.encoding, false); // Infiltrate the encoding into folder.
        }
        else {
            if (convertXML_)
                element = new StructField(deferred.qName);
            else {
                if (!QNAME_FIELD.equals(deferred.qName))
                    throw new SAXNotRecognizedException(deferred.qName + ":" + deferred.name);
                element = new StructField(deferred.name, deferred.size);
                if (deferred.encoding != null)
                    element.setEncoding(deferred.encoding);
                if (fillSpace_)
                    element.fill((byte)' ');
            }
            if (deferred.data.length() > 0){
            	element.setEncoding("iso-8859-1");
//            	element.setEncoding("GB2312");
            	element.setString(deferred.data.toString());
            }
        }

        if (stack_.empty()) { // Root element ?
            parsedElement_ = element;
            return;
        }

        // Create a foler to put the current element.
        deferred = (DeferredElement)stack_.peek();
        if (deferred.folder == null) {
            if (convertXML_)
                deferred.folder = new StructFolder(deferred.qName);
            else {
                if (!QNAME_STRUCT.equals(deferred.qName) && !QNAME_ARRAY.equals(deferred.qName))
                    throw new SAXNotRecognizedException(deferred.qName + ":" + deferred.name);
                deferred.folder = new StructFolder(deferred.name);
            }
        }

        for (int i = 0; i < multiplicity; i++)
            deferred.folder.addElement(i == 0 ? element : element.cloneElement());
    }

    /**
     * Receives notification of character data inside an element.
     *
     * @param chars the chars.
     * @param start the start position in the character array.
     * @param length the number of characters to use from the character array.
     * @exception SAXException any SAX exception, possibly wrapping another exception.
     */
    public final void characters(char[] chars, int start, int length){
        if (stack_.empty())
            return;
        DeferredElement deferred = (DeferredElement)stack_.peek();
        deferred.data.append(new String(chars, start, length));
    }

    // ------------------------------------------------ Error Handler Methods

    /**
     * Receives notification of a parser warning.
     *
     * @param ex the warning information encoded as an exception.
     * @exception SAXException any SAX exception, possibly wrapping another exception.
     */
    public final void warning(SAXParseException ex) throws SAXException {
        throw ex;
    }

    /**
     * Receives notification of a recoverable parser error.
     *
     * @param ex the error information encoded as an exception.
     * @exception SAXException any SAX exception, possibly wrapping another exception.
     */
    public final void error(SAXParseException ex) throws SAXException {
        throw ex;
    }

    /**
     * Receives notification of a fatal XML parsing error.
     *
     * @param ex the fatal error information encoded as an exception.
     * @exception SAXException any SAX exception, possibly wrapping another exception.
     */
    public final void fatalError(SAXParseException ex) throws SAXException {
        throw ex;
    }

} // the end of StructParser