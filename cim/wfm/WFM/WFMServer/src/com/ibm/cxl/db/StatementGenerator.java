
package com.ibm.cxl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;


public class StatementGenerator {

    public final static String NAME_PREFIX = "{"; // the prefix of the name of the placefolder
    public final static String NAME_SUFFIX = "}"; // the suffix of the name of the placefolder


    public static PreparedStatement generate(Connection conn, String sql) throws SQLException {
        return generate(conn, sql, null, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public static PreparedStatement generate(Connection conn, String sql, Map map) throws SQLException {
        return generate(conn, sql, map, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public final static PreparedStatement generate(Connection conn, String sql, Map map, int resultSetType, int resultSetConcurrency) throws SQLException {
        String str = null;
        ArrayList list = new ArrayList();

        if (conn == null || sql == null)
            return null;

        // analyse the placefolders enclosed by the suffix and the prefix in the specified SQL statement.
        int iStart = 0, iPrefix = 0, iSuffix = 0;
        StringBuffer sb = new StringBuffer();

        while (true) {
            if ((iPrefix = sql.indexOf(NAME_PREFIX, iStart)) == -1)
                break;
            if ((iSuffix = sql.indexOf(NAME_SUFFIX, iPrefix + 1)) == -1)
                break;

            str = sql.substring(iPrefix + 1, iSuffix);
            list.add(str);

            sb.append(sql.substring(iStart, iPrefix));
            sb.append('?');

            iStart = iSuffix + 1;
        }
        if (iStart < sql.length())
            sb.append(sql.substring(iStart));
        sql = sb.toString();

        // Create prepared statement.
        PreparedStatement stmt = null;
        Object obj = null;
        try {
            stmt = conn.prepareStatement(sql, resultSetType, resultSetConcurrency);

            // map parameters to the statement.
            for (int i = 0, j = list.size(); i < j; i++) {
                if (map != null) {
                    str = (String)list.get(i);
                    try {
                        obj = map.get(str);
                    }
                    catch (Exception ex) {
                        obj = null;
                    }
                }
                if (obj != null && obj instanceof Object[]) {
                    if (((Object[])obj).length > 0)
                        obj = ((Object[])obj)[0];
                    else
                        obj = null;
                }
                if (obj != null)
                    stmt.setObject(i + 1, obj);
                else
                    stmt.setNull(i + 1, Types.CHAR);
            }
        }
        catch (SQLException ex) {
            // if an exception occurs, release created statement and throws the exception.
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException ex1) {}
            }
            throw ex;
        }
        return stmt;
    }

} // the end of StatementGenerator