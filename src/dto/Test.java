package dto;

public class Test {
    private String webid;

    private String id;

    private String password;

    public String getWebid() {
        return webid;
    }

    public void setWebid(String webid) {
        this.webid = webid == null ? null : webid.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}