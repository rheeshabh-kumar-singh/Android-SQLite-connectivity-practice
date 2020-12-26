package com.example.sqlitepractice;

public class PetAttribute {

    int pID;
    String pName ;
    String pBreed ;
    String pGender ;
    int pWeight;

    public PetAttribute(int pID, String pName, String pBreed, String pGender, int pWeight) {
        this.pID = pID;
        this.pName = pName;
        this.pBreed = pBreed;
        this.pGender = pGender;
        this.pWeight = pWeight;
    }

    public int getpID() {
        return pID;
    }

    public String getpName() {
        return pName;
    }

    public String getpBreed() {
        return pBreed;
    }

    public String getpGender() {
        return pGender;
    }

    public int getpWeight() {
        return pWeight;
    }


}
