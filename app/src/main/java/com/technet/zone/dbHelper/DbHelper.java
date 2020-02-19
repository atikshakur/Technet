package com.technet.zone.dbHelper;

import android.content.Context;

import com.technet.zone.adapter.DataHolderAdapter;

public class DbHelper {

    private Easydb easyDB;
    public void addColumns(Context context){
        easyDB = Easydb.init(context, null, 1)
                .setTableName("bookmarks")
                .addColumn(new Column("url", new DataType()._text_().unique().done()))
                .addColumn(new Column("imageUrl", new DataType()._text_().unique().done()))
                .addColumn(new Column("title", new DataType()._text_().unique().done()))
                .doneTableColumn();
    }

    public void addDataToLocalDb(String url, String imageUrl, String title){
        easyDB.addData("url", url)
                .addData("imageUrl", imageUrl)
                .addData("title", title)
                .doneDataAdding();
    }
}
