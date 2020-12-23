<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.entity.User" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/16
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List list = new ArrayList();
    list.add(new User("zhangsan","18",new Date()));
    list.add(new User("yongjie","19",new Date()));
    list.add(new User("zhouxu","20",new Date()));
    request.setAttribute("list",list);
%>

<table border="1" align="center">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>日期</th>
    </tr>
    <c:forEach items="${list}" var="user" varStatus="s">
        <tr>
            <td>${s.count}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.date}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
