package models;

/**
 * Created by Jhon on 7/6/2017.
 */
public class User {
    private Integer id;
    private String userName;
    private String name;
    private String password;
    private Boolean isAdmin;
    private Boolean isAuthor;


    public User() {
    }

    public User(Integer id, String userName, String name, String password, Boolean isAdmin, Boolean isAuthor) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isAuthor = isAuthor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getAuthor() {
        return isAuthor;
    }

    public void setAuthor(Boolean author) {
        isAuthor = author;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", isAuthor=" + isAuthor +
                '}';
    }
}
