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
import com.example.esteban.brlife.Clases.MantenedorTresAtributos;
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
public class CargarBaseDeDatosMantenedorTresAtributos implements Response.Listener<JSONObject>,Response.ErrorListener {

    //lista general para mantenedores de tes atributos
    public static ArrayList<MantenedorTresAtributos> listaMantenedorTresAtributos =new ArrayList<>();

    //lista especifica de Sabor para usos como llenar mas de un spinner en una actividad. la lista sigue siendo de tres atributos
    public static ArrayList<MantenedorTresAtributos> listaSabor =new ArrayList<>();

    //lista especifica de Marca para usos como llenar mas de un spinner en una actividad. la lista sigue siendo de tres atributos
    public static ArrayList<MantenedorTresAtributos> listaMarca =new ArrayList<>();

    //lista especfica

    //listas filtro

    public static ArrayList<MantenedorTresAtributos>listaFiltroMarca=new ArrayList<>();
    public static ArrayList<MantenedorTresAtributos>listaFiltrSabor=new ArrayList<>();


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
    public CargarBaseDeDatosMantenedorTresAtributos(Context context, String tipoMantenedor){
        mantenedor=tipoMantenedor;
        contexto=context;
        request= Volley.newRequestQueue(context);
        llenarBaseDeDatosTipoProducto(context,mantenedor);

    }



    /**
     * lista de Objeto MantenedorTresAtributos
     * @return lista estatica de mantenedor
     */
    public static void eliminar(int id){
        for(int x = 0; x< listaMantenedorTresAtributos.size(); ++x){
            if (listaMantenedorTresAtributos.get(x).getIdMantenedorTresAtributos()==id){
                listaMantenedorTresAtributos.remove(x);

            }


        }
    }

    /**
     * Buscar un objeto MantenedorTresAtributos en lista estatica.
     * @param idMantenedor id de objeto.
     * @return objeto encontrado.
     */
    public static MantenedorTresAtributos buscar(int idMantenedor){
        for(MantenedorTresAtributos mantenedorTresAtributos: listaMantenedorTresAtributos){
            if (mantenedorTresAtributos.getIdMantenedorTresAtributos()==idMantenedor){
                return mantenedorTresAtributos ;
            }
        }
        return null;
    }


    /**
     * Editar objeto de lista estatica
     * @param id id de objeto a editar.
     * @param idTipoProducto id de tipoProducto
     * @param nombre
     */
    public static void editar(int id,int idTipoProducto,String nombre){
        for(int x = 0; x< listaMantenedorTresAtributos.size(); ++x){
            if (listaMantenedorTresAtributos.get(x).getIdMantenedorTresAtributos()==id){
                listaMantenedorTresAtributos.get(x).setNombreMantenedorTresAtributos(nombre);
                listaMantenedorTresAtributos.get(x).setFkMantenedorTresAtributos(idTipoProducto);

            }


        }

    }

    /**
     * nuevo registro de objeto en lista estatica
     * @param mantenedorTresAtributos
     */
    public static void agregar(MantenedorTresAtributos mantenedorTresAtributos){

       listaMantenedorTresAtributos.add(mantenedorTresAtributos);
    }



    /**
     * Metodo de conexion con base de datos y webService
     * @param context contexto de donde es invocado metodo.
     * @param mantenedor nombre de mantenedor.
     */
    private void llenarBaseDeDatosTipoProducto(Context context, String mantenedor) {
        //progreso=new ProgressDialog(context);
       // progreso.setMessage(context.getString(R.string.mensajeBarraProgresoCargando));
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
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(contexto.getString(R.string.ERROR),error.toString());
       // progreso.hide();
    }

    /**
     * respuesta de conexion con web service.
     * @param response
     */
    @Override
    public void onResponse(JSONObject response) {
        MantenedorTresAtributos mantenedorTresAtributos =null;

       // progreso.hide();
        listaMantenedorTresAtributos.clear();

        JSONArray json=response.optJSONArray(this.mantenedor);
        try {
            for (int i=0; i<json.length(); i++){
                JSONObject jsonObject=null;



                mantenedorTresAtributos =new MantenedorTresAtributos();
                jsonObject=json.getJSONObject(i);
                mantenedorTresAtributos.setIdMantenedorTresAtributos(jsonObject.getInt("Id_"+ this.mantenedor));
                SelccionMantenedor selccionMantenedor  = SelccionMantenedor.valueOf(mantenedor);
                switch (selccionMantenedor){
                    case Sabor:
                        mantenedorTresAtributos.setFkMantenedorTresAtributos(jsonObject.getInt("Id_TipoProducto"));
                        break;
                    case Marca:
                        mantenedorTresAtributos.setFkMantenedorTresAtributos(jsonObject.getInt("Id_TipoProducto"));
                        break;
                    case Provincia:
                        mantenedorTresAtributos.setFkMantenedorTresAtributos(jsonObject.getInt("Id_Region"));
                        break;
                    default:break;


                }

                //mantenedor.setIdTipoProducto(Integer.parseInt(jsonObject.toString()));
                mantenedorTresAtributos.setNombreMantenedorTresAtributos(jsonObject.getString("Nombre_"+ this.mantenedor));
                listaMantenedorTresAtributos.add(mantenedorTresAtributos);

                //llenar lista especifica marca
                if (mantenedor.equals(SelccionMantenedor.Marca.getSeleccion())) listaMarca.add(mantenedorTresAtributos);

               //llenar lsta especifica sabor
                if (mantenedor.equals(SelccionMantenedor.Sabor.getSeleccion()))listaSabor.add(mantenedorTresAtributos);



            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
