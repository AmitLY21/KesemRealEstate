package com.ALDev.kesemrealestate.Objects;

import android.media.Image;

import java.util.ArrayList;

public class Property {
    ArrayList<Image> propertyImages = new ArrayList<>();
    int numOfRooms;
    int numOfBathrooms;
    int squareFoot;
    int numOfParkingSpaces;
    int yearOfConstruction;
    int numOfFloors;
    boolean isLiked = false;
    boolean elevator;
    boolean storage;
    boolean balcony;
    boolean mamad; //residential secure space
    String address;

    @Override
    public String toString() {
        return "Property{" +
                "numOfRooms=" + numOfRooms +
                ", numOfBathrooms=" + numOfBathrooms +
                ", squareFoot=" + squareFoot +
                ", numOfParkingSpaces=" + numOfParkingSpaces +
                ", yearOfConstruction=" + yearOfConstruction +
                ", numOfFloors=" + numOfFloors +
                ", elevator=" + elevator +
                ", storage=" + storage +
                ", balcony=" + balcony +
                ", mamad=" + mamad +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public Property setAddress(String address) {
        this.address = address;
        return this;
    }

    String description;

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public Property setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
        return this;
    }

    public int getNumOfBathrooms() {
        return numOfBathrooms;
    }

    public Property setNumOfBathrooms(int numOfBathrooms) {
        this.numOfBathrooms = numOfBathrooms;
        return this;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public Property setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot;
        return this;
    }

    public int getNumOfParkingSpaces() {
        return numOfParkingSpaces;
    }

    public Property setNumOfParkingSpaces(int numOfParkingSpaces) {
        this.numOfParkingSpaces = numOfParkingSpaces;
        return this;
    }

    public int getYearOfConstruction() {
        return yearOfConstruction;
    }

    public Property setYearOfConstruction(int yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
        return this;
    }

    public int getNumOfFloors() {
        return numOfFloors;
    }

    public Property setNumOfFloors(int numOfFloors) {
        this.numOfFloors = numOfFloors;
        return this;
    }

    public boolean isElevator() {
        return elevator;
    }

    public Property setElevator(boolean elevator) {
        this.elevator = elevator;
        return this;
    }

    public boolean isStorage() {
        return storage;
    }

    public Property setStorage(boolean storage) {
        this.storage = storage;
        return this;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public Property setBalcony(boolean balcony) {
        this.balcony = balcony;
        return this;
    }

    public boolean isMamad() {
        return mamad;
    }

    public Property setMamad(boolean mamad) {
        this.mamad = mamad;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Property setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public ArrayList<Image> getPropertyImages() {
        return propertyImages;
    }

    public Property setPropertyImages(ArrayList<Image> propertyImages) {
        this.propertyImages = propertyImages;
        return this;
    }

    public Property setLiked(boolean liked) {
        isLiked = liked;
        return this;
    }
}
