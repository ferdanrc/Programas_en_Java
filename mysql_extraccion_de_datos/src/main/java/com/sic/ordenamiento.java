package com.sic;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Esta clase ordena los datos de un grupo de mediciones por el valor de medicion
 * Por: Fernando Daniel Ramirez Cruz
 * 
 */

public class ordenamiento{  

    /**
     * Este metodo ordena las mediciones segun el comparador definido en la clase medicion
     */
    public static ArrayList<medicion> ordenar_por_valor_medicion(mediciones grupo_mediciones)   
    {
        ArrayList<medicion> mdns = new ArrayList<medicion>(grupo_mediciones.getMdns().size());
        for(int i = 0; i < grupo_mediciones.getMdns().size(); i++)
        {
            mdns.add(new medicion(grupo_mediciones.getMdns().get(i).getStr_mdn().clone()));
        }

        //System.out.println("Elementos sin ordenar: \n"+grupo_mediciones.toString());
        Collections.sort(mdns); // Se ordenan todos los datos del arreglo de mediciones
        //System.out.println("Elementos ordenados: \n"+grupo_mediciones.toString());

        return mdns;
    }
}