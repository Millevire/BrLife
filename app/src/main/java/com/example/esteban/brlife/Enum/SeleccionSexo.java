package com.example.esteban.brlife.Enum;

import com.example.esteban.brlife.Clases.MantenedorDosAtributos;

import java.util.ArrayList;


/**
 * @author BrotherWare
 *
 * enum de tipo de sexo.
 */
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

    public static String buscaSexo(int idSexo){
        for (MantenedorDosAtributos sexo: listaSexo){
            if (sexo.getIdMantenedorDosAtributos()==idSexo){
                return sexo.getNombreMantenedorDosAtributos();
            }
        }
        return "";
    }
}
