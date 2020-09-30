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
import com.ibm.lcdview.tx.APIQCRRDi;
import com.ibm.lcdview.tx.APIQCRRDo;

public class CrrInfoAction extends PPTAction {


	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String crr_id = (String)((DynaActionForm)form).get("crr_id");
		
		APIQCRRDi apiqcrrdi = new APIQCRRDi();
		apiqcrrdi.crr_id 	= crr_id;
		apiqcrrdi.trx_id    = "BPIQCRRD";		//C0.00 Add
		
//C0.00 Remove		APIQCRRDo apiqcrrdo = (APIQCRRDo) TrxXML.sendRecv("APIQCRRD", apiqcrrdi);
		APIQCRRDo apiqcrrdo = (APIQCRRDo) TrxXML.sendRecv("BPIQCRRD", apiqcrrdi); 	//C0.00 Add

		request.setAttribute("result", apiqcrrdo);
		ArrayList list1 = TXUtilities.subAry2List(apiqcrrdo.oary1);
		request.setAttribute("subList1", list1);
		
		ArrayList list2 = TXUtilities.subAry2List(apiqcrrdo.oary2);
		request.setAttribute("subList2", list2);
		
		return mapping.findForward("success");
	}

}
