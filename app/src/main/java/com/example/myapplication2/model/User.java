package com.example.myapplication2.model;

public class User {
    public String id;
    public String name;
    public String secondName;
    public String email;

    public User() {

    }

    public User(String id, String name, String secondName, String email) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
    }
}
