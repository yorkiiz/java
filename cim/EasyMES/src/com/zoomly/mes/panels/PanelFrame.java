package com.zoomly.mes.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.zoomly.mes.FtpHelper;
import com.zoomly.mes.Start;
import com.zoomly.mes.Utility;

public class PanelFrame extends JFrame {

	private static final long serialVersionUID = -7746445778649621906L;

	private JPanel workPanel;
	
	private JTextField panelIdTF;
	
	public PanelFrame(){
		super(Utility.MES_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		workPanel = new JPanel();
		getContentPane().add(workPanel, BorderLayout.CENTER);
		init();
	}
	
	protected void init(){
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = new double[]{1.0};
		gbl.rowWeights = new double[]{0.0, 1.0};
		workPanel.setLayout(gbl);
		
		JLabel label = new JLabel(Utility.getText("Local_Directory"));
		JTextField rootDirTF = new JTextField(Utility.ROOT_DIR);
		rootDirTF.setMinimumSize(Utility.DEFAULT_COMPONENT_D);
		rootDirTF.setPreferredSize(Utility.DEFAULT_COMPONENT_D);
		label.setEnabled(false);
		rootDirTF.setEditable(false);
		JPanel labelP = new JPanel();
		labelP.add(label);
		labelP.add(rootDirTF);
		
		GridBagConstraints gbc_labelP = new GridBagConstraints();
		gbc_labelP.anchor = GridBagConstraints.NORTHWEST;
		gbc_labelP.gridx = 0;
		gbc_labelP.gridy = 0;
		gbc_labelP.insets = new Insets(10, 10, 10, 10);
		workPanel.add(labelP, gbc_labelP);
		
		JPanel mainPanel = createMainPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.anchor = GridBagConstraints.CENTER;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 1;
		gbc_mainPanel.insets = new Insets(10, 10, 10, 10);
		workPanel.add(mainPanel, gbc_mainPanel);
		
		
	}
	
	private JPanel createMainPanel(){
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl.rowWeights = new double[]{0.0, 0.0};
		JPanel panel = new JPanel();
		panel.setLayout(gbl);
		
		JLabel panelL = new JLabel(Utility.getText("Panel_ID"));
		GridBagConstraints gbc_panelL = new GridBagConstraints();
		gbc_panelL.anchor = GridBagConstraints.NORTHWEST;
		gbc_panelL.gridx = 0;
		gbc_panelL.gridy = 1;
		gbc_panelL.insets = new Insets(10, 10, 10, 10);
		panel.add(panelL, gbc_panelL);
		
		panelIdTF = new JTextField();
		panelIdTF.setMinimumSize(Utility.DEFAULT_COMPONENT_D);
		panelIdTF.setPreferredSize(Utility.DEFAULT_COMPONENT_D);
		GridBagConstraints gbc_panelTF = new GridBagConstraints();
		gbc_panelTF.anchor = GridBagConstraints.NORTHWEST;
		gbc_panelTF.gridx = 1;
		gbc_panelTF.gridy = 1;
		gbc_panelTF.insets = new Insets(10, 10, 10, 10);
		panel.add(panelIdTF, gbc_panelTF);
		
		panelIdTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String panelId = panelIdTF.getText();
				if(!Utility.isEmpty(panelId)){
					boolean result = Utility.createPanelFile(panelId.trim());
					if(result){
						upload();
						JOptionPane.showMessageDialog(null, Utility.getText("Panel_[{0}]_Success", panelId), Utility.MES_TITLE, JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, Utility.getText("Panel_[{0}]_Failure", panelId), Utility.MES_TITLE, JOptionPane.ERROR_MESSAGE);
					}
					panelIdTF.setText("");
				}
			}
		});
		
		return panel;
	}
	
	private void upload(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(Start.getMesConfig().isFtpEnable()){
					String panelId = panelIdTF.getText();
					FtpHelper ftpHelper = new FtpHelper();
					ftpHelper.connect();
					String localFile = Utility.getPanelFilePath(panelId);
					System.out.println("localFile:" + localFile);
					
					String remoteFile = Utility.getRemotePanelFilePath(panelId);
					System.out.println("remoteFile:" + remoteFile);
					
					ftpHelper.upload(localFile, remoteFile);
					ftpHelper.disConnect();
				}
			}
		});
	}
}
