<%@ page isErrorPage="true" %>
<%@ page import="org.apache.struts.action.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<tiles:insert definition="lcdLayout">
<tiles:put name="body" direct="true">

<lcd:title key="com.title.exception"/>
<HR>
<P><bean:message key="com.error.apology"/></P>

<% if (exception != null) { %>
  <P><PRE>
  <% exception.printStackTrace(new java.io.PrintWriter(out)); %>
  </PRE>
<% } %>
<% Exception reqEx = (Exception)request.getAttribute(Action.EXCEPTION_KEY); %>
<% if (reqEx != null) { %>
  <P><PRE>
  <% reqEx.printStackTrace(new java.io.PrintWriter(out)); %>
  </PRE>
<% } %>
</tiles:put>
</tiles:insert>