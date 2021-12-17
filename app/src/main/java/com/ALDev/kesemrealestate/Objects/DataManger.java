package com.ALDev.kesemrealestate.Objects;

import android.media.Image;

import java.util.ArrayList;

public class DataManger {

    public static ArrayList<Property> generateProperties() {
        ArrayList<Property> properties = new ArrayList<>();

        properties.add(new Property().setAddress("עמק זבולון, כפר סבא").setDescription("בית גדול"));
        properties.add(new Property().setAddress("בקעת הירח, כפא סבא").setDescription("יש גינה וגריל"));
        properties.add(new Property().setAddress("אפרים קציר, הוד השרון").setDescription("היסטרי וגבוה"));

        return properties;
    }


}
