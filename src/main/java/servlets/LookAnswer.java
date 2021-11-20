package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.QuestionDAO;
import beans.AnswerItem;

public class LookAnswer extends HttpServlet {
    public LookAnswer() {
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
        PrintWriter out = response.getWriter();
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        List<AnswerItem> list = QuestionDAO.lookAnswer(questionId);
        out.print("<ul>");
        for (AnswerItem ai : list) {
            out.print("<li style=\"list-style: disc outside none\">" + ai.getTitle() + "</li><br>");
        }
        out.print("</ul>");
    }

}
