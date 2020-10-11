<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/lcd.tld" prefix="lcd" %>
<lcd:check forwardKey="security.errorPage.ppt" fabKey="security.fab" systemKey="security.system.ppt" />

<html:html>
<FRAMESET cols="20%,*">
<html:frame frameName="menu" frameborder="0" page="/ppt/mainmenu.jsp" noresize="true"/>
<html:frame frameName="content" frameborder="0" page="/ppt_eqptList.do"/>
</FRAMESET>
</html:html>