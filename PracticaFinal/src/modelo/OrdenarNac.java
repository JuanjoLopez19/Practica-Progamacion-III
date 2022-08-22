/*
 * Práctica final de la asignatura de Programación III
 * Autor: Juan José López Gómez
 * DNI: 
 * Si la práctica no se ejecuta de forma correcta puede estar debido a los archivos de la carpeta"LigFemBal", adjunto con los que he estado trabajando sin supuestas erratas
 */
package modelo;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Juan José López Gómez
 */
public class OrdenarNac implements Comparator<Jugadora>, Serializable
{

    @Override
    public int compare(Jugadora o1, Jugadora o2) 
    {
        String []d1=o1.getFecha().split("/");
        d1[2]=d1[2].substring(0,4);
        String []d2=o2.getFecha().split("/");
        d2[2]=d2[2].substring(0,4);
        if(Integer.parseInt(d1[2])>Integer.parseInt(d2[2]))
            return -1;
        else if(Integer.parseInt(d1[2])<Integer.parseInt(d2[2]))
            return 1;
        else
        {
            if(Integer.parseInt(d1[1])>Integer.parseInt(d2[1]))
                return -1;
            else if(Integer.parseInt(d1[1])<Integer.parseInt(d2[1]))
                return 1;
            else
            {
                if(Integer.parseInt(d1[0])>Integer.parseInt(d2[0]))
                    return -1;
                else if(Integer.parseInt(d1[0])<Integer.parseInt(d2[0]))
                    return 1;
                else
                    return 0;
            }
        }
    }

    
    
    
}
