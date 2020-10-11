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

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * This class represents an exception caused by any exception.
 * This class can collect exceptions of different kind.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public abstract class WrappedException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8728936911488467524L;
	private Throwable cause = null;

    /**
     * Constructs an exception with no specified detail information.
     *
     */
    public WrappedException() {
        super();
    }

    /**
     * Constructs an exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public WrappedException(String message) {
        super(message);
    }

    /**
     * Constructs an exception with the source exception.
     *
     * @param cause the source exception.
     */
    public WrappedException(Throwable cause) {
        super();
        this.cause = cause;
    }

    /**
     * Constructs an exception with the specified detail message and the source exception.
     *
     * @param message the detail message.
     * @param cause the source exception.
     */
    public WrappedException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    /**
     * Returns the source exception that causes this exception, or null there is no source exception.
     *
     * @return the source exception.
     */
    public Throwable getCause() {
        return cause;
    }

    /**
     * Prints this Throwable and its backtrace to the specified print stream.
     *
     * @param ps PrintStream to use for output.
     */
    public void printStackTrace(PrintStream ps) {
        super.printStackTrace(ps);
        if (cause != null) {
            cause.printStackTrace(ps);
        }
    }

    /**
     * Prints this Throwable and its backtrace to the specified print writer.
     *
     * @param pw PrintWriter to use for output.
     */
    public void printStackTrace(PrintWriter pw) {
        super.printStackTrace(pw);
        if (cause != null) {
            cause.printStackTrace(pw);
        }
    }

} // the end of class