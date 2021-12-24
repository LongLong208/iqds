<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>
<%@ page import="beans.*, java.util.*" %>

<html>

<body>
    <div>
        <div style="text-align: center"><input id="keyword" type="text" style="width:400px" onchange="searchRow()"></div>
        <table id="right_table" border="1" style="text-align: center">
            <caption>试卷管理</caption>
            <tr>
                <th>试卷ID</th>
                <th>试卷名</th>
                <th><button type="button" class="layui-btn insertbtn" style="color: white; width: 100%;height: 100%;line-height: 45px;" onclick="window.location.href='/iqds/paper.jsp'">添加</button></th>
            </tr>
            <% 
                List<Paper> list = (List<Paper>)session.getAttribute("paperList");
                for(int i = 0; i < list.size(); ++i)
                {
            %>
                    <tr>
                        <td><%=list.get(i).getId()%></td>
                        <td><%=list.get(i).getPaperName()%></td>
                    </tr>
            <%
                }
            %>
        </table>
    </div>
</body>

</html>
