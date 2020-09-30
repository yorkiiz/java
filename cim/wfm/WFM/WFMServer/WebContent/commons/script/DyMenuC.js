var menuShown = false;
function showMenu(obj)
{
	PRE.style.visibility="visible";
	NEXT.style.visibility="visible";
	MAIN2.style.visibility="visible";
	eval( obj + '.style.visibility="visible";' );
}
function hideMenu()
{
	PRE.style.visibility="hidden";
	NEXT.style.visibility="hidden";
	MAIN2.style.visibility="hidden";
	PRE2.style.visibility="hidden";
	NEXT2.style.visibility="hidden";
}

var menu1 = new Array();
var menu2 = new Array();
var menu3 = new Array();
var menu4 = new Array();
var menu5 = new Array();
var cellWidth = 106;
var cellHeight = 18;
var cellSpacing = 2;
var menuDistance = cellHeight + cellSpacing;
var menu1Top = 10 + menuDistance;
var menu2Top = 10;
var menu3Top = 10 + menuDistance;
var menu1Left = 10;
var menu2Left = 10;
var menu3Left = 10;
var menu1Height = 0;
var menu2Length = 0;
var menu3Height = 0;
var menu1Clip = 0;
var menu3Clip = 0;
var menu1CurrentY = 0;
var menu3CurrentY = 0;
var posFlag = new Array(0, 0, 0);
var upTag = "UP";
var currentTag = "CURRENT";
var downTag = "DOWN";

function addItem(dispString, urlString, levIndex)
{
	var node = new Array(dispString, urlString);
	eval("menu" + levIndex + "[menu" + levIndex + ".length] = node;");
	if (levIndex==2)
		eval("menu" + levIndex + "Length += cellWidth;");
	else if (levIndex==4)
		menu3Height += cellHeight;
	else if (levIndex==5)
		menu2Length += cellWidth;
	else
		eval("menu" + levIndex + "Height += cellHeight;");
}

function writeMenu()
{
	if (menu1.length==0)
		addItem("","","1");
	
	if (menu1.length>0)
	{
		document.write("<DIV id=MENU1 style='z-index:5; visibility:hidden; position:absolute; top:" + menu1Top + "; height:" + cellHeight + " ; left:" + menu1Left + ";'>");
		document.write("<table border=0 cellpadding=0 cellspacing=" + cellSpacing + " onMouseOver='SC(1,1)' onMouseLeave='SC(1,0);checkMenu(1)'>");
		for (i=0; i<menu1.length; i++)
		{
			if (menu1[i][1]=="")
			{
				document.write("<tr><td nowrap class='w-menu2' width=" + cellWidth + " align=center>none</td></tr>");
			}
			else
			{
				document.write("<tr><td nowrap class='w-menu1' width=" + cellWidth + "><img src='image/icon_30.gif' width=7 height=8 hspace=2>");
				document.write("<A HREF='" + menu1[i][1] + "'>" + menu1[i][0] + "</A>");
				document.write("</td></tr>");
			}
		}
		document.write("</table></DIV>");
	}

	document.write("<DIV id=MENU2 style='z-index:6; text-align:center; position:absolute; top:" + menu2Top + "; left:" + menu2Left + "; height:" + cellHeight + "'>");
	document.write("<table border=0 cellpadding=0 cellspacing=" + cellSpacing + "><tr height=" + cellHeight + ">");
	document.write("<td nowrap class='pro-menu' align=center width=" + cellWidth + " onMouseOver='SC(1,1);showMainMenu(1)' onMouseLeave='SC(1,0);checkMenu(1)'><img src='image/icon_ul.gif' align=left width=9 height=9 border=0>" + upTag + "&nbsp;&nbsp;</td>");
	if (menu2.length>0)
	{
		for (i=0; i<menu2.length; i++)
		{
			document.write("<td nowrap class='w-menu2' width=" + cellWidth + "><img src='image/icon_30.gif' width=7 height=8 hspace=2>");
			document.write("<A HREF='" + menu2[i][1] + "'>" + menu2[i][0] + "</A>");
			document.write("</td>");
		}
	}
	if (menu5.length>0)
	{
		for (i=0; i<menu5.length; i++)
		{
			document.write("<td nowrap class='w-menu5' width=" + cellWidth + "><img src='image/icon_30.gif' width=7 height=8 hspace=2>");
			document.write("<A HREF='" + menu5[i][1] + "'>" + menu5[i][0] + "</A>");
			document.write("</td>");
		}
	}
	document.write("<td nowrap align=center class='pro-menu' width=" + cellWidth + " onMouseOver='SC(3,1);showMainMenu(3)' onMouseLeave='SC(3,0);checkMenu(3)'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + downTag + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='image/icon_dl.gif' width=9 height=9 border=0></td>");
	document.write("</tr>");
	document.write("</table></DIV>");

	if (menu3.length==0)
		addItem("","","3");

	if (menu3.length>0 || menu4.length>0)
	{
		document.write("<DIV id=MENU3 style='z-index:4; visibility:hidden; position:absolute; top:" + menu3Top + "; left:" + (menu3Left+(cellWidth+cellSpacing+8)*(1+menu2.length+menu5.length)) + "; height:" + cellHeight + "'>");
		document.write("<table border=0 cellpadding=0 cellspacing=" + cellSpacing + " onMouseOver='SC(3,1)' onMouseLeave='SC(3,0);checkMenu(3)'>");
		for (i=0; i<menu3.length; i++)
		{
			if (menu3[i][1]=="")
			{
				document.write("<tr><td nowrap class='w-menu2' width=" + cellWidth + " align=center>none</td></tr>");
			}
			else
			{
				document.write("<tr><td nowrap class='w-menu3' width=" + cellWidth + "><img src='image/icon_30.gif' width=7 height=8 hspace=2>");
				document.write("<A HREF='" + menu3[i][1] + "'>" + menu3[i][0] + "</A>");
				document.write("</td></tr>");
			}
		}
		for (i=0; i<menu4.length; i++)
		{
			document.write("<tr><td nowrap class='w-menu4' width=" + cellWidth + "><img src='image/icon_30.gif' width=7 height=8 hspace=2>");
			document.write("<A HREF='" + menu4[i][1] + "'>" + menu4[i][0] + "</A>");
			document.write("</td></tr>");
		}
		document.write("</table></DIV>");
	}
}

function showMainMenu(index)
{
	if ( eval("menu" + index + ".length>0") )
	{
		if (eval("MENU" + index + ".style.visibility != 'visible'"))
		{
			eval("menu" + index + "CurrentY = menu" + index + "Top - menu" + index + "Height;");
			eval("MENU" + index + ".style.top = menu" + index + "CurrentY;");
			eval("MENU" + index + ".style.visibility = 'visible';");
			eval("menu" + index + "Clip = menu" + index + "Height;");
			//if (index==3 && menu3.length>5 && CTB)
			//	CTB.style.visibility="hidden";
			moveMainMenu(index);
		}
	}
}

function moveMainMenu(index)
{
	eval("menu" + index + "CurrentY += 2;");
	eval("MENU" + index + ".style.top = menu" + index + "CurrentY;");
	var clipLength = eval("menu" + index + "Clip -= 2;");
	eval("MENU" + index + ".style.clip = 'rect(" + clipLength + " auto auto auto)';");
	if (eval("menu" + index + "CurrentY<menu" + index + "Top"))
	{
		setTimeout("moveMainMenu(" + index + ");", 10);
	}
	else
	{
		eval("MENU" + index + ".style.top = menu" + index + "Top;");
		eval("MENU" + index + ".style.clip = 'rect(auto auto auto auot)';");
	}
}

function hideMainMenu(index)
{
	if (posFlag[index]==0)
		eval("MENU" + index + ".style.visibility = 'hidden';");
	//if (CTB) 
	//	CTB.style.visibility="visible";
}

function checkMenu(index)
{
	setTimeout("hideMainMenu(" + index + ");", 250);
}

function SC(pos, status)
{
	posFlag[pos] = status;
}
