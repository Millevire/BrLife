package com.example.esteban.brlife.ConeionWebServices;

import com.example.esteban.brlife.Clases.RegistroDiario;
import com.example.esteban.brlife.Clases.RegistroHorarioTotales;
import com.example.esteban.brlife.Clases.RegistroUsuario;

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

public class CargarRegistroUsuarioHttpConexion {
    public static int dia;
    public static int mes;
    public static int ano;
    public static ArrayList<RegistroUsuario> listaRegistro = new ArrayList<>();
    public static ArrayList<RegistroHorarioTotales> listatotales = new ArrayList<>();
    public static ArrayList<RegistroDiario> listadiaria = new ArrayList<>();

    public static ArrayList<RegistroUsuario> getListaRegistro() {
        return listaRegistro;
    }

    public static void TraerDatosRegistroUsuario(String mantenedo, int idusuario, int dia, int mes, int ano, int horariocomida) throws IOException, JSONException {

        //listaRegistro = new ArrayList<>();
        listaRegistro.clear();
        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=s" +
                "&Id_Usuario="+idusuario+
                "&Dia="+dia +
                "&Mes="+mes +
                "&Ano="+ano+
                "&Id_HorarioComida="+horariocomida);
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
                JSONArray json=response.optJSONArray("RegistroUsuario");

                RegistroUsuario registroUsuario;

                try {
                    for (int i=0; i<json.length(); i++){
                        registroUsuario = new RegistroUsuario();
                        JSONObject jsonObject= null ;
                        jsonObject=json.getJSONObject(i);
                        registroUsuario.setIdregistrousuario(jsonObject.getInt("Id_RegistroUsuario"));
                        registroUsuario.setIdusuario(jsonObject.getInt("Id_Usuario"));
                        registroUsuario.setDia(jsonObject.getInt("Dia"));
                        registroUsuario.setMes(jsonObject.getInt("Mes"));
                        registroUsuario.setAno(jsonObject.getInt("Ano"));
                        registroUsuario.setHora(jsonObject.getString("Hora"));
                        registroUsuario.setIdproducto(jsonObject.getInt("Id_Producto"));
                        registroUsuario.setIdhorariocomida(jsonObject.getInt("Id_HorarioComida"));
                        registroUsuario.setValorporcion((float) jsonObject.getDouble("Valor_Porcion"));
                        listaRegistro.add(registroUsuario);

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

    public static void TraerDatosRegistroUsuarioTotales(String mantenedo, int idusuario, int dia, int mes, int ano) throws IOException, JSONException {

        //listatotales = new ArrayList<>();
        listatotales.clear();
        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=stth" +
                "&Id_Usuario="+idusuario+
                "&Dia="+dia +
                "&Mes="+mes +
                "&Ano="+ano);
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
                JSONArray json=response.optJSONArray("TotalesHorarioComida");

                RegistroHorarioTotales registroHorarioTotales;

                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject= null ;
                        jsonObject=json.getJSONObject(i);
                        registroHorarioTotales = new RegistroHorarioTotales();
                        registroHorarioTotales.setIdhorariocomida(jsonObject.getInt("Id_HorarioComida"));
                        registroHorarioTotales.setTotalhorariocomida((float) jsonObject.getDouble("ValorHorarioComidaTotal"));
                        listatotales.add(registroHorarioTotales);
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

    public static float TraerDatosRegistroUsuarioDiaria(String mantenedo, int idusuario, int dia, int mes, int ano) throws IOException, JSONException {

        listadiaria = new ArrayList<>();
        listadiaria.clear();
        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=sd" +
                "&Id_Usuario="+idusuario+
                "&Dia="+dia +
                "&Mes="+mes +
                "&Ano="+ano);
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
                JSONArray json=response.optJSONArray("RegistroDiario");

                RegistroHorarioTotales registroHorarioTotales;

                RegistroDiario registroDiario;

                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject= null ;
                        jsonObject=json.getJSONObject(i);
                        registroDiario = new RegistroDiario();
                        registroDiario.setIdusuario(jsonObject.getInt("Id_Usuario"));
                        registroDiario.setDia(jsonObject.getInt("Dia"));
                        registroDiario.setMes(jsonObject.getInt("Mes"));
                        registroDiario.setAno(jsonObject.getInt("Ano"));
                        registroDiario.setIdnutriente(jsonObject.getInt("Id_Nutriente"));
                        registroDiario.setValornutriente((float) jsonObject.getDouble("Valor_Nutriente"));
                        listadiaria.add(registroDiario);
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }


            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
        for (RegistroDiario e:listadiaria) {
            if (e.getIdnutriente() == 1){
                return e.getValornutriente();
            }
        }
        return  0;
    }

    public static void GuardarRegistro(String mantenedo, RegistroUsuario registroUsuario) throws IOException, JSONException {

        //listadiaria = new ArrayList<>();
        listadiaria.clear();
        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=i" +
                "&Id_Usuario="+registroUsuario.getIdusuario()+
                "&Dia="+registroUsuario.getDia() +
                "&Mes="+registroUsuario.getMes() +
                "&Ano="+registroUsuario.getAno()+
                "&Hora="+registroUsuario.getHora() +
                "&Id_Producto="+registroUsuario.getIdproducto() +
                "&Id_HorarioComida="+registroUsuario.getIdhorariocomida() +
                "&Valor_Porcion="+registroUsuario.getValorporcion());
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
        listaRegistro.add(registroUsuario);
    }

    public static void EliminarRegistro(String mantenedo, RegistroUsuario registroUsuario) throws IOException, JSONException {

        //listadiaria = new ArrayList<>();
        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=e" +
                "&Id_RegistroUsuario="+registroUsuario.getIdregistrousuario()+
                "&Id_Usuario="+registroUsuario.getIdusuario()+
                "&Dia="+registroUsuario.getDia() +
                "&Mes="+registroUsuario.getMes() +
                "&Ano="+registroUsuario.getAno()+
                "&Id_Producto="+registroUsuario.getIdproducto() +
                "&Valor_Porcion="+registroUsuario.getValorporcion());
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
        listaRegistro.remove(registroUsuario);
    }

    public static void ActualizarRegistro(String mantenedo, RegistroUsuario registroUsuario) throws IOException, JSONException {

        //listadiaria = new ArrayList<>();
        URL url = new URL("http://www.brotherwareoficial.com/WebServices/Mantenedor"+mantenedo+".php?tipoconsulta=a" +
                "&Id_RegistroUsuario="+registroUsuario.getIdregistrousuario()+
                "&Id_Usuario="+registroUsuario.getIdusuario()+
                "&Dia="+registroUsuario.getDia() +
                "&Mes="+registroUsuario.getMes() +
                "&Ano="+registroUsuario.getAno()+
                "&Hora="+registroUsuario.getHora() +
                "&Id_Producto="+registroUsuario.getIdproducto() +
                "&Id_HorarioComida="+registroUsuario.getIdhorariocomida() +
                "&Valor_Porcion="+registroUsuario.getValorporcion());
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

        int index = 0;
        for (RegistroUsuario e: listaRegistro) {
            if (e.getIdregistrousuario() == registroUsuario.getIdregistrousuario()){
                index = listaRegistro.indexOf(e);
            }
        }
        listaRegistro.remove(index);
        listaRegistro.add(registroUsuario);
    }

    public static ArrayList<RegistroHorarioTotales> getListatotales() {
        return listatotales;
    }

    public static ArrayList<RegistroDiario> getListadiaria() {
        return listadiaria;
    }
}
