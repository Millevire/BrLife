package com.example.esteban.brlife.ConeionWebServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.esteban.brlife.Clases.MantenedorDosAtributos;
import com.example.esteban.brlife.Enum.SelccionMantenedor;
import com.example.esteban.brlife.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



/**
 *
 *
 */
public class CargarBaseDeDatosDosAtributos implements Response.Listener<JSONObject>,Response.ErrorListener{
    public static ArrayList<MantenedorDosAtributos> listaMantenedorDosAtributos =new ArrayList<>();

    //Listas dedicadas a mantenedores
    public static ArrayList<MantenedorDosAtributos> listaObjetivo =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaInteres =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaTipoPersona =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaRol =new ArrayList<>();
    public static ArrayList<MantenedorDosAtributos> listaRegion =new ArrayList<>();



    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    public Context contexto;
    public String mantenedor;


    /**
     *
     * @param context variable de contexto proveniente de donde se invoque metodo.
     * @param tipoMantenedor variable que nos dara el nombre del mantenedor al que estemos llamando.
     * Metodo contructor que llenara una lista
     */
    public CargarBaseDeDatosDosAtributos(Context context, String tipoMantenedor){
        mantenedor=tipoMantenedor;
        contexto=context;
        request= Volley.newRequestQueue(context);
        llenarBaseDeDatosDosAtributos(context,mantenedor);

    }





    public static void eliminar(int id){
        for(int x = 0; x< listaMantenedorDosAtributos.size(); ++x){
            if (listaMantenedorDosAtributos.get(x).getIdMantenedorDosAtributos()==id){
                listaMantenedorDosAtributos.remove(x);

            }


        }
    }




    public static MantenedorDosAtributos buscar(int idMantenedor, Context context, String mantenedo){
        for(MantenedorDosAtributos mantenedor: listaMantenedorDosAtributos){
            if (mantenedor.getIdMantenedorDosAtributos()==idMantenedor){
                return mantenedor;
            }
        }
        return null;
    }

    public static MantenedorDosAtributos buscar(int idMantenedor){
        for(MantenedorDosAtributos mantenedor: listaMantenedorDosAtributos){
            if (mantenedor.getIdMantenedorDosAtributos()==idMantenedor){
                return mantenedor;
            }
        }
        return null;
    }


    public static int buscarIndice(MantenedorDosAtributos mantenedorTresAtributos){
        int position;
        position = listaMantenedorDosAtributos.indexOf(mantenedorTresAtributos);
        return position;
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
    public static void editar(int id,String nombre){
        for(int x = 0; x< listaMantenedorDosAtributos.size(); ++x){
            if (listaMantenedorDosAtributos.get(x).getIdMantenedorDosAtributos()==id){
                listaMantenedorDosAtributos.get(x).setNombreMantenedorDosAtributos(nombre);

            }


        }

    }
    public static void agregar(MantenedorDosAtributos mantenedor){
       // ordenar();
        listaMantenedorDosAtributos.add(mantenedor);
    }

    public static ArrayList<MantenedorDosAtributos> getListaMantenedorDosAtributos() {

        return listaMantenedorDosAtributos;
    }

    public static ArrayList<MantenedorDosAtributos> getListaInteres() {
        return listaInteres;
    }

    public static ArrayList<MantenedorDosAtributos> getListaObjetivo() {
        return listaObjetivo;
    }

    public static ArrayList<MantenedorDosAtributos> getListaRol() {
        return listaRol;
    }

    public static ArrayList<MantenedorDosAtributos> getListaTipoPersona() {
        return listaTipoPersona;
    }

    public static ArrayList<MantenedorDosAtributos> getListaRegion() {
        return listaRegion;
    }

    public static void limpiarListas(){
        listaInteres.clear();
        listaTipoPersona.clear();
        listaRol.clear();
        listaObjetivo.clear();
    }

    private void llenarBaseDeDatosDosAtributos(Context context, String mantenedor) {
       // progreso=new ProgressDialog(context);
        //progreso.setMessage(context.getString(R.string.mensajeBarraProgresoCargando));
        //progreso.show();

        String url=context.getString(R.string.URLwebServicePart1)+mantenedor+context.getString(R.string.URLwebServicePart2);

        //String url="http://www.brotherwareoficial.com/WebServices/MantenedorTipoProducto.php?tipoconsulta=s";
        url=url.replace(" ","%20");

        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(contexto.getString(R.string.ERROR),error.toString());
        //progreso.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        MantenedorDosAtributos mantenedorDosAtributos =null;

        //progreso.hide();
        listaMantenedorDosAtributos.clear();

        JSONArray json=response.optJSONArray(this.mantenedor);
        try {
            for (int i=0; i<json.length(); i++){
                JSONObject jsonObject=null;



                    mantenedorDosAtributos =new MantenedorDosAtributos();
                    jsonObject=json.getJSONObject(i);
                    mantenedorDosAtributos.setIdMantenedorDosAtributos(jsonObject.getInt("Id_"+ this.mantenedor));
                    // mantenedor.setIdTipoProducto(Integer.parseInt(jsonObject.toString()));
                    mantenedorDosAtributos.setNombreMantenedorDosAtributos(jsonObject.getString("Nombre_"+ this.mantenedor));
                    listaMantenedorDosAtributos.add(mantenedorDosAtributos);

                if (mantenedor.equals(SelccionMantenedor.Objetivo.getSeleccion()))listaObjetivo.add(mantenedorDosAtributos);
                if (mantenedor.equals(SelccionMantenedor.Interes.getSeleccion()))listaInteres.add(mantenedorDosAtributos);
                if (mantenedor.equals(SelccionMantenedor.TipoPersona.getSeleccion()))listaTipoPersona.add(mantenedorDosAtributos);
                if (mantenedor.equals(SelccionMantenedor.Rol.getSeleccion()))listaRol.add(mantenedorDosAtributos);
                if (mantenedor.equals(SelccionMantenedor.Region.getSeleccion()))listaRegion.add(mantenedorDosAtributos);
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
