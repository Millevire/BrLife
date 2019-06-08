package com.example.esteban.brlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button btnRegistar,btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Intent in1=new Intent(this,Form1Activity.class);
        final Intent in2=new Intent(this,MenuLateralActivity.class);
        btnRegistar=(Button)findViewById(R.id.btnRegistrar);
        btnIngresar=(Button)findViewById(R.id.btnIngresar);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in1);
            }
        });
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in2);
            }
        });

    }
}
