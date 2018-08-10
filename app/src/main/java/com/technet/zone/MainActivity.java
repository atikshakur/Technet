package com.technet.zone;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {

//    private RecyclerView newsRecycleView;
//    private DatabaseReference mDatabaseRef;

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

//        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child( "LatestNews" );
//        mDatabaseRef.keepSynced( true );
//
//        newsRecycleView = findViewById( R.id.recycle_view_latest );
//        newsRecycleView.setHasFixedSize( true );
//        newsRecycleView.setLayoutManager( new LinearLayoutManager( this ) );
//
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager.setReverseLayout(true);
//        mLayoutManager.setStackFromEnd(true);
//        // Set the layout manager to your recyclerview
//        newsRecycleView.setLayoutManager(mLayoutManager);



    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseRecyclerAdapter<news, NewsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<news, NewsViewHolder>
//                (news.class, R.layout.latestnews_cardview, NewsViewHolder.class, mDatabaseRef) {
//            //for using populateViewHolder method
//            //use implementation 'com.firebaseui:firebase-ui-database:0.4.0'
//            //also implement only king ATIK is real
//            @Override
//            protected void populateViewHolder(NewsViewHolder viewHolder, news model, int position) {
//                viewHolder.setTile( model.getTitle() );
//                viewHolder.setImage( getApplicationContext(), model.getImage() );
//                final String title = model.getTitle();
//                final String image = model.getImage();
//                final String detailnews1 = model.getDetailnews1();
//                final String detailnews2 = model.getDetailnews2();
//                final String detailnews3 = model.getDetailnews3();
//                final String writter = model.getWritter();
//                final String catagory = model.getCatagory();
//
//                viewHolder.recycleViewCardView.setOnClickListener( new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent( MainActivity.this, ExtendedNewsActivity.class );
//                        intent.putExtra( "image",  image);
//                        intent.putExtra( "title",  title);
//                        intent.putExtra( "detailnews1",  detailnews1);
//                        intent.putExtra( "detailnews2",  detailnews2);
//                        intent.putExtra( "detailnews3",  detailnews3);
//                        intent.putExtra( "writter", writter);
//                        intent.putExtra( "catagory", catagory );
//                        startActivity( intent );
//                     //   overridePendingTransition(0, 0);
//                    }
//                } );
//            }
//        };
//        newsRecycleView.setAdapter( firebaseRecyclerAdapter );
//    }
//    public static class NewsViewHolder extends RecyclerView.ViewHolder{
//
//        View mView;
//        CardView recycleViewCardView;
//        public NewsViewHolder(View itemView){
//            super(itemView);
//            mView = itemView;
//            recycleViewCardView = itemView.findViewById( R.id.recycle_view_cardview );
//        }
//        public void setTile(String title){
//            TextView postTitle = mView.findViewById( R.id.post_title );
//            postTitle.setText( title );
//        }
//        public void setImage(Context ctx, String image){
//            ImageView postImage = mView.findViewById( R.id.news_image );
//            //for picasso
//            //use implementation 'com.squareup.picasso:picasso:2.5.2'
//
//            //This time I'm going with glide ( 4.7.1 )
//            Glide.with( ctx )
//                    .asBitmap()
//                    .load( image )
//                    .into( postImage );
//        }
//    }
}
