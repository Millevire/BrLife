package com.example.esteban.brlife.Clases;


/**
 * @author BrotherWare
 *Clase MantenedorDosaAtributos.
 * Clase generica utilizada para crear objetos con solo dos atributos.
 */
public class MantenedorDosAtributos {
    private int idMantenedorDosAtributos;
    private String nombreMantenedorDosAtributos;

    public MantenedorDosAtributos() {
    }

    public MantenedorDosAtributos(int idMantenedorDosAtributos, String nombreMantenedorDosAtributos) {
        this.idMantenedorDosAtributos = idMantenedorDosAtributos;
        this.nombreMantenedorDosAtributos = nombreMantenedorDosAtributos;
    }

    public int getIdMantenedorDosAtributos() {
        return idMantenedorDosAtributos;
    }

    public void setIdMantenedorDosAtributos(int idMantenedorDosAtributos) {
        this.idMantenedorDosAtributos = idMantenedorDosAtributos;
    }

    public String getNombreMantenedorDosAtributos() {
        return nombreMantenedorDosAtributos;
    }

    public void setNombreMantenedorDosAtributos(String nombreMantenedorDosAtributos) {
        this.nombreMantenedorDosAtributos = nombreMantenedorDosAtributos;
    }

    @Override
    public String toString() {
        return nombreMantenedorDosAtributos;
    }
}
