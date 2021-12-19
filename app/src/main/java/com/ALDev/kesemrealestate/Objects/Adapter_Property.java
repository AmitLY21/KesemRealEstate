package com.ALDev.kesemrealestate.Objects;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.ALDev.kesemrealestate.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.textview.MaterialTextView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class Adapter_Property extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private ArrayList<Property> properties;
    private PropertyItemClickListener propertyItemClickListener;

    public Adapter_Property(Activity activity, ArrayList<Property> properties) {
        this.activity = activity;
        this.properties = properties;
    }

    public Adapter_Property setPropertyItemClickListener(PropertyItemClickListener propertyItemClickListener) {
        this.propertyItemClickListener = propertyItemClickListener;
        return this;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.property_card_view, viewGroup, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PropertyViewHolder propertyViewHolder = (PropertyViewHolder) holder;
        Property property = getItem(position);
        Log.d("pttt", "position= " + position);

        propertyViewHolder.carouselView.setPageCount(property.propertyImages.length);
        propertyViewHolder.carouselView.setImageListener((position1, imageView) -> imageView.setImageResource(property.propertyImages[position1]));

        propertyViewHolder.property_LBL_title.setText(property.getAddress());
        propertyViewHolder.property_LBL_description.setText(property.getDescription());

        propertyViewHolder.property_numOfRooms_chip.onRtlPropertiesChanged(View.LAYOUT_DIRECTION_RTL);
        propertyViewHolder.property_numOfRooms_chip.setText("מספר חדרים: " + String.valueOf(property.getNumOfRooms()));

        if (property.isLiked()) {
            propertyViewHolder.property_IMG_favorite.setImageResource(R.drawable.ic_heart_filled);
        } else {
            propertyViewHolder.property_IMG_favorite.setImageResource(R.drawable.ic_heart_empty);
        }
    }



    @Override
    public int getItemCount() {
        return properties.size();
    }

    private Property getItem(int position) {
        return properties.get(position);
    }


    private class PropertyViewHolder extends RecyclerView.ViewHolder {

        public CarouselView carouselView;
        public AppCompatImageButton property_IMG_favorite;
        public MaterialTextView property_LBL_title;
        public MaterialTextView property_LBL_description;
        public Chip property_numOfRooms_chip;
        public AppCompatButton property_map;


        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            carouselView = itemView.findViewById(R.id.carouselView);
            property_IMG_favorite = itemView.findViewById(R.id.property_IMG_favorite);
            property_LBL_title = itemView.findViewById(R.id.property_LBL_title);
            property_LBL_description = itemView.findViewById(R.id.property_LBL_address);
            property_map = itemView.findViewById(R.id.property_map);
            property_numOfRooms_chip = itemView.findViewById(R.id.chip_numOfRooms);

            itemView.setOnClickListener(view -> propertyItemClickListener.propertyItemClicked(getItem(getAdapterPosition()), getAdapterPosition()));

            property_IMG_favorite.setOnClickListener(v -> propertyItemClickListener.favoriteClicked(getItem(getAdapterPosition()), getAdapterPosition()));

            property_map.setOnClickListener(view -> {
                Toast.makeText(itemView.getContext(), "Map", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
