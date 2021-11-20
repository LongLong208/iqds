<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="beans.*, java.util.*, others.*" %>

<%
    List<Question> questions = null;
    Map<Integer, List<AnswerItem>> map = null;
    String mode = (String) session.getAttribute("mode");
    String title = null;

    if("test".equals(mode))
    {
        title = "限&nbsp;时&nbsp;测&nbsp;试";
        String userId ="" + (int) session.getAttribute("userId");
        TestRecord tr = (TestRecord) application.getAttribute(userId);
        questions = tr.getQuestions();
        map = tr.getMap();
    } else if("casual".equals(mode)) {
        title = "随&nbsp;机&nbsp;练&nbsp;习";
        questions = (List<Question>)session.getAttribute("questions");
        map = (Map<Integer, List<AnswerItem>>)session.getAttribute("answerMap");
    } else if("recite".equals(mode)) {
        title = "背&nbsp;题&nbsp;模&nbsp;式";
        questions = (List<Question>)session.getAttribute("questions");
        map = (Map<Integer, List<AnswerItem>>)session.getAttribute("answerMap");
    } else if("special".equals(mode)){
        questions = (List<Question>)session.getAttribute("questions");
        map = (Map<Integer, List<AnswerItem>>)session.getAttribute("answerMap");
        title = "专&nbsp;项&nbsp;练&nbsp;习：" + questions.get(0).getKnowledgeName();
    }
    String abcd = "ABCD";
%>

<html>

<body>
    <div class="layui-card"><div class="layui-card-header"><h1 style="font-size: 28px; text-align:center"><%=title%></h1></div></div>
    <%
        for(Question qt : questions)
        {
            int qId = qt.getQuestionId();
            List<AnswerItem> aList = map.get(qId);
            %>
                <div class="layui-card" style="margin: 0 25% 5% 25%; padding: 1px">

                <form id="q<%=qId%>">
                    <div class="layui-card-header" style="text-align:center;height: fit-content"><h2><%=qt.getTitle()%></h2><%="special".equals(mode) ? "<button id='q" + qId + "b' type='button' class='layui-btn' onclick=\"checkthis('q" + qId + "')\">提交</button>" : ""%></div>
                <%
                    for(int i = 0; i < aList.size(); ++i)
                    {
                        AnswerItem ai = aList.get(i);
                        %><div class="layui-card-body answerButton" id="a<%=ai.getAnswerItemId()%>" onclick="pick('a<%=ai.getAnswerItemId()%>')">
                        <%
                            if(!"recite".equals(mode))
                            {
                        %>
                                <span id="anspan<%=ai.getAnswerItemId()%>" class="letter"><%= abcd.charAt(i)%></span>
                        <%
                            }
                        %>
                        &nbsp;
                        <input class="answerElement" type="checkbox" name="q<%=qId%>" id="a<%=ai.getAnswerItemId()%>s" value="<%=ai.getAnswerItemId()%>"><%=ai.getTitle()%></div><%
                    }
                %>
                </form>
                </div>
            <%
        }
        if("casual".equals(mode) || "test".equals(mode))
        {
        %>
            <div id="finish" class="layui-card" style="box-shadow: 0 0 0 0 rgb(0 0 0 / 0%);background-color: rgba(1,1,1,0);">
                <div class="layui-card-header" style="text-align: center; width: auto;text-align: center;background-color: rgba(1,1,1,0);"><button type="button" class="layui-btn" style="height: 100%" onclick="checkQuestion(${loginState.type})">提交</button></div>
            </div>
        <%
        }
    %>

</body>

</html>

<%
    session.removeAttribute("question");
    session.removeAttribute("mode");
    session.removeAttribute("answerMap");
%>
