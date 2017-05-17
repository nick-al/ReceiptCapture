package com.applepie.receiptcapture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignIn extends AppCompatActivity {
    Button mLogin, mSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        mLogin = (Button) findViewById(R.id.login);
        mSignup = (Button) findViewById(R.id.singup);

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignIn.this,Signup.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
