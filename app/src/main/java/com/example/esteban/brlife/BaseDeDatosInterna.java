package com.example.esteban.brlife;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class BaseDeDatosInterna extends SQLiteOpenHelper {


    public BaseDeDatosInterna(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table VTablas(Id_Tabla integer, Nombre_Tabla text, Version numeric)");

        db.execSQL("create table Usuario(Id_Usuario integer, Nombre_Usuario text, Apellido_Paterno text," +
                " Apellido_Materno text, Alias text, Id_Region integer, Id_Provincia integer, Id_Comuna integer" +
                ", Edad integer, Peso numeric, Altura numeric, Id_Sexo integer, Id_Objetivo integer, Id_Rol integer," +
                " id_TipoPersona integer, CorreoElectronico text, Contraseña text)");

        db.execSQL("create table Producto(Id_Producto integer, CodigoBarra integer, Id_TipoProducto integer" +
                ", Id_Marca integer, Id_Sabor integer, NombreProducto text, CantidadRacion nuemeric, TipoMedicion integer" +
                ", Validacion integer)");

        db.execSQL("create table Sabor(Id_Sabor integer, Nombre_Sabor text, Id_TipoProducto)");

        db.execSQL("create table Marca(Id_Marca integer, Nombre_Marca text, Id_TipoProducto)");

        db.execSQL("create table Provincia(Id_Marca integer, Nombre_Marca text, Id_Region)");

        db.execSQL("create table Region(Id_Region integer, Nombre_Region)");

        db.execSQL("create table Rol(Id_Rol integer, Nombre_Rol)");

        db.execSQL("create table Interes(Id_Interes integer, Nombre_Interes)");

        db.execSQL("create table TipoPersona(Id_TipoPersona integer, Nombre_TipoPersona)");

        db.execSQL("create table Objetivo(Id_Objetivo integer, Nombre_Objetivo)");

        db.execSQL("create table HorarioComida(Id_HorarioComida integer, Nombre_HorarioComida)");

        db.execSQL("create table Comuna(Id_Comuna integer, Nombre_Comuna text, Id_Provicia integer, Id_Region integer)");

        db.execSQL("create table TipoProducto(Id_TipoProducto integer, Nombre_TipoProducto, VariedadMarca integer, VariedadSabor integer)");

        db.execSQL("create table ProductoNutriente(Id_ProductoNutriente integer, Id_Producto integer, Id_Nutriente integer, Valor numeric, Nombre_Nutriente text)");

        db.execSQL("create table UsuarioInteres(Id_UsuarioInteres numeric, Id_Usuario integer, Id_Interes)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
