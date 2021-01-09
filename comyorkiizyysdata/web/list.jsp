<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        window.onload = function(){
            document.getElementById("delSelect").onclick = function(){

                if(confirm("您确定要删除吗")){
                    var flag = false;
                    var cb = document.getElementsByName("tid");
                    for (var i=0;i<cb.length;i++) {
                        if(cb[i].checked){
                            flag=true;
                            break;

                        }
                    }
                    if(flag){
                        document.getElementById("form").submit();
                    }else {
                        alert("请先选择数据");
                    }

                }


            }

            document.getElementById("cbs").onclick = function(){

                var cb = document.getElementsByName("tid");
                for (var i=0;i<cb.length;i++) {
                    cb[i].checked = this.checked;
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">阴阳师资源信息</h3>

    <div style="float: left;margin: 5px">
        <form class="form-inline" action="${pageContext.request.contextPath}/selectServlet" method="get">
            <div class="form-group">
                <label for="time1">开始时间</label>
                <input type="text" class="form-control" id="time1" name ="time1" placeholder="2020-11-22">
            </div>
            <div class="form-group">
                <label for="time2">结束时间</label>
                <input type="text" class="form-control" id="time2" name="time2" placeholder="2020-12-22">
            </div>
            <div class="form-group">
                <label for="number">序号</label>
                <input type="text" class="form-control" id="number" name="number" placeholder="1">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <div style="float: right;margin: 5px">
        <a class="btn btn-primary" href="add.jsp">添加信息</a>
        <a class="btn btn-primary" href="javascript:void(0)" id="delSelect">删除选中</a>
    </div>
    <form id="form" action="${pageContext.request.contextPath}/delSelectServlet" method="post">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="cbs" ></th>
            <th>时间</th>
            <th>序号</th>
            <th>金币</th>
            <th>体力</th>
            <th>蓝票</th>
            <th>勾玉</th>
            <th>蛇皮</th>
            <th>金蛇皮</th>
            <th>蛇票</th>
            <th>备注</th>
            <th></th>
        </tr>
        <c:forEach items="${users}" var="user" varStatus="s">
            <tr>
                <td><input type="checkbox"  name="tid" value="${user.date}"></td>
                <td>${user.date}</td>
                <td>${user.number}</td>
                <td>${user.gold}</td>
                <td>${user.tili}</td>
                <td>${user.blue_ticket}</td>
                <td>${user.gouyu}</td>
                <td>${user.shepi}</td>
                <td>${user.goldenshepi}</td>
                <td>${user.shepiao}</td>
                <td>${user.comment}</td>
                <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/updateServlet?date=${user.date}&&number=${user.number}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/deleteServlet?date=${user.date}&&number=${user.number}">删除</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="12" align="center"><a class="btn btn-primary" href="add.html">添加信息</a></td>
        </tr>
    </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin: 5px">
                    共16条数据，4页
                </span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
