package com.example.esteban.brlife.Adaptadores;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.esteban.brlife.Clases.ProductoNutriente;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorProductoNutrienteHttpConecction;
import com.example.esteban.brlife.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdapterProductoNutriente extends BaseAdapter {
    private Context context;
    private ArrayList<ProductoNutriente> listaProductoNutriente=new ArrayList<>();


    public String nombreNutriente;

    public AdapterProductoNutriente(Context context,ArrayList<ProductoNutriente> listaProductoNutriente){
        this.context=context;
        this.listaProductoNutriente=listaProductoNutriente;
    }
    @Override
    public int getCount() {
        return listaProductoNutriente.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProductoNutriente.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_producto_nutriente,null);

        }

        TextView tvNombreNutriente=convertView.findViewById(R.id.tvNombreNutriente);
        TextView tvValorProductoNutriente=convertView.findViewById(R.id.tvValorProductoNutriente);

        tvNombreNutriente.setText(CargarMantenedorProductoNutrienteHttpConecction.listaProductoNutriente.get(position).getNombreNutriente());
        tvValorProductoNutriente.setText(String.valueOf(listaProductoNutriente.get(position).getValor()));

        return convertView;
    }

}
