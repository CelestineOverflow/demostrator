public class LoginManager {
    private String password;
    private long userId = -1;

    public void displayInterface() {
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isUserLogged(){
        return !(userId == -1 || password == null);
    }
}