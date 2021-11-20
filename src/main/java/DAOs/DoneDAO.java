package DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Question;

public class DoneDAO implements DAO {

    public static void done(int userId, int questionId, boolean isCorrect) {
        String sql = "select * from questiondone where userId = '" + userId + "' and questionId = '" + questionId + "'";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql);
            if (!rs.next()) {
                sql = "insert into questiondone(userId, questionId) values('" + userId + "', '" + questionId + "')";
                DBcon.executeUpdate(sql);
            }
            if (isCorrect)
                sql = "update questiondone set doneTimes = doneTimes + 1, correctTimes = correctTimes + 1 where userId = '"
                        + userId + "' and questionId = '" + questionId + "'";
            else
                sql = "update questiondone set doneTimes = doneTimes + 1 where userId = '" + userId
                        + "' and questionId = '" + questionId + "'";
            DBcon.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> checkCorrect(int userId) {
        String sql = "select * from questiondone where userId = '" + userId + "' and correctTimes > 0";
        ResultSet rs;
        List<Integer> intList = new ArrayList<>();
        try {
            rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                intList.add(rs.getInt("questionId"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return intList;
    }

    public static Map<Integer, Integer> correct(int userId) {
        Map<Integer, Integer> correctNum = new HashMap<>();
        ResultSet rs;
        String sql = "select * from question, questiondone, knowledge where question.questionId = questiondone.questionId and question.knowledgeId = knowledge.knowledgeId and questiondone.userId = '"
                + userId + "' and correctTimes > 0";
        try {
            rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                int klId = rs.getInt("knowledgeId");
                int count = correctNum.containsKey(klId) ? correctNum.get(klId) : 0;
                correctNum.put(klId, count + 1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return correctNum;
    }

    public static Map<Integer, Integer> total() {
        Map<Integer, Integer> totalNum = new HashMap<>();
        ResultSet rs;
        String sql = "select * from question, knowledge where question.knowledgeId = knowledge.knowledgeId";
        try {
            rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                int klId = rs.getInt("knowledgeId");
                int count = totalNum.containsKey(klId) ? totalNum.get(klId) : 0;
                totalNum.put(klId, count + 1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return totalNum;
    }

    public static List<Question> getWrong(int userId) {
        List<Question> qList = new ArrayList<>();
        String sql = "select * from questiondone, question where questiondone.questionId = question.questionId and correctTimes/doneTimes < 0.5 and userId = '"
                + userId + "' order by correctTimes/doneTimes";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                Question q = new Question();
                q.setQuestionId(rs.getInt("questionId"));
                q.setTitle(rs.getString("title"));
                q.setQuestionType(rs.getInt("questionType"));
                qList.add(q);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return qList;
    }

    @Override
    public int delete(int itemId) {
        return 0;
    }

    public static int getRate(int questionId, int userId) {
        int rate = 0;
        String sql = "select * from questiondone where questionId = '" + questionId + "' and userId = '" + userId + "'";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql);
            if (rs.next()) {
                rate = rs.getInt("correctTimes") * 100 / rs.getInt("doneTimes");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return rate;
    }

}
