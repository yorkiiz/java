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

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.bean.MessageTag;
import org.apache.struts.util.RequestUtils;

/**
 * This tag writes the bread crumbing.
 * 
 * @author user
 */
public class LcdBreadCrumbingTag extends MessageTag {


	// ------------------------------------------------------------- Properties

	private static final long serialVersionUID = 1L;
	/**
	 * The id which specific crumbing list in the session.
	 */
	private String crumbingId = null;
	
	public void setId(String value) {
		crumbingId = value;
	}
	public String getId() {
		return crumbingId;
	}

	/**
	 * The action indicates crumbing action.
	 * "init" "update"
	 */
	private String action = null;
	
	public void setAction(String value) {
		action = value;
	}
	public String getAction() {
		return action;
	}

	/**
	 * The URI leads a page. (if any)
	 */
	private String href = null;
	
	public void setHref(String value) {
		href = value;
	}
	public String getHref() {
		return href;
	}

	/**
	 * The pattern indicates the uri matching.
	 * "full"
	 */
	private String match = null;

	public void setMatch(String value) {
		match = value;
	}
	public String getMatch() {
		return match;
	}

	/**
	 * The token over parameters.
	 */
	private String params = null;
	
	public void setParams(String value) {
		params = value;
	}
	public String getParams() {
		return params;
	}

	/**
	 * The module-relative page URL.
	 */
	private String page = null;

	public void setPage(String value) {
		page = value;
	}
	public String getPage() {
		return page;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Process the start tag.
	 *
	 * @exception JspException if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {
		if (crumbingId == null)
			return SKIP_BODY;

		HttpSession session = ((HttpServletRequest)pageContext.getRequest()).getSession(false);
		if (session == null)
			return SKIP_BODY;

		boolean isInit = "init".equalsIgnoreCase(action);
		boolean isUpdate = "update".equalsIgnoreCase(action);
		boolean isFullMatch = "full".equalsIgnoreCase(match);
		String s = null;

		String crumbingURL = href;
		if (page == null && href == null)
			crumbingURL = getCrumbingURL();
		crumbingURL = calculateURL(crumbingURL);

		List list = (List)session.getAttribute(crumbingId);
		if (list == null)
			list = new ArrayList();

		if (isInit) {
			list.clear();
		} else if (isUpdate) {
			int i, j, sep;
			String cmpUrl = null;
			String url = null;
			sep = (isFullMatch) ? -1 : crumbingURL.indexOf('?');
			cmpUrl = (sep >= 0) ? crumbingURL.substring(0, sep) : crumbingURL;
			for (i = 0, j = list.size(); i < j; i++) {
				s = (String)list.get(i);
				sep = s.indexOf(';');
				url = (sep >= 0) ? s.substring(0, sep) : s;
				if (!isFullMatch) {
					sep = url.indexOf('?');
					url = (sep >= 0) ? url.substring(0, sep) : url;
				}
				if (cmpUrl.equals(url))
					break;
			}
			if (i < j)
				list = list.subList(0, i);
		}

		if (list.size() > 0)
			writeCrumbing(list);

		if (isInit || isUpdate) {
			s = getCrumbingLabel();
			list.add(crumbingURL + ";" + s);
			session.setAttribute(crumbingId, list);
		}
		return SKIP_BODY;
	}

	// ------------------------------------------------------ Protected Methods

	/**
	 * Returns the crumbing URL.
	 * @return the crumbing URL.
	 */
	protected String getCrumbingURL() {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		String s = null;
		StringBuffer sb = request.getRequestURL();
		if (sb != null)
			s = sb.toString();
		else
			s = request.getRequestURI();
		String query = request.getQueryString();
		if (query != null && query.length() > 0)
			s = s + "?" + query;
		return s;
	}

	/**
	 * Returns the complete URL to which this hyperlink will direct the user.
	 * 
	 * @exception JspException if a JSP exception has occurred
	 */
	protected String calculateURL(String url) throws JspException {
		Map map = new HashMap();
		String s = null;
		if (params != null) {
			ServletRequest request = pageContext.getRequest();
			StringTokenizer st = new StringTokenizer(params, ",");
			String key = null;
			while (st.hasMoreTokens()) {
				key = st.nextToken().trim();
				if ((s = request.getParameter(key)) != null)
					map.put(key, s);
			}
		}
		try {
			s = RequestUtils.computeURL(pageContext, null, url, page, map, null, false);
		} catch (MalformedURLException ex) {
			RequestUtils.saveException(pageContext, ex);
			throw new JspException(messages.getMessage("rewrite.url", ex.toString()));
		}
		return s;
	}

	/**
	 * Returns the crumbing label.
	 * @return the crumbing label.
	 * @exception JspException if a JSP exception has occurred
	 */
	protected String getCrumbingLabel() throws JspException {
		String key = this.key;
		if (key == null) {
			// Look up the requested property value
			Object value = RequestUtils.lookup(pageContext, name, property, scope);
			if (value != null && !(value instanceof String)) {
				JspException ex = new JspException(messages.getMessage("message.property", key));
				RequestUtils.saveException(pageContext, ex);
				throw ex;
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
			JspException ex = new JspException(messages.getMessage("message.message", key));
			RequestUtils.saveException(pageContext, ex);
			throw ex;
		}
		return message;
	}

	/**
	 * Writes the bread crumbing.
	 * @param list A list contains crumbing items.
	 * @exception JspException if a JSP exception has occurred
	 */
	protected void writeCrumbing(List list) throws JspException {
		StringBuffer sb = new StringBuffer();
		String s = null, uri = null, label = null;
		int sep = 0;
		for (int i = 0, j = list.size(); i < j; i++) {
			s = (String)list.get(i);
			sep = s.indexOf(';');
			if (sep < 0)
				continue;

			uri = s.substring(0, sep);
			label = s.substring(sep + 1);

			sb.append("<SPAN><A href=\"");
			sb.append(uri);
			sb.append("\">");
			sb.append(label);
			sb.append("</A> &gt; </SPAN>");
		}

		try {
			pageContext.getOut().println(sb.toString());
		} catch (IOException ex) {
			throw new JspException(ex);
		}
	}

}
