package com.example.esteban.brlife.ConeionWebServices;

import com.example.esteban.brlife.Clases.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CrudUsuarioHttpConecction {
    public static int acceso;
    public static Usuario usuario;
    public static int correovalidado;
    public static float maximocalorias;
    public static int nuevoidusuario;

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

//                JSONObject response = new JSONObject(responseSTR.toString());
//                JSONArray json=response.optJSONArray("Usuario");
//
//                try {
//                    for (int i=0; i<json.length(); i++){
//                        JSONObject jsonObject= null ;
//                        jsonObject=json.getJSONObject(i);
//                        usuario.setIdUsuario(jsonObject.getInt("Id_Usuario"));
//                    }
//
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
    }

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

//                JSONObject response = new JSONObject(responseSTR.toString());
//                JSONArray json=response.optJSONArray("Usuario");
//
//                try {
//                    for (int i=0; i<json.length(); i++){
//                        JSONObject jsonObject= null ;
//                        jsonObject=json.getJSONObject(i);
//                        usuario.setIdUsuario(jsonObject.getInt("Id_Usuario"));
//                    }
//
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
    }

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

//                JSONObject response = new JSONObject(responseSTR.toString());
//                JSONArray json=response.optJSONArray("Usuario");
//
//                try {
//                    for (int i=0; i<json.length(); i++){
//                        JSONObject jsonObject= null ;
//                        jsonObject=json.getJSONObject(i);
//                        usuario.setIdUsuario(jsonObject.getInt("Id_Usuario"));
//                    }
//
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
    }

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

//                JSONObject response = new JSONObject(responseSTR.toString());
//                JSONArray json=response.optJSONArray("Usuario");
//
//                try {
//                    for (int i=0; i<json.length(); i++){
//                        JSONObject jsonObject= null ;
//                        jsonObject=json.getJSONObject(i);
//                        usuario.setIdUsuario(jsonObject.getInt("Id_Usuario"));
//                    }
//
//                }catch (JSONException e) {
//                    e.printStackTrace();
//                }


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
    }
}
