package com.darcan.amazonviewer.models;

import java.util.Date;

public interface IVisualizable {
    /**
     * Este metodo captura el tiempo de inicio de visualizacion
     * @param dateI. Es un objeto de tipo date {@code Date} con el tiempo de inicio
     * @return Devuelve la fecha y hora capturada
     */
    Date startToSee(Date dateI);
    /**
     * Este metodo captura el tiempo exacto de final de visualizacion
     * @param dateI Es un objeto de ti[po {@code} Date con el tiempo de inicio
     * @param dateF Es un objeto de ti[po {@code} Date con el tiempo de finalizacion
     */

    void stopToSee(Date dateI, Date dateF);
}