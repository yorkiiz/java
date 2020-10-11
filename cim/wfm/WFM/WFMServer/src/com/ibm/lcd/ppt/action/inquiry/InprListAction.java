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
import com.ibm.lcdview.tx.APLICRSHi;
import com.ibm.lcdview.tx.APLICRSHo;
import com.ibm.lcdview.tx.APLIEQSHi;
import com.ibm.lcdview.tx.APLIEQSHo;


public class InprListAction extends PPTAction {

	// --------------------------------------------------------- Public Methods
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

//		//StructElement struct = structFactory.getStruct(config.getProperty("xplieqsh.struct.in"));
//		StructElement struct = structFactory.getStruct(config.getProperty("xplicrsh.struct.in"));
//		
//		if (eqpt_id != null && eqpt_id.length() > 0) {
//			struct.setString("eqpt_id", eqpt_id.trim());
//			struct.setString("unit", "LOT");
//			//struct = exchange(config.getProperty("xplieqsh.q.in"), struct, config.getProperty("xplieqsh.q.out"), config.getProperty("xplieqsh.struct.out"));
//			struct = exchange(config.getProperty("xplicrsh.q.in"), struct, config.getProperty("xplicrsh.q.out"), config.getProperty("xplicrsh.struct.out"));
//			ActionForward forward = checkResult(mapping, request, struct);
//			if (forward != null)
//				return forward;
//
//			//request.setAttribute("xplieqsh", struct);
//			request.setAttribute("xplicrsh", struct);
//		}
		String eqpt_id = (String)((DynaActionForm)form).get("eqpt_id");
		
		if(eqpt_id == null || eqpt_id.trim().length() == 0){
			return mapping.findForward("success");
		}
		
		APINQEQPi apinqeqpi = new APINQEQPi();
		apinqeqpi.eqpt_id 	= eqpt_id;
		apinqeqpi.trx_id    = "BPINQEQP";
		
//C0.00 Remove		APINQEQPo apinqeqpo = (APINQEQPo) TrxXML.sendRecv("APINQEQP", apinqeqpi);
		APINQEQPo apinqeqpo = (APINQEQPo) TrxXML.sendRecv("BPINQEQP", apinqeqpi);	//C0.00 Add
		if(apinqeqpo == null)
			apinqeqpo = new APINQEQPo();
		
		if( apinqeqpo.eqpt_cate.compareTo("C") == 0) //Common Category
		{
			System.out.println("Comm Type");
			APLICRSHi aplicrshi = new APLICRSHi();
			aplicrshi.eqpt_id 	= eqpt_id;
//C0.00 Remove			aplicrshi.unit 		= "WAFE";
			aplicrshi.unit 		= "PCS";		//C0.00 Add
			aplicrshi.trx_id    = "BPLICRSH";	//C0.00 Add
			
//C0.00 Remove			APLICRSHo aplicrsho = (APLICRSHo) TrxXML.sendRecv("APLICRSH", aplicrshi);
			APLICRSHo aplicrsho = (APLICRSHo) TrxXML.sendRecv("BPLICRSH", aplicrshi);	//C0.00 Add
			request.setAttribute("result", aplicrsho);
			ArrayList list = TXUtilities.subAry2List(aplicrsho.oary1);
			request.setAttribute("subList", list);
			
		}
		else
		{
			System.out.println("Non Comm Type");
			APLIEQSHi aplieqshi = new APLIEQSHi();
			aplieqshi.eqpt_id 	= eqpt_id;
//C0.00 Remove			aplieqshi.unit 		= "WAFE";
			aplieqshi.unit 		= "PCS";		//C0.00 Add
			aplieqshi.trx_id    = "BPLIEQSH";	//C0.00 Add
			
//C0.00 Remove			APLIEQSHo aplieqsho = (APLIEQSHo) TrxXML.sendRecv("APLIEQSH", aplieqshi);
			APLIEQSHo aplieqsho = (APLIEQSHo) TrxXML.sendRecv("BPLIEQSH", aplieqshi);	//C0.00 Add

			request.setAttribute("result", aplieqsho);
			ArrayList list = TXUtilities.subAry2List(aplieqsho.oary);
			request.setAttribute("subList", list);
		}


		return mapping.findForward("success");
	}

}
