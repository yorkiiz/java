<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<tiles:insert definition="pptLayout">
<tiles:put name="body" direct="true">

<lcd:breadcrumbing id="com.ibm.lcd.ppt.bc"/>
<lcd:title key="ppt.title.error.trx.timeout"/>
<HR>
<P><bean:message key="ppt.msg.error.trx.timeout"/></P>
</tiles:put>
</tiles:insert>