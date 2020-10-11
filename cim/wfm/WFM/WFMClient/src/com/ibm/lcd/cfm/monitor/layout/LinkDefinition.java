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
 * =====================================================================
 * Modification History
 * ---------------------------------------------------------------------
 * L1.1      HsiaoYa        add sub type 
 */
package com.ibm.lcd.cfm.monitor.layout;

import java.util.ArrayList;

/**
 * This class is the definition of an link.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2002/01/01 %
 */
public class LinkDefinition extends IdentifyObject {

    public int type = 0;
    public int sub_type=0;    				/* L1.1 */
    public String url = null;

    private ArrayList parameters = new ArrayList();

    /**
     * Constructs a new object.
     *
     */
    public LinkDefinition(String id) {
        super(id);
    }

    /**
     *
     */
    public final void addLinkParameter(LinkParameter linkParameter) {
        if (linkParameter != null)
            parameters.add(linkParameter);
    }

    /**
     *
     */
    public final LinkParameter[] getLinkParameters() {
        LinkParameter[] array = new LinkParameter[parameters.size()];
        return (LinkParameter[])parameters.toArray(array);
    }

}