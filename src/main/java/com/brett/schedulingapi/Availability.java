package com.brett.schedulingapi;

/**
 * Created by brett on 2/7/14.
 */
public class Availability {

    //{ "date" : "02/08/2014 14:00", "capacity": "5", "cost": "20.0" }

    private String date;
    private String capacity;
    private String cost;

    public Availability() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
