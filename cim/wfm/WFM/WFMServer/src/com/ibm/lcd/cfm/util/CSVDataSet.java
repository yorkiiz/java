package com.ibm.lcd.cfm.util;

public class CSVDataSet {
//T.02    public static final String DCS = "java:comp/env/jdbc/DCS";		/*T.01*/
    public static final String DCS = "jdbc/DBTRYTFTDCS";				/*T.02*/
//T.02    public static final String HIS = "java:comp/env/jdbc/HIS";		/*T.01*/
    //public static final String HIS = "jdbc/DBTRYTFTHIS";				/*T.02*/
    public static final String HIS = "jdbc/DBMESEXHIS";
//T.02    public static final String PPT = "java:comp/env/jdbc/PPT";		/*T.01*/
    //public static final String PPT = "jdbc/DBTRYTFTPPT";				/*T.02*/
    public static final String PPT = "jdbc/DBMESEXPPT";
    //private String sql = "";
    private boolean isTrim_;
    private boolean isEnclose_;

    private String splitString_;

    private String newLineString_;

    private String encloseString_;

    private String replaceString_;

    public CSVDataSet() {
        setTrim(true);
        setEnclose(true);
        setSplitString(",");
        setNewLineString("\n");
        setEncloseString("\"", "''");
    }

    public void setTrim(boolean flag) {
        this.isTrim_ = flag;
    }

    public boolean getTrim() {
        return isTrim_;
    }

    public void setEnclose(boolean flag) {
        this.isEnclose_ = flag;
    }

    public boolean getEnclose() {
        return isEnclose_;
    }

    public void setSplitString(String string) {
        this.splitString_ = string;
    }

    public String getSplitString() {
        return splitString_;
    }

    public void setNewLineString(String string) {
        this.newLineString_ = string;
    }

    public String getNewLineString() {
        return newLineString_;
    }

    public void setEncloseString(String enclose, String replace) {
        this.encloseString_ = enclose;
        this.replaceString_ = replace;
    }

    public String getEncloseStringE() {
        return encloseString_;
    }

    public String getEncloseStringR() {
        return replaceString_;
    }

//    public void setSelectSQL(String sql) {
//        this.sql = sql;
//    }
    
    public String getSelectSQL(String sql) {
        return sql;
    }
}
