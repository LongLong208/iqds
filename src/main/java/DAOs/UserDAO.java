package DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.User;

public class UserDAO implements DAO {
    private User user;

    public UserDAO() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public boolean check(String userName, String userPwd) {
        boolean loginSuccess = false;
        String sql = "select * from user where userName = ? and userPwd = ?";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql, userName, userPwd);
            if (rs.next())
                if (rs.getString("userName").equals(userName) && rs.getString("userPwd").equals(userPwd)) {
                    loginSuccess = true;
                    user.setUserId(rs.getInt("userId"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPwd(rs.getString("userPwd"));
                }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return loginSuccess;
    }

    public static ArrayList<User> checkAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "select * from user left join manager on user.userId = manager.userId";
        ResultSet rs;
        try {
            rs = DBcon.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setUserPwd(rs.getString("userPwd"));
                if (rs.getInt("managerId") != 0)
                    user.setMng(true);
                else
                    user.setMng(false);
                userList.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static boolean checkValid(String userName) {
        boolean valid = false;
        ResultSet rs;
        String sql = "select * from user where userName='" + userName + "'";
        try {
            rs = DBcon.executeQuery(sql);
            if (rs.isBeforeFirst() == false)
                valid = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return valid;
    }

    public static void add(String userName, String userPwd) {
        String sql = "insert into user(userName, userPwd) values(?, ?)";
        try {
            DBcon.executeUpdate(sql, userName, userPwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changePower(int userId) {
        String sql = "select * from manager where userId = '" + userId + "'";
        try {
            String sql2;
            if (DBcon.executeQuery(sql).isBeforeFirst()) {
                sql2 = "delete from manager where userId = '" + userId + "'";
            } else {
                sql2 = "insert into manager(userId) values('" + userId + "')";
            }
            DBcon.executeUpdate(sql2);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delete(int itemId) {
        return 0;
    }

}
