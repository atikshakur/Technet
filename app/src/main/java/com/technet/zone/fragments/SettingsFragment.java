package com.technet.zone.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.technet.zone.R;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private ListView listViewSupport, listViewAbout;
    private String[] supportList = {"Provide Feedback", "Rate App", "Share App",};
    private String[] about = {"Version 1.1.1"};
    View v;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;

        toolbar = v.findViewById(R.id.settings_toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar())
                .setTitle("Settings");

        listViewSupport = v.findViewById(R.id.settings_support_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, supportList);
        listViewSupport.setAdapter(adapter);

        listViewSupport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        openEmailClient();
                        break;
                    case 1:
                        rateApp();
                        break;
                    case 2:
                        shareApp();
                        break;
                }
            }
        });

        listViewAbout = v.findViewById(R.id.about_listview_settings);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, about);
        listViewAbout.setAdapter(adapter2);
    }

    private void openEmailClient() {
        String[] recipients = {"technetdevs@gmail.com"};
        String subject = "Technet app review";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an Email client"));
    }

    private void rateApp() {
        final String appPackageName = "com.technet.zone"; // getPackageName() from Context or Activity object
        try {
            Intent rateAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
            startActivity(rateAppIntent);
        } catch (android.content.ActivityNotFoundException anfe) {
            Intent rateAppIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
            startActivity(rateAppIntent2);
        }
    }

    private void shareApp() {
        Intent shareAppIntent = new Intent(Intent.ACTION_SEND);
        shareAppIntent.setType("text/plain");
        String appLink = "Hi! Get the Technet app : https://play.google.com/store/apps/details?id=com.technet.zone";
        shareAppIntent.putExtra(Intent.EXTRA_TEXT, appLink);
        startActivity(Intent.createChooser(shareAppIntent, "Share Using"));
    }

}
