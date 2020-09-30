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
import com.ibm.lcdview.tx.APLSTBAYi;
import com.ibm.lcdview.tx.APLSTBAYo;
import com.ibm.lcdview.tx.APLWIPEQi;
import com.ibm.lcdview.tx.APLWIPEQo;

public class EqptListAction extends PPTAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		
		APLSTBAYi aplstbayi = new APLSTBAYi();
		aplstbayi.lst_bay_typ = "1";
		aplstbayi.trx_id      = "BPLSTBAY"; 	//C0.00 Add
		
//C0.00 Remove		APLSTBAYo aplstbayo = (APLSTBAYo) TrxXML.sendRecv("APLSTBAY", aplstbayi);
		APLSTBAYo aplstbayo = (APLSTBAYo) TrxXML.sendRecv("BPLSTBAY", aplstbayi);	//C0.00 Add
		if(aplstbayo == null)
			aplstbayo = new APLSTBAYo();
		
		request.setAttribute("xplstbay", aplstbayo);

		ArrayList baylist = TXUtilities.subAry2List(aplstbayo.oary);
		request.setAttribute("bayList", baylist);
		
		// Retrieve equipment list (if any).
		String bay_id = (String)((DynaActionForm)form).get("bay_id");
		if (bay_id != null && bay_id.length() > 0) {
			
			APLWIPEQi aplwipeqi = new APLWIPEQi();
			aplwipeqi.bay_id = bay_id;
			aplwipeqi.lst_eqpt_typ = "4";
			APLWIPEQo aplwipeqo = (APLWIPEQo) TrxXML.sendRecv("APLWIPEQ", aplwipeqi);
			if(aplwipeqo == null)
				aplwipeqo = new APLWIPEQo();
			
			ArrayList eqplist = TXUtilities.subAry2List(aplwipeqo.oary);
			request.setAttribute("eqptList", eqplist);
		}
		return mapping.findForward("success");
	}

}
