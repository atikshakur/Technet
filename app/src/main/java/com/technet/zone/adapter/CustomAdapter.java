package com.technet.zone.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.technet.zone.R;

public class CustomAdapter extends CursorAdapter {
    private TextView bkPostTileSingleItem;
    private TextView bkPostCatagorySingleItem;

    public CustomAdapter(Context context, Cursor c) {
        super( context, c );
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate( R.layout.bk_list_item, parent, false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        bkPostTileSingleItem = view.findViewById(R.id.bk_post_title);
        bkPostTileSingleItem.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));

        bkPostCatagorySingleItem = view.findViewById(R.id.bk_post_catagory);
        bkPostCatagorySingleItem.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(7))));

    }

    public String getBkPostCatagorySingleItem() {
        return bkPostCatagorySingleItem.getText().toString();
    }

    public String getBkPostTileSingleItem() {
        return bkPostTileSingleItem.getText().toString();
    }
}
