<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>

<html>

<head>
    <meta charset="utf-8">

    <link rel="stylesheet" type="text/css" href="others/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="./css/index.css">
    <%-- <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> --%>
    <script src="others/jquery.js"></script>
    <script src="others/layui/layui.js"></script>
    <script src="js/header/header.js"></script>
    <script src="/iqds/others/layer/layer.js"></script>
</head>

<body>
    <script>header(${manager}, "${headerContent}", ${loginState.type});</script>
    <div class="container">
        
    <div class="loginform">
        <h2 style="text-align:center;font-size: 30px">用&nbsp;户&nbsp;登&nbsp;录</h2>
        <br>
        <br>
        <form id="loginform" action="/iqds/login" method="post" style="width:fit-content;">
            <table>
            <tr><td>用户名：</td><td><input type="text" name="userName"></td></tr>
            <tr><td>密码：</td><td><input type="password" name="userPwd"></td></tr>
            </table>
            <div style="text-align:center"><button type="submit" class="layui-btn">登录<button></div>
        </form>
    </div>

    </div>
</body>

</html>
