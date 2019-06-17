package com.example.esteban.brlife.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.esteban.brlife.Clases.Comuna;


import java.util.ArrayList;

public class SpinAdapterComuna extends ArrayAdapter<Comuna> {
    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private ArrayList<Comuna> listaComuna = new ArrayList<>();

    public SpinAdapterComuna (Context context, int textViewResourceId,
                        ArrayList<Comuna> listaComuna){
      super(context,textViewResourceId,listaComuna);
        this.context=context;
        this.listaComuna=listaComuna;

    }

    @Override
    public int getCount(){
        return listaComuna.size();
    }


    @Override
    public long getItemId(int id){
        for (Comuna c : listaComuna) {
            if (c.getIdComuna() == id){
                return listaComuna.indexOf(c);
            }
        }

        return id;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(listaComuna.get(position).getNombreComuna());
        label.setTextSize(14);
        label.setGravity(Gravity.CENTER);
        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setTextSize(14);
        label.setGravity(Gravity.CENTER);
        label.setText(listaComuna.get(position).getNombreComuna());

        return label;

}
}
