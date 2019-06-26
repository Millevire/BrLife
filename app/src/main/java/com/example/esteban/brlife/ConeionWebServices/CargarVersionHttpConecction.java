package com.example.esteban.brlife.ConeionWebServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.esteban.brlife.Version;
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

public class CargarVersionHttpConecction {
    public static ArrayList<Version> listaVersion;
    static ProgressDialog progreso;


    public static ArrayList<Version> CargarVersiones(Context context) throws  IOException, JSONException {
        listaVersion = new ArrayList<>();
        progreso=new ProgressDialog(context);
        progreso.setMessage(context.getString(R.string.mensajeBarraProgresoCargando));
        progreso.show();

        URL url = new URL(context.getString(R.string.URLwebServicePart1)
                +"VTabla"
                +context.getString(R.string.URLwebServicePart2));
        HttpURLConnection conexion = null;
        try {
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setConnectTimeout(20000);
            conexion.setReadTimeout(20000);
            conexion.setUseCaches(false);
            if (conexion.getResponseCode() == 200) {
                progreso.hide();
                String jsonCompleto = "";
                InputStream responseBody = conexion.getInputStream();
                InputStreamReader isr = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String linea;
                StringBuilder responseSTR = new StringBuilder();
                while ((linea = br.readLine()) != null)
                    responseSTR.append(linea);

                JSONObject response = new JSONObject(responseSTR.toString());


                Version version =null;

                listaVersion.clear();

                JSONArray json=response.optJSONArray("VTabla");
                try {
                    for (int i=0; i<json.length(); i++){
                        JSONObject jsonObject=null;

                        version =new Version();
                        jsonObject=json.getJSONObject(i);
                        version.setId_Tabla(jsonObject.getInt("Id_Tabla"));

                        version.setValor((float) jsonObject.getDouble("Version"));
                        version.setNombre_Tabla(jsonObject.getString("Nombre_Tabla"));
                        Toast.makeText(context, ""+jsonObject.getString("Nombre_Tabla"), Toast.LENGTH_SHORT).show();
                        listaVersion.add(version);

                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }else
                throw new RuntimeException("No se puede contectar al servidor");
        }finally {
            conexion.disconnect();
        }
        return listaVersion;
    }





}
