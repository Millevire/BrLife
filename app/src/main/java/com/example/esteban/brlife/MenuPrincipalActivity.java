package com.example.esteban.brlife;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.esteban.brlife.Clases.Producto;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosComuna;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarMantendorComunaHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorProductoHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlife.Enum.SelccionMantenedor;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;

import java.io.IOException;


/**
 * @author BrotherWare
 *
 * Actividad de Seleccion de accion para un usuario.
 *
 * Opcion de Probar aplicacion con verificacion de informacion de un producto mediante codigo de barra.
 * Opcion de Login o ingreso a cuenta existente.
 * Opcion de registro para un nuevo usuario.
 */


public class MenuPrincipalActivity extends AppCompatActivity {
  private Button btnProbarAplicacion,btnIniciarSession,btnRegistrarsePre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        btnProbarAplicacion=(Button)findViewById(R.id.btnProbarAplicacion);
        btnIniciarSession=(Button)findViewById(R.id.btnIniciarSession);
        btnRegistrarsePre=(Button)findViewById(R.id.btnRegistrarsePre);

        final Intent in1=new Intent(this,Form1Activity.class);
        final Intent in2=new Intent(this,LoginActivity.class);

        btnProbarAplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          scanner();
            }
        });

        btnIniciarSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in2);
            }
        });

        btnRegistrarsePre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in1);
            }
        });


        //permisos httpconection
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        CargarMantenedorDosAtributosHttpConecction.limpiarListas();
        CargarMantenedorTresAtributosHttpConecction.voidlimpiarListaProvincia();

        try {
            CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(this,SelccionMantenedor.Region.getSeleccion());
            CargarMantendorComunaHttpConecction.buscarMantenedorComuna(this,SelccionMantenedor.Comuna.getSeleccion());
            CargarMantenedorTresAtributosHttpConecction.buscarMantenedorTresAtributos(this,SelccionMantenedor.Provincia.getSeleccion());

            CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(this,SelccionMantenedor.Objetivo.getSeleccion());
            CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(this,SelccionMantenedor.Interes.getSeleccion());
            CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(this,SelccionMantenedor.Rol.getSeleccion());
            CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(this,SelccionMantenedor.TipoPersona.getSeleccion());



        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void scanner(){
        IntentIntegrator intent= new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);

        intent.setPrompt("ESCANEAR CODIGO BARRA");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        final Intent intentProducto =new Intent(this,NuevoRegistroProductoUsuarioActivity.class);

        if (result !=null){
            if (result.getContents()==null){
                Toast.makeText(this, "Cancelaste escaneo", Toast.LENGTH_LONG).show();

            }else{


                //BuscarProducto
                Producto producto=CargarMantenedorProductoHttpConecction.buscarProductoCodigoBarra(result.getContents().toString());

                if (producto !=null){

                    intentProducto.putExtra("Producto",producto);
                    intentProducto.putExtra("accion","probar");
                    startActivity(intentProducto);

                }

                Toast.makeText(this, " "+result.getContents().toString(), Toast.LENGTH_SHORT).show();
            }

        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
