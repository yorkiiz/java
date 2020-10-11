package com.ibm.lcd.ppt.action;

import com.ibm.cxl.util.PropertiesWrapper;
import com.ibm.lcd.commons.LcdConfig;

public class MQConstant 
{
	
	protected static PropertiesWrapper config = LcdConfig.getConfig();
	
	public static String MQ_GATEWAY_SERVER_DEV    = config.getProperty("MQ_GATEWAY_SERVER_DEV");
	public static String MQ_GATEWAY_TRANSMISSION  = config.getProperty("MQ_GATEWAY_TRANSMISSION");//"K1DEV01";	
	public static int    MQ_PORT_TRANSMISSION     = config.getInteger("MQ_PORT_TRANSMISSION");// 1514;
	public static String MQ_DESCRIPTION           = config.getProperty("MQ_DESCRIPTION");//"";
	public static String MQ_NAME                  = config.getProperty("MQ_NAME");//"";
	public static String MQ_CHANNELNAME           = config.getProperty("MQ_CHANNELNAME");//"K1DEV01.SVRCONN";
	
//	public static String MQ_GATEWAY_SERVER_DEV    = "9.191.44.176";
//	public static String MQ_GATEWAY_TRANSMISSION  = "K1DEV01";	
//	public static int    MQ_PORT_TRANSMISSION     = 1514;
//	public static String MQ_DESCRIPTION           = config.getProperty("MQ_DESCRIPTION", "");//"";
//	public static String MQ_NAME                  = config.getProperty("MQ_NAME", "");//"";
//	public static String MQ_CHANNELNAME           = "K1DEV01.SVRCONN";
	
	public static String MQ_SPAREGWNAME           = "";
    public static String SPARE_MQ_GATEWAY_SERVER        = "";
    public static String SPARE_MQ_GATEWAY_TRANSMISSION  = "";
    public static int    SPARE_MQ_PORT_TRANSMISSION     = 0;
    public static String SPARE_MQ_CHANNELNAME           = "";
    public static String SPARE_MQ_OPI_REPLY_QUEUE       = "";
    
    public static String MQ_OPI_REPLY_QUEUE       = config.getProperty("MQ_OPI_REPLY_QUEUE");//"SHARE.REPLY"; 
    
    public static boolean isSpareServerUsed = false;
    
    public static String MQ_TX_LOG_PATH			= config.getProperty("MQ_TX_LOG_PATH");

}
