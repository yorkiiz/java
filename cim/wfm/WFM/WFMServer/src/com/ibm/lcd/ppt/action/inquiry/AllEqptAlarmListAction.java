
package com.ibm.lcd.ppt.action.inquiry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.ibm.lcd.ppt.action.PPTAction;
import com.ibm.lcd.ppt.action.sql.EqptAlarmSQL;

public class AllEqptAlarmListAction extends PPTAction {
	/** Date format object. */
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		// Retrieve input from the form.
		DynaActionForm dynaForm = (DynaActionForm)form;
		
		// Adjust and aply dates.
		String fr_date = (String)dynaForm.get("fr_date");
		String to_date = (String)dynaForm.get("to_date");
		Date d, today = new Date();
		try {
			d = dateFormat.parse(fr_date);
		} catch (Exception ex) {
			d = new Date(today.getTime() - 604800000); // = 1000ms * 60s * 60m * 24h * 7d
		}
		fr_date = dateFormat.format(d);
		try {
			d = dateFormat.parse(to_date);
		} catch (Exception ex) {
			d = today;
		}
		to_date = dateFormat.format(d);
		
		dynaForm.set("fr_date", fr_date);
		dynaForm.set("to_date", to_date);
		
		//String displimit = config.getProperty("ppt.alrtmonitor.disphour");
		
		EqptAlarmSQL alarmsql = new EqptAlarmSQL();
		alarmsql.setEqptGrMap();
		
		//ArrayList rtnlist = alarmsql.getAllAlarmList(Integer.parseInt(displimit), EqptAlarmSQL.TYPE_MONITOR);
		
		ArrayList rtnlist = alarmsql.getAllAlarmList(fr_date, to_date, EqptAlarmSQL.TYPE_MONITOR);
		
		request.setAttribute("subList", rtnlist);
		request.setAttribute("mon_type", String.valueOf(EqptAlarmSQL.TYPE_MONITOR));
		
		return mapping.findForward("success");
	}

}
