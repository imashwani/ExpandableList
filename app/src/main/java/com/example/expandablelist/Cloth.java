package com.example.expandablelist;

public class Cloth {
    String name, description;
    int noOfCloth;

    public Cloth(String name, String description, int noOfCloth) {
        this.name = name;
        this.description = description;
        this.noOfCloth = noOfCloth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNoOfCloth() {
        return noOfCloth;
    }

    public void setNoOfCloth(int noOfCloth) {
        this.noOfCloth = noOfCloth;
    }
}
