package com.vinay.expandablelist.Model;

public class DataModel {
    private String title,version;
    private int id;

    //constructor

    public DataModel(String title, String version, int id) {
        this.title = title;
        this.version = version;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getVersion() {
        return version;
    }

    public int getId() {
        return id;
    }
}
