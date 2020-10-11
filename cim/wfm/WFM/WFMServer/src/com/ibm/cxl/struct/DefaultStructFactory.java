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

import java.util.Map;
import java.net.URL;

import com.ibm.cxl.util.PropertiesWrapper;
import com.ibm.cxl.util.ResourceDetector;

/**
 * This class is the default implementation to create structures.
 *
 * @author IBM Developer
 * @version $Revision: 2771 $ $Date: 2015-01-04 18:15:27 +0800 (周日, 04 一月 2015) $
 */
public class DefaultStructFactory {

    /** All system properties used by this class start with this */
    protected final static String SYSTEM_PREFIX = "com.ibm.cxl.struct.";

    /** The property key of validating (true/false). */
    public final static String PROPERTY_VALIDATING = SYSTEM_PREFIX + "validating";

    /** The property key of fixed size (true/false). */
    public final static String PROPERTY_FIXEDSIZE = SYSTEM_PREFIX + "fixedSize";

    /** The property key of filling space (true/false). */
    public final static String PROPERTY_FILLSPACE = SYSTEM_PREFIX + "fillSpace";

    /** Whether use a validating paser. */
    private boolean validating_ = false;

    /** Whether use fields have fixed size. */
    private boolean fixedSize_ = true;

    /** Whether fill fields with the white space. */
    //private boolean fillSpace_ = true;

    /**
     * Creates and returns an instance which is configured with the specified properties.
     *
     * @param m a Map object contains properties.
     * @return a configured instance.
     */
    public static DefaultStructFactory getInstance(Map m) {
        PropertiesWrapper p = new PropertiesWrapper(m);
        DefaultStructFactory instance = new DefaultStructFactory();
        instance.setValidating(p.getBoolean(PROPERTY_VALIDATING, false));
        instance.setFixedSize(p.getBoolean(PROPERTY_FIXEDSIZE, true));
        //instance.setFillSpace(p.getBoolean(PROPERTY_FILLSPACE, true));
        return instance;
    }

    /**
     * Constructs a new object.
     *
     */
    public DefaultStructFactory() {
    }

    /**
     * Specifies that whether the parser will validate documents as they are parsed.
     *
     * @param flag true if the parser will validate documents; false otherwise.
     */
    public final void setValidating(boolean flag) {
        validating_ = flag;
    }

    /**
     * Specifies that the parser will use fixed size.
     *
     * @param flag true if the parser will use fixed size; false otherwise.
     */
    public final void setFixedSize(boolean flag) {
        fixedSize_ = flag;
    }

    /**
     * Specifies that the parser will fill the white space.
     *
     * @param flag true if the parser will fill the white space; false otherwise.
     */
//    public final void setFillSpace(boolean flag) {
//        fillSpace_ = flag;
//    }

    /**
     * Returns a Struct which is defined in the specified path.
     *
     * @param path a String specifying the path to the struct XML file.
     * @return a StructElement object.
     * @exception StructException if any structure's errors occur.
     * @exception StructNotFoundException if the specified structure cannot be found.
     */
    public StructElement getStruct(String path) throws StructException {
        return getStruct(null, path);
    }

    /**
     * Returns a Struct which is defined in the specified path.
     *
     * @param obj an object used as the base point of reference.
     * @param path a String specifying the path to the struct XML file.
     * @return a StructElement object.
     * @exception StructException if any structure's errors occur.
     * @exception StructNotFoundException if the specified structure cannot be found.
     */
    public StructElement getStruct(Object obj, String path) throws StructException {
        try {
            URL url = ResourceDetector.get(obj, path);
            if (url != null) {
                StructParser parser = new StructParser();
                parser.setValidating(validating_);
                parser.setFixedSize(fixedSize_);
                return parser.parse(url.toString());
            }
        } catch (Exception ex) {
			ex.printStackTrace();
            throw new StructException(path, ex);
        }
        throw new StructNotFoundException(path);
    }

} // the end of class