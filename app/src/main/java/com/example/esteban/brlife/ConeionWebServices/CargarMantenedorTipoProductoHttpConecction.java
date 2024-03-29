package com.example.esteban.brlife.ConeionWebServices;

import android.content.Context;

import com.example.esteban.brlife.Clases.TipoProducto;
import com.example.esteban.brlife.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * @author BrotherWare
 *
 */
public class CargarMantenedorTipoProductoHttpConecction {

    public static ArrayList<TipoProducto> listaTipoProducto =new ArrayList<>();

    /**
     * Metodo para eliminar de la lista estatica por medio del id
     * @param id
     */
    public static void eliminar(int id){
        for(int x = 0; x< listaTipoProducto.size(); ++x){
            if (listaTipoProducto.get(x).getIdTipoProducto()==id){
                listaTipoProducto.remove(x);

            }

//
        }
    }

    /**
     * Metodo para buscar un TipoProducto por medio de su id
     * @param idMantenedor
     * @return
     */
    public static TipoProducto buscar(int idMantenedor){
        for(TipoProducto tipoProducto: listaTipoProducto){
            if (tipoProducto.getIdTipoProducto()==idMantenedor){
                return tipoProducto;
            }
        }
        return null;
    }


    /**
     * Metodo para buscar el nombre de tipo de producto por medio del id
     * @param idMantenedor
     * @return
     */
    public static String buscarNombre(int idMantenedor){
        for(TipoProducto tipoProducto: listaTipoProducto){
            if (tipoProducto.getIdTipoProducto()==idMantenedor){
                return tipoProducto.getNombreTipoProducto();
            }
        }
        return null;
    }
    //Se pasan todos los parametros para actualizarlo en la lista

    /**
     * Metodo para editar un tipo de producto en la lista estatica
     * @param id
     * @param nombre
     * @param sabor
     * @param marca
     */
    public static void editar(int id,String nombre, boolean sabor, boolean marca){
        for(int x = 0; x< listaTipoProducto.size(); ++x){
            if (listaTipoProducto.get(x).getIdTipoProducto()==id){
                listaTipoProducto.get(x).setNombreTipoProducto(nombre);
                listaTipoProducto.get(x).setVaridadSabor(sabor);
                listaTipoProducto.get(x).setVariedadMarca(marca);
            }


        }

    }

    /**
     * Metodo para agregar un tipo de producto en la lista estatica
     * @param tipoProducto
     */
    public static void agregar(TipoProducto tipoProducto){

        listaTipoProducto.add(tipoProducto);
    }

    /**
     * Metodo para buscar en la base de datos todos los tipos de productos por medio de webservice
     * @param context
     * @param mantenedo
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static ArrayList<TipoProducto> buscarMantenedorTipoProducto(Context context, String mantenedo) throws IOException, JSONException {
        listaTipoProducto = new ArrayList<>();
        URL url = new URL(context.getString(R.string.URLwebServicePart1)
                +mantenedo
                +context.getString(R.string.URLwebServicePart2));
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                String jsonCompleto = "";
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);

                JSONObject response = new JSONObject(responseSTR.toString());


                TipoProducto tipoProducto=null;

                listaTipoProducto.clear();

                JSONArray json=response.optJSONArray(mantenedo);
                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject=null;

                        tipoProducto = new TipoProducto();
                        jsonObject=json.getJSONObject(i);

                        //Variables para los booleanos
                        int variedadsabor, variedadmarca;
                        tipoProducto.setIdTipoProducto(jsonObject.getInt("Id_"+ mantenedo));
                        tipoProducto.setNombreTipoProducto(jsonObject.getString("Nombre_"+ mantenedo));
                        variedadmarca = jsonObject.getInt("VariedadMarca");
                        variedadsabor = jsonObject.getInt("VariedadSabor");

                        if (variedadmarca == 0){
                            tipoProducto.setVariedadMarca(false);
                        }else{
                            tipoProducto.setVariedadMarca(true);
                        }

                        if (variedadsabor == 0){
                            tipoProducto.setVaridadSabor(false);
                        }else{
                            tipoProducto.setVaridadSabor(true);
                        }

                        listaTipoProducto.add(tipoProducto);

                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }

        return listaTipoProducto;
    }
}
