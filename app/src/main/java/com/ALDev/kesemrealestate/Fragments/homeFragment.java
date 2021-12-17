package com.ALDev.kesemrealestate.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ALDev.kesemrealestate.Objects.Adapter_Property;
import com.ALDev.kesemrealestate.Objects.DataManger;
import com.ALDev.kesemrealestate.Objects.Property;
import com.ALDev.kesemrealestate.Objects.PropertyItemClickListener;
import com.ALDev.kesemrealestate.R;

import java.util.ArrayList;

public class homeFragment extends Fragment {

    private RecyclerView property_LST_view;

    private AppCompatActivity activity;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        property_LST_view = view.findViewById(R.id.property_list);
        ArrayList<Property> properties = DataManger.generateProperties();

        Adapter_Property adapter_property = new Adapter_Property(getActivity(), properties);
        // Grid
        property_LST_view.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        property_LST_view.setHasFixedSize(true);
        property_LST_view.setItemAnimator(new DefaultItemAnimator());
        property_LST_view.setAdapter(adapter_property);

        adapter_property.setPropertyItemClickListener(new PropertyItemClickListener() {
            @Override
            public void propertyItemClicked(Property property, int position) {

            }

            @Override
            public void favoriteClicked(Property property, int position) {
                property.setLiked(!property.isLiked());
                Toast.makeText(getActivity() , property.isLiked() + "\n" + property.getAddress(), Toast.LENGTH_SHORT).show();
                property_LST_view.getAdapter().notifyItemChanged(position);
            }
        });

        return view;
    }

}