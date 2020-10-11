<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">
<lcd:title key="ppt.title.eqptList"/>
<HR>
<%-- BAY LIST --%>
<html:form action="ppt_eqptList">
<TABLE class="FORM">
<TR>
  <TH class="LABEL"><bean:message key="ppt.label.bay_id"/></TH>
  <TD>
    <html:select property="bay_id">
      <html:option value=""><bean:message key="ppt.select.noselect"/></html:option>
      <html:options collection="bayList" property="bay_id" labelProperty="bay_dsc"/>
    </html:select>
  </TD>
  <TD><html:submit><bean:message key="com.button.select"/></html:submit></TD>
</TR>
</TABLE>
</html:form>
<%-- Equipment List --%>
<logic:notPresent name="eqptList"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
<logic:present name="eqptList">
  <TABLE class="LIST">
  <TR>
    <TH class="LIST"><bean:message key="ppt.label.id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.description"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.stat"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.stat_dsc"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.mode"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.wait_crr_cnt"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.inpr_crr_cnt"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.hld_crr_cnt"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.abnd_cnt"/></TH>
  </TR>
  <logic:iterate id="bean" name="eqptList">
    <TR>
      <TD class="LIST">
        <html:link page="/ppt_eqptInfo.do" paramId="eqpt_id" paramName="bean" paramProperty="eqpt_id">
          <bean:write name="bean" property="eqpt_id"/>
        </html:link>
      </TD>
      <TD class="LIST"><bean:write name="bean" property="eqpt_dsc"/></TD>
      <TD class="LIST" align="center"><bean:write name="bean" property="eqpt_stat"/></TD>
      <TD class="LIST"><bean:write name="bean" property="eqpt_stat_dsc"/></TD>
      <TD class="LIST" align="center"><bean:write name="bean" property="eqpt_mode"/></TD>
      <TD class="LIST" align="center">
        <html:link page="/ppt_wipList.do" paramId="eqpt_id" paramName="bean" paramProperty="eqpt_id">
          <bean:write name="bean" property="wait_crr_cnt"/>
        </html:link>
      </TD>
      <TD class="LIST" align="center">
        <html:link page="/ppt_inprList.do" paramId="eqpt_id" paramName="bean" paramProperty="eqpt_id">
          <bean:write name="bean" property="inpr_crr_cnt"/>
        </html:link>
      </TD>
      <TD class="LIST" align="center"><bean:write name="bean" property="hld_crr_cnt"/></TD>
      <TD class="LIST" align="center"><bean:write name="bean" property="abnd_cnt"/></TD>
    </TR>
  </logic:iterate>
  </TABLE>
</logic:present>

</tiles:put>
</tiles:insert>