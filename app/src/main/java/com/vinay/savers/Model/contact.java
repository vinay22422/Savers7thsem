package com.vinay.savers.Model;

public class contact {
    private  String Id;
    private  String Number;
    private  String Name;
    private String Age;


    public contact() {
    }

    public contact(String num, String num1,String num2,String num3) {
        this.Id = num;
        this.Number = num3;
        this.Name = num1;
        this.Age = num2;

    }

    public String getId() {
        return Id;
    }

    public String getNumber() {
        return Number;
    }

    public String getName() {
        return Name;
    }

    public String getAge() {
        return Age;
    }

}
