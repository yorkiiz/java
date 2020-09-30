<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">
<%-- <lcd:breadcrumbing id="com.ibm.lcd.ppt.bc" action="update" key="ppt.title.allEqptAlrtList" page="/ppt_allEqptAlrtList.do" params="eqpt_id"/> --%>
<lcd:title key="ppt.title.allEqptAlrtList"/>
<HR>
<TABLE class="FORM">
<TR>
  <TH class="LABEL"><bean:message key="ppt.label.eqpt_id"/></TH>
  <TD><bean:write name="ppt_EqptForm" property="eqpt_id"/></TD>
</TR>
</TABLE>
<BR/>
<%-- Alert List --%>
<logic:notPresent name="subList"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
<logic:present name="subList">
  <TABLE class="LIST">
  <TR>
    <TH class="LIST"><bean:message key="ppt.label.eqpt_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.alrt_type"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.alrt_dsc"/></TH>    
  </TR>
  <logic:iterate id="bean" name="subList">
    <TR>
      <TD class="LIST"><bean:write name="bean" property="eqpt_id"/></TD>
      <TD class="LIST" align="center"><bean:write name="bean" property="alrt_lvl"/></TD>
      <TD class="LIST"><bean:write name="bean" property="alrt_dsc"/></TD>      
    </TR>
  </logic:iterate>
  </TABLE>
</logic:present>

</tiles:put>
</tiles:insert>