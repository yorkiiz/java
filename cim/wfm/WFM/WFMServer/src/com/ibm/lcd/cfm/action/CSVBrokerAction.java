package com.ibm.lcd.cfm.action;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ibm.cxl.db.DataSourcePool;
import com.ibm.cxl.db.StatementGenerator;
import com.ibm.lcd.cfm.util.CSVTransformer;

public class CSVBrokerAction extends CFMAction {

	private static String KEY_SERVER_CACHE_REFRESH_TIME = "ServerCacheRefreshTime";
	static byte[] m_data = null;
	static RealEqptStatusRunner m_eqptStatusRunner = null;

	private Map map = null;
	private String ds = null;
	private String sql = null;
	private String key = null;

	private int m_isNoBodyConsumedCount = 0;

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if (m_isNoBodyConsumedCount > 0) {
			m_isNoBodyConsumedCount--;
		}

		if (m_eqptStatusRunner == null) {
			m_eqptStatusRunner = new RealEqptStatusRunner(true);
			m_eqptStatusRunner.start();
		}

		if (!m_eqptStatusRunner.isRunning()) {
			m_eqptStatusRunner.setRunning(true);
			m_isNoBodyConsumedCount = 0;
			m_eqptStatusRunner.interrupt();
		}

		// Get parameter map.
		try {
			map = getParametersMap(request);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String path = mapping.getPath();

		// Get requested path name.
		if (path != null && path.startsWith("/")) {
			path = path.substring(1);
		}
		// Get an appropriate datasource name (key is
		// "cfm.<pathname>.datasource").
		key = "cfm." + path + ".datasource";
		key = config.getProperty(key);
		if (key != null)
			ds = config.getProperty(key);
		// Get an appropriate query (key is "cfm.<pathname>.query").
		key = "cfm." + path + ".query";
		
		sql = config.getProperty(key);
		
		//System.out.println("key: "+sql);

		//Terry
//		byte[] buffer = new byte[102400];
//		try {
//			FileInputStream fr = new FileInputStream("E:/MESExpress WFM/vstatlst.del");
//			int totalByte = fr.read(buffer);
//			m_data = buffer;
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		
		
		
//		int i = 0;
//		try {
//			while (m_data == null && i < 3) {
//				Thread.sleep(3 * m_eqptStatusRunner.getRefreshTime());
//				i++;
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		// Respond with CSV.
		try {
			respondWithClass(response, m_data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void destroy() {
		if (!m_eqptStatusRunner.isRunning()) {
			m_eqptStatusRunner.interrupt();
		}
		m_eqptStatusRunner.shutDown();
		m_eqptStatusRunner = null;
	}

	class RealEqptStatusRunner extends Thread {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CSVTransformer transformer = null;

		private int refreshTime = 1000;

		private boolean m_runFlg = false;

		private boolean m_keepingRunning;

		public RealEqptStatusRunner(boolean b) {
			m_runFlg = b;
			transformer = new CSVTransformer();

			String rt = config.getProperty(KEY_SERVER_CACHE_REFRESH_TIME);
			try {
				refreshTime = Integer.parseInt(rt);
				refreshTime = refreshTime * 1000; // to miniseconds
			} catch (NumberFormatException ex) {
				refreshTime = 1000;
			}
			m_keepingRunning = true;
		}

		public int getRefreshTime() {
			return refreshTime;
		}

		public void setRunning(boolean b) {
			m_runFlg = b;
		}

		public boolean isRunning() {
			return m_runFlg;
		}

		public void shutDown() {
			m_keepingRunning = false;
		}

		public void run() {
			while (m_keepingRunning) {
				if (!m_runFlg) {
					try {
						Thread.sleep(refreshTime);
					} catch (InterruptedException e) {
					}
					continue;
				}

				try {
					// Connect to the database.
					conn = DataSourcePool.getInstance().getConnection(ds);

					if (conn == null)
						conn = DataSourcePool.getInstance().getConnection(ds);

					stmt = StatementGenerator.generate(conn, sql, map);
					rs = stmt.executeQuery();

					String str = transformer.transform(rs);
					
//					System.out.print(str);
					
					m_data = str.getBytes();
					m_isNoBodyConsumedCount++;

					if (m_isNoBodyConsumedCount > 10) {
						System.out.println("stop running...");
						m_runFlg = false;
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						if (rs != null) {
							rs.close();
						}
						if (stmt != null) {
							stmt.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				try {
					Thread.sleep(refreshTime);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
