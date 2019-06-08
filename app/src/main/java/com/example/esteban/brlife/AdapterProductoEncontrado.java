package com.example.esteban.brlife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterProductoEncontrado extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> productos;

    public AdapterProductoEncontrado(Context context, ArrayList<Producto> productos ){
    this.context=context;
    this.productos=productos;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_producto_encontrado,null);

        }
        Button btnAgregarAlimento=(Button)convertView.findViewById(R.id.btnAgregarAlimentoAdapterAlimEnc);
        TextView tvNombre=(TextView)convertView.findViewById(R.id.tvNombreAdapterAlimEnc);
        TextView tvMarcaAdapterAlimEnc=(TextView)convertView.findViewById(R.id.tvMarcaAdapterAlimEnc);
        TextView tvSabor=(TextView)convertView.findViewById(R.id.tvSaborAdapterAlimEnc);


        return convertView;
    }
}
