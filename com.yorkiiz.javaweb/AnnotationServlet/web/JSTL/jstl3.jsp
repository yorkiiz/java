<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/16
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>foreach</title>
</head>
<body>

<%
    List list = new ArrayList();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
%>

<c:forEach begin="1" end="10" var="1" step="1" varStatus="s">
    ${i}<h3>${s.index}<h4>${s.count}</h4></h3><br>
</c:forEach>

<c:forEach items="${list}" var="str" varStatus="s">
    ${s.index} ${s.count} ${str}<br>
</c:forEach>

</body>
</html>
