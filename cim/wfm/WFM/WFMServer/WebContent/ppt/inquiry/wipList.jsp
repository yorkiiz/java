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
<%--<lcd:breadcrumbing id="com.ibm.lcd.ppt.bc" action="update" key="ppt.title.wipList" page="/ppt_wipList.do" params="eqpt_id" /> --%>
<lcd:title key="ppt.title.wipList"/>
<HR>
<%-- Equipment Information --%>
<TABLE class="SECTION">
<TR><TH class="SECTION"><bean:message key="ppt.section.equipment"/></TH></TR>
<TR>
  <TD class="SECTION">
    <logic:notPresent name="result"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
    <logic:present name="result">
      <TABLE class="FORM">
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.eqpt_id"/></TH>
        <TD><bean:write name="result" property="eqpt_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.eqpt_dsc"/></TH>
        <TD><bean:write name="result" property="eqpt_dsc"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.eqpt_cate"/></TH>
        <TD><bean:write name="result" property="eqpt_cate"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.recipe_id"/></TH>
        <TD><bean:write name="result" property="recipe_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.eqpt_stat"/></TH>
        <TD><bean:write name="result" property="eqpt_stat"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.eqpt_stat_dsc"/></TH>
        <TD><bean:write name="result" property="eqpt_stat_dsc"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.eqpt_mode"/></TH>
        <TD><bean:write name="result" property="eqpt_mode"/></TD>
      </TR>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>
<BR/>
<TABLE class="SECTION">
<TR><TH class="SECTION"><bean:message key="ppt.section.wip"/></TH></TR>
<TR>
  <TD class="SECTION">
    <logic:notPresent name="subList"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
    <logic:present name="subList">
      <TABLE class="LIST">
      <TR>
        <TH class="LIST"><nobr><bean:message key="ppt.label.crr_id"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.crr_stat"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.ppbox_id"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.lot_id"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.nx_ope_no"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.nx_ope_id"/></nobr></TH> 
        <TH class="LIST"><nobr><bean:message key="ppt.label.nx_pep_lvl"/></nobr></TH>               
        <TH class="LIST"><nobr><bean:message key="ppt.label.product_id"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.ec_code"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.prty"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.logof_date"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.sht_cnt"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.pnl_cnt"/></nobr></TH>
        <TH class="LIST"><bean:message key="ppt.label.splt_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.nx_route_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.nx_route_ver"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.nx_ope_dsc"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.nx_recipe_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.nx_proc_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.rsv_user_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.rsv_flg"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.man_ope_time"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.rwk_avl_flg"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.n2_wip_cnt"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.n2_kanban"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.qrs_avl_flg"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.qrs_ope_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.qrs_date"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.min_qrs_avl_flg"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.min_qrs_ope_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.min_qrs_date"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.xf_cmd_stat"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.position"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.lot_note_flg"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.ld_crr_asgn_mode"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.assign_valid"/></TH>
      </TR>
      <logic:iterate id="bean" name="subList">
        <TR>
          <TD class="LIST">
            <html:link page="/ppt_crrInfo.do" paramId="crr_id" paramName="bean" paramProperty="crr_id">
              <nobr><bean:write name="bean" property="crr_id"/></nobr>
            </html:link>
          </TD>
          <TD class="LIST" align="center"><nobr><bean:write name="bean" property="crr_stat"/></nobr></TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="ppbox_id"/></nobr></TD>
          <TD class="LIST">
            <html:link page="/ppt_lotInfo.do" paramId="lot_id" paramName="bean" paramProperty="lot_id">
              <nobr><bean:write name="bean" property="lot_id"/></nobr>
            </html:link>
          </TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="nx_ope_no"/></nobr></TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="nx_ope_id"/></nobr></TD>    
          <TD class="LIST"><nobr><bean:write name="bean" property="nx_pep_lvl"/></nobr></TD>                
          <TD class="LIST"><nobr><bean:write name="bean" property="product_id"/></nobr></TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="ec_code"/></nobr></TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="prty"/></nobr></TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="logof_date"/></nobr>&nbsp;<bean:write name="bean" property="logof_time"/></TD>
          <TD class="LIST" align="center"><nobr><bean:write name="bean" property="sht_cnt"/></nobr></TD>
          <TD class="LIST" align="center"><nobr><bean:write name="bean" property="pnl_cnt"/></nobr></TD>
          <TD class="LIST"><bean:write name="bean" property="splt_id"/></TD>
          <TD class="LIST"><bean:write name="bean" property="nx_route_id"/></TD>
          <TD class="LIST"><bean:write name="bean" property="nx_route_ver"/></TD>
          <TD class="LIST"><bean:write name="bean" property="nx_ope_dsc"/></TD>
          <TD class="LIST"><bean:write name="bean" property="nx_recipe_id"/></TD>
          <TD class="LIST"><bean:write name="bean" property="nx_proc_id"/></TD>
          <TD class="LIST"><bean:write name="bean" property="rsv_user_id"/></TD>
          <TD class="LIST"><bean:write name="bean" property="rsv_flg"/></TD>
          <TD class="LIST"><bean:write name="bean" property="man_ope_time"/></TD>
          <TD class="LIST"><bean:write name="bean" property="rwk_avl_flg"/></TD>
          <TD class="LIST"><bean:write name="bean" property="n2_wip_cnt"/></TD>
          <TD class="LIST"><bean:write name="bean" property="n2_kanban"/></TD>
          <TD class="LIST"><bean:write name="bean" property="qrs_avl_flg"/></TD>
          <TD class="LIST"><bean:write name="bean" property="qrs_ope_id"/></TD>
          <TD class="LIST"><bean:write name="bean" property="qrs_date"/>&nbsp;<bean:write name="bean" property="qrs_time"/></TD>
          <TD class="LIST"><bean:write name="bean" property="min_qrs_avl_flg"/></TD>
          <TD class="LIST"><bean:write name="bean" property="min_qrs_ope_id"/></TD>
          <TD class="LIST"><bean:write name="bean" property="min_qrs_date"/>&nbsp;<bean:write name="bean" property="min_qrs_time"/></TD>
          <TD class="LIST"><bean:write name="bean" property="xf_cmd_stat"/></TD>
          <TD class="LIST"><bean:write name="bean" property="position"/></TD>
          <TD class="LIST"><bean:write name="bean" property="lot_note_flg"/></TD>
          <TD class="LIST"><bean:write name="bean" property="ld_crr_asgn_mode"/></TD>
          <TD class="LIST"><bean:write name="bean" property="assign_valid"/></TD>
        </TR>
      </logic:iterate>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>

</tiles:put>
</tiles:insert>