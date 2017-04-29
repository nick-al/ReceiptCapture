package com.applepie.receiptcapture;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Mockup5_2 extends AppCompatActivity {
    Button mMenu;
    ArrayList<Product> products = new ArrayList<Product>();
    ListAdapter boxAdapter;
    TextView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mockup5_2);
        mMenu = (Button) findViewById(R.id.menu);
        mBack = (TextView) findViewById(R.id.back);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Mockup5_2.this,ReceiptCapture.class);
                startActivity(intent);
                finish();
            }
        });
        fillData();
        boxAdapter = new ListAdapter(this, products);

        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);


        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(Mockup5_2.this, mMenu);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("Share")){
                            Intent intent=new Intent(Mockup5_2.this,ShareWith.class);
                            startActivity(intent);
                            finish();
                        }

                        if (item.getTitle().equals("Log Out")){
                            Intent intent=new Intent(Mockup5_2.this,Mockup5_4.class);
                            startActivity(intent);
                            finish();
                        }

                        Toast.makeText(
                                Mockup5_2.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });
    }

    void fillData() {

        products.add(new Product("Random", "49 Receipts",
                R.drawable.image_placeholder, false));

        products.add(new Product("Flatmates", "9 Receipts",
                R.drawable.image_placeholder, false));

        products.add(new Product("Super Market", "13 Receipts",
                R.drawable.image_placeholder, false));

        products.add(new Product("Study", "8 receipts",
                R.drawable.image_placeholder, false));

        products.add(new Product("Personal", "22 Receipts",
                R.drawable.image_placeholder, false));

        products.add(new Product("Tools", "7 Receipts",
                R.drawable.image_placeholder, false));


    }

}


