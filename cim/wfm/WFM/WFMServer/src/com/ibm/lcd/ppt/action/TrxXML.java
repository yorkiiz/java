//*********************************************************************************
//
// SYSTEM        : LCD View system
//
// PROGRAM NAME  : TrxXML.java
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
// 2013/12/03  C0.00  Nico Cai         Chg for Encoding
//*********************************************************************************

package com.ibm.lcd.ppt.action;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.ibm.lcdview.LCDViewTX;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMD;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

/**
 * This class is used in OPI to perform MQ PUT and GET communication with PPT Server.
 * The transmission format is XML.
 */
public final class TrxXML {

    private static final String UNKNOWN_HOST_NAME = "UNKNOWN";

    private static String inputQNamePostFix = "I";
    
    private final static int PUT_OPEN_OPTIONS = MQC.MQOO_OUTPUT | MQC.MQOO_SET_IDENTITY_CONTEXT | MQC.MQOO_FAIL_IF_QUIESCING;

    private final static int GET_OPEN_OPTIONS = MQC.MQOO_INPUT_SHARED // open queue for input
                                                | MQC.MQOO_FAIL_IF_QUIESCING; // but not if MQM stopping

    private static String localHostName = UNKNOWN_HOST_NAME;

    private static MQPutMessageOptions pmo;
    private static MQGetMessageOptions gmo;

    private static MQMessage retrievedMessage;
    private static MQMessage msgInput;
    
	//Parsing usage
	private static SAXParser m_parser = null;
	private static org.xml.sax.InputSource m_is = new org.xml.sax.InputSource();	
	
    //private final static char DCS_TX_INITIAL = 'D';
    //private final static String SHOP_TX_INITIAL = "A";
    
    //private static int m_getMsgMQRtn = 0;
    
    private final static int[] MQ_GATEWAY_SWITCH_ERR_CODE = 
    {
        MQException.MQRC_HCONN_ERROR, 
        MQException.MQRC_CONNECTION_BROKEN, 
        MQException.MQRC_Q_MGR_NOT_AVAILABLE
    };
    
    private static boolean LOCAL_SIMULATION = false;
    static {
//C0.00 Remove    	MQEnvironment.CCSID = 950;	//For windows server
    	MQEnvironment.CCSID = 1208;	//C0.00 Add
//    	MQEnvironment.CCSID = 1381;
		if (!LOCAL_SIMULATION) {

            try {
                localHostName = java.net.InetAddress.getLocalHost().getHostName();
            } catch (java.net.UnknownHostException ex) {
                localHostName = UNKNOWN_HOST_NAME;
            }

            msgInput = new MQMessage();
            msgInput.messageType = MQC.MQMT_REQUEST;
            msgInput.report = MQC.MQRO_EXCEPTION // ask for exceptions to be reported with original text
            // | MQRO_EXPIRATION // if we have this flag some data will be remain at local queue
            | MQC.MQRO_PASS_MSG_ID | MQC.MQRO_PASS_CORREL_ID | MQC.MQRO_DISCARD_MSG;
            msgInput.format = MQC.MQFMT_STRING;

            msgInput.applicationIdData = localHostName;
            msgInput.expiry = 300000;

            pmo = new MQPutMessageOptions();
            pmo.options = MQC.MQPMO_SYNCPOINT | MQC.MQPMO_SET_IDENTITY_CONTEXT | MQC.MQPMO_NEW_MSG_ID
            // | MQPMO_NEW_CORREL_ID
            | MQC.MQPMO_FAIL_IF_QUIESCING;

            // Set the get message options...
            gmo = new MQGetMessageOptions();
            // accept the defaults same as MQGMO_DEFAULT
            // get the message off the queue...

            gmo.options = MQC.MQGMO_WAIT | MQC.MQGMO_SYNCPOINT | MQC.MQGMO_FAIL_IF_QUIESCING;
            gmo.matchOptions = MQC.MQMO_MATCH_MSG_ID    // ID and Correlation ID after
                    | MQC.MQMO_MATCH_CORREL_ID;         // every MQGET
            gmo.waitInterval = 180000;

            retrievedMessage = new MQMessage();
            // retrievedMessage.characterSet = 950;
            try {
                retrievedMessage.resizeBuffer(4096);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public static synchronized LCDViewTX sendRecv(LCDViewTX inTrx) {
//        if (inTrx == null) {
//            return null;
//        }
//
//        String txQueueName = inTrx.getTrxID();
//
//        // This is used for simulation
//        if (LOCAL_SIMULATION) {
//            return null;//getLocalResult(txQueueName);
//        } else {
//            return getRemoteResult(txQueueName, inTrx);
//        }
//    }

    public static synchronized byte[] sendRecv(String qName, byte[] inTrx) {
        if (inTrx == null || inTrx.length == 0) {
            return null;
        }

        String txQueueName = qName;

        // This is used for simulation
        if (LOCAL_SIMULATION) {
            return null;//getLocalResult(txQueueName);
        } else {
            return getRemoteResult(txQueueName, inTrx);
        }
    }
    
    public static synchronized LCDViewTX sendRecv(String qName, LCDViewTX inTrx) {
        if (inTrx == null) {
            return null;
        }

        String txQueueName = qName;

        // This is used for simulation
        if (LOCAL_SIMULATION) {
            return null;//getLocalResult(txQueueName);
        } else {
        	 //System.out.println(inTrx.toXMLString());
    		writeTXLog(inTrx, true);
        	
        	byte[] result = getRemoteResult(txQueueName, inTrx.toXMLString().getBytes());
        	LCDViewTX resultTx = null;
			try {
//C0.00 Remove				resultTx = parseOutput(new String(result, "Big5"));
				resultTx = parseOutput(new String(result, "UTF-8"));	//C0.00 Add
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			writeTXLog(resultTx, false);
            return resultTx;
        }
    }
    
    private static boolean isMQSwitchErrCode(int code){
        boolean rtn = false;
        for( int i=0; i<MQ_GATEWAY_SWITCH_ERR_CODE.length; i++){
            if(code == MQ_GATEWAY_SWITCH_ERR_CODE[i]){
                rtn = false;
                break;
            }
        }
        
        return rtn;
    } 
    
    private static byte[] getRemoteResult(String txQueueName, byte[] inTrx) {
        
        int retryCnt = 4;
        //LCDViewTX outTrx = null;
        String msgText = null;
        
        //System.out.println("Queue Name: "+txQueueName);
        //System.out.println(new String(inTrx));
        //long txStart = System.currentTimeMillis();
        
        while(retryCnt > 0){
            retryCnt --;
            int putMsgRtn = putMessage2Queue(!MQConstant.isSpareServerUsed, txQueueName, inTrx);
            //m_getMsgMQRtn = putMsgRtn;
            if(putMsgRtn != 0){
               
                if(isMQSwitchErrCode(putMsgRtn))
                {
                    MQConstant.isSpareServerUsed = !MQConstant.isSpareServerUsed;
                    //System.out.println("Switch MQ Gateway to "+ (MQConstant.isSpareServerUsed?"Spare Server":"Primary Server") );
                    continue;
                }
                else{
                    break;
                }
            }
            
            msgText = getMessageFromQueue(!MQConstant.isSpareServerUsed, txQueueName);
            
            if(msgText != null){
                break;
            }
        }

//        // Check MQ Return Code
//        if (m_getMsgMQRtn != 0) {
//            
//            StringBuffer sb = new StringBuffer();
//            sb.append("<html><font color=\"#FF0000\" size=\"+2\"><B>MQ Communication Error.</B></font><BR><BR>");
//            sb.append("Return Code:&nbsp;&nbsp;");
//            sb.append("<font color=\"#0000FF\" size=\"+1\"><B>");
//            sb.append(m_getMsgMQRtn);
//            sb.append("</B></font><BR><BR>");
//            sb.append("Transaction Name:&nbsp;&nbsp;");
//            sb.append("<font color=\"#0000FF\" size=\"+1\"><B>");
//            sb.append(txQueueName);
//            sb.append("</B></font><BR><BR>");
//            sb.append("</html>");
//            sb = null;
//
//            return outTrx;
//        }
        if (msgText != null) {
        	//System.out.println(msgText);
            //outTrx = parseOutput(msgText);
        }else{
        	msgText = "";
        }
        
        try {
			return msgText.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    private static int putMessage2Queue(boolean primary, String txQueueName, byte[] inTrx)
    {
        int rtnVal = 0;
        MQQueue inputQueue = null;
        //System.out.println("putMessage2Queue "+primary);
        MQQueueManager qMgr = null;
        try {

            if(primary){
                MQEnvironment.hostname  = MQConstant.MQ_GATEWAY_SERVER_DEV;
                MQEnvironment.port      = MQConstant.MQ_PORT_TRANSMISSION;
                MQEnvironment.channel   = MQConstant.MQ_CHANNELNAME; 
                
                qMgr = new MQQueueManager(MQConstant.MQ_GATEWAY_TRANSMISSION); //create Queue Manager
            }
            else{
                MQEnvironment.hostname  = MQConstant.SPARE_MQ_GATEWAY_SERVER;
                MQEnvironment.port      = MQConstant.SPARE_MQ_PORT_TRANSMISSION; 
                MQEnvironment.channel   = MQConstant.MQ_CHANNELNAME; 
                
                qMgr = new MQQueueManager(MQConstant.SPARE_MQ_GATEWAY_TRANSMISSION);  //create Spare Queue Manager
            }
            
            //System.out.println("Access Queue");
            inputQueue = qMgr.accessQueue(txQueueName.concat(inputQNamePostFix), PUT_OPEN_OPTIONS);

            msgInput.clearMessage();
            //Set parameters of Input Message
            if(primary){
                msgInput.replyToQueueManagerName    = MQConstant.MQ_GATEWAY_TRANSMISSION;
                msgInput.replyToQueueName           = MQConstant.MQ_OPI_REPLY_QUEUE;
            }else{
                msgInput.replyToQueueManagerName    = MQConstant.SPARE_MQ_GATEWAY_TRANSMISSION;
                msgInput.replyToQueueName           = MQConstant.SPARE_MQ_OPI_REPLY_QUEUE;          
            }
            
            msgInput.characterSet=1208; 	//C0.00 Add

            // Fill in TX input data
//C0.00 Remove            msgInput.writeString(new String(inTrx, "ISO-8859-1"));
            msgInput.writeString(new String(inTrx, "UTF-8"));	//C0.00 Add
//            msgInput.writeString(new String(inTrx, "GB2312"));
            
            //System.out.println("put");
            inputQueue.put(msgInput, pmo);
            qMgr.commit();
            //System.out.println("commit");
            
        } catch (MQException e) {
            System.out.println("A WebSphere MQ error occurred in PUT Message: Completion code " + e.completionCode + " Reason code " + e.reasonCode);
            //System.out.println(e.getMessage());
            
            System.out.println("GW Host Name: " + MQEnvironment.hostname);
            System.out.println("GW Server: " + MQEnvironment.channel);
            System.out.println("Port: " + MQEnvironment.port);
            
            rtnVal = e.reasonCode;
        } catch (IOException e) {
            e.printStackTrace();
            
        }finally{
            try{
                // Disconnect from the queue manager
                if(inputQueue != null && inputQueue.isOpen())
                {
                    //System.out.println("Close Queue: "+inputQueue.name);
                    inputQueue.close();
                }
                if( qMgr != null && qMgr.isConnected())
                {
                    //System.out.println("Disconnect Q Manager: "+qMgr.name);
                    qMgr.disconnect();
                }
            }
            catch (MQException ex)
            {
                System.out.println("A WebSphere MQ error occurred while try to disconnect: Completion code " + ex.completionCode + " Reason code " + ex.reasonCode);
                System.out.println(ex.getMessage()); 
            }
        }
        return rtnVal;
    }
    
    private static String getMessageFromQueue(boolean primary, String txQueueName)
    {
        //m_getMsgMQRtn = 0;
        
        String msgText = null;
        MQQueueManager qMgr = null;
        
        MQQueue inputQueue = null;
        try {
            if(primary){
                MQEnvironment.hostname  = MQConstant.MQ_GATEWAY_SERVER_DEV;
                MQEnvironment.port      = MQConstant.MQ_PORT_TRANSMISSION;
                MQEnvironment.channel   = MQConstant.MQ_CHANNELNAME; 
                
                qMgr = new MQQueueManager(MQConstant.MQ_GATEWAY_TRANSMISSION); //create Queue Manager
            }
            else{
                MQEnvironment.hostname  = MQConstant.SPARE_MQ_GATEWAY_SERVER;
                MQEnvironment.port      = MQConstant.SPARE_MQ_PORT_TRANSMISSION; 
                MQEnvironment.channel   = MQConstant.MQ_CHANNELNAME; 
                
                qMgr = new MQQueueManager(MQConstant.SPARE_MQ_GATEWAY_TRANSMISSION);  //create Spare Queue Manager
            }
            
            inputQueue = qMgr.accessQueue(MQConstant.MQ_OPI_REPLY_QUEUE, GET_OPEN_OPTIONS);

            retrievedMessage.messageId = ((MQMD) (msgInput)).messageId;

            inputQueue.get(retrievedMessage, gmo);
            
            byte[] rawData = new byte[retrievedMessage.getMessageLength()];
            // And prove we have the message by displaying the UTF message text
            retrievedMessage.readFully(rawData);
            retrievedMessage.clearMessage();
            // retrievedMessage.readString(retrievedMessage.getMessageLength());
//C0.00 Remove            msgText = new String(rawData, "Big5");
            msgText = new String(rawData, "UTF-8");		//C0.00 Add
            rawData = null;
            
            msgText = msgText.replace(']', ')');
            msgText = msgText.replace('[', '(');
            //msgText = msgText.replaceAll("&", "&amp;");
            
        } catch (MQException e) {
            //m_getMsgMQRtn = e.reasonCode;
            System.out.println("A WebSphere MQ error occurred in GET Message: Completion code " + e.completionCode + " Reason code " + e.reasonCode);
            System.out.println(e.getMessage());
            
            System.out.println("GW Host Name: "+MQEnvironment.hostname);
            System.out.println("GW Server: "+MQEnvironment.channel);
            System.out.println("Port: "+MQEnvironment.port);
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
        }
        finally{
            try{
                // Disconnect from the queue manager
                if(inputQueue != null && inputQueue.isOpen())
                {
                    inputQueue.close();
                }
                if( qMgr != null && qMgr.isConnected())
                {
                    qMgr.disconnect();
                }
            }
            catch (MQException ex)
            { 
                System.out.println("A WebSphere MQ error occurred while try to disconnect: Completion code " + ex.completionCode + " Reason code " + ex.reasonCode);
                System.out.println(ex.getMessage()); 
            }
        }
                
        return msgText;        
    }
    
	protected static LCDViewTX parseOutput(String msgText)
	{
		LCDViewTX outTrx = null;
		
		if (m_parser == null) {
			// create parser
			try {
				m_parser = SAXParserFactory.newInstance().newSAXParser();
			}
			catch (Exception e) {
				//Global.theLogger.severe("error: Unable to Create SAX Parser");
				e.printStackTrace();
			}
		}
		
		OutputTXHandler m_txHandler = new OutputTXHandler();
		m_txHandler.resetTransactionObject();
		
		try 
		{
			//long xmlStart = System.currentTimeMillis();
			//org.xml.sax.InputSource is = new org.xml.sax.InputSource(new StringReader( msgText));
			m_is.setCharacterStream(new StringReader( msgText));
			m_is.setEncoding("UTF-8");  
			
			m_parser.parse(m_is, m_txHandler);
			//Global.theLogger.fine("XML Parsing Cost(ms):"+(System.currentTimeMillis()-xmlStart));
		}
		catch (SAXParseException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		outTrx = m_txHandler.getTransactionOutput();
		
//		if(ENABLE_TX_LOG){
//			writeTXLog(outTrx, false);
//		}
		//m_parser = null;
		return outTrx;
	}
	
	protected static final void writeTXLog(LCDViewTX inTrx, boolean inFlg){
		
		String postfix = inFlg ? "i" : "o";
		
		File f = new File(MQConstant.MQ_TX_LOG_PATH, inTrx.getTrxID().concat(postfix).concat(".xml"));
		
//C0.00 Remove		FileWriter fw = null;
		BufferedWriter fw = null;	//C0.00 Add
		try
		{
//C0.00 Remove			fw = new FileWriter(f);
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));	//C0.00 Add
			fw.write(inTrx.toXMLString());
		}catch(IOException ioex){
			//m_logger.severe("Failed to write TX Log: "+inTrx.getTrxID().concat(postfix).concat(".xml"));
			ioex.printStackTrace();
		}
		finally{
			try {
				if(fw != null) fw.close();
			} catch (IOException e) {
			}
			fw = null;
		}
		f = null;
	}

}
