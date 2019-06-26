package com.example.esteban.brlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.esteban.brlife.Adaptadores.AdapterProductoNutriente;
import com.example.esteban.brlife.Clases.Producto;
import com.example.esteban.brlife.Clases.ProductoNutriente;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorTresAtributosHttpConecction;

import java.util.ArrayList;

public class NuevoRegistroProductoUsuarioActivity extends AppCompatActivity {
    private TextView tvNombreProductoRegistro,tvSaborRegistro,tvMarcaRegistro;
    private Spinner spHorarioComidaRegistro,spPorcionRegistro;
    private EditText etPorcionRegistro;
    private ListView lvNutrientesRegistro;
    private Button btnAgregarRegistro;

    public AdapterProductoNutriente adapterProductoNutriente;
    public ArrayList<ProductoNutriente> listaProductoNutrientes;

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

        final Bundle bundle=getIntent().getExtras();

        if (bundle !=null){
            Producto producto=(Producto) bundle.getSerializable("producto");

            tvNombreProductoRegistro.setText(producto.getNombreProducto());
            tvSaborRegistro.setText(CargarMantenedorTresAtributosHttpConecction.buscaSabor(producto.getIdSabor()));
            tvMarcaRegistro.setText(CargarMantenedorTresAtributosHttpConecction.buscarMarca(producto.getIdMarca()));

        }



    }

    public void llenarSpiner(){


    }
}
