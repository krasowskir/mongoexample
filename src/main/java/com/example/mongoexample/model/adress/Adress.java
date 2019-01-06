package com.example.mongoexample.model.adress;

public class Adress {
    private String building;
    private float[] coord;
    private String street;
    private String zipcode;

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public float[] getCoord() {
        return coord;
    }

    public void setCoord(float[] coord) {
        this.coord = coord;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
