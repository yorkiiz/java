/*
 * (C) Copyright IBM Corp. 2003 All rights reserved.
 *
 * US Government Users Restricted Rights Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 *
 * The program is provided "as is" without any warranty express or
 * implied, including the warranty of non-infringement and the implied
 * warranties of merchantibility and fitness for a particular purpose.
 * IBM will not be liable for any damages suffered by you as a result
 * of using the Program. In no event will IBM be liable for any
 * special, indirect or consequential damages or lost profits even if
 * IBM has been advised of the possibility of their occurrence. IBM
 * will not be liable for any third party claims against you.
 *
 * Modification                                                          
 *   Date      Level  Author      Description                                
 *   --------  -----  ----------  --------------------------------------  
 * 2004/07/01  L0.00  iiSC        Initial Release.
 * 2005/01/11  L0.01  Y.Tanaka    Add alphabetCheck,digitCheck,twoByteCheck,lengthCheck
 * 2005/05/26  L0.02  Y.Tanaka    Add dateCheck, timeCheck
 */
package com.ibm.cxl.util;

/**
 * This class provides utilities for JSP.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public class JspUtilities {
    public static final int NORMAL              = 0;                    //L0.02
    public static final int ERROR               = 1;                    //L0.02
    public static final int ERROR_DATE          = ERROR;                //L0.02
    public static final int ERROR_MONTH         = ERROR + 1;            //L0.02
    public static final int ERROR_DAY           = ERROR + 2;            //L0.02
    public static final int ERROR_TIME          = ERROR;                //L0.02
    public static final int ERROR_HOUR          = ERROR + 1;            //L0.02
    public static final int ERROR_MIN           = ERROR + 2;            //L0.02
    public static final int ERROR_SEC           = ERROR + 3;            //L0.02

    /**
     * Returns an encoded string converted the specified object.
     * If the object can not be cast as the string object, returns a blank.
     *
     * @param obj a target object.
     * @return a string object contains encoded string.
     */
    public final static String encode(Object obj) {
        return encode(obj, false);
    }

    /**
     * Returns an encoded string converted the specified object.
     * If the object can not be cast as the string object, returns a blank.
     *
     * @param obj a target object.
     * @param encodeSpace true to encode ASCII space; false to not encode
     * @return a string object contains encoded string
     */
    public final static String encode(Object obj, boolean encodeSpace) {
        String str = null;
        try {
            str = obj.toString();
        } catch (Exception ex) {
            return "";
        }
        str = StringUtilities.replace(str, "&", "&amp;");
        str = StringUtilities.replace(str, "<", "&lt;");
        str = StringUtilities.replace(str, ">", "&gt;");
        str = StringUtilities.replace(str, "\"", "&quot;");
        if (encodeSpace)
            str = StringUtilities.replace(str, " ", "&nbsp;");
        return str;
    }

    /**
     * Return true if input string is digit or alphabet.
     * else false.
     *
     * @param input as String
     * @return true or false 
     */
    public final static boolean digitAlphabetCheck(String input){
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if( (c < '0' || c > '9') &&
                (c < 'a' || c > 'z') &&
                (c < 'A' || c > 'Z') ){
                return false;
            }
        }
        return true;
    }

    /**
     * Return true if input string is alphabet.
     * else false.
     *
     * @param input as String
     * @return true or false 
     * @auther L0.01
     */
    public final static boolean alphabetCheck(String input){
        if (input == null) return true;
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if( (c < 'a' || c > 'z') &&
                (c < 'A' || c > 'Z') ){
                return false;
            }
        }
        return true;
    }

    /**
     * Return true if input string is digit.
     * else false.
     *
     * @param input as String
     * @return true or false 
     * @auther L0.01
     */
    public final static boolean digitCheck(String input) {
        if (input == null) return true;
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if( (c < '0' || c > '9')){
                return false;
            }
        }
        return true;
    }

    /**
     * Return true if input string is twoByteet.
     * else false.
     *
     * @param input as String
     * @return true or false 
     * @auther L0.01
     */
    public final static boolean twoByteCheck(String input){
        if (input == null) return true;
        for(int i = 0; i < input.length(); i++) {
            byte[] bytes = input.substring(i, i+1).getBytes();
            if (bytes.length == 2) return false;
        }
        return true;
    }

    /**
     * Return true if input string is lengthCheck.
     * else false.
     *
     * @param input as String
     * @return true or false 
     * @auther L0.01
     */
    public final static boolean lengthCheck(String input, int length){
        if (input == null) return true;
        byte[] bytes = input.getBytes();

        return bytes.length <= length;
    }

    /**
     * Date check (yyyyMMdd)
     *
     * @param input as String
     * @param input as String
     * @param input as String
     * @return NORMAL, ERROR_DATE, ERROR_MONTH, ERROR_DAY
     * @auther L0.02
     */
    public final static int dateCheck(String year, String month, String day){
        try{
            int intYear = Integer.parseInt(year);
            int intMonth = Integer.parseInt(month);
            int intDay = Integer.parseInt(day);

            if (intMonth <0 || intMonth>12) return ERROR_MONTH;

            if(intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11){
                if (intDay>30 || intDay<=0) return ERROR_DAY;

            } else if(intMonth == 2) {
                //In the case of February 
                if ((intYear % 100) == 0){
                    if ((intYear % 400) == 0){
                        //In the case of the leap year 
                        if (intDay>29 || intDay<=0) return ERROR_DAY;
                    } else {
                        if (intDay>28 || intDay<=0) return ERROR_DAY;
                    }
                } else if ((intYear % 4) == 0){
                    //In the case of the leap year 
                    if (intDay>29 || intDay<=0) return ERROR_DAY;
                } else {
                    if (intDay>28 || intDay<=0) return ERROR_DAY;
                }
            } else {
                if (intDay>31 || intDay<=0) return ERROR_DAY;
            }

        } catch(Exception e) {
            return ERROR_DATE;
        }

        return NORMAL;
    }

    /**
     * Time check
     *
     * @param input as String
     * @param input as String
     * @param input as String
     * @return NORMAL, ERROR_HOUR, ERROR_MIN, ERROR_SEC
     * @auther L0.02
     */
    public final static int timeCheck(String hour, String minute , String second){
        try{

            int intHour = Integer.parseInt(hour);
            if (intHour<0 || intHour>24) return ERROR_HOUR;

            if (minute != null) {
                int intMin = Integer.parseInt(minute);
                if (intMin<0 || intMin>60) return ERROR_MIN;
            }

            if (second != null) {
                int intSec = Integer.parseInt(second);
                if (intSec<0 || intSec>60) return ERROR_SEC;
            }

        } catch(Exception e) {
            return ERROR_TIME;
        }

        return NORMAL;
    }
} // the end of class
