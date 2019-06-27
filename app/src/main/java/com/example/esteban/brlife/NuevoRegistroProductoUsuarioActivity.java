package com.example.esteban.brlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.esteban.brlife.Adaptadores.AdapterProductoNutriente;
import com.example.esteban.brlife.Adaptadores.SpinAdapter;
import com.example.esteban.brlife.Clases.Producto;
import com.example.esteban.brlife.Clases.ProductoNutriente;
import com.example.esteban.brlife.Clases.RegistroUsuario;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorProductoNutrienteHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarRegistroUsuarioHttpConexion;
import com.example.esteban.brlife.ConeionWebServices.CrudUsuarioHttpConecction;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class NuevoRegistroProductoUsuarioActivity extends AppCompatActivity {
    private TextView tvNombreProductoRegistro,tvSaborRegistro,tvMarcaRegistro;
    private Spinner spHorarioComidaRegistro,spPorcionRegistro;
    private EditText etPorcionRegistro;
    private ListView lvNutrientesRegistro;
    private Button btnAgregarRegistro;
    private SpinAdapter spinAdapter;
    public AdapterProductoNutriente adapterProductoNutriente;
    public ArrayList<ProductoNutriente> listaProductoNutrientes;
    public Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_registro_producto_usuario);

        //lista Producto nutriente
        listaProductoNutrientes=new ArrayList<>();

        //#region Referencia de weidget
        tvNombreProductoRegistro=(TextView)findViewById(R.id.tvNombreProductoRegistro);
        tvMarcaRegistro=(TextView)findViewById(R.id.tvMarcaRegistro);
        tvSaborRegistro=(TextView)findViewById(R.id.tvSaborRegistro);

        spHorarioComidaRegistro=(Spinner)findViewById(R.id.spHorarioComidaRegistro);
        spPorcionRegistro=(Spinner)findViewById(R.id.spPorcionRegistro);

        etPorcionRegistro=(EditText)findViewById(R.id.etPorcionRegistro);

        lvNutrientesRegistro=(ListView)findViewById(R.id.lvNutrientesRegistro);

        btnAgregarRegistro=(Button)findViewById(R.id.btnAgregarRegistro);
        //#endregion

        spinAdapter = new SpinAdapter(this,android.R.layout.simple_list_item_1, CargarMantenedorDosAtributosHttpConecction.getListaHorarioComida());
        spHorarioComidaRegistro.setAdapter(spinAdapter);

        adapterProductoNutriente = new AdapterProductoNutriente(this, CargarMantenedorProductoNutrienteHttpConecction.getListaProductoNutriente());
        lvNutrientesRegistro.setAdapter(adapterProductoNutriente);

        final Bundle bundle=getIntent().getExtras();

        if (bundle !=null){
            producto=(Producto) bundle.getSerializable("Producto");

            tvNombreProductoRegistro.setText(producto.getNombreProducto());
            tvSaborRegistro.setText(CargarMantenedorTresAtributosHttpConecction.buscaSabor(producto.getIdSabor(),producto.getFkTipoProducto()));
            tvMarcaRegistro.setText(CargarMantenedorTresAtributosHttpConecction.buscarMarca(producto.getIdMarca(),producto.getFkTipoProducto()));

        }


        btnAgregarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroUsuario registroUsuario = new RegistroUsuario(0, CrudUsuarioHttpConecction.usuario.getIdUsuario(),
                        CargarRegistroUsuarioHttpConexion.dia,CargarRegistroUsuarioHttpConexion.mes,CargarRegistroUsuarioHttpConexion.ano,"",producto.getIdProducto(),1,Float.parseFloat(etPorcionRegistro.getText().toString()));
                try {
                    CargarRegistroUsuarioHttpConexion.GuardarRegistro("RegistroUsuario",registroUsuario);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void llenarSpiner(){


    }
}
