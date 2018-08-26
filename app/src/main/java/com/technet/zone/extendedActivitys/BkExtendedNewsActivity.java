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

    private ImageView extendedImageView, backButton;
    private TextView byWritter;
    private TextView detailsNews1, detailsNews2, detailsNews3;
    private TextView titleText;
    private TextView mcatagory;

    private static final String TAG = "ExtendedNewsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.bk_extended_news_activity );

        titleText = findViewById( R.id.bk_titleText );
        mcatagory = findViewById( R.id.bk_catagory_text );
        byWritter = findViewById( R.id.bk_writer );
        detailsNews1 = findViewById( R.id.bk_detail_news_string1 );
        detailsNews2 = findViewById( R.id.bk_detail_news_string2 );
        detailsNews3 = findViewById( R.id.bk_detail_news_string3 );


        Bundle bundle = getIntent().getExtras();


        String title = bundle.getString( "title" );
        titleText.setText( title );

        String catagory = bundle.getString( "title" );
        titleText.setText( title );

        String writter = bundle.getString( "title" );
        titleText.setText( title );

        String detailnews1 = bundle.getString( "title" );
        titleText.setText( title );

        String detailnews2 = bundle.getString( "title" );
        titleText.setText( title );

        String detailnews3 = bundle.getString( "title" );
        titleText.setText( title );

        Log.d( TAG, "onCreate: started" );
    }

//    private void getIncomongIntent(){
////        if (getIntent().hasExtra( "image" ) && getIntent().hasExtra( "title" )
////                && getIntent().hasExtra( "detailnews1" ) && getIntent().hasExtra( "detailnews2" )
////                && getIntent().hasExtra( "detailnews3" ) && getIntent().hasExtra( "writter" )
////                && getIntent().hasExtra( "catagory" )){
////            Log.d( TAG, "getIncomongIntent: found intent extras" );
////
////            String image = getIntent().getStringExtra( "image" );
//            String title = getIntent().getStringExtra( "title" );
////            String detailnews1 = getIntent().getStringExtra( "detailnews1" );
////            String detailnews2 = getIntent().getStringExtra( "detailnews2" );
////            String detailnews3 = getIntent().getStringExtra( "detailnews3" );
////            String writter = getIntent().getStringExtra( "writter" );
//     //       String catagory = getIntent().getStringExtra( "catagory" );
////
//            setExtra(
//       //             image,
//                    title,
//        //            detailnews1,
//       //             writter,
//        //            detailnews2,
//        //            detailnews3,
//                    catagory );
////
//        }


//    private void setExtra(
//          //                  String image,
//                          String title,
//         ////                 String detailnews1,
//           //               String writter,
//         //                 String detailnews2,
//         //                 String detailnews3,
//                          String catagory)
//    {
//
////        extendedImageView = findViewById( R.id.bk_detail_news_image );
////        Glide.with( this )
////                .asBitmap()
////                .load( image )
////                .into( extendedImageView );
//
//        mcatagory = findViewById( R.id.bk_catagory_text );
//        mcatagory.setText( catagory );
//
////        byWritter = findViewById( R.id.bk_writer );
////        byWritter.setText( writter );
//
//        titleText = findViewById( R.id.bk_titleText );
//        titleText.setText( title );
////
////        detailsNews1 = findViewById( R.id.bk_detail_news_string1 );
////        detailsNews1.setText( detailnews1 );
////
////        detailsNews2 = findViewById( R.id.bk_detail_news_string2 );
////        detailsNews2.setText( detailnews2 );
////
////        detailsNews3 = findViewById( R.id.bk_detail_news_string3 );
////        detailsNews3.setText( detailnews3 );
//
//    }

}
