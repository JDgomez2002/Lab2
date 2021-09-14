//Universidad del Valle de Guatemala
//Programación Orientada a Objetos
//Catedrático Tomás Gálvez
//Segundo Semestre 2021
//José Daniel Gómez Cabrera
//Carné 21429
//Sección 11
//Actividad: Laboratorio 2

import java.util.Scanner;
import java.io.File;

/**
 * Clase BibliotecaProgramas.
 * 
 * @author José Daniel Gómez Cabrera
 * @version BibliotecaProgramas Class 1.1
 */
public class BibliotecaProgramas {
    Programa[] programas_disponibles;

    /**
     * Constructor de BibliotecaProgramas.
     * 
     * @author José Daniel Gómez Cabrera
     * @version BibliotecaProgramas 1.1
     */
    public BibliotecaProgramas(){
        try{
            this.programas_disponibles = file_reader();
        }
        catch(Exception e){
            String s = "BibliotecaProgramas: Constructor: "+e.getMessage();
            throw new RuntimeException(s);
        }
    }

    /**
     * Lector de archivo programas.txt.
     * 
     * @author José Daniel Gómez Cabrera
     * @version file_reader 1.1
     * @return Programa[]
     */
    private static Programa[] file_reader(){
        try{
            File programas_txt = new File("programas.txt");
            Scanner scan1 = new Scanner(programas_txt);
            String programas = "";
            while(scan1.hasNextLine()){
                programas = programas.concat(scan1.nextLine()+":");
            }
            String[] programas_separados = programas.split(":");
            Programa[] array_programas = new Programa[programas_separados.length];
            for(int k = 0; k<array_programas.length; k++){
                String[] info_programa = programas_separados[k].split(",");
                Programa mi_programa = new Programa(info_programa[0], Integer.parseInt(info_programa[1]), Integer.parseInt(info_programa[2]));
                array_programas[k] = mi_programa;
            }
            return array_programas;
        }
        catch(Exception e){
            String s = "file_reader: "+e.getMessage();
            throw new RuntimeException(s);
        }
    }

    /**
     * Metodo para ingresar un numero, y regresar un objeto programa de la lista.
     * 
     * @author José Daniel Gómez Cabrera
     * @version obtener_Programa 1.1
     * @return Programa
     * @param int (indice de programa deseado)
     */
    public Programa obtener_Programa(int no_programa){  
        Programa mi_programa = this.programas_disponibles[6];
        String programa_buscado = "";
        switch (no_programa) {
            case 1:
                programa_buscado = "GOOGLE CHROME";
                break;
            
            case 2:
            programa_buscado = "FIREFOX";
                break;

            case 3:
            programa_buscado = "ZOOM";
                break;
            
            case 4:
            programa_buscado = "WHATSAPP";
                break;

            case 5:
            programa_buscado = "TELEGRAM";
                break;
            
            case 6:
            programa_buscado = "VISUAL STUDIO";
                break;

            case 7:
            programa_buscado = "MAIL";
                break;

            case 8:
            programa_buscado = "CONTACTOS";
                break;
      
            default:
            programa_buscado = "WORD";
                break;
        }

        for(int i =0; i<this.programas_disponibles.length; i++){
            if(programas_disponibles[i].get_nombre_programa().equals(programa_buscado)){
                mi_programa = programas_disponibles[i];
            }
        }
        return mi_programa;
    }

    // /**
    //  * getter de Programa[].
    //  * 
    //  * @author José Daniel Gómez Cabrera
    //  * @version get_programas 1.1
    //  * @return Programa[]
    //  */
    // public Programa[] get_programas(){
    //     return this.programas_disponibles;
    // }
}
