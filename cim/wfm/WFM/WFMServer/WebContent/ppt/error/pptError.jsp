<%@ page import="com.ibm.cxl.struct.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<%
String err_msg = (String)request.getAttribute("err_msg");
String err_msg_detail = (String)request.getAttribute("err_msg_detail");
%>
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">

<%-- <lcd:breadcrumbing id="com.ibm.lcd.ppt.bc"/> --%>
<lcd:title key="ppt.title.error"/>
<HR>
<B><bean:message key="ppt.msg.error"/></B>
<TABLE border="0">
  <TR>
    <TH class="LABEL"><bean:message key="ppt.label.header.value"/>:</TH>
    <TD><%=err_msg %></TD>
  </TR>
  <logic:present name="err_msg_detail">
    <TR>
      <TH class="LABEL"><bean:message key="ppt.label.header.detail"/>:</TH>
      <TD><%=err_msg_detail %></TD>
    </TR>
  </logic:present>
</TABLE>
</tiles:put>
</tiles:insert>