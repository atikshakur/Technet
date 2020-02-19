package com.technet.zone.Activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.onesignal.OneSignal;
import com.technet.zone.R;
import com.technet.zone.fragments.BookmarkFragment;
import com.technet.zone.fragments.HomeFragmentv2;
import com.technet.zone.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //OneSignal
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottom_nav);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, new HomeFragmentv2())
                .commit();

        bottomNavigationViewEx.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new HomeFragmentv2();
                                break;
                            case R.id.nav_bookmark:
                                selectedFragment = new BookmarkFragment();
                                break;
                            case R.id.nav_settings:
                                selectedFragment = new SettingsFragment();
                                break;
                        }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, selectedFragment)
                                .commit();
                        return true;
                    }
                });

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
        bottomNavigationViewEx.setIconSize(24, 24);

    }
}
