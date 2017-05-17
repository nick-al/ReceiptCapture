package com.applepie.receiptcapture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Mockup6 extends AppCompatActivity {
    Button mMenu;
    TextView mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mockup6);
        mMenu = (Button) findViewById(R.id.menu);
        mBack = (TextView) findViewById(R.id.back);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Mockup6.this,Mockup5_2.class);
                startActivity(intent);
                finish();
            }
        });
        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(Mockup6.this, mMenu);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup1, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getTitle().equals("Share")){
                            Intent intent=new Intent(Mockup6.this,ShareWith.class);
                            startActivity(intent);
                            finish();
                        }
                        if (item.getTitle().equals("Log Out")){
                            Intent intent=new Intent(Mockup6.this,Mockup5_4.class);
                            startActivity(intent);
                            finish();
                        }
                        Toast.makeText(
                                Mockup6.this,
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
}
