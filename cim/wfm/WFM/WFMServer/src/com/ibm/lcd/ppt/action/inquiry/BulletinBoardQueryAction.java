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

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ibm.lcd.ppt.action.PPTAction;
import com.ibm.lcd.ppt.action.TrxXML;
import com.ibm.lcdview.tx.APMBLBODi;
import com.ibm.lcdview.tx.APMBLBODo;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;

public class BulletinBoardQueryAction extends PPTAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws MQException {
			
			
		APMBLBODi apmblbodi = new APMBLBODi();
		apmblbodi.bilbd_access_mode = "R";
		apmblbodi.trx_id="BPMBLBOD";	//C0.00 Add
//C0.00 Remove		APMBLBODo apmblbodo = (APMBLBODo) TrxXML.sendRecv("APMBLBOD", apmblbodi);
		APMBLBODo apmblbodo = (APMBLBODo) TrxXML.sendRecv("BPMBLBOD", apmblbodi);	//C0.00 Remove
		int code = 0;
		StringBuffer strbfr = new StringBuffer();
			for( int i=0; i<apmblbodo.oary.length; i++){
				try{
					code = Integer.parseInt(apmblbodo.oary[i].code);
				}catch(NumberFormatException nfe){
					code = 0;
				}
				if(code != 5) continue;	//modified to be 5
				
				int msgCnt = 0;
				try{
					msgCnt = Integer.parseInt(apmblbodo.oary[i].bilbd_msg_cnt);
				}catch(NumberFormatException nfe){
					msgCnt = 0;
				}
				
				for(int j=0; j<msgCnt; j++){
					strbfr.append(apmblbodo.oary[i].oary[j].board_msg);
					strbfr.append(' ');
				}
				
			}
		

		
		try{
			respondWithText(response, strbfr.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;			
	}
	
	protected void respondWithText(HttpServletResponse response, byte[] data) throws IOException {
		respondWithData(response, data, "text/plain");
	}

	protected void respondWithData(HttpServletResponse response, byte[] data, String type) throws IOException {
		ServletOutputStream stream = null;
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(type);
			response.setContentLength(data.length);
			stream = response.getOutputStream();
			stream.write(data);
			stream.flush();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException ex) {}
			}
		}
	}
}
