
package com.ibm.lcd.commons.taglib;

import javax.servlet.jsp.tagext.TagSupport;

import com.ibm.cxl.util.PropertiesWrapper;
import com.ibm.lcd.commons.LcdConfig;

public class LcdCheckTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	/** Configuration. */
	protected static PropertiesWrapper config = LcdConfig.getConfig();

	protected static boolean isEffective = config.getBoolean(LcdConfig.SECURITY_EFFECT_KEY, false);

	// ------------------------------------------------------------- Properties

	private String fabKey = null;

	public String getFabKey() {
		return fabKey;
	}

	public void setFabKey(String fabKey) {
		this.fabKey = fabKey;
	}

	private String systemKey = null;

	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}

	private String functionKey = null;

	public void setFunctionKey(String functionKey) {
		this.functionKey = functionKey;
	}

	public String getFunctionKey() {
		return functionKey;
	}

	private String forwardPage = null;

	public String getForwardPage() {
		return forwardPage;
	}

	public void setForwardPage(String forwardPage) {
		this.forwardPage = forwardPage;
	}

	private String forwardKey = null;

	public String getForwardKey() {
		return forwardKey;
	}

	public void setForwardKey(String forwardKey) {
		this.forwardKey = forwardKey;
	}

	// --------------------------------------------------------- Public Methods

	public int doEndTag(){

		return EVAL_PAGE;
	}

}
