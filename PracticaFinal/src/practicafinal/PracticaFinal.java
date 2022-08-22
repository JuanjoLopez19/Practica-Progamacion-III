/*
 * Práctica final de la asignatura de Programación III
 * Autor: Juan José López Gómez
 * DNI: 
 * Si la práctica no se ejecuta de forma correcta puede estar debido a los archivos de la carpeta"LigFemBal", adjunto con los que he estado trabajando sin supuestas erratas
*/
package practicafinal;


import vista.Vista;

/**
 *
 * @author Juan José López Gómez
 */
public class PracticaFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Vista v= new Vista();
        System.out.println("Bienvenido al programa de Gestión de una Temporada de la Liga de Baloncesto Femenina");
        System.out.println("Se va a comprobar si hay datos almacenados previamente de la liga");
        v.comprobar();
        v.runmenu("%n 1.-Gestión de temporada"
                + "%n 2.-Gestión de jugadoras"
                + "%n 3.-Gestión de jornada"
                + "%n 4.-Visualizar resultados"
                + "%n 5.-Almacenamiento de datos"
                + "%n q.-Salida y almacenaje en binario"
                + "%n Introduce una opción: ");
    }
    
}
