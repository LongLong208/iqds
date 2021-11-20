<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>

<html>

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="others/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="./css/index.css">

    <%-- <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> --%>
    <script src="others/jquery.js"></script>
    <script src="/iqds/others/layer/layer.js"></script>
    <script src="js/header/header.js"></script>
</head>

<body>
    <script>header(${manager}, "${headerContent}", ${loginState.type});</script>
    <div class="container">
        <script>
            layer.alert('${str1}', {
                time: ${time} * 1000
                ,success: function(layero, index){
                    var timeNum = this.time/1000, setText = function(start){
                        layer.title((start ? timeNum : --timeNum) + ' 秒后跳转至${str2}', index);
                    };
                    setText(!0);
                    this.timer = setInterval(setText, 1000);
                    if(timeNum <= 0) clearInterval(this.timer);
                }
                ,end: function(){
                    clearInterval(this.timer);
                    window.location.href = "${url}";
                }
            });
        </script>
    </div>
</body>

</html>
