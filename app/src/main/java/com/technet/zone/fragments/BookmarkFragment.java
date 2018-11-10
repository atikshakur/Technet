package com.technet.zone.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.technet.zone.adapter.DataHolderAdapter;
import com.technet.zone.R;
import com.technet.zone.dbHelper.Easydb;
import com.technet.zone.model.DataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class BookmarkFragment extends Fragment implements Serializable {

    private View v;
    private ListView listView;
    private Easydb easydb;
    private String title, image,catagory, writter, detailnews1, detailnews2, detailnews3;
    private DataHolderAdapter adapter;
    private Toolbar toolbar;

    public static ArrayList<DataModel> dataModel = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate( R.layout.fragment_bookmark, container, false );

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        this.v = view;

        toolbar = v.findViewById( R.id.bookmark_toolbar );
        ((AppCompatActivity) Objects.requireNonNull( getActivity() )).setSupportActionBar(toolbar);
        Objects.requireNonNull( ((AppCompatActivity) Objects.requireNonNull( getActivity() )).getSupportActionBar() )
                .setTitle( "Saved Articles" );

        easydb = new Easydb( getContext() );

        listView = v.findViewById( R.id.bookmark_listView );
        adapter = new DataHolderAdapter( Objects.requireNonNull( getContext() ), dataModel);
        listView.setAdapter(adapter);

        Cursor cursor = easydb.getAllData();

        dataModel.clear();

        while(cursor.moveToNext()){
            image = cursor.getString(1);
            title = cursor.getString( 2 );
            writter = cursor.getString( 6 );
            detailnews1 = cursor.getString( 3 );
            detailnews2 = cursor.getString( 4 );
            detailnews3 = cursor.getString( 5);
            catagory = cursor.getString( 7 );
            dataModel.add(new DataModel(title, image,writter, detailnews1, detailnews2, detailnews3, catagory));
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu2, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected( item );
        int id = item.getItemId();
        switch (id){
            case R.id.delete:
                deleteAll();
                break;
        }
        return true;
    }
    private void deleteAll(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Delete all saved articles?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                easydb.deleteAllDataFromTable();
                getFragmentManager().beginTransaction().detach(BookmarkFragment.this).attach(BookmarkFragment.this).commit();
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
