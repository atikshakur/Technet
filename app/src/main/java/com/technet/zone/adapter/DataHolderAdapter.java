package com.technet.zone.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.technet.zone.R;
import com.technet.zone.extendedActivitys.BkExtendedNewsActivity;
import com.technet.zone.model.DataModel;

import java.util.ArrayList;

public class DataHolderAdapter extends ArrayAdapter<DataModel> {

    ArrayList<DataModel> dataModels = new ArrayList<>();
    Context context;


    public DataHolderAdapter(@NonNull Context context, ArrayList<DataModel> dataModels) {
        super(context, R.layout.bk_list_item, dataModels);

        this.context = context;
        this.dataModels = dataModels;
    }

    class Holder {
        ListView listView;
        RelativeLayout relativeLayout;
        TextView bkTitle, bkCatagory, bkWritter, bkDetailnews1, bkDetailnews2, bkDetailnews3;
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
            view = layoutInflater.inflate(R.layout.bk_list_item, null);
            holder = new Holder();

            holder.relativeLayout = view.findViewById(R.id.bk_list_item_id);
            holder.listView = view.findViewById(R.id.bookmark_listView);

            holder.bkTitle = view.findViewById(R.id.bk_post_title);
            holder.bkImage = view.findViewById(R.id.bk_post_image);
            holder.bkCatagory = view.findViewById(R.id.bk_post_catagory);
            holder.bkWritter = view.findViewById(R.id.bk_post_writter);
            holder.bkDetailnews1 = view.findViewById(R.id.bk_post_detailnews1);
            holder.bkDetailnews2 = view.findViewById(R.id.bk_post_detailnews2);
            holder.bkDetailnews3 = view.findViewById(R.id.bk_post_detailnews3);
            //          holder.bkImage = view.findViewById( R.id.bk_post_image );

            view.setTag(holder);

        } else {
            holder = (Holder) view.getTag();
        }

        final DataModel dm = dataModels.get(position);

        holder.bkTitle.setText(dm.getTitle());
        Glide.with(context).load(dm.getImage()).into(holder.bkImage);
        holder.bkCatagory.setText(dm.getCatagory());
        holder.bkWritter.setText(dm.getWritter());
        holder.bkDetailnews1.setText(dm.getDetailnews1());
        holder.bkDetailnews2.setText(dm.getDetailnews2());
        holder.bkDetailnews3.setText(dm.getDetailnews3());
        //       holder.bkImage.setText(dm.getTitle());


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BkExtendedNewsActivity.class);

                String title = dm.getTitle();
                String image = dm.getImage();
                String catagory = dm.getCatagory();
                String writter = dm.getWritter();
                String detailnews1 = dm.getDetailnews1();
                String detailnews2 = dm.getDetailnews2();
                String detailnews3 = dm.getDetailnews3();

                intent.putExtra("title", title);
                intent.putExtra("image", image);
                intent.putExtra("catagory", catagory);
                intent.putExtra("writter", writter);
                intent.putExtra("detailnews1", detailnews1);
                intent.putExtra("detailnews2", detailnews2);
                intent.putExtra("detailnews3", detailnews3);
                getContext().startActivity(intent);
            }
        });
        return view;
    }
}
