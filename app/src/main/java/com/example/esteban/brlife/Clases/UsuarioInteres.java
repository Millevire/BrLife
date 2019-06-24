package com.example.esteban.brlife.Clases;

public class UsuarioInteres {
    private int idUsuarioInteres;
    private int idUsuario;
    private int idInteres;

    public UsuarioInteres(int idUsuarioInteres, int idUsuario, int idInteres) {
        this.idUsuarioInteres = idUsuarioInteres;
        this.idUsuario = idUsuario;
        this.idInteres = idInteres;
    }

    public int getIdUsuarioInteres() {
        return idUsuarioInteres;
    }

    public void setIdUsuarioInteres(int idUsuarioInteres) {
        this.idUsuarioInteres = idUsuarioInteres;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdInteres() {
        return idInteres;
    }

    public void setIdInteres(int idInteres) {
        this.idInteres = idInteres;
    }
}
