package com.ibm.lcd.ppt.action.sql;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.naming.NamingException;

import com.ibm.cxl.db.DataSourcePool;
import com.ibm.lcd.ppt.action.PPTSQL;

import com.ibm.cxl.util.PropertiesWrapper;
import com.ibm.lcd.commons.LcdConfig;

public class EqptAlarmSQL extends PPTSQL {

	public final static int TYPE_MONITOR = 0;
	public final static int TYPE_HIS = 1;

	private static HashMap m_eqptgrHM; // EQPT to get EQPT Group ID
	protected static PropertiesWrapper config = LcdConfig.getConfig();
	
	/** Date format object. */
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public void setEqptGrMap() throws Exception {

		if(m_eqptgrHM == null)
			m_eqptgrHM = new HashMap();
		else
			m_eqptgrHM.clear();
		
		String key = config.getProperty("cfm.eqptGroupRelationship.ds");
		if (key != null)
			ds = config.getProperty(key);
		
		try {
			conn = DataSourcePool.getInstance().getConnection(ds);
			String sqlStr = config.getProperty("cfm.eqptGroupRelationship.query");
			stmt = conn.prepareStatement(sqlStr);
			rs = stmt.executeQuery();

			while (rs.next())
			{
				String groupid = rs.getString("EQPTG_ID");
				String eqptid = rs.getString("EQPT_ID");

				if(groupid == null || eqptid == null) continue;

				m_eqptgrHM.put(eqptid, groupid);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			close();
		}
	}

	public ArrayList getAllAlarmList(int displimit, int type){

		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date());
		cal.add(Calendar.HOUR_OF_DAY, -(displimit));
		Date sqldate = new Date(cal.getTime().getTime());
		Time sqltime = new Time(cal.getTime().getTime());

		ArrayList rtnlist = new ArrayList();

		String dataSourceKey = null;
		if (type == TYPE_MONITOR) 
		{
			dataSourceKey = config.getProperty("cfm.allEqptAlarm.ds");
		}
		else if (type == TYPE_HIS)
		{
			dataSourceKey = config.getProperty("cfm.allEqptHisAlarm.ds");
		}
		
		if (dataSourceKey != null && dataSourceKey.length() != 0){
			ds = config.getProperty(dataSourceKey);
		}else{
			return null;
		}
		
		try {
			conn = DataSourcePool.getInstance().getConnection(ds);
		} catch (SQLException e) {
			conn = null;
			e.printStackTrace();
		} catch (NamingException e) {
			conn = null;
			e.printStackTrace();
		}

		if(conn == null) return rtnlist;
		
		String sqlStr = null;
		if (type == TYPE_MONITOR) 
		{
			sqlStr = config.getProperty("cfm.allEqptAlarm.query");
		}
		else if (type == TYPE_HIS)
		{
			sqlStr = config.getProperty("cfm.allEqptHisAlarm.query");
		}

		try {
			stmt = conn.prepareStatement(sqlStr);
			stmt.setDate(1, sqldate);
			stmt.setDate(2, sqldate);
			stmt.setTime(3, sqltime);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
	
				HashMap rtnmap = new HashMap();
	
				String rpt_date 	= rs.getString("RPT_DATE");
				String rpt_time 	= rs.getString("RPT_TIME");
				String on_off_flg 	= rs.getString( (type == TYPE_MONITOR ) ? "ALERT_ON_OFF_FLG" : "ALRT_ON_OF_FLG");
				String alrt_code 	= rs.getString("ALRT_CODE");
				String alrt_id 		= rs.getString("ALRT_ID");
				String alrt_comment = rs.getString("ALRT_COMMENT");
				String eqpt_id 		= rs.getString("EQPT_ID");
				String eqptg_id;
				String port_id = null;
	
				rpt_date 	= (rpt_date != null) ? rpt_date.trim().replace('-', '/') : "";
				rpt_time 	= (rpt_time != null) ? rpt_time.trim().replace('.', ':') : "";
				on_off_flg 	= (on_off_flg != null) ? on_off_flg.trim() : "";
				alrt_code 	= (alrt_code != null) ? alrt_code.trim() : "";
				alrt_id 	= (alrt_id != null) ? alrt_id.trim() : "";
				alrt_comment = (alrt_comment != null) ? alrt_comment.trim() : "";
				eqpt_id 	= (eqpt_id != null) ? eqpt_id.trim() : "";
				eqptg_id 	= (String) m_eqptgrHM.get(eqpt_id);
				port_id 	= (port_id != null) ? port_id.trim() : "";
	
				rtnmap.put("rpt_date_time", rpt_date + " " + rpt_time);
				rtnmap.put("on_off_flg", on_off_flg);
				rtnmap.put("alrt_code", alrt_code);
				rtnmap.put("alrt_id", alrt_id);
				rtnmap.put("alrt_comment", alrt_comment);
				rtnmap.put("eqpt_id", eqpt_id);
				rtnmap.put("eqptg_id", eqptg_id);
				rtnmap.put("port_id", port_id);
	
				rtnlist.add(rtnmap);
	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rtnlist;
	}

	public ArrayList getAllAlarmList(String frDateStr, String toDateStr, int type){

		ArrayList rtnlist = new ArrayList();

		java.util.Date frD = null;
		try {
			frD = dateFormat.parse(frDateStr);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		java.util.Date toD = null;
		try {
			toD = dateFormat.parse(toDateStr);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Date frDate = new Date(frD.getTime());
		Date toDate = new Date(toD.getTime());
		
		String dataSourceKey = null;
		if (type == TYPE_MONITOR) 
		{
			dataSourceKey = config.getProperty("cfm.allEqptAlarmByDate.ds");
		}
		else if (type == TYPE_HIS)
		{
			dataSourceKey = config.getProperty("cfm.allEqptHisAlarmByDate.ds");
		}
		
		if (dataSourceKey != null && dataSourceKey.length() != 0){
			ds = config.getProperty(dataSourceKey);
		}else{
			return null;
		}
		
		try {
			conn = DataSourcePool.getInstance().getConnection(ds);
		} catch (SQLException e) {
			conn = null;
			e.printStackTrace();
		} catch (NamingException e) {
			conn = null;
			e.printStackTrace();
		}

		if(conn == null) return rtnlist;
		
		String sqlStr = null;
		if (type == TYPE_MONITOR) 
		{
			sqlStr = config.getProperty("cfm.allEqptAlarmByDate.query");
		}
		else if (type == TYPE_HIS)
		{
			sqlStr = config.getProperty("cfm.allEqptHisAlarmByDate.query");
		}

		try {
			
			stmt = conn.prepareStatement(sqlStr);
			stmt.setDate(1, frDate);
			System.out.println("Fr: "+frDate);
			stmt.setDate(2, frDate);
			stmt.setDate(3, toDate);
			System.out.println("To: "+toDate);
			stmt.setDate(4, toDate);
			
			System.out.println(stmt);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
	
				HashMap rtnmap = new HashMap();
	
				String rpt_date 	= rs.getString("RPT_DATE");
				String rpt_time 	= rs.getString("RPT_TIME");
				String on_off_flg 	= rs.getString( (type == TYPE_MONITOR ) ? "ALERT_ON_OFF_FLG" : "ALRT_ON_OF_FLG");
				String alrt_code 	= rs.getString("ALRT_CODE");
				String alrt_id 		= rs.getString("ALRT_ID");
				String alrt_comment = rs.getString("ALRT_COMMENT");
				String eqpt_id 		= rs.getString("EQPT_ID");
				String eqptg_id;
				String port_id = null;
	
				rpt_date 	= (rpt_date != null) ? rpt_date.trim().replace('-', '/') : "";
				rpt_time 	= (rpt_time != null) ? rpt_time.trim().replace('.', ':') : "";
				on_off_flg 	= (on_off_flg != null) ? on_off_flg.trim() : "";
				alrt_code 	= (alrt_code != null) ? alrt_code.trim() : "";
				alrt_id 	= (alrt_id != null) ? alrt_id.trim() : "";
				alrt_comment = (alrt_comment != null) ? alrt_comment.trim() : "";
				eqpt_id 	= (eqpt_id != null) ? eqpt_id.trim() : "";
				eqptg_id 	= (String) m_eqptgrHM.get(eqpt_id);
				port_id 	= (port_id != null) ? port_id.trim() : "";
	
				rtnmap.put("rpt_date_time", rpt_date + " " + rpt_time);
				rtnmap.put("on_off_flg", on_off_flg);
				rtnmap.put("alrt_code", alrt_code);
				rtnmap.put("alrt_id", alrt_id);
				rtnmap.put("alrt_comment", alrt_comment);
				rtnmap.put("eqpt_id", eqpt_id);
				rtnmap.put("eqptg_id", eqptg_id);
				rtnmap.put("port_id", port_id);
	
				rtnlist.add(rtnmap);
	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rtnlist;
	}
	
	public ArrayList getAllLotAlarm(int displimit) throws Exception {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date());
		cal.add(Calendar.HOUR_OF_DAY, -(displimit));
		Date sqldate = new Date(cal.getTime().getTime());
		Time sqltime = new Time(cal.getTime().getTime());

		ArrayList rtnlist = new ArrayList();

		conn = DataSourcePool.getInstance().getConnection(dsHIS);

		StringBuffer sql = new StringBuffer();
		sql
				.append("SELECT DISTINCT DATE(EVT_TIMESTAMP) AS CLM_DATE, TIME(EVT_TIMESTAMP) AS CLM_TIME,EVT_CATE AS CLM_CATE, LOT_ID "
						+ // N0.05
						"FROM "
						+ config.getProperty("schema.his")
						+ "."
						+ "HBSHEET "
						+ "WHERE (DATE(EVT_TIMESTAMP) > ? OR (DATE(EVT_TIMESTAMP) = ? AND TIME(EVT_TIMESTAMP) > ?)) "
						+ // N0.01
						"AND   EVT_CATE IN ('HLDC','HLDR','FTHD','FTHR') "
						+ "ORDER BY CLM_DATE DESC, CLM_TIME DESC");

		stmt = conn.prepareStatement(sql.toString());
		stmt.setDate(1, sqldate);
		stmt.setDate(2, sqldate);
		stmt.setTime(3, sqltime);
		rs = stmt.executeQuery();

		while (rs.next()) {

			HashMap rtnmap = new HashMap();

			String clm_date = rs.getString("CLM_DATE");
			String clm_time = rs.getString("CLM_TIME");
			String clm_cate = rs.getString("CLM_CATE");
			String lot_id = rs.getString("LOT_ID");

			clm_date = (clm_date != null) ? clm_date.trim().replace('-', '/') : "";
			clm_time = (clm_time != null) ? clm_time.trim().replace('.', ':') : "";
			clm_cate = (clm_cate != null) ? clm_cate.trim() : "";
			lot_id = (lot_id != null) ? lot_id.trim() : "";

			int i = 0;
			for (i = 0; i < rtnlist.size(); i++) {
			}
			rtnmap.put("clm_date_time", clm_date + " " + clm_time);
			rtnmap.put("clm_cate", clm_cate);
			rtnmap.put("lot_id", lot_id);
			rtnmap.put("KEY", clm_date + clm_time + clm_cate + lot_id);

			rtnlist.add(rtnmap);
		}

		if (rtnlist.size() <= 0) {
			return null;
		}

		return rtnlist;
	}

}
