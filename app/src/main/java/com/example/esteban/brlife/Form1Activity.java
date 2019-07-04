package com.example.esteban.brlife;

import android.content.Intent;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabWidget;
import android.widget.Toast;

import com.example.esteban.brlife.Adaptadores.InputFilterMinMax;
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
import com.example.esteban.brlife.ConeionWebServices.CargarMantendorComunaHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlife.Enum.SelccionMantenedor;
import com.example.esteban.brlife.Enum.SeleccionSexo;
import com.example.esteban.brlife.Enum.SeleccionValorRol;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;


/**
 * @author BrotherWare
 *
 * Actividad de registro informacion personal de un usuario
 */
public class Form1Activity extends AppCompatActivity {
  private Button btnSifuenteForm1,btnBackForm1;
  private Spinner spSexoFomr1,spRegionForm1,spProvinciaForm1,spComunaForm1;
  private EditText etNombresForm1,etApellidoPaternoForm1,etApelidoMaternoForm1,etCorreoElectronicoForm1,etDiaForm1,etMesForm1,etAnoForm1;
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
    public int idregion, idprovincia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);


        //#region Referencia de widget

        btnSifuenteForm1=(Button)findViewById(R.id.btnSiguenteForm1);
        btnBackForm1=(Button)findViewById(R.id.btnBackForm1);

        spSexoFomr1=(Spinner)findViewById(R.id.spSexoFomr1);
        spRegionForm1=(Spinner)findViewById(R.id.spRegionForm1);
        spProvinciaForm1=(Spinner)findViewById(R.id.spProvinciaForm1);
        spComunaForm1=(Spinner)findViewById(R.id.spComunaForm1);

        etNombresForm1=(EditText)findViewById(R.id.etNombresForm1);
        etApellidoPaternoForm1=(EditText)findViewById(R.id.etApellidoPaternoForm1);
        etApelidoMaternoForm1=(EditText)findViewById(R.id.etApelidoMaternoForm1);
        etCorreoElectronicoForm1=(EditText)findViewById(R.id.etCorreoElectronicoForm1);

        etDiaForm1=(EditText)findViewById(R.id.etDiaForm1);
        etMesForm1=(EditText)findViewById(R.id.etMesForm1);
        etAnoForm1=(EditText)findViewById(R.id.etAnoForm1);
        //#endregion

     //Objeto Usuario
        final Usuario usuario=new Usuario();
        etDiaForm1.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "31")});
        etMesForm1.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "12")});
        etAnoForm1.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "2010")});

        //permisos httpconection
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //#region Validacion
        etNombresForm1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etNombresForm1.getText().length(); i++) {
                        if(Character.isWhitespace(etNombresForm1.getText().charAt(i))) count++; }
                    if (count==etNombresForm1.getText().length() || etNombresForm1.getText().toString().equals("")) {
                        etNombresForm1.setError("Ingrese un nombre valido");
                        btnSifuenteForm1.setEnabled(false);
                    }else btnSifuenteForm1.setEnabled(true);
                }
            }
        });

        etApellidoPaternoForm1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etApellidoPaternoForm1.getText().length(); i++) {
                        if(Character.isWhitespace(etApellidoPaternoForm1.getText().charAt(i))) count++; }
                    if (count==etApellidoPaternoForm1.getText().length() || etApellidoPaternoForm1.getText().toString().equals("")) {
                        etApellidoPaternoForm1.setError("Ingrese un apellido valido");
                        btnSifuenteForm1.setEnabled(false);
                    }else btnSifuenteForm1.setEnabled(true);
                }
            }
        });

        etApelidoMaternoForm1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etApelidoMaternoForm1.getText().length(); i++) {
                        if(Character.isWhitespace(etApelidoMaternoForm1.getText().charAt(i))) count++; }
                    if (count==etApelidoMaternoForm1.getText().length() || etApelidoMaternoForm1.getText().toString().equals("")) {
                        etApelidoMaternoForm1.setError("Ingrese un apellido valido");
                        btnSifuenteForm1.setEnabled(false);
                    }else btnSifuenteForm1.setEnabled(true);
                }
            }
        });

        etDiaForm1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etDiaForm1.getText().length(); i++) {
                        if(Character.isWhitespace(etDiaForm1.getText().charAt(i))) count++; }
                    if (count==etDiaForm1.getText().length() || etDiaForm1.getText().toString().equals("")) {
                        etDiaForm1.setError("Ingrese dia");
                        btnSifuenteForm1.setEnabled(false);
                    }else btnSifuenteForm1.setEnabled(true);
                }
            }
        });

        etMesForm1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etMesForm1.getText().length(); i++) {
                        if(Character.isWhitespace(etMesForm1.getText().charAt(i))) count++; }
                    if (count==etMesForm1.getText().length() || etMesForm1.getText().toString().equals("")) {
                        etMesForm1.setError("Ingrese dia");
                        btnSifuenteForm1.setEnabled(false);
                    }else btnSifuenteForm1.setEnabled(true);
                }
            }
        });

        etAnoForm1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etAnoForm1.getText().length(); i++) {
                        if(Character.isWhitespace(etAnoForm1.getText().charAt(i))) count++; }
                    if (count==etAnoForm1.getText().length() || etAnoForm1.getText().toString().equals("")) {
                        etAnoForm1.setError("Ingrese dia");
                        btnSifuenteForm1.setEnabled(false);
                    }else if(validarFecha(etDiaForm1.getText().toString()+"/"+etMesForm1.getText().toString()+"/"+etAnoForm1.getText().toString())){

                        btnSifuenteForm1.setEnabled(true);
                    }else{etAnoForm1.setError("Fecha invalida");}


                }
            }
        });

        etCorreoElectronicoForm1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else {
                    int count = 0;
                    for(int i = 0; i < etCorreoElectronicoForm1.getText().length(); i++) {
                        if(Character.isWhitespace(etCorreoElectronicoForm1.getText().charAt(i))) count++; }
                    if (count==etCorreoElectronicoForm1.getText().length() || etCorreoElectronicoForm1.getText().toString().equals("")) {
                        etCorreoElectronicoForm1.setError("Ingrese un correo electronico valido");
                        btnSifuenteForm1.setEnabled(false);
                    }else  if(!validarEmail(etCorreoElectronicoForm1.getText().toString())){

                        etCorreoElectronicoForm1.setError("Formato email no valido");
                    } else btnSifuenteForm1.setEnabled(true);
                }
            }
        });
        //#endregion



        final Intent intent =new Intent(this,Form2Activity.class);

        //Llenar Spinners
        llenarSpinner();


        //Verificar funcionaliodad EDITAR


        //iNFORMACION
        final Bundle bundle=getIntent().getExtras();






        //#region Boton siguente. Se encarga de llenar parte de objeto usuario y enviarlo a siguente actividad para continuear llenado.
        btnSifuenteForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etNombresForm1.getText().toString().equals("") || etApellidoPaternoForm1.getText().toString().equals("")
                    || etApelidoMaternoForm1.getText().toString().equals("") || etDiaForm1.getText().toString().equals("") || etMesForm1.getText().toString().equals("") || etAnoForm1.getText().toString().equals("")
                      ||  etCorreoElectronicoForm1.getText().toString().equals("") ){
                    Toast.makeText(Form1Activity.this, "¡Todos los campos son requeridos!", Toast.LENGTH_SHORT).show();
                }else if (validarFecha(etDiaForm1.getText().toString()+"/"+etMesForm1.getText().toString()+"/"+etAnoForm1.getText().toString())==false){
                    Toast.makeText(Form1Activity.this, "Fecha invalida", Toast.LENGTH_SHORT).show();
                    
                    
                }
                
                else{
                    usuario.setNombreUsuario(etNombresForm1.getText().toString());
                    usuario.setApellidoPaterno(etApellidoPaternoForm1.getText().toString());
                    usuario.setApellidoMaterno(etApelidoMaternoForm1.getText().toString());


                    //obtener id de sexo
                    MantenedorDosAtributos sexo = (MantenedorDosAtributos) spSexoFomr1.getSelectedItem();
                    usuario.setSexo(sexo.getIdMantenedorDosAtributos());

                    //Obtener correo electronico
                    usuario.setCorreoElectronico(etCorreoElectronicoForm1.getText().toString());

                    //fecha de nacimiento
                    //crear variable con formato
                    String fecha=etDiaForm1.getText().toString()+"/"+etMesForm1.getText().toString()+"/"+etAnoForm1.getText().toString();
                    usuario.setFechaNacimiento(fecha);


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


            }
        });
        //#endregion


        //Boton para ir atras
        btnBackForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //#region Spinner Region y accion con seleccon
        spRegionForm1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               idregion = CargarMantenedorDosAtributosHttpConecction.getListaRegion().get(position).getIdMantenedorDosAtributos();

                listaFiltroProvincia.clear();
                listaFiltroProvincia = CargarMantenedorTresAtributosHttpConecction.filtro(idregion);

                adapterProvincia=new SpinAdapterTresAtributos(Form1Activity.this,android.R.layout.simple_list_item_1,listaFiltroProvincia);
                spProvinciaForm1.setAdapter(adapterProvincia);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       //#endregion


        //#region Spinner provincia y acciones de seleccion
        spProvinciaForm1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            idprovincia = listaFiltroProvincia.get(position).getIdMantenedorTresAtributos();
            //idprovincia=CargarMantenedorTresAtributosHttpConecction.getListaProvincia().get(position).getFkMantenedorTresAtributos();


            listaFiltroComuna.clear();
            listaFiltroComuna= CargarMantendorComunaHttpConecction.filtro(idprovincia,idregion);

            adapterComuna=new SpinAdapterComuna(Form1Activity.this,android.R.layout.simple_list_item_1,listaFiltroComuna);
            spComunaForm1.setAdapter(adapterComuna);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       //#endregion

    }


    //#region metodo de llenado de spinner sexo y region
    /**
     * metodo de llenado de Spinner sexo y region
     */
    public void llenarSpinner(){
        new SeleccionSexo();
        adapterSexo=new SpinAdapter(this,android.R.layout.simple_list_item_1,SeleccionSexo.getListaSexo());
        spSexoFomr1.setAdapter(adapterSexo);

        adapterRegion=new SpinAdapter(this,android.R.layout.simple_list_item_1, CargarMantenedorDosAtributosHttpConecction.getListaRegion());
        spRegionForm1.setAdapter(adapterRegion);


 //#endregion

}

    public boolean validarFecha(String fecha) {


        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

}
