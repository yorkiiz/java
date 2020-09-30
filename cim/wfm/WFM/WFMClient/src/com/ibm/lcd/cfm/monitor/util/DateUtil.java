
package com.ibm.lcd.cfm.monitor.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;


public final class DateUtil {

    public static final String CLASS_NAME = "DateUtil";

    public static final int OK = 0;
    public static final int NG_FUTURE = 1;
    public static final int NG_NOT_NUM = 2;
    public static final int NG_TODAY = 3;
    public static final String[] DATE_PATTERN ={"yyyy.MM.dd G 'at' HH:mm:ss z",  // ex1 ) 2004.07.04 AD at 12:08:56 PDT
                                                   "EEE, MMM d, ''yy",              // ex2 ) Wed, Jul 4, '04
                                                   "h:mm a",                        // ex3 ) 12:08 PM
                                                   "hh 'o''clock' a, zzzz",         // ex4 ) 12 o'clock PM, Pacific Daylight Time
                                                   "K:mm a, z",                     // ex5 ) 0:08 PM, PDT
                                                   "yyyyy.MMMMM.dd GGG hh:mm aaa",  // ex6 ) 02004.July.04 AD 12:08 PM
                                                   "EEE, d MMM yyyy HH:mm:ss Z",    // ex7 ) Wed, 4 Jul 2004 12:08:56 -0700
                                                   "yyMMddHHmmssZ",                 // ex8 ) 040704120856-0700
                                                   "G",                             // ex9 ) ����  (�I��)
                                                   "y",                             // ex10) 04    (�N)
                                                   "M",                             // ex11) 7     (??)
                                                   "w",                             // ex12) 22    (�N�ɂ�����T)
                                                   "W",                             // ex13) 5     (??�ɂ�����T)
                                                   "D",                             // ex14) 145   (�N�ɂ������)
                                                   "d",                             // ex15) 25    (??�ɂ������)
                                                   "E",                             // ex16) ??    (�j��)
                                                   "a",                             // ex17) �ߌ�  (�ߑO/�ߌ�)
                                                   "H",                             // ex18) 19    (����ɂ�����?? (0 �` 23))
                                                   "k",                             // ex19) 19    (����ɂ�����?? (1 �` 24))
                                                   "K",                             // ex20) 7     (�ߑO/�ߌ��?? (0 �` 11))
                                                   "h",                             // ex21) 7     (�ߑO/�ߌ��?? (1 �` 12))
                                                   "m",                             // ex22) 53    (��)
                                                   "s",                             // ex23) 23    (�b)
                                                   "S",                             // ex24) 127   (�~??�b)
                                                   "z",                             // ex25) JST   (��ʓI�ȃ^�C??�]�[??)
                                                   "Z",                             // ex26) +0900 (RFC 822 �^�C??�]�[??)
                                                   "yyyy/MM/dd HH:mm:ss",           // ex27) 2004/05/25 19:53:23         (yyyy/MM/dd HH:mm:ss�`��)
                                                   "yyyy�NMM?�dd�� HH?�mm��ss�b",   // ex28) 2004�N05??25�� 19??53��23�b (yyyy�Nmm?�dd�� HH?�mm��ss�b�`��)
                                                   "yyyy/MM/dd HH:mm:ss.SSS",       // ex29) 2004/05/25 19:53:23.127     (yyyy/MM/dd HH:mm:ss.SSS�`��)
                                                   "yyyy:MM:dd",                    // ex30) 2004:05:25
                                                   "yyyy-MM-dd",                    // ex31) 2004-05-25
                                                   "yyyyMMdd",                      // ex32) 20040525
                                                   "HH:mm:ss",                      // ex33) 19:53:23
                                                   "yyyyMMddHHmmSSS",               // ex34) 20040525195323
                                                   "HHmmss"                         // ex35) 195323
                                                   };


    private static Map utilDateFormatters     = new Hashtable();  	// fomatDate(util.Date)�p
    private static Map sqlDateFormatters      = new Hashtable(); 	// fomatDate(sql.Date)�p
    private static Map parseDateFormatters    = new Hashtable(); 	// parseDateFormatters
    private static Map convertDateFormatters  = new Hashtable(); 	// convertDateString�p

    private DateUtil() {
    }

    public static synchronized String formatDate(java.util.Date date, String pattern) {

        DateFormat formatter = (DateFormat) utilDateFormatters.get(pattern);
        if (formatter == null) {
            formatter = new SimpleDateFormat(pattern);
            utilDateFormatters.put(pattern, formatter);
        }
        return formatter.format(date);
    }

    public static synchronized String formatDate(java.sql.Date date, String pattern) {

        DateFormat formatter = (DateFormat) sqlDateFormatters.get(pattern);
        if (formatter == null) {
            formatter = new SimpleDateFormat(pattern);
            sqlDateFormatters.put(pattern, formatter);
        }
        return formatter.format(new java.util.Date(date.getTime()));
    }

    public static synchronized java.util.Date parseDate(String date, String pattern) throws ParseException {
        DateFormat formatter = (DateFormat) parseDateFormatters.get(pattern);
        if (formatter == null) {

            formatter = new SimpleDateFormat(pattern);
            parseDateFormatters.put(pattern, formatter);
        }

        java.util.Date dateObj = null;
        formatter.setLenient(false);
        dateObj = formatter.parse(date);
        formatter.setLenient(true);

        return dateObj;
    }

    public static synchronized String convertDateString(String dateString, String sourcePattern, String convertPattern) {
        DateFormat parser = (DateFormat) convertDateFormatters.get(sourcePattern);
        if (parser == null) {
            parser = new SimpleDateFormat(sourcePattern);
            convertDateFormatters.put(sourcePattern, parser);
        }

        DateFormat formatter = (DateFormat) convertDateFormatters.get(convertPattern);
        if (formatter == null) {
            formatter = new SimpleDateFormat(convertPattern);
            convertDateFormatters.put(convertPattern, formatter);
        }

        try {
            return formatter.format(parser.parse(dateString));
        }
        catch (ParseException Pe) {
            return dateString;
        }

    }

    public static String convertWarekiDate(String warekiDate) throws IndexOutOfBoundsException {

        final int OK_LEN  = 6;  
        final int Y_INDEX = 0;
        final int M_INDEX = 2;
        final int D_INDEX = 4;

        String conWareki    = warekiDate;
        String reConWareki  = "";
        String mkConWareki  = "";
        int warekiLen      = 0;

        warekiLen = conWareki.length();

        if (warekiLen < OK_LEN || warekiLen > OK_LEN) {
            reConWareki = null;
        }
        else {
            mkConWareki = conWareki.substring(Y_INDEX, M_INDEX);      
            mkConWareki += " " + conWareki.substring(M_INDEX, D_INDEX);
            mkConWareki += " " + conWareki.substring(D_INDEX);

            reConWareki = mkConWareki;
        }

        return reConWareki;
    }

    public static String convDatePrintString( String dateString,  String sourcePattern ){

        try{

            String yearPattern = sourcePattern.startsWith("yyyy") == true ? "yyyy" : "yy" ;
            String parsedYear  = convertDateString( dateString, sourcePattern, yearPattern );
            String parsedMonth = convertDateString( dateString, sourcePattern, "M"  );
            String parsedDay   = convertDateString( dateString, sourcePattern, "d"  );

            parsedYear  = parsedYear.length()  == 1  ? " " + parsedYear  : parsedYear;
            parsedYear  = parsedYear.startsWith("0") ? " " + parsedYear.substring(1,parsedYear.length()) : parsedYear;
            parsedMonth = parsedMonth.length() == 1  ? " " + parsedMonth : parsedMonth;
            parsedDay   = parsedDay.length()   == 1  ? " " + parsedDay   : parsedDay;

            return parsedYear + parsedMonth + parsedDay ;

        }catch(Exception e){
            return dateString;
        }
    }

    public static boolean isDate(String date, String pattern) {
        try {
            DateUtil.parseDate(date, pattern);
            return true;
        }
        catch (ParseException Pe) {
            return false;
        }
        catch (NullPointerException NPe) {
            return false;
        }
    }

    public static String getBeforeAfterDate(String nowDate, int beforeAfterCount) {

        //final String METHOD_NAME = "getBeforeAfterDate";

        String rtn = "";

        if(!StringUtil.checkString(nowDate) || !isDate(nowDate, "yyyyMMdd")){
            return rtn;
        }

        Calendar cal = Calendar.getInstance();
        int year  = Integer.parseInt( nowDate.substring( 0, 4));
        int month = Integer.parseInt( nowDate.substring( 4, 6));
        int date  = Integer.parseInt( nowDate.substring( 6, 8));
        cal.set( year, month - 1, date);


        int move = 0;
        if( beforeAfterCount > 0){
            move =  1;
        }else{
            move = -1;
        }

        for(int days = 0; days != beforeAfterCount; days += move){
            cal.add( Calendar.DATE, move);
        }

        Date afterDate = cal.getTime();
        rtn = formatDate( afterDate, "yyyyMMdd");

        return rtn;
    }

    public boolean chechDay(int year, int month, int day) {

        boolean flag = true;
        int max_day  = 0;

        Calendar calendar = new GregorianCalendar();

        calendar.clear();
        calendar.set(year, month-1, 1);
        max_day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        if(day<=0 || day>max_day){
            flag = false;
        }

        return flag;
    }

    public int getMaxDay(int year, int month, int day) {

        int max_day = 0;

        Calendar calendar = new GregorianCalendar();

        calendar.clear();
        calendar.set(year, month-1, 1);
        max_day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        return max_day;
    }

    private static String modifyDate(String yyyymmdd, int gap, int ymdClass) {
        if ((yyyymmdd == null) || yyyymmdd.equals("")) {
            return yyyymmdd;
        }

        boolean isSlashed = (yyyymmdd.indexOf('/') != -1);
        if(isSlashed) {
            yyyymmdd = StringUtil.removeChar(yyyymmdd, '/');
        }

        if (yyyymmdd.length() != 8) {
            throw new IllegalArgumentException("�s���ȓ��t��??�񂪎w�肳��܂����B: [" + yyyymmdd + "]");
        }

        int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
        int mm = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
        int dd = Integer.parseInt(yyyymmdd.substring(6, 8));

        SimpleDateFormat formatter;
        if(isSlashed) {
            formatter = new SimpleDateFormat("yyyy/MM/dd");
        }
        else {
            formatter = new SimpleDateFormat("yyyyMMdd");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setLenient(false);
        calendar.set(yyyy, mm, dd, 0, 0, 0);

        calendar.add(ymdClass, gap);

        return formatter.format(calendar.getTime());
    }

    public static String addDay(String yyyymmdd, int gap) {
        return modifyDate(yyyymmdd, gap, Calendar.DATE);
    }

    public static String addMonth(String yyyymmdd, int gap) {
        return modifyDate(yyyymmdd, gap, Calendar.MONTH);
    }

    public static String addYear(String yyyymmdd, int gap) {
        return modifyDate(yyyymmdd, gap, Calendar.YEAR);
    }

    public static int chkBeforeAfter(String chk_yyyyMMddHHmmSSS, String chk2_yyyyMMddHHmmSSS){
        int flg = -1;

        if( (NumberUtil.numberCheck(chk_yyyyMMddHHmmSSS, 15)) && (NumberUtil.numberCheck(chk_yyyyMMddHHmmSSS, 15)) ){
            int format = NumberUtil.parseInt(chk_yyyyMMddHHmmSSS);
            int chk    = NumberUtil.parseInt(chk2_yyyyMMddHHmmSSS);
            int result = chk - format;

            if(result < 0){
                flg = 1;
            } else if (result > 0){
                flg = 2;
            } else {
                flg = 3;
            }
        }else{
            flg = -1;
        }

        return flg;
    }
}
