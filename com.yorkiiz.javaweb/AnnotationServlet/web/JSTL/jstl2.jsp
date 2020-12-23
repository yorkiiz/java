<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/16
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>choose标签</title>
</head>
<body>

<%
    request.setAttribute("number",3);
%>
<c:when test="${number==1}">星期一</c:when>
<c:when test="${number==2}">星期二</c:when>
<c:when test="${number==3}">星期三</c:when>
<c:when test="${number==4}">星期四</c:when>
<c:when test="${number==5}">星期五</c:when>
<c:when test="${number==6}">星期六</c:when>
<c:when test="${number==7}">星期日</c:when>


</body>
</html>
