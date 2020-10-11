package com.ibm.lcd.cfm.monitor.layout;

import java.awt.Color;
//import java.util.NoSuchElementException;
//import java.util.StringTokenizer;

//import com.ibm.lcd.cfm.monitor.util.LogUtil;

public class ColorDefinition extends IdentifyObject {
	final String CLASS_NAME = "ColorDefinition";

	public final static int STATUS = 1;
	public final static int MODE = 2;
	public final static int PORT_STATUS = 3;

	public final String main;
//	public String sub;
//	public String sub_upper;
//	public String sub_lower;
	public final int type;
	public Color color = null;
	
	public String description;

	public ColorDefinition(int type, String main, String desc) {
		super(main);

//		final String METHOD_NAME = "ColorDefinition";
//		LogUtil.out(true, CLASS_NAME, METHOD_NAME, "START");

		this.main = main;
		//this.sub = sub;
		this.type = type;
		this.description = desc; 
//		try {
//			StringTokenizer token = new StringTokenizer(sub, "-");
//			this.sub_upper = token.nextToken().trim();
//			this.sub_lower = token.nextToken().trim();
//		} catch (NoSuchElementException e) {
//			this.sub_upper = "";
//			this.sub_lower = "";
//		}
//		finally {
//			LogUtil.out(true, CLASS_NAME, METHOD_NAME, "END");
//		}
	}

//	public final boolean getSubColorDefinition(int type, String main, String sub) 
//	{
//		if (sub.equals(""))
//			return ((this.type == type) && (0 == this.main.compareTo(main)) && (((0 >= this.sub_upper.compareTo(sub)) && (0 <= this.sub_lower
//					.compareTo(sub))) || (0 == this.sub.compareTo(sub))));
//		else
//			return ((this.type == type) && (((0 >= this.sub_upper.compareTo(sub)) && (0 <= this.sub_lower
//					.compareTo(sub))) || (0 == this.sub.compareTo(sub))));
//	}
	
	public final void setDescription(String desc){
		if(desc == null)
			description = "";
		else
			description = desc;
	}
}
