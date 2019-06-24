package com.example.esteban.brlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btnRegistar,btnIngresar;
    private EditText etUsuarioLogin,etContraseñaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Intent in1=new Intent(this,Form1Activity.class);
        final Intent in2=new Intent(this,MenuLateralActivity.class);
        btnRegistar=(Button)findViewById(R.id.btnRegistrar);
        btnIngresar=(Button)findViewById(R.id.btnIngresar);

        etContraseñaLogin=(EditText)findViewById(R.id.etContraseñaLogin);
        etUsuarioLogin=(EditText)findViewById(R.id.etUsuarioLogin);


        //Validacion de capos vacios o solo espacios en edittext
        etUsuarioLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etUsuarioLogin.getText().length(); i++) {
                        if(Character.isWhitespace(etUsuarioLogin.getText().charAt(i))) count++; }
                    if (count==etUsuarioLogin.getText().length() || etUsuarioLogin.getText().toString().equals("")) {
                        etUsuarioLogin.setError("Ingrese un nombre de usuario");
                        etUsuarioLogin.setText("");
                        btnIngresar.setEnabled(false);
                    }else btnIngresar.setEnabled(true);
                }
            }
        });

        etContraseñaLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etContraseñaLogin.getText().length(); i++) {
                        if(Character.isWhitespace(etContraseñaLogin.getText().charAt(i))) count++; }
                    if (count==etContraseñaLogin.getText().length() || etContraseñaLogin.getText().toString().equals("")) {
                        etContraseñaLogin.setError("Ingrese una contraseña");
                        btnIngresar.setEnabled(false);
                    }else btnIngresar.setEnabled(true);
                }
            }
        });

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in1);
            }
        });
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int count = 0;
                    for(int i = 0; i < etContraseñaLogin.getText().length(); i++) {
                        if(Character.isWhitespace(etContraseñaLogin.getText().charAt(i))) count++; }
                    
                    
                    if (count==etContraseñaLogin.getText().length() || etContraseñaLogin.getText().toString().equals("")) {
                        etContraseñaLogin.setError("Ingrese una contraseña"); etContraseñaLogin.setText("");
                    }else if(!etUsuarioLogin.getText().toString().equals("") ||!etContraseñaLogin.getText().toString().equals("") ) {
                        startActivity(in2);
                        
                    }else if(etContraseñaLogin.getText().toString().equals("") && etUsuarioLogin.getText().toString().equals("")){
                        Toast.makeText(LoginActivity.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                        etUsuarioLogin.setError("Campo requerido");
                        etContraseñaLogin.setError("Campo requediro");
                    }

                       





            }
        });

    }
}
