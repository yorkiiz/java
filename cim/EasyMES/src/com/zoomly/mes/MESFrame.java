package com.zoomly.mes;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@Deprecated
public class MESFrame extends JFrame {

	private static final long serialVersionUID = -3085221246728176768L;
	
	private static final String MES_TITLE = "Hehui MES";
	
	public static final int SHOW_BOX = 1;
	
	public static final int SHOW_PANEL = 2;
	
	private JPanel workPanel;
	
	private JTextField boxIdTF;
	
	private JTextField panelIdTF;
	
	private static FtpConfig ftpConfig;
	
	private int flag;
	
	public MESFrame(int flag){
		super(MES_TITLE);
		this.flag = flag;
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
		
		JLabel label = new JLabel("Local Directory");
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
		gbl.columnWeights = new double[]{0.0, 0.0};
		gbl.rowWeights = new double[]{0.0, 0.0};
		JPanel panel = new JPanel();
		panel.setLayout(gbl);
		
		JLabel boxL = new JLabel("Box ID");
		GridBagConstraints gbc_boxL = new GridBagConstraints();
		gbc_boxL.anchor = GridBagConstraints.NORTHWEST;
		gbc_boxL.gridx = 0;
		gbc_boxL.gridy = 0;
		gbc_boxL.insets = new Insets(10, 10, 10, 10);
		panel.add(boxL, gbc_boxL);
		
		boxIdTF = new JTextField();
		boxIdTF.setMinimumSize(Utility.DEFAULT_COMPONENT_D);
		boxIdTF.setPreferredSize(Utility.DEFAULT_COMPONENT_D);
		GridBagConstraints gbc_boxTF = new GridBagConstraints();
		gbc_boxTF.anchor = GridBagConstraints.NORTHWEST;
		gbc_boxTF.gridx = 1;
		gbc_boxTF.gridy = 0;
		gbc_boxTF.insets = new Insets(10, 10, 10, 10);
		panel.add(boxIdTF, gbc_boxTF);
		
		JLabel panelL = new JLabel("Panel ID");
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
		
		boxIdTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String boxId = boxIdTF.getText();
				if(!Utility.isEmpty(boxId)){
					boolean result = Utility.createBoxFile(boxId.trim());
					if(result){
						JOptionPane.showMessageDialog(null, "Box[" + boxId +"] Success", MES_TITLE, JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Box[" + boxId +"] Failure", MES_TITLE, JOptionPane.ERROR_MESSAGE);
					}
					boxIdTF.setText("");
				}
			}
		});
		
		panelIdTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String panelId = panelIdTF.getText();
				if(!Utility.isEmpty(panelId)){
					boolean result = Utility.createPanelFile(panelId.trim());
					if(result){
						JOptionPane.showMessageDialog(null, "Panel[" + panelId +"] Success", MES_TITLE, JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Panel[" + panelId +"] Failure", MES_TITLE, JOptionPane.ERROR_MESSAGE);
					}
					panelIdTF.setText("");
				}
			}
		});
		
		if(flag == SHOW_BOX){
			panelL.setVisible(false);
			panelIdTF.setVisible(false);
		}else if(flag == SHOW_PANEL){
			boxL.setVisible(false);
			boxIdTF.setVisible(false);
		}
		return panel;
	}

	public static void main(String[] args, int flag) {
		MESFrame mesFrame = new MESFrame(flag);
		mesFrame.setSize(600, 400);
		Utility.centerFrame(mesFrame);
		Image deaultIcon = Toolkit.getDefaultToolkit().createImage(
				Utility.getClassLoader().getResource("images/mesicon.png"));
		mesFrame.setIconImage(deaultIcon);
		loadFtpConfig();
		mesFrame.setVisible(true);
	}
	
	private static void loadFtpConfig(){
		Properties properties = new Properties();
		InputStream is = Utility.getClassLoader().getResourceAsStream(Utility.FTP_CONFIG_FILE);
		try {
			properties.load(is);
			ftpConfig = new FtpConfig();
			ftpConfig.setHost(properties.getProperty("host"));
			String port = properties.getProperty("port");
			port = Utility.isEmpty(port)?"21":port;
			ftpConfig.setPort(Integer.valueOf(port));
			ftpConfig.setUser(properties.getProperty("user"));
			ftpConfig.setPassword(properties.getProperty("password"));
			ftpConfig.setRemotePath(properties.getProperty("remote_path"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static FtpConfig getFtpConfig(){
		return ftpConfig;
	}
	
}
