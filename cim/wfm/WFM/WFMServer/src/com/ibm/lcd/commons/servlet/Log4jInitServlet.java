
package com.ibm.lcd.commons.servlet;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import com.ibm.cxl.util.ResourceDetector;

public class Log4jInitServlet extends HttpServlet {

	private static final long serialVersionUID = -618753899929747274L;

	protected final static String FQCN = Log4jInitServlet.class.getName();

	protected final static String XML_CONFIG_KEY = "xmlConfig";

	protected final static String PROPERTY_CONFIG_KEY = "propertyConfig";

	public void init(ServletConfig config)
		throws ServletException {

		super.init(config);

		try {
			String configFile = config.getInitParameter(XML_CONFIG_KEY);
			URL url = null;
			if (configFile != null && configFile.length() > 0) {
				url = ResourceDetector.get(this, configFile);
				if (url != null) {
					DOMConfigurator.configure(url);
					Category.getInstance(FQCN).log(Priority.INFO, "log4j is configured with the XML configuration " + url.toString());
				}
			}

			if (url == null) {
				configFile = config.getInitParameter(PROPERTY_CONFIG_KEY);
				if (configFile != null && configFile.length() > 0) {
					url = ResourceDetector.get(this, configFile);
					if (url != null) {
						PropertyConfigurator.configure(url);
						Category.getInstance(FQCN).log(Priority.INFO, "log4j is configured with the property configuration " + url.toString());
					}
				}
			}

			if (url == null) {
				Category.getInstance(FQCN).log(Priority.INFO, "log4j is configured with the default configuration.");
			}

		} catch (MalformedURLException ex) {
			throw new ServletException(ex);
		}
	}

}
