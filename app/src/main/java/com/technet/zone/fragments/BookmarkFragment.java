package com.technet.zone.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.technet.zone.adapter.DataHolderAdapter;
import com.technet.zone.R;
import com.technet.zone.dbHelper.Easydb;
import com.technet.zone.model.DataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BookmarkFragment extends Fragment implements Serializable {

    View v;
    ListView listView;
    Easydb easydb;
    ImageView bkPostImage;
    String title, catagory;
//    private CustomAdapter customAdapter;
    String writter,  detailnews1,  detailnews2,
                 detailnews3;

    List<String> titles = new ArrayList<>(  );

    ArrayList<String> bkTitle, bkImage, bkCatagory,
            bkDetailnews1, bkDetailnews2, bkDetailnews3,bkWritter;
    ArrayAdapter<String> adapterTitle, adapterCatagory;

    DataHolderAdapter adapter;

    public static ArrayList<DataModel> dataModel = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_bookmark, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        this.v = view;

        easydb = new Easydb( getContext() );

        listView = v.findViewById( R.id.bookmark_listView );
        adapter = new DataHolderAdapter( Objects.requireNonNull( getContext() ), dataModel);
        listView.setAdapter(adapter);

        Cursor cursor = easydb.getAllData();

//        String title, String writter, String detailnews1, String detailnews2,
//                String detailnews3, String catagory;
        while(cursor.moveToNext()){

            title = cursor.getString( 2 );
            writter = cursor.getString( 6 );
            detailnews1 = cursor.getString( 3 );
            detailnews2 = cursor.getString( 4 );
            detailnews3 = cursor.getString( 5);
            catagory = cursor.getString( 7 );
            dataModel.add(new DataModel(title, writter, detailnews1, detailnews2, detailnews3, catagory));
        }


//        bkTitle = new ArrayList<>(  );
//        bkCatagory = new ArrayList<>(  );


//        while(cursor.moveToNext()){
//            bkTitle.add( cursor.getString( 2 ) ) ;
//            bkCatagory.add( cursor.getString( 7 ) );
//        }
//        adapterTitle = new ArrayAdapter<>( Objects.requireNonNull( getContext() ), R.layout.bk_list_item, R.id.bk_post_title, bkTitle );
//        listView.setAdapter( adapterTitle );


//        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String title = listView.getItemAtPosition( position ).toString();
//
//                Intent intent = new Intent( getActivity(), BkExtendedNewsActivity.class );
////  //              intent.putExtra( "image", bkCardImage );
//                intent.putExtra( "title",  title);
//////                intent.putExtra( "title",  title);
//////                intent.putExtra( "detailnews1",  detailnews1);
//////                intent.putExtra( "detailnews2",  detailnews2);
//////                intent.putExtra( "detailnews3",  detailnews3);
//////                intent.putExtra( "writter", writter);
//////                intent.putExtra( "catagory", catagory );
//                startActivity( intent );
//            }
//        } );
    }
}
