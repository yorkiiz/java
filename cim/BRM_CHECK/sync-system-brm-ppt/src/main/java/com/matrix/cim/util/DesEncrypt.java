//*********************************************************************************
//
// SYSTEM        : LCD View system
//
// PROGRAM NAME  : DesEncrypt.java
//
// Outline       :
//
// (c) Copyright 2013, International Business Machines Corp
//
// Modification history:
//
// DATE        LEVEL  NAME             COMMENT
// ----------  -----  ---------------  --------------------------------------------
// 2013/07/09  A0.00  Suker.Zhang      Initial Release
//*********************************************************************************
 
package com.matrix.cim.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.lang3.ArrayUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class DesEncrypt {
	
    private static final String key = "A1!B2@C3";
    
    public static String doEncrypt(String value) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        //cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
        byte data[] = value.getBytes("UTF-8");
        if (data.length % 8 != 0){
        	int length = 8 - data.length%8;
        	byte spaces[] = new byte[length];
        	for (int i=0;i<spaces.length;i++){
        		spaces[i]=0x0;
        	}
        	data= ArrayUtils.addAll(data, spaces);
        }

        byte encryptedData[] = cipher.doFinal(data);
        return Base64.encode(encryptedData);
    }
    
    public static String doDecrypt(String value) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        //cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte decryptedData[] = cipher.doFinal(base64Decoder.decodeBuffer(value));
        
        for (int i=(decryptedData.length-1); i>0; i--){
    		if (decryptedData[i] == 0x0){
    			decryptedData = ArrayUtils.remove(decryptedData, i);
    		}
    	}
        
        return new String(decryptedData);
    }
    
}