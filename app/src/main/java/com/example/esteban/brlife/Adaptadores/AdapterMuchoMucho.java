package com.example.esteban.brlife.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.esteban.brlife.Clases.MantenedorDosAtributos;
import com.example.esteban.brlife.R;

import java.util.ArrayList;

public class AdapterMuchoMucho extends BaseAdapter {
    private Context context;
    private ArrayList<MantenedorDosAtributos>listaMantenedorDoosAtributos;

    public AdapterMuchoMucho(Context context,ArrayList<MantenedorDosAtributos>listaMantenedorDoosAtributos) {
        this.context=context;
        this.listaMantenedorDoosAtributos=listaMantenedorDoosAtributos;

    }

    @Override
    public int getCount() {
        return listaMantenedorDoosAtributos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMantenedorDoosAtributos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = View.inflate(context, R.layout.adapter_mucho_mucho,null);

        }

        TextView tvNombreMuchoMucho=convertView.findViewById(R.id.tvNombreMuchoMucho);
        Button btnCerrarMuchoMucho=convertView.findViewById(R.id.btnCerrarMuchoMucho);

        tvNombreMuchoMucho.setText(listaMantenedorDoosAtributos.get(position).getNombreMantenedorDosAtributos());

        return convertView ;
    }
}
