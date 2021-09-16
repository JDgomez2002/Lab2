//Universidad del Valle de Guatemala
//Programación Orientada a Objetos
//Catedrático Tomás Gálvez
//Segundo Semestre 2021
//José Daniel Gómez Cabrera
//Carné 21429
//Sección 11
//Actividad: Laboratorio 2

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;

/**
 * Clase Vista.
 * 
 * @author José Daniel Gómez Cabrera
 * @version Vista Class 1.1
 */
public class Vista {
    private Scanner scan1 = new Scanner(System.in);

    /**
     * Bienvenida para el usuario.
     * 
     * @author José Daniel Gómez Cabrera
     * @version bienvenida 1.1
     */
    public void bienvenida(){
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("- BIENVENIDO");
        System.out.println("- Hora: "+LocalTime.now().getHour()+" horas con "+LocalTime.now().getMinute()+" minutos.");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("- Este es el Simulador de memoria RAM.");
        System.out.println("- Este programa le permitirá simular el tipo de memoria RAM que desee.");
        System.out.println("- Puede elegir entre SDR o DDR.");
        System.out.println("- Por favor siga las instrucciones del programa.");
        System.out.println("- Cuando ingrese datos, tendrá que colocar datos validos para avanzar.");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("- QUE COMIENCE LA SIMULACION.");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Obtiene del usuario el tipo de memoria RAM que se utilizara.
     * 
     * @author José Daniel Gómez Cabrera
     * @version solicitar_tipo_memoria 1.1
     * @return int
     */
    public int solicitar_tipo_memoria(){
        boolean continuar = true;
        int tipo_memoria = 0;
        while(continuar){
            try{
                System.out.println("- Ingrese 1 para SDR.");
                System.out.println("- Ingrese 2 para DDR.");
                System.out.println();
                System.out.print("\tIngrese su desicion: ");
                tipo_memoria = scan1.nextInt(3);
                if((tipo_memoria>0)&&(tipo_memoria<3)){
                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
                    System.out.println("- EUREKA");
                    if(tipo_memoria == 1){
                        System.out.println("- Ha escogido una memoria RAM de tipo SDR.");
                    }
                    else{
                        System.out.println("- Ha escogido una memoria RAM de tipo DDR.");
                    }
                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
                    continuar = false;
                }
                else{
                System.out.println();
                System.out.println("\t\tERROR: debe ingresar 1 o 2.");
                System.out.println();
                }
                
            }
            catch(Exception e){
                System.out.println();
                System.out.println("\t\tERROR: debe ingresar 1 o 2.");
                System.out.println();
                scan1.next();
            }
        }
        return tipo_memoria;
    }

    /**
     * Obtiene del usuario un numero entero.
     * 
     * @author José Daniel Gómez Cabrera
     * @version opcion_usuario_int 1.1
     * @return int
     */
    public int opcion_usuario_int(){
        int opcion = 0;
        boolean continuar = true;
        while(continuar){
            try{
                opcion = scan1.nextInt();
                continuar = false;
            }
            catch(Exception e){
                System.out.println("\t\t- Error: debe ingresar un numero entero.");
                scan1.next();
            }
        }
        return opcion;
    }

    /**
     * bienvenida para el usuario si desea una memoria SDR.
     * 
     * @author José Daniel Gómez Cabrera
     * @version bienvenida_sdr 1.1
     */
    public void bienvenida_sdr(){
        System.out.println("- Bienvenido a la memoria SDR.");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("- Este es un tipo de memoria de estático.");
        System.out.println("- Su tamano es de 16GB (256 Bloques).");
        System.out.println("- Como en una computadora normal, el sistema operativo ocupa cierto espacio.");
        System.out.println("- Pero no te preocupes, es solamente el 12 por ciento (30 bloques).");
        System.out.println("- Para comenzar, elegiremos los programas que deseas ingresar con la RAM.");
        System.out.println("- Si no deseas programas al iniciar la memoria puedes ingresar 0.");
        System.out.println(" NOTA: Para mejorar la dinamica del programa, toda la informacion solicitada en el");
        System.out.println(" instructivo del laboratorio 2 es mostrada a traves de una sola opcion del programa.");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Mensaje para notificar al usuario que la SDR se creo exitosamente.
     * 
     * @author José Daniel Gómez Cabrera
     * @version sdr_creada_exitosamente 1.1
     */
    public void sdr_creada_exitosamente(){
        System.out.println();
        System.out.println("- Su memoria SDR fue creada exitosamente.");
        System.out.println("- Ahora accedera al menu, en el cual podra mostrar la RAM completa u obtener informacoin de la misma.");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Mensaje para notificar al usuario que la SDR se creo exitosamente.
     * 
     * @author José Daniel Gómez Cabrera
     * @version sdr_creada_exitosamente 1.1
     */
    public void ciclo_sdr_exitoso(){
        System.out.println();
        System.out.println("- El ciclo de reloj para su memoria SDR fue realizado exitosamente.");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Menu para la memoria SDR.
     * 
     * @author José Daniel Gómez Cabrera
     * @version menu_sdr 1.1
     */
    public void menu_sdr(){
        System.out.println("- MENU PRINCIPAL SDR");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("1. Ciclo de Reloj.");
        System.out.println("2. Mostrar Memoria SDR.");
        System.out.println("3. Mostrar Informacion de la Memoria SDR.");
        System.out.println("4. Finalizar programa.");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * solicita opcion del menu de sdr.
     * 
     * @author José Daniel Gómez Cabrera
     * @version solicitar_opcion_menu_sdr 1.1
     * @return int
     */
    public int solicitar_opcion_menu_sdr(){
        boolean continuar = true;
        int opcion = 0;
        while(continuar){
            try{
                System.out.print("\tIngrese su desicion: ");
                opcion = scan1.nextInt();
                if((opcion>0)&&(opcion<5)){
                    System.out.println();
                    continuar = false;
                }
                else{
                System.out.println();
                System.out.println("\t\tERROR: debe ingresar 1, 2, 3 o 4.");
                System.out.println();
                }
                
            }
            catch(Exception e){
                System.out.println();
                System.out.println("\t\tERROR: debe ingresar 1, 2, 3 o 4.");
                System.out.println();
                scan1.next();
            }
        }
        return opcion;
    }

    /**
     * Solicita al usuario los programas para ejecutar en el siguiente ciclo de reloj.
     * solicita al usuario el numero de programas que desea ejecutar en la RAM y los indices de cada programa.
     * Si no desea ejecutar programas, el usuario puede escoger 0 y el programa correra sin programas nuevos.
     * 
     * @author José Daniel Gómez Cabrera
     * @version solicitar_programas_para_ciclo_reloj 1.1
     * @return int[] (indices de los programas)
     */
    public int[] solicitar_programas_para_ciclo_reloj(){
        System.out.println();
        System.out.println("Ingresa los programas que deseas ejecutar en el siguiente ciclo de reloj:");
        System.out.println();
        int[] programas_siguiente_ciclo = solicitar_programas_usuario();
        return programas_siguiente_ciclo;
    }

    /**
     * solicita al usuario el numero de programas que desea ejecutar en la RAM y los indices de cada programa.
     * Si no desea ejecutar programas, el usuario puede escoger 0 y el programa correra sin programas nuevos.
     * 
     * @author José Daniel Gómez Cabrera
     * @version solicitar_programas_usuario 1.1
     * @return int[] (indices de los programas)
     */
    public int[] solicitar_programas_usuario(){
        BibliotecaProgramas biblioteca = new BibliotecaProgramas();
        Programa[] programas = biblioteca.get_programas();
        System.out.println();
        System.out.println("- Los programas disponibles son: ");
        System.out.println();
        for(int k = 0; k<programas.length; k++){
            System.out.print(k+1);
            System.out.print(". ");
            System.out.print(programas[k].get_nombre_programa());
            System.out.print(", ");
            System.out.print(programas[k].get_megabytes());
            System.out.print(" MG, ");
            System.out.print(programas[k].get_ciclos_restantes());
            System.out.println(" Ciclos.");
        }
        int longitud;
        System.out.println();
        System.out.print("- Ingresa el numero de programas que deseas ejecutar. (Si no desea ejecutar ninguno, ingresa 0): ");
        longitud = opcion_usuario_int();
        System.out.println();
        int[] indices;
        if(longitud==0){
            int[] indice_cero = {0};
            indices = indice_cero;
        }
        else{
            indices = new int[longitud];
            for(int j = 0; j<longitud; j++){
                int numero_app = 0;
                numero_app = solicitar_numero_app(j);
                indices[j] = numero_app;
            }
        }
        return indices;
    }

    /**
     * solicita al usuario el numero indice de cada programa.
     * se utiliza en el metodo solicitar_programas_usuario()
     * solo se puede ingresar un numero entre 1 y 9.
     * 
     * @author José Daniel Gómez Cabrera
     * @version solicitar_numero_app 1.1
     * @return int (indices de los programas)
     * @param int (contador para darle a conocer el usuario el numero de programa que debe ingresar)
     */
    private int solicitar_numero_app(int j){
        boolean continuar = true;
        int numero_app = 0;
        while(continuar){
            try{
                System.out.print("Ingrese numero de la app "+(j+1)+": ");
                numero_app = scan1.nextInt(10);
                // System.out.println();
                if((numero_app>0)&&(numero_app<10)){
                    continuar = false;
                }
                else{
                System.out.println();
                System.out.println("\t\tERROR: debe ingresar un numero del 1 al 9.");
                System.out.println();
                }
                
            }
            catch(Exception e){
                System.out.println();
                System.out.println("\t\tERROR: debe ingresar un numero del 1 al 9.");
                System.out.println();
                scan1.next();
            }
        }
        return numero_app;
    }

    /**
     * metodo para poder mostrar los bloques de memoria RAM.
     * 
     * @author José Daniel Gómez Cabrera
     * @version mostrar_memoria_sdr 1.1
     * @param String[] (String de bloques de memoria de la sdr)
     */
    public void mostrar_memoria_sdr(String[] bloques_memoria){
        System.out.println();
        System.out.println();
        System.out.println("- MEMORIA SDR (256 bloques)");
        System.out.println();
        for(int k = 0; k<256; k++){
            System.out.println(bloques_memoria[k]);
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    public void mostrar_info_sdr(int bloques_libres, int bloques_ocupados, ArrayList<Programa> lista_ejecucion, ArrayList<Programa> lista_cola){
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("- INFORMACION DE MEMORIA SDR:");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("- Cantidad total de memoria: 16 GB (256 bloques)");
        System.out.print("- Cantidad de Memoria Disponible: ");
        System.out.println(bloques_libres+" Bloques");
        System.out.print("- Cantidad de Memoria en Uso: ");
        System.out.println(bloques_ocupados+" Bloques");
        System.out.print("Programas en Ejecucion: ");
        if(lista_ejecucion.size()==0){
            System.out.print("Sin programas en ejecucion.");
        }
        else{
            for(int k = 0; k<lista_ejecucion.size(); k++){
                System.out.print(lista_ejecucion.get(k).get_nombre_programa());
                if(k<(lista_ejecucion.size()-1)){
                    System.out.print(", ");
                }
            }
        }
        System.out.println();
        System.out.print("Programas en Cola: ");
        if(lista_cola.size()==0){
            System.out.print("Sin programas en cola.");
        }
        else{
            for(int k = 0; k<lista_cola.size(); k++){
                System.out.print(lista_cola.get(k).get_nombre_programa());
                if(k<(lista_cola.size()-1)){
                    System.out.print(", ");
                }
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }


    /**
     * Mensaje de despedida para el ususario
     * 
     * @author José Daniel Gómez Cabrera
     * @version despedida 1.1
     */
    public void despedida(){
        System.out.println();
        System.out.println("- MUCHAS GRACIAS POR UTILIZAR EL SIMULADOR DE MEMORIA RAM.");
        System.out.println("- Vuelve pronto.");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
    }

    /**
     * Mensaje de error para el ususario
     * 
     * @author José Daniel Gómez Cabrera
     * @version mensaje_error 1.1
     */
    public void mensaje_error(){
        System.out.println("\t- Error: por favor escoger una opcion del menu.");
    }
}
