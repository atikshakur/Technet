package com.technet.zone.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.technet.zone.Activities.WebviewActivity;
import com.technet.zone.R;
import com.technet.zone.model.DataModelV2;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;

public class DataHolderAdapter extends ArrayAdapter<DataModelV2> {

    private ArrayList<DataModelV2> dataModels = new ArrayList<>();
    Context context;

    public DataHolderAdapter(@NonNull Context context, ArrayList<DataModelV2> dataModels) {
        super(context, R.layout.bk_list_item_card, dataModels);

        this.context = context;
        this.dataModels = dataModels;
    }

    class Holder {
        ListView listView;
        CardView bkCardView;
        TextView bkTitle;
        ImageView bkImage;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Holder holder;
        View view = null;

        if (view == null) {
            assert layoutInflater != null;
            view = layoutInflater.inflate(R.layout.bk_list_item_card, null);
            holder = new Holder();

            holder.bkCardView = view.findViewById(R.id.bk_list_item_id);
            holder.listView = view.findViewById(R.id.bookmark_listView);
            holder.bkTitle = view.findViewById(R.id.bk_post_title);
            holder.bkImage = view.findViewById(R.id.bk_post_image);
//            holder.deleteBookmarkItem = view.findViewById(R.id.delete_bookmark_item);

            view.setTag(holder);

        } else {
            holder = (Holder) view.getTag();
        }

        final DataModelV2 dm = dataModels.get(position);

        holder.bkTitle.setText(dm.getTitle());
        Glide.with(context).load(dm.getImageUrl()).into(holder.bkImage);

        holder.bkCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FinestWebView.Builder(view.getContext()).show(dm.getUrl());
            }
        });
        return view;
    }
}
