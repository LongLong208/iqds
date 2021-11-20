<%@ page language="java" import="java.util.*,java.sql.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>

<html>

<head>
    <meta charset="utf-8">
    <title>Java Web面试题库系统</title>
    <link rel="stylesheet" type="text/css" href="./css/index.css">
    <%-- <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> --%>
    <script src="others/jquery.js"></script>
    <script src="js/header/header.js"></script>

</head>

<body>
    <script>header(${manager}, "${headerContent}", ${loginState.type});</script>
    <div class="container">


        <div class="content" style="font-size: 30px;">
            <img src="./icons/搜索.png" style="height: 60px;vertical-align: middle;">题目搜索<br>
            <form id="search" action="/iqds/search" method="post" onsubmit="if(search.key.value == '')return false;">
                <input type="text" style="width:400px" name="key">
                <input type="submit" value="搜索" style="width:60px">
            </form>
        </div>

    </div>
</body>

</html>
