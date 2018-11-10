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

import java.util.Objects;

public class ExtendedNewsActivity extends AppCompatActivity {

    private ImageView extendedImageView, backButton;
    private TextView byWritter;
    private TextView detailsNews1, detailsNews2, detailsNews3;
    private TextView titleText;
    private TextView mcatagory;

    private static final String TAG = "ExtendedNewsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extended_newsactivity);

        // Hide the status bar.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = findViewById(R.id.extended_news_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getIncomongIntent();

        Log.d(TAG, "onCreate: started");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void getIncomongIntent() {
        if (getIntent().hasExtra("image") && getIntent().hasExtra("title")
                && getIntent().hasExtra("detailnews1") && getIntent().hasExtra("detailnews2")
                && getIntent().hasExtra("detailnews3") && getIntent().hasExtra("writter")
                && getIntent().hasExtra("catagory")) {
            Log.d(TAG, "getIncomongIntent: found intent extras");

            String image = getIntent().getStringExtra("image");
            String title = getIntent().getStringExtra("title");
            String detailnews1 = getIntent().getStringExtra("detailnews1");
            String detailnews2 = getIntent().getStringExtra("detailnews2");
            String detailnews3 = getIntent().getStringExtra("detailnews3");
            String writter = getIntent().getStringExtra("writter");
            String catagory = getIntent().getStringExtra("catagory");

            setExtra(image, title, detailnews1, writter, detailnews2, detailnews3, catagory);
        }
    }

    private void setExtra(String image, String title,
                          String detailnews1, String writter, String detailnews2, String detailnews3, String catagory) {

        extendedImageView = findViewById(R.id.detail_news_image);
        Glide.with(this)
                .asBitmap()
                .load(image)
                .into(extendedImageView);

        mcatagory = findViewById(R.id.catagory_text);
        mcatagory.setText(catagory);

        byWritter = findViewById(R.id.writer);
        byWritter.setText(writter);

        titleText = findViewById(R.id.titleText);
        titleText.setText(title);

        detailsNews1 = findViewById(R.id.detail_news_string1);
        detailsNews1.setText(detailnews1);

        detailsNews2 = findViewById(R.id.detail_news_string2);
        detailsNews2.setText(detailnews2);

        detailsNews3 = findViewById(R.id.detail_news_string3);
        detailsNews3.setText(detailnews3);

    }


}
