package servlets.MngPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.QuestionDAO;
import beans.Question;

public class QuestionMng extends HttpServlet {

    public QuestionMng() {
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
        List<Question> questionList = QuestionDAO.checkAll();
        request.getSession().setAttribute("questionList", questionList);
        response.sendRedirect("/iqds/pages/questionMng.jsp");
    }
}
