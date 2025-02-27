
package DTO;


public class DTO_User {
    private int userID;
    private String userName;
    private String pass;
    private String email;
    private String fullName;
    private boolean isAdmin;

    public DTO_User() {
    }

    public DTO_User(int userID, String userName, String pass, String email, String fullName, boolean isAdmin) {
        this.userID = userID;
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
    }

    public DTO_User(String userName, String pass, String email, String fullName) {
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.fullName = fullName;
    }
    public DTO_User(String userName, String pass, String email, String fullName, boolean isAdmin) {
        this.userName = userName;
        this.pass = pass;
        this.email = email;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
    }
    

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
}
