package com.technet.zone.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.technet.zone.extendedActivitys.ExtendedNewsActivity;
import com.technet.zone.R;
import com.technet.zone.dbHelper.Column;
import com.technet.zone.dbHelper.DataType;
import com.technet.zone.dbHelper.Easydb;
import com.technet.zone.model.News;
import com.technet.zone.model.newsTrend;

public class HomeFragment extends Fragment {

    //RecyclerView
    private RecyclerView newsRecycleView;
    private RecyclerView trendingRecycleView;
    //db ref
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mDatabaseRefTrending;
    View v;
    private static final String TAG = "HomeFragment";
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;
        swipeRefreshLayout = v.findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.purple_text));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateLatestNews();
                populateTrendingNews();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //latest News
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("LatestNews");
        mDatabaseRef.keepSynced(true);

        newsRecycleView = v.findViewById(R.id.recycle_view_latest);
        newsRecycleView.setNestedScrollingEnabled(false);
        newsRecycleView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        // Set the layout manager to your recyclerview
        newsRecycleView.setLayoutManager(mLayoutManager);

        Log.d(TAG, "onViewCreated: latest coming");

        //trending News
        mDatabaseRefTrending = FirebaseDatabase.getInstance().getReference().child("TrendingNews");
        mDatabaseRefTrending.keepSynced(true);

        trendingRecycleView = v.findViewById(R.id.recycle_view_trending);
        trendingRecycleView.setNestedScrollingEnabled(true);
        trendingRecycleView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutManager2.setReverseLayout(true);
        mLayoutManager2.setStackFromEnd(true);
        // Set the layout manager to your recyclerview
        trendingRecycleView.setLayoutManager(mLayoutManager2);
        Log.d(TAG, "onViewCreated: trending coming");
        populateLatestNews();
        populateTrendingNews();
    }

    public void populateLatestNews() {
        // FOR LATEST NEWS
        final Easydb easydb = Easydb.init(getContext(), null, 1)
                .setTableName("BOOKMARK_TABLE")
                .addColumn(new Column("image", new DataType()._text_().unique().done()))
                .addColumn(new Column("title", new DataType()._text_().unique().done()))
                .addColumn(new Column("detailnews1", new DataType()._text_().unique().done()))
                .addColumn(new Column("detailnews2", new DataType()._text_().unique().done()))
                .addColumn(new Column("detailnews3", new DataType()._text_().unique().done()))
                .addColumn(new Column("writter", new DataType()._text_().done()))
                .addColumn(new Column("catagory", new DataType()._text_().done()))
                .doneTableColumn();

        FirebaseRecyclerAdapter<News, NewsViewHolder1> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<News,
                NewsViewHolder1>
                (News.class, R.layout.latestnews_cardview, NewsViewHolder1.class, mDatabaseRef) {
            //for using populateViewHolder method
            //use implementation 'com.firebaseui:firebase-ui-database:0.4.0'
            //also implement only king ATIK is real
            @Override
            protected void populateViewHolder(final NewsViewHolder1 viewHolder1, News model, int position) {
                viewHolder1.setTile(model.getTitle());
                viewHolder1.setImage(getContext(), model.getImage());
                final String image = model.getImage();
                final String title = model.getTitle();
                final String detailnews1 = model.getDetailnews1();
                final String detailnews2 = model.getDetailnews2();
                final String detailnews3 = model.getDetailnews3();
                final String writter = model.getWritter();
                final String catagory = model.getCatagory();
                Log.d(TAG, "onViewCreated: on start latest coming");

                viewHolder1.recycleViewCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ExtendedNewsActivity.class);
                        intent.putExtra("image", image);
                        intent.putExtra("title", title);
                        intent.putExtra("detailnews1", detailnews1);
                        intent.putExtra("detailnews2", detailnews2);
                        intent.putExtra("detailnews3", detailnews3);
                        intent.putExtra("writter", writter);
                        intent.putExtra("catagory", catagory);
                        startActivity(intent);
                        //   overridePendingTransition(0, 0);
                    }
                });

                viewHolder1.bookmarkButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                        easydb.addData("image", image)
                                .addData("title", title)
                                .addData("detailnews1", detailnews1)
                                .addData("detailnews2", detailnews2)
                                .addData("detailnews3", detailnews3)
                                .addData("writter", writter)
                                .addData("catagory", catagory)
                                .doneDataAdding();
                    }
                });

                viewHolder1.bookmarkButton.setOnClickListener(new View.OnClickListener() {
                    //init db


                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        };
        newsRecycleView.setAdapter(firebaseRecyclerAdapter);
    }

    public void populateTrendingNews() {
        //FOR TRENDING
        FirebaseRecyclerAdapter<newsTrend, NewsViewHolder2> firebaseRecyclerAdapter2 = new FirebaseRecyclerAdapter<newsTrend,
                NewsViewHolder2>
                (newsTrend.class, R.layout.trending_cardview, NewsViewHolder2.class, mDatabaseRefTrending) {
            //for using populateViewHolder method
            //use implementation 'com.firebaseui:firebase-ui-database:0.4.0'
            //also implement only king ATIK is real
            @Override
            protected void populateViewHolder(NewsViewHolder2 viewHolder2, newsTrend model1, int position1) {
                viewHolder2.setTile(model1.getTitle());
                viewHolder2.setImage(getContext(), model1.getImage());
                final String title = model1.getTitle();
                final String image = model1.getImage();
                final String detailnews1 = model1.getDetailnews1();
                final String detailnews2 = model1.getDetailnews2();
                final String detailnews3 = model1.getDetailnews3();
                final String writter = model1.getWritter();
                final String catagory = model1.getCatagory();
                Log.d(TAG, "onViewCreated: onstart trending coming");

                viewHolder2.recycleViewCardView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ExtendedNewsActivity.class);
                        intent.putExtra("image", image);
                        intent.putExtra("title", title);
                        intent.putExtra("detailnews1", detailnews1);
                        intent.putExtra("detailnews2", detailnews2);
                        intent.putExtra("detailnews3", detailnews3);
                        intent.putExtra("writter", writter);
                        intent.putExtra("catagory", catagory);
                        startActivity(intent);
                        //   overridePendingTransition(0, 0);
                    }
                });
            }
        };
        trendingRecycleView.setAdapter(firebaseRecyclerAdapter2);
    }

    public static class NewsViewHolder1 extends RecyclerView.ViewHolder {
        Context context;
        View mView;
        CardView recycleViewCardView;
        CheckBox bookmarkButton;

        //        context.getPreferences(Context.MODE_PRIVATE);
        public NewsViewHolder1(View itemView) {
            super(itemView);
            mView = itemView;
            recycleViewCardView = itemView.findViewById(R.id.recycle_view_cardview);
            bookmarkButton = itemView.findViewById(R.id.bookmark_icon);

        }

        public void setTile(String title) {
            Log.d(TAG, "setTile: setting title latest News");
            TextView postTitle = mView.findViewById(R.id.post_title);
            postTitle.setText(title);
        }

        public void setImage(Context ctx, String image) {
            Log.d(TAG, "setImage: setting image latest News");
            ImageView postImage = mView.findViewById(R.id.news_image);
            //for picasso
            //use implementation 'com.squareup.picasso:picasso:2.5.2'

            //This time I'm going with glide ( 4.7.1 )
            Glide.with(ctx)
                    .asBitmap()
                    .load(image)
                    .into(postImage);
        }
    }

    public static class NewsViewHolder2 extends RecyclerView.ViewHolder {

        View mView2;
        Context context;
        CardView recycleViewCardView2;

        public NewsViewHolder2(View itemView2) {
            super(itemView2);
            mView2 = itemView;
            recycleViewCardView2 = itemView2.findViewById(R.id.recycle_view_cardview_trending);
        }

        public void setTile(String title) {
            Log.d(TAG, "setTile: setting title trending News");
            TextView postTitle = mView2.findViewById(R.id.post_title);
            postTitle.setText(title);
        }

        public void setImage(Context ctx, String image) {
            Log.d(TAG, "setTile: setting image trending News");
            ImageView postImage = mView2.findViewById(R.id.news_image);
            //for picasso
            //use implementation 'com.squareup.picasso:picasso:2.5.2'

            //This time I'm going with glide ( 4.7.1 )
            Glide.with(ctx)
                    .asBitmap()
                    .load(image)
                    .into(postImage);
        }
    }
}
