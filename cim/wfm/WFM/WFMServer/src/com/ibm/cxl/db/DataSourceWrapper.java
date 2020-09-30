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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

/**
 * Wraps the data source object.
 * This class looks up the data source object from the naming context,
 * and attempts to establish a database connection.
 *
 * @author IBM Developer
 * @version $Revision: 2771 $ $Date: 2015-01-04 18:15:27 +0800 (周日, 04 一月 2015) $
 */
public class DataSourceWrapper {

    /** a name of the data source */
    private String name_;

    /** a DataSource object */
    private DataSource ds_ = null;

    /** an object to synchronize. */
    private Object sync_ = new Object();

    /**
     * Constructs a new, empty object.
     *
     * @param name a string object contains a name of the data source.
     */
    public DataSourceWrapper(String name) {
        name_ = name;
        ds_ = null;
    }

    /**
     * Returns a database connection.
     *
     * @return a connection object.
     * @exception SQLException if a database-access error occurs.
     * @exception NamingException if a naming exception is encountered.
     */
    public Connection getConnection() throws SQLException, NamingException {
        return getConnection(null, null);
    }

    /**
     * Returns a database connection.
     * Using a user id and a password, attempt to establish a database connection.
     *
     * @param userId a string object contains an user id.
     * @param password a string object contains a password.
     * @return a connection object.
     * @exception SQLException if a database-access error occurs.
     * @exception NamingException if a naming exception is encountered.
     */
    public Connection getConnection(String userId, String password) throws SQLException, NamingException {
        synchronized (sync_) {

            if (ds_ == null) {
                Context ctx = null;
                try {
                    ctx = new InitialContext();
                   Context envContext  = (Context)ctx.lookup("java:/comp/env");
                    ds_ = (DataSource)PortableRemoteObject.narrow(envContext.lookup(name_), DataSource.class);
                }
                catch (NamingException ex) {
                    throw ex;
                }
                finally {
                    if (ctx != null) {
                        try {
                            ctx.close();
                        }
                        catch (Exception ex) {}
                    }
                }
            }
        }

        // attempt to establish a database connection.
        if (userId != null && password != null)
            return ds_.getConnection(userId, password);
        else
            return ds_.getConnection();
    }

} // the end of DataSourceWrapper