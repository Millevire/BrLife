package com.example.esteban.brlife;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent =new Intent(this,MenuPrincipalActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



              startActivity(intent);
              finish();
            }
        },1000);

    }

}
