<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.cfm" fabKey="security.fab" systemKey="security.system.cfm" />

<tiles:insert definition="cfmLayout">
<tiles:put name="body" direct="true">

<lcd:title key="cfm.title.index"/>
<HR>
<html:errors/>
<body onload="window.close()"/>
</tiles:put>
</tiles:insert>