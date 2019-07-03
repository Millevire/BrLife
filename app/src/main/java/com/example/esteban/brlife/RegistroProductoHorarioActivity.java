package com.example.esteban.brlife;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.esteban.brlife.Adaptadores.AdapterRegistroProducto;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarRegistroUsuarioHttpConexion;

import java.util.List;

/**
 * @author BrotherWare
 *
 * Actividad de Historial de productos consumidos y registrados por horarios.
 * Busqueda de alimentos.
 *
 */
public class RegistroProductoHorarioActivity extends AppCompatActivity {
    private android.support.v7.widget.SearchView svFiltroProductoHorario;
    private ListView lvProductoHorario;
    private AdapterRegistroProducto adapterRegistroProducto;
    private Button btnBackRegistroProductoHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_producto_horario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        svFiltroProductoHorario =(android.support.v7.widget.SearchView) findViewById(R.id.svFiltroProductoHorario);
        lvProductoHorario=(ListView)findViewById(R.id.lvProductoHorario);
        btnBackRegistroProductoHorario=(Button)findViewById(R.id.btnBackRegistroProductoHorario);

        CargarMantenedorDosAtributosHttpConecction.listaHorarioComida.clear();

        adapterRegistroProducto = new AdapterRegistroProducto(this, CargarRegistroUsuarioHttpConexion.getListaRegistro());
        lvProductoHorario.setAdapter(adapterRegistroProducto);
        //Accion boton flotante
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //Funcionalidad boton back
        btnBackRegistroProductoHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterRegistroProducto.notifyDataSetChanged();
        CargarMantenedorDosAtributosHttpConecction.listaHorarioComida.clear();
    }
}
