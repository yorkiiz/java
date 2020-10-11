//*********************************************************************************
//
// SYSTEM        : LCD View system
//
// PROGRAM NAME  : LCDViewTX.java
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
//*********************************************************************************

package com.ibm.lcdview;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
 

public class LCDViewTX implements Cloneable, Map
{
	public final static String TX_TYPE_INPUT = "I"; 
	public final static String TX_TYPE_OUTPUT = "O"; 
	
	public final static String TX_ID_KEY    = "trx_id";
	public final static String TYPE_ID_KEY  = "type_id";
	public final static String RTN_CODE_KEY = "rtn_code";
	public final static String RTN_MESG_KEY = "rtn_mesg";
	
	private final static String CHAR_LESS_THAN          = "<";
	private final static String CHAR_LARGER_THAN        = ">";
	private final static String CHAR_LESS_THAN_SLASH    = "</";
	
	protected final String getDeclaredStringField(String filedName)
	{
		String rtnStr = null;
		
		Field f;
		try
		{
			f = this.getClass().getDeclaredField(filedName);
			if(f.getType().equals(String.class))
				rtnStr = (String)f.get(this);
		}
		catch (NoSuchFieldException e) {
			rtnStr = null;
		} catch (IllegalArgumentException e) {
			rtnStr = null;
		} catch (IllegalAccessException e) {
			rtnStr = null;
		}
		
		return rtnStr;
	}
	
	public final String getTrxID(){
		return getDeclaredStringField(TX_ID_KEY);
	}
	
	public final String getTypeID(){
		return getDeclaredStringField(TYPE_ID_KEY);
	}
	
	public boolean isTrxTypeInput(){
		return TX_TYPE_INPUT.equals(getTypeID());
	}
	
	public boolean isTrxTypeOutput(){
		return TX_TYPE_OUTPUT.equals(getTypeID());
	}
	
	public final String getReturnCode(){
		return getDeclaredStringField(RTN_CODE_KEY);
	}
	
	public final String getReturnMessage(){
		return getDeclaredStringField(RTN_MESG_KEY);
	}   
	
	public String toXMLString()
	{
		StringWriter sw = new StringWriter();
		
		sw.write("<?xml version='1.0' encoding='UTF-8' ?>");
		sw.write("<transaction>");
		
		toXMLElement(sw);
		
		sw.write("</transaction>");
		sw.flush();
		
		try
		{
			sw.close();        
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		
		return sw.toString();
	}
	
	public final void toXMLElement(StringWriter sw)
	{
		Field[] fields = null;
		
		try
		{
			fields = this.getClass().getFields();
		}
		catch(SecurityException se)
		{
			se.printStackTrace();
			fields = new Field[0];
		}
		
		try
		{
			String data = null;
			LCDViewTX subArray = null;
			
			for(int i=0; i<fields.length; i++)
			{
				Object obj = fields[i].get(this);
				
				if(fields[i].getDeclaringClass().isInterface()) continue;   //Do not print the interface variables
				if(Modifier.isStatic(fields[i].getModifiers())) continue;   //Do not print the interface variables
				
				if(fields[i].getType().isArray())
				{
					for(int j=0; j<Array.getLength(obj); j++ )
					{
						//System.out.println(j);
						subArray = (LCDViewTX)Array.get(obj, j);
						if(subArray != null)
						{
							sw.write(CHAR_LESS_THAN);
							sw.write(fields[i].getName());
							sw.write(CHAR_LARGER_THAN);
							
							subArray.toXMLElement(sw);
							
							sw.write(CHAR_LESS_THAN_SLASH);
							sw.write(fields[i].getName());
							sw.write(CHAR_LARGER_THAN);
						}
					}
					
					continue;
				}
				
				if(obj instanceof String)
				{
					data = (String)obj;
					if(data == null || data.length() == 0) continue;
					
					sw.write(CHAR_LESS_THAN);
					sw.write(fields[i].getName());
					sw.write(CHAR_LARGER_THAN);
					
					/*if( fields[i].getName().compareTo(TX_ID_KEY) == 0){
					 sw.write('A');
					 sw.write(data.substring(1));
					 }
					 else
					 {*/
					data = data.replaceAll("&", "&amp;");
					data = data.replaceAll("<", "&lt;");
					data = data.replaceAll(">", "&gt;");
					data = data.replaceAll("'", "&apos;");
					data = data.replaceAll("\"", "&quot;");
					sw.write(data);
					//}
					sw.write(CHAR_LESS_THAN_SLASH);
					sw.write(fields[i].getName());
					sw.write(CHAR_LARGER_THAN);
				}
				
				if( obj instanceof Character ){
					Character cdata = (Character)obj;
					//if(cdata == null) continue;
					
					sw.write(CHAR_LESS_THAN);
					sw.write(fields[i].getName());
					sw.write(CHAR_LARGER_THAN);
					
//					sw.write(cdata.toString());
					data = cdata.toString();
					data = data.replaceAll("&", "&amp;");
					data = data.replaceAll("<", "&lt;");
					data = data.replaceAll(">", "&gt;");
					data = data.replaceAll("'", "&apos;");
					data = data.replaceAll("\"", "&quot;");
					sw.write(data);
					
					sw.write(CHAR_LESS_THAN_SLASH);
					sw.write(fields[i].getName());
					sw.write(CHAR_LARGER_THAN);                    
				}
				
				/*if(obj instanceof com.ibm.lcdview.tx.AUTHORITY_CHK)
				 {
				 com.ibm.lcdview.tx.AUTHORITY_CHK ac = (com.ibm.lcdview.tx.AUTHORITY_CHK)obj;
				 if(ac == null) continue;
				 
				 sw.write("<");
				 sw.write(fields[i].getName());
				 sw.write(">");
				 
				 ac.toXMLElement(sw);
				 //sw.write();
				  
				  sw.write("</");
				  sw.write(fields[i].getName());
				  sw.write(">");
				  ac = null;
				  }*/
			}
		}
		catch(IllegalAccessException iae)
		{
			iae.printStackTrace();
		}
	}
	
	public void resetPulbicStringTypeFields(){
		Field fileds[] = null;
		try
		{
			fileds = getClass().getDeclaredFields();
		}
		catch(SecurityException se)
		{
			se.printStackTrace();
		}
		if(fileds == null)
			return;
		for(int i=0;  i<fileds.length;  i++){
			if(fileds[i].getType().equals(String.class) && Modifier.isPublic(fileds[i].getModifiers())){
				try {
					fileds[i].set(this, "");
				} catch (Exception e) {
				}
			}
		}
	}
	
	public String[] copyPulbicStringTypeFields(LCDViewTX sourceTx){
		Field fileds[] = null;
		try
		{ 
			if(sourceTx != null)System.out.println("sourceTx"+sourceTx.getClass());
			fileds = getClass().getDeclaredFields(); 
 
		}
		catch(SecurityException se)
		{
			se.printStackTrace();
		}
		if(fileds == null)
			return null;
		ArrayList existedFieldsV = new ArrayList();
		for(int i=0;  i<fileds.length;  i++){
			if(fileds[i].getType().equals(String.class) && Modifier.isPublic(fileds[i].getModifiers())){
				try {
					String sourceValue = sourceTx.getDeclaredStringField(fileds[i].getName());
					if(sourceValue != null){
						existedFieldsV.add(fileds[i].getName());
						fileds[i].set(this, sourceValue);
					}
				} catch (Exception e) {
				}
			}
		}
		String existedFields[] = new String[existedFieldsV.size()];
		System.arraycopy(existedFieldsV.toArray(), 0, existedFields, 0, existedFieldsV.size());
		
		return existedFields;
	}
	
	public Object clone(){
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
			return null;
		}
		Field fileds[] = null;
		try
		{
			fileds = getClass().getDeclaredFields();
		}
		catch(SecurityException se)
		{
			se.printStackTrace();
		}
		if(fileds != null){
			for(int i=0;  i<fileds.length;  i++){
				if(fileds[i].getType().isArray()){
					String cloneNotSupportedThisField = "Clone failed! Field type : " + fileds[i].getType().getComponentType().getName() + "[], field name : " + fileds[i].getName();
					try {
						if(!fileds[i].getType().getComponentType().isPrimitive()){
							Object subArray[] = (Object[]) fileds[i].get(this);
							Object cloneSubArray[] = (Object[]) java.lang.reflect.Array.newInstance(subArray.getClass().getComponentType(), subArray.length);
							fileds[i].set(obj, cloneSubArray);
							for(int j=0;  j<subArray.length;  j++){
								if(subArray[j] != null){
									Method cloneMethod = subArray[j].getClass().getMethod("clone", null);
									cloneSubArray[j] = cloneMethod.invoke(subArray[j], null);
								}
							}
						}else{
							throw new CloneNotSupportedException(cloneNotSupportedThisField);
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						System.err.println(cloneNotSupportedThisField);
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						System.err.println(cloneNotSupportedThisField);
						e.printStackTrace();
					}
				}
			}
		}
		return obj;
	}
	
	public Object get(Object key){
		
		//System.out.println("Key: "+key);
		String keyStr = (String)key;
		
		Object fieldValue = null;
		
		try {
			Field f = getClass().getDeclaredField(keyStr);
			fieldValue = f.get(this);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			//e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		//System.out.println("Value: "+fieldValue);
		return  fieldValue;
	}

	public void clear() {
		System.out.println("clear");
	}

	public boolean containsKey(Object key) {
		System.out.println("containsKey");
		String keyStr = (String)key;
		
		boolean rtnVal = true;
		try {
			Field f = getClass().getDeclaredField(keyStr);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			//e.printStackTrace();
			rtnVal = false;
		}
		return rtnVal;
	}

	public boolean containsValue(Object key) {
		System.out.println("containsValue");
		return false;
	}

	public Set entrySet() {
		System.out.println("entrySet");
		return null;
	}

	public boolean isEmpty() {
		System.out.println("isEmpty");
		return false;
	}

	public Set keySet() {
		System.out.println("keySet");
		return null;
	}

	public Object put(Object arg0, Object arg1) {
		System.out.println("put");
		return null;
	}

	public void putAll(Map arg0) {
		System.out.println("putAll");
	}

	public Object remove(Object arg0) {
		System.out.println("remove");
		return null;
	}

	public int size() {
		System.out.println("size");
		return 0;
	}

	public Collection values() {
		System.out.println("values");
		return null;
	}	
	
}