package com.applepie.receiptcapture;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.applepie.receiptcapture.adapters.PhotoAdapter;
import com.applepie.receiptcapture.events.OnItemLongClickEvent;
import com.applepie.receiptcapture.util.OttoBus;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HPENVY on 13/04/2017.
 */

public class PhotoList extends AppCompatActivity {

    private Bus bus = OttoBus.get();

    @BindView(R.id.recyclerPhotos) private RecyclerView photoRecycler;
    private PhotoAdapter adapter;
    public static SQLiteHelper sqLiteHelper;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_list_activity);
        ButterKnife.bind(this);

        sqLiteHelper = new SQLiteHelper(this, "PhotoDB.sqlite", null, 1);

        List<Photo> photos = new ArrayList<>();

        Cursor cursor = Mockup3.sqLiteHelper.getData("SELECT * FROM PHOTO");
        photos.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("Id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            photos.add(new Photo(name, id));
        }
        cursor.close();

        adapter = new PhotoAdapter(this, photos);
        GridLayoutManager glm = new GridLayoutManager(this, 1);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        photoRecycler.setLayoutManager(glm);
        photoRecycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Subscribe
    protected void onLongItemClick(final OnItemLongClickEvent event) {
        alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.title_delete)
                .setMessage(R.string.message_delete)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new DeleteAsyncTask().execute(event.getId());
                        adapter.removeItem(event.getId());
                        alertDialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();
    }

    private class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            sqLiteHelper.deleteData(integers[0]);
            ImageStorage.deleteImage(PhotoList.this, integers[0]);
            return null;
        }
    }
}
