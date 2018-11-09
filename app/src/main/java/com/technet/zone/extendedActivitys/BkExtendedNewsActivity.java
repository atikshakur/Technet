package com.technet.zone.extendedActivitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

    private static final String TAG = "ExtendedNewsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.bk_extended_news_activity );

        // Hide the status bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        titleText = findViewById( R.id.bk_titleText );
        mcatagory = findViewById( R.id.bk_catagory_text );
        byWritter = findViewById( R.id.bk_writer );
        detailsNews1Text = findViewById( R.id.bk_detail_news_string1 );
        detailsNews2Text = findViewById( R.id.bk_detail_news_string2 );
        detailsNews3Text = findViewById( R.id.bk_detail_news_string3 );

        if (getIntent().hasExtra( "title" ) && getIntent().hasExtra( "catagory" )){
            String title = getIntent().getStringExtra( "title" );
            String catagory = getIntent().getStringExtra( "catagory" );
            String writter = getIntent().getStringExtra( "writter" );
            String detailnews1 = getIntent().getStringExtra( "detailnews1" );
            String detailnews2 = getIntent().getStringExtra( "detailnews2" );
            String detailnews3 = getIntent().getStringExtra( "detailnews3" );

            titleText.setText( title );
            mcatagory.setText( catagory );
            byWritter.setText( writter );
            detailsNews1Text.setText( detailnews1 );
            detailsNews2Text.setText( detailnews2 );
            detailsNews3Text.setText( detailnews3 );
        }
    }
}
