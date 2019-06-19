package com.example.esteban.brlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Fomr3Activity extends AppCompatActivity {
 private Button btnFinalizarRegistroForm3,btnBackForm3;
 private EditText etNombreUsuarioForm3,etContrasenaForm3,etRepitaContrasenaForm3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fomr3);

        //Declaracion de Widgett
        btnFinalizarRegistroForm3=(Button)findViewById(R.id.btnFinalizarRegistroForm3);
        btnBackForm3=(Button)findViewById(R.id.btnBackForm3);

        etNombreUsuarioForm3=(EditText)findViewById(R.id.etNombreUsuarioForm3);
        etContrasenaForm3=(EditText)findViewById(R.id.etContrasenaForm3);
        etRepitaContrasenaForm3=(EditText)findViewById(R.id.etRepitaContrasenaForm3);



        final Intent intent =new Intent(this,MenuLateralActivity.class);




        btnFinalizarRegistroForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etContrasenaForm3.getText().toString().equals(etRepitaContrasenaForm3.getText().toString())){

                    if (etRepitaContrasenaForm3.getText().toString().equals("") && etRepitaContrasenaForm3.getText().toString().equals("") && etRepitaContrasenaForm3.getText().toString().equals("") ){
                        if (etNombreUsuarioForm3.getText().toString().equals("")) etNombreUsuarioForm3.setError("Campo requerido");
                        else  if (etContrasenaForm3.getText().toString().equals("")) etContrasenaForm3.setError("Campo requerido");
                        else  if (etRepitaContrasenaForm3.getText().toString().equals("")) etRepitaContrasenaForm3.setError("Campo requerido");
                    }else startActivity(intent);

                }else etRepitaContrasenaForm3.setError("Las contraseñas no coinciden");


            }
        });


        //Ir a activiad anterior
        btnBackForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
