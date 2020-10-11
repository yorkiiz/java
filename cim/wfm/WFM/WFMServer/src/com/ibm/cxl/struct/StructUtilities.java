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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class provides structure utilities.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public class StructUtilities {

    /**
     * Populate the values in the specified into elements in the specified structure.
     *
     * @param map a Map object contains values which are populated into the structure.
     * @param struct an element to be populated.
     */
    public final static void populate(Map map, StructElement struct) {
        if (struct != null && map != null) {
            String key = null;
            for (Iterator itr = map.keySet().iterator(); itr.hasNext(); ) {
                key = (String)itr.next();
                struct.setString(key, (String)map.get(key));
            }
        }
    }

    /**
     * Convert the structure to the Map object.
     *
     * @param struct an element to be converted.
     * @return a Map object converted.
     */
    public final static Map toMap(StructElement struct) {
        Map map = new HashMap();
        if (struct != null) {
            StructElement element = null;
            for (int i = 0, j = struct.getElementCount(); i < j; i++) {
                element = struct.element(i);
                //element.setEncoding("UTF-8");
                if (element instanceof StructField)
                    map.put(element.getName(), element.getString());
            }
        }
        return map;
    }

} // the end of StructUtilities
