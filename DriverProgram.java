//Universidad del Valle de Guatemala
//Programación Orientada a Objetos
//Catedrático Tomás Gálvez
//Segundo Semestre 2021
//José Daniel Gómez Cabrera
//Carné 21429
//Sección 11
//Actividad: Laboratorio 2

/**
 * Controlador del programa.
 * 
 * @author José Daniel Gómez Cabrera
 * @version DriverProgram 1.1
 */
public class DriverProgram{
    public static void main(String[] args){
        Vista interfaz1 = new Vista();
        int tipo_memoria;

        try{
            interfaz1.bienvenida();
            tipo_memoria = interfaz1.solicitar_tipo_memoria();
            switch (tipo_memoria) {
                //SDR
                case 1:
                    //inicializacion de la sdr
                    interfaz1.bienvenida_sdr();
                    int[] programas_iniciales = interfaz1.solicitar_programas_usuario();
                    SDR memoria_sdr = new SDR(programas_iniciales);
                    String[] bloques_sdr = memoria_sdr.get_memoria_sdr();
                    interfaz1.mostrar_memoria_sdr(bloques_sdr);
                    //menu de la sdr

                    break;

                case 2:
                    
                    break;
            
                default:
                    break;
            }
        }
        catch(Exception e){
            String s = "\t\t- Lo sentimos, Ha ocurrido un Error: "+e.getMessage();
            System.out.println(s);
        }
    }
}
