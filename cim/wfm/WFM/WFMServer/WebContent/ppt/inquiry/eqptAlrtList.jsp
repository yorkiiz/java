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
<%-- <lcd:breadcrumbing id="com.ibm.lcd.ppt.bc" action="update" key="ppt.title.eqptAlrtList" page="/ppt_eqptAlrtList.do" params="eqpt_id" /> --%>
<lcd:title key="ppt.title.eqptAlrtList"/>
<HR>
<%-- INPUT FORM --%>
<%-- <html:form action="/ppt_allEqptAlarmList"> --%>
<html:form action="/ppt_eqptAlrtList" >
<TABLE class="FORM">
<TR>
  <TH class="LABEL"><bean:message key="ppt.label.eqpt_id"/></TH>
  <TD><html:text property="eqpt_id"/></TD>
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
<%-- Alert List --%>
<logic:notPresent name="subList"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
<logic:present name="subList">
  <TABLE class="LIST">
  <TR>
    <TH class="LIST"><nobr><bean:message key="ppt.label.eqpt_id"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.eqpt_dsc"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.rpt_date"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.rpt_source"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alrt_code"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alrt_lvl"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alrt_on_off_flg"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alrt_dsc"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.cfm_user_id"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.cfm_date"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.cfm_comment"/></nobr></TH>
  </TR>
  <logic:iterate id="bean" name="subList">
    <TR>
      <TD class="LIST"><nobr><bean:write name="bean" property="eqpt_id"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="eqpt_dsc"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="rpt_date"/>&nbsp;<bean:write name="bean" property="rpt_time"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="rpt_source"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="alrt_code"/></nobr></TD>
      <TD class="LIST" align="center"><nobr><bean:write name="bean" property="alrt_lvl"/></nobr></TD>
      <TD class="LIST" align="center"><nobr><bean:write name="bean" property="alrt_on_off_flg"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="alrt_comment"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="cfm_user_id"/></nobr></TD>      
      <TD class="LIST"><nobr><bean:write name="bean" property="cfm_date"/>&nbsp;<bean:write name="bean" property="cfm_time"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="cfm_comment"/></nobr></TD>
    </TR>
  </logic:iterate>
  </TABLE>
</logic:present>

</tiles:put>
</tiles:insert>