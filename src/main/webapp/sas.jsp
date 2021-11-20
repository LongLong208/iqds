<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>
<%@ page import="beans.*, java.util.*, others.*" %>

<%
    List<Question> questions = null;
    Map<Integer, List<AnswerItem>> map = null;
    questions = (List<Question>)session.getAttribute("questions");
    map = (Map<Integer, List<AnswerItem>>)session.getAttribute("answerMap");
    Map<Integer, Double> rates = (Map<Integer,Double>)session.getAttribute("rates");
    String abcd = "ABCD";
    String mode = (String)session.getAttribute("wrongMode");
    session.removeAttribute("questions");
    session.removeAttribute("answerMap");
    session.removeAttribute("rates");
    session.removeAttribute("wrongMode");
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

</head>

<body>
    <script>header(${manager}, "${headerContent}", ${loginState.type});</script>
    <div class="container" style="background-color: rgb(212 212 212 / 38%);">
        <%if(!"true".equals(mode)){%>
        <div style="text-align: center;">
            <img src="./icons/搜索.png" style="height: 60px;vertical-align: middle;">题目搜索
            <form id="search" action="/iqds/search" method="post" onsubmit="if(search.key.value == '')return false;">
                <input type="text" style="width:400px" name="key">
                <input type="submit" value="搜索" style="width:60px">
            </form>
        </div>
        <%
        }
        else
        {   
            %>
                <div style="text-align: center;">
                    <img src="./icons/搜索.png" style="height: 60px;vertical-align: middle;">错题复习
                </div>
            <%
        }
        for(Question qt : questions)
        {
            int qId = qt.getQuestionId();
            List<AnswerItem> aList = map.get(qId);
            %>
                <div class="layui-card" style="margin: 0 25% 5% 25%; padding: 1px">
                        <div class="layui-card-header" style="text-align:center;height: fit-content"><h2><%=qt.getTitle()%></h2>
                            <%
                                if("true".equals(mode))
                                {
                                    %>
                                        <div>正确率：<%=rates.get(qId) %>%</div>
                                    <%
                                }
                            %>
                        </div>
                    <%
                        for(int i = 0; i < aList.size(); ++i)
                        {
                            AnswerItem ai = aList.get(i);
                            %><div class="layui-card-body">
                            <%if(qt.getQuestionType() == 0){%>
                                <span><%= abcd.charAt(i)%></span>
                            <%}%>
                            &nbsp;
                                <span><%=ai.getTitle()%></span>
                            </div><%
                        }
                        %>
                            <%if(qt.getQuestionType() == 0){%>
                                <div class="layui-card-body">答案：
                                    <%
                                        for(int i = 0; i < aList.size(); ++i)
                                        {
                                            AnswerItem ai = aList.get(i);
                                            if(ai.isTrue()){
                                                %><%=(char)('A' + i) %><%
                                            }
                                        }
                                    %>
                                </div>
                            <%}%>
                        <%
                    %>
                </div>
            <%
        }
    %>

    </div>
</body>

</html>
