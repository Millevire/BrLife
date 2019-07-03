package com.example.esteban.brlife;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.esteban.brlife.ConeionWebServices.CrudUsuario;
import com.example.esteban.brlife.ConeionWebServices.CrudUsuarioHttpConecction;

import org.json.JSONException;

import java.io.IOException;


/**
 * @author BrotherWare
 *
 * Actividad de Login o ingreso a cuenta creada.
 */
public class LoginActivity extends AppCompatActivity {
    private Button btnRegistar,btnIngresar, btnbacklogin;
    private EditText etUsuarioLogin,etContraseñaLogin;
    private Switch swguardarcon;
    public ProgressDialog progreso;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Intent in1=new Intent(this,Form1Activity.class);
        final Intent in2=new Intent(this, SessionUserActivity.class);
        btnRegistar=(Button)findViewById(R.id.btnRegistrar);
        btnIngresar=(Button)findViewById(R.id.btnIngresar);
        btnbacklogin =(Button) findViewById(R.id.btnbacklogin);
        swguardarcon=(Switch)findViewById(R.id.swguardarcon);
        progressBar=(ProgressBar)findViewById(R.id.progressBarLogin);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        etContraseñaLogin=(EditText)findViewById(R.id.etContraseñaLogin);
        etUsuarioLogin=(EditText)findViewById(R.id.etUsuarioLogin);

        //Progreso
        //iniProgres();




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
                    }else {
                        SharedPreferences prefs = getSharedPreferences(etUsuarioLogin.getText().toString(), MODE_PRIVATE);
                        if (prefs != null){
                            if (prefs.getBoolean("guardar",false)){
                                swguardarcon.setChecked(prefs.getBoolean("guardar",false));
                                etContraseñaLogin.setText(prefs.getString("contrasena", ""));
                            }
                        }
                        String restoredText = prefs.getString("text", null);
                        if (restoredText != null) {
                            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
                            int idName = prefs.getInt("idName", 0); //0 is the default value.
                        }

                        btnIngresar.setEnabled(true);
                    }
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
                    }else{
                        btnIngresar.setEnabled(true);

                    }
                }
            }
        });

        btnbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

                showDialog();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {



                        // btnIngresar.setEnabled(false);
                        int count = 0;
                        for(int i = 0; i < etContraseñaLogin.getText().length(); i++) {
                            if(Character.isWhitespace(etContraseñaLogin.getText().charAt(i))) count++; }


                        if (count==etContraseñaLogin.getText().length() || etContraseñaLogin.getText().toString().equals("")) {
                            etContraseñaLogin.setError("Ingrese una contraseña"); etContraseñaLogin.setText("");
                        }else if(!etUsuarioLogin.getText().toString().equals("") ||!etContraseñaLogin.getText().toString().equals("") ) {

                            int validacion = 0;
                            String correo = (etUsuarioLogin.getText().toString().contains("@")) ? etUsuarioLogin.getText().toString() : "";
                            String alias = "";
                            if (correo.isEmpty()){
                                alias = etUsuarioLogin.getText().toString();
                            }


                            try {

                                validacion =  CrudUsuarioHttpConecction.ValidarAccesoUsuario("Usuario",alias,correo,etContraseñaLogin.getText().toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (validacion > 0) {

                                Toast.makeText(LoginActivity.this, "id_usuario:" + validacion, Toast.LENGTH_SHORT).show();
                                btnIngresar.setEnabled(false);
                                progressBar.setVisibility(View.GONE);
                                try {
                                    CrudUsuarioHttpConecction.TraerDatosUsuario("Usuario",validacion);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                SharedPreferences.Editor editor = getSharedPreferences(etUsuarioLogin.getText().toString(), MODE_PRIVATE).edit();
                                if (swguardarcon.isChecked()){
                                    editor.putString("contrasena", etContraseñaLogin.getText().toString());
                                    editor.putBoolean("guardar", swguardarcon.isChecked());
                                    editor.apply();
                                }else{
                                    editor.putString("contrasena", "");
                                    editor.putBoolean("guardar", swguardarcon.isChecked());
                                    editor.apply();
                                }
                                startActivity(in2);
                            }else{

                                   progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Usuario y/o Correo Electronico/Alias es incorrecto", Toast.LENGTH_SHORT).show();
                            }
                        }else if(etContraseñaLogin.getText().toString().equals("") && etUsuarioLogin.getText().toString().equals("")){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                            etUsuarioLogin.setError("Campo requerido");
                            etContraseñaLogin.setError("Campo requediro");
                        }
                        btnIngresar.setEnabled(true);

                    }
                },500);



            }
        });

    }


   public void showDialog(){
       progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnIngresar.setEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
