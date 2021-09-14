//Universidad del Valle de Guatemala
//Programación Orientada a Objetos
//Catedrático Tomás Gálvez
//Segundo Semestre 2021
//José Daniel Gómez Cabrera
//Carné 21429
//Sección 11
//Actividad: Laboratorio 2

import java.util.ArrayList;

/**
 * Clase SDR.
 * 
 * @author José Daniel Gómez Cabrera
 * @version SDR Class 1.1
 */
public class SDR {
    private ArrayList<Programa> lista_ejecucion = new ArrayList<Programa>();
    private ArrayList<Programa> lista_cola = new ArrayList<Programa>();
    private String[] bloques_memoria = new String[256];
    private int espacios_libres;
    private int espacios_ocupados;

    /**
     * Constructor de SDR.
     * 
     * @author José Daniel Gómez Cabrera
     * @version SDR 1.1
     */
    public SDR(){
        this.espacios_libres = 0;
        this.espacios_ocupados = 0;
        for(int k = 0; k<bloques_memoria.length; k++){
            if(k<30){
                bloques_memoria[k] = "Sistema Operativo";
                this.espacios_ocupados += 1;    
            }
            else{
                bloques_memoria[k] = "Libre";
                this.espacios_libres += 1;
            }
        }
    }

    /**
     * Metodo para realizar un ciclo de reloj.
     * El metodo realiza:
     * 1 quita los programas que ya cumplieron sus ciclos de reloj.
     * 2 actualiza los ciclos de reloj para poder llevar un control y para que en el siguiente ciclo se pueda saber si deben de salir de la memoria.
     * 3 actualiza la cola de ejecucion y los agrega a la memoria los programas que quepan si hay espacio en la memoria.
     * 4 si no hay programas en cola, simplemente agrega a la memoria los programas que quepan.
     * 5 actualiza la lista de programas en ejecucion y programas en cola.
     * 
     * @author José Daniel Gómez Cabrera
     * @version ciclo_reloj_sdr 1.1
     * @param int[] (indices de los programas que se desean ejecutar)
     */
    public void ciclo_reloj_sdr(int[] indices){
        try{
            Programa[] programas_a_ejecutar = convertir_indices_a_programas(indices);
            //Quitar programa de la lista de ejecucion y los bloques de memoria si sus ciclos de reloj restantes son 0.
            if(!this.lista_ejecucion.isEmpty()){
                for(int k = 0; k<this.lista_ejecucion.size(); k++){
                    if((this.lista_ejecucion.get(k).get_ciclos_restantes())==0){
                        this.lista_ejecucion.remove(k);
                        for(int i = 0; i<256; i++){
                            if((bloques_memoria[i]).equals(this.lista_ejecucion.get(k).get_nombre_programa())){
                                this.bloques_memoria[k] = "Libre";
                                this.espacios_libres += 1;
                                this.espacios_ocupados -= 1;
                            }
                        }
                    }
                }
            }
            //Actualizar cilos de reloj restantes para los programas en la lista de ejecuion.
            if(!this.lista_ejecucion.isEmpty()){
                for(int j = 0; j<lista_ejecucion.size(); j++){
                    lista_ejecucion.get(j).actualizar_ciclos_restantes();
                }
            }
            //Actualizar cola con los programas que se desean ejecutar por parte del usuario.
            if(!this.lista_cola.isEmpty()){
                ArrayList<Programa> cola_actualizada = actualizar_cola(programas_a_ejecutar);
                
            }


        }
        catch(Exception e){
            String s = "SDR: ciclo_reloj: "+e.getMessage();
            throw new RuntimeException(s);
        }
    }

    /**
     * convierte un Array de indices a un Array de objetos Programa.
     * utiliza el metodo obtener_programa() de BibliotecaProgramas para llevar a cabo la conversion.
     * 
     * @author José Daniel Gómez Cabrera
     * @version convertir_indices_a_programas 1.1
     * @return Programa[]
     * @param int[] (indices de los programas)
     */
    private Programa[] convertir_indices_a_programas(int[] indices){
        try{
            BibliotecaProgramas biblioteca1 = new BibliotecaProgramas();
            Programa[] programas_a_ejecutar = new Programa[indices.length];
            for(int k = 0; k<programas_a_ejecutar.length; k++){
                programas_a_ejecutar[k] = biblioteca1.obtener_Programa(indices[k]);
            }
            return programas_a_ejecutar;
        }
        catch(Exception e){
            String s = "convertir_indices_a_programas: "+e.getMessage();
            throw new RuntimeException(s);
        }
    }

    /**
     * Actualiza la cola de ejecucion con los programas que el usuario desee ejecutar.
     * prioriza los programas que el usuario desee a los programas que ya estaban en la cola de ejecucion.
     * 
     * @author José Daniel Gómez Cabrera
     * @version convertir_indices_a_programas 1.1
     * @return ArrayList<Programa>
     * @param Programa[] (programas a ejecutar)
     */
    private ArrayList<Programa> actualizar_cola(Programa[] programas_a_ejecutar){
        try{
            ArrayList<Programa> cola_actualizada = new ArrayList<Programa>();
            for(int k = 0; k<programas_a_ejecutar.length; k++){
                cola_actualizada.add(programas_a_ejecutar[k]);
            }
            for(int k = 0; k<this.lista_cola.size(); k++){
                cola_actualizada.add(lista_cola.get(k));
            }
            return cola_actualizada;
        }
        catch(Exception e){
            String s = "actualizar_cola: "+e.getMessage();
            throw new RuntimeException(s);
        }
    }

    // public String[] get_memoria_sdr(){
    //     return this.bloques_memoria;
    // }

    // public int get_espacios_libres(){
    //     return this.espacios_libres;
    // }

    // public int get_espacios_ocupados(){
    //     return this.espacios_ocupados;
    // }
}
