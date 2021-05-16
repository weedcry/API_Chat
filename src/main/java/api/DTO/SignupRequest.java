package api.DTO;

import java.sql.Date;

public class SignupRequest {

    private String username;

    private String name;

    private  String password;

    private Date birthday;

    public SignupRequest() {
    }

    public SignupRequest(String username, String name, String password, Date birthday) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
    }

    public String getUsername() { return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() { return birthday; }

    public void setBirthday(Date birthday) { this.birthday = birthday; }
}
