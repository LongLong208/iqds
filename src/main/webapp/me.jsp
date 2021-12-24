<%@ page language="java" import="java.util.*,java.sql.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>
<%@ page import="beans.*, java.util.*" %>

<%
    Map<Integer, Integer> cq = (Map<Integer, Integer>) session.getAttribute("correctq");
    Map<Integer, Integer> tq = (Map<Integer, Integer>) session.getAttribute("totalq");
    List<Knowledge> kls = (List<Knowledge>) session.getAttribute("knowledges");
    session.removeAttribute("correctq");
    session.removeAttribute("totalq");
    session.removeAttribute("knowledges");
%>


<html>

<head>
    <meta charset="utf-8">

    <link rel="stylesheet" type="text/css" href="others/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <%-- <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> --%>
    <script src="others/jquery.js"></script>
    <script src="js/header/header.js"></script>
    <script src="/iqds/others/layer/layer.js"></script>

    <script>
        var barTimes = 30;
        var timersan;
        var barlen = [
            <%
            for(int i = 0; i < kls.size(); ++i){
                int klId = kls.get(i).getKnowledgeId();
                if(!"简答题".equals(kls.get(i).getKnowledgeName()))
                {
                    %><%=100*(cq.containsKey(klId) ? cq.get(klId) : 0) / (tq.containsKey(klId) ? tq.get(klId) : 1)%><%
                    if(i < kls.size() - 1){
                        %>,<%
                    }
                }
            }
            %>
        ];
        function progressmove() {
            var bars = document.getElementsByName("bar");
            timersan = setInterval(function () {
                if (barTimes > 0) {
                    barTimes--;
                    for (var i = 0; i < bars.length; ++i) {
                        bars[i].style["width"] = parseInt(barlen[i] * (30 - barTimes) / 30) + "%";
                    }
                } else {
                    clearInterval(timersan);
                }
            }, 10);
        }
    </script>

</head>

<body>
    <script>header(${manager}, "${headerContent}", ${loginState.type});</script>
    <div class="container" style="background-color: rgb(212 212 212 / 38%);">

        <div class="layui-card"><div class="layui-card-header"><h1 style="font-size: 28px; text-align:center">个人中心</h1></div></div>
        <div class="layui-card" style="margin: 0 25% 5% 25%; padding: 1px">
            <div class="layui-card-header" style="text-align:center;font-size: 26px;height: fit-content">用户:${userName}</div>
            <div class="layui-card-header" style="text-align:center;font-size: 20px;height: fit-content">知识点掌握情况</div>
            <%
                for(Knowledge kl : kls){
                    int klId = kl.getKnowledgeId();
                    if(!"简答题".equals(kl.getKnowledgeName())){
            %>
                        <div class="layui-card-body">
                            <%=kl.getKnowledgeName() %> <a class="layui-btn layui-btn-primary" type="button" href='/iqds/doquestion?mode=special&knowledgeId=<%=klId%>'>专项练习</a>
                            <div class="layui-progress layui-progress-big" lay-showPercent="yes">
                                <div name="bar" class="layui-progress-bar layui-bg-green" style="text-align: center;width:0%"><div style="color: #ff9900;text-shadow: 0px 0px 5px #000000;"><%=cq.containsKey(klId)?cq.get(klId):0%>/<%=tq.get(klId)%></div></div>
                            </div>
                        </div>
            <%
                    }
                }
            %>
        </div>
        <div style="text-align:center"><button type="button" class="layui-btn" onclick="window.location.href='/iqds/wrongquestion'">错题库</button></div>

    <script>progressmove();</script>

    </div>
</body>

</html>
