package com.example.esteban.brlife;

import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.Enum.SelccionMantenedor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final Intent intent =new Intent(this,MenuPrincipalActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {




                //Validacion de versiones
                int valVersion=Negocio.ValidarExistenciaTablaVtablas(MainActivity.this);
            if (valVersion>0){
                  Toast.makeText(MainActivity.this, "Tablas con "+valVersion+" Contenidos", Toast.LENGTH_SHORT).show();
                  
              }else {
                Toast.makeText(MainActivity.this, "Cargar tablas Version", Toast.LENGTH_SHORT).show();
                Negocio.CargarVtablas(MainActivity.this);
            }


            //Validacion de Region
                int valRegion=Negocio.ValidarExistenciaTablaRegion(MainActivity.this);
                if (valRegion>0){
                    Toast.makeText(MainActivity.this, "Tablas con "+valRegion+" Registros", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "Cargar tablas Region", Toast.LENGTH_SHORT).show();
                    Negocio.CargarRegion(MainActivity.this);

                }

                //Validacion Provincia
                int valProvincia=Negocio.ValidarExistenciaTablaProvincia(MainActivity.this);
                if (valProvincia>0){
                    Toast.makeText(MainActivity.this, "Tablas con "+valProvincia+" Registros", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(MainActivity.this, "Cargar tablas Provincia", Toast.LENGTH_SHORT).show();
                    Negocio.CargarProvincia(MainActivity.this);

                }
              
              startActivity(intent);
              finish();
            }
        },1000);

    }

}
