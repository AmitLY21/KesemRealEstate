package com.ALDev.kesemrealestate;

import androidx.annotation.NonNull;

import com.ALDev.kesemrealestate.Objects.Property;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MyFirebaseDB {

    public static void getAllProperties(CallBack_Properties callBack_properties) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://realestate-9cb72-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("properties");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Property> properties = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    try {
                        Property p = child.getValue(Property.class);
                        properties.add(p);
                    } catch (Exception ex) {
                    }
                }
                if (callBack_properties != null) {
                    callBack_properties.dataReady(properties);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void getPropertyImageList(String propertyNumber, CallBack_PropertyImages callBack_propertyImages) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://realestate-9cb72-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("Property-" + propertyNumber);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> imageList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                    imageList.add(map.get("imgLink"));
                }
                if (callBack_propertyImages != null) {
                    callBack_propertyImages.dataReady(imageList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public interface CallBack_Properties {
        void dataReady(ArrayList<Property> properties);
    }

    public interface CallBack_PropertyImages {
        void dataReady(ArrayList<String> imageList);
    }

}
