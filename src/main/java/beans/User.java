package beans;

public class User {
    private int userId;
    private String userName;
    private String userPwd;
    private boolean isMng;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public boolean isMng() {
        return isMng;
    }

    public void setMng(boolean isMng) {
        this.isMng = isMng;
    }

}
