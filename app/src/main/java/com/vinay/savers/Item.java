package com.vinay.savers;

public class Item {

    private String code = "";
    private String name = "";
    //private int population = 0;

    public Item(String name) {
        super();
        this.name = name;
        //this.population = population;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
