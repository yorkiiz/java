package com.ibm.lcd.cfm.monitor.status;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PreferenceDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = -2517796205936722720L;

	private static int S_WIDTH = 230;
	private static int S_HEIGHT = 200;
	
	private JPanel m_mainP;
	private JSpinner m_refreshTimeSpinner = null;
	private JSpinner m_scrollingSpeedSpinner = null;
	
	private int m_monitorInterval;
	private int m_scrollingTextSpeed;
	
	private JButton m_okBtn;
	private JButton m_cancelBtn;
	
	private boolean m_isOKPressed = false;
	
	public PreferenceDialog(Frame owner, boolean modal, int interval, int speed){
		super(owner, modal);
		
		setTitle("Preference");
		m_monitorInterval = interval;
		m_scrollingTextSpeed = speed;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - S_WIDTH) / 2, (screenSize.height - S_HEIGHT) / 2);
		setSize(new Dimension(S_WIDTH, S_HEIGHT));
		setResizable(false);
		
		initPanel();
	}
	
	private void initPanel(){
		m_mainP = new JPanel(new GridBagLayout());
		
		m_mainP.add(new JLabel("Monitor Interval: "), 
				new GridBagConstraints(0,0,1,1,
                0.0,
                0.0,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE,
                new Insets(10, 20, 10, 5),
                0,
                0));
		
		m_refreshTimeSpinner = new JSpinner();
		m_refreshTimeSpinner.setValue(new Integer(m_monitorInterval));
		
		m_refreshTimeSpinner.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				Integer valueI = (Integer) m_refreshTimeSpinner.getValue();
				int time = valueI.intValue();
				if(time < 1 ){
					m_refreshTimeSpinner.setValue(new Integer(1));
					time = 1;
				}
			}
		});
		
		m_mainP.add(m_refreshTimeSpinner, 
				new GridBagConstraints(1,0,2,1,
                1.0,
                1.0,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(10, 5, 10, 20),
                0,
                0));
		////////////////////
		m_mainP.add(new JLabel("Scrolling Speed: "), 
				new GridBagConstraints(0,1,1,1,
                0.0,
                0.0,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE,
                new Insets(10, 20, 10, 5),
                0,
                0));
		
		m_scrollingSpeedSpinner = new JSpinner();
		m_scrollingSpeedSpinner.setValue(new Integer(m_scrollingTextSpeed));
		
		m_scrollingSpeedSpinner.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				Integer valueI = (Integer) m_scrollingSpeedSpinner.getValue();
				int time = valueI.intValue();
				if(time < 1 ){
					m_scrollingSpeedSpinner.setValue(new Integer(1));
					time = 1;
				}
			}
		});
		
		m_mainP.add(m_scrollingSpeedSpinner, 
				new GridBagConstraints(1,1,2,1,
                1.0,
                1.0,
                GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL,
                new Insets(10, 5, 10, 20),
                0,
                0));
		
		/////////
		m_okBtn = new JButton("OK");
		m_okBtn.addActionListener(this);
		m_mainP.add(m_okBtn, 
				new GridBagConstraints(0,2,2,1,
                1.0,
                0.0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE,
                new Insets(5, 5, 10, 5),
                0,
                0));
		
		m_cancelBtn = new JButton("Cancel");
		m_cancelBtn.addActionListener(this);
		m_mainP.add(m_cancelBtn, 
				new GridBagConstraints(2,2,1,1,
                0.0,
                0.0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE,
                new Insets(5, 5, 10, 20),
                0,
                0));
	
		this.getContentPane().add(m_mainP);
	}
	
	public int getUpdatedMonitorInterval(){
		
		return m_monitorInterval;
	}
	
	public int getUpdatedScrollingSpeed(){
		
		return m_scrollingTextSpeed;
	}

	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == m_okBtn){
			
			m_isOKPressed = true;
			
			Integer valueI = (Integer) m_refreshTimeSpinner.getValue();
			int time = valueI.intValue();
			m_monitorInterval = time;
			
			valueI = (Integer) m_scrollingSpeedSpinner.getValue();
			time = valueI.intValue();
			m_scrollingTextSpeed = time;
			
			this.setVisible(false);
			
		}else if(event.getSource() == m_cancelBtn){
			m_isOKPressed = false;
			this.setVisible(false);
		}
	}
	
	public boolean isOKPressed(){
		return m_isOKPressed;
	}
}
