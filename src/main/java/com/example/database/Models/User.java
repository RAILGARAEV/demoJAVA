package com.example.database.Models;

public class User {
    String id;

    String login;

    String password;
    String firstname;
    String secondname;
    String lastname;

    String role;
    String status;

    public User(String id, String login, String password, String firstname, String secondname, String lastname, String role, String status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.role = role;
        this.status = status;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
