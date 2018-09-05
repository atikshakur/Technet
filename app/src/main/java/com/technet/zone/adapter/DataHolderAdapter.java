package com.technet.zone.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technet.zone.R;
import com.technet.zone.extendedActivitys.BkExtendedNewsActivity;
import com.technet.zone.model.DataModel;

import java.util.ArrayList;

/**
 * Created by p32929 on 9/5/2018.
 */

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

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        Holder holder;
        View view = null;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.bk_list_item, null);
            holder = new Holder();

            holder.relativeLayout =  view.findViewById(R.id.bk_list_item_id);
            holder.listView = view.findViewById( R.id.bookmark_listView );

            holder.bkTitle =  view.findViewById(R.id.bk_post_title);
            holder.bkCatagory =  view.findViewById(R.id.bk_post_catagory);
            holder.bkWritter = view.findViewById( R.id.bk_post_writter );
            holder.bkDetailnews1 = view.findViewById( R.id.bk_post_detailnews1 );
            holder.bkDetailnews2 = view.findViewById( R.id.bk_post_detailnews2 );
            holder.bkDetailnews3 = view.findViewById( R.id.bk_post_detailnews3 );
  //          holder.bkImage = view.findViewById( R.id.bk_post_image );

            view.setTag(holder);

        } else {
            holder = (Holder) view.getTag();
        }

        final DataModel dm = dataModels.get(position);

        holder.bkTitle.setText(dm.getTitle());
        holder.bkCatagory.setText(dm.getCatagory());
        holder.bkWritter.setText(dm.getWritter());
        holder.bkDetailnews1.setText(dm.getDetailnews1());
        holder.bkDetailnews2.setText(dm.getDetailnews2());
        holder.bkDetailnews3.setText(dm.getDetailnews3());
 //       holder.bkImage.setText(dm.getTitle());


//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog dialog = new AlertDialog.Builder(context)
//                        .setTitle("Showing all data")
//                        .setMessage("C1 = " + dm.getC1() + "\n"
//                                + " C2 = " + dm.getC2() + "\n"
//                                + " C3 = " + dm.getC3() + "\n"
//                                + " C4 = " + dm.getC4() + "\n"
//                                + " C5 = " + dm.getC5() + "\n"
//                                + " C6 = " + dm.getC6() + "\n"
//                                + " C7 = " + dm.getC7() + "\n"
//                        )
//                        .setPositiveButton("OK", null)
//                        .create();
//                dialog.show();
//            }
//        });
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getContext(), BkExtendedNewsActivity.class );

                String title = dm.getTitle();
                String catagory = dm.getCatagory();
                String writter = dm.getWritter();
                String detailnews1 = dm.getDetailnews1();
                String detailnews2 = dm.getDetailnews2();
                String detailnews3 = dm.getDetailnews3();

                intent.putExtra( "title", title );
                intent.putExtra( "catagory", catagory );
                intent.putExtra( "writter", writter );
                intent.putExtra( "detailnews1", detailnews1 );
                intent.putExtra( "detailnews2", detailnews2 );
                intent.putExtra( "detailnews3", detailnews3 );
                getContext().startActivity( intent );

            }
        });


        return view;
    }
}
