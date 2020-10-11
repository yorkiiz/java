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
import com.ibm.lcdview.tx.APLEQALMi;
import com.ibm.lcdview.tx.APLEQALMo;

public class EqptAlrtListAction extends PPTAction {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		DynaActionForm dynaForm = (DynaActionForm)form;
		
		String eqpt_id = (String)dynaForm.get("eqpt_id");
		
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
		
		APLEQALMi apleqalmi = new APLEQALMi();
		apleqalmi.eqpt_id 	= eqpt_id;
		apleqalmi.fr_date 	= fr_date;
		apleqalmi.to_date 	= to_date;
		apleqalmi.trx_id    = "BPLEQALM";		//C0.00 Add
		
//C0.00 Remove		APLEQALMo apleqalm = (APLEQALMo) TrxXML.sendRecv("APLEQALM", apleqalmi);
		APLEQALMo apleqalm = (APLEQALMo) TrxXML.sendRecv("BPLEQALM", apleqalmi);	//C0.00 Add
		if(apleqalm == null)
			apleqalm = new APLEQALMo();
		request.setAttribute("result", apleqalm);
		
		ArrayList list = TXUtilities.subAry2List(apleqalm.oary);
		System.out.println("List Size: "+list.size());
		
		request.setAttribute("subList", list);
		
		return mapping.findForward("success");
	}

}
