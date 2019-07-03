package com.example.esteban.brlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.esteban.brlife.Enum.SelccionMantenedor;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author BrotherWare
 *
 * Actividad de registro de un producto consumido a un horario determinado.
 * Calculo de calorias por porcion consumida de producto.
 */
public class NuevoRegistroProductoUsuarioActivity extends AppCompatActivity {
    private TextView tvNombreProductoRegistro,tvSaborRegistro,tvMarcaRegistro;
    private Spinner spHorarioComidaRegistro,spPorcionRegistro;
    private EditText etPorcionRegistro;
    private ListView lvNutrientesRegistro;
    private Button btnAgregarRegistro,btnBackRegistroProductoHorario;
    private SpinAdapter spinAdapter;
    public AdapterProductoNutriente adapterProductoNutriente;
    public ArrayList<ProductoNutriente> listaProductoNutrientes;
    public Producto producto;
    public int idhorario;
    public String accion = "";
    public RegistroUsuario registroUsuario = new RegistroUsuario();
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
        btnBackRegistroProductoHorario=(Button)findViewById(R.id.btnBackRegistroProductoHorario);
        //#endregion

        final Bundle bundle=getIntent().getExtras();

        try {
            accion = getIntent().getExtras().getString("accion");
        }catch (Exception e){
            accion = "";
        }
        if (bundle !=null){
            producto=(Producto) bundle.getSerializable("Producto");

            tvNombreProductoRegistro.setText(producto.getNombreProducto());
            tvSaborRegistro.setText(CargarMantenedorTresAtributosHttpConecction.buscaSabor(producto.getIdSabor(),producto.getFkTipoProducto()));
            tvMarcaRegistro.setText(CargarMantenedorTresAtributosHttpConecction.buscarMarca(producto.getIdMarca(),producto.getFkTipoProducto()));
            etPorcionRegistro.setText(producto.getCantidadRacion()+"");
            try {
                CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(this, SelccionMantenedor.HorarioComida.getSeleccion());
                CargarMantenedorProductoNutrienteHttpConecction.buscarMantenedorProductoNutriente(this,"ProductoNutriente",producto.getIdProducto());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (accion.equals("editar")){
                registroUsuario=(RegistroUsuario) bundle.getSerializable("Registro");
                etPorcionRegistro.setText(registroUsuario.getValorporcion() + "");
                btnAgregarRegistro.setText("Editar");
            }

        }

        adapterProductoNutriente = new AdapterProductoNutriente(this, CargarMantenedorProductoNutrienteHttpConecction.getListaProductoNutriente());
        lvNutrientesRegistro.setAdapter(adapterProductoNutriente);
        spinAdapter = new SpinAdapter(this,android.R.layout.simple_list_item_1, CargarMantenedorDosAtributosHttpConecction.getListaHorarioComida());
        spHorarioComidaRegistro.setAdapter(spinAdapter);

        if (accion.equals("editar")){
            etPorcionRegistro.setText(registroUsuario.getValorporcion() + "");
            int id = (int) spinAdapter.getItemId(registroUsuario.getIdhorariocomida());
            spHorarioComidaRegistro.setSelection(id);
        }


        spHorarioComidaRegistro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idhorario = CargarMantenedorDosAtributosHttpConecction.listaHorarioComida.get(position).getIdMantenedorDosAtributos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAgregarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float porcion = 0;
                try{
                    porcion = Float.parseFloat(etPorcionRegistro.getText().toString());
                }catch (Exception ex){
                    porcion = 0;
                }
                Date date  =  new Date();

                String hora = date.getHours() + ":"+  date.getMinutes();

                RegistroUsuario registroUser = new RegistroUsuario(0, CrudUsuarioHttpConecction.usuario.getIdUsuario(),
                        CargarRegistroUsuarioHttpConexion.dia,
                        CargarRegistroUsuarioHttpConexion.mes,
                        CargarRegistroUsuarioHttpConexion.ano,hora,producto.getIdProducto(),
                        idhorario,
                        porcion);
                try {
                    if (accion.equals("editar")){
                        registroUsuario.setIdhorariocomida(idhorario);
                        registroUsuario.setValorporcion(porcion);

                        CargarRegistroUsuarioHttpConexion.ActualizarRegistro("RegistroUsuario",registroUsuario);
                    }else {
                        CargarRegistroUsuarioHttpConexion.GuardarRegistro("RegistroUsuario", registroUser);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CargarMantenedorDosAtributosHttpConecction.listaHorarioComida.clear();
                finish();
            }
        });

        btnBackRegistroProductoHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CargarMantenedorDosAtributosHttpConecction.listaHorarioComida.clear();
    }
}
