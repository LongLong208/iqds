<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>
<%@ page import="beans.*, java.util.*" %>

<html>

<body>
    <div>
        <div style="text-align: center"><input id="keyword" type="text" style="width:400px" onchange="searchRow()"></div>
        <table id="right_table" border="1" style="text-align:center">
            <caption>用户管理</caption>
            <tr><th>用户ID</th><th>用户名</th><th>用户密码</th><th>管理权限</th></tr>
            <% 
                List<User> userList = (List<User>)session.getAttribute("userList");
                session.removeAttribute("userList");
                for(int i = 0; i < userList.size(); ++i)
                {
            %>
                    <tr>
                        <td><%=userList.get(i).getUserId()%></td>
                        <td><%=userList.get(i).getUserName()%></td>
                        <td><%=userList.get(i).getUserPwd()%></td>
                        <td><%=userList.get(i).isMng()? "有":"无"%></td>
                        <td><a class="layui-btn layui-btn-primary insertbtn" onclick="<%=userList.get(i).getUserId() != (int)session.getAttribute("userId") ? "changePower('user', " + userList.get(i).getUserId() + ")" : "changeFail()" %>" >变更权限</a></td>
                    </tr>
            <%
                }
            %>
        </table>
    </div>
</body>

</html>
