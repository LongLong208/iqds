<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>
<%@ page import="beans.*, java.util.*" %>

<html>

<body>
    <div>
        <div style="text-align: center"><input id="keyword" type="text" style="width:400px" onchange="searchRow()"></div>
        <table id="right_table" border="1" style="text-align: center">
            <caption>题库管理</caption>
            <tr><th>题目ID</th><th>题目</th><th>题目类型</th><th>知识点</th><th colspan='3'><button type="button" class="layui-btn insertbtn" style="color: white; width: 100%;height: 100%;line-height: 45px;" onclick="InsertNew('question')">添加</button></th></tr>
            <% 
                List<Question> questionList = (List<Question>)session.getAttribute("questionList");
                for(int i = 0; i < questionList.size(); ++i)
                {
            %>
                    <tr>
                        <td><%=questionList.get(i).getQuestionId()%></td>
                        <td><%=questionList.get(i).getTitle()%></td>
                        <td><%=questionList.get(i).getQuestionType() == 0 ? "选择题":"简答题"%></td>
                        <td><%=questionList.get(i).getKnowledgeName()%></td>
                        <td><a class="layui-btn layui-btn-primary insertbtn" id="la<%=questionList.get(i).getQuestionId()%>" onclick="LookAnswer(<%=questionList.get(i).getQuestionId()%>)">查看答案</a></td>
                        <td><a class="layui-btn layui-btn-primary insertbtn" onclick="Update('question',<%=questionList.get(i).getQuestionId()%>)">详细/修改</a></td>
                        <td><a class="layui-btn layui-btn-primary insertbtn" onclick="Delete('question',<%=questionList.get(i).getQuestionId()%>)">删除</a></td>
                    </tr>
            <%
                }
            %>
        </table>
    </div>
</body>

</html>
