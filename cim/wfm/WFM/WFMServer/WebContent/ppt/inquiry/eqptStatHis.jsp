<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">
<body onload="setScreenSize()"/>
<lcd:title key="ppt.title.eqptStatHis"/>
<HR>
<%-- INPUT FORM --%>
<html:form action="ppt_eqptStatHis">
<TABLE class="FORM">
<TR>
  <TH class="LABEL"><nobr><bean:message key="ppt.label.eqpt_id"/></nobr></TH>
  <TD><nobr><html:hidden property="eqpt_id" write="true"/></nobr></TD>
</TR>
<TR>
  <TH class="LABEL"><nobr><bean:message key="ppt.label.fr_date"/></nobr></TH>
  <TD><nobr><html:text property="fr_date"/></nobr></TD>
</TR>
<TR>
  <TH class="LABEL"><nobr><bean:message key="ppt.label.to_date"/></nobr></TH>
  <TD><nobr><html:text property="to_date"/></nobr></TD>
</TR>
<TR>
  <TD><html:submit><bean:message key="com.button.search"/></html:submit></TD>
</TR>
</TABLE>
</html:form>
<logic:notPresent name="subList"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
<logic:present name="subList">
  <TABLE class="LIST" ID="DetailList">
  <TR>
    <TH class="LIST" onclick="ST(0,'DetailList', true)"><nobr><bean:message key="ppt.label.clm_date"/></nobr></TH>
    <TH class="LIST" onclick="ST(1,'DetailList', true)"><nobr><bean:message key="ppt.label.clm_user"/></nobr></TH>
    <TH class="LIST" onclick="ST(2,'DetailList', true)"><nobr><bean:message key="ppt.label.clm_mfdt"/></nobr></TH>
    <TH class="LIST" onclick="ST(3,'DetailList', true)"><nobr><bean:message key="ppt.label.clm_mfwk"/></nobr></TH>
    <TH class="LIST" onclick="ST(4,'DetailList', true)"><nobr><bean:message key="ppt.label.clm_mfmn"/></nobr></TH>
    <TH class="LIST" onclick="ST(5,'DetailList', true)"><nobr><bean:message key="ppt.label.clm_mfsh"/></nobr></TH>
    <TH class="LIST" onclick="ST(6,'DetailList', true)"><nobr><bean:message key="ppt.label.report_date"/></nobr></TH>
    <TH class="LIST" onclick="ST(7,'DetailList', true)"><nobr><bean:message key="ppt.label.report_mfsh"/></nobr></TH>    
    <TH class="LIST" onclick="ST(8,'DetailList', true)"><nobr><bean:message key="ppt.label.eqpt_stat"/></nobr></TH>
    <TH class="LIST" onclick="ST(9,'DetailList', true)"><nobr><bean:message key="ppt.label.eqpt_sub_stat"/></nobr></TH>
    <TH class="LIST" onclick="ST(10,'DetailList', true)"><nobr><bean:message key="ppt.label.eqpt_stat_dsc"/></nobr></TH>
    <TH class="LIST" onclick="ST(11,'DetailList', true)"><nobr><bean:message key="ppt.label.pv_eqpt_stat"/></nobr></TH>
    <TH class="LIST" onclick="ST(12,'DetailList', true)"><nobr><bean:message key="ppt.label.pv_eqpt_sub_stat"/></nobr></TH>
    <TH class="LIST" onclick="ST(13,'DetailList', true)"><nobr><bean:message key="ppt.label.shtng_date"/></nobr></TH>
    <TH class="LIST" onclick="ST(14,'DetailList', true)"><nobr><bean:message key="ppt.label.shtng_user"/></nobr></TH>
    <TH class="LIST" onclick="ST(15,'DetailList', true)"><nobr><bean:message key="ppt.label.reason_code"/></nobr></TH>
    <TH class="LIST" onclick="ST(16,'DetailList', true)"><nobr><bean:message key="ppt.label.comment"/></nobr></TH>
  </TR>
  <TBODY>
  <logic:iterate id="bean" name="subList">
    <TR>
      <TD class="LISTNOWRAP"><nobr><bean:write name="bean" property="clm_date"/>&nbsp;<bean:write name="bean" property="clm_time"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="clm_user"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="clm_mfdt"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="clm_mfwk"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="clm_mfmn"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="clm_mfsh"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="report_date"/>&nbsp;<bean:write name="bean" property="report_time"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="report_mfsh"/></nobr></TD>
      <TD class="LIST" align="center"><nobr><bean:write name="bean" property="eqpt_stat"/></nobr></TD>
      <TD class="LIST" align="center"><nobr><bean:write name="bean" property="eqpt_sub_stat"/></nobr></TD>
      <TD class="LIST" align="center"><nobr><bean:write name="bean" property="eqpt_stat_dsc"/></nobr></TD>
      <TD class="LIST" align="center"><nobr><bean:write name="bean" property="pv_eqpt_stat"/></nobr></TD>
      <TD class="LIST" align="center"><nobr><bean:write name="bean" property="pv_eqpt_sub_stat"/></nobr></TD> 
      <TD class="LIST"><nobr><bean:write name="bean" property="shtng_date"/>&nbsp;<bean:write name="bean" property="shtng_time"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="shtng_user"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="reason_code"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="comment"/></nobr></TD>
    </TR>
  </logic:iterate>
  </TBODY>
  </TABLE>
  <script>
    ST(0,"DetailList", false);
  </script>  
</logic:present>
</tiles:put>
</tiles:insert>