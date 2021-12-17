package com.ALDev.kesemrealestate.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ALDev.kesemrealestate.R;

public class contactFragment extends Fragment {

    private ImageButton whatsappBTN;
    private ImageButton phoneBTN;
    private ImageButton facebookBTN;

    private final String PHONENUMBER = "0536069941";
    private final String FACEBOOK_ID = "100002893032846";

    private AppCompatActivity activity;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        findViews(view);
        // Inflate the layout for this fragment

        /**
         * Open whatsapp and start chat
         */
        whatsappBTN.setOnClickListener(view1 -> {
            String url = "https://api.whatsapp.com/send?phone=" + PHONENUMBER;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

        /**
         * Open Facebook page
         */
        facebookBTN.setOnClickListener(view12 -> {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/" + FACEBOOK_ID));
                startActivity(intent);
            } catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/appetizerandroid")));
            }
        });

        /**
         * Open phone dialer
         */
        phoneBTN.setOnClickListener(view13 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + PHONENUMBER));
            startActivity(intent);
        });
        return view;
    }

    private void findViews(View view) {
        whatsappBTN = view.findViewById(R.id.whatsapp_img_btn);
        phoneBTN = view.findViewById(R.id.phone_img_btn);
        facebookBTN = view.findViewById(R.id.facebook_img_btn);
    }
}