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
class Equipo implements Serializable
{

    private String nombre;
    private String direccion;
    private String telefono;
    private String web;
    private String email;
    final private ArrayList<Jugadora>jugadoras;

    public Equipo() 
    {
        this.nombre="Desconocido";
        this.direccion="Desconocida";
        this.telefono="12345678";
        this.email="Desconocido";
        this.web="Desconocido";
        this.jugadoras= new ArrayList();
    }

    static Equipo factory(String [] tokens)
    {
        if(tokens.length!=5)
        {
            return null;
        }
        else
        {
            Equipo e = new Equipo(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4]);
            return e;
        }
    }

    private Equipo(String nombre, String dir, String telefono, String web, String email) 
    {
      this.nombre=nombre.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
      this.direccion=dir;
      this.telefono=telefono;
      this.web=web;
      this.email=email;
      this.jugadoras=new ArrayList();
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    
    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    boolean setJugadora(String[] s) 
    {
        Jugadora j = Jugadora.factory(s);
        if(j!=null)
        {
            jugadoras.add(j);
            return true;
        }
        else
        {
            return false;
        }    
    }

    ArrayList <Jugadora>getJ() 
    {
        return this.jugadoras;
    }
    
    boolean modJ(String nombre_J, String[] opciones) 
    {
        for(Jugadora J : jugadoras)
        {
            if(J.getNombre().equals(nombre_J))
            {
                if(!opciones[0].isEmpty())
                {
                    J.setPosicion(opciones[0]);
                }
                if(!opciones[1].isEmpty())
                {
                    J.setDorsal(opciones[1]);
                }
                if(!opciones[2].isEmpty())
                {
                    J.setFecha(opciones[2]);
                }
                if(!opciones[3].isEmpty())
                {
                    J.setCiudad(opciones[3]);
                }
                if(!opciones[4].isEmpty())
                {
                    J.setPro(opciones[4]);
                }
                if(!opciones[5].isEmpty())
                {
                    J.setNacionalidad(opciones[5]);
                }
                if(!opciones[6].isEmpty())
                {
                    J.setAltura(opciones[6]);
                }
                return true;
            }
        }
        return false;
    }

    boolean delPlayer(String nombre_J) 
    {
        for(Jugadora J : jugadoras)
        {
            if(J.getNombre().equals(nombre_J))
            {
                return jugadoras.remove(J);
            }
        }
        return false;
    }

    boolean addJ(String nombre_J, String[] opciones) 
    {
        Jugadora j = Jugadora.factory(nombre_J,opciones);
        if(j!=null)
        {
            jugadoras.add(j);
            return true;
        }
        else
        {
            return false;
        }
    }

    String[][] showPlayers() 
    {
        String[][] tmp= new String [jugadoras.size()][];
        this.jugadoras.sort(Comparator.comparing(Jugadora::getPosicion).thenComparing(Jugadora::getAltura));
        for(int i=0;i<jugadoras.size();i++)
        {
            tmp[i]=jugadoras.get(i).toRow();
        }
        return tmp;
    }

    String tolinea() 
    {
        String linea=String.format("%40s\t%12s\t%50s\t%30s%n",
                this.nombre,
                this.telefono,
                this.web,
                this.email);
        return linea;
    }
    
    String[] toRow() 
    {
        String [] equipo={"Nombre: "+this.nombre,
            "Dirección: "+this.direccion,
            "Telefono: "+this.telefono,
            "Email: "+this.email,
            "Página Web: "+this.web};
        return equipo;
    }
}
