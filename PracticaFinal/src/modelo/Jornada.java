/*
 * Práctica final de la asignatura de Programación III
 * Autor: Juan José López Gómez
 * DNI: 
 * Si la práctica no se ejecuta de forma correcta puede estar debido a los archivos de la carpeta"LigFemBal", adjunto con los que he estado trabajando sin supuestas erratas
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;


/**
 *
 * @author Juan José López Gómez 
 */
public class Jornada implements Serializable
{
    private int n_jornada;
    private String fecha;
    private ArrayList<Partido>Partidos=new ArrayList();
    private ArrayList<Datos_equipo>Clasificacion=new ArrayList();
    private final int N_PARTIDOS=8;
    private final int N_DATOS=16;

    public Jornada() 
    {
        this.n_jornada=-1;
        this.fecha="XX/XX/XXXX";
        Partidos=new ArrayList();
        Clasificacion=new ArrayList();
    }

    Jornada(String N_J, String Fecha, ArrayList<Partido> p) 
    {
        this.n_jornada=Integer.parseInt(N_J);
        this.fecha=Fecha;
        this.Partidos=p;
    }
    
    
    public int getN_Part()
    {
        return this.N_PARTIDOS;
    }
    
    public int getN_jornada() {
        return n_jornada;
    }

    public void setN_jornada(int n_jornada) {
        this.n_jornada = n_jornada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public ArrayList <Datos_equipo> getDEP()
    {
        return this.Clasificacion;
    }
     public ArrayList<Partido> getPartidos() {
        return Partidos;
    }

    public void setPartidos(ArrayList<Partido> Partidos) {
        this.Partidos = Partidos;
    }
    
    public int getND()
    {
        return this.N_DATOS;
    }
    boolean datos(String[][] res) 
    {
        int contador=0;
        for(Partido p : Partidos)
        {
            if(p.getNombre_Local().equals(res[contador][0])&&p.getNombre_Visitante().equals(res[contador][1]))
                {
                    p.setPuntos_Local(Integer.parseInt(res[contador][2]));
                    
                    p.setPuntos_Visitante(Integer.parseInt(res[contador][3]));
                    
                    contador++;
                }
            else
                {
                    return false;
                }
        }
        
        for(String[] s : res)
            {
                Datos_equipo d1= Datos_equipo.factoryL(s);
                        
                Datos_equipo d2= Datos_equipo.factoryV(s);
                        
                if(d1!=null && d2!=null)
                    {
                        this.Clasificacion.add(d1);
                                
                        this.Clasificacion.add(d2);
                    }
                else
                    {
                        return false;
                    }
            }
        
        return true;
    }

    boolean modFH(String nombre, String fecha, String hora) 
    {
        for(Partido p: Partidos)
        {
            if(p.getNombre_Local().equals(nombre)||p.getNombre_Visitante().equals(nombre))
            {
                if(!fecha.isEmpty())
                {
                    p.setFecha(fecha);
                }
                if(!hora.isEmpty())
                {
                    p.setHora(hora);
                }
                return true;
            }
        }
        return false;
    }

    String[] showClas() 
    {
        this.Clasificacion.sort(Comparator.comparing(Datos_equipo::getPCL).thenComparing(Datos_equipo::getPF));
        String []s=new String[this.Clasificacion.size()];
        int i=this.Clasificacion.size()-1;
        for(Datos_equipo dp : Clasificacion)
        {
            s[i]=dp.toRow();
            i--;
        }
       return s;
    }
    
    
}
