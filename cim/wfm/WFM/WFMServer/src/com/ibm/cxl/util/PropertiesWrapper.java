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

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * This class wraps a Properties object, and extends its functions.
 *
 * @author IBM Developer
 * @version $Revision: 2771 $ $Date: 2015-01-04 18:15:27 +0800 (周日, 04 一月 2015) $
 */
public class PropertiesWrapper extends Properties {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3466800787883689837L;

	/**
     * Creates an empty object.
     *
     */
    public PropertiesWrapper() {
        super();
    }

    /**
     * Constructs a new object with the same mapping as the given map.
     *
     * @param m the map whose mappings are to be placed in this property list.
     */
    public PropertiesWrapper(Map m) {
        super();
        if (m != null) {
            Map.Entry e = null;
            Object key = null, val = null;
            for (Iterator i = m.entrySet().iterator(); i.hasNext(); ) {
                e = (Map.Entry)i.next();
                key = e.getKey();
                val = e.getValue();
                if (key != null && val != null)
                    setProperty((String)key, (String)val);
            }
        }
    }

    /**
     * Creates an object with the specified defaults.
     *
     * @param defaults the defaults.
     */
    public PropertiesWrapper(Properties defaults) {
        super(defaults);
    }

    /**
     * Searches for the property as an integer with the specified key in this property list.
     * The method returns 0 if the property is not found.
     *
     * @param key the property key.
     * @return the value in this property list with the specified key.
     */
    public int getInteger(String key) {
        return getInteger(key, 0);
    }

    /**
     * Searches for the property as an integer with the specified key in this property list.
     * The method returns the default value if the property is not found.
     *
     * @param key the property key.
     * @param defaultValue a default value.
     * @return the value in this property list with the specified key.
     */
    public int getInteger(String key, int defaultValue) {
        String s = getProperty(key);
        if (s != null) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException ex) {}
        }
        return defaultValue;
    }

    /**
     * Puts the spcified integer value into the property list with the specified key.
     * The value is evaluated as a string.
     *
     * @param key the property key.
     * @param value a value.
     */
    public void setInteger(String key, int value) {
        setProperty(key, String.valueOf(value));
    }

    /**
     * Searches for the property as a long with the specified key in this property list.
     * The method returns 0 if the property is not found.
     *
     * @param key the property key.
     * @return the value in this property list with the specified key.
     */
    public long getLong(String key) {
        return getLong(key, 0);
    }

    /**
     * Searches for the property as a long with the specified key in this property list.
     * The method returns the default value if the property is not found.
     *
     * @param key the property key.
     * @param defaultValue a default value.
     * @return the value in this property list with the specified key.
     */
    public long getLong(String key, long defaultValue) {
        String s = getProperty(key);
        if (s != null) {
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException ex) {}
        }
        return defaultValue;
    }

    /**
     * Puts the spcified long value into the property list with the specified key.
     * The value is evaluated as a string.
     *
     * @param key the property key.
     * @param value a value.
     */
    public void setLong(String key, long value) {
        setProperty(key, String.valueOf(value));
    }

    /**
     * Searches for the property as a boolean with the specified key in this property list.
     * The method returns false if the property is not found.
     *
     * @param key the property key.
     * @return the value in this property list with the specified key.
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * Searches for the property as a boolean with the specified key in this property list.
     * The method returns the default value if the property is not found.
     *
     * @param key the property key.
     * @param defaultValue a default value.
     * @return the value in this property list with the specified key.
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        String s = getProperty(key);
        if (s != null)
            return Boolean.valueOf(s).booleanValue();
        return defaultValue;
    }

    /**
     * Puts the spcified boolean value into the property list with the specified key.
     * The value is evaluated as a string.
     *
     * @param key the property key.
     * @param value a value.
     */
    public void setBoolean(String key, boolean value) {
        setProperty(key, String.valueOf(value));
    }

} // the end of class