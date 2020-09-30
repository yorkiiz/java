<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">
<LINK rel="stylesheet" href="/trytftcfm/css/subconf.css" TYPE="text/css">
<body onload="setScreenSize()"/>
<%-- <lcd:breadcrumbing id="com.ibm.lcd.ppt.bc" action="update" key="ppt.title.allEqptAlrtList" page="/ppt_allEqptAlrtList.do" params="eqpt_id"/> --%>
<logic:equal name="mon_type" value="0">
	<nobr><lcd:title key="ppt.title.allEqptAlrtList"/></nobr>
</logic:equal>
<logic:equal name="mon_type" value="1">
	<nobr><lcd:title key="ppt.title.allEqptAlrtHis"/></nobr>
</logic:equal>

<%-- INPUT FORM --%>
<%
String monType = (String)request.getAttribute("mon_type");

String actionStr = "";

if (monType != null && monType.compareTo("0") == 0 )
	actionStr = "/ppt_allEqptAlarmList";
else
	actionStr = "/ppt_allEqptAlarmHis";

%>
<html:form action="<%=actionStr%>">
<TABLE class="FORM">
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
  <TABLE class="LIST">
  <TR>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alleqptalarm.t_stamp"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alleqptalarm.on_off_flg"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alleqptalarm.alrt_code"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alleqptalarm.alrt_id"/></nobr></TH> 
    <TH class="LIST"><nobr><bean:message key="ppt.label.alleqptalarm.alrt_comment"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alleqptalarm.eqptg_id"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alleqptalarm.eqpt_id"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alleqptalarm.port_id"/></nobr></TH>
  </TR>
  <logic:iterate id="bean" name="subList">
    <TR>
      <TD class="LIST"><nobr><bean:write name="bean" property="rpt_date_time"/></nobr></TD>
      <TD class="LIST">
		<logic:equal name="bean" property="on_off_flg" value="Y">
			<nobr><bean:message key="ppt.stat.alleqptalarm.flg_on"/></nobr>
		</logic:equal>
		<logic:equal name="bean" property="on_off_flg" value="N">
			<nobr><bean:message key="ppt.stat.alleqptalarm.flg_off"/></nobr>
		</logic:equal>
      </TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="alrt_code"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="alrt_id"/></nobr></TD>
      <TD class="EQPT1"><nobr><bean:write name="bean" property="alrt_comment"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="eqptg_id"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="eqpt_id"/></nobr></TD>
      <TD class="LIST" align="center"><nobr><bean:write name="bean" property="port_id"/></nobr></TD>
    </TR>
  </logic:iterate>
  </TABLE>
</logic:present>

</tiles:put>
</tiles:insert>