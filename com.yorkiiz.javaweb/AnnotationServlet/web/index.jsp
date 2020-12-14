<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/14
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home jsp</title>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    Boolean cookieflg = false;
    if (cookies!=null&&cookies.length>0){
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if(name.equals("lastdate")){
                String value = cookie.getValue();
                System.out.println("解码前---------："+value);
                value = URLDecoder.decode(value,"utf-8");
                System.out.println("解码后---------："+value);

    %>
                <h1>欢迎回来，您上次的访问时间为<%=value%></h1>
<%
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                String str_date = sdf.format(date);
                System.out.println("编码前----------："+str_date);
                str_date= URLEncoder.encode(str_date,"UTF-8");
                System.out.println("编码后---------："+str_date);
                cookie.setValue(str_date);
                cookie.setMaxAge(60*60*24);
                response.addCookie(cookie);
                cookieflg = true;
                break;

            }
        }
    }

    if(!cookieflg){
        Cookie cookie = new Cookie("lastdate", "");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String str_date = sdf.format(date);
        System.out.println("编码前==========："+str_date);
        str_date=URLEncoder.encode(str_date,"UTF-8");
        System.out.println("编码后==========："+str_date);
        cookie.setValue(str_date);
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        System.out.println("解码前============："+str_date);
        str_date = URLDecoder.decode(str_date,"utf-8");
        System.out.println("解码后============："+str_date);

    %>
        <h1>欢迎初次访问回来<%=str_date%></h1>
<%
    }
%>
</body>
</html>
