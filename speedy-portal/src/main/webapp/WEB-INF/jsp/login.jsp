<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>登录</title>
    <sec:csrfMetaTags/>
</head>
<body>
<form action="/login" method="post">
    <sec:csrfInput/>
    <input type="text" placeholder="username" name="username">
    <input type="password" placeholder="password" name="password">
    <button type="submit">登录</button>
</form>
</body>
</html>
