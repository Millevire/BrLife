package com.example.esteban.brlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Form1Activity extends AppCompatActivity {
  private Button btnSifuenteForm2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
        btnSifuenteForm2=(Button)findViewById(R.id.btnSiguenteForm2);
        final Intent intent =new Intent(this,Form2Activity.class);

        btnSifuenteForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
