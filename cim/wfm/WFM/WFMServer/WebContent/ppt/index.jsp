<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>

<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">

<lcd:title key="ppt.title.index"/>
<HR>
<html:errors/>
<html:form action="ppt_login">
<TABLE class="FORM">
<TR>
  <TH class="LABEL"><bean:message key="com.label.userId"/></TH>
  <TD><html:text property="userId" /></TD>
</TR>
<TR>
  <TH class="LABEL"><bean:message key="com.label.password"/></TH>
  <TD><html:password property="password" /></TD>
</TR>
<TR>
  <TD><html:submit><bean:message key="com.button.login"/></html:submit></TD>
</TR>
</TABLE>
</html:form>

</tiles:put>
</tiles:insert>