package com.example.esteban.brlife.Clases;


/**
 * @author BrotherWare
 *
 * Clase RegistroHorarioTotales.
 *
 * Total de calorias por horario de comida
 */
public class RegistroHorarioTotales {
    public int idhorariocomida;
    public float totalhorariocomida;

    public RegistroHorarioTotales(int idhorariocomida, float totalhorariocomida) {
        this.idhorariocomida = idhorariocomida;
        this.totalhorariocomida = totalhorariocomida;
    }

    public RegistroHorarioTotales() {

    }

    public int getIdhorariocomida() {
        return idhorariocomida;
    }

    public void setIdhorariocomida(int idhorariocomida) {
        this.idhorariocomida = idhorariocomida;
    }

    public float getTotalhorariocomida() {
        return totalhorariocomida;
    }

    public void setTotalhorariocomida(float totalhorariocomida) {
        this.totalhorariocomida = totalhorariocomida;
    }


}
