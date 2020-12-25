package com.example.sqlitepractice;

public class PetAttribute {

    int pID;
    String pName ;
    String pBreed ;
    int pGender ;
    int pWeight;

    public PetAttribute(int pID, String pName, String pBreed, int pGender, int pWeight) {
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

    public int getpGender() {
        return pGender;
    }

    public int getpWeight() {
        return pWeight;
    }


}
