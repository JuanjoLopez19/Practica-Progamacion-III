/*
 * Práctica final de la asignatura de Programación III
 * Autor: Juan José López Gómez
 * DNI: 
 * Si la práctica no se ejecuta de forma correcta puede estar debido a los archivos de la carpeta"LigFemBal", adjunto con los que he estado trabajando sin supuestas erratas
 */
 
package controlador;

import com.coti.tools.OpMat;
import com.coti.tools.Rutas;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.LigFem;

/**
 *
 * @author Juan José López Gómez
 */
public class Controlador 
{
    LigFem l= new LigFem();
    
    public boolean setTemp(String temporada) 
    {
        if(l.getTemporada().isBlank())
        {
            l.setTemporada(temporada);
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    public String[] getJornada() 
    {
        String Escritorio=Rutas.pathToDesktop().toString();
        String [] parte=null;
        String ruta=Escritorio+File.separator+"LigFemBal"+File.separator+"datosjornadas.txt";
        File f= new File(ruta);
        FileReader fr = null; //Con la fumcion importfromdisk se puede importar con el \\+ y te da una matriz de tres columnas y las filas que sean
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        BufferedReader br = new BufferedReader(fr);
        String [] lineas = new String [l.getNJ()];
        int contador=0;
        try {
            do{
                lineas[contador]=br.readLine();
                contador++;
            }while(contador<l.getNJ());
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        try {
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lineas;
    }


    public boolean loadJornada(String[] jornada) 
    {
        return l.loadJornada(jornada);
}

    public String getRutaEq() 
    {
        String Escritorio=Rutas.pathToDesktop().toString();
        String ruta=Escritorio+File.separator+"LigFemBal"+File.separator+"datosequipos.txt";
        return ruta;
    }

    public boolean setEquipos(String[][] equipos) 
    {
        return l.setEquipos(equipos);
    }

    public boolean cargarJugadoras() 
    {
        return l.cargarJugadoras();
    }

    public boolean modJ(String nombre, String nombre_J, String[] opciones) 
    {
            return l.modj(nombre,nombre_J,opciones);
        
    }

    public boolean getTemp() 
    {
        return l.getTemporada().isEmpty();
    }


    public boolean delPlayer(String nombre, String nombre_J) 
    {
        return l.delPlayer(nombre,nombre_J);
    }

    public boolean addJ(String nombre, String nombre_J, String[] opciones) 
    {
        return l.addJ(nombre,nombre_J,opciones);
    }
    
    public File [] rutaJ()
    {
        String Escritorio=Rutas.pathToDesktop().toString();
        File [] listado=null;
        String ruta=Escritorio+File.separator+"LigFemBal"+File.separator+"resul_jornadas";
        File f = new File(ruta);
        if(f.exists())
        {
            listado= f.listFiles();
            return listado;
        }
        else
            return null;
    }

    public boolean leerRes(int num) 
    {
        String Escritorio=Rutas.pathToDesktop().toString();
        String [][]res=null;
        String[] numeros={"uno","dos","tres","cuatro",
                         "cinco","seis","siete","ocho",
                         "nueve","diez","once","doce",
                         "trece","catorce","quince","dieciseis","diecisiete","deiciocho","diecinueve","veinte"};
        String opcion=numeros[num-1];
        String ruta=Escritorio+File.separator+"LigFemBal"+File.separator+"resul_jornadas"+File.separator+opcion+".txt";
        File f= new File(ruta);
        if(f.exists())
        {
            try {
                res=OpMat.importFromDisk(f, "=");
            } catch (Exception ex) {
               return false;
            }
            return l.leerRes(num,res);
        }
        else
        {
            return false;
        }
    
    }

    public int splitTemp() 
    {
        String [] tmp=l.getTemporada().split("/");
        return Integer.parseInt(tmp[0]);
    }

    public boolean modFecha(int num, String fecha) 
    {
       return l.modFecha(num,fecha);
    }

    public boolean modFH(int num, String nombre, String fecha, String hora) 
    {
        return l.modFH(num,nombre,fecha,hora);
    }

    public String[][] ShowJ(int num) 
    {
        return l.showJ(num);
    }

    public void actualizar(int num) 
    {
        l.actualizar(num);
    }

    public String[] showClas(int num) 
    {
        return l.showClas(num);
    }

    public String[][] showEquipos() 
    {
        return l.showEquipos();
    }

    public String[][] showPlayers(String nombre) 
    {
        return l.showPlayers(nombre);
    }

    public String[][] showRelacion(String letra) 
    {
        return l.showRelacion(letra);
    }

    public boolean printJugadoras(String nombre) 
    {
        return l.printJugadoras(nombre);
    }

    public boolean printEquipos() 
    {
        return l.printEquipos();
    }

    public boolean printClas(int num) 
    {
        return l.printClas(num);
    }

    public boolean guardarBinario() 
    {
        
        String Escritorio=Rutas.pathToDesktop().toString();
        String ruta=Escritorio+File.separator+"LigFemBal"+File.separator+"binarios"+File.separator+"binario.bin";
        File f = new File(ruta);
        try {
             
            BufferedOutputStream bos= new BufferedOutputStream(new FileOutputStream(f));
            
            ObjectOutputStream oos= new ObjectOutputStream(bos);
            
            oos.writeObject(l);
            
            oos.close();
            return true;
        } catch ( FileNotFoundException ex){
                return false;
            } catch (IOException ex) {
                return false;
            }
    }
    public boolean leerBinario() 
        {

            String Escritorio=Rutas.pathToDesktop().toString();
            String ruta=Escritorio+File.separator+"LigFemBal"+File.separator+"binarios"+File.separator+"binario.bin";
            File f = new File(ruta);
            try{
               FileInputStream fis= new FileInputStream(f);
               BufferedInputStream bis = new BufferedInputStream(fis);
               ObjectInputStream ois= new ObjectInputStream(bis);
                
                this.l= (LigFem) ois.readObject();
                 
               ois.close();
               return true;
               } catch ( FileNotFoundException | ClassNotFoundException ex){
                return false;
            } catch (IOException ex) {
                return false;
            }
        }

    public boolean exist() 
    {
        String Escritorio=Rutas.pathToDesktop().toString();
        String ruta=Escritorio+File.separator+"LigFemBal"+File.separator+"binarios"+File.separator+"binario.bin";
        File f = new File(ruta);
        return f.exists();
        
    }

    public String[] nombresEquipo() 
    {
        return l.nombre();
    }

}

