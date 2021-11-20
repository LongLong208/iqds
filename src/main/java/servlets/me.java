package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOs.DoneDAO;
import DAOs.KnowledgeDAO;
import beans.Knowledge;

public class me extends HttpServlet {
    public me() {
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
        // 知识点： 做对数/题总数
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        Map<Integer, Integer> correctq = DoneDAO.correct(userId);
        Map<Integer, Integer> totalq = DoneDAO.total();
        List<Knowledge> knowledges = KnowledgeDAO.checkAll();

        session.setAttribute("correctq", correctq);
        session.setAttribute("totalq", totalq);
        session.setAttribute("knowledges", knowledges);
        response.sendRedirect("/iqds/me.jsp");
    }

}
