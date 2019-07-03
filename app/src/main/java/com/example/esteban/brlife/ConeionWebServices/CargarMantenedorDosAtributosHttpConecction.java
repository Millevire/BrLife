package com.example.esteban.brlife.ConeionWebServices;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.esteban.brlife.Clases.MantenedorDosAtributos;
import com.example.esteban.brlife.Enum.SelccionMantenedor;
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
public class CargarMantenedorDosAtributosHttpConecction {
    public static ArrayList<MantenedorDosAtributos> listaMantenedors;
    public static ArrayList<MantenedorDosAtributos> listaObjetivo =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaInteres =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaTipoPersona =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaRol =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaRegion =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaHorarioComida =new ArrayList<>();
    static ProgressDialog progreso;

    /**
     * Metodo retorna cualquier mantenedor de dos atrbitos pasando como parametro el context para
     * el progress dialog y el nombre del mantenedor para cargar uno en especifico
     * @param context
     * @param mantenedo
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static ArrayList<MantenedorDosAtributos> buscarMantenedorDosAtributos(Context context, String mantenedo) throws  IOException, JSONException {


        URL url = new URL(context.getString(R.string.URLwebServicePart1)
                +mantenedo
                +context.getString(R.string.URLwebServicePart2));
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);

            listaMantenedors = new ArrayList<>();
            progreso=new ProgressDialog(context);
            progreso.setMessage(context.getString(R.string.mensajeBarraProgresoCargando));
            progreso.show();



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


                MantenedorDosAtributos mantenedor =null;

                listaMantenedors.clear();

                JSONArray json=response.optJSONArray(mantenedo);
                progreso.hide();
                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject=null;
                        
                        mantenedor =new MantenedorDosAtributos();
                        jsonObject=json.getJSONObject(i);
                        mantenedor.setIdMantenedorDosAtributos(jsonObject.getInt("Id_"+ mantenedo));
                        mantenedor.setNombreMantenedorDosAtributos(jsonObject.getString("Nombre_"+ mantenedo));




                        SelccionMantenedor selccionMantenedor = SelccionMantenedor.valueOf(mantenedo);
                        switch (selccionMantenedor) {
                            case Objetivo:
                                listaObjetivo.add(mantenedor);
                                break;
                            case Interes:
                                listaInteres.add(mantenedor);
                                break;
                            case Region:
                                listaRegion.add(mantenedor);
                                break;
                            case TipoPersona:
                                listaTipoPersona.add(mantenedor);
                                break;
                            case Rol:
                                listaRol.add(mantenedor);
                                break;
                            case HorarioComida:
                                listaHorarioComida.add(mantenedor);
                                break;
                        }
                        listaMantenedors.add(mantenedor);

                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
        return listaMantenedors;
    }

    public static ArrayList<MantenedorDosAtributos> getListaMantenedors() {
        return listaMantenedors;
    }

    public static ArrayList<MantenedorDosAtributos> getListaInteres() {
        return listaInteres;
    }

    public static ArrayList<MantenedorDosAtributos> getListaObjetivo() {
        return listaObjetivo;
    }

    public static ArrayList<MantenedorDosAtributos> getListaRegion() {
        return listaRegion;
    }

    public static ArrayList<MantenedorDosAtributos> getListaRol() {
        return listaRol;
    }

    public static ArrayList<MantenedorDosAtributos> getListaTipoPersona() {
        return listaTipoPersona;
    }

    public static ArrayList<MantenedorDosAtributos> getListaHorarioComida() {
        return listaHorarioComida;
    }

    /**
     * Metodo limpia todas las listas estaticas
     */
    public static void limpiarListas(){
        listaInteres.clear();
        listaTipoPersona.clear();
        listaRol.clear();
        listaObjetivo.clear();
        listaRegion.clear();
        listaHorarioComida.clear();
    }

    /**
     * Metodo elimina por medio del id un objeto de la lista general estatica
     * @param id
     */
    public static void eliminar(int id){
        for(int x = 0; x< listaMantenedors.size(); ++x){
            if (listaMantenedors.get(x).getIdMantenedorDosAtributos()==id){
                listaMantenedors.remove(x);

            }


        }
    }

    /**
     * Metodo para buscar en la lista estatica general un objeto completo por medio del id
     * @param idMantenedor
     * @param context
     * @param mantenedo
     * @return
     */
    public static MantenedorDosAtributos buscar(int idMantenedor, Context context, String mantenedo){
        for(MantenedorDosAtributos mantenedor: listaMantenedors){
            if (mantenedor.getIdMantenedorDosAtributos()==idMantenedor){
                return mantenedor;
            }
        }
        return null;
    }

    /**
     * Metodo para buscar en la lista estatica general un objeto completo por medio del id
     * @param idMantenedor
     * @return
     */
    public static MantenedorDosAtributos buscar(int idMantenedor){
        for(MantenedorDosAtributos mantenedor: listaMantenedors){
            if (mantenedor.getIdMantenedorDosAtributos()==idMantenedor){
                return mantenedor;
            }
        }
        return null;
    }

    /**
     * Agrega a la lista estatica el objeto entero que se agrega en la base de datos
     * @param mantenedor
     */
    public static void agregar(MantenedorDosAtributos mantenedor){
        listaMantenedors.add(mantenedor);
    }


    /**
     * Metodo para editar de la lista estatica general un objeto por medio de su id
     * @param id
     * @param nombre
     */
    public static void editar(int id,String nombre){
        for(int x = 0; x< listaMantenedors.size(); ++x){
            if (listaMantenedors.get(x).getIdMantenedorDosAtributos()==id){
                listaMantenedors.get(x).setNombreMantenedorDosAtributos(nombre);

            }


        }

    }


    /**
     * Metodo para buscar el nombre del mantenedor region por medio de su id
     * @param idRegion
     * @return
     */
    public static String buscarNombreREgion(int idRegion){
        for (MantenedorDosAtributos region:listaRegion){
            if (region.getIdMantenedorDosAtributos()==idRegion){
                return region.getNombreMantenedorDosAtributos();
            }
        }
        return "";
    }

    /**
     * Metodo para buscar el nombre del mantenedor tipo de persona por medio de su id
     * @param idTipoPersona
     * @return
     */
    public static String buscarTipoPersona(int idTipoPersona){
        for (MantenedorDosAtributos tipoPersona:listaTipoPersona){
            if (tipoPersona.getIdMantenedorDosAtributos()==idTipoPersona){
                return tipoPersona.getNombreMantenedorDosAtributos();
            }
        }
        return "";
    }

    /**
     * Metodo para buscar el nombre del mantenedor rol por medio de su id
     * @param idRol
     * @return
     */
    public static String buscarNombreRol(int idRol){
        for (MantenedorDosAtributos rol:listaRol){
            if (rol.getIdMantenedorDosAtributos()==idRol){
                return rol.getNombreMantenedorDosAtributos();
            }
        }
        return "";
    }

    /**
     * Metodo para buscar el nombre del mantenedor Objetivo por medio de su id
     * @param idObjetivo
     * @return
     */
    public static String buscarNombreObjetivo(int idObjetivo){
        for (MantenedorDosAtributos objetivo:listaObjetivo){
            if (objetivo.getIdMantenedorDosAtributos()==idObjetivo){
                return objetivo.getNombreMantenedorDosAtributos();
            }
        }
        return "";
    }

    /**
     * Metodo para buscar el nombre del mantenedor region por medio de su id
     * @param idInteres
     * @return
     */
    public static String buscarNombreInteres(int idInteres){
        for (MantenedorDosAtributos interes:listaInteres){
            if (interes.getIdMantenedorDosAtributos()==idInteres){
                return interes.getNombreMantenedorDosAtributos();
            }
        }
        return "";
    }
}
