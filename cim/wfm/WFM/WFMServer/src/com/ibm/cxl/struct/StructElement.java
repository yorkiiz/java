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
 *
 * Modification                                                          
 *   Date      Level  Author      Description                                
 *   --------  -----  ----------  --------------------------------------  
 * 2004/07/01  L0.00  iiSC        Initial Release.
 * 2005/04/25  L0.01  Y.Tanaka    Chg getInteger
 */

package com.ibm.cxl.struct;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * This class provides a skeletal implementation of a structural element.
 * Note that this implementation is not synchronized. If multiple threads access an instance concurrnetly,
 * and at least one of the threads modifies the list structurally, it must be synchronized externally.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public abstract class StructElement implements Cloneable, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4199411703141527061L;

	/** the name of this element. */
    private String name_ = null;

    /** the name of a supported character encoding. */
    private String encoding_ = null;

    /**
     * Creates a new object.
     *
     * @param name the name of this element.
     */
    public StructElement(String name) {
        name_ = name;
    }

    /**
     * Returns the name of this object.
     *
     * @return the name of this object.
     */
    public final String getName() {
        return name_;
    }

    /**
     * Returns the size of this object, in bytes.
     *
     * @return the size of this object, in bytes.
     */
    public int getSize() {
        return 0;
    }

    /**
     * Clears data in this element.
     *
     */
    public final void clear() {
        fill((byte)0);
    }

    /**
     * Fills data in this element with the specified byte.
     *
     * @param by a byte to be filled.
     */
    public abstract void fill(byte by);

    /**
     * Returns an array of elements which match to the specified path.
     *
     * @param name the path to selected elements.
     * @return an array of elements.
     */
    public StructElement[] select(String path) {
        return new StructElement[0];
    }

    /**
     * Returns a child element at the specified position in this element.
     * If the element is not found, returns null.
     *
     * @param index an index of the element to be returned.
     * @return an element at the specified position in this element.
     */
    public StructElement element(int index) {
        return null;
    }

    /**
     * Returns a child element which has the specified path.
     * If the element is not found, returns null.
     *
     * @param path the path to the element to be returned.
     * @return an element which has the specified path.
     */
    public StructElement element(String path) {
        return null;
    }

    /**
     * Returns the number of child elements in this element.
     *
     * @return the number of child elements in this element.
     */
    public int getElementCount() {
        return 0;
    }

    /**
     * Returns an array of bytes which is contained in this element.
     *
     * @return an array of bytes which is contained in this element.
     */
    public abstract byte[] getBytes();

    /**
     * Stores an array of bytes into this element.
     *
     * @param by an array of bytes to be stored.
     * @param offset an index of the first byte to be stored.
     */
    public abstract void setBytes(byte[] by, int offset);

    /**
     * Stores an array of bytes into this element.
     *
     * @param by an array of bytes to be stored.
     */
    public final void setBytes(byte[] by) {
        setBytes(by, 0);
    }

    /**
     * Returns a string object created by converting original data in this element.
     *
     * @return a string object created.
     */
    public final String getString() {
        if (encoding_ != null) {
            try {
                return new String(getBytes(), encoding_);
            } catch (UnsupportedEncodingException ex) {}
            return "";
        }
        return new String(getBytes());
    }

    /**
     * Returns a element's value as string, or default string if the element is not found.
     *
     * @param path the path to an element.
     * @param defaultString default string.
     * @return a string object created.
     */
    public final String getString(String path, String defaultString) {
        StructElement element = element(path);
        return (element != null) ? element.getString() : defaultString;
    }

    /**
     * Stores the specified string into this element by converting original data.
     *
     * @param str the string object to be stored.
     */
    public final void setString(String str) {
        byte[] by = null;
        if (str != null) {
            if (encoding_ != null) {
                try {
                    by = str.getBytes(encoding_);
                } catch (UnsupportedEncodingException ex) {
                    by = null;
                }
            }
            else
                by = str.getBytes();
        }
        setBytes(by, 0);
    }

    /**
     * Stores the specified value into elements which has the specified path.
     *
     * @param path the path to elements.
     * @param value string value.
     */
    public final void setString(String path, String value) {
        StructElement[] elements = select(path);
        for (int i = 0; i < elements.length; i++)
            elements[i].setString(value);
    }

    /**
     * Returns an integer value created by converting original data in this element.
     *
     * @return an integer value created.
     */
    public final int getInteger() {
        try {
//L0.01            return Integer.parseInt(getString());
            return Integer.parseInt(getString().trim());        //L0.01
        } catch (Exception e) {}
        return 0;
    }

    /**
     * Returns a element's value as integer, or default value if the element is not found.
     *
     * @param path the path to an element.
     * @param defaultValue default integer value.
     * @return an integer value created.
     */
    public final int getInteger(String path, int defaultValue) {
        try {
//L0.01            return Integer.parseInt(getString(path, null));
            return Integer.parseInt(getString(path, null).trim());          //L0.01
        } catch (Exception e) {}
        return defaultValue;
    }

    /**
     * Stores the specified integer value into this element by converting original data.
     *
     * @param i the integer value to be stored.
     */
    public final void setInteger(int i) {
        setString(String.valueOf(i));
    }

    /**
     * Stores the specified integer value into into elements which has the specified path.
     *
     * @param path the path to elements.
     * @param n the integer value to be stored.
     */
    public final void setInteger(String path, int i) {
        setString(path, String.valueOf(i));
    }

    /**
     * Returns a long value created by converting original data in this element.
     *
     * @return a long value created.
     */
    public final long getLong() {
        try {
            return Long.parseLong(getString());
        } catch (Exception e) {}
        return 0;
    }

    /**
     * Returns a element's value as long, or default value if the element is not found.
     *
     * @param path the path to an element.
     * @param defaultValue default integer value.
     * @return a long value created.
     */
    public final long getLong(String path, long defaultValue) {
        try {
            return Long.parseLong(getString(path, null));
        } catch (Exception e) {}
        return defaultValue;
    }

    /**
     * Stores the specified long value into this element by converting original data.
     *
     * @param n the long value to be stored.
     */
    public final void setLong(long n) {
        setString(String.valueOf(n));
    }

    /**
     * Stores the specified long value into into elements which has the specified path.
     *
     * @param path the path to elements.
     * @param n the long value to be stored.
     */
    public final void setLong(String path, long n) {
        setString(path, String.valueOf(n));
    }

    /**
     * Returns a string representation of the object. In general, the toString method returns a string that "textually represents" this object. The result should be a concise but informative representation that is easy for a person to read.
     *
     * @return a string representation of the object.
     */
    public String toString() {
        return getString();
    }

    /**
     * Returns the duplicate of this element.
     *
     * @return a duplicated element.
     */
    public StructElement cloneElement() {
        StructElement element = null;
        try {
            element = (StructElement)clone();
        } catch (CloneNotSupportedException ex) {}
        return element;
    }

    /**
     * Indicates the character encoding which is used for converting.
     *
     * @param encoding the name of a character encoding.
     */
    public final void setEncoding(String encoding) {
        setEncoding(encoding, true);
    }

    /**
     * Indicates the character encoding which is used for converting.
     *
     * @param encoding the name of a character encoding.
     * @param flag true force to replace current encoding; false otherwise.
     */
    public void setEncoding(String encoding, boolean flag) {
        if (flag || encoding_ == null)
            encoding_ = encoding;
    }

} // the end of StructElement