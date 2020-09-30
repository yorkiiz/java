<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />

<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">

<%-- <lcd:breadcrumbing id="com.ibm.lcd.ppt.bc" action="update" key="ppt.title.crrInfo" page="/ppt_crrInfo.do" params="crr_id" /> --%>
<lcd:title key="ppt.title.crrInfo"/>
<HR>
<%-- Carrier Information --%>
<TABLE class="SECTION">
<TR><TH class="SECTION"><bean:message key="ppt.section.general"/></TH></TR>
<TR>
  <TD class="SECTION">
    <logic:notPresent name="result"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
    <logic:present name="result">
      <TABLE class="FORM">
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.crr_id"/></TH>
        <TD><bean:write name="result" property="crr_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.sgr_id"/></TH>
        <TD><bean:write name="result" property="sgr_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.lot_id"/></TH>
        <TD><bean:write name="result" property="lot_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.splt_id"/></TH>
        <TD><bean:write name="result" property="splt_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.crr_stat"/></TH>
        <TD><bean:write name="result" property="crr_stat"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.valid_flg"/></TH>
        <TD><bean:write name="result" property="valid_flg"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.crr_mnt_rst_date"/></TH>
        <TD><bean:write name="result" property="crr_mnt_rst_date"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.sht_cnt"/></TH>
        <TD><bean:write name="result" property="sht_cnt"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.pnl_cnt"/></TH>
        <TD><bean:write name="result" property="pnl_cnt"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.pnl_sht_cnt"/></TH>
        <TD><bean:write name="result" property="pnl_sht_cnt"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.clup_flg"/></TH>
        <TD><bean:write name="result" property="clup_flg"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.clup_date"/></TH>
        <TD><bean:write name="result" property="clup_date"/>&nbsp;<bean:write name="result" property="clup_time"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.real_emp"/></TH>
        <TD><bean:write name="result" property="real_emp"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.product_id"/></TH>
        <TD><bean:write name="result" property="product_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.ec_code"/></TH>
        <TD><bean:write name="result" property="ec_code"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.route_id"/></TH>
        <TD><bean:write name="result" property="route_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.route_ver"/></TH>
        <TD><bean:write name="result" property="route_ver"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.ope_no"/></TH>
        <TD><bean:write name="result" property="ope_no"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.ope_id"/></TH>
        <TD><bean:write name="result" property="ope_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.ope_ver"/></TH>
        <TD><bean:write name="result" property="ope_ver"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.ope_dsc"/></TH>
        <TD><bean:write name="result" property="ope_dsc"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.dept_code"/></TH>
        <TD><bean:write name="result" property="dept_code"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.proc_id"/></TH>
        <TD><bean:write name="result" property="proc_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.proc_dsc"/></TH>
        <TD><bean:write name="result" property="proc_dsc"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.prty"/></TH>
        <TD><bean:write name="result" property="priority"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.eqpt_id"/></TH>
        <TD><bean:write name="result" property="eqpt_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.eqpt_port_id"/></TH>
        <TD><bean:write name="result" property="eqpt_port_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.eqpt_dsc"/></TH>
        <TD><bean:write name="result" property="eqpt_dsc"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.pv_eqpt_id"/></TH>
        <TD><bean:write name="result" property="pv_eqpt_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.pep_lvl"/></TH>
        <TD><bean:write name="result" property="pep_lvl"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.recipe_id"/></TH>
        <TD><bean:write name="result" property="recipe_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.pv_ope_no"/></TH>
        <TD><bean:write name="result" property="pv_ope_no"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.mproc_id"/></TH>
        <TD><bean:write name="result" property="mproc_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.mproc_flg"/></TH>
        <TD><bean:write name="result" property="mproc_flg"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.hld_user"/></TH>
        <TD><bean:write name="result" property="hld_user"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.hld_code"/></TH>
        <TD><bean:write name="result" property="hld_code"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.hld_date"/></TH>
        <TD><bean:write name="result" property="hld_date"/>&nbsp;<bean:write name="result" property="hld_time"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.wip_bank_flg"/></TH>
        <TD><bean:write name="result" property="wip_bank_flg"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.def_wip_bank_id"/></TH>
        <TD><bean:write name="result" property="def_wip_bank_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.shp_bank_flg"/></TH>
        <TD><bean:write name="result" property="shp_bank_flg"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.crrg_id"/></TH>
        <TD><bean:write name="result" property="crrg_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.crr_use_cnt"/></TH>
        <TD><bean:write name="result" property="crr_use_cnt"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.max_crr_use_cnt"/></TH>
        <TD><bean:write name="result" property="max_crr_use_cnt"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.max_use_over_flg"/></TH>
        <TD><bean:write name="result" property="max_use_over_flg"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.crr_use_time"/></TH>
        <TD><bean:write name="result" property="crr_use_time"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.max_crr_use_time"/></TH>
        <TD><bean:write name="result" property="max_crr_use_time"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.max_time_over_flg"/></TH>
        <TD><bean:write name="result" property="max_time_over_flg"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.crr_rwk_cnt"/></TH>
        <TD><bean:write name="result" property="crr_rwk_cnt"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.ptt_grade"/></TH>
        <TD><bean:write name="result" property="ptt_grade"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.crr_grade"/></TH>
        <TD><bean:write name="result" property="crr_grade"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.mes_id"/></TH>
        <TD><bean:write name="result" property="mes_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.crr_cate"/></TH>
        <TD><bean:write name="result" property="crr_cate"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.iqc_stat"/></TH>
        <TD><bean:write name="result" property="iqc_stat"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.max_sht_cnt"/></TH>
        <TD><bean:write name="result" property="max_sht_cnt"/></TD>
      </TR>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>
<BR/>
<%-- Sheet Information --%>
<TABLE class="SECTION">
<TR><TH class="SECTION"><bean:message key="ppt.section.sheet"/></TH></TR>
<TR>
  <TD class="SECTION">
    <logic:notPresent name="subList1"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
    <logic:present name="subList1">
      <TABLE class="LIST">
      <TR>
        <TH class="LIST"><bean:message key="ppt.label.slot_no"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.sht_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.sht_stat"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.scrp_typ"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.lot_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.splt_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.sheetjdg"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.thickness"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.abnormal_st_flg"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.ito_flg"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.sgr_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.reproc_flg"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.sht_note_flg"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.pnl_cnt"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.pnl_grade"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.rwk_cnt"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.ptt_grade"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.ptt_type"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.ptt_group"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.crr_grade"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.eng_sht_flg"/></TH>
      </TR>
      <logic:iterate id="bean" name="subList1">
        <TR>
          <TD class="LIST" align="center"><bean:write name="bean" property="slot_no"/></TD>
          <TD class="LIST">
            <html:link page="/ppt_shtInfo.do" paramId="sht_id" paramName="bean" paramProperty="sht_id">
              <bean:write name="bean" property="sht_id"/>
            </html:link>
          </TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="sht_stat"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="scrp_typ"/></TD>
          <TD class="LIST">
            <html:link page="/ppt_lotInfo.do" paramId="lot_id" paramName="bean" paramProperty="lot_id">
              <bean:write name="bean" property="lot_id"/>
            </html:link>
          </TD>
          <TD class="LIST"><bean:write name="bean" property="splt_id"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="sheetjdg"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="thickness"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="abnormal_st_flg"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="ito_flg"/></TD>
          <TD class="LIST"><bean:write name="bean" property="sgr_id"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="reproc_flg"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="sht_note_flg"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="pnl_cnt"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="pnl_grade"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="rwk_cnt"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="ptt_grade"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="ptt_type"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="ptt_group"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="crr_grade"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="eng_sht_flg"/></TD>
        </TR>
      </logic:iterate>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>
<BR/>
<%-- QRS Information --%>
<TABLE class="SECTION">
<TR><TH class="SECTION"><bean:message key="ppt.section.qrs"/></TH></TR>
<TR>
  <TD class="SECTION">
    <logic:notPresent name="subList2"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
    <logic:present name="subList2">
      <TABLE class="LIST">
      <TR>
        <TH class="LIST"><bean:message key="ppt.label.qrs_ope_id"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.qrs_typ"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.qrs_date"/></TH>
        <TH class="LIST"><bean:message key="ppt.label.qrk_date"/></TH>
      </TR>
      <logic:iterate id="bean" name="subList2">
        <TR>
          <TD class="LIST"><bean:write name="bean" property="qrs_ope_id"/></TD>
          <TD class="LIST" align="center"><bean:write name="bean" property="qrs_typ"/></TD>
          <TD class="LIST"><bean:write name="bean" property="qrs_date"/>&nbsp;<bean:write name="bean" property="qrs_time"/></TD>
          <TD class="LIST"><bean:write name="bean" property="qrk_date"/>&nbsp;<bean:write name="bean" property="qrk_time"/></TD>
        </TR>
      </logic:iterate>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>

</tiles:put>
</tiles:insert>