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
package com.ibm.cxl.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import javax.jms.JMSException;

/**
 * This class provides utilities to log into a file.
 *
 * @author IBM Developer
 * @version $Revision: 2771 $ $Date: 2015-01-04 18:15:27 +0800 (周日, 04 一月 2015) $
 */
public class FileLogger {

    /** File object expresses a log file. */
    private File file_ = null;

    /**
     * Constructs an object specifying a path name.
     *
     * @param pathName a string object contains a path name.
     */
    public FileLogger(String pathName) {
        this(null, pathName);
    }

    /**
     * Constructs an object specifying a file name and a path.
     *
     * @param path a string object contains a path
     * @param name a string object contains a name
     */
    public FileLogger(String path, String name) {
        file_ = new File(path, name).getAbsoluteFile();
    }

    /**
     * Returns length of file in bytes.
     *
     * @return length of file if file exists; otherwise, 0.
     */
    public final long length() {
        synchronized(file_) {
            return file_.length();
        }
    }

    /**
     * Renames a file using new name.
     * If new file already exists, it will be overwritten.
     *
     * @param newName a string object contains new name.
     * @return true if succeed; otherwise, false.
     */
    public final boolean rename(String newName) {
        synchronized(file_) {
            return rename(file_.getParent(), newName);
        }
    }

    /**
     * Renames a file using new name and path.
     * If new file already exists, it will be overwritten.
     *
     * @param newPath a string object contains new path.
     * @param newName a string object contains new name.
     * @return true if succeed; otherwise, false.
     */
    public final boolean rename(String newPath, String newName) {
        synchronized(file_) {
            File newFile = new File(newPath, newName);
            if (newFile.exists() && !newFile.delete())
                return false;
            return file_.renameTo(newFile);
        }
    }

    /**
     * Deletes a file.
     *
     * @return true if succeed; otherwise, false.
     */
    public final boolean delete() {
        synchronized(file_) {
            return file_.delete();
        }
    }

    /**
     * Logs a message and an error.
     *
     * @param msg a string object contain a message.
     * @param t a Throwable object represents an error.
     */
    public void log(String msg, Throwable t) throws IOException {
        if (msg == null && t == null)
            return;
        synchronized(file_) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new FileWriter(file_.getAbsolutePath(), true), true);
                if (msg != null)
                    writer.println(msg);
                if (t != null) {
                    t.printStackTrace(writer);
//                    if (t instanceof JMSException) {
//                        Exception e = ((JMSException)t).getLinkedException();
//                        if (e != null)
//                            e.printStackTrace(writer);
//                    }
                }
            } catch (IOException ex) {
                throw ex;
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (Exception ex) {}
                }
            }
        }
    }

} // the end of FileLogger