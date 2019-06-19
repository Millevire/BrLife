package com.example.esteban.brlife;

import android.content.Intent;
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
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosComuna;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlife.Enum.SelccionMantenedor;
import com.example.esteban.brlife.Enum.SeleccionSexo;

import java.util.ArrayList;

public class Form1Activity extends AppCompatActivity {
  private Button btnSifuenteForm1,btnBackForm1;
  private Spinner spSexoFomr1,spRegionForm1,spProvinciaForm1,spComunaForm1;
  private EditText etNombresForm1,etApellidoPaternoForm1,etApelidoMaternoForm1,etEdadForm1,etCorreoElectronicoForm1,etDiaForm1,etMesForm1,etAnoForm1;
  private SpinAdapter adapterSexo;


  private SpinAdapter adapterRegion;
  private SpinAdapterTresAtributos adapterProvincia;
  private SpinAdapterComuna adapterComuna;

    private ArrayList<Comuna> listaFiltroComuna = new ArrayList<>();
    private ArrayList<MantenedorTresAtributos> listaFiltroProvincia = new ArrayList<>();

    private ArrayList<Comuna> listaComuna = new ArrayList<>();

    public int idregion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
        btnSifuenteForm1=(Button)findViewById(R.id.btnSiguenteForm1);
        btnBackForm1=(Button)findViewById(R.id.btnBackForm1);
        spSexoFomr1=(Spinner)findViewById(R.id.spSexoFomr1);
        spRegionForm1=(Spinner)findViewById(R.id.spRegionForm1);
        spProvinciaForm1=(Spinner)findViewById(R.id.spProvinciaForm1);
        spComunaForm1=(Spinner)findViewById(R.id.spComunaForm1);


        //Cargar arraylist para Spinner de siguente actividad form2





        final Intent intent =new Intent(this,Form2Activity.class);

        //Llenar Spinners
        llenarSpinner();


        btnSifuenteForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
               idregion = CargarBaseDeDatosDosAtributos.getListaMantenedorDosAtributos().get(position).getIdMantenedorDosAtributos();
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

            if (listaFiltroProvincia != null){

                for (MantenedorTresAtributos m: listaFiltroProvincia){

                }
            }

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
