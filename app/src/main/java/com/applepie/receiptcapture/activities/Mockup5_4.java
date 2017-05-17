package com.applepie.receiptcapture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Mockup5_4 extends AppCompatActivity {
TextView mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mockup5_4);
        mBack=(TextView)findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent=new Intent(Mockup5_4.this,SignIn.class);
                    startActivity(intent);
                    finish();

            }
        });
    }
}
