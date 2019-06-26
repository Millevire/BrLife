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

        db.execSQL("create table VTablas(Id_Tabla integer, Version numeric, Nombre_Tabla text)");

        db.execSQL("create table Marca(Id_Marca integer, Nombre_Marca text, Id_TipoProducto integer)");

        db.execSQL("create table Provincia(Id_Provincia integer, Id_Region integer,Nombre_Provincia text)");

        db.execSQL("create table Region(Id_Region integer, Nombre_Region text)");

        db.execSQL("create table Nutriente(Id_Nutriente integer, Nombre_Nutriente text)");

        db.execSQL("create table Comuna(Id_Comuna integer, Nombre_Comuna text, Id_Provicia integer, Id_Region integer)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
