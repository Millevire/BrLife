package com.example.esteban.brlife.Clases;


/**
 * @author BrotherWare
 *Clase MantenedorDosaAtributos.
 * Clase generica utilizada para crear objetos con solo tres atributos.
 */
public class MantenedorTresAtributos {
    private int idMantenedorTresAtributos;
    private int fkMantenedorTresAtributos;
    private String nombreMantenedorTresAtributos;

    public MantenedorTresAtributos(int idMantenedorTresAtributos, int fkMantenedorTresAtributos, String nombreMantenedorTresAtributos) {
        this.idMantenedorTresAtributos = idMantenedorTresAtributos;
        this.fkMantenedorTresAtributos = fkMantenedorTresAtributos;
        this.nombreMantenedorTresAtributos = nombreMantenedorTresAtributos;
    }

    public MantenedorTresAtributos() {
    }

    public int getIdMantenedorTresAtributos() {
        return idMantenedorTresAtributos;
    }

    public void setIdMantenedorTresAtributos(int idMantenedorTresAtributos) {
        this.idMantenedorTresAtributos = idMantenedorTresAtributos;
    }

    public int getFkMantenedorTresAtributos() {
        return fkMantenedorTresAtributos;
    }

    public void setFkMantenedorTresAtributos(int idTipoProducto) {
        this.fkMantenedorTresAtributos = idTipoProducto;
    }

    public String getNombreMantenedorTresAtributos() {
        return nombreMantenedorTresAtributos;
    }

    public void setNombreMantenedorTresAtributos(String nombreMantenedorTresAtributos) {
        this.nombreMantenedorTresAtributos = nombreMantenedorTresAtributos;
    }

    @Override
    public String toString() {
        return nombreMantenedorTresAtributos;
    }
}
