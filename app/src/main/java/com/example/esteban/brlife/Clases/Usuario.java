package com.example.esteban.brlife.Clases;

import java.io.Serializable;

public class Usuario implements Serializable{
    private int idUsuario;
    private String nombreUsuario;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int edad;
    private int sexo;
    private String correoElectronico;
    private int fkRegion;
    private int fkProvincia;
    private int fkComuna;
    private int fkObjetivo;
    private int fkSomatipo;
    private int fkRol;
    private int peso;
    private int estatura;
    private String nombreAlias;
    private String contraseña;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String apellidoPaterno, String apellidoMaterno, int edad, int sexo, String correoElectronico, int fkRegion, int fkProvincia, int fkComuna, int fkObjetivo, int fkSomatipo, int fkRol, int peso, int estatura, String nombreAlias, String contraseña) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.edad = edad;
        this.sexo = sexo;
        this.correoElectronico = correoElectronico;
        this.fkRegion = fkRegion;
        this.fkProvincia = fkProvincia;
        this.fkComuna = fkComuna;
        this.fkObjetivo = fkObjetivo;
        this.fkSomatipo = fkSomatipo;
        this.fkRol = fkRol;
        this.peso = peso;
        this.estatura = estatura;
        this.nombreAlias = nombreAlias;
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getFkRegion() {
        return fkRegion;
    }

    public void setFkRegion(int fkRegion) {
        this.fkRegion = fkRegion;
    }

    public int getFkProvincia() {
        return fkProvincia;
    }

    public void setFkProvincia(int fkProvincia) {
        this.fkProvincia = fkProvincia;
    }

    public int getFkComuna() {
        return fkComuna;
    }

    public void setFkComuna(int fkComuna) {
        this.fkComuna = fkComuna;
    }

    public int getFkObjetivo() {
        return fkObjetivo;
    }

    public void setFkObjetivo(int fkObjetivo) {
        this.fkObjetivo = fkObjetivo;
    }

    public int getFkSomatipo() {
        return fkSomatipo;
    }

    public void setFkSomatipo(int fkSomatipo) {
        this.fkSomatipo = fkSomatipo;
    }

    public int getFkRol() {
        return fkRol;
    }

    public void setFkRol(int fkRol) {
        this.fkRol = fkRol;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public String getNombreAlias() {
        return nombreAlias;
    }

    public void setNombreAlias(String nombreAlias) {
        this.nombreAlias = nombreAlias;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
