
package com.ibm.lcd.cfm.monitor.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil{
    public static final String CLASS_NAME = "LogUtil";
    
    
	// 0: D,I,W,E,F,O
	// 1: I,W,E,F,O
	// 2: W,E,F,O
	// 3: E,F,O
	// 4: F,O
	// 5: 出力しない
	public static final int logNowLevel  = 5;
    

    private static final String TRUE      = "TRUE";
    //private static final String FALSE     = "FALSE";

    private static final int I_ALL   = 0;
    private static final int I_DEBUG = 0;
    private static final int I_INFO  = 1;
    private static final int I_WARN  = 2;
    private static final int I_ERROR = 3;
    private static final int I_FATAL = 4;
    private static final int I_OFF   = 5;

    public static final String S_ALL   = "ALL";
    public static final String S_DEBUG = "DEBUG";
    public static final String S_INFO  = "INFO";
    public static final String S_WARN  = "WARN";
    public static final String S_ERROR = "ERROR";
    public static final String S_FATAL = "FATAL";
    public static final String S_OFF   = "OFF";

    private static final String O_DEBUG = "[D] ";
    private static final String O_INFO  = "[I] ";
    private static final String O_WARN  = "[W] ";
    private static final String O_ERROR = "[E] ";
    private static final String O_FATAL = "[F] ";

    public static final String[] logLevel = {S_ALL,  // デバッグ??ベ??0
                                               S_DEBUG, // デバッグ??ベ??0
                                               S_INFO,  // デバッグ??ベ??1
                                               S_WARN,  // デバッグ??ベ??2
                                               S_ERROR, // デバッグ??ベ??3
                                               S_FATAL, // デバッグ??ベ??4
                                               S_OFF    // デバッグ??ベ??5
                                               };



    public static final String logNowDate = TRUE;



    public static final Level DEBUG = new Level(I_DEBUG, S_DEBUG, O_DEBUG);
    public static final Level INFO  = new Level(I_INFO,  S_INFO,  O_INFO);
    public static final Level WARN  = new Level(I_WARN,  S_WARN,  O_WARN);
    public static final Level ERROR = new Level(I_ERROR, S_ERROR, O_ERROR);
    public static final Level FATAL = new Level(I_FATAL, S_FATAL, O_FATAL);

    public static final Level ALL   = new Level(I_ALL,   S_ALL,   "");
    public static final Level OFF   = new Level(I_OFF,   S_OFF,   "");

    private static Level nowLevel = null;
    private static Boolean dispTime = null;
    private static DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS  ");

    private LogUtil(){
    }

    public static void out(Level level){
        outlog(level, "");
    }

    public static void out(Level level, boolean x){
        outlog(level, String.valueOf(x));
    }

    public static void out(Level level, char x){
        outlog(level, String.valueOf(x));
    }

    public static void out(Level level, int x){
        outlog(level, String.valueOf(x));
    }

    public static void out(Level level, long x){
        outlog(level, String.valueOf(x));
    }

    public static void out(Level level, float x){
        outlog(level, String.valueOf(x));
    }

    public static void out(Level level, double x){
        outlog(level, String.valueOf(x));
    }

    public static void out(Level level, char x[]){
        outlog(level, String.valueOf(x));
    }

    public static void out(Level level, String x){
        outlog(level, x);
    }

    public static void out(Level level, Throwable x){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            PrintStream ps = new PrintStream(baos);
            x.printStackTrace(ps);
            outlog(level, baos.toString());
        }
        catch(Exception ex1){
            outlog(level, x.getMessage());
            outlog(level, ex1.getMessage());
        }
        finally{
            try{
                baos.close();
            }
            catch(Exception ex2){
                outlog(level, ex2.getMessage());
            }
        }
    }

    public static void out(Level level, Object x){
        outlog(level, String.valueOf(x));
    }




    public static void out(){
        out(DEBUG);
    }

    public static void out(boolean x){
        out(DEBUG, x);
    }

    public static void out(char x){
        out(DEBUG, x);
    }

    public static void out(int x){
        out(DEBUG, x);
    }

    public static void out(long x){
        out(DEBUG, x);
    }

    public static void out(float x){
        out(DEBUG, x);
    }

    public static void out(double x){
        out(DEBUG, x);
    }

    public static void out(char x[]){
        out(DEBUG, x);
    }

    public static void out(String x){
        out(DEBUG, x);
    }

    public static void out(Throwable x){
        out(DEBUG, x);
    }

    public static void out(Object x){
        out(DEBUG, x);
    }

    private static void outlog(Level level, String x){
        if(level.level >= logNowLevel && logNowLevel < 5){
            String outMsg;

            setDispTimeFromProperty();

            if(dispTime.equals(Boolean.TRUE)){
                outMsg = fmt.format(new Date()) + level.outStr + x;
            }
            else{
                outMsg = level.outStr + x;
            }

            // ??グ出力
            System.out.println(outMsg);
        }
    }

    public static void setPriority(Level newLevel){
        nowLevel = newLevel;
    }

    public static void setPriorityFromProperty(){
        String propStr = S_ALL;
        try{
            if(logNowLevel>=0 && logNowLevel<5){
                propStr = logLevel[logNowLevel];
            }else{
                propStr = logLevel[6];
            }
        }
        catch(Exception ex){
        }
        Level newLevel = toLevel(propStr);
        setPriority(newLevel);
    }

    public static String getPriority(){
        if(nowLevel == null){
            return null;
        }
        return nowLevel.levelStr;
    }

    private static Level toLevel(String lvStr){
        if(lvStr == null){
            return DEBUG;
        }
        if(lvStr.equals(DEBUG.levelStr) == true){
            return DEBUG;
        }
        if(lvStr.equals(INFO.levelStr)  == true){
            return INFO;
        }
        if(lvStr.equals(WARN.levelStr)  == true){
            return WARN;
        }
        if(lvStr.equals(ERROR.levelStr) == true){
            return ERROR;
        }
        if(lvStr.equals(FATAL.levelStr) == true){
            return FATAL;
        }
        if(lvStr.equals(ALL.levelStr)   == true){
            return ALL;
        }
        if(lvStr.equals(OFF.levelStr)   == true){
            return OFF;
        }
        return DEBUG;
    }

    public static void setDispTime(boolean x){
        dispTime = new Boolean(x);
    }

    public static void setDispTimeFromProperty(){
        String propStr = "false";
        try{
            propStr = logNowDate;
        }
        catch(Exception ex){
        }
        dispTime = new Boolean(propStr);
    }

    public static boolean getDispTime(){
        return dispTime.booleanValue();
    }

    public static void TRACE(boolean DEBUG, String CLASS_NAME, String METHOD_NAME,String DEBUG_LOG){
        if(DEBUG){
            final int defaultBufferSize = 256;
            StringBuffer buffer   = new StringBuffer(defaultBufferSize);

            buffer.append(StringUtil.getCheckedValue(CLASS_NAME, ""));
            buffer.append(".");
            buffer.append(StringUtil.getCheckedValue(METHOD_NAME, ""));
            buffer.append("() ===>");
            buffer.append(StringUtil.getCheckedValue(DEBUG_LOG, ""));

            out(buffer.toString());
        }
    }
    
	public static void out(boolean DEBUG, String CLASS_NAME, String METHOD_NAME,String DEBUG_LOG){
		if(DEBUG){
			final int defaultBufferSize = 256;
			StringBuffer buffer   = new StringBuffer(defaultBufferSize);

			buffer.append(StringUtil.getCheckedValue(CLASS_NAME, ""));
			buffer.append(".");
			buffer.append(StringUtil.getCheckedValue(METHOD_NAME, ""));
			buffer.append("() ===>");
			buffer.append(StringUtil.getCheckedValue(DEBUG_LOG, ""));

			out(buffer.toString());
		}
	}

    private static class Level{
        public static final String CLASS_NAME = "LogUtil$Level";

        final int    level;
        final String levelStr;
        final String outStr;

        Level(int lv, String lvStr, String ouStr){
            this.level    = lv;
            this.levelStr = lvStr;
            this.outStr   = ouStr;
        }
    }
}
