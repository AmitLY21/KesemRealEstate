package com.ALDev.kesemrealestate.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Property implements Serializable {
    ArrayList<String> propertyImages = new ArrayList<>();
    String description;
    private String propertyNumber;
    private int numOfRooms;
    private int numOfBathrooms;
    private int squareFoot;
    private int numOfParkingSpaces;
    private int numOfFloors;
    private boolean isLiked = false;
    private boolean elevator;
    private boolean storage;
    private boolean balcony;
    private boolean mamad; //residential secure space
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(propertyNumber, property.propertyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyNumber);
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyImages=" + propertyImages +
                ", propertyNumber='" + propertyNumber + '\'' +
                ", numOfRooms=" + numOfRooms +
                ", numOfBathrooms=" + numOfBathrooms +
                ", squareFoot=" + squareFoot +
                ", numOfParkingSpaces=" + numOfParkingSpaces +
                ", numOfFloors=" + numOfFloors +
                ", isLiked=" + isLiked +
                ", elevator=" + elevator +
                ", storage=" + storage +
                ", balcony=" + balcony +
                ", mamad=" + mamad +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }


    public String getAddress() {
        return address;
    }


    public int getNumOfRooms() {
        return numOfRooms;
    }


    public int getNumOfBathrooms() {
        return numOfBathrooms;
    }


    public int getSquareFoot() {
        return squareFoot;
    }

    public int getNumOfParkingSpaces() {
        return numOfParkingSpaces;
    }

    public boolean isElevator() {
        return elevator;
    }

    public boolean isStorage() {
        return storage;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public boolean isMamad() {
        return mamad;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public Property setLiked(boolean liked) {
        isLiked = liked;
        return this;
    }

    public ArrayList<String> getPropertyImages() {
        return propertyImages;
    }

    public Property setPropertyImages(ArrayList<String> propertyImages) {
        this.propertyImages = propertyImages;
        return this;
    }

}
