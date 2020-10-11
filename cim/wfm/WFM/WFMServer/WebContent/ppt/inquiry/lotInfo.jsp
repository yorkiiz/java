<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />
<%--
StructElement struct = (StructElement)request.getAttribute("xpinqlot");
if (struct != null){
	// Convert structure.
	request.setAttribute("result", StructUtilities.toMap(struct));
}
--%>
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">
<lcd:title key="ppt.title.lotInfo"/>
<HR>
<%-- Lot Information --%>
<TABLE class="SECTION">
<TR><TH class="SECTION"><bean:message key="ppt.section.general"/></TH></TR>
<TR>
  <TD class="SECTION">
    <logic:notPresent name="result"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
    <logic:present name="result">
      <TABLE class="FORM">
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.lot_id"/></TH>
        <TD><%=request.getAttribute("lot_id")%></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.lot_stat"/></TH>
        <TD><bean:write name="result" property="lot_stat"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.product_cate"/></TH>
        <TD><bean:write name="result" property="product_cate"/></TD>
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
        <TH class="LABEL"><bean:message key="ppt.label.stb_sht_cnt"/></TH>
        <TD><bean:write name="result" property="stb_sht_cnt"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.pnl_sht_cnt"/></TH>
        <TD><bean:write name="result" property="pnl_sht_cnt"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.lot_note_flg"/></TH>
        <TD><bean:write name="result" property="lot_note_flg"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.mtrl_product_id"/></TH>
        <TD><bean:write name="result" property="mtrl_product_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.mtrl_product_dsc"/></TH>
        <TD><bean:write name="result" property="mtrl_product_dsc"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.mtrl_mkr_id"/></TH>
        <TD><bean:write name="result" property="mtrl_mkr_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.mtrl_mkr_dsc"/></TH>
        <TD><bean:write name="result" property="mtrl_mkr_dsc"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.order_id"/></TH>
        <TD><bean:write name="result" property="order_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.sales_order"/></TH>
        <TD><bean:write name="result" property="sales_order"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.stb_fab_id"/></TH>
        <TD><bean:write name="result" property="stb_fab_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.stb_line_id"/></TH>
        <TD><bean:write name="result" property="stb_line_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.stb_product_id"/></TH>
        <TD><bean:write name="result" property="stb_product_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.stb_product_dsc"/></TH>
        <TD><bean:write name="result" property="stb_product_dsc"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.stb_ec_code"/></TH>
        <TD><bean:write name="result" property="stb_ec_code"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.stb_charge_code"/></TH>
        <TD><bean:write name="result" property="stb_charge_code"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.stb_owner_id"/></TH>
        <TD><bean:write name="result" property="stb_owner_id"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.stb_prty"/></TH>
        <TD><bean:write name="result" property="stb_prty"/></TD>
      </TR>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>
</tiles:put>
</tiles:insert>