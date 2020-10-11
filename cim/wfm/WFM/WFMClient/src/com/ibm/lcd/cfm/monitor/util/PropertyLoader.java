
package com.ibm.lcd.cfm.monitor.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	
    private static PropertyLoader singleton__ = new PropertyLoader();

    public static Properties load(String name) {
        return load(null, name);
    }

    public static Properties load(Object obj, String name) {
    	
        Properties properties = new Properties();
        InputStream stream = null;

        if (obj == null)
            obj = singleton__;
        try {
            stream = obj.getClass().getResourceAsStream(name);
            if (stream != null)
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

}