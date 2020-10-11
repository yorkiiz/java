package com.zoomly.mes;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JFrame;

public class Utility {
	
	public static final String MES_TITLE = "Hehui MES";
	
	public static final Dimension DEFAULT_COMPONENT_D = new Dimension(150, 23);
	
	public static final Dimension DIMENSION_LEN_40 = new Dimension(40, 23);
	
	public static final String DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final SimpleDateFormat SDF = new SimpleDateFormat(DATA_FORMAT);
	
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public static final String CSV_FILE_SUFFIX = ".csv";
	
	public static final String TABLE_FILE_SUFFIX = ".table";
	
	public static final String MES_CONFIG = "config/MESConfig.properties";
	
	public static final String FTP_CONFIG_FILE = "config/ftp.properties";
	
	public static final String REMOTE_PROD_ID_FILE = "prodIdMessage.properties";
	
	public static final String LOCAL_PROD_ID_FILE = "config/prodIdMessage.properties";
	
	public static final String ROOT_DIR = "d:\\hehui";
	
//	public static final String ROOT_DIR = "\\\\192.168.219.128\\hehui";
	
	public static final String PROP_PANELLIST = "PANELLIST";
	
	public static final Locale DEFAULT_LOCALE = new Locale("zh", "CN");
	
	public static boolean isEmpty(String str){
		if(str == null || str.trim().length() == 0){
			return true;
		}
		return false;
	}
	
	public static boolean createBoxFile(String boxId){
		//get file path
		String filePath = getBoxFilePath(boxId);
		//get file content
		String content = boxId + "," + getCurrentDate();
		//create file
		File file = new File(filePath);
		if(file.exists()){
			renameCSVFile(file);
		}
		boolean result = createFile(filePath, content);
		return result;
	}
	
	public static boolean createPanelFile(String panelId){
		//get file path
		String filePath = getPanelFilePath(panelId);
		//get file content
		String content = panelId + "," + getCurrentDate();
		//create file
		boolean result = createFile(filePath, content);
		return result;
	}
	
	public static boolean createPanelFile(String panelId, String boxId){
		//get file path
		String filePath = getPanelFilePath(panelId);
		//get file content
		String content = panelId + "," + getCurrentDate() + "," + boxId + "," + getInnerBoxTablePath(boxId);
		//create file
		boolean result = createFile(filePath, content);
		saveInnerBoxTable(panelId, boxId);
		return result;
	}
	
	private static void saveInnerBoxTable(String panelId, String boxId){
		//get file path
		String filePath = getInnerBoxTablePath(boxId);
		//get file content
		String content = getPanelFilePath(panelId) + "," + boxId;
		//save file
		Properties properties = readProperties(filePath);
		properties.setProperty(panelId, content);
		saveProperties(properties, filePath);
	}
	
	public static boolean createInnerBoxFile(String innerBoxId, String outerBoxId){
		//get file path
		String filePath = getInnerBoxFilePath(innerBoxId);
		//get file content
		String content = innerBoxId + "," + getCurrentDate() + "," + outerBoxId + "," + getOuterBoxTablePath(outerBoxId);
		//create file
		boolean result = createFile(filePath, content);
		saveOuterBoxTable(innerBoxId, outerBoxId);
		return result;
	}
	
	private static void saveOuterBoxTable(String innerBoxId, String outerBoxId){
		//get file path
		String filePath = getOuterBoxTablePath(outerBoxId);
		//get file content
		String content = getInnerBoxTablePath(innerBoxId) + "," + outerBoxId;
		//save file
		Properties properties = readProperties(filePath);
		properties.setProperty(innerBoxId, content);
		saveProperties(properties, filePath);
	}
	
	public static Properties readProperties(String filePath){
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			properties.load(is);
		}catch (FileNotFoundException e) {
			try {
				File file = new File(filePath);
				if(!file.getParentFile().exists()){
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}if(is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	
	public static void saveProperties(Properties properties, String filePath){
		BufferedWriter write = null;
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			write = new BufferedWriter(new OutputStreamWriter(fos));
			for (Entry<Object, Object> entry : properties.entrySet()) {
				write.write(entry.getKey().toString());
				write.write("=");
				write.write(entry.getValue().toString().replaceAll("\\\\", "/"));
				write.newLine();
			}
			write.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(write != null){
				try {
					write.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static boolean createFile(String filePath, String content){
		File file = new File(filePath);
		BufferedWriter bw = null;
		try {
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if( bw != null ){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Deprecated
	private static void renameCSVFile(File file){
		String content = readCSVFile(file);
		if(content == null){
			return;
		}
		String[] strs = content.split(",");
		String name = strs[0];
		String lastTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if(strs.length < 2){
			lastTime = sdf.format(new Date());
		}else{
			lastTime = strs[1];
			try {
				Date date = SDF.parse(lastTime);
				lastTime = sdf.format(date);
			} catch (ParseException e) {
				lastTime = sdf.format(new Date());
			}
		}
		String path = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(FILE_SEPARATOR)+1);
		String newName = path + name + "_" + lastTime + CSV_FILE_SUFFIX;
		file.renameTo(new File(newName));
	}
	
	private static String readCSVFile(File file){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			return br.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static String getBoxFilePath(String boxId){
		//get file path: BOX/yyyyMMdd/boxId.csv
		String path = generateLocalPath(ROOT_DIR, "BOX", getCurrentDayNoDelimiter());
		String fileName = boxId + CSV_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getBoxLocalFilePath(String boxId ){
		//get file path: download/boxId.csv
		String path = generateLocalPath(ROOT_DIR, "download");
		String fileName = boxId + CSV_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getRemoteBoxFilePath(String boxId){
		//get file path: BOX/yyyyMMdd/boxId.csv
		String path = generateFtpPath("", "BOX", getCurrentDayNoDelimiter());
		String fileName = boxId + CSV_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getRemoteBoxFilePath(String boxId ,FtpHelper ftpHelper){
		//get file path: WorderId/boxId_callendar.csv
		String worderId = boxId.substring(9,22);
		List<String> list = ftpHelper.nameList(""+worderId+"/"+ boxId +"*");
		String filePath = "";
		filePath = list.get(0);
		for(String str:list){
			if(filePath.compareTo(str)<0){
				filePath = str;
			}
		}
		return filePath;
	}
	
	public static String getRemotePanelListPath(){
		//get file path: download/panellist.table
		String path = generateFtpPath("", "download");
		String fileName = PROP_PANELLIST + TABLE_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getPanelListFile(){
		//get file path: download/panellist.table
		String path = generateLocalPath(ROOT_DIR, "download");
		String fileName = PROP_PANELLIST + TABLE_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getPanelFilePath(String panelId){
		//get path: PANEL/yyyyMMdd/lotId/panelId.csv
		String lotId = "UNKNOW";
		if (panelId.trim().length() > 5) {
			lotId = panelId.substring(0, panelId.length() - 5);
		}
		String path = generateLocalPath(ROOT_DIR, "PANEL", getCurrentDayNoDelimiter(), lotId);
		String fileName = panelId + CSV_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getRemotePanelFilePath(String panelId){
		//get path: PANEL/yyyyMMdd/lotId/panelId.csv
		String lotId = "UNKNOW";
		if (panelId.trim().length() > 5) {
			lotId = panelId.substring(0, panelId.length() - 5);
		}
		String path = generateFtpPath("", "PANEL", getCurrentDayNoDelimiter(), lotId);
		String fileName = panelId + CSV_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getInnerBoxTablePath(String boxId){
		//get path: Inner_Box/yyyyMM/table/boxId.table
		String path = generateLocalPath(ROOT_DIR, "Inner_Box", getCurrentMonthNoDelimiter(), "table");
		String fileName = boxId + TABLE_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getRemoteInnerBoxTablePath(String boxId){
		//get path: Inner_Box/yyyyMM/table/boxId.table
		String path = generateFtpPath("", "Inner_Box", getCurrentMonthNoDelimiter(), "table");
		String fileName = boxId + TABLE_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getRemoteInnerBoxPath(String boxId){
		//get path: Inner_Box/yyyyMM/table/boxId.table
		String path = generateFtpPath("", "Inner_Box", getCurrentMonthNoDelimiter(), "csv");
		String fileName = boxId + CSV_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getRemoteOuterBoxTablePath(String boxId){
		//get path: Inner_Box/yyyyMM/table/boxId.table
		String path = generateFtpPath("", "Outer_Box", getCurrentMonthNoDelimiter(), "table");
		String fileName = boxId + TABLE_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getProdRemotePath(){
		//get path: Inner_Box/yyyyMM/table/boxId.table
		//String path = generateFtpPath("", "Outer_Box");
		String path = generateFtpPath("");
		String fileName = REMOTE_PROD_ID_FILE;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getInnerBoxFilePath(String innerBoxId){
		//get path: Inner_Box/yyyyMM/csv/innerBoxId.csv
		String path = generateLocalPath(ROOT_DIR, "Inner_Box", getCurrentMonthNoDelimiter(), "csv");
		String fileName = innerBoxId + CSV_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String getOuterBoxTablePath(String outerBoxId){
		//get path: Outer_Box/yyyyMM/table/outerBoxId.table
		String path = generateLocalPath(ROOT_DIR, "Outer_Box", getCurrentMonthNoDelimiter(), "table");
		String fileName = outerBoxId + TABLE_FILE_SUFFIX;
		String filePath = path + fileName;
		return filePath;
	}
	
	public static String generateLocalPath(String rootDir, String... strs){
		String path = rootDir;
		if(!path.endsWith(FILE_SEPARATOR)){
			path = path + FILE_SEPARATOR;
		}
		for (String string : strs) {
			path += string + FILE_SEPARATOR;
		}
		return path;
	}
	
	public static String generateFtpPath(String rootDir, String... strs){
		String path = rootDir;
		if(!isEmpty(path) && !path.endsWith(FtpHelper.FTP_FILE_SEPARATOR)){
			path = path + FtpHelper.FTP_FILE_SEPARATOR;
		}
		for (String string : strs) {
			path += string + FtpHelper.FTP_FILE_SEPARATOR;
		}
		return path;
	}
	
	public static String getCurrentDate(){
		return SDF.format(new Date());
	}
	
	public static String getCurrentDayNoDelimiter(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
	
	public static String getCurrentMonthNoDelimiter(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(new Date());
	}
	
	public static ClassLoader getClassLoader(){
		return Utility.class.getClassLoader();
	}
	
	public static void centerFrame(JFrame frame){
		Dimension dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimension2 = frame.getSize();
		if(dimension2.height > dimension1.height){
			dimension2.height = dimension1.height;
		}
		if(dimension2.width > dimension1.width){
			dimension2.width = dimension1.width;
		}
		frame.setLocation((dimension1.width - dimension2.width)/2, (dimension1.height - dimension2.height)/2);
	}
	
	/**
	 * 多语言处理
	 * @param key
	 * @param l
	 * @return
	 */
	private static String getText(String key, Locale l){
		ResourceBundle resb = ResourceBundle.getBundle("messages", l);
		String text = null;
		try {
			text = resb.getString(key);
		} catch (Exception e) {
			if(text == null){
				if(!isEmpty(key) && key.indexOf("_") > 0){
					text = key.replaceAll("_", " ");
				}else {
					text = key;
				}
			}
		}
		
		return text;
	}
	
	/**
	 * 多语言处理
	 * @param key
	 * @return
	 */
	public static String getText(String key){
		return getText(key, DEFAULT_LOCALE);
	}
	
	public static String getText(String pattern, Object... arguments){
		String result = getText(pattern);
		return MessageFormat.format(result, arguments);
	}
	
	public static boolean isDuplicatePanel(String panelId, String boxId){
		String filePath = getInnerBoxTablePath(boxId);
		Properties properties = readProperties(filePath);
		if(properties.containsKey(panelId)){
			return true;
		}
		return false;
	}
	
	public static boolean isDuplicateBox(String innerBoxId, String outerBoxId){
		String filePath = getOuterBoxTablePath(outerBoxId);
		Properties properties = readProperties(filePath);
		if(properties.containsKey(innerBoxId)){
			return true;
		}
		return false;
	}
	
	public static String getProdIdByPanelId(String panelId){
		String prodId = "";
		Properties properties = new Properties();
		String localPanelFile = getPanelListFile();
		String remotePanelList = getRemotePanelListPath();
		properties = Utility.readProperties(localPanelFile);
		prodId = properties.getProperty(panelId);
		if(prodId == "" || Utility.isEmpty(prodId)){
			FtpHelper ftpHelper = new FtpHelper();
			ftpHelper.connect();
			try {
				properties.load(ftpHelper.readRemoteFile(remotePanelList));
			} catch (Exception e) {
				e.printStackTrace();
			}
			ftpHelper.disConnect();
			prodId = properties.getProperty(panelId);
		}
		return prodId;
	}
	
	public static String getPanelIdByInnerBoxId(String innerBoxId){
		Properties properties = new Properties();
		String localInnerBoxFile = getInnerBoxTablePath(innerBoxId);
		String remoteFile = getRemoteInnerBoxTablePath(innerBoxId);		
		properties = Utility.readProperties(localInnerBoxFile);
		String randPanel = "";
		if(properties.size()>0){
			for (Entry<Object, Object> entry : properties.entrySet()) {
				randPanel = (String)entry.getKey();
			}				
		}
		if(randPanel == "" || Utility.isEmpty(randPanel)){
			FtpHelper ftpHelper = new FtpHelper();
			ftpHelper.connect();
			try {
				properties.load(ftpHelper.readRemoteFile(remoteFile));
				
			} catch (Exception e) {
				
			}
			ftpHelper.disConnect();
			if(properties.size()>0){
				for (Entry<Object, Object> entry : properties.entrySet()) {
					randPanel = (String)entry.getKey();
				}				
			}
		}
		return randPanel;
	}
	
}
