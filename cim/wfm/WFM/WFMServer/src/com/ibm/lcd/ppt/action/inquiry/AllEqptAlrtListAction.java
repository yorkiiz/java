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

import java.util.ArrayList;

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

public class AllEqptAlrtListAction extends PPTAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String eqpt_id = (String)((DynaActionForm)form).get("eqpt_id");
		
		APLEQALMi apleqalmi = new APLEQALMi();
		apleqalmi.eqpt_id 	= eqpt_id;//"01IED1";
		apleqalmi.trx_id    = "APLEQALM";	//C0.00 Add
		
//C0.00 Remove		APLEQALMo apleqalm = (APLEQALMo) TrxXML.sendRecv("APLEQALM", apleqalmi);
		APLEQALMo apleqalm = (APLEQALMo) TrxXML.sendRecv("BPLEQALM", apleqalmi);		//C0.00 Add 

		request.setAttribute("result", apleqalm);
		ArrayList list = TXUtilities.subAry2List(apleqalm.oary);
		request.setAttribute("subList", list);
		
		return mapping.findForward("success");
	}
}
