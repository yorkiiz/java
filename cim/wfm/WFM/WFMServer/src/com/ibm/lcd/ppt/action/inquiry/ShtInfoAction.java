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
import com.ibm.lcdview.tx.APINQSHTi;
import com.ibm.lcdview.tx.APINQSHTo;

public class ShtInfoAction extends PPTAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String sht_id = (String)((DynaActionForm)form).get("sht_id");
		if (sht_id != null && sht_id.length() > 0) {
			
			APINQSHTi apinqshti = new APINQSHTi();
			apinqshti.sht_id 	= sht_id;
			apinqshti.trx_id    = "BPINQSHT";		//C0.00 Add
			
//C0.00 Remove			APINQSHTo apinqshto = (APINQSHTo) TrxXML.sendRecv("APINQSHT", apinqshti);
			APINQSHTo apinqshto = (APINQSHTo) TrxXML.sendRecv("BPINQSHT", apinqshti);	//C0.00 Add	
			if(apinqshto == null)
				apinqshto = new APINQSHTo();
			
			request.setAttribute("result", apinqshto);
			request.setAttribute("sht_id", sht_id);
			
			ArrayList list1 = TXUtilities.subAry2List(apinqshto.oary1);
			request.setAttribute("subList1", list1);
			
			ArrayList list2 = TXUtilities.subAry2List(apinqshto.oary2);
			request.setAttribute("subList2", list2);
			
		}
		return mapping.findForward("success");
	}

}
