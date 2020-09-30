<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />
<%--
StructElement struct = (StructElement)request.getAttribute("xpinqsht");
if (struct != null) {
	int i, j;
	ArrayList list = null;
	StructElement[] oary = null;

	// Convert structure.
	request.setAttribute("result", StructUtilities.toMap(struct));

	// Create q-time list.
	oary = struct.select("oary1");
	i = struct.getInteger("qrs_cnt", 0);
	if (i > 0 && oary.length > 0) {
		list = new ArrayList();
		for (j = 0; j < i; j++)
			list.add(StructUtilities.toMap(oary[j]));
		request.setAttribute("subList1", list);
	}

	// Create abnormal list.
	oary = struct.select("oary2");
	i = struct.getInteger("abn_cnt", 0);
	if (i > 0 && oary.length > 0) {
		list = new ArrayList();
		for (j = 0; j < i && j < oary.length; j++)
			list.add(StructUtilities.toMap(oary[j]));
		request.setAttribute("subList2", list);
	}
}
--%>
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">

<%-- <lcd:breadcrumbing id="com.ibm.lcd.ppt.bc" action="update" key="ppt.title.shtInfo" page="/ppt_shtInfo.do" params="sht_id" /> --%>
<lcd:title key="ppt.title.shtInfo"/>
<HR>
<%-- Sheet Information --%>
<TABLE class="SECTION">
<TR><TH class="SECTION"><nobr><bean:message key="ppt.section.general"/></nobr></TH></TR>
<TR>
  <TD class="SECTION">
    <logic:notPresent name="result"><nobr><bean:message key="ppt.msg.nodata"/></nobr></logic:notPresent>
    <logic:present name="result">
      <TABLE class="FORM">
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.sht_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="sht_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.lot_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="lot_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.splt_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="splt_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.crr_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="crr_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.slot_no"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="slot_no"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.sht_stat"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="sht_stat"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.pnl_cnt"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="pnl_cnt"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.x_axis_cnt"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="x_axis_cnt"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.y_axis_cnt"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="y_axis_cnt"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.pnl_sht_cnt"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="pnl_sht_cnt"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.pnl_grade"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="pnl_grade"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.reproc_flg"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="reproc_flg"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.route_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="route_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.route_ver"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="route_ver"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.ope_no"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="ope_no"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.ope_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="ope_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.ope_ver"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="ope_ver"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.proc_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="proc_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.eqpt_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="eqpt_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.eqpt_port_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="eqpt_port_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.pep_lvl"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="pep_lvl"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.recipe_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="recipe_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.ds_recipe_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="ds_recipe_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.ac_recipe_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="ac_recipe_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.mproc_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="mproc_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.mproc_flg"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="mproc_flg"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.abnd_flg"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="abnd_flg"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.deldays"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="deldays"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.sht_note_flg"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="sht_note_flg"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.cel_stb_date"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="cel_stb_date"/>&nbsp;<bean:write name="result" property="cel_stb_time"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.fab_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="fab_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.line_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="line_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.product_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="product_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.ec_code"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="ec_code"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.charge_code"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="charge_code"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.owner_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="owner_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.prty"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="prty"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.rwk_cnt"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="rwk_cnt"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.ptt_grade"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="ptt_grade"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.ptt_type"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="ptt_type"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.ptt_group"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="ptt_group"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.crr_grade"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="crr_grade"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.order_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="order_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.sales_order"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="sales_order"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.project_cd"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="project_cd"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.eng_name"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="eng_name"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.exp_no"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="exp_no"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.ito_flg"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="ito_flg"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.abnormal_st_flg"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="abnormal_st_flg"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.dmy_iqc_rslt"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="dmy_iqc_rslt"/></nobr></TD>
      </TR>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>
<BR/>
<%-- QRS Information --%>
<TABLE class="SECTION">
<TR><TH class="SECTION"><nobr><bean:message key="ppt.section.qrs"/></nobr></TH></TR>
<TR>
  <TD class="SECTION">
    <logic:notPresent name="subList1"><nobr><bean:message key="ppt.msg.nodata"/></nobr></logic:notPresent>
    <logic:present name="subList1">
      <TABLE class="LIST">
      <TR>
        <TH class="LIST"><nobr><bean:message key="ppt.label.qrs_ope_id"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.qrs_typ"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.qrs_date"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.qrk_date"/></nobr></TH>
      </TR>
      <logic:iterate id="bean" name="subList1">
        <TR>
          <TD class="LIST"><nobr><bean:write name="bean" property="qrs_ope_id"/></nobr></TD>
          <TD class="LIST" align="center"><nobr><bean:write name="bean" property="qrs_typ"/></nobr></TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="qrs_date"/>&nbsp;<bean:write name="bean" property="qrs_time"/></nobr></TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="qrk_date"/>&nbsp;<bean:write name="bean" property="qrk_time"/></nobr></TD>
        </TR>
      </logic:iterate>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>
<BR/>
<%-- Abnormal Information --%>
<TABLE class="SECTION">
<TR><TH class="SECTION"><nobr><bean:message key="ppt.section.abnormal"/></nobr></TH></TR>
<TR>
  <TD class="SECTION">
    <logic:notPresent name="subList2"><nobr><bean:message key="ppt.msg.nodata"/></nobr></logic:notPresent>
    <logic:present name="subList2">
      <TABLE class="LIST">
      <TR>
        <TH class="LIST"><nobr><bean:message key="ppt.label.seq_no"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.abnormal_flg"/></nobr></TH>
      </TR>
      <logic:iterate id="bean" name="subList2">
        <TR>
          <TD class="LIST" align="right"><nobr><bean:write name="bean" property="seq_no"/></nobr></TD>
          <TD class="LIST" align="center"><nobr><bean:write name="bean" property="abnormal_flg"/></nobr></TD>
        </TR>
      </logic:iterate>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>
<BR/>
<%-- Related Information --%>
<TABLE class="SECTION">
<TR><TH class="SECTION"><nobr><bean:message key="ppt.section.related"/></nobr></TH></TR>
<TR>
  <TD class="SECTION">
    <UL>
      <LI>
        <html:link page="/ppt_shtHis.do" paramId="sht_id" paramName="result" paramProperty="sht_id">
          <bean:message key="ppt.title.shtHis"/>
        </html:link>
      </LI>
    </UL>
  </TD>
</TR>
</TABLE>

</tiles:put>
</tiles:insert>