package Model.BEAN;

public class UserSessionInfo {
    private int id;
    private String username;
    private String fullname;

    public UserSessionInfo(int id, String username, String fullname) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
