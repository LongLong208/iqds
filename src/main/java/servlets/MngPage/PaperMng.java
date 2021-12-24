package servlets.MngPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.PaperDAO;
import beans.Paper;

public class PaperMng extends HttpServlet {

    public PaperMng() {
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
        List<Paper> list = PaperDAO.queryAll();
        request.getSession().setAttribute("paperList", list);
        response.sendRedirect("/iqds/pages/papermng.jsp");
    }
}
