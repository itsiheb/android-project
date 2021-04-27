package com.medianet.miniproject.model;

import com.google.firebase.database.Exclude;

public class Game {

    private String id;
    private String name;
    private Boolean isFavorie;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Exclude  public Boolean getIsFavorie() {
        return isFavorie;
    }

    @Exclude public void setIsFavorie(Boolean isFavorie) {
        this.isFavorie = isFavorie;
    }
}

