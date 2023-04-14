package com.example.tablayoutcrud.model;

public class Cat {
    private int avatar;
    private String name;
    private double price;
    private String desc;

    public Cat() {
    }

    public Cat(int avatar, String name, double price, String desc) {
        this.avatar = avatar;
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
