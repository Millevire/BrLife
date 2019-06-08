package com.example.esteban.brlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fomr3Activity extends AppCompatActivity {
 private Button btnFinalizarRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fomr3);
        btnFinalizarRegistro=(Button)findViewById(R.id.btnFinalizarRegistro);
        final Intent intent =new Intent(this,MenuLateralActivity.class);
        btnFinalizarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
