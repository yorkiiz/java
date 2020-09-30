/*
 * (C) Copyright IBM Corp. 2002  All rights reserved.
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

/**
 * This class will be thrown when an application tries to load structure XML.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2002/01/01 %
 */
public class StructNotFoundException extends StructException {

	private static final long serialVersionUID = 496587771799152338L;

	/**
     * Constructs an exception with no specified detail information.
     *
     */
    public StructNotFoundException() {
        super();
    }

    /**
     * Constructs an exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public StructNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs an exception with the source exception.
     *
     * @param cause the source exception.
     */
    public StructNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs an exception with the specified detail message and the source exception.
     *
     * @param message the detail message.
     * @param cause the source exception.
     */
    public StructNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

} // the end of class