<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>登陆页面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

</head>

<body>
<h1>用户登录</h1>
<hr>
<div id="container">

    <div id="box">
        <form action="/login" method="post">
            <p class="main">
                <label>用户名: </label>
                <input name="username" value="daiwei" />
                <label>密码: </label>
                <input type="password" name="password" value="123456">
            </p>
            <p class="space">
                <input type="submit" value="登录" class="login" style="cursor: pointer;"/>
            </p>
        </form>
    </div>
</div>

</body>
</html>
