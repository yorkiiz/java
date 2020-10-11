<%@ page import="com.ibm.cxl.struct.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<%
StructElement struct = (StructElement)request.getAttribute("result");
if (struct != null)
    request.setAttribute("result", StructUtilities.toMap(struct));
%>
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">

<lcd:breadcrumbing id="com.ibm.lcd.ppt.bc"/>
<lcd:title key="ppt.title.error.trx"/>
<HR>
<P><bean:message key="ppt.msg.error.trx"/></P>
<logic:present name="result">
  <TABLE border="0">
  <TR>
    <TH class="LABEL"><bean:message key="ppt.label.trx_id"/></TH>
    <TD><bean:write name="result" property="trx_id"/></TD>
  </TR>
  <TR>
    <TH class="LABEL"><bean:message key="ppt.label.rtn_code"/></TH>
    <TD><bean:write name="result" property="rtn_code"/></TD>
  </TR>
  </TABLE>
</logic:present>

</tiles:put>
</tiles:insert>