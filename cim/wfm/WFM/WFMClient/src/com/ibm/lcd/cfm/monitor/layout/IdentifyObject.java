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
package com.ibm.lcd.cfm.monitor.layout;

/**
 * This class is the base class for each class which represents its state.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2002/01/01 %
 */
public abstract class IdentifyObject {

    private final String id;
    private String name = null;

    /**
     * idのセット
     *
     * @param id
     */
    public IdentifyObject(String id) {
        this.id = id;
    }

    /**
     * idの取得
     *
     * @return id
     */
    public final String getId() {
        return id;
    }

    /**
     * 名前のセット
     *
     * @param name
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * 名前の取得
     *
     * @return name
     */
    public final String getName() {
        return this.name;
    }

    /**
     *
     * @param obj
     * @return boolean
     */
    public final boolean equals(Object obj) {
        if (id.equals(obj))
            return true;
        return false;
    }

    /**
     * 
     * @return 
     */
    public final String toString() {
        return (this.name != null) ? (this.name + " (" + this.id + ")") : this.id;
    }

}
