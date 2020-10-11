<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<html:html>
<HEAD>
<TITLE></TITLE>
<LINK rel="stylesheet" href="<%= request.getContextPath() %>/css/lcd.css" TYPE="text/css">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/commons/script/sortingTable.js"></SCRIPT>
<script language="JavaScript" type="text/JavaScript">
  function ST(colIndex,tableName,flag) {
	sortTable(colIndex, tableName, flag);
  }
  //open JSP windown resizeTo full screen
  //var ADJUST_X = -8;
  //var ADJUST_Y = -57;
  
  window.focus(top); //2003/12/31 HsiaoYa Revise add need window to stay in front(JSP)
  window.moveTo(0,0);  
  //window.resizeTo(screen.width ,screen.height -25);
  
  //open applet window resize to full screen
  function openWindow(url) {

        //var w = window.screen.width + ADJUST_X;
        //var h = window.screen.height + ADJUST_Y;
        <!--var win = window.open("<%= request.getContextPath() %>"+ url, "", "location=no,toolbar=no,menubar=no,status=no,resizable=yes,top=0,left=0,width=" + w + ",height=" + h);        -->
        var win = window.open("<%= request.getContextPath() %>"+ url, "", "location=no,toolbar=no,menubar=no,status=no,resizable=yes,top=0,left=0");
	window.opener = top;
    	window.close();

  }
  
  function setScreenSize() {
    window.moveTo(0,0);
    window.resizeTo(window.screen.availWidth, window.screen.availHeight);
  }

</script>
</HEAD>
<BODY>
<OBJECT id=maximize classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11">
	<param name="Command" value="Maximize">
</OBJECT>
<script>
	if(top.screenLeft > 0) maximize.Click();
</script>
<!-- Display Contents of Page -->
<TABLE border="0" cellspacing="0" cellpadding="0" width="100%">
<TR>
  <TD bgcolor="#ffcc00"><FONT color="#ffffff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<B>MESExpress</B></FONT></TD>
</TR>
<TR><TD><BR/></TD></TR>
<TR>
  <TD bgcolor="#ffffff">
    <tiles:insert attribute="body" />
  </TD>
</TR>
</TABLE>
</BODY>
</html:html>