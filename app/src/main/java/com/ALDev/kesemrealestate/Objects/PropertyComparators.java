package com.ALDev.kesemrealestate.Objects;

import java.util.Comparator;

public class PropertyComparators implements Comparator<Property> {
    /**
     * Compares by rooms
     *
     * @param p1
     * @param p2
     * @return
     */
    public int compare(Property p1, Property p2) {
        if (p1.getNumOfRooms() == p2.getNumOfRooms())
            return 0;
        else if (p1.getNumOfRooms() > p2.getNumOfRooms())
            return -1;
        else
            return 1;
    }
}
