
package com.ibm.lcd.commons.plugin;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ApplicationConfig;

import com.ibm.lcd.commons.LcdConfig;

public class LcdPlugin implements PlugIn {

	private String lcdConfig = null;

	public void destroy() {
	}

	public void init(ActionServlet servlet, ApplicationConfig config){

		LcdConfig.init(servlet, lcdConfig);
	}

	public void setLcdConfig(String value) {
		lcdConfig = value;
	}

}
