package com.sic;

import java.io.*;
import java.util.ArrayList;

/**
 * Esta clase contiene metodos para leer y escribir datos en archivos .csv
 * Tomado de https://www.javatpoint.com/how-to-read-csv-file-in-java
 * 
 */

public class ManejoCSV {
    
    /**
     * Este metodo lee la informacion de un archivo .csv
     * 
     * @param nombre_archivo Es el nombre del archivo en donde esta almacenada la informacion
     * @return Regresa los datos leidos
     */
    public static ArrayList<String[]> leercsv(String nombre_archivo){
        String line = "", splitBy = ",";
        ArrayList<String[]> str= new ArrayList<String[]>();
        try
        {
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader(nombre_archivo));
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {
                String[] datos = line.split(splitBy);    // use comma as separator 
                 
                /*for(int i = 0; i < datos.length; i++)
                {
                    System.out.print(datos[i]+", ");
                }
                System.out.println(); */ 
                str.add(datos.clone());
            }
            br.close();
        }
        catch (IOException e)   
        {
            e.printStackTrace();  
        }
        return str;
    }

    /**
     * Este metodo escribe datos a un archivo .csv
     * 
     * @param datos Son los datos a escribir
     * @param nombre_archivo Es el nombre del archivo 
     */
    public static void escribirCSV(ArrayList<String[]> datos, String nombre_archivo)
    {
        try {
            FileWriter csvWriter = new FileWriter(nombre_archivo);
            
            for(int i = 0; i < datos.size(); i++)
            {
                for(int j = 0; j < datos.get(i).length; i++)
                {
                    csvWriter.append(datos.get(i)[j]+",");
                }
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
