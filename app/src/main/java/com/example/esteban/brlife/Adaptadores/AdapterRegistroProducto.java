package com.example.esteban.brlife.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esteban.brlife.Clases.Producto;
import com.example.esteban.brlife.Clases.ProductoNutriente;
import com.example.esteban.brlife.Clases.RegistroUsuario;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorProductoHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorTipoProductoHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarRegistroUsuarioHttpConexion;
import com.example.esteban.brlife.Enum.SeleccionTipoProducto;
import com.example.esteban.brlife.NuevoRegistroProductoUsuarioActivity;
import com.example.esteban.brlife.R;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * @author BrotherWare
 *
 *Clase adaptador
 *Encargado de cargar vistas personalizadas a una lista de objetos RegistroUsuario.
 * Entrega de posicion de objetos en un ArrayList.
 */
public class AdapterRegistroProducto extends BaseAdapter {
    private Context context;
    private ArrayList<RegistroUsuario> listaRegistroUsuario =new ArrayList<>();
    private ArrayList<RegistroUsuario>listaAux=new ArrayList<>();
public AdapterRegistroProducto(Context context, ArrayList<RegistroUsuario>listaRegistroUsuario){
    this.listaRegistroUsuario=listaRegistroUsuario;
    this.context=context;
    this.listaAux.addAll(listaRegistroUsuario);
}


    @Override
    public int getCount() {
        return listaRegistroUsuario.size();
    }

    @Override
    public Object getItem(int position) {
        return listaRegistroUsuario.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_producto_registrado,null);

        }

        TextView tvNombreProductoRegistrado=convertView.findViewById(R.id.tvNombreProductoRegistrado);
        TextView tvSaborProductoProductoRegistrado=convertView.findViewById(R.id.tvSaborProductoProductoRegistrado);
        TextView tvMarcaProductoRegistrado=convertView.findViewById(R.id.tvMarcaProductoRegistrado);
        //TextView tvCaloriasProductoRegistrado=convertView.findViewById(R.id.tvCaloriasProductoRegistrado);
        TextView tvHoraProductoRegistrado=convertView.findViewById(R.id.tvHoraProductoRegistrado);
        TextView tvProcionProductoRegistrado=convertView.findViewById(R.id.tvProcionProductoRegistrado);

        Button btnEliminarProductoRegistrado=convertView.findViewById(R.id.btnEliminarProductoRegistrado);
        Button btnEditarProductoRegistrado=convertView.findViewById(R.id.btnEditarProductoRegistrado);

        ImageView ivImagenProductoRegistrado=convertView.findViewById(R.id.ivImagenProductoRegistrado);


        //Para poder instanciar la actividad de agregar el producto al registro y usarla para poder modificar
        final Intent intent =new Intent(context, NuevoRegistroProductoUsuarioActivity.class);

        //BuscarProducto
        final Producto producto=CargarMantenedorProductoHttpConecction.buscarProducto(listaRegistroUsuario.get(position).getIdproducto());

        final RegistroUsuario registroUsuario = listaRegistroUsuario.get(position);

        tvNombreProductoRegistrado.setText(producto.getNombreProducto());
        tvSaborProductoProductoRegistrado.setText(CargarMantenedorTresAtributosHttpConecction.buscaSabor(producto.getIdSabor(),producto.getFkTipoProducto()));
        tvMarcaProductoRegistrado.setText(CargarMantenedorTresAtributosHttpConecction.buscarMarca(producto.getIdMarca(),producto.getFkTipoProducto()));
        tvHoraProductoRegistrado.setText(listaRegistroUsuario.get(position).getHora());
        tvProcionProductoRegistrado.setText(listaRegistroUsuario.get(position).getValorporcion() +"");


      SeleccionTipoProducto nombreTipoProducto= SeleccionTipoProducto.valueOf(producto.getNombreTipoProducto());

        switch (nombreTipoProducto){
            case Crustaceo:
                ivImagenProductoRegistrado.setImageResource(R.drawable.crustaceo);
                break;
            case Agua:
                ivImagenProductoRegistrado.setImageResource(R.drawable.agua);
                break;
            case Huevo:
                ivImagenProductoRegistrado.setImageResource(R.drawable.huevos);
                break;
            case Pescado:
                ivImagenProductoRegistrado.setImageResource(R.drawable.pescado);
                break;
            case Carne:
                ivImagenProductoRegistrado.setImageResource(R.drawable.carne);
                break;
            case Cereal:
                ivImagenProductoRegistrado.setImageResource(R.drawable.cereal);
                break;
            case Legrumbre:
                ivImagenProductoRegistrado.setImageResource(R.drawable.legumbre);
                break;
            case Lacteo:
                ivImagenProductoRegistrado.setImageResource(R.drawable.lacteo);
                break;
            case Bebida:
                ivImagenProductoRegistrado.setImageResource(R.drawable.bebida);
                break;
            case Fruta:
                ivImagenProductoRegistrado.setImageResource(R.drawable.fruta);
                break;
            case Galleta:
                ivImagenProductoRegistrado.setImageResource(R.drawable.galleta);
                break;
            case Fritura:
                ivImagenProductoRegistrado.setImageResource(R.drawable.frituras);
                break;
            case Cafe:
                ivImagenProductoRegistrado.setImageResource(R.drawable.cafe);
                break;
            case Caramelo:
                ivImagenProductoRegistrado.setImageResource(R.drawable.caramelo);
                break;
            case Chocolate:
                ivImagenProductoRegistrado.setImageResource(R.drawable.chocolate);
                break;
            case Te:
                ivImagenProductoRegistrado.setImageResource(R.drawable.te);
                break;
            case Pan:
                ivImagenProductoRegistrado.setImageResource(R.drawable.pan);
                break;
        }

       btnEditarProductoRegistrado.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               intent.putExtra("accion","editar");
               intent.putExtra("Producto", (Serializable) producto);
               intent.putExtra("Registro", (Serializable) registroUsuario);
               context.startActivity(intent);
           }
       });

        btnEliminarProductoRegistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CargarRegistroUsuarioHttpConexion.EliminarRegistro("RegistroUsuario",registroUsuario);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
