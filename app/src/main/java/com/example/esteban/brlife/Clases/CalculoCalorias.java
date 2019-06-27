package com.example.esteban.brlife.Clases;

import com.example.esteban.brlife.Clases.MantenedorDosAtributos;
import com.example.esteban.brlife.Clases.ValorRol;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.ConeionWebServices.CargarMantenedorDosAtributosHttpConecction;
import com.example.esteban.brlife.Enum.SeleccionValorRol;

/**
 * Calculo de calorias por tipo de usuario para consumo diario
 * Ecuaciones de Mifflin-St Jeor
 */
public class CalculoCalorias {

    public static float caloriasMaximasDiarias;

   public static float calcularCalorias(float peso, float altura, int edad, int rol, int sexo, int objetivo) {

        //Variable globales
        float TMB = 0;
        int valorAumentoPeso = 500;
        int valorBajarPeso = 500;


        //buscar objeto con idRol
        String nombeRol = CargarMantenedorDosAtributosHttpConecction.buscarNombreRol(rol);
        String nombreObjetivo = CargarMantenedorDosAtributosHttpConecction.buscarNombreObjetivo(objetivo);


        /**
         * La TMB tiene que multiplicarse por un factor
         * correspondiente al nivel de actividad para determinar
         * el requerimiento calórico diario.
         * Ahora buscaremos el valor.
         */
        ValorRol valorRol = SeleccionValorRol.buscarValorRol(nombeRol);


        //Calculo para un hombre
        if (sexo == 1) {

            //Clculo de Tasa Metabolica Basal con atributos peso,altura y edad para un hombre
            TMB = (float) ((10 * peso) + (6.25 * altura) - (5 * edad) + 5);


            //Verificamos que el objeto no sea nulo
            if (valorRol != null) {

                float valor=valorRol.getValorRol();

                caloriasMaximasDiarias = (TMB * valorRol.getValorRol());

                //retornamos valor de calorias para un hombre
                if (nombreObjetivo.equals("Bajar de peso")) {
                    return (float) (caloriasMaximasDiarias-500.0);
                }else if(nombreObjetivo.equals("Subir de peso")){
                    return (float) (caloriasMaximasDiarias+500.0);
                }else return caloriasMaximasDiarias;


            }


        }
        //Calculo para una mujer
        else if (sexo == 2) {
            //Clculo de Tasa Metabolica Basal con atributos peso,altura y edad para un hombre
            TMB = (float) ((10 * peso) + (6.25 * altura) - (5 * edad) - 161);

            caloriasMaximasDiarias =  (TMB * valorRol.getValorRol());

            //retornamos valor de calorias para una mujer
            if (nombreObjetivo.equals("Bajar de peso")) {
                return (float) (caloriasMaximasDiarias-500.0);
            }else if(nombreObjetivo.equals("Subir de peso")){
                return (float) (caloriasMaximasDiarias+500.0);
            }else return caloriasMaximasDiarias;
        }

        return 0;
    }



}
