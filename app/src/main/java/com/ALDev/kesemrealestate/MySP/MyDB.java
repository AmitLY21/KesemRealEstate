package com.ALDev.kesemrealestate.MySP;

import com.ALDev.kesemrealestate.Objects.Property;

import java.util.ArrayList;

public class MyDB {

    private ArrayList<Property> likedProperties = new ArrayList<>();

    public MyDB() {

    }

    public ArrayList<Property> getLikedProperties() {
        return likedProperties;
    }

    public MyDB setLikedProperties(ArrayList<Property> likedProperties) {
        this.likedProperties = likedProperties;
        return this;
    }
}
