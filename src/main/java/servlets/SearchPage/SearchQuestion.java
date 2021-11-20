package servlets.SearchPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOs.QuestionDAO;
import beans.AnswerItem;
import beans.Question;

public class SearchQuestion extends HttpServlet {

    public SearchQuestion() {
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
        String key = request.getParameter("key");
        List<Question> qts = QuestionDAO.check(key);
        Map<Integer, List<AnswerItem>> map = new HashMap<>();
        for (int i = 0; i < qts.size(); ++i) {
            List<AnswerItem> ans = QuestionDAO.getAnswer(qts.get(i).getQuestionId());
            map.put(qts.get(i).getQuestionId(), ans);
        }
        HttpSession session = request.getSession();
        session.setAttribute("questions", qts);
        session.setAttribute("answerMap", map);
        response.sendRedirect("/iqds/sas.jsp");
    }

}
