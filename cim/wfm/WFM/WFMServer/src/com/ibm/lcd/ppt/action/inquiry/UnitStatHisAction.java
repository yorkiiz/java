//*********************************************************************************
//
// SYSTEM        : IBM MES system
//
// PROGRAM NAME  : OutputTXHandler.java
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
// 2013/11/25  C0.00  Nico Cai         Chg for Module
//*********************************************************************************
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
import com.ibm.lcd.ppt.action.TrxXML;
import com.ibm.lcdview.tx.APIEQSHSi;
import com.ibm.lcdview.tx.APIEQSHSo;
import com.ibm.lcdview.tx.APINQEQPi;
import com.ibm.lcdview.tx.APINQEQPo;

public class UnitStatHisAction extends PPTAction {

	/** Date format object. */
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		//StructElement struct = structFactory.getStruct(config.getProperty("xpieqshs.struct.in"));

		// Retrieve input from the form.
		DynaActionForm dynaForm = (DynaActionForm)form;
		String eqpt_id = (String)dynaForm.get("eqpt_id");

		if (eqpt_id != null && eqpt_id.length() > 0) {
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

			//--
			APIEQSHSi apieqshsi = new APIEQSHSi();
			apieqshsi.eqpt_id 	= eqpt_id;
			apieqshsi.fr_date = fr_date;
			apieqshsi.to_date = to_date;
			apieqshsi.trx_id  = "BPIEQSHS";		//C0.00 Add
			
//C0.00 Remove			APIEQSHSo apieqshso = (APIEQSHSo)TrxXML.sendRecv("APIEQSHS", apieqshsi);
			APIEQSHSo apieqshso = (APIEQSHSo)TrxXML.sendRecv("BPIEQSHS", apieqshsi);	//C0.00 Add
			if(apieqshso == null)
				apieqshso = new APIEQSHSo();
			
			ArrayList list = TXUtilities.subAry2List(apieqshso.oary);
			System.out.println("HIS List Size: "+list.size());
			
			request.setAttribute("subList", list);

			request.setAttribute("xpieqshs", apieqshso);
			
			APINQEQPi apinqeqpi = new APINQEQPi();
			apinqeqpi.eqpt_id 	= eqpt_id;
			
			APINQEQPo apinqeqpo = (APINQEQPo)TrxXML.sendRecv("APINQEQP", apinqeqpi);
			if(apinqeqpo == null)
				apinqeqpo = new APINQEQPo();
			
			request.setAttribute("xpinqeqp", apinqeqpo);
		}
		
		return mapping.findForward("success");
	}

}
