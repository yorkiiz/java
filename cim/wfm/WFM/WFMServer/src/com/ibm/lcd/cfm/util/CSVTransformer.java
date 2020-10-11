
package com.ibm.lcd.cfm.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.ibm.cxl.util.GenericTable;
import com.ibm.cxl.util.StringUtilities;
//import com.ibm.lcd.cfm.monitor.util.LogUtil;


public class CSVTransformer {
    final String CLASS_NAME = "CSVTransformer";

    /** Whether trim column data. */
    private boolean isTrim_;

    /** Whether enclose column data with enclose string. */
    private boolean isEnclose_;

    /** A string which is used to split each column data. */
    private String splitString_;

    /** A string which is used to new line. */
    private String newLineString_;

    /** A string which is used to enclose column data */
    private String encloseString_;

    /** A string which is used to replace the enclose strings in column data */
    private String replaceString_;

    public CSVTransformer() {
        setTrim(true);
        setEnclose(true);
        setSplitString(",");
        setNewLineString("\n");
        setEncloseString("\"", "''");
    }

    public void setTrim(boolean flag) {
        isTrim_ = flag;
    }

    public void setEnclose(boolean flag) {
        isEnclose_ = flag;
    }

    public void setSplitString(String string) {
        splitString_ = string;
    }

    public void setNewLineString(String string) {
        newLineString_ = string;
    }

    public void setEncloseString(String enclose, String replace) {
        encloseString_ = enclose;
        replaceString_ = replace;
    }

    public String transform(ResultSet rs) throws SQLException {

//        final String METHOD_NAME = "transform";
//        LogUtil.TRACE(true, CLASS_NAME, METHOD_NAME, "START");

        int i = 0;
        String str = null;
        StringBuffer sb = new StringBuffer();

        ResultSetMetaData meta = rs.getMetaData();
        for (i = 0; i < meta.getColumnCount(); i++) {
            if (i > 0)
                sb.append(splitString_);
            str = meta.getColumnName(i + 1);
            if (isTrim_)
                str = str.trim();
            if (isEnclose_) {
                str = StringUtilities.replace(str, encloseString_, replaceString_);
                str = encloseString_ + str + encloseString_;
            }
            sb.append(str);
        }
        while (rs.next()) {
            sb.append(newLineString_);
            for (i = 0; i < meta.getColumnCount(); i++) {
                if (i > 0)
                    sb.append(splitString_);
                str = rs.getString(i + 1);
                if (str != null) {
                    if (isTrim_)
                        str = str.trim();
                }
                else
                    str = "";
                if (isEnclose_) {
                    str = StringUtilities.replace(str, encloseString_, replaceString_);
                    str = encloseString_ + str + encloseString_;
                }
                sb.append(str);
            }
        }
//        LogUtil.TRACE(true, CLASS_NAME, METHOD_NAME, "END");
        return sb.toString();
    }

    public String transform(ResultSet rs, String key) throws SQLException {

//        final String METHOD_NAME = "transform";
//        LogUtil.TRACE(true, CLASS_NAME, METHOD_NAME, "START");

        int i = 0;
        String str = null;
        StringBuffer sb = new StringBuffer();

        ResultSetMetaData meta = rs.getMetaData();

        while (rs.next()) {
            sb.append("");
            for (i = 0; i < meta.getColumnCount(); i++) {
                if (i > 0)
                    sb.append(splitString_);
                str = rs.getString(i + 1);

                if (str != null) {
                    if (isTrim_)
                        str = str.trim();
                }else{
                    str = "";
                }
                if (isEnclose_) {
                    str = StringUtilities.replace(str, encloseString_, replaceString_);
                    str = encloseString_ + str + encloseString_;
                }
                sb.append(str);
            }
        }
//        LogUtil.TRACE(true, CLASS_NAME, METHOD_NAME, "END");

        return sb.toString();
    }

    public String transform(GenericTable tbl) {
        StringBuffer sb = new StringBuffer();
        Object obj = null;
        String str = null;
        int rows = tbl.getRows();
        int columns = tbl.getColumns();
        int row = 0, column = 0;

        for (row = 0; row < rows; row++) {
            if (row > 0)
                sb.append(newLineString_);
            for (column = 0; column < columns; column++) {
                if (column > 0)
                    sb.append(splitString_);
                obj = tbl.get(row, column);
                if (obj != null) {
                    try {
                        str = obj.toString();
                    }
                    catch (Exception ex) {
                        str = "";
                    }
                    if (isTrim_)
                        str = str.trim();
                }
                else
                    str = "";
                if (isEnclose_) {
                    str = StringUtilities.replace(str, encloseString_, replaceString_);
                    str = encloseString_ + str + encloseString_;
                }
                sb.append(str);
            }
        }

        return sb.toString();
    }

}
