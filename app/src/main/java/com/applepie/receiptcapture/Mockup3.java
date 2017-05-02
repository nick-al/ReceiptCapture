package com.applepie.receiptcapture;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Seng301 Receipt Capture App
 *
 * Code written by Nick, Adriaan, Elijah, Kevin, Rohullah
 *
 */
public class Mockup3 extends AppCompatActivity {
    //global variables to initialise variables
    ImageView imageView;
    Button mCapture, mGallery, btnAdd, btnList;
    EditText edtName;
    public static SQLiteHelper sqLiteHelper;
    final int REQUEST_CODE_GALLERY = 999;
    final int REQUEST_CODE_CAMERA = 1337;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mockup3);

        init();

        sqLiteHelper = new SQLiteHelper(this, "PhotoDB.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS PHOTO (Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, image BLOB)");

        mCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        Mockup3.this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA
                );
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mockup3.this, PhotoList.class);
                startActivity(intent);
            }
        });

        mGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        Mockup3.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sqLiteHelper.insertData(
                            edtName.getText().toString().trim(),
                            imageViewToByte(imageView)
                    );
                    Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void takePicture() {
        Intent captureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (captureIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(captureIntent);
        }
    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(getApplication(), "You dont have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }else if(requestCode == REQUEST_CODE_CAMERA){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mCapture.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        takePicture();
                    }
                });
            }
            else{
                Toast.makeText(getApplication(), "You dont have permission to access this device's camera!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void init(){
        imageView = (ImageView) findViewById(R.id.imageView);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        edtName = (EditText) findViewById(R.id.edtName);
        mGallery = (Button) findViewById(R.id.gallery);
        mCapture = (Button) findViewById(R.id.capture);
        btnList = (Button) findViewById(R.id.btnList);
    }
}
