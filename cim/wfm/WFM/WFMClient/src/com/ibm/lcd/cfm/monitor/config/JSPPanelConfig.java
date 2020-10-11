
package com.ibm.lcd.cfm.monitor.config;

import java.awt.Color;
import java.awt.Font;

public final class JSPPanelConfig {
	
	final String CLASS_NAME = "JSPResultPanelConfig";

	public final static int pct			= 0;
	public final static int proc		= 1;
	public final static int devstat		= 2;
	
	public final static int CANVAS_DIMENSION_W[]		= {	450,450,450,450 };
	
	public final static int CANVAS_DIMENSION_H[]		= {	200,200,200,200 };

	public final static int GRAPH_OFFSET[]			= {	40,	40,	40,	40 };

	public final static int GRAPH_HEIGHT[]			= {	180,180,360,180 };
	
	public final static int MAX_VALUE1[]				= {	20, 20, 20, 20 };
	
	public final static int MAX_VALUE2[]				= {	100,100,100,100 };
	
	public final static int GRAPH_DEVIDE[]			= {	4,	4,	4,	4 };

	public final static int GRAPH_WIDTH[]			= {	450,450,450,450 };

	public final static int GRAPH_WIDTH_DETAIL_E[]	= {	1,	1,	1,	1 };


	public final static String HEADER_NAME_E[]		=  
		{ "Utilization History",
		"Process Rate History",
		"Complete History",
		"Complete History(Stocker to Equipment)",
		"Complete History(Equipment to Stocker)",
		"Complete History(to Stocker)",
		"Transfer Report Count"};

	public final static int HEADER_WIDTH[]			= {	7,	7,	7,	7 };

	public final static int HEADER_HEIGHT[]			= {	19,	19,	19,	19 };

	public final static int SUB_STATUS_WIDTH[]		= {	400,400,400,400 };

	public final static int SUB_STATUS_HEIGHT[]		= {	36,	36,	36,	36 };
	
	public final static Font FONT_H					= new Font("Courier", Font.PLAIN, 12);

	public final static Font FONT_S					= new Font("Courier", Font.PLAIN, 12);

	public final static Font FONT_Y					= new Font("Courier", Font.PLAIN, 11);

	public final static Font FONT_X					= new Font("Courier", Font.PLAIN, 9);

	public final static Font FONT_B					= new Font("Courier", Font.PLAIN, 9);

	public final static Color FONT_COLOR_H			= Color.BLUE;
	
	public final static Color FONT_COLOR_S			= new Color(0,128,0);

	public final static Color FONT_COLOR_Y			= Color.BLACK;

	public final static Color FONT_COLOR_X			= Color.BLACK;

	public final static Color FONT_COLOR_B			= Color.RED;
    
	public final static boolean IS_SCALE_X			= true;

}
