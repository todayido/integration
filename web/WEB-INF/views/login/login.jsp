<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/28
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<html>
<head>
    <title>Login</title>
</head>
<body>
    ${message}
    <form action="<%=path%>/login" method="post">
        用户名：<input type="text" name="username"><br>
        密  码：<input type="text" name="password"><br>
        <input type="submit" value="提交" />
    </form>
</body>
</html>
