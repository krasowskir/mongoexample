package com.example.mongoexample.model;

import com.example.mongoexample.model.adress.Adress;

public class Restaurant {

    private String name;
    private String restaurant_id;
    private Adress address;
    private Klasse[] grades;
    private String borough;
    private String cuisine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Adress getAddress() {
        return address;
    }

    public void setAddress(Adress address) {
        this.address = address;
    }

    public Klasse[] getGrades() {
        return grades;
    }

    public void setGrades(Klasse[] grades) {
        this.grades = grades;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
