package com.ALDev.kesemrealestate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ALDev.kesemrealestate.Activities.FullPropertyActivity;
import com.ALDev.kesemrealestate.MyFirebaseDB;
import com.ALDev.kesemrealestate.MySP.MSP;
import com.ALDev.kesemrealestate.MySP.MyDB;
import com.ALDev.kesemrealestate.Objects.Adapter_Property;
import com.ALDev.kesemrealestate.Objects.Property;
import com.ALDev.kesemrealestate.Objects.PropertyComparators;
import com.ALDev.kesemrealestate.Objects.PropertyItemClickListener;
import com.ALDev.kesemrealestate.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class homeFragment extends Fragment {

    private RecyclerView property_LST_view;
    private final MyDB myDB = new MyDB();
    private final ArrayList<Property> properties = new ArrayList<>();

    private AppCompatActivity activity;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViews(view);
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        MyFirebaseDB.CallBack_Properties callBack_properties = new MyFirebaseDB.CallBack_Properties() {
            @Override
            public void dataReady(ArrayList<Property> properties) {
                Collections.sort(properties, new PropertyComparators());

                Adapter_Property adapter_property = new Adapter_Property(getActivity(), properties);
                // Grid
                property_LST_view.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                property_LST_view.setItemAnimator(new DefaultItemAnimator());
                property_LST_view.setAdapter(adapter_property);


                adapter_property.setPropertyItemClickListener(new PropertyItemClickListener() {
                    @Override
                    public void propertyItemClicked(Property property, int position) {
                        Intent intent = new Intent(getActivity(), FullPropertyActivity.class);
                        intent.putExtra("property", property);
                        startActivity(intent);
                    }

                    @Override
                    public void favoriteClicked(Property property, int position) {
                        property.setLiked(!property.isLiked());
                        Snackbar snackbar = Snackbar.make(view, "נוסף לרשימת אהבתי", Snackbar.LENGTH_SHORT);
                        ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
                        snackbar.show();
                        property_LST_view.getAdapter().notifyItemChanged(position);
                        saveToSP(property);
                    }
                });
            }
        };
        MyFirebaseDB.getAllProperties(callBack_properties);

    }

    private void saveToSP(Property property) {
        if (!myDB.getLikedProperties().contains(property))
            myDB.getLikedProperties().add(property);
        String json = new Gson().toJson(myDB);
        MSP.getMe().putString("MY_LIKED_PROPERTIES", json);
    }

    private void findViews(View view) {
        property_LST_view = view.findViewById(R.id.property_list);

    }

}