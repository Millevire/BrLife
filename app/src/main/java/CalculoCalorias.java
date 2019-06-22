import com.example.esteban.brlife.Clases.MantenedorDosAtributos;
import com.example.esteban.brlife.Clases.ValorRol;
import com.example.esteban.brlife.ConeionWebServices.CargarBaseDeDatosDosAtributos;
import com.example.esteban.brlife.Enum.SeleccionValorRol;

/**
 * Calculo de calorias por tipo de usuario para consumo diario
 * Ecuaciones de Mifflin-St Jeor
 */
public class CalculoCalorias {


    public static int calcularCalorias(int peso, int altura, int edad, int rol, int sexo){

    //Variable globales
    int TMB=0;
    int CaloriasMaximasDiarias;

    //buscar objeto con idRol
        MantenedorDosAtributos mantenedorDosAtributos=CargarBaseDeDatosDosAtributos.buscar(rol);


        /**
         * La TMB tiene que multiplicarse por un factor
         * correspondiente al nivel de actividad para determinar
         * el requerimiento calórico diario.
         * Ahora buscaremos el valor.
         */
        ValorRol valorRol= SeleccionValorRol.buscarValorRol(mantenedorDosAtributos.getNombreMantenedorDosAtributos());


    //Calculo para un hombre
     if (sexo==1){

         //Clculo de Tasa Metabolica Basal con atributos peso,altura y edad para un hombre
         TMB= (int) ((10*peso)+(6.25*altura)-(5*edad)+5);



         //Verificamos que el objeto no sea nulo
         if (valorRol !=null){
             CaloriasMaximasDiarias= (int) (TMB*valorRol.getValorRol());

             //retornamos valor de calorias para un hombre
             return  CaloriasMaximasDiarias;
         }


     }
     //Calculo para una mujer
     else if (mantenedorDosAtributos.getIdMantenedorDosAtributos()==2){
         //Clculo de Tasa Metabolica Basal con atributos peso,altura y edad para un hombre
         TMB= (int) ((10*peso)+(6.25*altura)-(5*edad)-161);

         CaloriasMaximasDiarias= (int) (TMB*valorRol.getValorRol());

         //retornamos valor de calorias para una mujer
         return  CaloriasMaximasDiarias;
     }

         return 0;
     }








}
