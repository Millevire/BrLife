package com.example.esteban.brlife.Enum;


/**
 * @author BrotherWare
 *
 */
     public enum  SelccionMantenedor {
        TipoProducto("TipoProducto"),
        Region("Region"),
        Rol("Rol"),
        Interes("Interes"),
         HorarioComida("HorarioComida"),
         Producto("Producto"),
         Sabor("Sabor"),
         Marca("Marca"),
         Nutriente("Nutriente"),
         Provincia("Provincia"),
         Comuna("Comuna"),
         TipoPersona("TipoPersona"),
         Objetivo("Objetivo"),
         ProductoNutriente("ProductoNutriente"),
         Usuario("Usuario"),
         InteresUsuario("InteresUsuario");

        private String seleccion;

         SelccionMantenedor(String mantenedor) {
             this.seleccion = mantenedor;
         }
          public String getSeleccion(){
             return seleccion;
          }
     }




