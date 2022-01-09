package com.ALDev.kesemrealestate.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.ALDev.kesemrealestate.MyFirebaseDB;
import com.ALDev.kesemrealestate.MyMap;
import com.ALDev.kesemrealestate.Objects.Property;
import com.ALDev.kesemrealestate.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.chip.Chip;
import com.google.android.material.textview.MaterialTextView;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class FullPropertyActivity extends AppCompatActivity {
    public ImageCarousel carouselView;
    public MaterialTextView property_LBL_address;
    public MaterialTextView property_LBL_description;
    public Chip property_numOfRooms_chip;
    public Chip property_numOfbathrooms_chip;
    public Chip property_numOfParkingLots_chip;
    public Chip property_SquareMeter;
    public Chip property_mamad_chip;
    public Chip property_storage_chip;
    public Chip property_balcony_chip;
    public Chip property_elevator_chip;
    public AppCompatButton BTN_property_contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_card_property_view);

        findViews();
        Property property = (Property) getIntent().getSerializableExtra("property");
        initViews(property);
        initMap();

        BTN_property_contact.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(FullPropertyActivity.this, HomeActivity.class);
            intent.putExtra("contact", "" + R.id.page_3_contact);
            startActivity(intent);
        });

    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(googleMap -> {
            LatLng latLng = MyMap.getLatLngByAddress(getBaseContext(), property_LBL_address.getText().toString());
            googleMap.clear();
            googleMap.addMarker(new MarkerOptions().position(latLng).title("נמצא כאן"));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17), 2300, null);
        });
    }

    @SuppressLint("SetTextI18n")
    private void initViews(Property property) {
        if (property != null) {
            initImages(property);
            property_LBL_address.setText(property.getAddress());
            property_LBL_description.setText(property.getDescription());
            property_numOfRooms_chip.setText("מספר חדרים: " + property.getNumOfRooms());
            property_numOfbathrooms_chip.setText("מספר חדרי שירותים: " + property.getNumOfBathrooms());
            property_numOfParkingLots_chip.setText("מספר חניות: " + property.getNumOfParkingSpaces());
            property_SquareMeter.setText("מטר רבוע: " + property.getSquareFoot());
            if (property.isMamad()) {
                property_mamad_chip.setText("ממד");
            } else {
                property_mamad_chip.setVisibility(View.GONE);
            }
            if (property.isElevator()) {
                property_elevator_chip.setText("מעלית");

            } else {
                property_elevator_chip.setVisibility(View.GONE);
            }
            if (property.isStorage()) {
                property_storage_chip.setText("מחסן");
            } else {
                property_storage_chip.setVisibility(View.GONE);
            }
            if (property.isBalcony()) {
                property_balcony_chip.setText("מרפסת");
            } else {
                property_balcony_chip.setVisibility(View.GONE);
            }
        }
    }

    private void initImages(Property property) {
        MyFirebaseDB.CallBack_PropertyImages callBack_propertyImages = imageList -> {
            property.setPropertyImages(imageList);
            if (property.getPropertyImages().size() != 0) {
                List<CarouselItem> list = new ArrayList<>();
                for (String imgLink : property.getPropertyImages()) {
                    Log.d("pttt", "onBindViewHolder: " + imgLink);
                    list.add(new CarouselItem(imgLink));
                }
                Log.d("pttt", "onBindViewHolder: " + list.toString());
                carouselView.setData(list);
            }
        };
        MyFirebaseDB.getPropertyImageList(property.getPropertyNumber(), callBack_propertyImages);

    }

    private void findViews() {
        carouselView = findViewById(R.id.carouselView_fullCard);
        property_LBL_address = findViewById(R.id.property_LBL_address);
        property_LBL_description = findViewById(R.id.property_LBL_DESCRPTION);
        property_numOfRooms_chip = findViewById(R.id.chip_numOfRooms);
        property_numOfbathrooms_chip = findViewById(R.id.chip_numOfBathrooms);
        property_numOfParkingLots_chip = findViewById(R.id.chip_numOfParkingLots);
        property_SquareMeter = findViewById(R.id.chip_numOfSquareMeter);
        property_mamad_chip = findViewById(R.id.chip_Mamad);
        property_storage_chip = findViewById(R.id.chip_Storage);
        property_balcony_chip = findViewById(R.id.chip_balcony);
        property_elevator_chip = findViewById(R.id.chip_Elevator);
        BTN_property_contact = findViewById(R.id.contact_BTN);
    }
}