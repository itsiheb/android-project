package com.medianet.miniproject.model;

import java.util.ArrayList;

public class User {

    String id;
    String email;
    String password;
    String name;
    ArrayList<String> list_games = new ArrayList<>();

    public User(){

    }
    public User(String id, String email, String password, String name, ArrayList<String> list_games) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.list_games = list_games;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getList_games() {
        return list_games;
    }

    public void setList_games(ArrayList<String> list_games) {
        this.list_games = list_games;
    }
}
