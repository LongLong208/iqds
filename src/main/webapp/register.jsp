<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>

<head>
    <meta charset="utf-8">
    <title>Java Web面试题库系统</title>
    <script src="others/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="others/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="./css/index.css">
    <%-- <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> --%>
    <script src="others/layui/layui.js"></script>
    <script src="js/header/header.js"></script>
    <script src="/iqds/others/layer/layer.js"></script>

    <script>
        function checkAll(){
            return checkUserName() && checkPwd() && checkPwdCon();
        }
        function checkUserName() {
            var userName = rgform.userName.value;
            if(userName == "") return false;
            if(userNameOk(userName) == false){
                layer.tips('用户名以字母开头，由字母或数字组成！', '#Name', {
                            tips: [2, '#fe6d68'],
                            time: 2000
                        });
                return false;
            }
            var nameOk = false;
            $.ajax({
                type: "POST",
                dataType: "text",
                url: "/iqds/checkusername?userName=" + userName,
                async: false, 
                data: "",
                success: function (data) {
                    if(data == "false"){
                        layer.tips('用户名已被使用！', '#Name', {
                            tips: [2, '#fe6d68'],
                            time: 2000
                        });
                        nameOk = false;
                    } else {
                        layer.tips('用户名可用！', '#Name', {
                            tips: [2, '#16c4af'],
                            time: 2000
                        });
                        nameOk = true;
                    }
                },
                error : function() {
                    layer.msg('异常');
                }
            });
            return nameOk;
        }
        function userNameOk(name){
            if(!(name[0] >= 'A' && name[0] <= 'Z' || name[0] >= 'a' && name[0] <= 'z'))
                return false;
            for(var i = 1; i < name.length; ++i){
                if(!(name[i] >= 'A' && name[i] <= 'Z' || name[i] >= 'a' && name[i] <= 'z' || name[i] >= '0' && name[i] <='9'))
                    return false;
            }
            return true;
        }
        function pwdOk(pwd){
            for(var i = 0; i < pwd.length; ++i){
                if(!(pwd[i] >= 'A' && pwd[i] <= 'Z' || pwd[i] >= 'a' && pwd[i] <= 'z' || pwd[i] >= '0' && pwd[i] <='9'))
                    return false;
            }
            return true;
        }
        function checkPwd(){
            var userpwd = rgform.userPwd.value;
            if(pwdOk(userpwd) == false){
                layer.tips('密码由数字或字母组成！', '#pwd', {
                    tips: [2, '#fe6d68'],
                    time: 2000
                });
                return false;
            }
            return true;
        }
        function checkPwdCon(){
            if(rgform.userPwd.value != rgform.userPwdCon.value){
                layer.tips('密码必须一致！', '#pwdcon', {
                    tips: [2, '#fe6d68'],
                    time: 2000
                });
                return false;
            }
            return true;
        }
    </script>
</head>

<body>
    <script>header(${manager}, "${headerContent}", ${loginState.type});</script>
    <div class="container">
        
        <div class="loginform">
            <h2 style="text-align:center;font-size: 30px">用&nbsp;户&nbsp;注&nbsp;册</h2>
            <br>
            <br>
            <form id="rgform" action="/iqds/register" method="post" style="width:fit-content;" onsubmit='return checkAll()'>
                <table>
                <tr ><td>用户名：</td><td><input id="Name" type="text" name="userName" onblur="checkUserName()"></td></tr>
                <tr><td>密码：</td><td><input id="pwd" type="password" name="userPwd" onblur="checkPwd()"></td></tr>
                <tr><td>重复密码：</td><td><input id="pwdcon" type="password" name="userPwdCon" onblur="checkPwdCon()"></td></tr>
                </table>
                <div style="text-align:center"><button type="sumbit" class="layui-btn" >注册<button></div>
            </form>
        </div>

    </div>
</body>

</html>
