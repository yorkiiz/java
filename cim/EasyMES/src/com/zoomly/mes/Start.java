package com.zoomly.mes;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//import sun.net.ftp.FtpProtocolException;

import com.zoomly.mes.panels.BoxBoxFrame;
import com.zoomly.mes.panels.BoxFrame;
import com.zoomly.mes.panels.PanelBoxFrame;
import com.zoomly.mes.panels.PanelFrame;

public class Start {
	
	private static JFrame mainFrame;
	
	private static MESConfig mesConfig;
	
	private static FtpConfig ftpConfig;
	
	private static Properties prodList;

	public static void main(String[] args) {
		loadMESConfig();
		loadFtpConfig();
		getProdIdConfig();
		mainFrame = makeMainFrame();
		mainFrame.setSize(600, 400);
		Utility.centerFrame(mainFrame);
		Image deaultIcon = Toolkit.getDefaultToolkit().createImage(
				Utility.getClassLoader().getResource("images/mesicon.png"));
		mainFrame.setIconImage(deaultIcon);
		
		mainFrame.setVisible(true);
	}
	
	private static void loadMESConfig(){
		Properties properties = new Properties();
		InputStream is = Utility.getClassLoader().getResourceAsStream(Utility.MES_CONFIG);
		try {
			properties.load(is);
			mesConfig = new MESConfig();
			mesConfig.setMainFrame(properties.getProperty("main_frame"));
			String ftpEnable = properties.getProperty("ftp_enable");
			if(!Utility.isEmpty(ftpEnable) && ftpEnable.trim().equalsIgnoreCase("NO")){
				mesConfig.setFtpEnable(false);
			}else {
				mesConfig.setFtpEnable(true);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	private static JFrame makeMainFrame(){
		if(mesConfig.getMainFrame().equals("panel")){
			return new PanelFrame();
		}else if(mesConfig.getMainFrame().equals("box")){
			return new BoxFrame();
		}else if(mesConfig.getMainFrame().equals("panelbox")){
			return new PanelBoxFrame();
		}else if(mesConfig.getMainFrame().equals("boxbox")){
			return new BoxBoxFrame();
		}
		return null;
	}
	
	private static void getProdIdConfig(){
		String prodRemoteLoad = Utility.getProdRemotePath();
		Properties properties = new Properties();
		if(getMesConfig().isFtpEnable()){
			FtpHelper ftpHelper = new FtpHelper();
			ftpHelper.connect();
			try {
				properties.load(ftpHelper.readRemoteFile(prodRemoteLoad));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Utility.getText("No_Find_ProdIdMessage"), Utility.MES_TITLE, JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			
			ftpHelper.disConnect();
		}else {
			InputStream is = Utility.getClassLoader().getResourceAsStream(Utility.LOCAL_PROD_ID_FILE);
			try {
				properties.load(is);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		setProdList(properties);
	}
	
	public static MESConfig getMesConfig() {
		return mesConfig;
	}
	
	public static FtpConfig getFtpConfig(){
		return ftpConfig;
	}

	public static Properties getProdList() {
		return prodList;
	}

	public static void setProdList(Properties prodList) {
		Start.prodList = prodList;
	}
	
}
