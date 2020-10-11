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
import com.ibm.lcdview.tx.APIOPSHTi;
import com.ibm.lcdview.tx.APIOPSHTo;

public class ShtHisAction extends PPTAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String sht_id = (String)((DynaActionForm)form).get("sht_id");
		
		if (sht_id != null && sht_id.length() > 0) {
			
			APIOPSHTi apiopshti = new APIOPSHTi();
			apiopshti.sht_id 	= sht_id;
			apiopshti.trx_id    = "BPIOPSHT";	//C0.00 Add
			
//C0.00 Remove			APIOPSHTo apiopshto = (APIOPSHTo) TrxXML.sendRecv("APIOPSHT", apiopshti);
			APIOPSHTo apiopshto = (APIOPSHTo) TrxXML.sendRecv("BPIOPSHT", apiopshti); //C0.00 Add
			if(apiopshto == null)
				apiopshto = new APIOPSHTo();
			
			request.setAttribute("result", apiopshto);
			request.setAttribute("sht_id", sht_id);
			
			ArrayList list = TXUtilities.subAry2List(apiopshto.oary);
			request.setAttribute("subList", list);
		}
		return mapping.findForward("success");
	}

}
