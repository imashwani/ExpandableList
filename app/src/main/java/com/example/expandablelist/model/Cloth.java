package com.example.expandablelist.model;

import java.util.ArrayList;

public class Cloth {
    String name, description;
    int noOfCloth;
    boolean isChecked;
    ArrayList<SubClothItem> subClothItemArrayList = new ArrayList<>();


    public Cloth(String name, String description, int noOfCloth, ArrayList<SubClothItem> subClothItemArrayList) {
        this.name = name;
        this.description = description;
        this.noOfCloth = noOfCloth;
        this.isChecked = false;
        this.subClothItemArrayList = subClothItemArrayList;
    }

    public Cloth(String name, String description, int noOfCloth, boolean isChecked, ArrayList<SubClothItem> subClothItemArrayList) {
        this.name = name;
        this.description = description;
        this.noOfCloth = noOfCloth;
        this.isChecked = isChecked;
        this.subClothItemArrayList = subClothItemArrayList;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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

    public ArrayList<SubClothItem> getSubClothItemArrayList() {
        return subClothItemArrayList;
    }

    public void setSubClothItemArrayList(ArrayList<SubClothItem> subClothItemArrayList) {
        this.subClothItemArrayList = subClothItemArrayList;
    }

}
