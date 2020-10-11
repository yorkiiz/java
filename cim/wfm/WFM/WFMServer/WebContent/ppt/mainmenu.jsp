<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html:html>
<HEAD>
<TITLE></TITLE>
<LINK rel="stylesheet" href="<%= request.getContextPath() %>/css/lcd.css" TYPE="text/css">
</HEAD>
<BODY>
<TABLE width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#6666FF">
<TR><TD colspan="2"><html:img page="/image/odot.gif" height="3" width="1" alt=""/></TD></TR>
<TR>
  <TD>&#160;</TD>
  <TD><html:link styleClass="MENU" page="/ppt_eqptList.do" target="content"><bean:message key="ppt.title.eqptList"/></html:link></TD>
</TR>
<TR><TD colspan="2"><html:img page="/image/odot.gif" height="3" width="1" alt=""/></TD></TR>
<TR><TD colspan="2" bgcolor="#ccccff"><html:img page="/image/odot.gif" height="1" width="1" alt=""/></TD></TR>
<TR><TD colspan="2"><html:img page="/image/odot.gif" height="3" width="1" alt=""/></TD></TR>
<TR>
  <TD>&#160;</TD>
  <TD><html:link styleClass="MENU" page="/ppt_stcList.do" target="content"><bean:message key="ppt.title.stcList"/></html:link></TD>
</TR>
<TR><TD colspan="2"><html:img page="/image/odot.gif" height="3" width="1" alt=""/></TD></TR>
<TR><TD colspan="2" bgcolor="#ccccff"><html:img page="/image/odot.gif" height="1" width="1" alt=""/></TD></TR>
<TR><TD colspan="2"><html:img page="/image/odot.gif" height="3" width="1" alt=""/></TD></TR>
<TR>
  <TD>&#160;</TD>
  <TD><html:link styleClass="MENU" page="/ppt_logout.do" target="_top"><bean:message key="com.title.logout"/></html:link></TD>
</TR>
<TR><TD colspan="2"><html:img page="/image/odot.gif" height="3" width="1" alt=""/></TD></TR>
</TABLE>
</BODY>
</html:html>