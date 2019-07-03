package com.example.esteban.brlife.ConeionWebServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.esteban.brlife.Clases.Usuario;

import org.json.JSONObject;


/**
 * @author BrotherWare
 *
 */
public class CrudUsuario implements Response.Listener<JSONObject>,Response.ErrorListener{
    static RequestQueue request;
    static JsonObjectRequest jsonObjectRequest;
    static ProgressDialog progreso;
    Context context;

    /**
     * constructor para crud Usuario
     * @param context contexto
     * @param usuario objeto usuario
     * @param tipoConsulta tipo consulta para crud.
     */
    public CrudUsuario(Context context, Usuario usuario,String tipoConsulta){
        request= Volley.newRequestQueue(context);
        this.context=context;

        switch (tipoConsulta){
            case "nuevo":
                nuevoUsuario(context,usuario);
                break;
            case "editar":
                editarUsuario(context, usuario);
                break;
            case "elimina":
                eliminarUsuario(context,usuario);
                break;

                default: break;
        }

    }

    /**
     * eliminar usuario
     * @param context contexto
     * @param usuario objeto para eliminar
     */
    private void eliminarUsuario(Context context, Usuario usuario) {
        progreso=new ProgressDialog(context);
        progreso.setMessage("Cargando...");
        progreso.show();

    }

    /**
     * editar usuario
     * @param context
     * @param usuario
     */
    private void editarUsuario(Context context, Usuario usuario) {
        progreso=new ProgressDialog(context);
        progreso.setMessage("Cargando...");
        progreso.show();
    }

    /**
     * nuevo registro usuario
     * @param context
     * @param usuario
     */
    private void nuevoUsuario(Context context, Usuario usuario) {
        progreso=new ProgressDialog(context);
        progreso.setMessage("Cargando...");
        progreso.show();

        String url="http://www.brotherwareoficial.com/WebServices/MantenedorUsuario.php?tipoconsulta=a&"+
                "Id_Usuario="+usuario.getIdUsuario()+
                "&Nombre_Usuario="+ usuario.getNombreUsuario()+
                "&Apellido_Paterno="+usuario.getApellidoPaterno()+
                "&Apellido_Materno="+usuario.getApellidoMaterno()+
                "&Alias="+usuario.getNombreAlias()+
                "&Id_Region="+usuario.getFkRegion()+
                "&Id_Provincia="+usuario.getFkProvincia()+
                "&Id_Comuna="+usuario.getFkComuna()+
                "&FechaNac="+usuario.getFechaNacimiento()+
                "&Peso="+usuario.getPeso()+
                "&Altura="+usuario.getEstatura()+
                "&Id_Sexo="+usuario.getSexo()+
                "&Id_Objetivo="+usuario.getFkObjetivo()
                +"&Id_Rol="+usuario.getFkRol()+
                "&Id_TipoPersona="+usuario.getFkSomatipo()+
                "&CorreoElectronico="+usuario.getCorreoElectronico()+
                "&Contrasena="+usuario.getContraseña()+
                "&MaximoCalorias="+1000;
        jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        // Toast.makeText(context, "ERROR"+error.toString(), Toast.LENGTH_SHORT).show();
        Log.d("ERROR",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast.makeText(context, "Nuevo usuario agregado", Toast.LENGTH_SHORT).show();
    }
}
