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
 * Modification                                                          
 *   Date      Level  Author      Description                                
 *   --------  -----  ----------  --------------------------------------  
 * 2003/05/06  L0.00  iiSC        Initial Release.
 * 2005/01/12  L0.01  Y.Tanaka    Add setting encode
 */
package com.ibm.cxl.struct;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;

/**
 * This class can transform a structural element.
 * It may be used to process XML from the structural element and write the transformation output to a stream.
 * An instance of this class may not be used in multiple threads running concurrently.
 * Defferent instance may be used concurrently by different threads.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public class StructTransformer {

    /** Whether add additional whitespace when outputting the result tree. */
    private boolean indent_ = false;

    /** XML Encode set. 
     * @aouther L0.01
     */
    private String encode_ = null;

    /**
     * Specifies that whether the transformer may add additional whitespace when outputting the result tree.
     *
     * @param flag true if the transformer will add additinal whitespace; false otherwise.
     */
    public final void setIndent(boolean flag) {
        indent_ = flag;
    }

    /**
     * XML Encode set. 
     *
     * @param enode
     * @aouther L0.01
     */
    public final void setEncode(String encode) {
        encode_ = encode;
    }

    /**
     * Returns the Transformer to transform the structural element.
     *
     * @return returns a transformer.
     * @exception TransformerConfigurationException if a transformer cannot be created which satisfies the configuration requested.
     */
    public Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer former = factory.newTransformer();
        return former;
    }

    /**
     * Creates and returns a new Document.
     *
     * @exception ParserConfigurationException if a builder cannot be created which satisfies the configuration requested.
     */
    public Document newDocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        return doc;
    }

    /**
     * Processes a structural element to an output stream.
     *
     * @param element a structural element that will contains the structural tree.
     * @param stream a valid OutputStream reference.
     * @exception DOMExcepion if any DOM exception occurs.
     * @exception ParserConfigurationException if a builder cannot be created which satisfies the configuration requested.
     * @exception TransformerException if an unrecoverable error occurs during the course of the transformation.
     */
    public final void transform(StructElement element, OutputStream stream) throws ParserConfigurationException, TransformerException, DOMException {
        Document doc = createXMLDocument(element);
        Transformer former = getTransformer();
        DOMSource source = new DOMSource(doc.getDocumentElement());
        StreamResult result = new StreamResult(stream);

        former.setOutputProperty(OutputKeys.INDENT, indent_ ? "yes" : "no");

        if(encode_ != null && !encode_.trim().equals(""))                        //L0.01
            former.setOutputProperty(OutputKeys.ENCODING , encode_);            //L0.01

        former.transform(source, result);
    }

    /**
     * Transforms a structural element into an array of bytes.
     *
     * @param element a structural element that will contains the structural tree.
     * @return an array of bytes that contains transformed document.
     * @exception DOMExcepion if any DOM exception occurs.
     * @exception IOException if any I/O exception occurs.
     * @exception ParserConfigurationException if a builder cannot be created which satisfies the configuration requested.
     * @exception TransformerException if an unrecoverable error occurs during the course of the transformation.
     */
    public final byte[] toByteArray(StructElement element) throws ParserConfigurationException, TransformerException, DOMException, IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        transform(element, stream);
        stream.flush();
        byte[] by = stream.toByteArray();
        stream.close();
        return by;
    }

    /**
     * Creates a XML document by converting a structural element.
     *
     * @param struct_element a StructElement object to be converted.
     * @return a created Document object.
     * @exception ParserConfigurationException if a builder cannot be created which statisfies the configuration reqested.
     * @exception DOMException if any DOM errors occur.
     */
    public final Document createXMLDocument(StructElement struct_element) throws ParserConfigurationException, DOMException {
        Document doc = newDocument();
        if (struct_element != null)
            doc.appendChild(_createXMLElement(doc, struct_element));
        return doc;
    }

    /**
     * Creates a XML element by converting a structural element.
     *
     * @param doc a XML document to create elements.
     * @param struct_element a StructElement object to be converted.
     * @return a created Element object.
     * @exception DOMException if any DOM errors occur.
     */
    private Element _createXMLElement(Document doc, StructElement struct_element) throws DOMException {
        Element element = doc.createElement(struct_element.getName());
        int i = struct_element.getElementCount();
        if (i > 0) {
            for (int j = 0; j < i; j++)
                element.appendChild(_createXMLElement(doc, struct_element.element(j)));
        }
        else {
            String str = struct_element.getString();
            if (str != null && str.length() > 0)
                element.appendChild(doc.createTextNode(str));
        }
        return element;
    }

} // the end of StructTransformer