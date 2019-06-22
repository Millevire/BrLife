package com.example.esteban.brlife.Enum;

import com.example.esteban.brlife.Clases.ValorRol;

import java.util.ArrayList;
import java.util.Collection;

public class SeleccionValorRol {
    public  static ArrayList<ValorRol> listaValorRol;


    /**
     * metodo autollenado de lista Valor rol
     */
    public SeleccionValorRol(){
        listaValorRol=new ArrayList<>();

        listaValorRol.add(new ValorRol("Sedentario", (float) 1.200,"Poca actividad física"));
        listaValorRol.add(new ValorRol("Actividad ligera", (float) 1.375 ,"Poco ejercicio, deportes 1-3 días/semana"));
        listaValorRol.add(new ValorRol("Nivel moderado", (float) 1.550 ,"Ejercicio o deportes 3-5 días/semana"));
        listaValorRol.add(new ValorRol("Muy activo", (float) 1.725 ,"Ejercicio duro o deportes 6-7 días/semana"));
        listaValorRol.add(new ValorRol("Extraordinariamente activo", (float) 1.900 ,"Ejercicio muy vigoroso o trabajo físico"));

    }

    /**
     * Retorna lista Valor rol
     * @return lista Valor rol
     */
    public static ArrayList<ValorRol> getListaValorRol() {
        return listaValorRol;
    }

    /**
     * metodo para buscar un objeto ValorRol
     * @param nombreRol nombre rol para buscar
     * @return objeto ValorRol
     */
    public static ValorRol buscarValorRol(String nombreRol){
        for ( ValorRol valorol: listaValorRol){
            if (valorol.getNombreRol().equals(nombreRol)){
                return valorol;
            }

        }
        return null;
    }


}
