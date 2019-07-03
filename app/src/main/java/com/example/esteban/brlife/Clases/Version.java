package com.example.esteban.brlife.Clases;

/**
 * @author BrotherWare
 *
 * Clase de control de versiones de tablas de base de datos.
 * Compara tablas internas con base de datos de webService.
 * Al encontrar diferencias de versiones, esta actualiza datos internos.
 */
public class Version {
    public  int Id_Tabla;
    public float Valor;
    public String Nombre_Tabla;

    public Version() {
    }

    public Version(int id_Tabla, float valor, String nombre_Tabla) {
        Id_Tabla = id_Tabla;
        Valor = valor;
        Nombre_Tabla = nombre_Tabla;
    }

    public int getId_Tabla() {
        return Id_Tabla;
    }

    public void setId_Tabla(int id_Tabla) {
        Id_Tabla = id_Tabla;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float valor) {
        Valor = valor;
    }

    public String getNombre_Tabla() {
        return Nombre_Tabla;
    }

    public void setNombre_Tabla(String nombre_Tabla) {
        Nombre_Tabla = nombre_Tabla;
    }
}
