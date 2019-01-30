package com.example.expandablelist;

import java.util.ArrayList;

public class Order {
    ArrayList<Cloth> clothArrayList;
    String serviceType;
    int items;
    float cost;

    public Order(ArrayList<Cloth> clothArrayList, String serviceType, int items, float cost) {
        this.clothArrayList = clothArrayList;
        this.serviceType = serviceType;
        this.items = items;
        this.cost = cost;
    }

    public ArrayList<Cloth> getClothArrayList() {
        return clothArrayList;
    }

    public void setClothArrayList(ArrayList<Cloth> clothArrayList) {
        this.clothArrayList = clothArrayList;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
