<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />

<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">
<LINK rel="stylesheet" href="/trytftcfm/css/subconf.css" TYPE="text/css">
<body onload="setScreenSize()"/>
<lcd:title key="ppt.title.eqptInfo.jpn"/> <bean:write name="eqpt_id" /><BR/>
<HR>
<TABLE class="SECTION">
<TR>
    <TH class="SECTION" id="eqptInfoSec1"><bean:message key="ppt.section.eqptinfo.machineInfo.jpn"/></TH>
</TR>
<TR>
	<TD class="SECTION">
		<logic:notPresent name="eqptstatinfo"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
		<logic:present name="eqptstatinfo">
			<TABLE class="EQPT">
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.dsc.jpn"/></nobr></TH>
					<TD class="EQPT1"><bean:write name="eqptstatinfo" property="eqpt_dsc"/></TD>					
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.rooteqpt.jpn"/></nobr></TH>
					<TD class="EQPT1"><bean:write name="eqptstatinfo" property="root_eqpt_id"/></TD>
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.paraeqpt.jpn"/></nobr></TH>
					<TD class="EQPT1"><bean:write name="eqptstatinfo" property="prt_eqpt_id"/></TD>
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.type.jpn"/></nobr></TH>
					<!-- <TD class="EQPT1"><bean:write name="eqptstatinfo" property="eqpt_typ"/></TD>  -->				
					<TD class="EQPT1">
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="A">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.agv"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="L">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.lim"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="M">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.measure"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="P">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.proc"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="T">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.tester"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="S">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.stocker"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="R">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.proc"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="U">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.ship"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="V">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.mqc"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="W">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.startbank"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="X">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.endbank"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="Y">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.wipbank"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_typ" value="Z">
							<nobr><bean:message key="ppt.stat.eqptinfo.eqpttype.scrapbank"/></nobr>
						</logic:equal>
					</TD>					 
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.subtype.jpn"/></nobr></TH>
					<TD class="EQPT1">
						<logic:equal name="eqptstatinfo" property="eqpt_subtyp" value="">
							<nobr><bean:message key="ppt.stat.eqptinfo.subeqpttype.gen"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_subtyp" value="G">
							<nobr><bean:message key="ppt.stat.eqptinfo.subeqpttype.checker"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_subtyp" value="H">
							<nobr><bean:message key="ppt.stat.eqptinfo.subeqpttype.specifystepper"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_subtyp" value="C">
							<nobr><bean:message key="ppt.stat.eqptinfo.subeqpttype.stepper"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_subtyp" value="A">
							<nobr><bean:message key="ppt.stat.eqptinfo.subeqpttype.array"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_subtyp" value="L">
							<nobr><bean:message key="ppt.stat.eqptinfo.subeqpttype.laser"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_subtyp" value="V">
							<nobr><bean:message key="ppt.stat.eqptinfo.subeqpttype.visual"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_subtyp" value="R">
							<nobr><bean:message key="ppt.stat.eqptinfo.subeqpttype.reticle"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_subtyp" value="I">
							<nobr><bean:message key="ppt.stat.eqptinfo.subeqpttype.clear"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_subtyp" value="X">
							<nobr><bean:message key="ppt.stat.eqptinfo.subeqpttype.dummy"/></nobr>
						</logic:equal>
					</TD>	
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.segment.jpn"/></nobr></TH>
					<TD class="EQPT1"><bean:write name="eqptstatinfo" property="bay_id"/></TD>
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.flg_crr_asgn_mode.jpn"/></nobr></TH>
					<TD class="EQPT1"><bean:write name="eqptstatinfo" property="floor_code"/></TD>
				</TR>
			</TABLE>
		</logic:present>
	</TD>
</TR>
</TABLE>
<TABLE class="SECTION">
<TR>
    <TH class="SECTION" id="eqptInfoSec1"><bean:message key="ppt.section.eqptinfo.machineStat.jpn"/></TH>
</TR>
<TR>
	<TD class="SECTION">
		<logic:notPresent name="eqptstatinfo"><bean:message key="ppt.msg.nodata"/></logic:notPresent>
		<logic:present name="eqptstatinfo">
			<TABLE class="EQPT">
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.stat.jpn"/></nobr></TH>
					<TD class="EQPT1"><bean:write name="eqptstatinfo" property="eqpt_stat"/></TD>
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.substat.jpn"/></nobr></TH>
					<TD class="EQPT1"><bean:write name="eqptstatinfo" property="eqpt_sub_stat"/></TD>
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.opermode.jpn"/></nobr></TH>
					<TD class="EQPT1"><bean:write name="eqptstatinfo" property="eqpt_mode"/></TD>
					<!-- 
					<TD class="EQPT1">
						<logic:equal name="eqptstatinfo" property="eqpt_mode" value="CONT">
							<nobr><bean:message key="ppt.stat.eqptinfo.secs.online.jpn"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_mode" value="MANU">
							<nobr><bean:message key="ppt.stat.eqptinfo.secs.offline.jpn"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="eqpt_mode" value="MONI">
							<nobr><bean:message key="ppt.stat.eqptinfo.secs.monitor"/></nobr>
						</logic:equal>
					</TD>
					 -->
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.flg_lot_out.jpn"/></nobr></TH>
					<TD class="EQPT1"><bean:write name="eqptstatinfo" property="ld_crr_asgn_mode"/></TD>
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.id.jpn"/></nobr></TH>
					<TD class="EQPT1"><bean:write name="eqptstatinfo" property="cr_recipe_id"/></TD>
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.prestat.jpn"/></nobr></TH>
					<TD class="EQPT1"><bean:write name="eqptstatinfo" property="pv_eqpt_sub_stat"/></TD>
				</TR>
				<TR>
					<TH class="EQPT" id="eqptInfoHead1"><nobr><bean:message key="ppt.label.eqptinfo.machine.flg_crr_asgn_mode.jpn"/></nobr></TH>
					<TD class="EQPT1">
						<logic:equal name="eqptstatinfo" property="ld_crr_asgn_mode" value="Y">
							<nobr><bean:message key="ppt.stat.eqptinfo.lotflag.auto.jpn"/></nobr>
						</logic:equal>
						<logic:equal name="eqptstatinfo" property="ld_crr_asgn_mode" value="N">
							<nobr><bean:message key="ppt.stat.eqptinfo.lotflag.ope.jpn"/></nobr>
						</logic:equal>
					</TD>
				</TR>
			</TABLE>
		</logic:present>
	</TD>
</TR>
</TABLE>
<BR/>
<TABLE class="SECTION">
<TR><TH class="SECTION"><bean:message key="ppt.section.eqptinfo.everyStat.jpn"/></TH></TR>
<TR>
	<TD class="SECTION">
		<logic:present name="portinfo">
            <TABLE class="EQPT">
				<TR>
					<TH class="EQPT" id="eqptInfoHead3"><bean:message key="ppt.label.eqptinfo.stat.port.jpn"/></TH>
					<TH class="EQPT" id="eqptInfoHead3"><bean:message key="ppt.label.eqptinfo.stat.porttype"/></TH>
					<TH class="EQPT" id="eqptInfoHead3"><bean:message key="ppt.label.eqptinfo.stat.status.jpn"/></TH>
					<TH class="EQPT" id="eqptInfoHead3">Port Change Date</TH>
				</TR>
				<logic:iterate id="q1" name="portinfo">
					<TR>
						<TD class="EQPT2"><bean:write name="q1" property="port_id"/></TD>
						<TD class="EQPT2"><bean:write name="q1" property="port_typ"/></TD>
						<TD class="EQPT2"><bean:write name="q1" property="port_stat"/></TD>
						<TD class="EQPT2"><bean:write name="q1" property="port_date"/></TD>
					</TR>
				</logic:iterate>
			</TABLE>
		</logic:present>
		<logic:notPresent name="estatinfo">
			<logic:notPresent name="portinfo">
				<bean:message key="ppt.msg.nodata"/>
			</logic:notPresent>
		</logic:notPresent>
	</TD>
</TR>
</TABLE>
<BR/>
</tiles:put>
</tiles:insert>