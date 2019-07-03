package com.example.esteban.brlife.Clases;


/**
 * @author BrotherWare
 *
 */
public class RegistroDiario {
    public int idusuario;
    public int dia;
    public int mes;
    public int ano;
    public int idnutriente;
    public float valornutriente;

    public RegistroDiario() {
    }

    public RegistroDiario(int idusuario, int dia, int mes, int ano, int idnutriente, float valornutriente) {
        this.idusuario = idusuario;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.idnutriente = idnutriente;
        this.valornutriente = valornutriente;
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

    public int getIdnutriente() {
        return idnutriente;
    }

    public void setIdnutriente(int idnutriente) {
        this.idnutriente = idnutriente;
    }

    public float getValornutriente() {
        return valornutriente;
    }

    public void setValornutriente(float valornutriente) {
        this.valornutriente = valornutriente;
    }
}
