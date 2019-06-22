package com.example.esteban.brlife;

import android.content.Intent;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.esteban.brlife.Adaptadores.SpinAdapter;
import com.example.esteban.brlife.Adaptadores.SpinAdapterComuna;
import com.example.esteban.brlife.Adaptadores.SpinAdapterTresAtributos;
import com.example.esteban.brlife.Clases.Comuna;
import com.example.esteban.brlife.Clases.MantenedorDosAtributos;
import com.example.esteban.brlife.Clases.MantenedorTresAtributos;
import com.example.esteban.brlife.Clases.Usuario;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosComuna;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlife.Enum.SelccionMantenedor;
import com.example.esteban.brlife.Enum.SeleccionSexo;
import com.example.esteban.brlife.Enum.SeleccionValorRol;

import java.io.Serializable;
import java.util.ArrayList;

public class Form1Activity extends AppCompatActivity {
  private Button btnSifuenteForm1,btnBackForm1;
  private Spinner spSexoFomr1,spRegionForm1,spProvinciaForm1,spComunaForm1;
  private EditText etNombresForm1,etApellidoPaternoForm1,etApelidoMaternoForm1,etEdadForm1,etCorreoElectronicoForm1;
  private SpinAdapter adapterSexo;


  //Sdapter
  private SpinAdapter adapterRegion;
  private SpinAdapterTresAtributos adapterProvincia;
  private SpinAdapterComuna adapterComuna;


//Listas
    private ArrayList<Comuna> listaFiltroComuna = new ArrayList<>();
    private ArrayList<MantenedorTresAtributos> listaFiltroProvincia = new ArrayList<>();
    private ArrayList<Comuna> listaComuna = new ArrayList<>();

    //Variable global de idRegion
    public int idregion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);


        //Referencia de widget

        btnSifuenteForm1=(Button)findViewById(R.id.btnSiguenteForm1);
        btnBackForm1=(Button)findViewById(R.id.btnBackForm1);

        spSexoFomr1=(Spinner)findViewById(R.id.spSexoFomr1);
        spRegionForm1=(Spinner)findViewById(R.id.spRegionForm1);
        spProvinciaForm1=(Spinner)findViewById(R.id.spProvinciaForm1);
        spComunaForm1=(Spinner)findViewById(R.id.spComunaForm1);

        etNombresForm1=(EditText)findViewById(R.id.etNombresForm1);
        etApellidoPaternoForm1=(EditText)findViewById(R.id.etApellidoPaternoForm1);
        etApelidoMaternoForm1=(EditText)findViewById(R.id.etApelidoMaternoForm1);
        etEdadForm1=(EditText)findViewById(R.id.etEdadForm1);
        etCorreoElectronicoForm1=(EditText)findViewById(R.id.etCorreoElectronicoForm1);

     //Objeto Usuario
        final Usuario usuario=new Usuario();





        final Intent intent =new Intent(this,Form2Activity.class);

        //Llenar Spinners
        llenarSpinner();


        btnSifuenteForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setNombreUsuario(etNombresForm1.getText().toString());
                usuario.setApellidoPaterno(etApellidoPaternoForm1.getText().toString());
                usuario.setApellidoMaterno(etApelidoMaternoForm1.getText().toString());
                usuario.setEdad(Integer.parseInt(etEdadForm1.getText().toString()));

                //obtener id de sexo
                MantenedorDosAtributos sexo = (MantenedorDosAtributos) spSexoFomr1.getSelectedItem();
                usuario.setSexo(sexo.getIdMantenedorDosAtributos());

                usuario.setCorreoElectronico(etCorreoElectronicoForm1.getText().toString());

                //ontener id region
                MantenedorDosAtributos region=(MantenedorDosAtributos) spRegionForm1.getSelectedItem();
                usuario.setFkRegion(region.getIdMantenedorDosAtributos());

                //obtener provincia
                MantenedorTresAtributos provincia=(MantenedorTresAtributos)spProvinciaForm1.getSelectedItem();
                usuario.setFkProvincia(provincia.getIdMantenedorTresAtributos());

                //obtener comuna
                Comuna comuna=(Comuna)spComunaForm1.getSelectedItem();
                usuario.setFkComuna(comuna.getIdComuna());

                //Enviamos objeto a siguente actividad
                intent.putExtra("usuario",  usuario);
                startActivity(intent);
            }
        });

        btnBackForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spRegionForm1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               idregion = CargarBaseDeDatosDosAtributos.getListaRegion().get(position).getIdMantenedorDosAtributos();


                listaFiltroProvincia = CargarBaseDeDatosMantenedorTresAtributos.filtro(idregion);

                adapterProvincia=new SpinAdapterTresAtributos(Form1Activity.this,android.R.layout.simple_list_item_1,listaFiltroProvincia);
                spProvinciaForm1.setAdapter(adapterProvincia);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       // spRegionForm1.seton

        spProvinciaForm1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int idProvincia=CargarBaseDeDatosMantenedorTresAtributos.getListaMantenedorTresAtributos().get(position).getIdMantenedorTresAtributos();



            listaFiltroComuna= CargarBaseDeDatosComuna.filtro(idProvincia,idregion);

            adapterComuna=new SpinAdapterComuna(Form1Activity.this,android.R.layout.simple_list_item_1,listaFiltroComuna);
            spComunaForm1.setAdapter(adapterComuna);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    public void llenarSpinner(){
        new SeleccionSexo();
        adapterSexo=new SpinAdapter(this,android.R.layout.simple_list_item_1,SeleccionSexo.getListaSexo());
        spSexoFomr1.setAdapter(adapterSexo);

        adapterRegion=new SpinAdapter(this,android.R.layout.simple_list_item_1,CargarBaseDeDatosDosAtributos.getListaRegion());
        spRegionForm1.setAdapter(adapterRegion);


    }


}
