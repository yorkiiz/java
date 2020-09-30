<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, com.ibm.cxl.struct.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />
<%
%>
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">

<body onload="setScreenSize()"/>

<%-- <lcd:breadcrumbing id="com.ibm.lcd.ppt.bc" action="update" key="ppt.title.allEqptAlrtList" page="/ppt_allEqptAlrtList.do" params="eqpt_id"/> --%>
<lcd:title key="ppt.title.allLotAlarmList"/>
<HR>

<%-- Alert List --%>
<logic:notPresent name="subList"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
<logic:present name="subList">
  <TABLE class="LIST">
  <TR>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alllotalarm.t_stamp"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alllotalarm.lot_id"/></nobr></TH>
    <TH class="LIST"><nobr><bean:message key="ppt.label.alllotalarm.clm_cate"/></nobr></TH>
  </TR>
  <logic:iterate id="bean" name="subList">
    <TR>
      <TD class="LIST"><nobr><bean:write name="bean" property="clm_date_time"/></nobr></TD>
      <TD class="LIST"><nobr><bean:write name="bean" property="lot_id"/></nobr></TD>
      <TD class="LIST">
      	<logic:equal name="bean" property="clm_cate" value="HLDC">
			<nobr><bean:message key="ppt.stat.alllotalarm.hldc"/></nobr>
		</logic:equal>
		<logic:equal name="bean" property="clm_cate" value="HLDR">
			<nobr><bean:message key="ppt.stat.alllotalarm.hldr"/></nobr>
		</logic:equal>
		<logic:equal name="bean" property="clm_cate" value="FTHD">
			<nobr><bean:message key="ppt.stat.alllotalarm.fthd"/></nobr>
		</logic:equal>
		<logic:equal name="bean" property="clm_cate" value="FTHR">
			<nobr><bean:message key="ppt.stat.alllotalarm.fthr"/></nobr>
		</logic:equal>
      </TD>
    </TR>
  </logic:iterate>
  </TABLE>
</logic:present>

</tiles:put>
</tiles:insert>