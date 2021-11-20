package servlets.InsertPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.QuestionDAO;

public class QuestionInsert extends HttpServlet {

    public QuestionInsert() {
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
        int klId = Integer.parseInt(request.getParameter("knowledgeId"));
        String klName = null;
        if (klId == 0) {
            klName = request.getParameter("knowledgeName");
        }
        String title = request.getParameter("title");
        String str = "";
        for (int i = 0; i < title.length(); ++i) {
            String each;
            switch (title.charAt(i)) {
                case '\"':
                    each = "&quot;";
                    break;
                case '\'':
                    each = "&apos;";
                    break;
                case ' ':
                    each = "&nbsp;";
                    break;
                case '\r':
                case '\n':
                    each = "<br>";
                    break;
                case '<':
                    each = "&lt;";
                    break;
                case '>':
                    each = "&gt;";
                    break;
                case '&':
                    each = "&amp;";
                    break;
                case '\t':
                    each = "&nbsp;";
                    break;
                default:
                    each = "" + title.charAt(i);
            }
            str += each;
        }
        String qId = request.getParameter("questionId");
        int questionId = -1;
        if (qId != null)
            questionId = Integer.parseInt(qId);
        String[] answersorigin = request.getParameterValues("answers");
        String[] answers = new String[answersorigin.length];
        for (int i = 0; i < answersorigin.length; ++i) {
            String ai = answersorigin[i];
            String ssstr = "";
            for (int f = 0; f < ai.length(); ++f) {
                String each;
                switch (ai.charAt(f)) {
                    case '\"':
                        each = "&quot;";
                        break;
                    case '\'':
                        each = "&apos;";
                        break;
                    case ' ':
                        each = "&nbsp;";
                        break;
                    case '\r':
                    case '\n':
                        each = "<br>";
                        break;
                    case '<':
                        each = "&lt;";
                        break;
                    case '>':
                        each = "&gt;";
                        break;
                    case '&':
                        each = "&amp;";
                        break;
                    case '\t':
                        each = "&nbsp;";
                        break;
                    default:
                        each = "" + ai.charAt(f);
                }
                ssstr += each;
            }
            answers[i] = ssstr;
        }
        int[] correctAnswers = null;

        int questionType = Integer.parseInt(request.getParameter("questionType"));
        if (questionType == 0) {
            String[] correctAnswersString = request.getParameterValues("correctAnswers");
            correctAnswers = new int[correctAnswersString.length];
            for (int i = 0; i < correctAnswersString.length; ++i) {
                correctAnswers[i] = Integer.parseInt(correctAnswersString[i]);
            }
        }
        QuestionDAO.insert(questionId, str, questionType, correctAnswers, answers, klId, klName);
    }

}
