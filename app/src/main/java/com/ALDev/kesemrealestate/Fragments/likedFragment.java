package com.ALDev.kesemrealestate.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.ALDev.kesemrealestate.Objects.PropertyItemClickListener;
import com.ALDev.kesemrealestate.R;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;


public class likedFragment extends Fragment {

    private AppCompatActivity activity;
    private LottieAnimationView lottieAnimationView;
    private RecyclerView propertyLiked_LST_view;
    private MyDB myDB;
    private ArrayList<Property> properties = new ArrayList<>();

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liked, container, false);
        findViews(view);

        loadFromSP();
        checkUpdatedProperties();
        if (properties.size() != 0) {
            lottieAnimationView.setVisibility(View.GONE);
        }
        Adapter_Property adapter_property = new Adapter_Property(getContext(), this.properties);

        propertyLiked_LST_view.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        propertyLiked_LST_view.setItemAnimator(new DefaultItemAnimator());
        propertyLiked_LST_view.setAdapter(adapter_property);

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
                propertyLiked_LST_view.getAdapter().notifyItemChanged(position);
                if (!property.isLiked()) {
                    removePropertyFromSP(property);
                    Snackbar snackbar = Snackbar.make(view, "הוסר!", Snackbar.LENGTH_SHORT);
                    ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
                    snackbar.show();
                }
            }
        });

        return view;
    }

    /**
     * remove the property from the shared preference
     *
     * @param property
     */
    private void removePropertyFromSP(Property property) {
        String js = MSP.getMe().getString("MY_LIKED_PROPERTIES", "");
        myDB = new Gson().fromJson(js, MyDB.class);

        myDB.getLikedProperties().remove(property);

        String json = new Gson().toJson(myDB);
        MSP.getMe().putString("MY_LIKED_PROPERTIES", json);
    }


    /**
     * Check if the loaded data from sp is matched
     * to the data from the firebase database
     */
    private void checkUpdatedProperties() {
        MyFirebaseDB.CallBack_Properties callBack_properties = properties -> {
            for (Property p : this.properties) {
                if (!properties.contains(p)) {
                    this.properties.remove(p);
                }
            }
        };
    }

    private void loadFromSP() {
        String js = MSP.getMe().getString("MY_LIKED_PROPERTIES", "");
        myDB = new Gson().fromJson(js, MyDB.class);
        properties = myDB.getLikedProperties();
    }

    private void findViews(View view) {
        propertyLiked_LST_view = view.findViewById(R.id.propertyLiked_list);
        lottieAnimationView = view.findViewById(R.id.lottie_SPC_no_properties_yet);
    }


}