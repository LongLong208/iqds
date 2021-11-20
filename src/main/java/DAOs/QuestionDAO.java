package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.AnswerItem;
import beans.Question;

public class QuestionDAO implements DAO {

    public QuestionDAO() {

    }

    public static List<Question> checkAll() {
        ArrayList<Question> questionList = new ArrayList<>();
        String sql = "select * from question,knowledge where question.knowledgeId = knowledge.knowledgeId";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt("questionId"));
                question.setTitle(rs.getString("title"));
                question.setQuestionType(rs.getInt("questionType"));
                question.setKnowledgeId(rs.getInt("knowledgeId"));
                question.setKnowledgeName(rs.getString("knowledgeName"));
                questionList.add(question);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public static List<Question> check(String key) {
        ArrayList<Question> questionList = new ArrayList<>();
        String sql = "select * from question where title like '%" + key + "%'";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt("questionId"));
                question.setTitle(rs.getString("title"));
                question.setQuestionType(rs.getInt("questionType"));
                questionList.add(question);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public static List<Question> checkAll(int type) {
        ArrayList<Question> questionList = new ArrayList<>();
        String sql = "select * from question,knowledge where question.knowledgeId = knowledge.knowledgeId and questionType = '"
                + type + "'";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt("questionId"));
                question.setTitle(rs.getString("title"));
                question.setQuestionType(rs.getInt("questionType"));
                question.setKnowledgeId(rs.getInt("knowledgeId"));
                question.setKnowledgeName(rs.getString("knowledgeName"));
                questionList.add(question);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public static List<Question> checkAll(int type, int knowledgeId) {
        ArrayList<Question> questionList = new ArrayList<>();
        String sql = "select * from question,knowledge where question.knowledgeId = knowledge.knowledgeId and questionType = '"
                + type + "' and knowledge.knowledgeId = '" + knowledgeId + "'";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt("questionId"));
                question.setTitle(rs.getString("title"));
                question.setQuestionType(rs.getInt("questionType"));
                question.setKnowledgeId(rs.getInt("knowledgeId"));
                question.setKnowledgeName(rs.getString("knowledgeName"));
                questionList.add(question);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    public static int lastId() {
        int last = 0;
        String sql = "select questionId from question order by questionId desc";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql);
            if (rs.next())
                last = rs.getInt("questionId");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return last;
    }

    public static void insert(int questionId, String title, int questionType, int[] correctAnswers, String[] answers,
            int knowledgeId, String knowledgeName) {
        String questionSql;
        if (knowledgeId == 0) {
            knowledgeId = KnowledgeDAO.insert(knowledgeName);
        }
        if (questionId > 0)
            questionSql = "insert into question(title, questionType, knowledgeId, questionId) values(?, ?, ?, ?)";
        else
            questionSql = "insert into question(title, questionType, knowledgeId) values(?, ?, ?)";
        String answerSql = "insert into answeritem(title, isTrue, questionId) values(?, ?, ?)";
        for (int i = 1; i < answers.length; ++i)
            answerSql += ", (?, ?, ?)";
        try {
            PreparedStatement pStmt = DBcon.prepareStatement(questionSql);
            pStmt.setString(1, title);
            pStmt.setInt(2, questionType);
            pStmt.setInt(3, knowledgeId);
            if (questionId > 0)
                pStmt.setInt(4, questionId);
            pStmt.executeUpdate();
            int last;
            if (questionId > 0)
                last = questionId;
            else
                last = lastId();

            PreparedStatement pStmt2 = DBcon.prepareStatement(answerSql);
            int j = 0;
            for (int i = 0; i < answers.length; ++i) {
                pStmt2.setString(i * 3 + 1, answers[i]);
                if (questionType == 0) {
                    if (j < correctAnswers.length && correctAnswers[j] == i) {
                        pStmt2.setBoolean(i * 3 + 2, true);
                        ++j;
                    } else {
                        pStmt2.setBoolean(i * 3 + 2, false);
                    }
                } else {
                    pStmt2.setBoolean(i * 3 + 2, true);
                }
                pStmt2.setInt(i * 3 + 3, last);
            }
            pStmt2.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(int questionId) {
        int result = 0;
        String sql = "delete from answeritem where questionId = ?";

        try {
            result += DBcon.executeUpdate(sql, questionId);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        sql = "delete from question where questionId = ?";
        try {
            result += DBcon.executeUpdate(sql, questionId);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<AnswerItem> lookAnswer(int questionId) {
        List<AnswerItem> list = new ArrayList<AnswerItem>();
        String sql = "select * from answeritem where questionId = ? and isTrue = true";
        try {
            ResultSet rs = DBcon.executeQuery(sql, questionId);
            while (rs.next()) {
                AnswerItem ai = new AnswerItem();
                ai.setAnswerItemId(rs.getInt("answeritemId"));
                ai.setQuestionId(rs.getInt("questionId"));
                ai.setTitle(rs.getString("title"));
                ai.setTrue(rs.getBoolean("isTrue"));
                list.add(ai);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Question get(int questionId) {
        Question question = null;
        String sql = "select * from question,knowledge where question.questionId = ? and question.knowledgeId = knowledge.knowledgeId";
        try {
            ResultSet rs = DBcon.executeQuery(sql, questionId);
            if (rs.next()) {
                question = new Question();
                question.setQuestionId(rs.getInt("questionId"));
                question.setTitle(rs.getString("title"));
                question.setQuestionType(rs.getInt("questionType"));
                question.setKnowledgeId(rs.getInt("knowledgeId"));
                question.setKnowledgeName(rs.getString("knowledgeName"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    public static List<AnswerItem> getAnswer(int questionId) {
        List<AnswerItem> list = new ArrayList<AnswerItem>();
        String sql = "select * from answeritem where questionId = ?";
        try {
            ResultSet rs = DBcon.executeQuery(sql, questionId);
            while (rs.next()) {
                AnswerItem ai = new AnswerItem();
                ai.setAnswerItemId(rs.getInt("answeritemId"));
                ai.setQuestionId(rs.getInt("questionId"));
                ai.setTitle(rs.getString("title"));
                ai.setTrue(rs.getBoolean("isTrue"));
                list.add(ai);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
