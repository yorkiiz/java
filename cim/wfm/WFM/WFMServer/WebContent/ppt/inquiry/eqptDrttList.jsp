<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, com.ibm.cxl.struct.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />
<%
int i, j;
ArrayList list = null;
StructElement[] oary = null;
String portid = (String) request.getAttribute("port_id");
request.removeAttribute("port_id");
//Create General Info
StructElement struct = (StructElement)request.getAttribute("xpinqeqp");
if (struct != null) {

	// Convert structure.
	request.setAttribute("result", StructUtilities.toMap(struct));

	// Create port list.
	oary = struct.select("oary");
	i = struct.getInteger("port_cnt", 0);
	if (i > 0 && oary.length > 0) {
		list = new ArrayList();
		for (j = 0; j < i && j < oary.length; j++)
			list.add(StructUtilities.toMap(oary[j]));
		request.setAttribute("subList1", list);
	}
}
//Create what next
struct = (StructElement)request.getAttribute("dpwhtnxt");
if (struct != null) {


	// Create history list.
	oary = struct.select("oary");
	i = struct.getInteger("crr_cnt", 0);
	if (i > 0 && oary.length > 0) {
		list = new ArrayList();
		for (j = 0; j < i && j < oary.length; j++)
			list.add(StructUtilities.toMap(oary[j]));
		request.setAttribute("subList2", list);
	}
}

//Create dpieqarl
struct = (StructElement)request.getAttribute("dpieqarl");
if (struct != null) {

	// Create history list.
	oary = struct.select("oary1");
	i = struct.getInteger("rule_aply_cnt", 0);
	if (i > 0 && oary.length > 0) {
		list = new ArrayList();
		for (j = 0; j < i && j < oary.length; j++)
			list.add(StructUtilities.toMap(oary[j]));
		request.setAttribute("subList3", list);
	}
}

%>
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">

<%-- <lcd:breadcrumbing id="com.ibm.lcd.ppt.bc" action="update" key="ppt.title.eqptDrttList" page="/ppt_eqptDrttList.do" params="eqpt_id" params="port_id"/> --%>
<lcd:title key="ppt.title.eqptDrttList"/>
<HR>
<TABLE class="SECTION">
<TR><TH class="SECTION"><bean:message key="ppt.section.general"/></TH></TR>
<TR>
  <TD class="SECTION">
    <logic:notPresent name="result"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
    <logic:present name="result">
      <TABLE class="FORM">
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.eqpt_id"/></TH>
        <TD class="WIDTH"><bean:write name="result" property="eqpt_id"/></TD>
        <TH class="LABEL"><bean:message key="ppt.label.eqpt_mode"/></TH>
        <TD><bean:write name="result" property="eqpt_mode"/></TD>
      </TR>
      <TR>
        <TH class="LABEL"><bean:message key="ppt.label.ld_crr_asgn_mode"/></TH>
        <TD><bean:write name="result" property="ld_crr_asgn_mode"/></TD>
        <TH class="LABEL"><bean:message key="ppt.label.ul_crr_asgn_mode"/></TH>
        <TD><bean:write name="result" property="ul_crr_asgn_mode"/></TD>
      </TR>
			<logic:iterate id="bean" name="subList1">
				<logic:equal name="bean" property="port_id" value="<%=portid%>">
			      <TR>
			        <TH class="LABEL"><bean:message key="ppt.label.port_id"/></TH>
			        <TD><bean:write name="bean" property="port_id"/></TD>			      
			        <TH class="LABEL"><bean:message key="ppt.label.port_stat"/></TH>
			        <TD><bean:write name="bean" property="port_stat"/></TD>
			      </TR>				
			      <TR>					
			        <TH class="LABEL"><bean:message key="ppt.label.agv_mode"/></TH>
			        <TD><bean:write name="bean" property="agv_mode"/></TD>       
						<logic:iterate id="bean1" name="subList3">
							<logic:equal name="bean1" property="port_id" value="<%=portid%>">			        
						        <TH class="LABEL"><bean:message key="ppt.label.rule_templ_id"/></TH>
						        <TD><bean:write name="bean1" property="rule_templ_id"/></TD>
							</logic:equal>
						</logic:iterate>
			      </TR> 
				</logic:equal>
			</logic:iterate>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>
<BR/>
<%-- Port Information --%>
<TABLE class="SECTION">
<TR><TH class="SECTION"><bean:message key="ppt.section.port"/></TH></TR>
<TR>
  <TD class="SECTION">
    <logic:notPresent name="subList1"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
    <logic:present name="subList1">
      <TABLE class="LIST">
      <TR>
    	<TH class="LIST"><bean:message key="ppt.label.port_id"/></TH>
    	<TH class="LIST"><bean:message key="ppt.label.port_stat"/></TH>
    	<TH class="LIST"><bean:message key="ppt.label.agv_mode"/></TH>   
      </TR>
      <logic:iterate id="bean" name="subList1">
	    <TR>
		  <TD class="LIST"><A HREF='<%=request.getContextPath()%>/ppt_eqptDrttList.do?eqpt_id=<bean:write name="result" property="eqpt_id"/>&port_id=<bean:write name="bean" property="port_id"/>'><bean:write name="bean" property="port_id"/></A></TD>
	      <TD class="LIST" align="center"><bean:write name="bean" property="port_stat"/></TD>
	      <TD class="LIST"><bean:write name="bean" property="agv_mode"/></TD>   
	    </TR>
      </logic:iterate>
      </TABLE>
    </logic:present>
  </TD>
</TR>
</TABLE>
<BR/>
<%-- Dispatch Rule Template Test Result  List --%>
<logic:notPresent name="subList2"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
<logic:present name="subList2">
  <TABLE class="LIST">
  <TR>
    <TH class="LIST"><bean:message key="ppt.label.crr_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.crr_stat"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.mask_reason"/></TH>   
    <TH class="LIST"><bean:message key="ppt.label.position"/></TH>  
	<TH class="LIST"><bean:message key="ppt.label.port_id"/></TH>      
    <TH class="LIST"><bean:message key="ppt.label.zone_id"/></TH>    
    <TH class="LIST"><bean:message key="ppt.label.lot_id"/></TH>    
    <TH class="LIST"><bean:message key="ppt.label.product_id"/></TH>    
    <TH class="LIST"><bean:message key="ppt.label.nx_route_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.nx_ope_no"/></TH>     
    <TH class="LIST"><bean:message key="ppt.label.nx_ope_id"/></TH>   
    <TH class="LIST"><bean:message key="ppt.label.prty"/></TH>    
    <TH class="LIST"><bean:message key="ppt.label.sht_cnt"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.qrs_ope_id"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.qrs_date"/></TH>
    <TH class="LIST"><bean:message key="ppt.label.qrs_time"/></TH>    
  </TR>
  <logic:iterate id="bean" name="subList2">
    <TR>
      <TD class="LIST"><bean:write name="bean" property="crr_id"/></TD>
      <TD class="LIST" align="center"><bean:write name="bean" property="crr_stat"/></TD>
      <TD class="LIST"><bean:write name="bean" property="mask_reason"/></TD>   
      <TD class="LIST"><bean:write name="bean" property="position"/></TD>
      <TD class="LIST"><bean:write name="bean" property="port_id"/></TD>   
      <TD class="LIST"><bean:write name="bean" property="zone_id"/></TD>  
      <TD class="LIST"><bean:write name="bean" property="lot_id"/></TD>   
      <TD class="LIST"><bean:write name="bean" property="product_id"/></TD>   
      <TD class="LIST"><bean:write name="bean" property="nx_route_id"/></TD>
      <TD class="LIST"><bean:write name="bean" property="nx_ope_no"/></TD>   
      <TD class="LIST"><bean:write name="bean" property="nx_ope_id"/></TD>        
      <TD class="LIST"><bean:write name="bean" property="priority"/></TD>  
      <TD class="LIST"><bean:write name="bean" property="sht_cnt"/></TD>   
      <TD class="LIST"><bean:write name="bean" property="qrs_ope_id"/></TD>        
      <TD class="LIST"><bean:write name="bean" property="qrs_date"/></TD>  
      <TD class="LIST"><bean:write name="bean" property="qrs_time"/></TD>        
    </TR>
  </logic:iterate>
  </TABLE>
</logic:present>

</tiles:put>
</tiles:insert>