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
public class Nacimiento implements Serializable
{
    private String fecha;
    private String ciudad;
    private String provincia;

    public Nacimiento() 
    {
        this.fecha="XX/XX/XXXX";
        this.ciudad="Desconodido";
        this.provincia="Desconocido";
    }

    Nacimiento(String fecha, String ciudad, String provincia) 
    {
        this.fecha=fecha;
        this.ciudad=ciudad;
        this.provincia=provincia;
    }
    
    static Nacimiento factory(String nacionalidad) 
    {
        Nacimiento nac;
        if(nacionalidad.endsWith(")"))
        {
            String fecha=nacionalidad.substring(0,11);
            String []prueba=nacionalidad.split("\\(");
            String p=prueba[0].substring(11,prueba[0].length()-1);
            String [] prueba2=p.split(",");
            prueba[1]=prueba[1].substring(0, prueba[1].length()-1);
             nac = new Nacimiento(fecha,prueba2[0],prueba[1]);
            return nac;
        }
        else
        {
            String[] prueba=nacionalidad.split(" ");
            if(prueba.length==1)
               nac= new Nacimiento(nacionalidad,"-","-");
            else
            {
                nac = new Nacimiento(prueba[0],prueba[1],"-");
            }
            return nac;       
        }
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    
}
