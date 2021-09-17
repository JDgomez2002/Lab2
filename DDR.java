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
 * Clase DDR.
 * 
 * @author José Daniel Gómez Cabrera
 * @version DDR Class 1.1
 */
public class DDR {
    private ArrayList<Programa> lista_ejecucion_ddr = new ArrayList<Programa>();
    private ArrayList<Programa> lista_cola_ddr = new ArrayList<Programa>();
    private ArrayList<String> bloques_memoria_ddr = new ArrayList<String>();
    private int espacios_libres_ddr;
    private int espacios_ocupados_ddr;
    private String tamano_memoria_ddr; //4GB, 8GB, 16GB o 32GB

    /**
     * Constructor de DDR.
     * 
     * @author José Daniel Gómez Cabrera
     * @version DDR 1.1
     */
    public DDR(int[] indices){
        this.espacios_libres_ddr = 0;
        this.espacios_ocupados_ddr = 0;
        //iniciar memoria con 4GB (64 bloques)
        for(int k = 0; k<64; k++){
            if(k<30){
                bloques_memoria_ddr.add("Sistema Operativo");
                this.espacios_ocupados_ddr += 1;
            }
            else{
                bloques_memoria_ddr.add("Libre");
                this.espacios_libres_ddr += 1;
            }
        }
        this.tamano_memoria_ddr = "4GB (64 Bloques)";
        ciclo_reloj_ddr(indices);
    }

    /**
     * Metodo para realizar un ciclo de reloj.
     * El metodo realiza:
     * 1 quita los programas que ya cumplieron sus ciclos de reloj.
     * 2 actualiza los ciclos de reloj para poder llevar un control y para que en el siguiente ciclo se pueda saber si deben de salir de la memoria.
     * 3 actualiza la cola de ejecucion y los agrega a la memoria los programas que quepan si hay espacio en la memoria.
     * 4 si no hay programas en cola, simplemente agrega a la memoria los programas que quepan.
     * 5 actualiza la lista de programas en ejecucion y programas en cola.
     * 6 si el usuario no desea agregar programas para ejecutar en el ciclo de reloj siguiente, trabaja unicamente con los programas que esten en ejecucion y cola anterior.
     * 
     * @author José Daniel Gómez Cabrera
     * @version ciclo_reloj_sdr 1.1
     * @param int[] (indices de los programas que se desean ejecutar)
     */
    public void ciclo_reloj_ddr(int[] indices){
        try{
            Programa[] programas_a_ejecutar = convertir_indices_a_programas_ddr(indices);
            //Quitar programa de la lista de ejecucion y los bloques de memoria si sus ciclos de reloj restantes son 0.
            ArrayList<Programa> copia_lista_ejecucion = new ArrayList<Programa>(lista_ejecucion_ddr);
            if(!(this.lista_ejecucion_ddr.isEmpty())){
                for(int k = 0; k<copia_lista_ejecucion.size(); k++){
                    if((copia_lista_ejecucion.get(k).get_ciclos_restantes())<=1){
                        this.lista_ejecucion_ddr.remove(copia_lista_ejecucion.get(k));
                        for(int i = 0; i<this.bloques_memoria_ddr.size(); i++){
                            if((bloques_memoria_ddr.get(i)).equals(copia_lista_ejecucion.get(k).get_nombre_programa())){
                                this.bloques_memoria_ddr.set(i, "Libre");
                            }
                        }
                        actualizar_espacios_ddr();
                    }
                }
            }
            
            //Actualizar cilos de reloj restantes para los programas en la lista de ejecucion.
            if(!(this.lista_ejecucion_ddr.isEmpty())){
                for(int j = 0; j<lista_ejecucion_ddr.size(); j++){
                    lista_ejecucion_ddr.get(j).actualizar_ciclos_restantes();
                }
            }

            //Actualizar y poner en prioridad los programas que el usuario desea.
            //Colocar los programas que el usuario desea primero y despues los de la cola.
            ArrayList<Programa> cola_actualizada;
            //Actualizar cola con los programas que se desean ejecutar por parte del usuario.
            if(!(programas_a_ejecutar==null)){
                cola_actualizada = actualizar_cola_ddr(programas_a_ejecutar);
            }
            //la lista actualizada sera la misma cola, si el usuario no desea ingresar ningun programa.
            else{
                cola_actualizada = new ArrayList<Programa>(this.lista_cola_ddr);
            }

            //Agrandar o disminuir memoria ddr
            int bloques_cola = contar_bloques_en_cola(cola_actualizada);
            incrementar_o_disminuir_memoria(bloques_cola); 

            ArrayList<Programa> contador_cola_actualizada = new ArrayList<Programa>(cola_actualizada);
            actualizar_espacios_ddr();
            for(int i = 0; i<contador_cola_actualizada.size(); i++){
                if(this.espacios_libres_ddr>=(contador_cola_actualizada.get(i).get_bloques())){
                    this.lista_ejecucion_ddr.add(contador_cola_actualizada.get(i));
                    int bloques_llenados = 0;
                    for(int u = 0; u<this.bloques_memoria_ddr.size(); u++){
                        if((this.bloques_memoria_ddr.get(u).equals("Libre"))&&(bloques_llenados<contador_cola_actualizada.get(i).get_bloques())){
                            this.bloques_memoria_ddr.set(u, contador_cola_actualizada.get(i).get_nombre_programa());
                            bloques_llenados += 1;
                        }
                    }
                    cola_actualizada.remove(contador_cola_actualizada.get(i));
                }
                actualizar_espacios_ddr();
            }
            this.lista_cola_ddr = cola_actualizada; 
        }
        catch(Exception e){
            String s = "DDR: ciclo_reloj: "+e.getMessage();
            throw new RuntimeException(s);
        }
    }

    /**
     * convierte un Array de indices a un Array de objetos Programa.
     * utiliza el metodo obtener_programa() de BibliotecaProgramas para llevar a cabo la conversion.
     * Se utiliza en el ciclo de reloj de sdr.
     * 
     * @author José Daniel Gómez Cabrera
     * @version convertir_indices_a_programas_ddr 1.1
     * @return Programa[]
     * @param int[] (indices de los programas)
     */
    private Programa[] convertir_indices_a_programas_ddr(int[] indices){
        try{
            if(indices[0]==0){
                Programa[] programa_vacio = null;
                return programa_vacio;
            }
            else{
                BibliotecaProgramas biblioteca1 = new BibliotecaProgramas();
                Programa[] programas_a_ejecutar = new Programa[indices.length];
                for(int k = 0; k<programas_a_ejecutar.length; k++){
                    programas_a_ejecutar[k] = biblioteca1.obtener_Programa(indices[k]);
                }
                return programas_a_ejecutar;
            }
        }
        catch(Exception e){
            String s = "convertir_indices_a_programas_ddr: "+e.getMessage();
            throw new RuntimeException(s);
        }
    }

    /**
     * Actualiza los espacios libres u ocupados de la memoria para llevar un control.
     * Se utiliza en el ciclo de reloj para poder hacer operaciones logicas facilmente.
     * Simplemente hace un barrido con contadores para saber los espacios disponibles u ocupados en la memoria.
     * Se utiliza en el ciclo de reloj de sdr.
     * 
     * @author José Daniel Gómez Cabrera
     * @version actualizar_espacios_ddr 1.1
     */
    private void actualizar_espacios_ddr(){
        int contador_espacios_libres = 0;
        int contador_espacios_ocupados = 0;
        for(int k = 0; k<this.bloques_memoria_ddr.size(); k++){
            if(this.bloques_memoria_ddr.get(k).equals("Libre")){
                contador_espacios_libres += 1;
            }
            else{
                contador_espacios_ocupados += 1;
            }
        }
        this.espacios_libres_ddr = contador_espacios_libres;
        this.espacios_ocupados_ddr = contador_espacios_ocupados;
    }

    /**
     * Actualiza la cola de ejecucion con los programas que el usuario desee ejecutar.
     * prioriza los programas que el usuario desee a los programas que ya estaban en la cola de ejecucion.
     * Se utiliza en el ciclo de reloj de sdr.
     * 
     * @author José Daniel Gómez Cabrera
     * @version actualizar_cola_ddr 1.1
     * @return ArrayList<Programa>
     * @param Programa[] (programas a ejecutar)
     */
    private ArrayList<Programa> actualizar_cola_ddr(Programa[] programas_a_ejecutar){
        try{
            ArrayList<Programa> cola_actualizada = new ArrayList<Programa>();
            for(int k = 0; k<programas_a_ejecutar.length; k++){
                cola_actualizada.add(programas_a_ejecutar[k]);
            }
            for(int k = 0; k<this.lista_cola_ddr.size(); k++){
                cola_actualizada.add(this.lista_cola_ddr.get(k));
            }
            return cola_actualizada;
        }
        catch(Exception e){
            String s = "actualizar_cola_ddr: "+e.getMessage();
            throw new RuntimeException(s);
        }
    }

    /**
     * For para contar los numeros de bloques de los programas en cola.
     * Se utiliza para determinar cuanta memoria se va a utilizar, y si necesita crecer o disminuir de tamano.
     * 
     * @author José Daniel Gómez Cabrera
     * @version contar_bloques_en_cola 1.1
     * @return int (numero de bloques de los programas en cola)
     */
    private int contar_bloques_en_cola(ArrayList<Programa> lista_actualizada){
        int bloques_en_cola = 0;
        for(int k = 0; k<(lista_actualizada.size()); k++){
            for(int i = 0; i<(lista_actualizada.get(k).get_bloques()); i++){
                bloques_en_cola += 1;
            }
        }
        return bloques_en_cola;
    }

    /**
     * Evalua el numero de bloques requeridos para aumentar o disminuir el tamano de la memoria,
     * utiliza el numero de bloques utilizados por los programas acutales,
     * y el numero de bloques de los programas en cola para determinar cuanta capacidad de memoria se necesita.
     * 
     * @author José Daniel Gómez Cabrera
     * @version incrementar_o_decrementar_memoria 1.1
     * @param int (numero de bloques de los programas a ejecutar)
     */
    private void incrementar_o_disminuir_memoria(int bloques_en_cola){
        actualizar_espacios_ddr();
        int bloques_necesarios = (this.espacios_ocupados_ddr)+(bloques_en_cola);
        //Convertir a memoria de 4GB (64 bloques)
        if((bloques_necesarios>0)&&(bloques_necesarios<=64)){
            convertir_memoria(64);
            this.tamano_memoria_ddr = "4GB (64 Bloques)";
        }
        //Convertir a memoria de 8GB (128 bloques)
        else if((bloques_necesarios>64)&&(bloques_necesarios<=128)){
            convertir_memoria(128);
            this.tamano_memoria_ddr = "8GB (128 Bloques)";
        }
        //Convertir a memoria de 16GB (256 bloques)
        else if((bloques_necesarios>128)&&(bloques_necesarios<=256)){
            convertir_memoria(256);
            this.tamano_memoria_ddr = "16GB (256 Bloques)";
        }
        //Convertir a memoria de 32GB (512 bloques)
        else if(bloques_necesarios>256){
            convertir_memoria(512);
            this.tamano_memoria_ddr = "32GB (512 Bloques)";
        }
        actualizar_espacios_ddr();
    }

    /**
     * Aumenta o disminuye el tamano de la memoria segun el tamano de bloques deseado,
     * utiliza el numero de bloques utilizados por los programas acutales,
     * el numero de espacios libres los determina gracias al numero de bloques que se le asignen.
     * 
     * @author José Daniel Gómez Cabrera
     * @version convertir_memoria 1.1
     * @param int (numero de bloques de memoria deseados)
     */
    private void convertir_memoria(int cantidad_bloques){
        this.bloques_memoria_ddr.clear();
        int bloques_memoria_llenados = 0;
        int bloques_libres_faltantes = 0;
        for(int i = 0; i<30; i++){
            this.bloques_memoria_ddr.add("Sistema Operativo");
            bloques_memoria_llenados += 1;
        }
        for(int k = 0; k<this.lista_ejecucion_ddr.size(); k++){
            // int bloques_llenados = 0;
            for(int u = 0; u<(this.lista_ejecucion_ddr.get(k).get_bloques()); u++){
                this.bloques_memoria_ddr.add(this.lista_ejecucion_ddr.get(k).get_nombre_programa());
                bloques_memoria_llenados += 1;
            }
        }
        bloques_libres_faltantes = ((cantidad_bloques) - (bloques_memoria_llenados));
        for(int n = 0; n<bloques_libres_faltantes; n++){
            this.bloques_memoria_ddr.add("Libre");
        }
    }

    /**
     * Getter de la memoria ddr.
     * 
     * @author José Daniel Gómez Cabrera
     * @version get_memoria_ddr 1.1
     * @return ArrayList<String> (memoria)
     */
    public ArrayList<String> get_memoria_ddr(){
        return this.bloques_memoria_ddr;
    }

    /**
     * Getter de la lista de ejecucion de la ddr.
     * 
     * @author José Daniel Gómez Cabrera
     * @version get_lista_ejecucion_ddr 1.1
     * @return ArrayList<Programa>
     */
    public ArrayList<Programa> get_lista_ejecucion_ddr(){
        return this.lista_ejecucion_ddr;
    }

    /**
     * Getter de la lista de cola de la ddr.
     * 
     * @author José Daniel Gómez Cabrera
     * @version get_lista_cola_ddr 1.1
     * @return ArrayList<Programa>
     */
    public ArrayList<Programa> get_lista_cola_ddr(){
        return this.lista_cola_ddr;
    }

    /**
     * Getter de la espacios libres de la ddr.
     * 
     * @author José Daniel Gómez Cabrera
     * @version get_espacios_libres_ddr 1.1
     * @return int
     */
    public int get_espacios_libres_ddr(){
        return this.espacios_libres_ddr;
    }

    /**
     * Getter de la espacios ocupados de la ddr.
     * 
     * @author José Daniel Gómez Cabrera
     * @version get_espacios_ocupados_ddr 1.1
     * @return int
     */
    public int get_espacios_ocupados_ddr(){
        return this.espacios_ocupados_ddr;
    }

    /**
     * Getter del tamano de memoria de la ddr.
     * 
     * @author José Daniel Gómez Cabrera
     * @version get_tamano_ddr 1.1
     * @return String
     */
    public String get_tamano_ddr(){
        return this.tamano_memoria_ddr;
    }

}
