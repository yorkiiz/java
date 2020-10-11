
package com.ibm.lcd.cfm.monitor.util;

import java.math.BigDecimal;

public class NumberUtil {

    private NumberUtil(){
    }

   
    public static int parseInt(String value){
        if(value != null){
            try{
               return (int)Math.round( Double.parseDouble(value.trim()) );
            }
            catch(NumberFormatException NFe){
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static long parseLong(String value){
        if(value != null){
            try{
                return Math.round( Double.parseDouble(value.trim()) );
            }
            catch(NumberFormatException NFe){
                return 0L;
            }
        }
        else{
            return 0L;
        }
    }

    public static double parseDouble(String value){
        if(value != null){
            try{
                return Double.parseDouble(value.trim());
            }
            catch(NumberFormatException NFe){
                return 0.0;
            }
        }
        else{
            return 0.0;
        }
    }

    public static String formatNumDigit(long value, int digit){
        StringBuffer buffer = new StringBuffer(String.valueOf(value));
        for(int count = buffer.length(); count < digit; count++){
            buffer.insert(0,"0");
        }
        return new String(buffer);
    }

     public static boolean numberCheck(String string, int length) {

         boolean flag = false;

         for (int i=0 ; i<=length ; i++) {
             char num = string.charAt(i);

             if ((new String("1234567890")).indexOf(num) < 0 &&
                 !Character.isISOControl(num)) {
                 flag = true;
             }
         }
         return flag;
     }

    public static double decimalRounding(BigDecimal from, int num) {
        if (num >= 0) {
            BigDecimal to = from.setScale(num, BigDecimal.ROUND_HALF_UP);

            return to.doubleValue();

        } else {
            return from.doubleValue();
        }
    }
    
	public static double decimalRounding(String strDouble, int num, int pattern) {
			BigDecimal from;
			BigDecimal to;

            Double dbl = new Double(NumberUtil.parseDouble(strDouble));

			from = new BigDecimal(dbl.toString());
			
			if(pattern == 1){
			    to = from.setScale(num, BigDecimal.ROUND_HALF_UP);
			}else if(pattern == 2){
				to = from.setScale(num, BigDecimal.ROUND_UP);
			}else if(pattern == 3){
				to = from.setScale(2,BigDecimal.ROUND_DOWN);	
			}else{
				return from.doubleValue();
			}
			
		return to.doubleValue();
	}
}
