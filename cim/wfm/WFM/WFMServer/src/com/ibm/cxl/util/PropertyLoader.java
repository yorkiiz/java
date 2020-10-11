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

import java.io.InputStream;
import java.util.Properties;

/**
 * This class provides convenient methods to load external properties specified with a name.
 * You must attend to the loading mechanism. See Class.getResourceAsStream() method for detail.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public class PropertyLoader {

    /**
     * Finds a resource with a ginen name, and returns a Properties object contains properties.
     * The rules for searching resources are associated with the object of this class.
     *
     * @param name the name of the desired resource.
     * @return a Property object.
     */
    public static Properties load(String name) {
        return load(null, name);
    }

    /**
     * Finds a resource with a ginen name, and returns a Properties object contains properties.
     * The rules for searching resources are associated with the specified object.
     * ServletContext.getResourceAsStream() method will be invoked if the object is an instance of GenericServlet.
     * The object's Class.getResourceAsSteam() method will be invoked otherwise.
     * And also, an instance of this class will be used if the object is null.
     *
     * @param obj the object associated with the desired resource.
     * @param name the name of the desired resource.
     * @return a Property object.
     */
    public static Properties load(Object obj, String name) {
        Properties properties = new Properties();
        InputStream stream = null;

        try {
            if ((stream = ResourceDetector.getAsStream(obj, name)) != null)
                properties.load(stream);
        } catch (Exception ex) {
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception ex) {}
            }
        }
        return properties;
    }

} // the end of class