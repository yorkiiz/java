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

import java.util.Arrays;
import java.util.ArrayList;

/**
 * Implementation of the StructElement abstract class.
 * This class extends the super class as the folder element which contains other childs elements.
 * The folder contains child elements, but it has no value itself.
 * Note that this implementation is not synchronized. If multiple threads access an instance concurrnetly,
 * and at least one of the threads modifies the list structurally, it must be synchronized externally.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2002/01/01 %
 */
public class StructFolder extends StructElement {

	private static final long serialVersionUID = 2636793721996218139L;
	/** an ArrayList contains child elements in order. */
    private ArrayList lstElement_;

    /**
     * Constructs a new, empty object.
     *
     * @param name the name of this element.
     */
    public StructFolder(String name) {
        super(name);
        lstElement_ = new ArrayList();
    }

    /**
     * Indicates the character encoding which is used for converting.
     *
     * @param encoding the name of a character encoding.
     * @param flag true force to replace current encoding; false otherwise.
     */
    public void setEncoding(String encoding, boolean flag) {
        for (int i = lstElement_.size() - 1; i >= 0; i--)
            ((StructElement)lstElement_.get(i)).setEncoding(encoding, flag);
        super.setEncoding(encoding, flag);
    }

    /**
     * Returns the size of this folder, in bytes.
     * The size is the total size of child elements in this folder.
     *
     * @return the size of this folder.
     */
    public final int getSize() {
        int total = 0;
        for (int i = lstElement_.size() - 1; i >= 0; i--)
            total += ((StructElement)lstElement_.get(i)).getSize();
        return total;
    }

    /**
     * Returns an array of bytes which is contained in this folder.
     * The array contains the data of all elements in this folder.
     *
     * @return an array of bytes which is contained in this folder.
     */
    public final byte[] getBytes() {
        int i = 0, iOffset = 0, iCount = lstElement_.size();
        byte[] by = null;
        byte[] byData = new byte[getSize()];
        Arrays.fill(byData, (byte)0);
        for (i = 0; i < iCount; i++) {
            by = ((StructElement)lstElement_.get(i)).getBytes();
//            for (j = 0; j < by.length; j++)
//                byData[iOffset + j] = by[j];
            System.arraycopy(by, 0, byData, iOffset, by.length);
            iOffset += by.length;
        }
        return byData;
    }

    /**
     * Stores an array of bytes into this folder.
     * The array is stored into child elements in this folder.
     *
     * @param by an array of bytes to be stored.
     * @param iOffset an index of the first byte to be stored.
     */
    public final void setBytes(byte[] by, int iOffset) {
        int i = 0, j = lstElement_.size();
        StructElement element = null;
        for (i = 0; i < j; i++) {
            element = (StructElement)lstElement_.get(i);
            element.setBytes(by, iOffset);
            iOffset += element.getSize();
        }
    }

    /**
     * Fills data in this element with the specified byte.
     * All elements in this folder are filled with the specified byte.
     *
     * @param by a byte to be filled.
     */
    public final void fill(byte by) {
        for (int i = lstElement_.size() - 1; i >= 0; i--)
            ((StructElement)lstElement_.get(i)).fill(by);
    }

    /**
     * Returns the number of elements in this folder.
     *
     * @return the number of elements in this folder.
     */
    public final int getElementCount() {
        return lstElement_.size();
    }

    /**
     * Returns an array of elements which match to the specified path.
     *
     * @param name the path to selected elements.
     * @return an array of elements.
     */
    public StructElement[] select(String path) {
        if (path != null) {
            ArrayList lst = null;
            int separator = path.indexOf('/');
            String name = (separator < 0) ? path : path.substring(0, separator);
            StructElement element = null;
            if (separator < 0)
                lst = new ArrayList();
            for (int i = 0, j = lstElement_.size(); i < j; i++) {
                element = (StructElement)lstElement_.get(i);
                if (name.equals(element.getName())) {
                    if (separator < 0)
                        lst.add(element);
                    else
                        break;
                }
                element = null;
            }
            if (separator < 0)
                return (StructElement[])lst.toArray(new StructElement[0]);
            if (element != null)
                return element.select(path.substring(separator + 1));
        }
        return new StructElement[0];
    }

    /**
     * Returns an element at the specified position in this folder.
     * If the element is not found, returns null.
     *
     * @param index an index of the element to be returned.
     * @return an element at the specified position in this folder.
     */
    public final StructElement element(int index) {
        return (index >= 0 && index < lstElement_.size()) ? (StructElement)lstElement_.get(index) : null;
    }

    /**
     * Returns an element which has the specified path.
     * If the element is not found, returns null.
     *
     * @param path the path to the element.
     * @return an element which has the specified path.
     */
    public final StructElement element(String path) {
        if (path != null) {
            int separator = path.indexOf('/');
            String name = (separator < 0) ? path : path.substring(0, separator);
            StructElement element = null;
            for (int i = 0, j = lstElement_.size(); i < j; i++) {
                element = (StructElement)lstElement_.get(i);
                if (name.equals(element.getName()))
                    break;
                element = null;
            }
            if (element != null)
                return (separator < 0) ? element : element.element(path.substring(separator + 1));
        }
        return null;
    }

    /**
     * Appends the specified element to the end of this folder.
     *
     * @param element an element to be appended.
     */
    public final void addElement(StructElement element) {
        if (element != null)
            lstElement_.add(element);
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return a clone of this instance.
     * @exception CloneNotSupportedException if the object's class does not support the Cloneable interface.
     */
    protected Object clone() throws CloneNotSupportedException {
        StructFolder newobj = (StructFolder)super.clone();
        newobj.lstElement_ = new ArrayList();
        for (int i = 0, j = lstElement_.size(); i < j; i++)
            newobj.addElement(((StructElement)lstElement_.get(i)).cloneElement());
        return newobj;
    }

} // the end of StructFolder