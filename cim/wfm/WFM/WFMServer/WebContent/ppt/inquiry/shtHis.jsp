<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">
<lcd:title key="ppt.title.shtHis"/>
<HR>
<TABLE class="FORM">
<TR>
  <TH class="LABEL"><bean:message key="ppt.label.sht_id"/></TH>
  <TD><bean:write name="ppt_ShtForm" property="sht_id"/></TD>
</TR>
</TABLE>
<BR/>
<%-- History Information --%>
<logic:notPresent name="subList"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
<logic:present name="subList">
  <TABLE class="LIST">
  <TR>
    <TH class="LIST"><bean:message key="ppt.label.clm_cate"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.clm_user"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.clm_date"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.lot_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.splt_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.crr_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.slot_no"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.ppbox_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.pnl_cnt"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.route_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.route_ver"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.ope_no"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.ope_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.ope_ver"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.proc_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.eqptg_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.eqpt_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.eqpt_port_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.pep_lvl"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.evt_cate"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.evt_code"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.evt_user"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.evt_date"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.evt_mfdt"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.evt_mfwk"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.evt_mfmn"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.evt_mfsh"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.product_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.product_cate"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.ec_code"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.prty"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.ds_recipe_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.ac_recipe_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.rep_cate"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.dept_code"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.rpt_user"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.sht_ope_msg"/></TH>
  </TR>
  <logic:iterate id="bean" name="subList">
    <TR>
      <TD class="LIST" align="center"><bean:write name="bean" property="clm_cate"/></TD>
      <TD class="LIST"><bean:write name="bean" property="clm_user"/></TD>
      <TD class="LIST"><bean:write name="bean" property="clm_date"/>&nbsp;<bean:write name="bean" property="clm_time"/></TD>
      <TD class="LIST"><bean:write name="bean" property="lot_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="splt_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="crr_id"/></TD>
      <TD class="LIST" align="center"><bean:write name="bean" property="slot_no"/></TD>
      <TD class="LIST"><bean:write name="bean" property="ppbox_id"/></TD>
      <TD class="LIST" align="center"><bean:write name="bean" property="pnl_cnt"/></TD>
      <TD class="LIST"><bean:write name="bean" property="route_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="route_ver"/></TD>
      <TD class="LIST"><bean:write name="bean" property="ope_no"/></TD>
      <TD class="LIST"><bean:write name="bean" property="ope_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="ope_ver"/></TD>
      <TD class="LIST"><bean:write name="bean" property="proc_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="eqptg_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="eqpt_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="eqpt_port_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="pep_lvl"/></TD>
      <TD class="LIST"><bean:write name="bean" property="evt_cate"/></TD>
      <TD class="LIST"><bean:write name="bean" property="evt_code"/></TD>
      <TD class="LIST"><bean:write name="bean" property="evt_user"/></TD>
      <TD class="LIST"><bean:write name="bean" property="evt_date"/>&nbsp;<bean:write name="bean" property="evt_time"/></TD>
      <TD class="LIST"><bean:write name="bean" property="evt_mfdt"/></TD>
      <TD class="LIST"><bean:write name="bean" property="evt_mfwk"/></TD>
      <TD class="LIST"><bean:write name="bean" property="evt_mfmn"/></TD>
      <TD class="LIST"><bean:write name="bean" property="evt_mfsh"/></TD>
      <TD class="LIST"><bean:write name="bean" property="product_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="product_cate"/></TD>
      <TD class="LIST"><bean:write name="bean" property="ec_code"/></TD>
      <TD class="LIST"><bean:write name="bean" property="prty"/></TD>
      <TD class="LIST"><bean:write name="bean" property="ds_recipe_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="ac_recipe_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="rep_cate"/></TD>
      <TD class="LIST"><bean:write name="bean" property="dept_code"/></TD>
      <TD class="LIST"><bean:write name="bean" property="rpt_user"/></TD>
      <TD class="LIST"><bean:write name="bean" property="sht_ope_msg"/></TD>
    </TR>
  </logic:iterate>
  </TABLE>
</logic:present>

</tiles:put>
</tiles:insert>