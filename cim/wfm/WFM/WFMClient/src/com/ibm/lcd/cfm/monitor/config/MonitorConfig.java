//*********************************************************************************
//
// SYSTEM        : IBM MES system
//
// PROGRAM NAME  : MonitorConfig.java
//
// Outline       :
//
// (c) Copyright 2005, International Business Machines Corp
//
// Modification history:
//
// DATE        LEVEL  NAME             COMMENT
// ----------  -----  ---------------  --------------------------------------------
// 2005/08/01  A0.00  IBM              Initial Release
// 2014/03/14  C0.00  Nico Cai         Add loaded carrier info on port
//*********************************************************************************
package com.ibm.lcd.cfm.monitor.config;

import java.applet.Applet;
import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.ibm.lcd.cfm.monitor.util.StringUtil;

public final class MonitorConfig {

	public final static String TRACKING_URL = "monitor_tracking_url";

	public final static String LAYOUT_URL = "monitor_layout_url";
	public final static String LAYOUT_XML = "monitor_layout_xml";

	public final static String PATH_LOGOFF = "monitor_path_logoff";
	public final static String PATH_STATUS = "monitor_path_status";
	public final static String MARQUEE_TEXT = "monitor_marquee_text";

	public final static String LINK_TARGET = "monitor_link_target";
	public final static String INTERVAL = "monitor_interval";
	public final static String WAIT_TIME = "monitor_wait_time";
	
	public final static String SCROLLING_SPEED = "scroll.text.initialspeed";
	public final static String SCROLLING_BACKGROUND = "scroll.text.background";
	public final static String SCROLLING_FOREGROUND = "scroll.text.foreground";

	public final static String MONITOR_LEGEND_LOCATION = "monitor_legend_location";
	
	public final static String IMG_ALERT = "img_alrt";
	public final static String IMG_SHELF = "img_shelf";
	public final static String IMG_E84 = "img_e84";
	public final static String IMG_WIP = "img_wip";
	public final static String IMG_WIPNUM = "img_wipnum";
	public final static String IMG_ABNORMAL = "img_abnd";
	public final static String IMG_ASSIGN = "img_asgn";
	public final static String IMG_ASSIGN_LD = "img_asgn_ld";
	public final static String IMG_ASSIGN_UL = "img_asgn_ul";
	public final static String IMG_STAT_OK = "img_stat_ok";
	public final static String IMG_STAT_NG = "img_stat_ng";
	public final static String IMG_STAT_ON = "img_stat_on";
	public final static String IMG_STAT_OFF = "img_stat_off";
	public final static String IMG_WIP_OVAL = "img_wip_oval"; 
	
	public final static String IMG_WIP_OVAL_EQP = "img_wip_oval_eqp"; 
	public final static String IMG_WIP_OVAL_STK = "img_wip_oval_stk";
	public final static String IMG_CHAMBER = "img_chamber";
	public final static String IMG_ZOOM_IN = "img_zoom_in";
	public final static String IMG_ZOOM_OUT = "img_zoom_out";
	public final static String IMG_ORIGINAL_SIZE = "img_original_size";
	
	

	//public final static String PATH_EQPT_ID = "monitor_path_eqptId";
	//public final static String PATH_STOCKER_ID = "monitor_path_stockerId";
//	public final static String PATH_DEVICE1 = "monitor_path_device1";
//	public final static String PATH_DEVICE2 = "monitor_path_device2";
//	public final static String PATH_DEVICE3 = "monitor_path_device3";
//	public final static String PATH_DEVICE4 = "monitor_path_device4";
//	public final static String PATH_DEVICE5 = "monitor_path_device5";
//	public final static String PATH_DEVICE6 = "monitor_path_device6";
//	public final static String PATH_DEVICE7 = "monitor_path_device7";
//	public final static String PATH_DEVICE8 = "monitor_path_device8";
//	public final static String PATH_DEVICE9 = "monitor_path_device9";
//	public final static String PATH_DEVICE10 = "monitor_path_device10";
//	public final static String PATH_CHAMBERLIST = "monitor_path_chamberList";
//	public final static String PATH_CHAMBERMAXLIST = "monitor_path_chamberMaxList";
	//public final static String PATH_WIPLIST1 = "monitor_path_wipList1";
//	public final static String PATH_SHELFLIST = "monitor_path_shelfList";
//	public final static String PATH_BUFFERSTOCKLIST = "monitor_path_bufferStockList";

	public final static String COOKIE = "monitor_cookie";

	public final static String LANGUAGE = "monitor_language";

	public final static String CONTEXT_PATH = "monitor_context_path";

	public final static String BUNDLE_BASE = "FloorMonitorResource";

	public final static String EQPT_ID 		= "EQPT_ID";
	public final static String PORT_ID 		= "PORT_ID";
	public final static String PORT_STAT 	= "PORT_STAT";
	public final static String WIP_CNT 		= "WIP_CNT";
	public final static String INPR_CNT 	= "INPR_CNT";
	public final static String CRR_ID		= "CRR_ID";			//C0.00
	
	//public final static String CRR_ASGN 	= "CRR_ASGN";
	//public final static String LD_CRR_ASGN = "LD_CRR_ASGN";
	//public final static String UL_CRR_ASGN = "UL_CRR_ASGN";
	//public final static String ABND_CNT 	= "ABND_CNT";

	
	// ------------------------------------------------------ Class Variables

	private static Hashtable config = new Hashtable();

	private static String[] alertKeys = null;

	private static WipConfig[] wipConfigs = null;

	private static SummaryConfig[] summaryConfigs = null;

	private static Hashtable imageTable = new Hashtable();

	private static Hashtable iconTable = new Hashtable();

	private static ResourceBundle messageResource = null;

	//private static MonitorListConfig[] monitorListConfigs = null;

	public final static void initSummaryConfig(Map map) {

		String s = (String) map.get("summary");
		if (s == null || s.length() <= 0)
			return;
		String trackingURL = get(TRACKING_URL, null);
		String prefix = null;
		StringTokenizer token = new StringTokenizer(s, ",");
		SummaryConfig summaryConfig = null;
		ArrayList list = new ArrayList();

		while (token.hasMoreTokens()) {
			prefix = "summary." + token.nextToken().trim();
			summaryConfig = new SummaryConfig();
			summaryConfig.uri = resolveURI(trackingURL, (String) map.get(prefix + ".uri"));
			summaryConfig.titleKey = (String) map.get(prefix + ".titleKey");
			s = (String) map.get(prefix + ".type");
			if ("graph".equalsIgnoreCase(s))
				summaryConfig.type = 1;
			else
				summaryConfig.type = 0;
			summaryConfig.parseColors((String) map.get(prefix + ".color"));
			list.add(summaryConfig);
		}
		summaryConfigs = (SummaryConfig[]) list.toArray(new SummaryConfig[list.size()]);

	}

	public final static SummaryConfig[] getSummaryConfig() {
		if (summaryConfigs != null)
			return summaryConfigs;
		else
			return new SummaryConfig[0];
	}

//	public final static void initMonitorListConfig(Map map) {
//
//		String m = (String) map.get("monitor");
//		if (m == null || m.length() <= 0)
//			return;
//		String trackingURL = get(TRACKING_URL, null);
//		String prefix = null;
//		StringTokenizer token = new StringTokenizer(m, ",");
//		MonitorListConfig monitorListConfig = null;
//		ArrayList list = new ArrayList();
//
//		while (token.hasMoreTokens()) {
//			prefix = "monitor." + token.nextToken().trim();
//			monitorListConfig = new MonitorListConfig();
//			monitorListConfig.uri = resolveURI(trackingURL, (String) map.get(prefix + ".uri"));
//			monitorListConfig.titleKey = (String) map.get(prefix + ".titleKey");
//			list.add(monitorListConfig);
//		}
//		monitorListConfigs = (MonitorListConfig[]) list.toArray(new MonitorListConfig[list.size()]);
//	}

//	public final static MonitorListConfig[] getMonitorListConfig() {
//		final String METHOD_NAME = "getMonitorListConfig";
//		LogUtil.out(true, "MonitorConfig", METHOD_NAME, "START");
//
//		if (monitorListConfigs != null)
//			return monitorListConfigs;
//		else
//			return new MonitorListConfig[0];
//	}

	// ------------------------------------------------- Public Methods (WIP)

	/**
	 * This internal class represents a wip configuration.
	 */
	private static class WipConfig {
		final String CLASS_NAME = "MonitorConfig$WipConfig";

		public int count = 0; // maximum count of wip.
		public String key = ""; // the key of wip's image.

		public WipConfig() {
		}
	}

	public final static void initWipConfig(Component component, Map map) {

		String s = (String) map.get("wip");
		if (s == null || s.length() <= 0)
			return;
		// String key = null;
		StringTokenizer token = new StringTokenizer(s, ",");
		int i = 0;
		WipConfig wipConfig = null;
		ArrayList list = new ArrayList();

		while (token.hasMoreTokens()) {
			s = token.nextToken().trim();
			try {
				i = Integer.parseInt(s);
			} catch (Exception ex) {
				continue;
			}
			wipConfig = new WipConfig();
			wipConfig.count = i;
			wipConfig.key = "wip." + s;
			s = (String) map.get(wipConfig.key);
			addImage(component, wipConfig.key, s); // Add image table.
			list.add(wipConfig);
		}
		wipConfigs = (WipConfig[]) list.toArray(new WipConfig[list.size()]);
	}

	/**
	 * Returns the key of a wip image with the specified count.
	 * 
	 * @param count
	 *            wip count.
	 * @return the key if the corresponding image is defined, otherwise null.
	 */
	public final static String getWipKey(int count) {

		String s = null;
		if (wipConfigs != null && count > 0) {
			for (int i = 0; i < wipConfigs.length; i++) {
				if (count >= wipConfigs[i].count) {
					s = wipConfigs[i].key;
				} else {
					break;
				}
			}
		}
		return s;
	}

	public final static void initAlertConfig(Component component, Map map) {

		String s = (String) map.get("alert");
		if (s == null || s.length() <= 0)
			return;
		String key = null;
		StringTokenizer token = new StringTokenizer(s, ",");
		int i = 0;
		alertKeys = new String[token.countTokens()];
		while (token.hasMoreTokens()) {
			key = "alert." + token.nextToken().trim();
			s = (String) map.get(key);
			addImage(component, key, s); // Add image table.
			alertKeys[i++] = key;
		}
	}

	/**
	 * Returns the key of an alert image with the specified level.
	 * 
	 * @param level
	 *            alert level (begin level is 1).
	 * @return the key if the level is defined, otherwise null.
	 */
	public final static String getAlertKey(int level) {

		if (alertKeys != null && level > 0) {
			int idx = level - 1;
			if (idx >= alertKeys.length)
				idx = alertKeys.length - 1;
			return alertKeys[idx];
		}

		return null;
	}

	// ---------------------------------------------- Public Methods (Images)

	/**
	 * Initialize application specific images.
	 * 
	 * @param component
	 *            the component resolve the path name.
	 * @return true if the load succeeded, false otherwise.
	 */
	public final static boolean initApplicationImage(Component component) {

		addImage(component, MonitorConfig.IMG_ALERT, "images/alrt.gif");
		// L1.8 addImage(component, MonitorConfig.IMG_LONG_TIME_ALERT,
		// "images/long_p.gif"); /* L1.3 */
		addImage(component, MonitorConfig.IMG_SHELF, "images/shelf.gif");// L1.8
		addImage(component, MonitorConfig.IMG_E84, "images/e84.gif"); /* L1.4 */
		addImage(component, MonitorConfig.IMG_WIP, "images/wip.gif");
		addImage(component, MonitorConfig.IMG_WIPNUM, "images/wip_num.gif"); /* L1.1 */
		addImage(component, MonitorConfig.IMG_WIP_OVAL, "images/wip_oval.gif"); /* L1.1 */
		addImage(component, MonitorConfig.IMG_WIP_OVAL_EQP, "images/wip_oval1.gif"); /* L1.2 */
		addImage(component, MonitorConfig.IMG_WIP_OVAL_STK, "images/wip_oval2.gif"); /* L1.2 */
		addImage(component, MonitorConfig.IMG_ABNORMAL, "images/abnd.gif");
		addImage(component, MonitorConfig.IMG_ASSIGN, "images/asgn.gif");
		addImage(component, MonitorConfig.IMG_ASSIGN_LD, "images/asgn_ld.gif");
		addImage(component, MonitorConfig.IMG_ASSIGN_UL, "images/asgn_ul.gif");
		addImage(component, MonitorConfig.IMG_STAT_OK, "images/st_ok.gif");
		addImage(component, MonitorConfig.IMG_STAT_NG, "images/st_ng.gif");
		addImage(component, MonitorConfig.IMG_STAT_ON, "images/st_on.gif");
		addImage(component, MonitorConfig.IMG_STAT_OFF, "images/st_off.gif");
		addImage(component, MonitorConfig.IMG_CHAMBER, "images/chamber.gif");
		addImage(component, MonitorConfig.IMG_ZOOM_IN, "images/zoom_in.gif");
		addImage(component, MonitorConfig.IMG_ZOOM_OUT, "images/zoom_out.gif");
		addImage(component, MonitorConfig.IMG_ORIGINAL_SIZE, "images/original_size.gif");

		return loadImage(component);
	}

	/**
	 * Append an image into the image table. Before you use images in the image
	 * table, you must invoke the loadImage() method.
	 * 
	 * @param component
	 *            the component resolve the path name.
	 * @param name
	 *            the key name of the image.
	 * @param path
	 *            the relative path to the image.
	 */
	public final static void addImage(Component component, String name, String path) {

		try {
			Image image = null;
			if (component instanceof Applet) {
				Applet applet = (Applet) component;
				image = applet.getImage(applet.getCodeBase(), path);
			} else if (component != null) {
				URL url = component.getClass().getResource("/" + path);
				if (url != null)
					image = Toolkit.getDefaultToolkit().getImage(url);
			}
			if (image != null)
				imageTable.put(name, image);
		} catch (Exception ex) {
		} 
	}

	/**
	 * Load images in the image table.
	 * 
	 * @param component
	 *            the component tracks loading images.
	 * @return true if the load succeeded, false otherwise.
	 */
	public final static boolean loadImage(Component component) {

		MediaTracker tracker = new MediaTracker(component);
		Image image = null;
		for (Enumeration enumeration = imageTable.keys(); enumeration.hasMoreElements();) {
			image = getImage((String) enumeration.nextElement());
			if (image != null)
				tracker.addImage(image, 0);
		}
		try {
			tracker.waitForAll();
			return (tracker.statusAll(true) == MediaTracker.COMPLETE);
		} catch (InterruptedException ex) {

		} 
		return false;
	}

	/**
	 * Returns the image with the specified key name.
	 * 
	 * @param name
	 *            the key name of the image.
	 * @return the image, or null if the image not found.
	 */
	public final static Image getImage(String name) {
		if (name != null) {
			return (Image) imageTable.get(name);
		}
		return null;
	}

	/**
	 * Returns an image with the specified name in the image table, as an Icon.
	 * 
	 * @param name
	 *            the key name of the image.
	 * @return the icon, or null if the image not found.
	 */
	public final static Icon getIcon(String name) {
		if (name == null)
			return null;
		Icon icon = (Icon) iconTable.get(name); // Get from the cache.
		if (icon == null) {
			Image image = getImage(name);
			if (image != null) {
				icon = new ImageIcon(image);
				iconTable.put(name, icon);
			}
		}
		return icon;
	}

	// --------------------------------------------- Public Methods (Message)

	/**
	 * Initializes the message resource.
	 * 
	 * @param locale
	 *            the locale.
	 */
	public final static boolean loadMessageResource(Locale locale) {
		
		boolean rtnVal = false;
		try {
			if (locale == null)
				locale = Locale.getDefault();
			messageResource = ResourceBundle.getBundle(BUNDLE_BASE, locale);
			rtnVal = true;
		} catch (MissingResourceException ex) {
			ex.printStackTrace();
			rtnVal = false;
		}
		return rtnVal;
	}

	/**
	 * Returns the message with the specified key.
	 * 
	 * @param key
	 *            the key of message.
	 * @return the message, or blank string if the key is not found.
	 */
	public final static String getMessage(String key) {
		
		if (messageResource == null) return "";
		
		String rtnVal = null;
		try
		{
			rtnVal = StringUtil.getCheckedValue(messageResource.getString(key), "");
		}catch(java.util.MissingResourceException ex){
			rtnVal = "";
		}
		
		return rtnVal;
	}

	// ---------------------------------------------- Public Methods (Config)

	public final static void initConfig(Map map) {
		config.putAll(map);
	}


	public final static String get(Object key, String def) {
		String rtn = config.get(key).toString();
		if(rtn != null)
			return rtn;
		return def;
	}

	public final static int getAsInteger(Object key, int def) {
		int rtn = def;
		try {
			rtn = Integer.parseInt(config.get(key).toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtn;
	}

	public final static long getAsLong(Object key, long def) {
		long rtn = def;
		try {
			rtn = Long.parseLong(config.get(key).toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtn;
	}

	public final static boolean getAsBoolean(Object key, boolean def) {
		boolean rtn = def;
		try {
			rtn = Boolean.getBoolean(config.get(key).toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rtn;
	}

	public final static String getAbsoluteURI(String pathKey) {
		return getAbsoluteURI(TRACKING_URL, pathKey);
	}

	public final static String getAbsoluteURI(String urlKey, String pathKey) {
		String path = get(pathKey, "");
		if (path.indexOf("://") >= 0)
			return path;
		String url = get(urlKey, "");
		return resolveURI(url, path);
	}

	private static String resolveURI(String uri, String path) {
		StringBuffer sb = new StringBuffer();
		if (uri != null)
			sb.append(uri);
		if (path != null) {
			if (uri != null) {
				if (uri.endsWith("/") && path.startsWith("/")) {
					sb.append(path.substring(1));
				} else if (!uri.endsWith("/") && !path.startsWith("/")) {
					sb.append('/');
					sb.append(path);
				} else {
					sb.append(path);
				}
			} else {
				sb.append(path);
			}
		}
		return sb.toString();
	}
}
