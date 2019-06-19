package com.example.esteban.brlife;

import android.content.EntityIterator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.esteban.brlife.Adaptadores.SpinAdapter;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;

public class Form2Activity extends AppCompatActivity {
  private Button btnSiguenteForm2,btnSomatipoForm2,btnBackForm2;

  private Spinner spObjetivoForm2,spInteresForm2,spTipoPersonaForm2,spRolForm2;
  private SpinAdapter adapterObjetivo,adapterInteres,adapterTipoPersona,adapterRol;
  private EditText etPesoForm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);

        //Referencia de widget
        btnSiguenteForm2=(Button)findViewById(R.id.btnSiguenteForm2);
        btnSomatipoForm2=(Button)findViewById(R.id.btnSomatipoForm2);
        btnBackForm2=(Button)findViewById(R.id.btnBackForm2);

        spObjetivoForm2=(Spinner)findViewById(R.id.spObjetivoForm2);
        spInteresForm2=(Spinner)findViewById(R.id.spInteresForm2);
        spTipoPersonaForm2=(Spinner)findViewById(R.id.spTipoPersonaForm2);
        spRolForm2=(Spinner)findViewById(R.id.spRolForm2);

        etPesoForm2=(EditText)findViewById(R.id.etPesoForm2);




        final Intent intent =new Intent(this,Fomr3Activity.class);


        //Metodo llenar spinner
        llenarSpinner();

        btnSiguenteForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        
        btnSomatipoForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new CuadroDialogoInformacionTipoPersona(Form2Activity.this);
            }
        });

        btnBackForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void llenarSpinner(){

        //Spinner objetivo
        adapterObjetivo=new SpinAdapter(this,android.R.layout.simple_list_item_1, CargarBaseDeDatosDosAtributos.getListaObjetivo());
        spObjetivoForm2.setAdapter(adapterObjetivo);


        //Spinner Interes
        adapterInteres=new SpinAdapter(this,android.R.layout.simple_list_item_1, CargarBaseDeDatosDosAtributos.getListaInteres());
        spInteresForm2.setAdapter(adapterInteres);

        //Spinner TipoPerson
        adapterTipoPersona=new SpinAdapter(this,android.R.layout.simple_list_item_1, CargarBaseDeDatosDosAtributos.getListaTipoPersona());
        spTipoPersonaForm2.setAdapter(adapterTipoPersona);

        //Spinner Rol
        adapterRol=new SpinAdapter(this,android.R.layout.simple_list_item_1, CargarBaseDeDatosDosAtributos.getListaRol());
        spRolForm2.setAdapter(adapterRol);



    }
}
