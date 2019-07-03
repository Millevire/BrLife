package com.example.esteban.brlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.esteban.brlife.Adaptadores.AdapterProducto;
import com.example.esteban.brlife.Clases.Producto;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorProductoHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorProductoNutrienteHttpConecction;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * @author BrotherWare
 *
 * Actividad de Busqueda de productos manualmente existentes en base de datos.
 */
public class SeleccionarProductoActivity extends AppCompatActivity {
   private Button btnBackSeleccionarProducto;
   private android.support.v7.widget.SearchView svFiltroProducto;
   private ListView lvFiltoProducto;
   private AdapterProducto adapterProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_producto);
      //  btnMostrarAlimento=(Button)findViewById(R.id.btnMostrarAliemento);

        lvFiltoProducto=(ListView)findViewById(R.id.lvFiltoProducto);
        svFiltroProducto=(android.support.v7.widget.SearchView)findViewById(R.id.svFiltroProducto);

        btnBackSeleccionarProducto=(Button)findViewById(R.id.btnBackSeleccionarProducto);

        //Cragar losta a listview
        adapterProducto = new AdapterProducto(this, CargarMantenedorProductoHttpConecction.getListaProducto());
        lvFiltoProducto.setAdapter(adapterProducto);

        //Filto producto
        svFiltroProducto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String filtro) {
                adapterProducto.Filtro("");
                lvFiltoProducto.clearTextFilter();
                adapterProducto.Filtro(filtro);

                return true;
            }
        });


        lvFiltoProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent =new Intent(SeleccionarProductoActivity.this,NuevoRegistroProductoUsuarioActivity.class);
                Producto producto = CargarMantenedorProductoHttpConecction.listaProducto.get(position);

                intent.putExtra("Producto",producto);
                startActivity(intent);

            }
        });


        //Funcionalidad boton back
        btnBackSeleccionarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
