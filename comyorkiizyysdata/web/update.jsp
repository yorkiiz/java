<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/22
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- HTML5文档-->
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
    <title>添加数据</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h3>更新数据</h3></center>
    <form action="${pageContext.request.contextPath}/updateServlet2" method="post">
        <div class="form-group">
            <label for="date">时间：</label>
            <input type="text" class="form-control" id="date" name="date" value="${yysData1.date}"  placeholder="请输入时间">
        </div>

        <div class="form-group">
            <label for="number">编号：</label>
            <input type="text" class="form-control" id="number" name="number" value="${yysData1.number}"  placeholder="请输入编号">
        </div>

        <div class="form-group">
            <label for="gold">金币：</label>
            <input type="text" class="form-control" id="gold" name="gold" value="${yysData1.gold}" placeholder="请输入金币">
        </div>

        <div class="form-group">
            <label for="tili">体力：</label>
            <input type="text" class="form-control" id="tili" name="tili" value="${yysData1.tili}" placeholder="请输入体力">
        </div>

        <div class="form-group">
            <label for="blue_ticket">蓝票：</label>
            <input type="text" class="form-control" id="blue_ticket" name="blue_ticket" value="${yysData1.blue_ticket}" placeholder="请输入蓝票">
        </div>

        <div class="form-group">
            <label for="gouyu">勾玉：</label>
            <input type="text" class="form-control" id="gouyu" name="gouyu" value="${yysData1.gouyu}" placeholder="请输入勾玉">
        </div>

        <div class="form-group">
            <label for="shepi">蛇皮：</label>
            <input type="text" class="form-control" id="shepi" name="shepi" value="${yysData1.shepi}" placeholder="请输入蛇皮">
        </div>

        <div class="form-group">
            <label for="goldenshepi">金蛇皮：</label>
            <input type="text" class="form-control" id="goldenshepi" name="goldenshepi" value="${yysData1.goldenshepi}" placeholder="请输入金蛇皮">
        </div>

        <div class="form-group">
            <label for="shepiao">蛇票：</label>
            <input type="text" class="form-control" id="shepiao" name="shepiao" value="${yysData1.shepiao}" placeholder="请输入蛇票">
        </div>


        <div class="form-group">
            <label for="comment">备注：</label>
            <input type="text" class="form-control" id="comment" name="comment" value="${yysData1.comment}" placeholder="请输入备注">
        </div>


        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" />
        </div>
    </form>
</div>
</body>
</html>
