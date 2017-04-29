package com.applepie.receiptcapture;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by HPENVY on 13/04/2017.
 */

public class PhotoList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Photo> list;
    PhotoListAdapter adapter = null;
    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_list_activity);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new PhotoListAdapter(this, R.layout.photo_items, list);
        gridView.setAdapter(adapter);

        Cursor cursor = Mockup3.sqLiteHelper.getData("SELECT * FROM PHOTO");
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);

            list.add(new Photo(name, image, id));
        }
        adapter.notifyDataSetChanged();
    }
}
