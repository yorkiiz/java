package com.zoomly.mes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

import sun.net.ftp.FtpProtocolException;

public class FtpHelper {
	
	public static final String FTP_FILE_SEPARATOR = "/";
	
	private FTPClient ftpClient;
	
	private FtpConfig ftpConfig;
	
	public FtpHelper(){
		ftpConfig = Start.getFtpConfig()==null?new FtpConfig():Start.getFtpConfig();
		ftpClient = new FTPClient();	
	}
	
	/**
	 * 连接并登陆FTP server
	 */
	
	public boolean connect(){  
        boolean isLogin = false;  
        FTPClientConfig ftpClientConfig = new FTPClientConfig();  
        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());  
        ftpClient.setControlEncoding("UTF-8");  
        ftpClient.configure(ftpClientConfig);  
        try {  
            if (ftpConfig.getPort() > 0) {  
                ftpClient.connect(ftpConfig.getHost(), ftpConfig.getPort());  
            } else {  
                ftpClient.connect(ftpConfig.getHost());  
            }  
            // FTP服务器连接回答  
            int reply = this.ftpClient.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                this.ftpClient.disconnect();   
                return isLogin;  
            }  
            ftpClient.login(ftpConfig.getUser(), ftpConfig.getPassword());  
            // 设置传输协议  
            ftpClient.enterLocalPassiveMode();  
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            isLogin = true;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        ftpClient.setBufferSize(1024 * 2);  
        ftpClient.setDataTimeout(30 * 1000);
        System.out.println("成功登录服务器");
        return isLogin;  
    }  
  
    /** 
     * @退出关闭服务器链接 
     * */  
    public void disConnect() {  
        if (null != ftpClient && ftpClient.isConnected()) {  
            try {  
                boolean reuslt = this.ftpClient.logout();// 退出FTP服务器  
                if (reuslt) {  
                    System.out.println("成功退出服务器");  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
                System.out.println("退出FTP服务器异常！" + e.getMessage());  
            } finally {  
                try {  
                    this.ftpClient.disconnect();// 关闭FTP服务器的连接  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    System.out.println("关闭FTP服务器的连接异常！");  
                }  
            }  
        }  
    } 
	
	
	public boolean isConnected(){
		return ftpClient.isConnected();
	}
	
	/**
	 * 上传文件
	 * @param localFile
	 * @param remoteFile
	 * @return
	 */
	public boolean upload(String localFile, String remoteFile){
		BufferedInputStream inStream = null;  
        boolean success = false;  
        try {  
        	if(remoteFile.indexOf(FTP_FILE_SEPARATOR) > -1){
				String remotePath = remoteFile.substring(0,remoteFile.lastIndexOf(FTP_FILE_SEPARATOR));
				
				if(!isDirsExist(remotePath)){
					createDirs(remotePath);
				}
			}
        	inStream = new BufferedInputStream(new FileInputStream(localFile));  
            success = this.ftpClient.storeFile(remoteFile, inStream);  
            if (success == true) {  
                System.out.println("上传成功！");
                return success;  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();   
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (inStream != null) {  
                try {  
                    inStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return success;  
		
	}
	
	/**
	 * 下载文件
	 * @param remoteFile
	 * @param localFile
	 * @return
	 */
	public boolean download(String remoteFile, String localFile){
		
        BufferedOutputStream outStream = null;  
        boolean success = false;  
        try {  
            outStream = new BufferedOutputStream(new FileOutputStream(  
            		localFile));  
            success = this.ftpClient.retrieveFile(remoteFile, outStream);  
            if (success == true) {  
               System.out.println("下载成功！");
                return success;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println(remoteFile + "下载失败");  
        } finally {  
            if (null != outStream) {  
                try {  
                    outStream.flush();  
                    outStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        if (success == false) {  
        	System.out.println(remoteFile + "下载失败");  
        }  
        return success;  
    } 
	
	public InputStream readRemoteFile(String remoteFile) throws FtpProtocolException, IOException{
		InputStream input = null;
		try{
			input = ftpClient.retrieveFileStream(remoteFile);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	public List<String> nameList(String path){
		List<String> list = new  ArrayList<String>();
		String[] strs = null;
		try {
			strs = ftpClient.listNames(path);
			for(String str:strs){
				list.add(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
		return list;
	}
	
	private boolean isDirsExist(String dir){
		try {
			//获得工作目录
			String curDir = ftpClient.printWorkingDirectory();
			//判断指定目录是否存在
			boolean flag = ftpClient.changeWorkingDirectory(dir);
			if(!flag)return false;
			//恢复工作目录
			ftpClient.changeWorkingDirectory(curDir);
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
