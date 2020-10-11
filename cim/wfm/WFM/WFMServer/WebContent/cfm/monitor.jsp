<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<%
    //LogUtil.out("MESExpress ");
    String tracking_url = null, cookie = null;
    StringBuffer sb = new StringBuffer(request.getScheme());
    sb.append("://");
    sb.append(request.getServerName());
    sb.append(":" + request.getServerPort());
    sb.append(request.getContextPath());
    sb.append('/');
    tracking_url = sb.toString();
    if ((cookie = request.getHeader("Cookie")) == null)
        cookie = "";
%>
<html:html>
<HEAD>
<TITLE><bean:message key="cfm.title.index"/></TITLE>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="1">
</HEAD>
<BODY bgcolor="gray" topmargin="1" bottommargin="1" rightmargin="1" leftmargin="1" marginheight="1" marginwidth="1">
<OBJECT id=maximize classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11">
	<param name="Command" value="Maximize">
</OBJECT>
<script>
	if(top.screenLeft > 0) maximize.Click();
</script>
<OBJECT classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
        width="100%" height="100%" align="baseline"
        codebase="http://java.sun.com/products/plugin/autodl/jinstall-1_4_1-windows-i586.cab#Version=1,4,1,mn">
  <PARAM name="java_archive" value="<%= request.getContextPath() %>/cfm/cfm-monitor.jar, <%= request.getContextPath() %>/cfm/liquidlnf.jar">
  <PARAM name="java_code" value="com.ibm.lcd.cfm.monitor.FloorMonitorApplet.class">
  <PARAM name="java_codebase" value="/">
  <PARAM name="java_type" value="application/x-java-applet;jpi-version=1.5.0">
  <PARAM name="scriptable" value="false">
  <PARAM name="mayscript" value="false">
  <PARAM name="monitor_tracking_url" value="<%= tracking_url %>">
  <PARAM name="monitor_layout_url" value="<%= tracking_url + "cfm/layout" %>">
  <PARAM name="monitor_context_path" value="<%= request.getContextPath() %>">
  <PARAM name="monitor_cookie" value="<%= cookie %>">
  <PARAM name="monitor_wait_time" value="0">
  <COMMENT>
    <EMBED type="application/x-java-applet;jpi-version=1.5.0"
           pluginspage="http://java.sun.com/j2se/1.5.0/download.html"
           width="100%" height="100%" align="baseline"
           archive="<%= request.getContextPath() %>/cfm/cfm-monitor.jar, <%= request.getContextPath() %>/cfm/liquidlnf.jar" code="com.ibm.lcd.cfm.monitor.FloorMonitorApplet.class" codebase="./"
           scriptable="false"
           mayscript="false"
           monitor_tracking_url="<%= tracking_url %>"
           monitor_layout_url="<%= tracking_url + "cfm/layout" %>"
           monitor_context_path="<%= request.getContextPath() %>"
           monitor_cookie="<%= cookie %>"
           monitor_wait_time="0">
      <NOEMBED>
        No Java 2 SDK, Standard Edition v 1.5.0 support for APPLET.
      </NOEMBED>
    </EMBED>
  </COMMENT>
</OBJECT>
</BODY>
</html:html>