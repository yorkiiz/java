//*********************************************************************************
//
// SYSTEM        : LCD View system
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
// 2009/01/07  A1.00  Mindy Chien      remark B->A
// 2009/03/09  A1.01  Mindy Chien      fix shift data for FAB1
// 2013/11/25  C0.00  Nico Cai         Chg for Module
//*********************************************************************************

package com.ibm.lcd.ppt.action;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ibm.lcdview.LCDViewTX;

public final class OutputTXHandler extends DefaultHandler
{
	private String m_currentElementName = null;
	
	private LCDViewTX m_txObjectParent = null;  //Main TX Output
	private LCDViewTX m_txObject = null;
	
	private String m_transactionName = null;
	private Stack m_subArrayStack;
	private ArrayList m_subArrayL;
	//private String m_currentSubArrayName;
	
	public OutputTXHandler()
	{
		m_subArrayStack = new Stack();
		m_subArrayL = new ArrayList();
	}
	
	public LCDViewTX getTransactionOutput()
	{
		return m_txObjectParent;
	}
	
	public void resetTransactionObject()
	{
		m_txObjectParent = null;
	}
	
	public void startDocument() throws SAXException
	{ 
		m_transactionName = null;
	}
	
	
	public void endDocument() throws SAXException
	{
		m_transactionName = null;
	}
	
	public void startElement(java.lang.String uri,
			java.lang.String localName,
			java.lang.String qName,
			Attributes attributes)
	throws SAXException
	{
		m_currentElementName = qName;
		Field f = null;
		
		if( m_currentElementName.compareTo("transaction") == 0 ) return;
		
		if(m_txObject != null)
		{
			try
			{
				f = m_txObject.getClass().getDeclaredField(m_currentElementName);
			}
			catch(java.lang.NoSuchFieldException nsfe)
			{
				//System.err.println("Failed to get Declared Field: "+m_currentElementName);
				//System.out.println("Err: "+m_currentElementName);
				//nsfe.printStackTrace();
			}
		}
		else{
			if(m_currentElementName.compareTo("transaction") != 0 &&  m_currentElementName.compareTo("trx_id") != 0)
			{
				System.out.println("1 Fatal Error - m_txObject is null! Element: "+m_currentElementName);
			}
		}
		
		if(f != null && f.getType().isArray())
		{
			//m_currentSubArrayName = m_currentElementName;
			try
			{
				Class txClass = f.getType().getComponentType();            
				LCDViewTX newSubObj = (LCDViewTX)txClass.newInstance(); 
				LCDViewTX[] subAry = (LCDViewTX[])f.get(m_txObject);
				for(int i=0; i<subAry.length; i++)
				{
					if(subAry[i] == null){
						subAry[i] = newSubObj;
						break;
					}
				}
				pushSubArray(m_txObject);
				m_subArrayL.add(m_currentElementName); 
				
				m_txObject = newSubObj;
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public void endElement(java.lang.String uri,
			java.lang.String localName,
			java.lang.String qName)
	throws SAXException
	{
		if(m_subArrayL.contains(qName))
		{
			//m_currentElementName = m_currentSubArrayName;
			if( !m_subArrayStack.empty() ){
				m_txObject = popSubArray();
				m_subArrayL.remove(qName);
			}
		}
//		if(m_currentElementName == null)
//		{
//		//m_currentElementName = m_currentSubArrayName;
//		if( !m_subArrayStack.empty() )
//		m_txObject = popSubArray();
//		}

		m_currentElementName = null;
		
	}
	
	public void characters(char[] ch,
			int start,
			int length)
	throws SAXException    
	{
		String data = new String(ch, start, length);
			
//A1.01		if(data.trim().length() == 0)
//A1.01			return; 

		if(m_currentElementName.compareTo("trx_id") == 0)
		{
			if(m_transactionName == null)
			{
				m_transactionName = data;
			}else{
				m_transactionName += data;
			}
			
			if(m_transactionName.length() == 8)
			{
				Class txClass  =null;
				String dcsTransactionName = "D";
				boolean dcsEncounted = false;
				m_transactionName=m_transactionName.replaceFirst("B", "A");	//C0.00 Add
				try {
					txClass = Class.forName("com.ibm.lcdview.tx."+m_transactionName+"o");
//A1.00					txClass = Class.forName("com.ibm.lcdview.tx."+m_transactionName.replaceFirst("^B", "A")+"o");
				} catch (ClassNotFoundException e1) {
					//try to get DCS TX Object
					dcsTransactionName = dcsTransactionName.concat(m_transactionName.substring(1));
					dcsEncounted = true;  
				}		
				if(dcsEncounted)
				{
					try{
						txClass = Class.forName("com.ibm.lcdview.tx."+dcsTransactionName+"o");
//						System.out.println(dcsEncounted+",conver to DCS tx, txClass:" + txClass);
//						txClass = Class.forName("com.ibm.lcdview.tx."+ m_transactionName+"o");
					}catch(Exception e){ 
						e.printStackTrace();
					}
				}
				if(txClass == null){
//					System.exit(0);
					return;
				}
				try {
					m_txObject = (LCDViewTX)txClass.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				m_txObjectParent = m_txObject;             
			}
		}
		
		if(m_currentElementName == null)
		{
			System.out.println("Fatal Error: m_currentElementName is null.");
			return;
		}
		
		if(m_txObject == null)
		{
			System.out.println("m_currentElementName: "+m_currentElementName);
			System.out.println("Fatal Error: m_txObject is null.");
			return;
		}
		
		try
		{
			Field f = m_txObject.getClass().getDeclaredField(m_currentElementName);
			Object obj = f.get(m_txObject);
			if(obj != null){
				data = (String)obj + data;
			}

			f.set(m_txObject, data);
			
		}catch(NoSuchFieldException ex)
		{
			//System.err.println("Failed to get Declared Field: "+m_currentElementName);
//			ex.printStackTrace();
		}
		catch(IllegalAccessException ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void pushSubArray(LCDViewTX obj)
	{ 
		m_subArrayStack.push(obj);
	}
	
	private LCDViewTX popSubArray()
	{ 
		return (LCDViewTX)m_subArrayStack.pop();
	}    
}