package DAOs;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

import beans.Paper;

public class PaperDAO implements DAO {

    @Override
    public int delete(int itemId) {
        return 0;
    }

    public static List<Paper> queryAll() {
        String sql = "select * from paper";
        List<Paper> ret = new ArrayList<Paper>();
        try {
            ResultSet rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                Paper paper = new Paper();
                paper.setId(rs.getInt("paperId"));
                paper.setPaperName(rs.getString("paperName"));
                ret.add(paper);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
