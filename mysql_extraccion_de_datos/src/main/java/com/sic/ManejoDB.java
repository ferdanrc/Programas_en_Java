package com.sic;

import java.sql.*;
import java.util.ArrayList;

/**
 * Esta clase contiene metodos para establecer conexion con una base de datos y realizar diferentes operaciones
 * Basado en https://github.com/codigo-iot/Java-y-MySQL_Conexion_Base_De_Datos
 */

public class ManejoDB {

    /**
     * Este metodo establece una conexion con la base de datos
     * 
     * @param db_path Es la direccion de la base de datos
     * @param usuario Es el nombre del usuario con el que se accede a la base de datos
     * @param passwd Es la contraseña de la base de datos
     * @return Regresa el objeto con la conexion a la base de datos, si no hay errores
     */
    public static Connection nueva_conexion(String db_path, String usuario, String passwd){
        try{
            //Llamada a la libreria mysql conector
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Nueva conexión                           conexión a localhost |   nombre de bd | ususario mysql | contraseña  
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datos", "usuariotabla", "pass1234");
            Connection con = DriverManager.getConnection(db_path, usuario, passwd);
            return con;
        } catch (Exception e) {
            //Imprimir errores 
            System.out.println(e);
        }
        return null;
    }

    /**
     * Este metodo agrega un conjunto de mediciones a la base de datos
     * 
     * @param db_path Es la direccion de la base de datos
     * @param nombre_tabla Es la tabla en donde se guardan los datos
     * @param usuario Es el nombre del usuario con el que se accede a la base de datos 
     * @param passwd Es la contraseña de la base de datos
     * @param datos Son los datos a almacenar en la base de datos 
     */
    public static void agregar_datos(String db_path,String nombre_tabla, String usuario, String passwd, ArrayList<medicion> datos)
    {
        //Try-Catch para manejo de errores
        try {
            Connection con = nueva_conexion(db_path,usuario,passwd);
            for(int i = 0; i < datos.size(); i++ )
            {
                //Nuevo statement para crear datos
                Statement stmt = con.createStatement();
                //Creación de Query | Insertar valores 
                //Estos valores deben coincidir con los valores que se asignaron durante la creación de la tabla
                String str="";
                // Se concatena toda la informacion de la medicion en un string
                for(int j = 0; j < datos.get(i).getStr_mdn().length; j++)
                {
                    if(datos.get(i).getStr_mdn()[j].length() > 10)
                    {
                        str += datos.get(i).getStr_mdn()[j].substring(0,9);
                    }
                    else
                    {
                        str += datos.get(i).getStr_mdn()[j];
                    }
                    if(j != datos.get(i).getStr_mdn().length-1)
                    {
                        str += ",";
                    }
                }
                //System.out.println("Dato a agregar: "+str);
                stmt.executeUpdate("INSERT INTO "+nombre_tabla+" VALUES("+str+")");
                //Se cierra la conexión 
            }

            con.close();
        } catch (Exception e) {
            //Imprimir errores 
            System.out.println(e);
        }
    }

    /**
     * Este metodo elimina un conjunto de mediciones a la base de datos por id
     * 
     * @param db_path Es la direccion de la base de datos
     * @param nombre_tabla Es la tabla en donde se guardan los datos
     * @param usuario Es el nombre del usuario con el que se accede a la base de datos 
     * @param passwd Es la contraseña de la base de datos
     * @param id Es el conjunto de id asociado a las mediciones a eliminar
     */
    public static void eliminar_datos_por_id(String db_path,String nombre_tabla, String usuario, String passwd,int[] id){
        //Try-Catch para manejo de errores
        try {
            Connection con = nueva_conexion(db_path,usuario,passwd);

            for(int i = 0; i < id.length; i++)
            {
                //Nuevo statement para eliminar datos
                PreparedStatement st = con.prepareStatement("DELETE FROM "+nombre_tabla+" WHERE id = "+id[i]+";");
                //Ejecutar statement
                st.executeUpdate();
            }

            //Se cierra la conexión 
            con.close();

        } catch (Exception e) {
            //Imprimir errores 
            System.out.println(e);
        }
    }
}
