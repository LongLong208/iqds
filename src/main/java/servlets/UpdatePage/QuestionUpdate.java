package servlets.UpdatePage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.QuestionDAO;
import beans.AnswerItem;
import beans.Question;

public class QuestionUpdate extends HttpServlet {

    public QuestionUpdate() {
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
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        Question question = QuestionDAO.get(questionId);
        List<AnswerItem> answerlist = QuestionDAO.getAnswer(questionId);
        request.setAttribute("question", question);
        request.setAttribute("answers", answerlist);
        request.getRequestDispatcher("/pages/forms/question.jsp").forward(request, response);
    }

}
