<%@ page language="java" import="java.util.*, state.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>

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

    <script>
        function moreMode(type)
        {
            if(type){
                document.write("<div class='block'>");
                document.write("<a class='inblock' href='/iqds/doquestion?mode=test'><img src='./icons/测验.png' style='height:80px;vertical-align: middle;'>去测验</a></div>");
                document.write("<div class='block'>");
                document.write("<a class='inblock' href='/iqds/doquestion?mode=recite'><img src='./icons/背题.png' style='height:80px;vertical-align: middle;'>去背题</a></div>");
            }
        }
    </script>

</head>

<body>
    <script>header(${manager}, "${headerContent}", ${loginState.type});</script>
    <div class="container">


        <div class="layui-carousel" id="MyCarousel">
            <div carousel-item>
                <div><img src="img/c1.jpg" style="width:100%"></div>
                <div><img src="img/c2.jpg" style="width:100%"></div>
                <div><img src="img/c3.jpg" style="width:100%"></div>
            </div>
        </div>
        <div class="line">
            立即使用！
        </div>
        <div class="blocks">
            <div class="block">
                <a class="inblock" href="/iqds/doquestion?mode=casual"><img src="./icons/做题.png"
                        style="height:80px;vertical-align: middle;">去刷题</a>
            </div>
            <div class="block">
                <a class="inblock" href="/iqds/search.jsp"><img src="./icons/搜索.png"
                        style="height:80px;vertical-align: middle;">去搜题</a>
            </div>
            
            <script>
                moreMode(${loginState.type});
            </script>
        </div>
    </div>

    <script>
            layui.use('carousel', function(){
                var carousel = layui.carousel;
                //建造实例
                carousel.render({
                    elem: '#MyCarousel',
                    width: '100%', //设置容器宽度
                    height: '80%',
                    interval: 5000,
                    arrow: 'always', //始终显示箭头
                    //,anim: 'updown' //切换动画方式
                });
            });
    </script>
</body>

</html>
