package com.ibm.lcd.ppt.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ibm.cxl.util.PropertiesWrapper;
import com.ibm.lcd.commons.LcdConfig;

public abstract class PPTSQL {

	// -------------------------------------------------------- Class Variables

	/** Configuration. */
	protected static PropertiesWrapper config = LcdConfig.getConfig();

	/** Commons logging instance. */
	//private static Log log = LogFactory.getLog(PPTSQL.class);

	/** Paramters for message service. */
	// private static boolean usexml = config.getBoolean(LcdConfig.MS_USEXML,
	// false);
	// private static long timeout = config.getLong(LcdConfig.MS_TIMEOUT, 5000);
	// private static String qcName = config.getProperty("qc.ppt.name");
	// private static String qcUid = config.getProperty("qc.ppt.userid");
	// private static String qcPwd = config.getProperty("qc.ppt.password");
	protected static String dsPPT = config.getProperty("ds.ppt");
	protected static String dsHIS = config.getProperty("ds.his");

	protected static final int hourOfDay = 24;

	protected Connection conn = null;
	protected PreparedStatement stmt = null;
	protected ResultSet rs = null;
	protected ResultSet inrs = null;

	protected String ds;

	protected void close() throws Exception {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (conn != null) {
			conn.close();
		}
	}
}
