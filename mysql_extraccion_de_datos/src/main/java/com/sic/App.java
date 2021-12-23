package com.sic;

/**
 * Almacenamiento de mediciones en una base de datos
 * Por: Fernando Daniel Ramirez Cruz
 * 
 * Este programa permite guardar en una base de datos las mediciones almacenadas en
 * archivos con datos separados por comas. Este archivo debe tener extension .csv. 
 * Por ejemplo: 1,23.7,54.6,43.5
 * 
 * 
 */

public class App {

    /**
     * Este metodo guarda un conjunto de mediciones almacenadas en un archivo .csv
     * 
     */
    public static void main(String[] args) {
        limpiar_db(); // Primero limpia la base de datos
        mediciones mediciones_sensores = new mediciones(ManejoCSV.leercsv("mediciones3.csv")); // lee las mediciones del archivo .csv
        
        System.out.println("mediciones no ordenadas: \n"+mediciones_sensores.toString()); // Muestra las mediciones no ordenadas
        mediciones_sensores.setMdns(ordenamiento.ordenar_por_valor_medicion(mediciones_sensores)); // Ordena las mediciones por el valor de cada medicion
        System.out.println("mediciones ordenadas: \n"+mediciones_sensores.toString()); // Muestra las mediciones ordenadas

        ManejoDB.agregar_datos("jdbc:mysql://localhost:3306/datos","datos_sensores", "fernando", "pass1234", mediciones_sensores.getMdns()); // Guarda los datos en la base de datos
    }

    /**
     * Este metodo limpia todos los datos almacenados en la base de datos
     * 
     */
    public static void limpiar_db(){
        int[] id_eliminar = new int[100];
        for(int i = 0; i < 100; i++)
        {
            id_eliminar[i] = i;
        }
        ManejoDB.eliminar_datos_por_id("jdbc:mysql://localhost:3306/datos","datos_sensores", "fernando", "pass1234",id_eliminar);
    }
}
