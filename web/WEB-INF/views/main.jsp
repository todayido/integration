<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/24
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String path = request.getContextPath();%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    This is main page...<br>
    <a href="<%=path%>/user/info?username=${username}" >获取用户信息【登录用户即可获取自己的用户信息】</a><br>

    <a href="<%=path%>/user/add">增加用户</a><br>
    <a href="<%=path%>/user/delete">删除用户</a><br>
    <a href="<%=path%>/user/update">修改用户</a><br>
    <a href="<%=path%>/user/get">查询用户</a><br>
    <shiro:hasRole name="admin">
        具有管理员角色的可以看到该信息<br>
    </shiro:hasRole>
    <shiro:hasRole name="login">
        登录用户可以看到该信息<br>
    </shiro:hasRole>
    <form action="<%=path%>/logout" method="post">
        <button  type="submit">注销</button>
    </form>
</body>
</html>
