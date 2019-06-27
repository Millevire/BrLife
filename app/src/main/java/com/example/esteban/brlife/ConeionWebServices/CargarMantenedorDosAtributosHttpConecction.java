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

public class CargarMantenedorDosAtributosHttpConecction {
    public static ArrayList<MantenedorDosAtributos> listaMantenedors;
    public static ArrayList<MantenedorDosAtributos> listaObjetivo =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaInteres =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaTipoPersona =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaRol =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaRegion =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaHorarioComida =new ArrayList<>();
    static ProgressDialog progreso;

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

    public static void limpiarListas(){
        listaInteres.clear();
        listaTipoPersona.clear();
        listaRol.clear();
        listaObjetivo.clear();
        listaRegion.clear();
        listaHorarioComida.clear();
    }


    public static void eliminar(int id){
        for(int x = 0; x< listaMantenedors.size(); ++x){
            if (listaMantenedors.get(x).getIdMantenedorDosAtributos()==id){
                listaMantenedors.remove(x);

            }


        }
    }

    public static MantenedorDosAtributos buscar(int idMantenedor, Context context, String mantenedo){
        for(MantenedorDosAtributos mantenedor: listaMantenedors){
            if (mantenedor.getIdMantenedorDosAtributos()==idMantenedor){
                return mantenedor;
            }
        }
        return null;
    }

    public static MantenedorDosAtributos buscar(int idMantenedor){
        for(MantenedorDosAtributos mantenedor: listaMantenedors){
            if (mantenedor.getIdMantenedorDosAtributos()==idMantenedor){
                return mantenedor;
            }
        }
        return null;
    }

    public static void agregar(MantenedorDosAtributos mantenedor){
        listaMantenedors.add(mantenedor);
    }

    public static int buscarIndice(MantenedorDosAtributos mantenedorTresAtributos){
        int position;
        position = listaMantenedors.indexOf(mantenedorTresAtributos);
        return position;
    }

    public static void editar(int id,String nombre){
        for(int x = 0; x< listaMantenedors.size(); ++x){
            if (listaMantenedors.get(x).getIdMantenedorDosAtributos()==id){
                listaMantenedors.get(x).setNombreMantenedorDosAtributos(nombre);

            }


        }

    }



    public static String buscarNombreREgion(int idRegion){
        for (MantenedorDosAtributos region:listaRegion){
            if (region.getIdMantenedorDosAtributos()==idRegion){
                return region.getNombreMantenedorDosAtributos();
            }
        }
        return "";
    }

    public static String buscarTipoPersona(int idTipoPersona){
        for (MantenedorDosAtributos tipoPersona:listaTipoPersona){
            if (tipoPersona.getIdMantenedorDosAtributos()==idTipoPersona){
                return tipoPersona.getNombreMantenedorDosAtributos();
            }
        }
        return "";
    }

    public static String buscarNombreRol(int idRol){
        for (MantenedorDosAtributos rol:listaRol){
            if (rol.getIdMantenedorDosAtributos()==idRol){
                return rol.getNombreMantenedorDosAtributos();
            }
        }
        return "";
    }

    public static String buscarNombreObjetivo(int idObjetivo){
        for (MantenedorDosAtributos objetivo:listaObjetivo){
            if (objetivo.getIdMantenedorDosAtributos()==idObjetivo){
                return objetivo.getNombreMantenedorDosAtributos();
            }
        }
        return "";
    }

    public static String buscarNombreInteres(int idInteres){
        for (MantenedorDosAtributos interes:listaInteres){
            if (interes.getIdMantenedorDosAtributos()==idInteres){
                return interes.getNombreMantenedorDosAtributos();
            }
        }
        return "";
    }
}
