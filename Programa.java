//Universidad del Valle de Guatemala
//Programación Orientada a Objetos
//Catedrático Tomás Gálvez
//Segundo Semestre 2021
//José Daniel Gómez Cabrera
//Carné 21429
//Sección 11
//Actividad: Laboratorio 2

/**
 * Clase Programa.
 * 
 * @author José Daniel Gómez Cabrera
 * @version Programa Class 1.1
 */
public class Programa {
    private String nombre;
    private int espacio_memoria;
    private int ciclos_reloj;
    private int ciclos_restantes;
    private int bloques_memoria;

    /**
     * Constructor de Programa.
     * 
     * @author José Daniel Gómez Cabrera
     * @version Programa 1.1
     */
    public Programa(String nombre, int espacio_memoria, int ciclos_reloj){
        try{
            this.nombre = nombre;
            this.espacio_memoria = espacio_memoria;
            this.ciclos_reloj = ciclos_reloj;
            this.ciclos_restantes = ciclos_reloj;
            this.bloques_memoria = (obtener_bloques_memoria(espacio_memoria));
        }
        catch(Exception e){
            String s = "Programa: Constructor Programa: "+e.getMessage();
            throw new RuntimeException(s);
        }
    }

    /**
     * Actualiza los ciclos de reloj restantes para saber cuando retirar el programa de la memoria RAM.
     * 
     * @author José Daniel Gómez Cabrera
     * @version actualizar_ciclos_restantes 1.1
     */
    public void actualizar_ciclos_restantes(){
        try{
            this.ciclos_restantes -= 1;
        }
        catch(Exception e){
            String s = "Programa: actualizar_ciclos_restantes: "+e.getMessage();
            throw new RuntimeException(s);
        }
    }

    /**
     * Obtiene la cantidad de bloques de memoria del programa a partir de la cantidad de megabytes.
     * Si la division tiene residuo, significa que ocupa debe de ocupar un bloque mas de la division.
     * por ejemplo, 2070 megabytes (cada bloque son 64 megabytes) (2070/64=32.3), tendria que ocupar 33 bloques.
     * 
     * @author José Daniel Gómez Cabrera
     * @version obtener_bloques_memoria 1.1
     * @return int
     * @param megabytes (int)
     */
    private int obtener_bloques_memoria(int megabytes){
        int residue = (megabytes%64);
        int bloques_memoria;
        if(residue==0){
            bloques_memoria = (megabytes/64);
        }
        else{
            bloques_memoria = (megabytes/64)+1;
        }
        return bloques_memoria;
    }

    /**
     * getter de nombre.
     * 
     * @author José Daniel Gómez Cabrera
     * @version get_nombre_programa 1.1
     * @return String
     */
    public String get_nombre_programa(){
        return this.nombre;
    }

    /**
     * getter de megabytes de espacio en memoria.
     * 
     * @author José Daniel Gómez Cabrera
     * @version get_megabytes 1.1
     * @return int
     */
    public int get_megabytes(){
        return this.espacio_memoria;
    }

    /**
     * getter de ciclos de reloj restantes.
     * 
     * @author José Daniel Gómez Cabrera
     * @version get_ciclos_restantes 1.1
     * @return int
     */
    public int get_ciclos_restantes(){
        return this.ciclos_restantes;
    }

    /**
     * getter de cantidad de bloques de memoria que ocupa el programa.
     * 
     * @author José Daniel Gómez Cabrera
     * @version get_bloques 1.1
     * @return int
     */
    public int get_bloques(){
        return this.bloques_memoria;
    }

    
}
