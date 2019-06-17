package com.example.esteban.brlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Form2Activity extends AppCompatActivity {
  private Button btnSiguenteForm2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);

        btnSiguenteForm2=(Button)findViewById(R.id.btnSiguenteForm2);
        final Intent intent =new Intent(this,Fomr3Activity.class);

        btnSiguenteForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
