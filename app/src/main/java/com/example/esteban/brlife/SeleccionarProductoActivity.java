package com.example.esteban.brlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.Button;
import android.widget.ListView;

import com.example.esteban.brlife.Adaptadores.AdapterProducto;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorProductoHttpConecction;

import java.util.List;

public class SeleccionarProductoActivity extends AppCompatActivity {
   private Button btnMostrarAlimento;
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


    }
}
