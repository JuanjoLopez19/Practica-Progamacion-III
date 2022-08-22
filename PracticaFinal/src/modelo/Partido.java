/*
 * Práctica final de la asignatura de Programación III
 * Autor: Juan José López Gómez
 * DNI: 
 * Si la práctica no se ejecuta de forma correcta puede estar debido a los archivos de la carpeta"LigFemBal", adjunto con los que he estado trabajando sin supuestas erratas
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Juan José López Gómez
 */
public class Partido implements Serializable
{

    private String nombre_Local;
    private String nombre_Visitante;
    private int puntos_Local;
    private int puntos_Visitante;
    private String fecha;
    private String hora;

    public Partido() 
    {
        this.nombre_Local="Desconocido";
        this.nombre_Visitante="Desconocido";
        this.puntos_Local=0;
        this.puntos_Visitante=0;
        this.fecha="XX/XX/XXXX";
        this.hora="XX:XX";
    }

    Partido(String nl, String nv, String fecha , String hora) 
    {
        this.nombre_Local=nl;
        this.nombre_Visitante=nv;
        this.fecha=fecha;
        this.hora=hora;
    }
    
    static Partido factory(String[] D) 
    {
        if(D.length!=4)
        {
            return null;
        }
        else
        {
            Partido P= new Partido(D[0],D[1],D[2],D[3]);
            
            return P;
        }
    }
    public String getNombre_Local() {
        return nombre_Local;
    }

    public void setNombre_Local(String nombre_Local) {
        this.nombre_Local = nombre_Local;
    }

    public String getNombre_Visitante() {
        return nombre_Visitante;
    }

    public void setNombre_Visitante(String nombre_Visitante) {
        this.nombre_Visitante = nombre_Visitante;
    }

    public int getPuntos_Local() {
        return puntos_Local;
    }

    public void setPuntos_Local(int puntos_Local) {
        this.puntos_Local = puntos_Local;
    }

    public int getPuntos_Visitante() {
        return puntos_Visitante;
    }

    public void setPuntos_Visitante(int puntos_Visitante) {
        this.puntos_Visitante = puntos_Visitante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    String[] toState() 
    {
        String [] linea= {this.nombre_Local,this.nombre_Visitante,this.puntos_Local+"",this.puntos_Visitante+""};
        return linea;
    }
    
}
