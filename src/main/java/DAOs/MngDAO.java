package DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;

public class MngDAO implements DAO {
    private User user;

    public MngDAO() {
    }

    public boolean check(String userName, String userPwd) {
        boolean success = false;
        UserDAO userDAO = new UserDAO();
        if (userDAO.check(userName, userPwd)) {
            user = userDAO.getUser();
            String sql = "select * from manager where userId = ?";
            try {
                ResultSet resultSet = DBcon.executeQuery(sql, "" + user.getUserId());
                if (resultSet.next())
                    success = true;
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        }

        return success;
    }

    public static boolean check(int userId) {
        boolean success = false;
        String sql = "select * from manager where userId = ?";
        try {
            ResultSet resultSet = DBcon.executeQuery(sql, "" + userId);
            if (resultSet.next())
                success = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int delete(int itemId) {
        // TODO Auto-generated method stub
        return 0;
    }
}
