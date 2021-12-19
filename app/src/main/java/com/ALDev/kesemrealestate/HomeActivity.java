package com.ALDev.kesemrealestate;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ALDev.kesemrealestate.Fragments.contactFragment;
import com.ALDev.kesemrealestate.Fragments.homeFragment;
import com.ALDev.kesemrealestate.Fragments.likedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private Fragment homeFragment;
    private Fragment likedFragment;
    private Fragment contactFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initFragments();
        bottomNavViewSwitcher();

    }

    private void initFragments() {
        homeFragment = new homeFragment();
        likedFragment = new likedFragment();
        contactFragment = new contactFragment();
    }

    /**
     * Init bottomNavView , set default page and accessing switcher method
     */
    private void bottomNavViewSwitcher() {
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setItemIconTintList(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.page_1_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.page_1_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                    return true;

                case R.id.page_2_like:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, likedFragment).commit();
                    return true;

                case R.id.page_3_contact:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, contactFragment).commit();
                    return true;
            }
            return false;
        });
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return false;
    }


}