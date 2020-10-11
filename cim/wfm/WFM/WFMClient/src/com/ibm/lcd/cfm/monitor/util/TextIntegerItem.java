/*
 * $Header:
 * $Revision: 1.0
 * $Date: 2002/02/16
 *
 * ====================================================================
 *
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
package com.ibm.lcd.cfm.monitor.util;

/**
 * This class represents the set of a text and an integer.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2002/01/01 %
 */
public class TextIntegerItem {

    private final String text;
    private final int number;

    /**
     *
     */
    public TextIntegerItem(String text, int number) {
        this.text = new String(text);
        this.number = number;
    }

    /**
     *
     */
    public final int getInteger() {
        return this.number;
    }

    /**
     *
     */
    public final String getText() {
        return this.text;
    }

    /**
     *
     */
    public final String toString() {
        return (this.text != null) ? this.text : "";
    }

}