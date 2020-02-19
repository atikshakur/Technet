package com.technet.zone.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.technet.zone.Activities.WebviewActivity;
import com.technet.zone.R;
import com.technet.zone.dbHelper.DbHelper;
import com.technet.zone.extendedActivitys.BkExtendedNewsActivity;
import com.technet.zone.model.Newsv2;
import com.thefinestartist.finestwebview.FinestWebView;


public class RecyclerAdapterV2 extends FirestoreRecyclerAdapter<Newsv2, RecyclerAdapterV2.NewsViewHolderV3>{

    public RecyclerAdapterV2(@NonNull FirestoreRecyclerOptions<Newsv2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final NewsViewHolderV3 holder, int position, @NonNull final Newsv2 model) {
        holder.title.setText(model.getTitle());
        holder.source.setText(model.getSource());

        Glide.with(holder.image.getContext())
                .asBitmap()
                .load(model.getImageUrl())
                .transform(new CenterCrop(), new RoundedCorners(8))
                .into(holder.image);

        holder.singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FinestWebView.Builder(v.getContext()).show(model.getUrl());
            }
        });

        holder.bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper();
                dbHelper.addColumns(v.getContext());
                dbHelper.addDataToLocalDb(model.getUrl(), model.getImageUrl(), model.getTitle());
            }
        });
    }

    @NonNull
    @Override
    public NewsViewHolderV3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new RecyclerAdapterV2.NewsViewHolderV3(view);
    }

//    @Override
//    public int getItemCount() {
//        return 0;
//    }

    class NewsViewHolderV3 extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView source;
        private ImageView image;
        private RelativeLayout singleItem;
        private ImageButton bookmarkButton;

        public NewsViewHolderV3(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titlev2);
            source = itemView.findViewById(R.id.source);
            image = itemView.findViewById(R.id.img);
            singleItem = itemView.findViewById(R.id.single_item);
            bookmarkButton = itemView.findViewById(R.id.bookmark_iconv2);
        }
    }
}
