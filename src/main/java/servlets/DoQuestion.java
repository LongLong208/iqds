package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOs.QuestionDAO;
import beans.AnswerItem;
import beans.Question;
import beans.TestRecord;
import others.Randoms;

public class DoQuestion extends HttpServlet {
    public DoQuestion() {
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
        String mode = request.getParameter("mode");
        HttpSession session = request.getSession();
        session.setAttribute("mode", mode);
        session.setAttribute("lastMode", mode);

        int quNum = 5;
        ServletContext application = this.getServletContext();

        Map<Integer, List<AnswerItem>> map = new HashMap<>();
        List<Question> questions = new ArrayList<Question>();
        List<Question> questionAll;
        int[] randoms;
        switch (mode) {
            case "test":
                quNum = 10;
                String userId = "" + (int) session.getAttribute("userId");
                TestRecord tr = (TestRecord) application.getAttribute(userId);
                if (tr != null) {
                    Date now = new Date();
                    long nows = now.getTime();
                    long starts = tr.getTime().getTime();
                    if (nows - starts <= 3 * 1000 * 60) {
                        response.sendRedirect("/iqds/doquestion.jsp");
                        return;
                    } else {
                        application.removeAttribute(userId);
                    }
                }

                questionAll = QuestionDAO.checkAll(0);
                randoms = Randoms.getRandoms(questionAll.size());

                for (int i = 0; i < quNum; ++i) {
                    questions.add(questionAll.get(randoms[i]));
                }
                for (Question qt : questions) {
                    List<AnswerItem> aList = QuestionDAO.getAnswer(qt.getQuestionId());
                    map.put(qt.getQuestionId(), Randoms.randoms(aList));
                }
                List<Integer> chosen = new ArrayList<>();
                tr = new TestRecord(questions, map);
                tr.setChosen(chosen);
                application.setAttribute(userId, tr);
                response.sendRedirect("/iqds/doquestion.jsp");
                break;
            case "casual":
                quNum = 5;
                questionAll = QuestionDAO.checkAll(0);
                randoms = Randoms.getRandoms(questionAll.size());

                for (int i = 0; i < quNum; ++i) {
                    questions.add(questionAll.get(randoms[i]));
                }
                for (Question qt : questions) {
                    List<AnswerItem> aList = QuestionDAO.getAnswer(qt.getQuestionId());
                    map.put(qt.getQuestionId(), Randoms.randoms(aList));
                }

                session.setAttribute("questions", questions);
                session.setAttribute("answerMap", map);
                response.sendRedirect("/iqds/doquestion.jsp");
                break;
            case "recite":
                questionAll = QuestionDAO.checkAll(1);

                for (Question qt : questionAll) {
                    List<AnswerItem> aList = QuestionDAO.getAnswer(qt.getQuestionId());
                    map.put(qt.getQuestionId(), aList);
                }

                session.setAttribute("questions", questionAll);
                session.setAttribute("answerMap", map);
                response.sendRedirect("/iqds/doquestion.jsp");
                break;
            case "special":
                String klIdO = request.getParameter("knowledgeId");
                if (klIdO == null) {
                    klIdO = (String) session.getAttribute("lastKlId");
                }
                session.setAttribute("lastKlId", klIdO);
                int knowledgeId = Integer.parseInt(klIdO);

                questionAll = QuestionDAO.checkAll(0, knowledgeId);

                for (Question qt : questionAll) {
                    List<AnswerItem> aList = QuestionDAO.getAnswer(qt.getQuestionId());
                    map.put(qt.getQuestionId(), aList);
                }

                session.setAttribute("questions", questionAll);
                session.setAttribute("answerMap", map);
                response.sendRedirect("/iqds/doquestion.jsp");
                break;
        }

    }
}
