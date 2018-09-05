package com.technet.zone.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.technet.zone.R;
import com.technet.zone.fragments.BookmarkFragment;
import com.technet.zone.fragments.HomeFragment;
import com.technet.zone.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        BottomNavigationViewEx bottomNavigationViewEx = findViewById( R.id.bottom_nav );
        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.frame_container,new HomeFragment() )
                .commit();

        bottomNavigationViewEx.setOnNavigationItemSelectedListener
                ( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragmet = null;

                switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragmet = new HomeFragment();
                            break;
                        case R.id.nav_bookmark:
                            selectedFragmet = new BookmarkFragment();
                            break;
                        case R.id.nav_settings:
                            selectedFragmet = new SettingsFragment();
                            break;
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace( R.id.frame_container,selectedFragmet )
                        .commit();
                return true;
            }
        } );

        bottomNavigationViewEx.enableAnimation( false );
        bottomNavigationViewEx.enableItemShiftingMode( false );
        bottomNavigationViewEx.enableShiftingMode( false );
        bottomNavigationViewEx.setTextVisibility( false );
        bottomNavigationViewEx.setIconSize( 28, 28 );
    }
}
