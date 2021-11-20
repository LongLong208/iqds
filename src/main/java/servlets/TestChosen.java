package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.TestRecord;

public class TestChosen extends HttpServlet {

    public TestChosen() {
        super();
    }

    @Override
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
        ServletContext application = this.getServletContext();
        int id = Integer.parseInt(request.getParameter("Id"));
        String userId = "" + (int) session.getAttribute("userId");
        TestRecord tr = (TestRecord) application.getAttribute(userId);
        List<Integer> list = tr.getChosen();
        if (!list.contains(id))
            list.add(id);
        tr.setChosen(list);
    }
}
