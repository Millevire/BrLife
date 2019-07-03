package com.example.esteban.brlife.ConeionWebServices;

import android.content.Context;

import com.example.esteban.brlife.Clases.Comuna;
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
public class CargarMantendorComunaHttpConecction {
    public static ArrayList<Comuna> listaComuna =new ArrayList<>();
    public static ArrayList<Comuna> listaAuxComuna =new ArrayList<>();
    public static ArrayList<Comuna> listaFiltroComuna =new ArrayList<>();

    public static ArrayList<Comuna> getListaComuna() {return listaComuna;}

    public static void eliminar(int id){
        for(int x = 0; x< listaComuna.size(); ++x){
            if (listaComuna.get(x).getIdComuna()==id){
                listaComuna.remove(x);

            }

//
        }
    }

    /**
     *
     * @param idMantenedor
     * @return Comuna
     * Metodo busca por medio del id que se le entra el objeto que cohincida con esa id y retorna el objeto completo
     */
    public static Comuna buscar(int idMantenedor){
        for(Comuna Comuna: listaComuna){
            if (Comuna.getIdComuna()==idMantenedor){
                return Comuna;
            }
        }
        return null;
    }
    //Se pasan todos los parametros para actualizarlo en la lista

    /**
     *
     * @param idcomuna
     * @param nombre
     * @param idregion
     * @param idprovincia
     * Al momento de editar en la base de datos una se actualiza en esta lista los atributos en base al id
     * de la comuna
     */
    public static void editar(int idcomuna ,String nombre, int idregion, int idprovincia){
        for(int x = 0; x< listaComuna.size(); ++x){
            if (listaComuna.get(x).getIdComuna()==idcomuna){
                listaComuna.get(x).setNombreComuna(nombre);
                listaComuna.get(x).setIdProvincia(idprovincia);
                listaComuna.get(x).setIdComuna(idregion);
            }


        }

    }

    /**
     *
     * @param idMantenedor
     * @return
     * Metodo retorna solo el nombre de la comuna en base a su id
     */
    public static String buscarNombreComuna(int idMantenedor){
        for(Comuna comuna: listaComuna){
            if (comuna.getIdComuna()==idMantenedor){
                return comuna.getNombreComuna() ;
            }
        }
        return null;
    }

    /**
     * @param Comuna
     * Se agrega en la lista estatica la nueva comuna agregada
     */
    public static void agregar(Comuna Comuna){

        listaComuna.add(Comuna);
    }

    /**
     *
     * @param fkProvincia
     * @param fkRegion
     * Metodo retorna una nueva lista en base a la provincia y a la comuna
     * @return
     */
    public static ArrayList<Comuna>filtro(int fkProvincia,int fkRegion){
        listaFiltroComuna.clear();
        listaAuxComuna.clear();
        for (Comuna c: listaComuna){
            if (c.getIdRegion()==fkRegion ){
                listaAuxComuna.add(c);

            }

        }

        for (Comuna comuna : listaAuxComuna){
            if (comuna.getIdProvincia()==fkProvincia){
                listaFiltroComuna.add(comuna);
            }

        }
        return listaFiltroComuna;
    }

    /**
     *
     * @param context
     * @param mantenedo
     * @return
     * @throws IOException
     * @throws JSONException
     * Metodo retorna de la base de datos todas las comunas en la base de datos por medio de webservice
     */
    public static ArrayList<Comuna> buscarMantenedorComuna(Context context, String mantenedo) throws IOException, JSONException {
        listaComuna = new ArrayList<>();
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


                Comuna Comuna =null;

                listaComuna.clear();

                JSONArray json=response.optJSONArray(mantenedo);


                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject=null;

                        Comuna = new Comuna();
                        jsonObject=json.getJSONObject(i);

                        //Variables para los booleanos
                        Comuna.setIdComuna(jsonObject.getInt("Id_"+ mantenedo));
                        Comuna.setNombreComuna(jsonObject.getString("Nombre_"+ mantenedo));
                        Comuna.setIdProvincia(jsonObject.getInt("Id_Provincia"));
                        Comuna.setIdRegion(jsonObject.getInt("Id_Region"));

                        listaComuna.add(Comuna);

                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }




            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }

        return listaComuna;
    }
}
