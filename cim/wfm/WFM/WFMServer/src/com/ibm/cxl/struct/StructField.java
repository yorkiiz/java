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

/**
 * Implementation of the StructElement abstract class.
 * This class extends the super class as the simple element which contains real data with an array of bytes.
 * This class corresponds to the leaf of the structural hierarchy.
 * Usually, this class belongs to a parent element, but it is not exactly essential.
 * Note that this implementation is not synchronized. If multiple threads access an instance concurrnetly,
 * and at least one of the threads modifies the list structurally, it must be synchronized externally.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public class StructField extends StructElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5442762521104962826L;

	/** an array of bytes contains real data. */
    private byte[] by_;

    /** the size of data, in bytes. */
    private int size_;

    /**
     * Constructs a new object.
     *
     * @param name the name of a new element.
     */
    public StructField(String name) {
        this(name, 0);
    }

    /**
     * Constructs a new object.
     * The size over 0 allocates the buffer for the data, and treats the data as fixed size.
     *
     * @param name a name of the element.
     * @param size the size of data, in bytes.
     */
    public StructField(String name, int size) {
        super(name);
        size_ = (size > 0) ? size : 0;
        if (size_ > 0)
            by_ = new byte[size_];
        else
            by_ = null;
    }

    /**
     * Returns the size of this element in bytes.
     * If the size of the data is fixed size, returns its size.
     * Otherwise, returns real size of the data.
     *
     * @return the size of this element in bytes.
     */
    public final int getSize() {
        if (size_ > 0)
            return size_;
        else
            return (by_ != null) ? by_.length : 0;
    }

    /**
     * Returns an array of bytes which is contained in this element.
     *
     * @return an array of bytes which is contained in this element.
     */
    public final byte[] getBytes() {
        return (by_ != null) ? by_ : new byte[0];
    }

    /**
     * Stores an array of bytes into this element.
     *
     * @param by    an array of bytes to be stored.
     * @param iOffset    an index of the first byte to be stored.
     */
    public final void setBytes(byte[] by, int offset) {
        if (by == null || offset < 0)
            return;
        int i = by.length - offset;
        if (i > 0) {
            if (size_ <= 0)
                by_ = new byte[i];
//            for (int j = 0; j < i && j < by_.length; j++)
//                by_[j] = by[offset + j];
            if (i > by_.length) {
                System.arraycopy(by, offset, by_, 0, by_.length);
            } else {
                System.arraycopy(by, offset, by_, 0, i);
            }
        }
    }

    /**
     * Fills data in this element with the specified byte.
     *
     * @param by a value of byte to be filled.
     */
    public final void fill(byte by) {
        if (size_ > 0)
            Arrays.fill(by_, by);
        else
            by_ = null;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return a clone of this instance.
     * @exception CloneNotSupportedException if the object's class does not support the Cloneable interface.
     */
    protected Object clone() throws CloneNotSupportedException {
        StructField newobj = (StructField)super.clone();
        newobj.by_ = (by_ == null) ? null : (byte[])by_.clone();
        return newobj;
    }

} // the end of StructField