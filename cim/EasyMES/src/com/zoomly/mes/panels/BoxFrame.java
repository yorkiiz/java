package com.zoomly.mes.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import sun.net.ftp.FtpProtocolException;

import com.zoomly.mes.FtpHelper;
import com.zoomly.mes.Start;
import com.zoomly.mes.Utility;

public class BoxFrame extends JFrame {
	
	private static final long serialVersionUID = -7304684328539330069L;

	private JPanel workPanel;
	
	private JTextField boxIdTF;
	
	public BoxFrame(){
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
		gbl.columnWeights = new double[]{0.0, 0.0};
		gbl.rowWeights = new double[]{0.0, 0.0};
		JPanel panel = new JPanel();
		panel.setLayout(gbl);
		
		JLabel boxL = new JLabel(Utility.getText("Bag_ID"));
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
		
		boxIdTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String boxId = boxIdTF.getText();
				if(!Utility.isEmpty(boxId)){
					boolean result = Utility.createBoxFile(boxId.trim());
					if(result){
						upload();
						download();
						JOptionPane.showMessageDialog(null, Utility.getText("Box_[{0}]_Success", boxId), Utility.MES_TITLE, JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, Utility.getText("Box_[{0}]_Failure", boxId), Utility.MES_TITLE, JOptionPane.ERROR_MESSAGE);
					}
					boxIdTF.setText("");
				}
			}
		});
		
		return panel;
	}
	
	private void download(){
		
		if(Start.getMesConfig().isFtpEnable()){
			String boxId = boxIdTF.getText();
			FtpHelper ftpHelper = new FtpHelper();
			ftpHelper.connect();
			
			String remoteFile = Utility.getRemoteBoxFilePath(boxId , ftpHelper);
			String localPanelFile = Utility.getPanelListFile();
			String remotePanelList = Utility.getRemotePanelListPath();
	        
			savePanelList(remoteFile, localPanelFile);
			ftpHelper.upload(localPanelFile, remotePanelList);
			
			ftpHelper.disConnect();
		}
//		else{
//			JOptionPane.showMessageDialog(null, Utility.getText("FTP_not_connet"), Utility.MES_TITLE, JOptionPane.INFORMATION_MESSAGE);
//		}
	}
	
	private void savePanelList(String remoteFile, String panelFile) {
		BufferedReader reader = null;
		Properties localProperties = new Properties();
		Properties remoteProperties = new Properties();
		try {
			FtpHelper ftpHelper = new FtpHelper();
			ftpHelper.connect();
			//获取源文件输入流
			reader = new BufferedReader(                         
	            	new InputStreamReader(ftpHelper.readRemoteFile(remoteFile),"UTF-8"));
			ftpHelper.disConnect();
			String lineTxt = null;
			while((lineTxt = reader.readLine()) != null){ 
                String[] strs = lineTxt.split(",");
                String prodId = "";
                if(strs.length<9||strs[8]==""){
                	
                }else{
                	if(strs[8].endsWith(";")){
                		strs[8] = strs[8].substring(0, strs[8].indexOf(";"));
                	}
                	prodId = strs[8];
                }
                remoteProperties.setProperty(strs[1], prodId);
            }
			localProperties = Utility.readProperties(panelFile);
			localProperties.putAll(remoteProperties);
			
			Utility.saveProperties(localProperties, panelFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(reader != null){
					reader.close();
				}
			} catch (Exception e2) {
				
			}
		}
		
	}
	
	private void upload(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(Start.getMesConfig().isFtpEnable()){
					String boxId = boxIdTF.getText();
					FtpHelper ftpHelper = new FtpHelper();
					ftpHelper.connect();
					String localFile = Utility.getBoxFilePath(boxId);
					System.out.println("localFile:"+localFile);
					
					String remoteFile = Utility.getRemoteBoxFilePath(boxId);
					System.out.println("remoteFile:"+remoteFile);
					ftpHelper.upload(localFile, remoteFile);
					ftpHelper.disConnect();
				}
			}
		});
	}

}
