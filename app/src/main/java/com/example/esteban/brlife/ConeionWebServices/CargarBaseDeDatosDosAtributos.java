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
 * @author BrotherWare
 *Clase de conexion a webService mediante libreria Volley
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




    /**
     * lista de Objeto MantenedorDosAtributos
     * @return lista estatica de mantenedor
     */
    public static void eliminar(int id){
        for(int x = 0; x< listaMantenedorDosAtributos.size(); ++x){
            if (listaMantenedorDosAtributos.get(x).getIdMantenedorDosAtributos()==id){
                listaMantenedorDosAtributos.remove(x);

            }


        }
    }



    /**
     * Buscar un objeto MantenedorDosAtributos en lista estatica.
     * @param idMantenedor id de objeto.
     * @return objeto encontrado.
     */
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


    /**
     * Editar objeto en lista estatica.
     * @param id id de objeto.
     * @param nombre nombre de objeto.
     */
    public static void editar(int id,String nombre){
        for(int x = 0; x< listaMantenedorDosAtributos.size(); ++x){
            if (listaMantenedorDosAtributos.get(x).getIdMantenedorDosAtributos()==id){
                listaMantenedorDosAtributos.get(x).setNombreMantenedorDosAtributos(nombre);

            }


        }

    }

    /**
     * Registro de nuevo objeto en lista estacica MantenedorDosAtributos.
     * @param mantenedor nuevo objeto.
     */
    public static void agregar(MantenedorDosAtributos mantenedor){
       // ordenar();
        listaMantenedorDosAtributos.add(mantenedor);
    }


    public static ArrayList<MantenedorDosAtributos> getListaRegion() {
        return listaRegion;
    }



    /**
     * Metodo de conexion con base de datos y webService
     * @param context contexto de donde es invocado metodo.
     * @param mantenedor nombre de mantenedor.
     */
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


    /**
     * Respuesta de error de conexion
     * @param error error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(contexto.getString(R.string.ERROR),error.toString());
        //progreso.hide();
    }

    /**
     * respuesta de conexion con web service.
     * @param response respuesta
     */
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
