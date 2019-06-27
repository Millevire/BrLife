package com.example.esteban.brlife;

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
import android.widget.Toast;

import com.example.esteban.brlife.Clases.CalculoCalorias;
import com.example.esteban.brlife.Clases.Usuario;
import com.example.esteban.brlife.Clases.UsuarioInteres;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosComuna;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosUsuario;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosUsuarioInteres;
import com.example.esteban.brlife.ConeionWebServices.CargarMantendorComunaHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarNuevoIdHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CrudUsuario;
import com.example.esteban.brlife.ConeionWebServices.CrudUsuarioHttpConecction;
import com.example.esteban.brlife.Enum.SelccionMantenedor;
import com.example.esteban.brlife.Enum.SeleccionSexo;
import com.example.esteban.brlife.Enum.SeleccionValorRol;

import org.json.JSONException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ValidacionRegistroUsuarioActivity extends AppCompatActivity {
    private Button btnBackValidacion,btnAceptarValidacion;
    private TextView tvNombreValidacion,tvNombreUsuarioValidacion,tvApellidosValidacion,tvSexoValidacion,tvFehcaNacimientoValidacion,
            tvCorreoValidacion,tvComunaValidacion,tvProvinciaValidacion,tvRegionValidacion,tvAlturaValidacion,tvPesoValidacion,tvSomatotipoValidacion,
            tvRolValidacion,tvObjetivoValidacion,tvCaloriasMaximasValidacion,tvEdadValidacion;
    private ListView lvInteresesValidacion;
    private ArrayAdapter<UsuarioInteres> adapterUsuarioInteres;
    private DateTimeFormatter ftm;
    public LocalDate fechaNac;
    public LocalDate ahora;
    public float caloriasMaximas=0;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion_registro_usuario);

        //#region Referencia de widget
        btnBackValidacion=(Button)findViewById(R.id.btnBackValidacion);
        btnAceptarValidacion=(Button)findViewById(R.id.btnAceptarValidacion);

        tvNombreValidacion=(TextView) findViewById(R.id.tvNombreValidacion);
        tvNombreUsuarioValidacion=(TextView)findViewById(R.id.tvNombreUsuarioValidacion);
        tvApellidosValidacion=(TextView) findViewById(R.id.tvApellidosValidacion);
        tvSexoValidacion=(TextView) findViewById(R.id.tvSexoValidacion);
        tvFehcaNacimientoValidacion=(TextView) findViewById(R.id.tvFehcaNacimientoValidacion);
        tvCorreoValidacion=(TextView) findViewById(R.id.tvCorreoValidacion);
        tvComunaValidacion=(TextView) findViewById(R.id.tvComunaValidacion);
        tvProvinciaValidacion=(TextView) findViewById(R.id.tvProvinciaValidacion);
        tvRegionValidacion=(TextView) findViewById(R.id.tvRegionValidacion);
        tvAlturaValidacion=(TextView) findViewById(R.id.tvAlturaValidacion);
        tvPesoValidacion=(TextView) findViewById(R.id.tvPesoValidacion);
        tvSomatotipoValidacion=(TextView) findViewById(R.id.tvSomatotipoValidacion);
        tvRolValidacion=(TextView) findViewById(R.id.tvRolValidacion);
        tvObjetivoValidacion=(TextView) findViewById(R.id.tvObjetivoValidacion);
        tvCaloriasMaximasValidacion=(TextView)findViewById(R.id.tvCaloriasMaximasValidacion);
        tvEdadValidacion=(TextView)findViewById(R.id.tvEdadValidacion);

        lvInteresesValidacion=(ListView)findViewById(R.id.lvInteresesValidacion);
        //#endregion

        ahora=LocalDate.now();

        //permisos httpconection
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Bundle que traera objeto usuario
        final Bundle bundle=getIntent().getExtras();

        if (bundle !=null) {
            Usuario usuario = (Usuario) bundle.getSerializable("usuario");



            //Obtener edad
            LocalDate date = null;
            ftm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            fechaNac = LocalDate.parse(usuario.getFechaNacimiento(),ftm);
            Period periodo = Period.between(fechaNac, ahora);
            try {
                tvEdadValidacion.setText(periodo.getYears() + "");

                new SeleccionValorRol();
                tvCaloriasMaximasValidacion.setText(String.valueOf(CalculoCalorias.calcularCalorias(usuario.getPeso(),usuario.getEstatura(),periodo.getYears(),usuario.getFkRol(),usuario.getSexo(),usuario.getFkObjetivo())));
                caloriasMaximas=CalculoCalorias.calcularCalorias(usuario.getPeso(),usuario.getEstatura(),periodo.getYears(),usuario.getFkRol(),usuario.getSexo(),usuario.getFkObjetivo());
            }catch (Exception ex){
                tvEdadValidacion.setText("");

            }

            //Calcular calorias maximas



            tvNombreValidacion.setText(usuario.getNombreUsuario());
            tvNombreUsuarioValidacion.setText(usuario.getNombreAlias());
            tvApellidosValidacion.setText(usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno());
            tvSexoValidacion.setText(SeleccionSexo.buscaSexo(usuario.getSexo()));
            tvFehcaNacimientoValidacion.setText(usuario.getFechaNacimiento());
            tvCorreoValidacion.setText(usuario.getCorreoElectronico());
            tvComunaValidacion.setText(CargarMantendorComunaHttpConecction.buscarNombreComuna(usuario.getFkComuna()));
            tvProvinciaValidacion.setText(CargarMantenedorTresAtributosHttpConecction.buscarNombreProvincia(usuario.getFkProvincia()));
            tvRegionValidacion.setText(CargarMantenedorDosAtributosHttpConecction.buscarNombreREgion(usuario.getFkRegion()));
            tvAlturaValidacion.setText(String.valueOf(usuario.getEstatura()));
            tvPesoValidacion.setText(String.valueOf(usuario.getPeso()));
            tvSomatotipoValidacion.setText(CargarMantenedorDosAtributosHttpConecction.buscarTipoPersona(usuario.getFkSomatipo()));
            tvRolValidacion.setText(CargarMantenedorDosAtributosHttpConecction.buscarNombreRol(usuario.getFkRol()));
            tvObjetivoValidacion.setText(CargarMantenedorDosAtributosHttpConecction.buscarNombreObjetivo(usuario.getFkObjetivo()));

            //Agregar Intereses
            adapterUsuarioInteres=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, CargarBaseDeDatosUsuarioInteres.getListaUsuarioInteres());
            lvInteresesValidacion.setAdapter(adapterUsuarioInteres);

        }


        btnAceptarValidacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bundle !=null) {
                    Usuario usuario = (Usuario) bundle.getSerializable("usuario");

                    try {
                       //Agregar nuevo id usuario
                        int idUsuario=CargarNuevoIdHttpConecction.buscarMantenedorNuevoId(ValidacionRegistroUsuarioActivity.this,SelccionMantenedor.Usuario.getSeleccion());

                        Toast.makeText(ValidacionRegistroUsuarioActivity.this, ""+idUsuario, Toast.LENGTH_SHORT).show();

                        usuario.setIdUsuario(idUsuario);

                        //new CrudUsuario(ValidacionRegistroUsuarioActivity.this,usuario,ValidacionRegistroUsuarioActivity.this.getString(R.string.nuevo));

                       CrudUsuarioHttpConecction.ActualizarUsuario(SelccionMantenedor.Usuario.getSeleccion(),usuario,caloriasMaximas);

                        //Inserat interes
                       for (UsuarioInteres usuarioInteres: CargarBaseDeDatosUsuarioInteres.getListaUsuarioInteres()){
                           CrudUsuarioHttpConecction.InsertarUsuarioInteres(SelccionMantenedor.InteresUsuario.getSeleccion(),usuario.getIdUsuario(),usuarioInteres.getIdInteres());
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }






                }


            }

        });

        btnBackValidacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
