package com.example.esteban.brlife.Enum;

import com.example.esteban.brlife.Clases.MantenedorDosAtributos;

import java.util.ArrayList;

public class SeleccionSexo {
    public  static ArrayList<MantenedorDosAtributos> listaSexo;

    public SeleccionSexo(){
        listaSexo=new ArrayList<>();
        listaSexo.add(new MantenedorDosAtributos(1,"Masculino"));
        listaSexo.add(new MantenedorDosAtributos(2,"Femenino"));
    }

    public static ArrayList<MantenedorDosAtributos> getListaSexo() {
        return listaSexo;
    }
}
