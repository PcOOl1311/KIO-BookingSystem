package edu.acg.kio.kiobookingsystem.classs;


import edu.acg.kio.kiobookingsystem.enumerators.UserType;

public class User {

    private String name;
    private String password;
    private String contactInfo;
    private UserType userType;


    // CONSTRUCTOR
    public User(String name, String password, String contactInfo, UserType userType) {
        this.name = name;
        this.password = password;
        this.contactInfo = contactInfo;
        this.userType = userType;
    }

    // GETTER

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public UserType getUserType() {
        return userType;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "------------------------------------" + " \n" +
                " Name     = " + name + " \n" +
                " Password = " + password + " \n" +
                " Contact Info:  = " + contactInfo + " \n" +
                " User Type  = " + userType + " \n" +
                "------------------------------------";
    }
}
