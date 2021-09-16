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
                    interfaz1.sdr_creada_exitosamente();

                    //menu de la sdr
                    boolean continuar_sdr = true;
                    int opcion_usuario_sdr = 0;
                    while(continuar_sdr){
                        interfaz1.menu_sdr();
                        opcion_usuario_sdr = interfaz1.solicitar_opcion_menu_sdr();
                        switch (opcion_usuario_sdr){
                            //Ciclo de reloj
                            case 1:
                                int[] programas_siguiente_ciclo = interfaz1.solicitar_programas_para_ciclo_reloj();
                                memoria_sdr.ciclo_reloj_sdr(programas_siguiente_ciclo);
                                interfaz1.ciclo_sdr_exitoso();
                                break;

                            //Mostrar memoria
                            case 2:
                                interfaz1.mostrar_memoria_sdr(memoria_sdr.get_memoria_sdr());
                                break;

                            //Mostrar informacion de la memoria
                            case 3:
                                interfaz1.mostrar_info_sdr(memoria_sdr.get_espacios_libres(), memoria_sdr.get_espacios_ocupados(), memoria_sdr.get_lista_ejecucion(), memoria_sdr.get_lista_cola());
                                break;
                            
                            //finalizar programa
                            case 4:
                                interfaz1.despedida();
                                continuar_sdr = false;
                                break;

                            default:
                                interfaz1.mensaje_error();
                                break;
                        }
                    }
                    break;

                case 2:
                    
                    break;
            
                default:
                    interfaz1.mensaje_error();
                    break;
            }
        }
        catch(Exception e){
            String s = "\t\t- Lo sentimos, Ha ocurrido un Error: "+e.getMessage();
            System.out.println(s);
        }
    }
}
