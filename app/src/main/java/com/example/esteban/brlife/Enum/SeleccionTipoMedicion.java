package com.example.esteban.brlife.Enum;


import com.example.esteban.brlife.Clases.MantenedorDosAtributos;

import java.util.ArrayList;


/**
 * @author BrotherWare
 *
 */
public class   SeleccionTipoMedicion {
        public  static ArrayList<MantenedorDosAtributos> TipoMedicion;

    public SeleccionTipoMedicion() {
        TipoMedicion = new ArrayList<>();
        TipoMedicion.add(new MantenedorDosAtributos(1,"ml"));
        TipoMedicion.add(new MantenedorDosAtributos(2,"gr"));

    }

    public static ArrayList<MantenedorDosAtributos> getTipoMedicion() {
        return TipoMedicion;
    }
}

