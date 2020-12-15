<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/15
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<--%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="home.jsp"%>
<html>
<head>
    <title>home标签</title>
</head>
<body>

<%
    List<String> a = new ArrayList<String>();
    a.add("abc");
    System.out.println(a);
%>

</body>
</html>
