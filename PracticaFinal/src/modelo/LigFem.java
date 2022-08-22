/*
 * Práctica final de la asignatura de Programación III
 * Autor: Juan José López Gómez
 * DNI: 
 * Si la práctica no se ejecuta de forma correcta puede estar debido a los archivos de la carpeta"LigFemBal", adjunto con los que he estado trabajando sin supuestas erratas
 */
package modelo;

import com.coti.tools.OpMat;
import com.coti.tools.Rutas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan José López Gómez
 */
public class LigFem implements Serializable
{
    private String Temporada;
    private ArrayList<Jornada> Jornadas=new ArrayList();
    private final int N_J=15;
    private ArrayList<Equipo> Equipos=new ArrayList();
    private final int N_E=16;
    
    public LigFem() 
    {
        this.Temporada="";
        this.Jornadas= new ArrayList();
        this.Equipos= new ArrayList();
    }

    public String getTemporada() {
        return Temporada;
    }

    public void setTemporada(String Temporada) {
        this.Temporada = Temporada;
    }
    
    public int getNJ()
    {
        return this.N_J;
    }


    public boolean setEquipos(String[][] equipos) 
    {
        int contador=0;
        if(equipos.length!=this.N_E)
        {
            return false;
        }
        else
        {
            do
            {
                Equipo e=Equipo.factory(equipos[contador]);
                if(e!=null)
                {
                    Equipos.add(e);
                    contador++;
                }
                else
                {
                    return false;
                }
            }while(contador<this.N_E);
            return true;
        }
    }

    public boolean cargarJugadoras() 
    {
        String Escritorio=Rutas.pathToDesktop().toString();
        int contador=0;
        String ruta;
        String nombre;
        String [][] jugadora=null;
        do
        {
            nombre=Equipos.get(contador).getNombre();
            nombre=nombre.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
            nombre+=".txt"; 
            ruta=Escritorio+
                File.separator+"LigFemBal"+
                File.separator+"jugadoras"+
                File.separator+nombre;
            File f= new File(ruta);
            if(f.exists())
            {
                try {
                    jugadora=OpMat.importFromDisk(f, "\t");
                } catch (Exception ex) {
                    Logger.getLogger(LigFem.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(jugadora!=null)
                {
                    for(String []s :jugadora)
                    {
                        if(!Equipos.get(contador).setJugadora(s))
                        {
                            return false;
                        }
                    }
                    contador++;
                }
                else
                {
                    return false;
                }             
            }
            else
            {
                return false;
            }
        }while(contador<Equipos.size());
        return true;
    }
    
     public boolean loadJornada(String[] jornada) 
    {
        String [] AJ=null;
        String [] P=null;
        String [] D=null;
        for (String jornada1 : jornada) {
            ArrayList <Partido> p= new ArrayList();
            AJ = jornada1.split("\\+");
            P=AJ[2].split("#");
            for (String P1 : P) {
                D = P1.split("\\$");
                Partido tmp=Partido.factory(D);
                p.add(tmp);
            }
            Jornada j= new Jornada(AJ[0],AJ[1],p);
            Jornadas.add(j);
        }
        return true;
    }
    
    public boolean modj(String nombre, String nombre_J, String[] opciones) 
    {
        for(Equipo e : Equipos)
        {
            if(e.getNombre().equals(nombre))
            {
               return e.modJ(nombre_J,opciones);
            }
        }
        return false;
    }

    public boolean delPlayer(String nombre, String nombre_J) 
    {
        for(Equipo e : Equipos)
        {
            if(e.getNombre().equals(nombre))
            {
               return e.delPlayer(nombre_J);
            }
        }
        return false;
    }

    public boolean addJ(String nombre, String nombre_J, String[] opciones) 
    {
        for(Equipo e : Equipos)
        {
            if(e.getNombre().equals(nombre))
            {
               return e.addJ(nombre_J,opciones);
            }
        }
        return false;
    }

    public boolean leerRes(int num, String[][] res)
    {
        if(Jornadas.get(num-1).getDEP().isEmpty())
        {
            int contador=num-1;
            for(Jornada j : Jornadas)
            {
                if(j.getN_jornada()==num)
                    j.datos(res);
            }
            if(contador==0)
            {
                return this.cargarPrimero(contador);
            }
            else return this.cargarAnterior(contador);
        }
        return false;
    }

    public boolean cargarAnterior(int contador) 
    {
        String Escritorio=Rutas.pathToDesktop().toString();
        String [][]res=null;
        String[] numeros={"uno","dos","tres","cuatro",
                         "cinco","seis","siete","ocho",
                         "nueve","diez","once","doce",
                         "trece","catorce","quince","dieciseis","diecisiete","deiciocho","diecinueve","veinte"};
        String opcion=numeros[contador-1];
        String ruta=Escritorio+File.separator+"LigFemBal"+File.separator+"resul_jornadas"+File.separator+opcion+".txt";
        File f= new File(ruta);
        if(f.exists())
        {
            try {
                res=OpMat.importFromDisk(f, "=");
            } catch (Exception ex) {
               return false;
            }
            return leerResAnterior(contador,res);
        }
        else
        {
            return false;
        }
    }

    private boolean leerResAnterior(int contador, String[][] res) 
    {
        if(contador>1)
        {
            this.cargarAnterior(contador-1);
            for(Jornada j : Jornadas)
            {
                if(j.getN_jornada()==contador)
                {
                    return j.datos(res);
                }
            }
            return false;
        }
        else if(contador==1)
        {
            if(this.cargarPrimero(contador-1))
            {
                for(Jornada j : Jornadas)
                {
                        if(j.getN_jornada()==contador)
                    {
                        return j.datos(res);
                    }
                }
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    private boolean cargarPrimero(int i) 
    {
        String Escritorio=Rutas.pathToDesktop().toString();
        String [][]res=null;
        String opcion="uno";
        String ruta=Escritorio+File.separator+"LigFemBal"+File.separator+"resul_jornadas"+File.separator+opcion+".txt";
        File f= new File(ruta);
        if(f.exists())
        {
            try {
                res=OpMat.importFromDisk(f, "=");
            } catch (Exception ex) {
               return false;
            }
            return leerResAnterior(i,res);
        }
        else
        {
            return false;
        }
    }

    public void actualizar(int num) 
    {
        int ant=0,sig=1;
        int Pf=0,Pc=0,Pj,Pg,Pp,Pcl;
        Datos_equipo Movistar=null,idk=null;
       do
        {
            for(int i=0;i<Jornadas.get(ant).getDEP().size();i++)
            {
                Pf=Jornadas.get(ant).getDEP().get(i).getPF();
                    Pc=Jornadas.get(ant).getDEP().get(i).getPC();
                        Pj=Jornadas.get(ant).getDEP().get(i).getN_PJ();
                            Pg=Jornadas.get(ant).getDEP().get(i).getN_PG();
                                Pp=Jornadas.get(ant).getDEP().get(i).getN_PP();
                                
                for(int j=0;j<Jornadas.get(sig).getDEP().size();j++)
                    {
                        if(Jornadas.get(sig).getDEP().size()==14)
                        {
                            if(Jornadas.get(ant).getDEP().get(i).getNombre().equals("MOVISTAR ESTUDIANTES")) 
                            {
                                Movistar=Jornadas.get(ant).getDEP().get(i); 
                            }
                            if(Jornadas.get(ant).getDEP().get(i).getNombre().equals("IDK EUSKOTREN"))
                            {
                                idk=Jornadas.get(ant).getDEP().get(i);
                            }
                        }
                        
                        if(Jornadas.get(ant).getDEP().size()==14)
                        {
                            if(Jornadas.get(sig).getDEP().get(j).getNombre().equals("MOVISTAR ESTUDIANTES"))
                            {
                                Jornadas.get(sig).getDEP().get(j).setN_PJ(Movistar.getN_PJ()+1);
                                Jornadas.get(sig).getDEP().get(j).setN_PG(Movistar.getN_PG());
                                Jornadas.get(sig).getDEP().get(j).setN_PP(Movistar.getN_PP());
                                Jornadas.get(sig).getDEP().get(j).setPC(Movistar.getPC());
                                Jornadas.get(sig).getDEP().get(j).setPF(Movistar.getPF());
                                Jornadas.get(sig).getDEP().get(j).setPCL(Movistar.getPCL());
                            }
                            if(Jornadas.get(sig).getDEP().get(j).getNombre().equals("IDK EUSKOTREN"))
                            {
                                Jornadas.get(sig).getDEP().get(j).setN_PJ(idk.getN_PJ()+1);
                                Jornadas.get(sig).getDEP().get(j).setN_PG(idk.getN_PG());
                                Jornadas.get(sig).getDEP().get(j).setN_PP(idk.getN_PP());
                                Jornadas.get(sig).getDEP().get(j).setPC(idk.getPC());
                                Jornadas.get(sig).getDEP().get(j).setPF(idk.getPF());
                                Jornadas.get(sig).getDEP().get(j).setPCL(idk.getPCL());
                            }
                        }
                        if(Jornadas.get(ant).getDEP().get(i).getNombre().equals(Jornadas.get(sig).getDEP().get(j).getNombre()))
                            {
                                Pf+=Jornadas.get(sig).getDEP().get(j).getPF();
                                    Pc+=Jornadas.get(sig).getDEP().get(j).getPC();
                                        Pj+=Jornadas.get(sig).getDEP().get(j).getN_PJ();
                                            Pg+=Jornadas.get(sig).getDEP().get(j).getN_PG();
                                                Pp+=Jornadas.get(sig).getDEP().get(j).getN_PP();  
                                                    Pcl=(2*Pg)+Pp;
                     
                                Jornadas.get(sig).getDEP().get(j).setPF(Pf);
                                    Jornadas.get(sig).getDEP().get(j).setPC(Pc);
                                        Jornadas.get(sig).getDEP().get(j).setN_PJ(Pj);
                                            Jornadas.get(sig).getDEP().get(j).setN_PG(Pg);
                                                    Jornadas.get(sig).getDEP().get(j).setN_PP(Pp);
                                                        Jornadas.get(sig).getDEP().get(j).setPCL(Pcl);
                            }
                        
                    }
            }
            ant++;
            sig++;
        }while(sig<num);
    }
    
    public boolean modFecha(int num, String fecha) 
    {
        for(Jornada j : Jornadas)
        {
            if(j.getN_jornada()==num)
            {
                j.setFecha(fecha);
                return true;
            }   
        }

        return false;
    }

    public boolean modFH(int num, String nombre, String fecha, String hora) 
    {
        for(Jornada j : Jornadas)
        {
            if(j.getN_jornada()==num)
            {
                return j.modFH(nombre,fecha,hora);
            }
                
        }
        return false;
    }
    
    public String[][] showJ(int num) 
    {
        String [][]tmp=new String[Jornadas.get(0).getN_Part()][];
        for(Jornada J: Jornadas)
        {
             if(J.getN_jornada()==num)
             {
                 if(!J.getDEP().isEmpty())
                 {
                    for(int i=0;i<J.getN_Part();i++)
                    {
                        tmp[i]=J.getPartidos().get(i).toState();
                    }
                        return tmp;
                 }
             }
        }
        return null;
        
    }

    public String[] showClas(int num) 
    {
        for(Jornada j : Jornadas)
        {
            if(j.getN_jornada()==num && !j.getDEP().isEmpty())
            {
                return j.showClas();
            }
                
        }
        return null;
    }

    public String[][] showEquipos() 
    {
        String[][]tmp= new String[Equipos.size()][];
        Equipos.sort(Comparator.comparing(Equipo:: getTelefono).reversed());
        for(int i=0;i<Equipos.size();i++)
        {
            tmp[i]=Equipos.get(i).toRow();
        }
        return tmp;
    }

    public String[][] showPlayers(String nombre) 
    {
        for(Equipo e: Equipos)
        {
            if(e.getNombre().equals(nombre))
                return e.showPlayers();
        }
        return null;
    }

    public String[][] showRelacion(String letra) 
    {
        ArrayList<Jugadora> j= new ArrayList();
        for(Equipo e : Equipos )
        {
            for(Jugadora p : e.getJ())
            {
                if(p.getNombre().charAt(0)==letra.charAt(0))
                    j.add(p);
            }
        }
        
        Collections.sort(j,new OrdenarNac());
        
        String[][]tmp=new String[j.size()][];
        if(tmp.length!=0)
        {
            for(int i=j.size()-1, k=0; i>=0;i--,k++)
            {
                tmp[i]=j.get(k).toRowF();
            }
            return tmp;
        }
        else
            return null;
    }

    public boolean printJugadoras(String nombre) 
    {
        PrintWriter pw=null;
        boolean exist=false;
        String Escritorio=Rutas.pathToDesktop().toString();
        for(Equipo e: Equipos)
        {
            if(e.getNombre().equals(nombre))
                exist=true;
        }
        if(exist)
        {
            String ruta=Escritorio
                    +File.separator
                    +"LigFemBal"
                    +File.separator
                    +"fichsalida"
                    +File.separator
                    +nombre+".enc";
            File f= new File(ruta);
            try {
               pw= new PrintWriter(f);
            } catch (FileNotFoundException ex) {
                return false;
            }
            pw.printf("%40s\t%40s\t%40s\t%40s\t%40s\t%40s\t%40s\t%40s%n",
                    "NOMBRE",
                    "POSICIÓN",
                    "DORSAL",
                    "FECHA DE NACIMIENTO",
                    "CIUDAD DE NACIMIENTO",
                    "PROVINCIA DE NACIMIENTO",
                    "NACIONALIDAD",
                    "ALTURA");
            for(Equipo e : Equipos)
            {
                if(e.getNombre().equals(nombre))
                {
                    for(Jugadora j : e.getJ())
                    {
                        pw.printf("%s",j.tolinea());
                    }
                }
            }
            pw.close();
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean printEquipos()
    {
        PrintWriter pw;
        String Escritorio=Rutas.pathToDesktop().toString();
        String ruta=Escritorio
                +File.separator
                +"LigFemBal"
                +File.separator
                +"fichsalida"
                +File.separator
                +"equipos.txt";
        File f= new File(ruta);
        try {
               pw= new PrintWriter(f);
            } catch (FileNotFoundException ex) {
                return false;
         }
        pw.printf("%40s\t%12s\t%50s\t%30s%n",
                "NOMBRE",
                "TELÉFONO",
                "PÁGINA WEB",
                "EMAIL");
        for(Equipo e :this.Equipos)
        {
            pw.printf("%s",e.tolinea());
        }
        pw.close();
        return true;    
    }

    public boolean printClas(int num) 
    {
        PrintWriter pw;
        boolean exist=false;
        String Escritorio=Rutas.pathToDesktop().toString();
        for(Jornada j : Jornadas)
        {
            if(j.getN_jornada()==num && !j.getDEP().isEmpty())
                exist=true;
        }
        if(exist)
        {
            String ruta=Escritorio
                    +File.separator
                    +"LigFemBal"
                    +File.separator
                    +"fichsalida"
                    +File.separator
                    +"fich_hmtl_"
                    +num
                    +".html";
            
            File f= new File (ruta);
            try {
               pw= new PrintWriter(f);
            } catch (FileNotFoundException ex) {
                return false;
         }
            pw.printf("<!DOCTYPE html>%n"
                    + "<HTML>%n"
                    + "<HEAD>%n"
                    + "<meta charset=\"UTF-8\">%n"
                    + "<H1>Clasificación de la jornada %d</H1>%n"
                    + "</HEAD>%n"
                    + "<BODY>", num);
            pw.printf("<TABLE BORDER =1>%n");
            String cabecera = String.format("<TR>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "</TR>",
                 "PUESTO",
                 "EQUIPO",
                 "PJ",
                 "PG",
                 "PP",
                 "PF",
                 "PC",
                 "PCL");
                pw.printf("%s%n",cabecera);    
            for(Jornada j : Jornadas)
            {
                if(j.getN_jornada()==num) 
                {
                    j.getDEP().sort(Comparator.comparing(Datos_equipo::getPCL).thenComparing(Datos_equipo::getPF));
                    for(int i=j.getDEP().size()-1,k=0;i>=0;i--,k++)
                    {
                        pw.printf("%s%n",j.getDEP().get(i).toHTMLRow(k));
                    }
                }
            }
            pw.close();
            return true;
        }
        else
        {
            return false;
        }
    }

    public String[] nombre() 
    {
        String [] nombre=new String [Equipos.size()];
        int i =0;
        for(Equipo e : Equipos)
        {
            nombre[i]=e.getNombre();
            i++;
        }
        return nombre;
    }
    
}
