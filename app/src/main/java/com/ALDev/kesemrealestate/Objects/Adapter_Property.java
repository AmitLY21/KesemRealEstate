package com.ALDev.kesemrealestate.Objects;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.ALDev.kesemrealestate.MyFirebaseDB;
import com.ALDev.kesemrealestate.MyMap;
import com.ALDev.kesemrealestate.MySP.MSP;
import com.ALDev.kesemrealestate.MySP.MyDB;
import com.ALDev.kesemrealestate.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Property extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Property> properties;
    private PropertyItemClickListener propertyItemClickListener;
    private MyDB myDB = new MyDB();

    public Adapter_Property(Context context, ArrayList<Property> properties) {
        this.context = context;
        this.properties = properties;

        //CHECK LIKED PROPERTIES FROM SP
        String js = MSP.getMe().getString("MY_LIKED_PROPERTIES", "");
        myDB = new Gson().fromJson(js, MyDB.class);
        for (Property p : this.properties) {
            if (myDB.getLikedProperties().contains(p))
                p.setLiked(true);
        }
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
        Property property = properties.get(position);
        Log.d("pttt", "position= " + position);

        MyFirebaseDB.CallBack_PropertyImages callBack_propertyImages = new MyFirebaseDB.CallBack_PropertyImages() {
            @Override
            public void dataReady(ArrayList<String> imageList) {
                property.setPropertyImages(imageList);
                if (property.propertyImages.size() != 0) {
                    List<CarouselItem> list = new ArrayList<>();
                    for (String imgLink : property.getPropertyImages()) {
                        Log.d("pttt", "onBindViewHolder: " + imgLink);
                        list.add(new CarouselItem(imgLink));
                    }
                    Log.d("pttt", "onBindViewHolder: " + list.toString());
                    propertyViewHolder.carouselView.setData(list);
                }
            }
        };
        MyFirebaseDB.getPropertyImageList(property.getPropertyNumber(), callBack_propertyImages);

        propertyViewHolder.property_LBL_title.setText(property.getAddress());
        propertyViewHolder.property_LBL_description.setText(property.getDescription());
        propertyViewHolder.property_numOfRooms_chip.onRtlPropertiesChanged(View.LAYOUT_DIRECTION_RTL);
        propertyViewHolder.property_numOfRooms_chip.setText("מספר חדרים: " + property.getNumOfRooms());
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

        public ImageCarousel carouselView;
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

            itemView.setOnClickListener(view ->
                    propertyItemClickListener.propertyItemClicked(getItem(getAdapterPosition()), getAdapterPosition()));

            property_IMG_favorite.setOnClickListener(v ->
                    propertyItemClickListener.favoriteClicked(getItem(getAdapterPosition()), getAdapterPosition()));

            /**
             * Open Map Intent with current property address
             */
            property_map.setOnClickListener(view -> {
                String geo = MyMap.getGeoByAddress(context, property_LBL_title.getText().toString());
                Log.d("pttt", "PropertyViewHolder: " + geo);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geo));
                context.startActivity(intent);
            });
        }
    }
}
