
package com.ibm.lcd.cfm.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.StringTokenizer;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ibm.cxl.db.DataSourcePool;
import com.ibm.cxl.db.StatementGenerator;
import com.ibm.lcd.cfm.util.CSVTransformer;
import com.ibm.lcd.cfm.util.SumTable;

public class SummaryBrokerAction extends CFMAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// Get parameter map.
		Map map = getParametersMap(request);

		String path = mapping.getPath();
		String ds = null, sql = null;
		String s = null;

		// Get requested path name.
		if (path != null && path.startsWith("/"))
			path = path.substring(1);

		// Get an appropriate datasource name (key is "cfm.<pathname>.datasource").
		s = config.getProperty("cfm." + path + ".datasource");
		if (s != null)
			ds = config.getProperty(s);

		// Get an appropriate query (key is "cfm.<pathname>.query").
		sql = config.getProperty("cfm." + path + ".query");
		if (ds == null || sql == null) {
			s = config.getProperty("cfm." + path + ".datasource");
			if (s != null)
				ds = config.getProperty(s);	
		}

		// Get column names for each axis.
		String columnAxisX = config.getProperty("cfm." + path + ".column.axis.x");
		String columnAxisY = config.getProperty("cfm." + path + ".column.axis.y");

		// Get and parse value column names.
		s = config.getProperty("cfm." + path + ".column.value", "");
		StringTokenizer token = new StringTokenizer(s, ",");
		int i = token.countTokens();
		String[] columnValues = new String[i];
		i = 0;
		while (token.hasMoreTokens()) {
			columnValues[i] = token.nextToken();
			i++;
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		SumTable sumTable = new SumTable();
		String axisX = null, axisY = null;
		
		try {
			// Process axis.
			processAxis(path, map, 0, sumTable);
			processAxis(path, map, 1, sumTable);

			// Connect to the database.
			conn = DataSourcePool.getInstance().getConnection(ds);
			if(conn == null)
				conn = DataSourcePool.getInstance().getConnection(ds);
				
			// Execute query.
			stmt = StatementGenerator.generate(conn, sql, map);
			rs = stmt.executeQuery();

			while (rs.next()) {
				axisY = rs.getString(columnAxisY);
				if (columnAxisX != null) {
					axisX = rs.getString(columnAxisX);
					sumTable.sumup(axisX, axisY, rs.getObject(columnValues[0]));
				} else {
					for (i = 0; i < columnValues.length; i++) {
						axisX = config.getProperty("cfm." + path + ".label." + columnValues[i]);
						if (axisX == null || axisX.length() <= 0)
							axisX = columnValues[i];
						sumTable.sumup(axisX, axisY, rs.getObject(columnValues[i]));
					}
				}
			}

			// Respond with CSV.
			CSVTransformer transformer = new CSVTransformer();
			s = transformer.transform(sumTable);
			respondWithClass(response, s.getBytes());

		} catch (Exception ex) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

		} finally {
			// Release used resources.
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception ex) {}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {}
			}
		}
		// Always return null.
		return null;
	}

	private void processAxis(String path, Map paramterMap, int axis, SumTable sumTable)
		throws NamingException, SQLException {

		String sql = null, ds = null;
		if (axis == 0) {
			sql = config.getProperty("cfm." + path + ".axis.x.query");
			ds = config.getProperty("cfm." + path + ".axis.x.datasource");
		} else {
			sql = config.getProperty("cfm." + path + ".axis.y.query");
			ds = config.getProperty("cfm." + path + ".axis.y.datasource");
		}
		if (sql == null)  // Return if the query is not specified.
			return;

		if (ds == null)  // Use path's datasource if the axis's datasource is not found.
			ds = config.getProperty("cfm." + path + ".datasource");
		if (ds != null)
			ds = config.getProperty(ds);


		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DataSourcePool.getInstance().getConnection(ds);
			stmt = StatementGenerator.generate(conn, sql, paramterMap);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (axis == 0){				
					sumTable.addAxisX(rs.getString(1));
				}else{
					sumTable.addAxisY(rs.getString(1));
				}
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception ex) {}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {}
			}
		}
	}

}
