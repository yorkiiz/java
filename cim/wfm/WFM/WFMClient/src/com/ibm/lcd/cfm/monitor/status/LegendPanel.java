
package com.ibm.lcd.cfm.monitor.status;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ibm.lcd.cfm.monitor.config.MonitorConfig;
import com.ibm.lcd.cfm.monitor.layout.ColorDefinition;
import com.ibm.lcd.cfm.monitor.layout.MonitorLayout;
import com.ibm.lcd.cfm.monitor.util.StringUtil;

public final class LegendPanel extends JPanel {
	private static final long serialVersionUID = 8773518420098358545L;

	
	public static final int HORIZONTAL_LEGEND  	= 0;
	public static final int VERTICAL_LEGEND  	= 1;
	
	private static final String EMPTY_STR = "    "; 
	
	private int m_type;
	
	private static final Font m_modeF = new Font("Serif", Font.BOLD, 10) ;
	private static final Font m_legendF = new Font("Sans-Serif", Font.PLAIN, 10) ;
	
    public LegendPanel(MonitorLayout layout) {
        super();
        m_type = VERTICAL_LEGEND;
        initPanel(layout);
    }

    public LegendPanel(MonitorLayout layout, int type) {
        super();
        m_type = type;
        initPanel(layout);
    }
    
    private void initPanel(MonitorLayout layout) {

        if (layout == null)
            return;

        ColorDefinition[] colorDef = layout.getColorDefinition();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        //JPanel panel = new JPanel(gridbag);
        this.setLayout(gridbag);
        this.setBackground(Color.WHITE);
        
        gbc.insets = new Insets(1, 1, 1, 1);

        appendLegend(colorDef, ColorDefinition.STATUS, this, gridbag, gbc);
        appendLegend(colorDef, ColorDefinition.MODE, this, gridbag, gbc);
        appendLegend(colorDef, ColorDefinition.PORT_STATUS, this, gridbag, gbc);

        JLabel dummyL = new JLabel();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets.top = 0;
        gbc.insets.left = 0;
        gbc.insets.bottom = 0;
        gbc.insets.right = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = m_y;
        gridbag.setConstraints(dummyL, gbc);
        
        this.add(dummyL);
    }
    
    private int m_x = 0;
    private int m_y = 0;
    
    private void appendLegend(ColorDefinition[] colorDef, int type, JPanel panel, GridBagLayout gridbag, GridBagConstraints gbc)
    {

        if (colorDef == null || colorDef.length <= 0)
            return;

        JLabel textLabel = null;

        if (type == ColorDefinition.MODE){
            textLabel = new JLabel(MonitorConfig.getMessage("legend.type.mode"));
            
        }else if (type == ColorDefinition.STATUS){
            textLabel = new JLabel(MonitorConfig.getMessage("legend.type.status"));
            
        }else if (type == ColorDefinition.PORT_STATUS){
            textLabel = new JLabel(MonitorConfig.getMessage("legend.type.portstatus"));
        }

        if (textLabel != null) 
        {
            textLabel.setFont(m_modeF);
            textLabel.setForeground(Color.BLUE);
            //textLabel.setVerticalAlignment(JLabel.CENTER);
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.gridwidth = (m_type == VERTICAL_LEGEND) ?  GridBagConstraints.REMAINDER : 1; 
            gbc.insets.top = 2;
            gbc.insets.left = 2;
            gbc.weightx = 1.0;
            gbc.weightx = (m_type == VERTICAL_LEGEND) ? 1.0 :0.0; 
            
            if(m_type == VERTICAL_LEGEND){
            	gbc.gridx = 0;
            }
            else{
            	m_x = 0;
            	gbc.gridx = m_x++;
            }
            
            if(m_type == VERTICAL_LEGEND)
            	gbc.gridy = m_y++;
            else
            	gbc.gridy = ++m_y;
            
            gridbag.setConstraints(textLabel, gbc);
            panel.add(textLabel);
        }


		JLabel textL = null;
		JLabel colorL = null;
		
		int colCnt = 0;
		
        for (int i = 0; i < colorDef.length; i++) 
        {
            if (colorDef[i].type != type)
                continue;
			
            colCnt++;
            
            colorL = new JLabel(EMPTY_STR);
            colorL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if (colorDef[i].color != null) {
                colorL.setOpaque(true);
                colorL.setBackground(colorDef[i].color);
            }
			
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.gridwidth = 1;
            gbc.insets.top = 2;
            gbc.insets.left = (m_type == VERTICAL_LEGEND)? 10 : 5;
            gbc.weightx = 0.0;
            
            if(m_type == VERTICAL_LEGEND){
            	 gbc.gridx = m_x;

            }else{
//             	if(colCnt == 7)
//             		m_x = 1;
            	gbc.gridx = m_x++;
            }
            if(m_type == VERTICAL_LEGEND){
            	gbc.gridy = m_y;
            }else{
//            	if(colCnt == 7)
//            		m_y++;
            	gbc.gridy = m_y;
            }
            gridbag.setConstraints(colorL, gbc);
            panel.add(colorL);

            String def = StringUtil.getCheckedValue(colorDef[i].main, "");
            StringUtil.trim(def);
            

            StringBuffer sb = new StringBuffer(def);
            if( colorDef[i].description != null && colorDef[i].description.trim().length() != 0)
            {
            	sb.append('(');
            	sb.append(colorDef[i].description);
            	sb.append(')');
            }
            textL= new JLabel(sb.toString());
            textL.setFont(m_legendF);

            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.gridwidth = 1;
            gbc.weightx = 1.0;
            gbc.insets.top = 2;
            gbc.insets.left = 2;
            gbc.insets.right = 0;
            
            if(m_type == VERTICAL_LEGEND)
            	gbc.gridx = m_x+1;
           else
        	   gbc.gridx = m_x++;
           
           if(m_type == VERTICAL_LEGEND)
        	   gbc.gridy = m_y++;
           else
           		gbc.gridy = m_y;

           gridbag.setConstraints(textL, gbc);
        
    	   panel.add(textL);
        }
    }
}
