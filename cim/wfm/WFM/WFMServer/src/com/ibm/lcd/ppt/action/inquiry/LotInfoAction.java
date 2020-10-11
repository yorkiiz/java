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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.ibm.lcd.ppt.action.PPTAction;
import com.ibm.lcd.ppt.action.TrxXML;
import com.ibm.lcdview.tx.APINQLOTi;
import com.ibm.lcdview.tx.APINQLOTo;

public class LotInfoAction extends PPTAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String lot_id = (String)((DynaActionForm)form).get("lot_id");
		if (lot_id != null && lot_id.length() > 0) {
			
			APINQLOTi apinqloti = new APINQLOTi();
			apinqloti.lot_id 	= lot_id;
			apinqloti.trx_id    = "BPINQLOT";		//C0.00 Add
			
//C0.00 Remove			APINQLOTo apinqloto = (APINQLOTo) TrxXML.sendRecv("APINQLOT", apinqloti);
			APINQLOTo apinqloto = (APINQLOTo) TrxXML.sendRecv("BPINQLOT", apinqloti);		//C0.00 Add
			if(apinqloto == null)
				apinqloto = new APINQLOTo();
			
			request.setAttribute("result", apinqloto);
			request.setAttribute("lot_id", lot_id);
		}
		return mapping.findForward("success");
	}

}
