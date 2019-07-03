package com.example.esteban.brlife.ConeionWebServices;

import com.example.esteban.brlife.Clases.Usuario;
import com.example.esteban.brlife.Clases.UsuarioInteres;

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
public class CrudUsuarioHttpConecction {
    public static int acceso;
    public static Usuario usuario;
    public static int correovalidado;
    public static float maximocalorias;
    public static int nuevoidusuario;
    public static ArrayList<UsuarioInteres> listausuariointeres = new ArrayList<>();

    /**
     * Metodo valida acceso del usuario por medio de los parametros:
     * @param mantenedo el nombre del mantenedor
     * @param alias el alias que peude ingresar el usuario
     * @param corrreoelectronico el correo que puede ingresar el usuario
     * @param contrasena la contraseña del usuario para ingresar a la aplicacion
     * @return retorna el id del usuario
     * @throws IOException
     * @throws JSONException
     */
    public static int ValidarAccesoUsuario(String mantenedo, String alias, String corrreoelectronico, String contrasena) throws IOException, JSONException {


        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=vu" +
                "&Alias="+alias +
                "&CorreoElectronico="+corrreoelectronico +
                "&Contrasena="+contrasena);
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);

                JSONObject response = new JSONObject(responseSTR.toString());
                JSONArray json=response.optJSONArray("Acceso");

                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);
                        acceso = jsonObject.getInt("Validado");
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
        return acceso;
    }

    /**
     * Metodo trae los datos del usuario por medio de los parametros:
     * @param mantenedo nombre del mantenedor
     * @param idusuario el id del usuario
     * @throws IOException en caso de problemas con el IO
     * @throws JSONException en caso de problemas con el JSON
     */
    public static void TraerDatosUsuario(String mantenedo, int idusuario) throws IOException, JSONException {


        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=s" +
                "&Id_Usuario="+idusuario);
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);

                JSONObject response = new JSONObject(responseSTR.toString());
                JSONArray json=response.optJSONArray("Usuario");

                usuario = new Usuario();

                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject= null ;
                        jsonObject=json.getJSONObject(i);
                        usuario.setIdUsuario(jsonObject.getInt("Id_Usuario"));
                        usuario.setNombreUsuario(jsonObject.getString("Nombre_Usuario"));
                        usuario.setApellidoPaterno(jsonObject.getString("Apellido_Paterno"));
                        usuario.setApellidoMaterno(jsonObject.getString("Apellido_Materno"));
                        usuario.setNombreAlias(jsonObject.getString("Alias"));
                        usuario.setFkRegion(jsonObject.getInt("Id_Region"));
                        usuario.setFkProvincia(jsonObject.getInt("Id_Provincia"));
                        usuario.setFkComuna(jsonObject.getInt("Id_Comuna"));
                        usuario.setFechaNacimiento(jsonObject.getString("FechaNac"));
                        usuario.setPeso((float) jsonObject.getDouble("Peso"));
                        usuario.setEstatura((float) jsonObject.getDouble("Altura"));
                        usuario.setSexo(jsonObject.getInt("Id_Sexo"));
                        usuario.setFkObjetivo(jsonObject.getInt("Id_Objetivo"));
                        usuario.setFkRol(jsonObject.getInt("Id_Rol"));
                        usuario.setFkSomatipo(jsonObject.getInt("Id_TipoPersona"));
                        usuario.setCorreoElectronico(jsonObject.getString("CorreoElectronico"));
                        usuario.setContraseña(jsonObject.getString("Contrasena"));
                        maximocalorias = (float) jsonObject.getDouble("Cantidad_Maxima");
                        correovalidado = jsonObject.getInt("Validado");
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
    }

    /**
     * Metodo actualiza los datos del usuario en la base de datos
     * @param mantenedo nombre del mantenedor
     * @param usuario objeto del usuario
     * @param MaximoCalorias el maximo de calorias calculadas para el usuario
     * @throws IOException en caso de problemas con IO
     * @throws JSONException en caso de problemas con el JSON
     */
    public static void ActualizarUsuario(String mantenedo, Usuario usuario, float MaximoCalorias) throws IOException, JSONException {


        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=a" +
                "&Id_Usuario="+usuario.getIdUsuario() +
                "&Nombre_Usuario="+usuario.getNombreUsuario() +
                "&Apellido_Paterno="+usuario.getApellidoPaterno() +
                "&Apellido_Materno="+usuario.getApellidoMaterno() +
                "&Alias="+usuario.getNombreAlias() +
                "&Id_Region="+usuario.getFkRegion() +
                "&Id_Provincia="+usuario.getFkProvincia() +
                "&Id_Comuna="+usuario.getFkComuna() +
                "&FechaNac="+usuario.getFechaNacimiento() +
                "&Peso="+usuario.getPeso() +
                "&Altura="+usuario.getEstatura() +
                "&Id_Sexo="+usuario.getSexo() +
                "&Id_Objetivo="+usuario.getFkObjetivo() +
                "&Id_Rol="+usuario.getFkRol() +
                "&Id_TipoPersona="+usuario.getFkSomatipo() +
                "&CorreoElectronico="+usuario.getCorreoElectronico() +
                "&Contrasena="+usuario.getContraseña() +
                "&MaximoCalorias="+MaximoCalorias);
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
    }

    /**
     * Metodo para obtener un nuevo id al registrarse un usuario
     * @param mantenedo el nombre del mantenedor
     * @return retorna un nuevo id
     * @throws IOException en caso de problemas con el IO
     * @throws JSONException en caso de problemas con el JSON
     */
    public static int ObtenerNuevoIdUsuario(String mantenedo) throws IOException, JSONException {


        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=n");
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);

                JSONObject response = new JSONObject(responseSTR.toString());
                JSONArray json=response.optJSONArray("Id_Usuario_Nuevo");

                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);
                        nuevoidusuario = jsonObject.getInt("nuevoid");
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
        return nuevoidusuario;
    }


    /**
     * Metodo para actualizar el correo del usuario en la base de datos
     * @param mantenedo el nombre del mantendor
     * @param usuario el objeto usuario
     * @throws IOException en caso de problemas con el IO
     * @throws JSONException en caso de problemas con JSON
     */
    public static void ActualizarCorreoUsuario(String mantenedo, Usuario usuario) throws IOException, JSONException {


        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=ac" +
                "&Id_Usuario="+usuario.getIdUsuario() +
                "&CorreoElectronico="+usuario.getCorreoElectronico());
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);
            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
    }

    /**
     * Metodo para actualizar los datos personales del usuario
     * @param mantenedo el nombre del mantenedor
     * @param usuario el objeto usuario
     * @param MaximoCalorias en maximo de calorias
     * @throws IOException en caso de problemas con IO
     * @throws JSONException en caso de problemas con el JSON
     */
    public static void ActualizarDatosPersonalesUsuario(String mantenedo, Usuario usuario, float MaximoCalorias) throws IOException, JSONException {


        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=adp" +
                "&Id_Usuario="+usuario.getIdUsuario() +
                "&Nombre_Usuario="+usuario.getNombreUsuario() +
                "&Apellido_Paterno="+usuario.getApellidoPaterno() +
                "&Apellido_Materno="+usuario.getApellidoMaterno() +
                "&Alias="+usuario.getNombreAlias() +
                "&Id_Region="+usuario.getFkRegion() +
                "&Id_Provincia="+usuario.getFkProvincia() +
                "&Id_Comuna="+usuario.getFkComuna() +
                "&FechaNac="+usuario.getFechaNacimiento() +
                "&Peso="+usuario.getPeso() +
                "&Altura="+usuario.getEstatura() +
                "&Id_Sexo="+usuario.getSexo() +
                "&Id_Objetivo="+usuario.getFkObjetivo() +
                "&Id_Rol="+usuario.getFkRol() +
                "&Id_TipoPersona="+usuario.getFkSomatipo() +
                "&MaximoCalorias="+MaximoCalorias);
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);

            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
    }

    /**
     * Metodo para eliminar un usuario
     * @param mantenedo el nombre del mantenedor
     * @param idusuario el id del usuario
     * @throws IOException en caso de problemas con el IO
     * @throws JSONException el caso de problemas con el JSON
     */
    public static void EliminarUsuario(String mantenedo, int idusuario) throws IOException, JSONException {


        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=e" +
                "&Id_Usuario="+idusuario);
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);

            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
    }

    /**
     * Metodo para insertar los interes del usuario
     * @param mantenedo el nombre del mantenedor
     * @param idusuario el id del usuario
     * @param idinteres el id del interes del usuario
     * @throws IOException en caso de problemas con el IO
     * @throws JSONException en caso de problemas con el JSON
     */
    public static void InsertarUsuarioInteres(String mantenedo, int idusuario, int idinteres) throws IOException, JSONException {
        int nuevoid = 0;

        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=i" +
                "&idUsuario="+idusuario+
                "&idInteres="+idinteres);
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);

                JSONObject response = new JSONObject(responseSTR.toString());
                JSONArray json=response.optJSONArray("Id_InteresUsuario_Nuevo");

                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject= null ;
                        jsonObject=json.getJSONObject(i);
                         nuevoid = jsonObject.getInt("Id_InteresUsuario_Nuevo");
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
        listausuariointeres.add(new UsuarioInteres(nuevoid,idusuario,idinteres));
    }

    /**
     * Metodo para eliminar los intereses del usuario
     * @param mantenedo el nombre de mantenedor
     * @param idusuariointeres el id usuario interes a eliminar
     * @param idusuario el id del usuario
     * @param idinteres el id el interes
     * @throws IOException en caso de problemas con el IO
     * @throws JSONException en caso de problemas con el JSON
     */
    public static void eliminarUsuarioInteres(String mantenedo, int idusuariointeres, int idusuario, int idinteres) throws IOException, JSONException {

        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=e" +
                "&idUsuario="+idusuario+
                "&idInteres="+idinteres);
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
        listausuariointeres.remove(new UsuarioInteres(idusuariointeres,idusuario,idinteres));
    }

    /**
     * Metodo para traer todos los intereses del usuario
     * @param mantenedo el nombre del mantendor
     * @param idusuariointeres el id usuario interes
     * @param idusuario el id del usuario
     * @param idinteres el id del interes
     * @throws IOException
     * @throws JSONException
     */
    public static void traerUsuarioInteres(String mantenedo, int idusuariointeres, int idusuario, int idinteres) throws IOException, JSONException {

        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=s" +
                "&idUsuario="+idusuario);
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);

                JSONObject response = new JSONObject(responseSTR.toString());
                JSONArray json=response.optJSONArray("InteresUsuario");

                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject= null ;
                        jsonObject=json.getJSONObject(i);
                        listausuariointeres.add(new UsuarioInteres(jsonObject.getInt("Id_InteresUsuario"),
                                jsonObject.getInt("Id_Usuario"),jsonObject.getInt("Id_Interes")));
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
    }


    public static ArrayList<UsuarioInteres> getListausuariointeres() {
        return listausuariointeres;
    }

    public static void setListausuariointeres(ArrayList<UsuarioInteres> listausuariointeres) {
        CrudUsuarioHttpConecction.listausuariointeres = listausuariointeres;
    }
}
