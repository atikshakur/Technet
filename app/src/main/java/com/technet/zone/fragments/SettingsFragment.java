package com.technet.zone.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import com.technet.zone.R;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private ListView listViewSupport, listViewAbout;
    private String[] supportList = {"Provide Feedback", "Rate App", "Share App", };
    private String[] about = {"Version 1.0"};
    View v;
    private Toolbar toolbar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_settings, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        this.v = view;

        toolbar = v.findViewById( R.id.settings_toolbar );
        ((AppCompatActivity) Objects.requireNonNull( getActivity() )).setSupportActionBar(toolbar);
        Objects.requireNonNull( ((AppCompatActivity) Objects.requireNonNull( getActivity() )).getSupportActionBar() )
                .setTitle( "Settings" );

        listViewSupport = v.findViewById( R.id.settings_support_listview );
        ArrayAdapter<String> adapter = new ArrayAdapter<>( getContext(), android.R.layout.simple_list_item_1, supportList );
        listViewSupport.setAdapter( adapter );

        listViewAbout = v.findViewById( R.id.about_listview_settings );
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>( getContext(), android.R.layout.simple_list_item_1, about );
        listViewAbout.setAdapter( adapter2 );
    }
}
