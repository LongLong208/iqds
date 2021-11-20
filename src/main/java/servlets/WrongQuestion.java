package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOs.DoneDAO;
import DAOs.QuestionDAO;
import beans.AnswerItem;
import beans.Question;

public class WrongQuestion extends HttpServlet {

    public WrongQuestion() {
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
        int userId = (int) session.getAttribute("userId");
        List<Question> qList = DoneDAO.getWrong(userId);
        Map<Integer, List<AnswerItem>> map = new HashMap<>();
        Map<Integer, Integer> cRates = new HashMap<>();
        for (Question qt : qList) {
            int r = DoneDAO.getRate(qt.getQuestionId(), userId);
            List<AnswerItem> aList = QuestionDAO.getAnswer(qt.getQuestionId());
            map.put(qt.getQuestionId(), aList);
            cRates.put(qt.getQuestionId(), r);
        }
        session.setAttribute("questions", qList);
        session.setAttribute("answerMap", map);
        session.setAttribute("rates", cRates);
        session.setAttribute("wrongMode", "true");
        response.sendRedirect("/iqds/sas.jsp");
    }
}
