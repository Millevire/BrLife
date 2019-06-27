package com.example.esteban.brlife;

import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorProductoHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorTipoProductoHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlife.Enum.SelccionMantenedor;
import com.example.esteban.brlife.Enum.SeleccionTipoProducto;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final Intent intent =new Intent(this,MenuPrincipalActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              //permisos httpconection
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

           //Cargar Productos

                try {
                    //limpiar listas Marca y sabor

                    CargarMantenedorTresAtributosHttpConecction.limpiarListaMarcaSabor();

                    //Cargar losta producto
                    CargarMantenedorProductoHttpConecction.buscarMantenedorProducto(MainActivity.this, SelccionMantenedor.Producto.getSeleccion());

                    //Cargar lista tipo producto
                    CargarMantenedorTipoProductoHttpConecction.buscarMantenedorTipoProducto(MainActivity.this,SelccionMantenedor.TipoProducto.getSeleccion());

                    //Cargar lista Marca
                    CargarMantenedorTresAtributosHttpConecction.buscarMantenedorTresAtributos(MainActivity.this,SelccionMantenedor.Marca.getSeleccion());

                    //Cargar lista sabor
                    CargarMantenedorTresAtributosHttpConecction.buscarMantenedorTresAtributos(MainActivity.this,SelccionMantenedor.Sabor.getSeleccion());
                    CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(MainActivity.this,SelccionMantenedor.HorarioComida.getSeleccion());

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //#region pruebaPersistenciaPendiente
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
                //#endregion
              
              startActivity(intent);
              finish();
            }
        },1000);

    }

}
