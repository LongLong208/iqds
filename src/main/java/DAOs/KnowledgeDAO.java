package DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Knowledge;

public class KnowledgeDAO implements DAO {

    @Override
    public int delete(int itemId) {
        return 0;
    }

    public static List<Knowledge> checkAll() {
        List<Knowledge> list = new ArrayList<Knowledge>();
        String sql = "select * from knowledge";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                Knowledge knowledge = new Knowledge();
                knowledge.setKnowledgeId(rs.getInt("knowledgeId"));
                knowledge.setKnowledgeName(rs.getString("knowledgeName"));
                list.add(knowledge);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int lastId() {
        int last = 0;
        String sql = "select knowledgeId from knowledge order by knowledgeId desc";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql);
            if (rs.next())
                last = rs.getInt("knowledgeId");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return last;
    }

    public static int insert(String knowledgeName) {
        String sql = "insert into knowledge(knowledgeName) values(?)";
        try {
            DBcon.executeUpdate(sql, knowledgeName);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return lastId();
    }

    public static List<Integer> allId() {
        String sql = "select knowledgeId from knowledge";
        ResultSet rs;
        List<Integer> intList = new ArrayList<>();
        try {
            rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                intList.add(rs.getInt("knowledgeId"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return intList;
    }

}
