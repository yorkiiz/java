<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/16
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    List list = new ArrayList();
    list.add("aaaa");
    request.setAttribute("list",list);
    request.setAttribute("number",3);
%>

<c:if test="${not empty list}">
    遍历集合....
</c:if>

<c:if test="${number%2!=0}">
    ${number}为奇数
</c:if>



</body>
</html>
