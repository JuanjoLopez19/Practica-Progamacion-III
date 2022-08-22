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
public class Jugadora implements Serializable
{
    private String nombre;
    private String posicion;
    private String dorsal;
    private String nacionalidad;
    private Nacimiento nac;
    private String altura;

    public Jugadora() 
    {
        this.nombre="Desconocido";
        this.posicion="Desconocido";
        this.dorsal="XX";
        this.nacionalidad="Desconocida";
        this.nac=new Nacimiento();
        this.altura="-1";
    }

    private Jugadora(String nombre, String posicion, String dorsal, String nacionalidad, String pais, String talla) 
    {
        this.nombre=nombre.replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
        this.posicion=posicion;
        this.dorsal=dorsal;
        this.nac= Nacimiento.factory(nacionalidad);
        this.nacionalidad=pais;
        this.altura=talla;
    }

    private Jugadora(String nombre_J, String pos, String dorsal, String fecha, String ciudad, String pro, String nacion, String altura) 
    {
        this.nombre=nombre_J;
        this.posicion=pos;
        this.dorsal=dorsal;
        this.nac=new Nacimiento(fecha,ciudad,pro);
        this.nacionalidad=nacion;
        this.altura=altura;
    }
    
    static Jugadora factory(String[] s) 
    {
        if(s.length==6)
        {
            for(int i=0;i<s.length;i++)
            {
                if(s[i].equals("")||s[i].equals("-"))
                {
                    s[i]="Desconocido";
                }
            }
            Jugadora j = new Jugadora(s[0],s[1],s[2],s[3],s[4],s[5]);   
            return j;
        }
        else
        {
           for(int i=0;i<s.length;i++)
            {
                if(s[i].equals("")||s[i].equals("-"))
                {
                    s[i]="Desconocido";
                }
            }
            Jugadora j = new Jugadora(s[0],s[1],s[2],s[3],s[4],"-1");   
            return j;
        }
    }

    static Jugadora factory(String nombre_J, String[] s) 
    {
        for(int i=0;i<s.length;i++)
            {
                if(s[i].equals("")||s[i].equals("-"))
                {
                    s[i]="Desconocido";
                }
            }
        Jugadora J= new Jugadora(nombre_J,s[0],s[1],s[2],s[3],s[4],s[5],s[6]);
        return J;
            
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setFecha(String fecha)
    {
        this.nac.setFecha(fecha);
    }
    public void setCiudad(String ciudad)
    {
        this.nac.setCiudad(ciudad);
    }
    public void setPro(String pro)
    {
        this.nac.setProvincia(pro);
    }
    public String getFecha()
    {
        return this.nac.getFecha();
    }
    public String getdia()
    {
        return this.nac.getFecha().substring(0,2);
    }
    public String getmes()
    {
        return this.nac.getFecha().substring(4,5);
    }
    public String getano()
    {
        return this.nac.getFecha().substring(7,10);
    }
    public String getCiudad()
    {
        return this.nac.getCiudad();
    }
    public String getPro()
    {
        return this.nac.getProvincia();
    }
    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    String[] toRow() 
    {
        String[] jugadora={"Nombre: "+this.nombre,
            "Posición: "+this.posicion,
            "Dorsal: "+this.dorsal,
            "Nacimiento: "+this.nac.getFecha()+" || "+this.nac.getCiudad()+" || "+this.nac.getProvincia(),
            "Nacionalidad: "+this.nacionalidad,
            "Altura: "+this.altura};
            
        return jugadora;
    }

    String[] toRowF()
    {
        String[] jugadora={"Nombre: "+this.nombre,
            "Posición: "+this.posicion,
            "Dorsal: "+this.dorsal,
            "Nacimiento: "+this.nac.getFecha(),
            "Nacionalidad: "+this.nacionalidad,
            "Altura: "+this.altura};
            
        return jugadora;
    }

    String tolinea() 
    {
        String linea=String.format("%40s\t%40s\t%40s\t%40s\t%40s\t%40s\t%40s\t%40s%n",
                this.nombre,
                this.posicion,
                this.dorsal,
                this.getFecha(),
                this.getCiudad(),
                this.getPro(),
                this.nacionalidad,
                this.altura);
        return linea;
    }
    
    
    
}
