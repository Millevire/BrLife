package com.example.esteban.brlife.Clases;

public class RegistroUsuario {
    public int idregistrousuario;
    public int idusuario;
    public int dia;
    public int mes;
    public int ano;
    public String hora;
    public int idproducto;
    public int idhorariocomida;
    public float valorporcion;

    public RegistroUsuario(int idregistrousuario, int idusuario, int dia, int mes, int ano, String hora, int idproducto, int idhorariocomida, float valorporcion) {
        this.idregistrousuario = idregistrousuario;
        this.idusuario = idusuario;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.idproducto = idproducto;
        this.idhorariocomida = idhorariocomida;
        this.valorporcion = valorporcion;
    }

    public int getIdregistrousuario() {
        return idregistrousuario;
    }

    public void setIdregistrousuario(int idregistrousuario) {
        this.idregistrousuario = idregistrousuario;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdhorariocomida() {
        return idhorariocomida;
    }

    public void setIdhorariocomida(int idhorariocomida) {
        this.idhorariocomida = idhorariocomida;
    }

    public float getValorporcion() {
        return valorporcion;
    }

    public void setValorporcion(float valorporcion) {
        this.valorporcion = valorporcion;
    }
}
