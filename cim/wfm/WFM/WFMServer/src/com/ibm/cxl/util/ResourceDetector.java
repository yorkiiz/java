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
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;

/**
 * This class supports access to the resource in the method not depending on a position.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public class ResourceDetector {

    /**
     * Returns a URL to the resource that is mapped to a specified name.
     *
     * @param path a String specifying the path to the resource.
     * @return the resource located at the named path, or null if there is no resource at the specified path.
     * @exception MalformedURLException if the pathname is not given in the correct form.
     */
    public static URL get(String name) throws MalformedURLException {
        return get(null, name);
    }

    /**
     * Returns a URL to the resource that is mapped to a specified name.
     *
     * @param obj an object used as the base point of reference.
     * @param path a String specifying the path to the resource.
     * @return the resource located at the named path, or null if there is no resource at the specified path.
     * @exception MalformedURLException if the pathname is not given in the correct form.
     */
    public static URL get(Object obj, String path) throws MalformedURLException {
        URL url = null;
        if (obj == null)
            url = Thread.currentThread().getContextClassLoader().getResource(path);
        else if (obj instanceof Servlet)
            url = ((Servlet)obj).getServletConfig().getServletContext().getResource(path);
        else if (obj instanceof ServletContext)
            url = ((ServletContext)obj).getResource(path);
        else
            url = obj.getClass().getResource(path);
        return url;
    }

    /**
     * Returns the resource located at the named path as an InputStream object.
     *
     * @param path a String specifying the path to the resource.
     * @return the InputStream object, or null if no resource exists at the specified path.
     */
    public static InputStream getAsStream(String path) {
        return getAsStream(null, path);
    }

    /**
     * Returns the resource located at the named path as an InputStream object.
     *
     * @param obj an object used as the base point of reference.
     * @param path a String specifying the path to the resource.
     * @return the InputStream object, or null if no resource exists at the specified path.
     */
    public static InputStream getAsStream(Object obj, String path) {
        InputStream stream = null;
        if (obj == null)
            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        else if (obj instanceof Servlet)
            stream = ((Servlet)obj).getServletConfig().getServletContext().getResourceAsStream(path);
        else if (obj instanceof ServletContext)
            stream = ((ServletContext)obj).getResourceAsStream(path);
        else
            stream = obj.getClass().getResourceAsStream(path);
        return stream;
    }

} // the end of class