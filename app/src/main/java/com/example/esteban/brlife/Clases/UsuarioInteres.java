package com.example.esteban.brlife.Clases;

import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosUsuarioInteres;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorDosAtributosHttpConecction;


/**
 * @author BrotherWare
 *
 */
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

    @Override
    public String toString() {

        return CargarMantenedorDosAtributosHttpConecction.buscarNombreInteres(this.idInteres);
    }
}
