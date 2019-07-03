package com.example.esteban.brlife.ConeionWebServices;

import com.example.esteban.brlife.Clases.MantenedorDosAtributos;
import com.example.esteban.brlife.Clases.UsuarioInteres;

import java.util.ArrayList;


/**
 * @author BrotherWare
 *
 */
public class CargarBaseDeDatosUsuarioInteres {

    public static ArrayList<UsuarioInteres> listaUsuarioInteres=new ArrayList<>();


    public static ArrayList<UsuarioInteres> getListaUsuarioInteres() {
        return listaUsuarioInteres;
        }

        public static void llenarListaUsuarioInteres(ArrayList <MantenedorDosAtributos> listaInteres){

       if (listaInteres.size()>=0){
           for(MantenedorDosAtributos interes: listaInteres){
               listaUsuarioInteres.add(new UsuarioInteres(0,0,interes.getIdMantenedorDosAtributos()));

           }


       }

        }

    }

