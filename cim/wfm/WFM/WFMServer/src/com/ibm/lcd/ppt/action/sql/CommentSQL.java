package com.ibm.lcd.ppt.action.sql;

import java.util.ArrayList;
import java.util.HashMap;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

import com.ibm.cxl.db.DataSourcePool;
import com.ibm.lcd.ppt.action.PPTSQL;

import com.ibm.cxl.util.PropertiesWrapper;
import com.ibm.lcd.commons.LcdConfig;

public class CommentSQL extends PPTSQL {

	//private static Log log = LogFactory.getLog(CommentSQL.class);

	protected static PropertiesWrapper config = LcdConfig.getConfig();

	public ArrayList getAlarmList(String eqpt_id, int displimit) throws Exception {

		//log.debug("START");

		ArrayList rtnlist = new ArrayList();
		HashMap rtnmap = null;
		int count = 0;

		try {
			conn = DataSourcePool.getInstance().getConnection(dsHIS);

			StringBuffer sql = new StringBuffer();
			sql
					.append("SELECT EQPT_ID,ALRT_CODE,ALRT_LVL,ALRT_COMMENT,CFM_USER_ID,DATE(CFM_TIMESTAMP) AS CFM_DATE,TIME(CFM_TIMESTAMP) AS CFM_TIME,"
							+
							"DATE(RPT_TIMESTAMP) AS RPT_DATE,TIME(RPT_TIMESTAMP) AS RPT_TIME,RPT_SOURCE,ALERT_ON_OFF_FLG "
							+
							"FROM "
							+ config.getProperty("schema.his")
							+ "."
							+ "BALERT "
							+ "WHERE EQPT_ID = ? AND ALERT_ON_OFF_FLG='Y' " + "ORDER BY RPT_DATE DESC, RPT_TIME DESC ");
			sql.append("WITH UR ");

			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, eqpt_id);
			rs = stmt.executeQuery();

			while (rs.next() && (count <= displimit)) {
				rtnmap = new HashMap();

				String local_eqpt_id = rs.getString("EQPT_ID");
				String alrt_code = rs.getString("ALRT_CODE");
				String alrt_lvl = rs.getString("ALRT_LVL");
				String alrt_comment = rs.getString("ALRT_COMMENT");
				String cfm_user_id = rs.getString("CFM_USER_ID");
				String cfm_date = rs.getString("CFM_DATE");
				String cfm_time = rs.getString("CFM_TIME");
				String rpt_date = rs.getString("RPT_DATE");
				String rpt_time = rs.getString("RPT_TIME");
				String rpt_source = rs.getString("RPT_SOURCE");
				String on_off_flg = rs.getString("ALERT_ON_OFF_FLG");

				local_eqpt_id = (local_eqpt_id != null) ? local_eqpt_id.trim() : "";
				alrt_code = (alrt_code != null) ? alrt_code.trim() : "";
				alrt_lvl = (alrt_lvl != null) ? alrt_lvl.trim() : "";
				alrt_comment = (alrt_comment != null) ? alrt_comment.trim() : "";
				cfm_user_id = (cfm_user_id != null) ? cfm_user_id.trim() : "";
				cfm_date = (cfm_date != null) ? cfm_date.trim() : "";
				cfm_time = (cfm_time != null) ? cfm_time.trim() : "";
				rpt_date = (rpt_date != null) ? rpt_date.trim() : "";
				rpt_time = (rpt_time != null) ? rpt_time.trim() : "";
				rpt_source = (rpt_source != null) ? rpt_source.trim() : "";
				on_off_flg = (on_off_flg != null) ? on_off_flg.trim() : "";

//				log.debug("EQPT_ID:" + eqpt_id + " ALRT_CODE:" + alrt_code + " ALRT_LVL:" + alrt_lvl + " ALRT_COMMENT:"
//						+ alrt_comment);
//				log.debug("CFM_USER_ID:" + cfm_user_id + " CFM_DATE:" + cfm_date + " CFM_TIME:" + cfm_time);
//				log.debug("RPT_DATE:" + rpt_date + " RPT_TIME:" + rpt_time + " RPT_SOURCE:" + rpt_source
//						+ " ALRT_ON_OFF_FLG:" + on_off_flg);

				rtnmap.put("eqpt_id", local_eqpt_id);
				rtnmap.put("alrt_code", alrt_code);
				rtnmap.put("alrt_lvl", alrt_lvl);
				rtnmap.put("alrt_comment", alrt_comment);
				rtnmap.put("cfm_user_id", cfm_user_id);
				rtnmap.put("cfm_date", cfm_date);
				rtnmap.put("cfm_time", cfm_time);
				rtnmap.put("rpt_date", rpt_date);
				rtnmap.put("rpt_time", rpt_time);
				rtnmap.put("rpt_source", rpt_source);
				rtnmap.put("alrt_on_off_flg", on_off_flg);

				rtnlist.add(rtnmap);

				count++;
			}

			if (count == 0) {
				rtnlist = null;
			}

		} catch (Exception ex) {
//			log.warn(ex.getMessage());
			rtnlist = null;
		}

//		log.debug("END");

		return rtnlist;
	}

//	public String getEqptDsc(String eqpt_id) throws Exception {
//
////		log.debug("START");
//
//		String eqpt_dsc = "";
//
//		try {
//			conn = DataSourcePool.getInstance().getConnection(dsHIS);// S002
//
//			StringBuffer sql = new StringBuffer();
//			sql.append("SELECT EQPT_ID, EQPT_DSC FROM " + config.getProperty("schema.his") + "."
//					+ "AEQPT WHERE EQPT_ID = ? "); // N0.03
//			sql.append("WITH UR ");
//
//			stmt = conn.prepareStatement(sql.toString());
//			stmt.setString(1, eqpt_id);
//			rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				eqpt_dsc = rs.getString("EQPT_DSC");
//				if (eqpt_dsc != null) {
//					eqpt_dsc = eqpt_dsc.trim();
//				} else {
//					eqpt_dsc = "";
//				}
////				log.debug("EQPT_DSC:" + eqpt_dsc);
//				break;
//			}
//
//		} catch (Exception ex) {
////			log.warn(ex.getMessage(), ex);
//			eqpt_dsc = null;
//		}
//
////		log.debug("END");
//
//		return eqpt_dsc;
//	}

//	public String getMtrlComment(String mtrl_product_id) throws Exception {
//
////		log.debug("START");
//
//		String comment = "";
//
//		try {
//			conn = DataSourcePool.getInstance().getConnection(dsHIS);
//
//			StringBuffer sql = new StringBuffer();
//			sql.append("SELECT MTRL_PRODUCT_ID, COMMENT FROM " + config.getProperty("schema.his") + "."
//					+ "AMTRLST WHERE EQPT_ID = ? ");
//			sql.append("WITH UR ");
//
//			stmt = conn.prepareStatement(sql.toString());
//			stmt.setString(1, mtrl_product_id);
//			rs = stmt.executeQuery();
//
//			while (rs.next()) {
//				comment = rs.getString("COMMENT");
//				if (comment != null) {
//					comment = comment.trim();
//				} else {
//					comment = "";
//				}
////				log.debug("COMMENT:" + comment);
//				break;
//			}
//
//		} catch (Exception ex) {
////			log.warn(ex.getMessage(), ex);
//			comment = null;
//		}
//
////		log.debug("END");
//
//		return comment;
//	}

}
