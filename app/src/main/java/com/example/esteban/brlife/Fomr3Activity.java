package com.example.esteban.brlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.esteban.brlife.Clases.Usuario;

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

        //Bundle que traera objeto usuario
        final Bundle bundle=getIntent().getExtras();


       //Envio a siguente actividad
        final Intent intent =new Intent(this, ValidacionRegistroUsuarioActivity.class);


        //Validacion de campo vacio o espacios
        etNombreUsuarioForm3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etNombreUsuarioForm3.getText().length(); i++) {
                        if(Character.isWhitespace(etNombreUsuarioForm3.getText().charAt(i))) count++; }
                    if (count==etNombreUsuarioForm3.getText().length() || etNombreUsuarioForm3.getText().toString().equals("")) {
                        etNombreUsuarioForm3.setError("Ingrese un valor valido");
                        btnFinalizarRegistroForm3.setEnabled(false);
                    }else btnFinalizarRegistroForm3.setEnabled(true);
                }
            }
        });

        etContrasenaForm3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etContrasenaForm3.getText().length(); i++) {
                        if(Character.isWhitespace(etContrasenaForm3.getText().charAt(i))) count++; }
                    if (count==etContrasenaForm3.getText().length() || etContrasenaForm3.getText().toString().equals("")) {
                        etContrasenaForm3.setError("Ingrese un valor valido");
                        btnFinalizarRegistroForm3.setEnabled(false);
                    }else btnFinalizarRegistroForm3.setEnabled(true);
                }
            }
        });

        etRepitaContrasenaForm3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etRepitaContrasenaForm3.getText().length(); i++) {
                        if(Character.isWhitespace(etRepitaContrasenaForm3.getText().charAt(i))) count++; }
                    if (count==etRepitaContrasenaForm3.getText().length() || etRepitaContrasenaForm3.getText().toString().equals("")) {
                        etRepitaContrasenaForm3.setError("Ingrese un valor valido");
                        btnFinalizarRegistroForm3.setEnabled(false);
                    }else btnFinalizarRegistroForm3.setEnabled(true);
                }
            }
        });



        btnFinalizarRegistroForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etContrasenaForm3.getText().toString().equals(etRepitaContrasenaForm3.getText().toString())){

                    if (etRepitaContrasenaForm3.getText().toString().equals("") && etRepitaContrasenaForm3.getText().toString().equals("") && etRepitaContrasenaForm3.getText().toString().equals("") ){
                        if (etNombreUsuarioForm3.getText().toString().equals("")) etNombreUsuarioForm3.setError("Campo requerido");
                        else  if (etContrasenaForm3.getText().toString().equals("")) etContrasenaForm3.setError("Campo requerido");
                        else  if (etRepitaContrasenaForm3.getText().toString().equals("")) etRepitaContrasenaForm3.setError("Campo requerido");
                    }else {
                      //Validacion de bundle recibido
                        if (bundle!=null){
                            //Objeto recibido
                            Usuario usuario = (Usuario) bundle.getSerializable("usuario");

                            //Nombre de usuario
                            usuario.setNombreAlias(etNombreUsuarioForm3.getText().toString());

                            //Contraseña
                            usuario.setContraseña(etRepitaContrasenaForm3.getText().toString());

                            //Envio de objeto a siguente actividad
                            intent.putExtra("usuario",usuario);
                            startActivity(intent);
                        }



                    }

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
