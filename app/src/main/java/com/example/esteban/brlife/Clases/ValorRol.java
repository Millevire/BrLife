package com.example.esteban.brlife.Clases;

public class ValorRol {
    private String nombreRol;
    private float valorRol;
    private String comentario;

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public float getValorRol() {
        return valorRol;
    }

    public void setValorRol(float valorRol) {
        this.valorRol = valorRol;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public ValorRol(String nombreRol, float valorRol, String comentario) {
        this.nombreRol = nombreRol;
        this.valorRol = valorRol;
        this.comentario=comentario;
    }
}
