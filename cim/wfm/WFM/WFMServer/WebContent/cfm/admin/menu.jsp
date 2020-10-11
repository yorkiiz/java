<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.cfm" fabKey="security.fab" systemKey="security.system.cfm" />

<tiles:insert definition="cfmLayout">
<tiles:put name="body" direct="true">

<lcd:title key="cfm.title.index"/>
<HR>
<html:errors/>
<CENTER>
<TABLE border="0" cellspacing="10" cellpadding="5">
<%--フロアモニタ--%>
<TR bgcolor="#6666FF">
  <TD><html:link styleClass="MENU" href="javascript:openWindow('/cfm/monitor.jsp')"><bean:message key="cfm.title.monitor"/></html:link>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
</TR>
<%--
<TR bgcolor="#6666FF">
  <TD><html:link styleClass="MENU" href="javascript:openWindow('/cfm/admin/uploadForm.jsp')"><bean:message key="cfm.title.upload"/></html:link></TD>
</TR>
<TR bgcolor="#6666FF">
  <TD><html:link styleClass="MENU" href="javascript:openWindow('/cfm_layoutDownload.do')"><bean:message key="cfm.title.download"/></html:link></TD>
</TR>
--%>
<%--ログアウト--%>
<TR bgcolor="#6666FF">
  <%--<TD><html:link styleClass="MENU" href="#" onclick="window.close()"><bean:message key="com.title.logout"/></html:link></TD>--%>
  <TD><html:link styleClass="MENU" href="javascript:openWindow('/cfm_closeWindow.do')"><bean:message key="com.title.logout"/></html:link></TD>
</TR>
</TABLE>
</CENTER>

</tiles:put>
</tiles:insert>