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
import com.ibm.lcdview.tx.APIWIPEQi;
import com.ibm.lcdview.tx.APIWIPEQo;

public class WipListAction extends PPTAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String eqpt_id = (String)((DynaActionForm)form).get("eqpt_id");
		if (eqpt_id != null && eqpt_id.length() > 0) 
		{
			APIWIPEQi apiwipeqi = new APIWIPEQi();
			apiwipeqi.eqpt_id 	= eqpt_id;
			apiwipeqi.trx_id	= "BPIWIPEQ";		//C0.00 Add
			
//C0.00 Remove			APIWIPEQo apiwipeqo = (APIWIPEQo) TrxXML.sendRecv("APIWIPEQ", apiwipeqi);
			APIWIPEQo apiwipeqo = (APIWIPEQo) TrxXML.sendRecv("BPIWIPEQ", apiwipeqi);		//C0.00 Add
			if(apiwipeqo == null)
				apiwipeqo = new APIWIPEQo();
			request.setAttribute("result", apiwipeqo);
			
			ArrayList list = TXUtilities.subAry2List(apiwipeqo.oary);
			System.out.println("WIP List Size: "+list.size());
			
			request.setAttribute("subList", list);
		}
		
		return mapping.findForward("success");
	}

}
