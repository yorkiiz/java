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
<%-- <lcd:breadcrumbing id="com.ibm.lcd.ppt.bc" action="update" key="ppt.title.inprList" page="/ppt_inprList.do" params="eqpt_id" /> --%>
<lcd:title key="ppt.title.inprList"/>
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
        <TH class="LABEL"><nobr><bean:message key="ppt.label.eqpt_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="eqpt_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.eqpt_dsc"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="eqpt_dsc"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.recipe_id"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="recipe_id"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.eqpt_stat"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="eqpt_stat"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.eqpt_stat_dsc"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="eqpt_stat_dsc"/></nobr></TD>
      </TR>
      <TR>
        <TH class="LABEL"><nobr><bean:message key="ppt.label.eqpt_mode"/></nobr></TH>
        <TD><nobr><bean:write name="result" property="eqpt_mode"/></nobr></TD>
      </TR>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>
<BR/>
<TABLE class="SECTION">
<TR><TH class="SECTION"><bean:message key="ppt.section.inpr"/></TH></TR>
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
    	<TH class="LIST"><nobr><bean:message key="ppt.label.cr_ope_no"/></nobr></TH>
    	<TH class="LIST"><nobr><bean:message key="ppt.label.cr_ope_id"/></nobr></TH>
    	<TH class="LIST"><nobr><bean:message key="ppt.label.cr_pep_lvl"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.product_id"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.ec_code"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.prty"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.logon_date"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.pnl_cnt"/></nobr></TH>
        <TH class="LIST"><nobr><bean:message key="ppt.label.abnd_flg"/></nobr></TH>
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
      	  <TD class="LIST"><nobr><bean:write name="bean" property="cr_ope_no"/></nobr></TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="cr_ope_id"/></nobr></TD>   
          <TD class="LIST"><nobr><bean:write name="bean" property="cr_pep_lvl"/></nobr></TD>                    
          <TD class="LIST"><nobr><bean:write name="bean" property="product_id"/></nobr></TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="ec_code"/></nobr></TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="prty"/></nobr></TD>
          <TD class="LIST"><nobr><bean:write name="bean" property="logon_date"/>&nbsp;<bean:write name="bean" property="logon_time"/></nobr></TD>
          <TD class="LIST" align="center"><nobr><bean:write name="bean" property="pnl_cnt"/></nobr></TD>
          <TD class="LIST" align="center"><nobr><bean:write name="bean" property="abnd_flg"/></nobr></TD>
        </TR>
      </logic:iterate>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>
</tiles:put>
</tiles:insert>