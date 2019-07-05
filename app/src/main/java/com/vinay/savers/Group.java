package com.vinay.savers;

import java.util.ArrayList;

public class Group {

    private String name;

    private ArrayList<Item> itemList = new ArrayList<Item>();

    public Group(String name, ArrayList<Item> itemList) {
        super();
        this.name = name;
        this.itemList = itemList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Item> getItemList() {
        return itemList;
    }
    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    };


}