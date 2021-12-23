package com.sic;

/**
 * Esta clase define los datos que contiene una medicion
 * 
 * Por: Fernando Daniel Ramirez Cruz
 */

public class medicion implements Comparable<medicion>{
    private int id; // Es el identificador de la medicion
    private String fecha; // Es la fecha de cuando se tomo la medicion
    private String hora; // Es la hora de cuando se tomo la medicion
    private double medicion_distancia; // Es el valor de la medicion de distancia
    private double medicion_temperatura; // Es el valor de la medicion de temperatura
    private double medicion_humedad; // Es el valor de la medicion de humedad
    private String[] str_mdn; // Son todos los valores anteriores en string
    private int num_mediciones = 6; // Es el numero de datos de la medicion (hay 6 campos)

    /**
     * Constructor de una medicion con datos en blanco
     */
    public medicion(){
        this.id = 0; 
        this.fecha = "";
        this.hora = "";
        this.medicion_distancia = 0;
        this.medicion_temperatura = 0;
        this.medicion_humedad = 0;

        this.str_mdn = new String[num_mediciones];
        for(int i = 0; i < this.str_mdn.length; i++)
        {
            str_mdn[i] = "";
        }
    }

    /**
     * Constructor indicando los valores de la medicion
     * 
     * @param id Es el identificador de la medicion
     * @param fecha Es la fecha de cuando se tomo la medicion
     * @param hora Es la hora de cuando se tomo la medicion
     * @param medicion_distancia Es el valor de la medicion de distancia
     * @param medicion_temperatura Es el valor de la medicion de temperatura
     * @param medicion_humedad Es el valor de la medicion de humedad
     */
    public medicion(int id, String fecha, String hora, double medicion_distancia, double medicion_temperatura, double medicion_humedad){
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.medicion_distancia = medicion_distancia;
        this.medicion_temperatura = medicion_temperatura;
        this.medicion_humedad = medicion_humedad;
        
        this.str_mdn = new String[num_mediciones];
        this.str_mdn[0] = Integer.toString(id);
        this.str_mdn[1] = fecha;
        this.str_mdn[2] = hora;
        this.str_mdn[3] = Double.toString(medicion_distancia);
        this.str_mdn[4] = Double.toString(medicion_temperatura);
        this.str_mdn[5] = Double.toString(medicion_humedad);
    }

    /**
     * Constructor con un arreglo de datos de una medicion
     * 
     * @param datos Es un arreglo string que contiene los datos de la medicion
     */
    public medicion(String[] datos)
    {
        try{
            this.id = Integer.parseInt(datos[0]);
            
            this.str_mdn = new String[num_mediciones];
            this.str_mdn[0] = datos[0];
            
            // En el caso de contar con 6 campos, asignar cada dato en string a la medicion
            if(datos.length == 6)
            {
                this.fecha = datos[1].substring(0,8);
                this.hora = datos[2];
                this.medicion_distancia = Double.parseDouble(datos[3]);
                this.medicion_temperatura = Double.parseDouble(datos[4]);
                this.medicion_humedad = Double.parseDouble(datos[5]);

                this.str_mdn[1] = datos[1];
                this.str_mdn[2] = datos[2];
                this.str_mdn[3] = datos[3];
                this.str_mdn[4] = datos[4];
                this.str_mdn[5] = datos[5];
            }
            // En caso contrario, la fecha y la hora vienen juntos, por lo que hay que separarlos
            else
            {
                this.fecha = datos[1].substring(0,8);
                this.hora = datos[1].substring(8);
                this.medicion_distancia = Double.parseDouble(datos[2]);
                this.medicion_temperatura = Double.parseDouble(datos[3]);
                this.medicion_humedad = Double.parseDouble(datos[4]);

                this.str_mdn[1] = datos[1].substring(0,8);
                this.str_mdn[2] = datos[1].substring(8);
                this.str_mdn[3] = datos[2];
                this.str_mdn[4] = datos[3];
                this.str_mdn[5] = datos[4];
            }

        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
        this.str_mdn[0] = Integer.toString(id);
    }

    public String getFecha(){
        return this.fecha;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
        this.str_mdn[1] = fecha;
    }

    public String getHora(){
        return this.hora;
    }

    public void setHora(String hora){
        this.hora = hora;
        this.str_mdn[2] = hora;
    }

    public double getMedicion_distancia(){
        return this.medicion_distancia;
    }

    public void setMedicion_distancia(double medicion_distancia){
        this.medicion_distancia = medicion_distancia;
        this.str_mdn[3] = Double.toString(medicion_distancia);
    }

    public double getMedicion_temperatura(){
        return this.medicion_distancia;
    }

    public void setMedicion_temperatura(double medicion_temperatura){
        this.medicion_distancia = medicion_temperatura;
        this.str_mdn[4] = Double.toString(medicion_temperatura);
    }

    public double getMedicion_humedad(){
        return this.medicion_distancia;
    }

    public void setMedicion_humedad(double medicion_humedad){
        this.medicion_distancia = medicion_humedad;
        this.str_mdn[5] = Double.toString(medicion_humedad);
    }

    public String[] getStr_mdn(){
        return this.str_mdn;
    }

    /**
     * Comparador por valor de la medicion
     * Idea adaptada de https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
     */
    @Override
    public int compareTo(medicion mdn) {
        return Double.compare(this.getMedicion_distancia(), mdn.getMedicion_distancia());
    }

    @Override
    public String toString(){
        String str = "";
        str += this.id + ", " + this.fecha + ", " + this.hora + ", " + this.medicion_distancia + ", " + this.medicion_temperatura + ", " + this.medicion_humedad;
        return str;
    }
}