package com.technet.zone.extendedActivitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.technet.zone.R;

import java.io.Serializable;

public class BkExtendedNewsActivity extends AppCompatActivity implements Serializable {

    private TextView byWritter;
    private TextView detailsNews1Text, detailsNews2Text, detailsNews3Text;
    private TextView titleText;
    private TextView mcatagory;
    private ImageView newsImage;

    private static final String TAG = "ExtendedNewsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bk_extended_news_activity);

        // Hide the status bar.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.bkextended_news_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleText = findViewById(R.id.bk_titleText);
        newsImage = findViewById(R.id.bk_detail_news_image);
        mcatagory = findViewById(R.id.bk_catagory_text);
        byWritter = findViewById(R.id.bk_writer);
        detailsNews1Text = findViewById(R.id.bk_detail_news_string1);
        detailsNews2Text = findViewById(R.id.bk_detail_news_string2);
        detailsNews3Text = findViewById(R.id.bk_detail_news_string3);

        if (getIntent().hasExtra("title") && getIntent().hasExtra("catagory")) {
            String title = getIntent().getStringExtra("title");
            String image = getIntent().getStringExtra("image");
            String catagory = getIntent().getStringExtra("catagory");
            String writter = getIntent().getStringExtra("writter");
            String detailnews1 = getIntent().getStringExtra("detailnews1");
            String detailnews2 = getIntent().getStringExtra("detailnews2");
            String detailnews3 = getIntent().getStringExtra("detailnews3");

            titleText.setText(title);
            Glide.with(BkExtendedNewsActivity.this).load(image).into(newsImage);
            mcatagory.setText(catagory);
            byWritter.setText(writter);
            detailsNews1Text.setText(detailnews1);
            detailsNews2Text.setText(detailnews2);
            detailsNews3Text.setText(detailnews3);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
