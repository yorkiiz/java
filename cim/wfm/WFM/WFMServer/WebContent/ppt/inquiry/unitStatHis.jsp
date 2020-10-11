<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />

<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">

<%-- <lcd:breadcrumbing id="com.ibm.lcd.ppt.bc" action="update" key="ppt.title.unitStatHis" page="/ppt_unitStatHis.do" params="eqpt_id" /> --%>
<lcd:title key="ppt.title.unitStatHis"/>
<HR>
<%-- INPUT FORM --%>
<html:form action="ppt_unitStatHis">
<TABLE class="FORM">
<TR>
  <TH class="LABEL"><bean:message key="ppt.label.prt_eqpt_id"/></TH>
  <TD><bean:write name="xpinqeqp" property="prt_eqpt_id"/></TD>
</TR>
<TR>
  <TH class="LABEL"><bean:message key="ppt.label.eqpt_id"/></TH>
  <TD><html:hidden property="eqpt_id" write="true"/></TD>
</TR>
<TR>
  <TH class="LABEL"><bean:message key="ppt.label.fr_date"/></TH>
  <TD><html:text property="fr_date"/></TD>
</TR>
<TR>
  <TH class="LABEL"><bean:message key="ppt.label.to_date"/></TH>
  <TD><html:text property="to_date"/></TD>
</TR>
<TR>
  <TD><html:submit><bean:message key="com.button.search"/></html:submit></TD>
</TR>
</TABLE>
</html:form>
<HR>
<logic:notPresent name="subList"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
<logic:present name="subList">
  <TABLE class="LIST" ID="unitStatHis">
  <TR>
    <TH class="LIST" onclick="ST(0,'unitStatHis', false)"><bean:message key="ppt.label.clm_date"/></TH>
    <TH class="LIST" onclick="ST(1,'unitStatHis', false)"><bean:message key="ppt.label.report_date"/></TH>
    <TH class="LIST" onclick="ST(2,'unitStatHis', false)"><bean:message key="ppt.label.eqpt_stat"/></TH>
    <TH class="LIST" onclick="ST(3,'unitStatHis', false)"><bean:message key="ppt.label.eqpt_sub_stat"/></TH>
    <TH class="LIST" onclick="ST(4,'unitStatHis', false)"><bean:message key="ppt.label.eqpt_stat_dsc"/></TH>
    <TH class="LIST" onclick="ST(5,'unitStatHis', false)"><bean:message key="ppt.label.shtng_date"/></TH>
    <TH class="LIST" onclick="ST(6,'unitStatHis', false)"><bean:message key="ppt.label.shtng_user"/></TH>
    <TH class="LIST" onclick="ST(7,'unitStatHis', false)"><bean:message key="ppt.label.reason_code"/></TH>
  </TR>
  <TBODY>
  <logic:iterate id="bean" name="subList">
    <TR>
      <TD class="LISTNOWRAP"><bean:write name="bean" property="clm_date"/>&nbsp;<bean:write name="bean" property="clm_time"/></TD>
      <TD class="LIST"><bean:write name="bean" property="report_date"/>&nbsp;<bean:write name="bean" property="report_time"/></TD>
      <TD class="LIST" align="center"><bean:write name="bean" property="eqpt_stat"/></TD>
      <TD class="LIST" align="center"><bean:write name="bean" property="eqpt_sub_stat"/></TD>
      <TD class="LIST" align="center"><bean:write name="bean" property="eqpt_stat_dsc"/></TD>
      <TD class="LIST"><bean:write name="bean" property="shtng_date"/>&nbsp;<bean:write name="bean" property="shtng_time"/></TD>
      <TD class="LIST"><bean:write name="bean" property="shtng_user"/></TD>
      <TD class="LIST"><bean:write name="bean" property="reason_code"/></TD>
    </TR>
  </logic:iterate>
  </TBODY>
  </TABLE>
  <script>
    <!-- true is sort by descend, false is sort by ascend --> 
    ST(0,"unitStatHis", true);
  </script>  
</logic:present>
</tiles:put>
</tiles:insert>