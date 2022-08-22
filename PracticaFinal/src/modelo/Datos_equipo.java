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
 class Datos_equipo implements Serializable
{
    private String nombre;
    private int n_PJ; 
    private int n_PG; 
    private int n_PP; 
    private int PF; 
    private int PC; 
    private int PCL;

    public Datos_equipo() 
    {
        this.nombre="Desconocido";
        this.n_PJ=0;
        this.n_PG=0;
        this.n_PP=0;
        this.PF=0;
        this.PC=0;
        this.PCL=0;
    }

    public Datos_equipo(String nombre, int n_PJ, int n_PG, int n_PP, int PF, int PC, int PCL) {
        this.nombre = nombre; this.n_PJ = n_PJ; this.n_PG = n_PG; this.n_PP = n_PP; this.PF = PF; this.PC = PC; this.PCL = PCL;
    }

     static Datos_equipo factoryL(String[] res) 
    {
        int pg;
        int pp;
        String nombre=res[0];
        int pj=1;
        
        if(Integer.parseInt(res[2])>Integer.parseInt(res[3]))
        {
            pg=1;
            pp=0;
        }
        else
        {
             pg=0;
             pp=1;
            
        }
        int Pf=Integer.parseInt(res[2]);
        int Pc=Integer.parseInt(res[3]);
        int Pcl=2*pg+pp;
        
        Datos_equipo e= new Datos_equipo(nombre,pj,pg,pp,Pf,Pc,Pcl);
        return e;
    }

    static Datos_equipo factoryV(String[] res) 
    {
        int pg;
        int pp;
        String nombre=res[1];
        int pj=1;
        if(Integer.parseInt(res[3])>Integer.parseInt(res[2]))
        {
             pg=1;
             pp=0;
        }
        else
        {
             pg=0;
             pp=1;
            
        }
        int Pf=Integer.parseInt(res[3]);
        int Pc=Integer.parseInt(res[2]);
        int Pcl=2*pg+pp;
        
        Datos_equipo e= new Datos_equipo(nombre,pj,pg,pp,Pf,Pc,Pcl);
        return e;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getN_PJ() {
        return n_PJ;
    }

    public void setN_PJ(int n_PJ) {
        this.n_PJ = n_PJ;
    }

    public int getN_PG() {
        return n_PG;
    }

    public void setN_PG(int n_PG) {
        this.n_PG = n_PG;
    }

    public int getN_PP() {
        return n_PP;
    }

    public void setN_PP(int n_PP) {
        this.n_PP = n_PP;
    }

    public int getPF() {
        return PF;
    }

    public void setPF(int PF) {
        this.PF = PF;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public int getPCL() {
        return PCL;
    }

    public void setPCL(int PCL) {
        this.PCL = PCL;
    }

    String toRow() 
    {
        String s=String.format("%50s\t%4s\t%4s\t%4s\t%4s\t%4s\t%4s\t",this.nombre,this.n_PJ,this.n_PG,this.n_PP,this.PF,this.PC,this.PCL);
        return s;
    }

    String toHTMLRow(int num) 
    {
        String resultado;
        resultado = String.format("<TR>"
                + "<TD>%d</TD>"
                + "<TD>%s</TD>"
                + "<TD>%d</TD>"
                + "<TD>%d</TD>"
                + "<TD>%d</TD>"
                + "<TD>%d</TD>"
                + "<TD>%d</TD>"
                + "<TD>%d</TD>"
                + "</TR>",
                num,
                this.nombre,
                this.n_PJ,
                this.n_PG,
                this.n_PP,
                this.PF,
                this.PC,
                this.PCL);
        
        return resultado;
    }   
}
