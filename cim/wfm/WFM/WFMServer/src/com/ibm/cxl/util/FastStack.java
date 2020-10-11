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
package com.ibm.cxl.util;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * The FastStack class represents a last-in-first-out (LIFO) stack of objects as well as the java.util.Stack.
 * It extends class java.util.ArrayList with same operations that java.util.Stack provides.
 * Note that this implementation is not synchronized. If multiple threads access an instance concurrnetly,
 * and at least one of the threads modifies the list structurally, it must be synchronized externally.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public class FastStack extends ArrayList {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2131567158828344896L;

	/**
     * Creates an empty stack.
     *
     */
    public FastStack() {
        super();
    }

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param item the item to be pushed onto this stack.
     * @return the item argument.
     */
    public Object push(Object item) {
        add(item);
        return item;
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.
     *
     * @return the object at the top of this stack.
     * @exception EmptyStackException if this stack is empty.
     */
    public Object pop() throws EmptyStackException {
        if (size() == 0)
            throw new EmptyStackException();
        return (remove(size() - 1));
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     *
     * @return the object at the top of this stack.
     * @exception EmptyStackException if this stack is empty.
     */
    public Object peek() throws EmptyStackException {
        if (size() == 0)
            throw new EmptyStackException();
        return (get(size() - 1));
    }

    /**
     * Tests if this stack is empty.
     *
     * @return true if and only if this stack contains no items; false otherwise.
     */
    public boolean empty() {
        return isEmpty();
    }

    /**
     * Returns the 1-based position where an object is on this stack. If the object o occurs as an item in this stack,
     * this method returns the distance from the top of the stack of the occurrence nearest the top of the stack.
     * The topmost item on the stack is considered to be at distance 1. The equals method is used to compare o to the items in this stack.
     *
     * @param o the desired object.
     * @return the 1-based position from the top of the stack where the object is located; the return value -1 indicates that the object is not on the stack.
     */
    public int search(Object o) {
        int i = lastIndexOf(o);
        return ((i >= 0) ? (size() - i + 1) : -1);
    }

} // the end of FastStack