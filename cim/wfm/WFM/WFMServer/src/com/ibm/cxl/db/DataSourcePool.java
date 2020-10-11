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
 */
package com.ibm.cxl.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.naming.NamingException;

/**
 * This class returns datasources which are looked up from the name space or are
 * kept in the pool.
 * 
 * @author IBM Developer
 * @version $Revision: 2771 $ $Date: 2015-01-04 18:15:27 +0800 (周日, 04 一月 2015) $
 */
public class DataSourcePool {

	/** a singleton object of this class. */
	private final static DataSourcePool singleton__ = new DataSourcePool();

	/** a map of datasources. */
	private HashMap hash_ = new HashMap();

	/**
	 * Constructs an object.
	 * 
	 */
	public DataSourcePool() {
	}

	/**
	 * Returns a singleton of this class.
	 * 
	 * @return a singleton of this class.
	 */
	public final static DataSourcePool getInstance() {
		return singleton__;
	}

	/**
	 * Returns a database connection.
	 * 
	 * @param name
	 *            the name of datasource name in the name space.
	 * @return a database connection.
	 * @exception SQLException
	 *                if a database-access error occurs.
	 * @exception NamingException
	 *                if a naming exception is encountered.
	 */
	public Connection getConnection(String name) throws SQLException,
			NamingException {
		return getConnection(name, null, null);
	}

	/**
	 * Returns a database connection.
	 * 
	 * @param name
	 *            the name of datasource name in the name space.
	 * @param userId
	 *            user identity to connect.
	 * @param password
	 *            password to connect.
	 * @return a database connection.
	 * @exception SQLException
	 *                if a database-access error occurs.
	 * @exception NamingException
	 *                if a naming exception is encountered.
	 */
	public Connection getConnection(String name, String userId, String password)
			throws SQLException, NamingException {
		if (name == null)
			name = "";
		DataSourceWrapper dsw = null;
		synchronized (hash_) {
			dsw = (DataSourceWrapper) hash_.get(name);
			if (dsw == null) {
				dsw = new DataSourceWrapper(name);
				hash_.put(name, dsw);
			}
		}
		return dsw.getConnection(userId, password);
	}

} // the end of DataSourcePool