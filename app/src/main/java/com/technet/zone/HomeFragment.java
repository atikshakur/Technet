package com.technet.zone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    private RecyclerView newsRecycleView;
    private RecyclerView trendingRecycleView;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mDatabaseRefTrending;
    View v;

    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_home, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        this.v = view;

        //latest news
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child( "LatestNews" );
        mDatabaseRef.keepSynced( true );
        //trending news
        mDatabaseRefTrending = FirebaseDatabase.getInstance().getReference().child( "TrendingNews" );
        mDatabaseRefTrending.keepSynced( true );

        newsRecycleView = v.findViewById(R.id.recycle_view_latest);
        newsRecycleView.setNestedScrollingEnabled(false);
        newsRecycleView.setHasFixedSize( true );

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        // Set the layout manager to your recyclerview
        newsRecycleView.setLayoutManager(mLayoutManager);

        Log.d( TAG, "onViewCreated: latest coming" );

        trendingRecycleView = v.findViewById(R.id.recycle_view_trending);
        trendingRecycleView.setNestedScrollingEnabled(false);
        trendingRecycleView.setHasFixedSize( true );

        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        // Set the layout manager to your recyclerview
        trendingRecycleView.setLayoutManager(mLayoutManager2);
        Log.d( TAG, "onViewCreated: trending coming" );
    }

    @Override
    public void onStart() {
        super.onStart();

        // FOR LATEST NEWS
        FirebaseRecyclerAdapter<news, NewsViewHolder1> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<news,
                NewsViewHolder1>
                (news.class, R.layout.latestnews_cardview, NewsViewHolder1.class, mDatabaseRef) {
            //for using populateViewHolder method
            //use implementation 'com.firebaseui:firebase-ui-database:0.4.0'
            //also implement only king ATIK is real
            @Override
            protected void populateViewHolder(NewsViewHolder1 viewHolder1, news model, int position) {
                viewHolder1.setTile( model.getTitle() );
                viewHolder1.setImage( getContext(), model.getImage() );
                final String title = model.getTitle();
                final String image = model.getImage();
                final String detailnews1 = model.getDetailnews1();
                final String detailnews2 = model.getDetailnews2();
                final String detailnews3 = model.getDetailnews3();
                final String writter = model.getWritter();
                final String catagory = model.getCatagory();
                Log.d( TAG, "onViewCreated: on start latest coming" );

                viewHolder1.recycleViewCardView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( getActivity(), ExtendedNewsActivity.class );
                        intent.putExtra( "image",  image);
                        intent.putExtra( "title",  title);
                        intent.putExtra( "detailnews1",  detailnews1);
                        intent.putExtra( "detailnews2",  detailnews2);
                        intent.putExtra( "detailnews3",  detailnews3);
                        intent.putExtra( "writter", writter);
                        intent.putExtra( "catagory", catagory );
                        startActivity( intent );
                        //   overridePendingTransition(0, 0);
                    }
                } );
            }
        };
        newsRecycleView.setAdapter( firebaseRecyclerAdapter );



        //FOR TRENDING
        FirebaseRecyclerAdapter<newsTrend, NewsViewHolder2> firebaseRecyclerAdapter2 = new FirebaseRecyclerAdapter<newsTrend,
                NewsViewHolder2>
                (newsTrend.class, R.layout.trending_cardview, NewsViewHolder2.class, mDatabaseRefTrending) {
            //for using populateViewHolder method
            //use implementation 'com.firebaseui:firebase-ui-database:0.4.0'
            //also implement only king ATIK is real
            @Override
            protected void populateViewHolder(NewsViewHolder2 viewHolder2, newsTrend model1, int position1) {
                viewHolder2.setTile( model1.getTitle() );
                viewHolder2.setImage( getContext(), model1.getImage() );
                final String title = model1.getTitle();
                final String image = model1.getImage();
                final String detailnews1 = model1.getDetailnews1();
                final String detailnews2 = model1.getDetailnews2();
                final String detailnews3 = model1.getDetailnews3();
                final String writter = model1.getWritter();
                final String catagory = model1.getCatagory();
                Log.d( TAG, "onViewCreated: onstart trending coming" );

                viewHolder2.recycleViewCardView2.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( getActivity(), ExtendedNewsActivity.class );
                        intent.putExtra( "image",  image);
                        intent.putExtra( "title",  title);
                        intent.putExtra( "detailnews1",  detailnews1);
                        intent.putExtra( "detailnews2",  detailnews2);
                        intent.putExtra( "detailnews3",  detailnews3);
                        intent.putExtra( "writter", writter);
                        intent.putExtra( "catagory", catagory );
                        startActivity( intent );
                        //   overridePendingTransition(0, 0);
                    }
                } );
            }
        };
        trendingRecycleView.setAdapter( firebaseRecyclerAdapter2 );
    }
    public static class NewsViewHolder1 extends RecyclerView.ViewHolder{

        View mView;
        CardView recycleViewCardView;
        public NewsViewHolder1(View itemView){
            super(itemView);
            mView = itemView;
            recycleViewCardView = itemView.findViewById( R.id.recycle_view_cardview );
        }
        public void setTile(String title){
            Log.d( TAG, "setTile: setting title latest news" );
            TextView postTitle = mView.findViewById( R.id.post_title );
            postTitle.setText( title );
        }
        public void setImage(Context ctx, String image){
            Log.d( TAG, "setImage: setting image latest news" );
            ImageView postImage = mView.findViewById( R.id.news_image );
            //for picasso
            //use implementation 'com.squareup.picasso:picasso:2.5.2'

            //This time I'm going with glide ( 4.7.1 )
            Glide.with( ctx )
                    .asBitmap()
                    .load( image )
                    .into( postImage );
        }
    }
    public static class NewsViewHolder2 extends RecyclerView.ViewHolder{

        View mView2;
        CardView recycleViewCardView2;
        public NewsViewHolder2(View itemView2){
            super(itemView2);
            mView2 = itemView;
            recycleViewCardView2 = itemView2.findViewById( R.id.recycle_view_cardview_trending );
        }
        public void setTile(String title){
            Log.d( TAG, "setTile: setting title trending news" );
            TextView postTitle = mView2.findViewById( R.id.post_title );
            postTitle.setText( title );
        }
        public void setImage(Context ctx, String image){
            Log.d( TAG, "setTile: setting image trending news" );
            ImageView postImage = mView2.findViewById( R.id.news_image );
            //for picasso
            //use implementation 'com.squareup.picasso:picasso:2.5.2'

            //This time I'm going with glide ( 4.7.1 )
            Glide.with( ctx )
                    .asBitmap()
                    .load( image )
                    .into( postImage );
        }
    }
}
