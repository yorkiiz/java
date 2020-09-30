/*
 * $Header:
 * $Revision: 1.0
 * $Date: 2003/01/01
 *
 * ====================================================================
 *
 * (C) Copyright IBM Corp. 2003  All rights reserved.
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
package com.ibm.lcd.commons.taglib;

import javax.servlet.jsp.JspException;
import org.apache.struts.taglib.bean.MessageTag;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

/**
 * This tag writes page's title.
 * 
 * @author user
 */
public class LcdTitleTag extends MessageTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String style = "PAGE-TITLE";

	/**
	 * Process the start tag.
	 *
	 * @exception JspException if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {

		String key = this.key;
		if (key == null) {
			// Look up the requested property value
			Object value = RequestUtils.lookup(pageContext, name, property, scope);
			if (value != null && !(value instanceof String)) {
				JspException e = new JspException(messages.getMessage("message.property", key));
				RequestUtils.saveException(pageContext, e);
				throw e;
			}
			key = (String)value;
		}

		// Construct the optional arguments array we will be using
		Object args[] = new Object[5];
		args[0] = arg0;
		args[1] = arg1;
		args[2] = arg2;
		args[3] = arg3;
		args[4] = arg4;

		// Retrieve the message string we are looking for
		String message = null;
		if ((message = RequestUtils.message(pageContext, this.bundle, this.localeKey, key, args)) == null) {
			JspException e = new JspException(messages.getMessage("message.message", key));
			RequestUtils.saveException(pageContext, e);
			throw e;
		}

		String s = "<P class=\"" + style + "\"> " + message + "</P>\n";

		// Print the retrieved message to our output writer
		ResponseUtils.write(pageContext, s);

		// Continue processing this page
		return SKIP_BODY;
	}

	/**
	 * Gets the style
	 * @return Returns a String
	 */
	public String getStyle() {
		return style;
	}
	/**
	 * Sets the style
	 * @param style The style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

}
