package com.ibm.lcd.ppt.action.inquiry;

import java.util.ArrayList;

import com.ibm.lcdview.LCDViewTX;

public class TXUtilities {

//	public static HashMap toMap(LCDViewTX tx) {
//		java.util.HashMap rtnMap = new java.util.HashMap();
//		
//		Field[] fields = null;
//		
//		try
//		{
//			fields = tx.getClass().getFields();
//		}
//		catch(SecurityException se)
//		{
//			se.printStackTrace();
//			fields = new Field[0];
//		}
//		
//		try
//		{
//			String data = null;
//			LCDViewTX subArray = null;
//			
//			for(int i=0; i<fields.length; i++)
//			{
//				Object obj = fields[i].get(tx);
//				
//				if(fields[i].getDeclaringClass().isInterface()) continue;   //Do not print the interface variables
//				if(Modifier.isStatic(fields[i].getModifiers())) continue;   //Do not print the interface variables
//				
//				
//				if(obj instanceof String)
//				{
//					data = (String)obj;
//					if(data == null || data.length() == 0) data = "";
//					
//					rtnMap.put(fields[i].getName(), data);
//					
//
//				}
//				
//				if( obj instanceof Character ){
//					Character cdata = (Character)obj;
//					
//					rtnMap.put(fields[i].getName(), String.valueOf(cdata));                
//				}
//			}
//		}
//		catch(IllegalAccessException iae)
//		{
//			iae.printStackTrace();
//		}
//		return rtnMap;
//	}

	public static ArrayList subAry2List(LCDViewTX[] tx) {
		ArrayList rtnList = new ArrayList();
		
		for(int i=0; i<tx.length; i++){
			if(tx[i] == null) break;
			rtnList.add(tx[i]);
		}
		
		return rtnList;
	}


}
