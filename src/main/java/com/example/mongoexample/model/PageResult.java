package com.example.mongoexample.model;

import java.util.List;

public class PageResult {

    private int items;
    private List<Restaurant> restaurantList;

    public PageResult(int items, List<Restaurant> restaurantList) {
        this.items = items;
        this.restaurantList = restaurantList;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
