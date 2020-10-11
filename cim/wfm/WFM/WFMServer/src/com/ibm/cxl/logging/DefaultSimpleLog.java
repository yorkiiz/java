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

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;

import com.ibm.cxl.util.PropertiesWrapper;

/**
 * This class logs messages with default format.
 *
 * @author IBM Developer
 * @version $Revision: 2771 $ $Date: 2015-01-04 18:15:27 +0800 (周日, 04 一月 2015) $
 */
public class DefaultSimpleLog {

    /** All system properties used by this class start with this */
    protected final static String SYSTEM_PREFIX = "com.ibm.cxl.logging.";

    /** The property key of path. */
    public final static String PROPERTY_PATH = SYSTEM_PREFIX + "path";

    /** The property key of prefix. */
    public final static String PROPERTY_PREFIX = SYSTEM_PREFIX + "prefix";

    /** The property key of suffix. */
    public final static String PROPERTY_SUFFIX = SYSTEM_PREFIX + "suffix";

    /** The property key of level. */
    public final static String PROPERTY_LEVEL = SYSTEM_PREFIX + "level";

    /** The property key of maximum length. */
    public final static String PROPERTY_MAXLENGTH = SYSTEM_PREFIX + "maxLength";

    /** The property key of overwrite (true/false). */
    public final static String PROPERTY_OVERWRITE = SYSTEM_PREFIX + "overwrite";

    /** The property key of archive path. */
    public final static String PROPERTY_ARCHIVEPATH = SYSTEM_PREFIX + "archivePath";

    /** "Trace" level logging. */
    public static final int LEVEL_TRACE = 1;
    /** "Debug" level logging. */
    public static final int LEVEL_DEBUG = 2;
    /** "Info" level logging. */
    public static final int LEVEL_INFO  = 3;
    /** "Warn" level logging. */
    public static final int LEVEL_WARN  = 4;
    /** "Error" level logging. */
    public static final int LEVEL_ERROR = 5;
    /** "Fatal" level logging. */
    public static final int LEVEL_FATAL = 6;

    /** Enable all logging levels */
    public static final int LEVEL_ALL = (LEVEL_TRACE - 1);
    /** Enable no logging levels */
    public static final int LEVEL_OFF = (LEVEL_FATAL + 1);

    /** a format of the date */
    private final static SimpleDateFormat sdf__ = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS z");

    /** private variables. */
    private FileLogger logger_ = null;
    private String path_ = null;
    private String prefix_ = null;
    private String suffix_ = null;
    private int currentLevel_ = LEVEL_ALL;
    private long maxLength_ = 0;
    private boolean overwrite_ = true;
    private String archivePath_ = null;

    /**
     * Creates and returns an instance which is configured with the specified properties.
     *
     * @param m a Map object contains properties.
     * @return a configured instance.
     */
    public static DefaultSimpleLog getInstance(Map m) {
        PropertiesWrapper p = new PropertiesWrapper(m);
        DefaultSimpleLog instance = new DefaultSimpleLog(
            p.getProperty(PROPERTY_PATH),
            p.getProperty(PROPERTY_PREFIX, "default"),
            p.getProperty(PROPERTY_SUFFIX, ".log")
        );
        instance.setLevel(p.getInteger(PROPERTY_LEVEL, LEVEL_ALL));
        instance.setMaxLength(p.getInteger(PROPERTY_MAXLENGTH, 0));
        instance.setOverwrite(p.getBoolean(PROPERTY_OVERWRITE, true));
        instance.setArchivePath(p.getProperty(PROPERTY_ARCHIVEPATH));
        return instance;
    }

    /**
     * Constructs a new object.
     *
     * @param path the pathname to store a log file.
     * @param suffix the prefix of a log file.
     * @param prefix the suffix of a log file.
     */
    public DefaultSimpleLog(String path, String prefix, String suffix) {
        path_ = path;
        if (prefix != null && prefix.length() > 0)
            prefix_ = prefix;
        else
            prefix_ = "default";
        if (suffix != null && suffix.length() > 0)
            suffix_ = suffix;
        else
            suffix_ = "";
        logger_ = new FileLogger(path_, prefix_ + suffix_);
    }

    /**
     * Set logging level.
     *
     * @param level new logging level.
     */
    public void setLevel(int level) {
        currentLevel_ = level;
    }

    /**
     * Set maximum length of log file.
     * The maximum length below zero means that there is no limitation.
     *
     * @param maxLength maximum length in bytes.
     */
    public void setMaxLength(int maxLength) {
        maxLength_ = maxLength;
    }

    /**
     * Sets whether a log file is overwritten or not.
     *
     * @param flag true if the overwrite is effective; false, otherwise.
     */
    public void setOverwrite(boolean flag) {
        overwrite_ = flag;
    }

    /**
     * Set archive path of log file.
     *
     * @param path the path to the target directory.
     */
    public void setArchivePath(String path) {
        archivePath_ = path;
    }

    /**
     * Tests if the specified level is enabled.
     *
     * @param level logging level to be tested.
     * @return true if the level is enabled; otherwise, false.
     */
    protected boolean isLevelEnabled(int level) {
        return (level >= currentLevel_);
    }

    /**
     * Do the actual logging.
     * This method assembles the message.
     *
     * @param level
     * @param msg
     * @param t
     */
    protected void log(int level, Object msg, Throwable t) {
        if (!isLevelEnabled(level))
            return;

        StringBuffer sb = new StringBuffer();
        boolean logFlag = true;

        // append a timestamp.
        sb.append('[');
        sb.append(sdf__.format(new Date()));
        sb.append("] ");

        // append a readable representation of the log level.
        switch(level) {
        case LEVEL_TRACE: sb.append("<T> "); break;
        case LEVEL_DEBUG: sb.append("<D> "); break;
        case LEVEL_INFO:  sb.append("<I> "); break;
        case LEVEL_WARN:  sb.append("<W> "); break;
        case LEVEL_ERROR: sb.append("<E> "); break;
        case LEVEL_FATAL: sb.append("<F> "); break;
        }

        // append a message.
        sb.append(String.valueOf(msg));

        synchronized(logger_) {
            // backup old log file.
            if (maxLength_ > 0 && logger_.length() > maxLength_) {
                if (overwrite_)
                    logFlag = logger_.delete();
                else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String str = sdf.format(new Date());
                    if (archivePath_ != null)
                        logFlag = logger_.rename(archivePath_, prefix_ + str + suffix_);
                    else
                        logFlag = logger_.rename(prefix_ + str + suffix_);
                }
            }
            // log a message.
            if (logFlag) {
                try {
                    logger_.log(sb.toString(), t);
                } catch (IOException ex) {}
            }
        }
    }

    // -------------------------------------------------------- Log Implementation

    /**
     * Logs a message with debug log level.
     *
     * @param msg a message to be logged.
     */
    public final void debug(Object msg) {
        log(LEVEL_DEBUG, msg, null);
    }

    /**
     * Logs a message and an error with debug log level.
     *
     * @param msg a message to be logged.
     * @param t an error to be logged.
     */
    public final void debug(Object msg, Throwable t) {
        log(LEVEL_DEBUG, msg, t);
    }

    /**
     * Logs a message with trace log level.
     *
     * @param msg a message to be logged.
     */
    public final void trace(Object msg) {
        log(LEVEL_TRACE, msg, null);
    }

    /**
     * Logs a message and an error with trace log level.
     *
     * @param msg a message to be logged.
     * @param t an error to be logged.
     */
    public final void trace(Object msg, Throwable t) {
        log(LEVEL_TRACE, msg, t);
    }

    /**
     * Logs a message with information log level.
     *
     * @param msg a message to be logged.
     */
    public final void info(Object msg) {
        log(LEVEL_INFO, msg, null);
    }

    /**
     * Logs a message and an error with information log level.
     *
     * @param msg a message to be logged.
     * @param t an error to be logged.
     */
    public final void info(Object msg, Throwable t) {
        log(LEVEL_INFO, msg, t);
    }

    /**
     * Logs a message with warning log level.
     *
     * @param msg a message to be logged.
     */
    public final void warn(Object msg) {
        log(LEVEL_WARN, msg, null);
    }

    /**
     * Logs a message and an error with warning log level.
     *
     * @param msg a message to be logged.
     * @param t an error to be logged.
     */
    public final void warn(Object msg, Throwable t) {
        log(LEVEL_WARN, msg, t);
    }

    /**
     * Logs a message with error log level.
     *
     * @param msg a message to be logged.
     */
    public final void error(Object msg) {
        log(LEVEL_ERROR, msg, null);
    }

    /**
     * Logs a message and an error with error log level.
     *
     * @param msg a message to be logged.
     * @param t an error to be logged.
     */
    public final void error(Object msg, Throwable t) {
        log(LEVEL_ERROR, msg, t);
    }

    /**
     * Logs a message with fatal log level.
     *
     * @param msg a message to be logged.
     */
    public final void fatal(Object msg) {
        log(LEVEL_FATAL, msg, null);
    }

    /**
     * Logs a message and an error with fatal log level.
     *
     * @param msg a message to be logged.
     * @param t an error to be logged.
     */
    public final void fatal(Object msg, Throwable t) {
        log(LEVEL_FATAL, msg, t);
    }

    /**
     * Tests if debug log level is enabled.
     *
     * @return true if the level is enabled; otherwise, false.
     */
    public final boolean isDebugEnabled() {
        return isLevelEnabled(LEVEL_DEBUG);
    }

    /**
     * Tests if trace log level is enabled.
     *
     * @return true if the level is enabled; otherwise, false.
     */
    public final boolean isTraceEnabled() {
        return isLevelEnabled(LEVEL_TRACE);
    }

    /**
     * Tests if information log level is enabled.
     *
     * @return true if the level is enabled; otherwise, false.
     */
    public final boolean isInfoEnabled() {
        return isLevelEnabled(LEVEL_INFO);
    }

    /**
     * Tests if warning log level is enabled.
     *
     * @return true if the level is enabled; otherwise, false.
     */
    public final boolean isWarnEnabled() {
        return isLevelEnabled(LEVEL_WARN);
    }

    /**
     * Tests if error log level is enabled.
     *
     * @return true if the level is enabled; otherwise, false.
     */
    public final boolean isErrorEnabled() {
        return isLevelEnabled(LEVEL_ERROR);
    }

    /**
     * Tests if fatal log level is enabled.
     *
     * @return true if the level is enabled; otherwise, false.
     */
    public final boolean isFatalEnabled() {
        return isLevelEnabled(LEVEL_FATAL);
    }

} // the end of DefaultSimpleLog