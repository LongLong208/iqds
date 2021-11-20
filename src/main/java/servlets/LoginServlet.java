package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DAOs.MngDAO;
import DAOs.UserDAO;
import beans.User;
import others.Page;
import state.AppState;

public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AppState loginState = (AppState) session.getAttribute("loginState");
        if (loginState.getType() == 1) {
            Page.redirect(session, response, "/iqds", "您已登录！", 3, "主页。");
        } else if (loginState.getType() == 0) {
            String userName = request.getParameter("userName");
            String userPwd = request.getParameter("userPwd");
            UserDAO userDAO = new UserDAO();
            if (!userDAO.check(userName, userPwd)) {
                Page.redirect(session, response, "/iqds/login.jsp", "用户名或密码错误！", 1, "登录页面。");
            } else {
                User user = userDAO.getUser();
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userName", user.getUserName());
                session.setAttribute("loginState", loginState.getNext());
                if (MngDAO.check(user.getUserId()))
                    session.setAttribute("manager", "1");
                else
                    session.setAttribute("manager", "0");
                session.setAttribute("headerContent", "<a href='/iqds/me'>用户：" + user.getUserName() + "</a>");
                String title;
                if (request.getParameter("userPwdCon") == null)
                    title = "登录成功！";
                else
                    title = "注册成功！已自动登录！";
                Page.redirect(session, response, "/iqds", title, 3, "主页。");
            }
        }
    }

}
