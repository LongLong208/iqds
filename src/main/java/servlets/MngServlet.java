package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOs.MngDAO;
import others.Page;
import state.AppState;

public class MngServlet extends HttpServlet {

    public MngServlet() {
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
        boolean noAuthority = true;
        HttpSession session = request.getSession();
        AppState loginState = (AppState) session.getAttribute("loginState");
        if (loginState.getType() == 1) {
            if (MngDAO.check((int) session.getAttribute("userId"))) {
                noAuthority = false;
                response.sendRedirect("/iqds/manager.jsp");
            }
        }
        if (noAuthority) {
            Page.redirect(session, response, "/iqds", "", 1, "");
        }
    }

}
