package com.example.esteban.brlife;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.esteban.brlife.Clases.CalculoCalorias;
import com.example.esteban.brlife.Clases.Usuario;
import com.example.esteban.brlife.Clases.UsuarioInteres;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosUsuarioInteres;
import com.example.esteban.brlife.ConeionWebServices.CargarMantendorComunaHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlife.Enum.SeleccionSexo;
import com.example.esteban.brlife.Enum.SeleccionValorRol;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class EditarUsuarioActivity extends AppCompatActivity {
    private Button btnBackEditar,btnAceptarEditar,btnEditarUsuario,btnEditarInfoPersonal,btnEditarInfoFisicaObjInter;
    private TextView tvNombreEditar,tvNombreUsuarioEditar,tvApellidosEditar,tvSexoEditar,tvFehcaNacimientoEditar,
            tvCorreoEditar,tvComunaEditar,tvProvinciaEditar,tvRegionEditar,tvAlturaEditar,tvPesoEditar,tvSomatotipoEditar,
            tvRolEditar,tvObjetivoEditar,tvCaloriasMaximasEditar,tvEdadEditar;
    private ListView lvInteresesEditar;
    private ArrayAdapter<UsuarioInteres> adapterUsuarioInteres;
    private DateTimeFormatter ftm;
    public LocalDate fechaNac;
    public LocalDate ahora;
    public float caloriasMaximas=0;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);


        //#region Referencia de widget
        btnBackEditar=(Button)findViewById(R.id.btnBackEditar);
        btnAceptarEditar=(Button)findViewById(R.id.btnAceptarEditar);

        tvNombreEditar=(TextView) findViewById(R.id.tvNombreEditar);
        tvNombreUsuarioEditar=(TextView)findViewById(R.id.tvNombreUsuarioEditar);
        tvApellidosEditar=(TextView) findViewById(R.id.tvApellidoEditar);
        tvSexoEditar=(TextView) findViewById(R.id.tvSexoEditar);
        tvFehcaNacimientoEditar=(TextView) findViewById(R.id.tvFehcaNacimientoEditar);
        tvCorreoEditar=(TextView) findViewById(R.id.tvCorreoEditar);
        tvComunaEditar=(TextView) findViewById(R.id.tvComunaEditar);
        tvProvinciaEditar=(TextView) findViewById(R.id.tvProvinciaEditar);
        tvRegionEditar=(TextView) findViewById(R.id.tvRegionEditar);
        tvAlturaEditar=(TextView) findViewById(R.id.tvAlturaEditar);
        tvPesoEditar=(TextView) findViewById(R.id.tvPesoEditar);
        tvSomatotipoEditar=(TextView) findViewById(R.id.tvSomatotipoEditar);
        tvRolEditar=(TextView) findViewById(R.id.tvRolEditar);
        tvObjetivoEditar=(TextView) findViewById(R.id.tvObjetivoEditar);
        tvCaloriasMaximasEditar=(TextView)findViewById(R.id.tvCaloriasMaximasEditar);
        tvEdadEditar=(TextView)findViewById(R.id.tvEdadEditar);

        lvInteresesEditar=(ListView)findViewById(R.id.lvInteresesEditar);
        //#endregion

        ahora=LocalDate.now();

        //permisos httpconection
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Envio a siguente actividad
        final Intent intent =new Intent(this, SessionUserActivity.class);

         new SeleccionSexo();

        //Bundle que traera objeto usuario
        final Bundle bundle=getIntent().getExtras();
        if (bundle !=null) {
            Usuario usuario = (Usuario) bundle.getSerializable("usuario");

            if(usuario!=null){

                //Obtener edad
                LocalDate date = null;
                ftm = DateTimeFormatter.ofPattern("d/M/yyyy");
                fechaNac = LocalDate.parse(usuario.getFechaNacimiento(),ftm);
                Period periodo = Period.between(fechaNac, ahora);
                try {
                    tvEdadEditar.setText(periodo.getYears() + "");

                    new SeleccionValorRol();
                    tvCaloriasMaximasEditar.setText(String.valueOf(CalculoCalorias.calcularCalorias(usuario.getPeso(),usuario.getEstatura(),periodo.getYears(),usuario.getFkRol(),usuario.getSexo(),usuario.getFkObjetivo())));
                    caloriasMaximas=CalculoCalorias.calcularCalorias(usuario.getPeso(),usuario.getEstatura(),periodo.getYears(),usuario.getFkRol(),usuario.getSexo(),usuario.getFkObjetivo());
                }catch (Exception ex){
                    tvEdadEditar.setText("");

                }

                //Calcular calorias maximas



                tvNombreEditar.setText(usuario.getNombreUsuario());
                tvNombreUsuarioEditar.setText(usuario.getNombreAlias());
                tvApellidosEditar.setText(usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno());
                tvSexoEditar.setText(SeleccionSexo.buscaSexo(usuario.getSexo()));
                tvFehcaNacimientoEditar.setText(usuario.getFechaNacimiento());
                tvCorreoEditar.setText(usuario.getCorreoElectronico());
                tvComunaEditar.setText(CargarMantendorComunaHttpConecction.buscarNombreComuna(usuario.getFkComuna()));
                tvProvinciaEditar.setText(CargarMantenedorTresAtributosHttpConecction.buscarNombreProvincia(usuario.getFkProvincia()));
                tvRegionEditar.setText(CargarMantenedorDosAtributosHttpConecction.buscarNombreREgion(usuario.getFkRegion()));
                tvAlturaEditar.setText(String.valueOf(usuario.getEstatura()));
                tvPesoEditar.setText(String.valueOf(usuario.getPeso()));
                tvSomatotipoEditar.setText(CargarMantenedorDosAtributosHttpConecction.buscarTipoPersona(usuario.getFkSomatipo()));
                tvRolEditar.setText(CargarMantenedorDosAtributosHttpConecction.buscarNombreRol(usuario.getFkRol()));
                tvObjetivoEditar.setText(CargarMantenedorDosAtributosHttpConecction.buscarNombreObjetivo(usuario.getFkObjetivo()));

                //Agregar Intereses
                adapterUsuarioInteres=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, CargarBaseDeDatosUsuarioInteres.getListaUsuarioInteres());
                lvInteresesEditar.setAdapter(adapterUsuarioInteres);
            }


            btnBackEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }


    }
}
