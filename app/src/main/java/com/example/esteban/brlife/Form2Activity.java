package com.example.esteban.brlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.esteban.brlife.Adaptadores.AdapterIntereses;
import com.example.esteban.brlife.Adaptadores.SpinAdapter;
import com.example.esteban.brlife.Clases.MantenedorDosAtributos;
import com.example.esteban.brlife.Clases.MantenedorTresAtributos;
import com.example.esteban.brlife.Clases.Usuario;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosUsuarioInteres;

import java.util.ArrayList;

public class Form2Activity extends AppCompatActivity {
  private Button btnSiguenteForm2,btnSomatipoForm2,btnBackForm2;

  private Spinner spObjetivoForm2,spInteresForm2,spTipoPersonaForm2,spRolForm2;
  private SpinAdapter adapterObjetivo,adapterInteres,adapterTipoPersona,adapterRol;
  private EditText etPesoForm2,etEstaturaForm2;
  private ListView lvInteresesForm2;
  private AdapterIntereses adaptadorIntereses;
 int size=60;
 int cantidadVeces=0;
  private ArrayList <MantenedorDosAtributos> listaIntereses=new ArrayList<>();

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
        etEstaturaForm2=(EditText)findViewById(R.id.etEstaturaForm2);

        lvInteresesForm2=(ListView)findViewById(R.id.lvInteresesForm2);



        adaptadorIntereses=new AdapterIntereses(this,listaIntereses);
        lvInteresesForm2.setAdapter(adaptadorIntereses);



        //Validacion de textos vacios o espacios en EditText

        etEstaturaForm2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etEstaturaForm2.getText().length(); i++) {
                        if(Character.isWhitespace(etEstaturaForm2.getText().charAt(i))) count++; }
                    if (count==etEstaturaForm2.getText().length() || etEstaturaForm2.getText().toString().equals("")) {
                        etEstaturaForm2.setError("Ingrese un valor valido");
                        btnSiguenteForm2.setEnabled(false);
                    }else btnSiguenteForm2.setEnabled(true);
                }
            }
        });

        etPesoForm2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etPesoForm2.getText().length(); i++) {
                        if(Character.isWhitespace(etPesoForm2.getText().charAt(i))) count++; }
                    if (count==etPesoForm2.getText().length() || etPesoForm2.getText().toString().equals("")) {
                        etPesoForm2.setError("Ingrese un valor valido");
                        btnSiguenteForm2.setEnabled(false);
                    }else btnSiguenteForm2.setEnabled(true);
                }
            }
        });


        //envio a siguente actividad
        final Intent intent =new Intent(this,Fomr3Activity.class);


        //Metodo llenar spinner
        llenarSpinner();




        final Bundle bundle=getIntent().getExtras();


        btnSiguenteForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (bundle !=null) {
                    Usuario usuario = (Usuario) bundle.getSerializable("usuario");

                    //obtener id objetivo
                    MantenedorDosAtributos objetivo = (MantenedorDosAtributos) spObjetivoForm2.getSelectedItem();
                    usuario.setFkObjetivo(objetivo.getIdMantenedorDosAtributos());

                    //Obtener id somatipo
                    MantenedorDosAtributos somatipo = (MantenedorDosAtributos) spTipoPersonaForm2.getSelectedItem();
                    usuario.setFkSomatipo(somatipo.getIdMantenedorDosAtributos());

                    //Obtener Rol
                    MantenedorDosAtributos rol = (MantenedorDosAtributos) spRolForm2.getSelectedItem();
                    usuario.setFkRol(rol.getIdMantenedorDosAtributos());

                    //Peso
                    usuario.setPeso(Float.parseFloat(etPesoForm2.getText().toString()));

                    //Estatura
                    usuario.setEstatura(Float.parseFloat(etEstaturaForm2.getText().toString()));

                    //Enviar listaInteres
                    CargarBaseDeDatosUsuarioInteres.llenarListaUsuarioInteres(listaIntereses);




                    intent.putExtra("usuario",usuario);
                    startActivity(intent);
                }



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

        etPesoForm2.clearFocus();
        etEstaturaForm2.clearFocus();


        spInteresForm2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                cantidadVeces++;
                if (cantidadVeces>1){
                    MantenedorDosAtributos mantenedorDosAtributos=(MantenedorDosAtributos) spInteresForm2.getSelectedItem();

                    size=size+120;
                    listaIntereses.add(mantenedorDosAtributos);
                    adaptadorIntereses.notifyDataSetChanged();
                    lvInteresesForm2.getLayoutParams().height=size;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
