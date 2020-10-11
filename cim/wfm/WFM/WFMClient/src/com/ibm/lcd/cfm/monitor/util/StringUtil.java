//*********************************************************************************
//
// SYSTEM        : LCD View system
//
// PROGRAM NAME  : StringUtil.java
//
// Outline       :
//
// (c) Copyright 2005, International Business Machines Corp
//
// Modification history:
//
// DATE        LEVEL  NAME             COMMENT
// ----------  -----  ---------------  --------------------------------------------
// 2005/08/01  A0.00  IBM              Initial Release
// 2013/12/03  C0.00  Nico Cai         Chg for Encoding
//*********************************************************************************
package com.ibm.lcd.cfm.monitor.util;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;


public class StringUtil {

    private static final String EMPTY_STRING   = "";
    private static final String ZEN_SPACE      = " ";
    private static final String HAN_SPACE      = " ";

    public static boolean checkString(String value){
        if((value != null) && (0 < value.length())){
            return true;
        }
        else{
            return false;
        }
    }

    public static String getCheckedValue(String value, String defaultValue){
        if(checkString(value) == true){
            return value;
        }
        else{
            return defaultValue;
        }
    }

    public static int getCheckedValue(String value, int defaultValue){
        try{
            return Integer.parseInt(value.trim());
        }
        catch(Exception e){
            return defaultValue;
        }
    }

    public static double getCheckedValue(String value, double defaultValue){
        try{
            return Double.parseDouble(value.trim());
        }
        catch(Exception e){
            return defaultValue;
        }
    }

    public static String trim(String value){
        if(value != null){
            return value.trim();
        }
        else{
            return "";
        }
    }

    public static String trimAll(String strBef) {

        if (strBef == null || EMPTY_STRING.equals(strBef)) {
            return EMPTY_STRING;
        }

        String strAft = strBef;

        for (;;) {
            if (strAft.indexOf(ZEN_SPACE) == 0 || strAft.indexOf(HAN_SPACE) == 0) {
                strAft = strAft.substring(1);

            } else {
                break;
            }
        }

        StringBuffer sb = new StringBuffer(strAft);
        sb.reverse();
        strAft = sb.toString();

        for (;;) {
            if (strAft.indexOf(ZEN_SPACE) == 0 || strAft.indexOf(HAN_SPACE) == 0) {
                strAft = strAft.substring(1);

            } else {
                break;
            }
        }

        sb = new StringBuffer(strAft);

        sb.reverse();

        strAft = sb.toString();

        return strAft;
    }

    public static String ltrim(String value) {
        if ((value == null) || (value.length() == 0)) {
            return value;
        }

        if (!value.startsWith(" ") && !value.startsWith("　")) {
            return value;
        }

        int offset = 0;

        for (; offset < value.length(); offset++) {
            String charValue = value.substring(offset, offset + 1);

            if (!charValue.equals(" ") && !charValue.equals("　")) {
                break;
            }
        }

        return value.substring(offset);
    }


    public static String rtrim(String value) {
        if ((value == null) || (value.length() == 0)) {
            return value;
        }

        if (!value.endsWith(" ") && !value.endsWith("　")) {
            return value;
        }

        int offset = value.length() - 1;

        for (; offset >= 0; offset--) {
            String charValue = value.substring(offset, offset + 1);

            if (!charValue.equals(" ") && !charValue.equals("　")) {
                break;
            }
        }

        return value.substring(0, offset + 1);
    }

    public static String replace(String targetStr, String oldPart, String newPart) {
        if (targetStr == null || targetStr.equals("")) {
            return targetStr;
        }
        if (oldPart == null || oldPart.equals("")) {
            return targetStr;
        }
        if (newPart == null) {
            return targetStr;
        }
        if (oldPart.equals(newPart)) {
            return targetStr;
        }
        if (targetStr.indexOf(oldPart) == -1) {
            return targetStr;
        }

        int baseOffset = 0;
        int offset = 0;
        int oldLength = oldPart.length();
        StringBuffer sbuf = new StringBuffer();

        while ((offset = targetStr.indexOf(oldPart, baseOffset)) != -1) {
            sbuf.append(targetStr.substring(baseOffset, offset));
            sbuf.append(newPart);
            baseOffset = offset + oldLength;
        }

        sbuf.append(targetStr.substring(baseOffset));

        return sbuf.toString();
    }

    public static String removeChar(String targetStr, char needlessChar) {
        if (targetStr == null || targetStr.equals("")) {
            return targetStr;
        }
        StringTokenizer st = new StringTokenizer(targetStr, "" + needlessChar);
        StringBuffer sbuf = new StringBuffer();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            sbuf.append(token);
        }

        return sbuf.toString();
    }


    public static String getSpecNumStringHEAD(String text, int length, boolean delFlg){
        if(length <= 0){
            return text;
        }

        String specText = "";
        specText = StringUtil.getCheckedValue(text, "");
        int afterLength = length;
        int nowLength = specText.length();

        if(nowLength > afterLength){
            if(delFlg){
                specText.substring(nowLength - afterLength);
                return specText;
            }else{
                return specText;
            }

        } else if (nowLength == afterLength) {
            return specText;

        } else {
            int cal = afterLength - nowLength;
            String blank = makeEmptyString(cal);

            specText = blank + specText ;
        }

        return specText;
    }

    public static String getSpecNumStringTAIL(String text, int length, boolean delFlg){
        if(length <= 0){
            return text;
        }

        String specText = "";
        specText = StringUtil.getCheckedValue(text, "");
        int afterLength = length;
        int nowLength = specText.length();
        if(nowLength > afterLength){
            if(delFlg){
                specText.substring(0, length);
                return specText;
            }else{
                return specText;
            }

        } else if (nowLength == afterLength) {
            return specText;

        } else {
            int cal = afterLength - nowLength;
            String blank = makeEmptyString(cal);

            specText = specText + blank ;
            return specText;
        }
    }

    public static String makeEmptyString(int length){

        StringBuffer sb = new StringBuffer();
        for(int i=0;i<length;i++){
            sb.append(HAN_SPACE);
        }
        return sb.toString();
    }

    public static String getTrueSizeString( String value, int size ){

        if( ! StringUtil.checkString(value) ){
            return EMPTY_STRING;
        }
        if ( size <= 0 ) {
            return EMPTY_STRING;
        }

        StringBuffer newString = new StringBuffer();  
        byte[] byteChar = null;                       
        int newStringLength = 0;                       

        for ( int i = 0 ; i < value.length() ; i++ ) {
            byteChar = value.substring( i , (i + 1) ).getBytes();

            if ( byteChar.length == 1 ) {
                if ( newStringLength < size ) {
                    newString.append( value.substring( i , (i + 1) ) );
                }
                else {
                    break;
                }
            }
            else if ( byteChar.length == 2 ) {
                if ( ( size - newStringLength ) >= 2 ) {
                    newString.append( value.substring( i , (i + 1) ) );
                }
                else if ( ( size - newStringLength ) == 1 ) {
                    newString.append(HAN_SPACE);
                    break;
                }
                else {
                    break;
                }
            }
            newStringLength += byteChar.length;

        }

        return newString.toString();
    }


    public static String addtoN( String value, int size ){
        StringBuffer buffer = new StringBuffer(128);

        if( ! StringUtil.checkString(value) ){
            value = EMPTY_STRING;
        }else{
            if( !value.equals( makeEmptyString( value.length() ) )){
                value = StringUtil.substitute( value, HAN_SPACE, EMPTY_STRING );
            }
        }

        byte[] byteStr = value.getBytes();   
        int byteSize = size * 2;
        int addCount = ( byteSize - byteStr.length ) / 2;

        if ( addCount >= 0 ) {
            buffer.append( value );
            if ( (byteStr.length % 2) == 1 ) {
                buffer.append(HAN_SPACE);
            }
            for ( int i = 0 ; i < addCount ; i++ ) {
                buffer.append(ZEN_SPACE);
            }
        }
        else {
            buffer.append( getTrueSizeString( value, byteSize ) );
        }

        return buffer.toString();
    }


    public static String addtoN2( String value, int size ){
        StringBuffer buffer = new StringBuffer(128);

        if( ! StringUtil.checkString(value) ){
            value = EMPTY_STRING;
        }else{
            if( !value.equals( makeEmptyString( value.length() ) )){
                value = StringUtil.substitute( value, HAN_SPACE, EMPTY_STRING );
            }
        }

        byte[] byteStr = value.getBytes();
        int byteSize = size * 2;
        int addCount = ( byteSize - byteStr.length ) / 2;

        if ( addCount >= 0 ) {
            if ( (byteStr.length % 2) == 1 ) {
                buffer.append(HAN_SPACE);
            }
            for ( int i = 0 ; i < addCount ; i++ ) {
                buffer.append(ZEN_SPACE);
            }

            buffer.append( value );
        }
        else {
            buffer.append( getTrueSizeString( value, byteSize ) );
        }

        return buffer.toString();
    }

    public static String substitute( String str, String src, String dest ){

        if(str == null){
            return "";
        }
        else if((src == null) || (dest == null)){
            return str;
        }
        else{
            if( str.indexOf( src ) != -1 ){

                int pos = str.length();
                StringBuffer sb = new StringBuffer( str );

                while( (pos = str.lastIndexOf( src, pos - 1 ) ) != -1 ){
                    sb.delete( pos, pos + src.length()  );
                    sb.insert( pos, dest );
                }

                str = sb.toString();
            }

            return str;
        }
    }


    public static String decode(String str, String enc)
            throws UnsupportedEncodingException, NullPointerException {

        if(enc.compareTo("") == 0) {
            return(str);
        }
//C0.00 Remove        return(new String(str.getBytes("ISO-8859-1"), enc));
        return(new String(str.getBytes("UTF-8"), enc));	//C0.00 Add
//        return(new String(str.getBytes("GB2312"), enc));
    }

    public static String encode(String str, String enc)
            throws UnsupportedEncodingException, NullPointerException {

        if(enc.compareTo("") == 0) {
            return(str);
        }
//        return(new String(str.getBytes(enc), "ISO-8859-1"));
//C0.00 Remove        return(new String(str.getBytes(enc), "GB2312"));
        return(new String(str.getBytes("UTF-8"), enc));	//C0.00 Add
    }
}
