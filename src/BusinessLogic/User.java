package BusinessLogic;

public class User {
    private String login;
    private String password;
    private boolean isAdmin;
    private int UserId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public int getUserId() {
        return UserId;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
