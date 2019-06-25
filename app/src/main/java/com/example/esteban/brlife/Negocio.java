package com.example.esteban.brlife;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.esteban.brlife.ConeionWebServices.CargarVersionHttpConecction;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class Negocio {






   public  Negocio(Context context){
       try {
           ArrayList<Version>listaVersion=CargarVersionHttpConecction.CargarVersiones(context);

           BaseDeDatosInterna adbs =new BaseDeDatosInterna(context,"BDPrueba",null,1);
           SQLiteDatabase miBD= adbs.getWritableDatabase();

           if (miBD !=null){

               for(Version version: listaVersion) {

                   miBD.execSQL("insert into VTablas(Id_Tabla, Version, Nombre_Tabla) values("+version.getId_Tabla()+","+version.getValor()+",'"+version.getNombre_Tabla()+"')");

               }


               miBD.close();



           }


       } catch (IOException e) {
           e.printStackTrace();
       } catch (JSONException e) {
           e.printStackTrace();
       }



   }

}
