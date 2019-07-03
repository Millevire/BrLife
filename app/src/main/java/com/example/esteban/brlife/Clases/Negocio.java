package com.example.esteban.brlife.Clases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorTresAtributosHttpConecction;
import com.example.esteban.brlife.ConeionWebServices.CargarVersionHttpConecction;
import com.example.esteban.brlife.Enum.SelccionMantenedor;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


/**
 * @author BrotherWare
 *
 */
public class Negocio {
public static ArrayList<Version>listaprueba=new ArrayList<>();



    /**
     * Tabla de versiones de tablas
     * @param context
     */
   public  static void CargarVtablas(Context context){
       StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);

       try {

           ArrayList<Version>listaVersion=CargarVersionHttpConecction.CargarVersiones(context);
           BaseDeDatosInterna adbs =new BaseDeDatosInterna(context,"BDPrueba",null,1);
           SQLiteDatabase miBD= adbs.getWritableDatabase();

           if (miBD !=null){
               for(Version version: listaVersion) {

                   miBD.execSQL("insert into VTablas(Id_Tabla, Version, Nombre_Tabla) values("+version.getId_Tabla()+","+version.getValor()+",'"+version.getNombre_Tabla()+"')");
                   Log.e("TAG_",version.Nombre_Tabla);
               }
               miBD.close();
           }

       } catch (IOException e) {
           Log.e("TAG_",e.toString());
       } catch (JSONException e) {
           Log.e("TAG_Vtabla",e.toString());
       }
       Toast.makeText(context, "Cargando tablas nuevas...", Toast.LENGTH_SHORT).show();

   }


   //Validar existencia de tabla de versiones
    public static int ValidarExistenciaTablaVtablas(Context context){


        int cont=0;
        BaseDeDatosInterna adbs =new BaseDeDatosInterna(context,"BDPrueba",null,1);
        SQLiteDatabase miBD= adbs.getWritableDatabase();


        Cursor c=  miBD.rawQuery("select * from VTablas",null);


        //Pregunta si select tiene un registro.
        if (c.moveToFirst()){
            do {
                listaprueba.add(new Version(c.getInt(0),c.getFloat(1),c.getString(2)));
                Log.d("TAG_","tabla"+c.getString(2));
            }while (c.moveToNext());

        }
        miBD.close();

        return  listaprueba.size();


    }


    //Validar existencia de tabla de versiones
    public static int ValidarExistenciaTablaRegion(Context context){

     ArrayList<MantenedorDosAtributos>listaRegionSQLites=new ArrayList<>();

     listaRegionSQLites.clear();
       CargarBaseDeDatosDosAtributos.getListaRegion().clear();

        int cont=0;
        BaseDeDatosInterna adbs =new BaseDeDatosInterna(context,"BDPrueba",null,1);
        SQLiteDatabase miBD= adbs.getWritableDatabase();

        Cursor c=  miBD.rawQuery("select * from Region",null);

        //Pregunta si select tiene un registro.
        if (c.moveToFirst()){
            do {
                listaRegionSQLites.add(new MantenedorDosAtributos(c.getInt(0),c.getString(1)));
                Log.d("TAG_","Region "+c.getString(1));
            }while (c.moveToNext());
        }
        miBD.close();
        CargarBaseDeDatosDosAtributos.getListaRegion().addAll(listaRegionSQLites);
        return  listaRegionSQLites.size();

    }

    public static void CargarRegion(Context context){

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {

        ArrayList<MantenedorDosAtributos> listaRegion= CargarMantenedorDosAtributosHttpConecction.buscarMantenedorDosAtributos(context,SelccionMantenedor.Region.getSeleccion());
            BaseDeDatosInterna adbs =new BaseDeDatosInterna(context,"BDPrueba",null,1);
            SQLiteDatabase miBD= adbs.getWritableDatabase();

            if (miBD !=null){
                for(MantenedorDosAtributos region: listaRegion) {

                    miBD.execSQL("insert into Region(Id_Region, Nombre_Region) values("+region.getIdMantenedorDosAtributos()+",'"+region.getNombreMantenedorDosAtributos()+"')");
                    Log.e("TAG_",region.getNombreMantenedorDosAtributos());
                }
                miBD.close();
            }

        } catch (IOException e) {
            Log.e("TAG_",e.toString());
        } catch (JSONException e) {
            Log.e("TAG_",e.toString());
        }
        Toast.makeText(context, "Cargando tablas nuevas...", Toast.LENGTH_SHORT).show();



    }




    //Validar existencia de tabla de versiones
    public static int ValidarExistenciaTablaProvincia(Context context){
        ArrayList<MantenedorTresAtributos>listaProvinciaSQLite=new ArrayList<>();
        listaProvinciaSQLite.clear();
        CargarBaseDeDatosDosAtributos.getListaRegion().clear();

        int cont=0;
        BaseDeDatosInterna adbs =new BaseDeDatosInterna(context,"BDPrueba",null,1);
        SQLiteDatabase miBD= adbs.getWritableDatabase();

        Cursor c=  miBD.rawQuery("select * from Provincia",null);

        //Pregunta si select tiene un registro.
        if (c.moveToFirst()){
            do {
                listaProvinciaSQLite.add(new MantenedorTresAtributos(c.getInt(0),c.getInt(1),c.getString(2)));
                Log.d("TAG_","Provincia: "+c.getString(2));
            }while (c.moveToNext());
        }
        miBD.close();

        return  listaProvinciaSQLite.size();

    }

    public static void CargarProvincia(Context context){

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {

            ArrayList<MantenedorTresAtributos> listaProvincia= CargarMantenedorTresAtributosHttpConecction.buscarMantenedorTresAtributos(context,SelccionMantenedor.Provincia.getSeleccion());
            BaseDeDatosInterna adbs =new BaseDeDatosInterna(context,"BDPrueba",null,1);
            SQLiteDatabase miBD= adbs.getWritableDatabase();

            if (miBD !=null){
                for(MantenedorTresAtributos provincia: listaProvincia) {

                    miBD.execSQL("insert into Provincia(Id_Provincia, Id_Region,Nombre_Provincia) values("+provincia.getIdMantenedorTresAtributos()+","+provincia.getFkMantenedorTresAtributos()+",'"+provincia.getNombreMantenedorTresAtributos()+"')");
                    Log.e("TAG_Provincia",provincia.getNombreMantenedorTresAtributos());
                }
                miBD.close();
            }

        } catch (IOException e) {
            Log.e("TAG_",e.toString());
        } catch (JSONException e) {
            Log.e("TAG_",e.toString());
        }
        Toast.makeText(context, "Cargando tablas nuevas...", Toast.LENGTH_SHORT).show();



    }


}
