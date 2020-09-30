package com.zoomly.mes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;
@Deprecated
public class FtpHelper2 {
	
	public static final String FTP_FILE_SEPARATOR = "/";
	
	private FtpClient ftpClient;
	
	private FtpConfig ftpConfig;
	
	public FtpHelper2(){
		ftpConfig = Start.getFtpConfig()==null?new FtpConfig():Start.getFtpConfig();
	}
	
	/**
	 * 连接并登陆FTP server
	 */
	public void connect(){
		try {
			//初始化并连接
			ftpClient = FtpClient.create(new InetSocketAddress(ftpConfig.getHost(), ftpConfig.getPort()));
			//登陆
			ftpClient.login(ftpConfig.getUser(), ftpConfig.getPassword().toCharArray());
			//二进制方式
			ftpClient.setBinaryType();
			//被动模式
			ftpClient.enablePassiveMode(true);
			//切换到远程目录
			if(!Utility.isEmpty(ftpConfig.getRemotePath())){
				ftpClient.changeDirectory(ftpConfig.getRemotePath());
			}
			
			System.out.println("login success!");
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isConnected(){
		return ftpClient.isConnected();
	}
	
	/**
	 * 断开连接
	 */
	public void disConnect(){
		try {
			ftpClient.close();
			System.out.println("disconnect success!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传文件
	 * @param localFile
	 * @param remoteFile
	 * @return
	 */
	public boolean upload(String localFile, String remoteFile){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			if(remoteFile.indexOf(FTP_FILE_SEPARATOR) > -1){
				String remotePath = remoteFile.substring(0,remoteFile.lastIndexOf(FTP_FILE_SEPARATOR));
				if(!isDirsExist(remotePath)){
					createDirs(remotePath);
				}
			}
			//获取远程文件输入流
			bos = new BufferedOutputStream(ftpClient.putFileStream(remoteFile));
			File file = new File(localFile);
			//获取本地文件输入流
			bis = new BufferedInputStream(new FileInputStream(file));
			byte[] bytes = new byte[1024];
			int c;
			while ((c = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, c);
			}
			System.out.println("upload success!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				if(bis != null){
					bis.close();
				}
				if(bos != null){
					bos.close();
				}
			} catch (Exception e2) {
				
			}
		}
		
	}
	
	/**
	 * 下载文件
	 * @param remoteFile
	 * @param localFile
	 * @return
	 */
	public boolean download(String remoteFile, String localFile){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			//获取远程文件输入流
			bis = new BufferedInputStream(ftpClient.getFileStream(remoteFile));
			File file = new File(localFile);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			//获取本地文件输出流
			bos = new BufferedOutputStream(new FileOutputStream(file));
			byte[] bytes = new byte[1024];
			int c;
			while ((c = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, c);
			}
			System.out.println("download success!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				if(bis != null){
					bis.close();
				}
				if(bos != null){
					bos.close();
				}
			} catch (Exception e2) {
				
			}
		}
	}
	
	public InputStream readRemoteFile(String remoteFile) throws FtpProtocolException, IOException{
		InputStream input = null;
		try{
			input = ftpClient.getFileStream(remoteFile);
		}catch(FtpProtocolException e){
			System.out.println(e.getReplyCode());
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	public List<String> nameList(String path){
		BufferedReader read = null;
		List<String> list = new  ArrayList<String>();
		try {
			//获取远程文件输入流
	        read = new BufferedReader(new InputStreamReader( 
	    					ftpClient.nameList(path),"UTF-8")); 
	        String lineTxt = null; 
	        while((lineTxt = read.readLine()) != null){ 
	            list.add(lineTxt);
	        }
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(read != null){
					read.close();
				}
			} catch (Exception e2) {
				
			}
		}
		return list;
	}
	
	private boolean isDirsExist(String dir){
		try {
			//获得工作目录
			String curDir = ftpClient.getWorkingDirectory();
			//判断指定目录是否存在
			ftpClient.changeDirectory(dir);
			//恢复工作目录
			ftpClient.changeDirectory(curDir);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 递归创建ftp目录
	 * @param dir
	 */
	private void createDirs(String dir){
		if(Utility.isEmpty(dir)){
			return;
		}
		try {
			if(!isDirsExist(dir)){
				int index = dir.lastIndexOf(FTP_FILE_SEPARATOR);
				if(index > -1){
					String tmp = dir.substring(index);
					dir = dir.substring(0, index);
					createDirs(dir);
					dir = dir + tmp;
					if(dir.substring(dir.length()-1).equals(FTP_FILE_SEPARATOR)) return;
				}
				try {
					ftpClient.makeDirectory(dir);
				} catch (Exception e) {
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
