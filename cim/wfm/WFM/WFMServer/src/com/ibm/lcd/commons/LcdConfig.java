
package com.ibm.lcd.commons;

import java.util.Properties;


import com.ibm.cxl.util.PropertyLoader;
import com.ibm.cxl.util.PropertiesWrapper;

public class LcdConfig {

	// ----------------------------------------------------- Manifest Constants

	public static final String MS_USEXML = "ms.usexml";

	public static final String MS_TIMEOUT = "ms.timeout";

	/** the key indicates security key. */
	public final static String SECURITY_KEY = "security.key";

	/** the key indicates security effect key. */
	public final static String SECURITY_EFFECT_KEY = "security.effect";

	// -------------------------------------------------------- Class Variables


	/** Original Properties object. */
	private static Properties config = null;

	/** Synchronized object. */
	private static Object syncObject = new Object();

	// ----------------------------------------------------------- Constructors

	/**
	 * Constructs a new object.
	 * NOTE: Cannot create an instance of this class.
	 */
	private LcdConfig() {
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Initializes this configuration.
	 * 
	 * @param obj The object associated with the desired configuration.
	 * @param path The path name of the configuration.
	 */
	public static void init(Object obj, String path) {
		synchronized (syncObject) {
			config = PropertyLoader.load(obj, path);
		}
	}

	/**
	 * Returns a copy of configuration.
	 * 
	 * @return a copy of configuration.
	 */
	public static PropertiesWrapper getConfig() {
		return new PropertiesWrapper(config);
	}

}
