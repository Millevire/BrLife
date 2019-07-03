package com.example.esteban.brlife.ConeionWebServices;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.esteban.brlife.Clases.Comuna;
import com.example.esteban.brlife.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * @author BrotherWare
 *
 */
public class CargarBaseDeDatosComuna implements Response.Listener<JSONObject>,Response.ErrorListener{
    public static ArrayList<Comuna> listaComuna =new ArrayList<>();

    public static ArrayList<Comuna> listaAuxComuna =new ArrayList<>();
    public static ArrayList<Comuna> listaFiltroComuna =new ArrayList<>();


    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    public Context contexto;
    public String mantenedor;

    public CargarBaseDeDatosComuna(Context contexto, String mantenedor) {
        this.contexto = contexto;
        this.mantenedor = mantenedor;
        request= Volley.newRequestQueue(contexto);
        llenarBaseDeDatosComuna(contexto,mantenedor);
    }

    public static ArrayList<Comuna> getListaComuna() {return listaComuna;}

    public static void eliminar(int id){
        for(int x = 0; x< listaComuna.size(); ++x){
            if (listaComuna.get(x).getIdComuna()==id){
                listaComuna.remove(x);

            }

//
        }
    }

    public static Comuna buscar(int idMantenedor){
        for(Comuna comuna: listaComuna){
            if (comuna.getIdComuna()==idMantenedor){
                return comuna;
            }
        }
        return null;
    }

    public static String buscarNombreComuna(int idMantenedor){
        for(Comuna comuna: listaComuna){
            if (comuna.getIdComuna()==idMantenedor){
                return comuna.getNombreComuna() ;
            }
        }
        return null;
    }
    //Se pasan todos los parametros para actualizarlo en la lista
    public static void editar(int idcomuna ,String nombre, int idregion, int idprovincia){
        for(int x = 0; x< listaComuna.size(); ++x){
            if (listaComuna.get(x).getIdComuna()==idcomuna){
                listaComuna.get(x).setNombreComuna(nombre);
                listaComuna.get(x).setIdProvincia(idprovincia);
                listaComuna.get(x).setIdComuna(idregion);
            }


        }

    }

    public static void agregar(Comuna Comuna){

        listaComuna.add(Comuna);
    }

    public static ArrayList<Comuna>filtro(int fkProvincia,int fkRegion){
     listaFiltroComuna.clear();

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




    private void llenarBaseDeDatosComuna(Context context, String mantenedor) {
        progreso=new ProgressDialog(context);
        progreso.setMessage(context.getString(R.string.mensajeBarraProgresoCargando));
        progreso.show();

        String url=context.getString(R.string.URLwebServicePart1)+mantenedor+context.getString(R.string.URLwebServicePart2);

        url=url.replace(" ","%20");

       jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        request.add(jsonObjectRequest);
    }




    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        Comuna Comuna = null;
        progreso.hide();
        listaComuna.clear();

        JSONArray json=response.optJSONArray(this.mantenedor);
        try {
                for (int i=0; i<json.length(); i++){
                JSONObject jsonObject=null;

                Comuna = new Comuna();
                jsonObject=json.getJSONObject(i);

                //Variables para los booleanos
                Comuna.setIdComuna(jsonObject.getInt("Id_"+ this.mantenedor));
                Comuna.setNombreComuna(jsonObject.getString("Nombre_"+ this.mantenedor));
                Comuna.setIdProvincia(jsonObject.getInt("Id_Provincia"));
                Comuna.setIdRegion(jsonObject.getInt("Id_Region"));

                listaComuna.add(Comuna);

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
