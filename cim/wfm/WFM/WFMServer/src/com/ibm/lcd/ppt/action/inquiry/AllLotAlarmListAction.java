
package com.ibm.lcd.ppt.action.inquiry;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ibm.lcd.ppt.action.PPTAction;
import com.ibm.lcd.ppt.action.sql.EqptAlarmSQL;

public class AllLotAlarmListAction extends PPTAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String displimit = config.getProperty("ppt.alllotalrt.disphour");
		
		EqptAlarmSQL alarmsql = new EqptAlarmSQL();
		ArrayList rtnlist = alarmsql.getAllLotAlarm(Integer.parseInt(displimit));
		request.setAttribute("subList", rtnlist);

		return mapping.findForward("success");
	}

}
