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
import com.ibm.lcdview.tx.APINQEQPi;
import com.ibm.lcdview.tx.APINQEQPo;


public class EqptInfoAction extends PPTAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		
		try{
			String eqpt_id = (String)((DynaActionForm)form).get("eqpt_id");
			if (eqpt_id == null || eqpt_id.length() <= 0) {
				request.setAttribute("err_msg", "ID");
				return mapping.findForward("ppt_Error");
			}
			request.setAttribute("eqpt_id", eqpt_id);
			

			APINQEQPi apinqeqpi = new APINQEQPi();
			apinqeqpi.eqpt_id 	= eqpt_id;
			apinqeqpi.trx_id = "BPINQEQP";		//C0.00 Add
			
//C0.00 Remove			APINQEQPo apinqeqpo = (APINQEQPo) TrxXML.sendRecv("APINQEQP", apinqeqpi);
			APINQEQPo apinqeqpo = (APINQEQPo) TrxXML.sendRecv("BPINQEQP", apinqeqpi);	//C0.00 Add
			if(apinqeqpo == null)
				apinqeqpo = new APINQEQPo();
			
			ArrayList portinfoList = TXUtilities.subAry2List(apinqeqpo.oary);
			
			request.setAttribute("eqptstatinfo", apinqeqpo);
			request.setAttribute("portinfo", portinfoList);
			
		}catch(Exception e){
			request.setAttribute("err_msg", "");
			return mapping.findForward("ppt_Error");
		}
		return mapping.findForward("success");
	}
}
