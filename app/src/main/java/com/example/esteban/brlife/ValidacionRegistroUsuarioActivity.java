package com.example.esteban.brlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.esteban.brlife.Clases.Usuario;
import com.example.esteban.brlife.Clases.UsuarioInteres;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosComuna;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosMantenedorTresAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosUsuarioInteres;
import com.example.esteban.brlife.Enum.SeleccionSexo;

import java.util.List;

public class ValidacionRegistroUsuarioActivity extends AppCompatActivity {
    private Button btnBackValidacion,btnAceptarValidacion;
    private TextView tvNombreValidacion,tvNombreUsuarioValidacion,tvApellidosValidacion,tvSexoValidacion,tvFehcaNacimientoValidacion,
            tvCorreoValidacion,tvComunaValidacion,tvProvinciaValidacion,tvRegionValidacion,tvAlturaValidacion,tvPesoValidacion,tvSomatotipoValidacion,
            tvRolValidacion,tvObjetivoValidacion;
    private ListView lvInteresesValidacion;
    private ArrayAdapter<UsuarioInteres> adapterUsuarioInteres;



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

        lvInteresesValidacion=(ListView)findViewById(R.id.lvInteresesValidacion);
        //#endregion


        //Bundle que traera objeto usuario
        final Bundle bundle=getIntent().getExtras();

        if (bundle !=null){
            Usuario usuario = (Usuario) bundle.getSerializable("usuario");

            tvNombreValidacion.setText(usuario.getNombreUsuario());
            tvNombreUsuarioValidacion.setText(usuario.getNombreAlias());
            tvApellidosValidacion.setText(usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno());
            tvSexoValidacion.setText(SeleccionSexo.buscaSexo(usuario.getSexo()));
            tvFehcaNacimientoValidacion.setText(usuario.getFechaNacimiento());
            tvCorreoValidacion.setText(usuario.getCorreoElectronico());
            tvComunaValidacion.setText(CargarBaseDeDatosComuna.buscarNombreComuna(usuario.getFkComuna()));
            tvProvinciaValidacion.setText(CargarBaseDeDatosMantenedorTresAtributos.buscarNombreProvincia(usuario.getFkProvincia()));
            tvRegionValidacion.setText(CargarBaseDeDatosDosAtributos.buscarNombreREgion(usuario.getFkRegion()));
            tvAlturaValidacion.setText(String.valueOf(usuario.getEstatura()));
            tvPesoValidacion.setText(String.valueOf(usuario.getPeso()));
            tvSomatotipoValidacion.setText(CargarBaseDeDatosDosAtributos.buscarTipoPersona(usuario.getFkSomatipo()));
            tvRolValidacion.setText(CargarBaseDeDatosDosAtributos.buscarNombreRol(usuario.getFkRol()));
            tvObjetivoValidacion.setText(CargarBaseDeDatosDosAtributos.buscarNombreObjetivo(usuario.getFkObjetivo()));

            //Agregar Intereses
            adapterUsuarioInteres=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, CargarBaseDeDatosUsuarioInteres.getListaUsuarioInteres());
            lvInteresesValidacion.setAdapter(adapterUsuarioInteres);

        }

    }
}
