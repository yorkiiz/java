package com.ibm.lcd.cfm.monitor.status;

import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JPanel;

import com.ibm.lcd.cfm.monitor.config.JSPPanelConfig;
import com.ibm.lcd.cfm.monitor.util.DateUtil;
import com.ibm.lcd.cfm.monitor.util.GraphMaker;

public class JSPPanel extends Applet {
	
	private static final long serialVersionUID = 1L;


	public JSPPanel(){
	}
	
	public void paint(Graphics g){
		
		JPanel pl = new JPanel();
		pl.setLayout(new GridLayout(1, 1));
		
		int gtype = Integer.parseInt(getParameter("graphtype"));
		int stype = Integer.parseInt(getParameter("sizetype"));
		int length = Integer.parseInt(getParameter("length"));
		int maxvalue;
		String total;
        String sub_status_name;
		
		if(gtype == JSPPanelConfig.pct){
			maxvalue = 100;
			
			double dbltotal = Double.parseDouble(getParameter("total"));
			total = String.valueOf(dbltotal) + "%";
            sub_status_name = "Avg: ";
		}else{
			maxvalue = Integer.parseInt(getParameter("maxvalue"));
			
			if( maxvalue > JSPPanelConfig.MAX_VALUE2[stype] ){
				int unit = JSPPanelConfig.MAX_VALUE2[stype];
				maxvalue = maxvalue - (maxvalue % unit) + unit;
			}else if( maxvalue > JSPPanelConfig.MAX_VALUE1[stype]){
				int unit = JSPPanelConfig.MAX_VALUE1[stype];
				maxvalue = maxvalue - (maxvalue % unit) + unit;
			}else{
				maxvalue = JSPPanelConfig.MAX_VALUE1[stype];
			}
			
			int inttotal = Integer.parseInt(getParameter("total"));
			total = String.valueOf(inttotal);
            sub_status_name = "TOTAL: ";
		}
		
		String group_id = "";
		if(stype == 3){
			group_id = "Group IDÅF" + getParameter("group_id");
		}
		
		int headertype;
		if(gtype == JSPPanelConfig.devstat){
			headertype = Integer.parseInt(getParameter("headertype"));
		}else{
			headertype = gtype;
		}
		
		String param;
		int[] data_set = new int[length + 1];
		data_set[0] = 0;
		for(int i = 1; i < length+1; i++){
			param = getParameter("param"+ String.valueOf(i-1));
			data_set[i] = (int)Double.parseDouble(param);
		}
		
		String[] data_label = getWidthStatus(DateUtil.formatDate(new Date(), DateUtil.DATE_PATTERN[17]), length);

		Canvas canvas = new GraphMaker(JSPPanelConfig.GRAPH_OFFSET[stype], JSPPanelConfig.GRAPH_HEIGHT[stype],
										maxvalue, JSPPanelConfig.GRAPH_DEVIDE[stype],
										JSPPanelConfig.GRAPH_WIDTH[stype], JSPPanelConfig.GRAPH_WIDTH_DETAIL_E[stype],
										JSPPanelConfig.HEADER_NAME_E[headertype] + "[Past" + length + "H] " + group_id,
										JSPPanelConfig.HEADER_WIDTH[stype],JSPPanelConfig.HEADER_HEIGHT[stype],
                                        sub_status_name + total,
										JSPPanelConfig.SUB_STATUS_WIDTH[stype], JSPPanelConfig.SUB_STATUS_HEIGHT[stype],
										JSPPanelConfig.FONT_H, JSPPanelConfig.FONT_COLOR_H,
										JSPPanelConfig.FONT_S, JSPPanelConfig.FONT_COLOR_S,
										JSPPanelConfig.FONT_Y, JSPPanelConfig.FONT_COLOR_Y,
										JSPPanelConfig.FONT_X, JSPPanelConfig.FONT_COLOR_X,
										JSPPanelConfig.FONT_B, JSPPanelConfig.FONT_COLOR_B,
										JSPPanelConfig.IS_SCALE_X,
										data_set, data_label);
										
		canvas.setSize(new Dimension(700, 600));
		canvas.setBackground(Color.WHITE);
        
		pl.add(canvas);
		add(pl);
	}
	
	
	private String[] getWidthStatus(String timeHH, int length){
		
		int time = Integer.parseInt(timeHH);
		String[] retstr = new String[length + 1];
		retstr[0] = "";
		
		for(int i = 1; i < length+1; i++){
			retstr[i] = "";
			i++;
			
			if((time + i) % 24 < 10)	{ retstr[i] = "0" + String.valueOf( (time + i) % 24 ); }
			else						{ retstr[i] = String.valueOf( (time + i) % 24 ); }
		}
		
		return retstr;
	}
	
}