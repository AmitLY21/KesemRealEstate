package com.ALDev.kesemrealestate.Objects;

import com.ALDev.kesemrealestate.R;

import java.util.ArrayList;

public class DataManger {

    public static ArrayList<Property> generateProperties() {
        ArrayList<Property> properties = new ArrayList<>();

        properties.add(new Property().setAddress("עמק זבולון, כפר סבא").setDescription("בית גדול").setNumOfRooms(7).setPropertyImages(new int[]{R.drawable.img3, R.drawable.img1, R.drawable.img2}));
        properties.add(new Property().setAddress("בקעת הירח, כפא סבא").setDescription("יש גינה וגריל").setNumOfRooms(5).setPropertyImages(new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3}));
        properties.add(new Property().setAddress("אפרים קציר, הוד השרון").setDescription("היסטרי וגבוה").setNumOfRooms(3).setPropertyImages(new int[]{R.drawable.img2, R.drawable.img3, R.drawable.img1}));

        return properties;
    }


}
