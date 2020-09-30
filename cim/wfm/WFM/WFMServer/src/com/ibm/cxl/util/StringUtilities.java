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
 * Modification                                                          
 *   Date      Level  Author      Description                                
 *   --------  -----  ----------  --------------------------------------  
 * 2004/07/01  L0.00  iiSC        Initial Release.
 * 2006/02/22  L0.01  Y.Tanaka    Add LTrim AND RTrim.
 */
package com.ibm.cxl.util;

/**
 * This class provides convenient methods for manipulating strings.
 *
 * @author IBM Developer
 * @version $Revision: 2771 $ $Date: 2015-01-04 18:15:27 +0800 (周日, 04 一月 2015) $
 */
public class StringUtilities {

    /**
     * Replaces all of old string to new string within a target string.
     *
     * @param target a string object contains a target string.
     * @param oldString a string object contains old string.
     * @param newString a string object contains new string.
     * @return a string object replaced all of old string to new string within a target string.
     */
    public final static String replace(String target, String oldString, String newString) {
        int i = 0, j = 0;
        StringBuffer sb = new StringBuffer();
        if (target != null) {
            while ((j = target.indexOf(oldString, i)) >= 0) {
                sb.append(target.substring(i, j));
                sb.append(newString);
                i = j + oldString.length();
            }
            if (target.length() > i)
                sb.append(target.substring(i));
        }
        return sb.toString();
    }

    /**
     * Left trim.
     *
     * @param target a string object contains a target string.
     * @return a string object replaced all of old string to new string within a target string.
     * @author L0.01
     */
    public final static String LTrim(String target) {
        if (target == null) return target;
        int length = target.length();

        if (length == 0 ) return target;

        if(target.charAt(0) != ' ') return target;
        if(target.charAt(length-1) != ' ') return target.trim();

        for (int i=0; i<length; i++) {
            char c = target.charAt(i);
            if (c != ' ') {
                return target.substring(i, length);
            }
        }

        return target;
    }


    /**
     * Right trim.
     *
     * @param target a string object contains a target string.
     * @return a string object replaced all of old string to new string within a target string.
     * @author L0.01
     */
    public final static String RTrim(String target) {
        if (target == null) return target;
        int length = target.length();

        if (length == 0 ) return target;

        if(target.charAt(length-1) != ' ') return target;
        if(target.charAt(0) != ' ') return target.trim();

        for (int i=length-1; i>0; i--) {
            char c = target.charAt(i);
            if (c != ' ') {
                return target.substring(0, i);
            }
        }

        return target;
    }

} // the end of StringUtilities