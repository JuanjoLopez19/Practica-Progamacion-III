/*
 * Práctica final de la asignatura de Programación III
 * Autor: Juan José López Gómez
 * DNI: 
 * Si la práctica no se ejecuta de forma correcta puede estar debido a los archivos de la carpeta "LigFemBal", adjunto con los que he estado trabajando sin supuestas erratas
*/
package vista;

import com.coti.tools.Esdia;
import com.coti.tools.OpMat;
import controlador.Controlador;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan José López Gómez
 */
public class Vista 
{
    Controlador c= new Controlador();
    public void runmenu(String menu) 
    {
        boolean exit=false,exit2=false,exit3=false,exit4=false,exit5=false;
        do
        {
            
            String opcion=Esdia.readString(menu);
            String opc2,opc3,opc4,opc5;
            switch(opcion)
            {
                case "1":
                {
                    if(setTemp())
                    { 
                        System.out.println("Se ha declarado correctamente la temporada.");
                        if(cargarJornada())
                        {
                            System.out.println("Se han cargado correctamente los datos de la jornada");
                            if(cargarEquipos())
                            {
                                System.out.println("Se han cargado correctamente los equipos");
                                if(cargarJugadoras())
                                {
                                    System.out.println("Se han cargado correctamente las jugadoras en cada equipo");
                                }
                                else
                                {
                                    System.out.println("Ha habido un error al cargar las jugadoras de cada equipo");
                                    break;
                                }
                            }
                            else
                            {
                                System.out.println("Se ha producido un error al cargar los datos de los equipos");
                                break;
                            }
                        }
                        else
                        {
                            System.out.println("Error al cargar las jornadas.");
                            break;
                        }
                    }
                    else
                    {
                        System.out.println("La temporada ya estaba cargada no hace falta volver a cargarla");
                        break;
                    }
                    break;
                }
                case "2":
                {
                    if(!c.getTemp())
                    {
                    do
                    {
                        switch(opc2=menuJugadoras())
                        {
                            case "1" ->
                            {
                                modificarDatos();
                            }
                            case "2" ->
                            {
                                eliminarJugadora();
                            }   
                            case "3" ->
                            {
                                addPlayer();
                            }
                            case "q" ->{
                                exit2=Esdia.yesOrNo("¿Quieres regresar al menú principal?");
                            }
                        }
                    }while(!exit2);
                    break;
                    }
                    else
                    {
                        System.out.println("La Temporada no ha sido inicializada por lo que no se puede hacer nada.");
                        break;
                    }
                }   
                case "3":
                {
                    if(!c.getTemp())
                    {
                        do
                        {
                            switch(opc3=menuJornada())
                            {
                                case "1" ->{
                                    leerResultados();
                                }
                                case "2" ->{
                                    modificarFecha();
                                }
                                case "3" ->{
                                    modFH();
                                }
                                case "4" ->{
                                    mostrarJornada();
                                }
                                case "5" ->{
                                    mostrarClasificacion();
                                }
                                case "q" ->{
                                    exit3=Esdia.yesOrNo("¿Quieres regresar al menú principal?");
                                }
                            }
                        }while(!exit3);
                    }
                    else
                    {
                       System.out.println("La Temporada no ha sido inicializada por lo que no se puede hacer nada.");
                       break; 
                    }
                    break;
                }
                case "4":
                {
                    if(!c.getTemp())
                    {
                        do
                        {
                            switch(opc4=menuVisualizar())
                            {
                                case "1" ->{
                                    showJugadoras();
                                }
                                case "2" ->{
                                    showEquipos();
                                }
                                case "3" ->{
                                    showRelacion();
                                } 
                                case "q" ->{
                                    exit4=Esdia.yesOrNo("¿Quieres regresar al menú principal?");
                                }
                            }
                        }while(!exit4);
                    break;
                }
                }
                case "5":
                {
                    if(!c.getTemp())
                    {
                        do
                        {
                            switch(opc5=menuAlmacenar())
                            {
                                case "1" ->{
                                    printJugadoras();
                                }
                                case "2" ->{
                                    printEquipos();
                                }
                                case "3" ->{
                                    printClas();
                                }
                                case "q" ->{
                                    exit5=Esdia.yesOrNo("¿Quieres regresar al menú principal?");
                                }
                            }
                        }while(!exit5);
                    break;
                    }
                }
                case "q":
                {
                    exit=Esdia.yesOrNo("¿Quiere salir de la aplicación?");
                    if(exit)
                    {
                        guardarBinario();
                    }
                    break;
                }
                default:
                {
                    System.out.println("Ha introducido una opción no válida, vuelva a introducirla una opción.");
                    
                }
            }
        }while(!exit);
    }

    private boolean setTemp() 
    {
        int Temporada=Esdia.readInt("Introduce el año de la temporada: ",1950,2050);
        int t=Temporada+1;
        String Temp=Temporada+"/"+t;
        return c.setTemp(Temp);
    }

    private boolean cargarJornada() 
    {
        System.out.println("Se van a cargar los datos referentes a cada Jornada.");
        System.out.println("");
        String[] jornada=c.getJornada();
        if(jornada!=null)
            return c.loadJornada(jornada);
        else
            return false;
    }

    private boolean cargarEquipos() 
    {
        System.out.println("Se van a cargar los datos referentes a cada equipo.");
        System.out.println("");
        File f=new File(c.getRutaEq());
        String [][] equipos=null;
        if(f.exists())
        {
        try {
            equipos=OpMat.importFromDisk(f, "#");
        } catch (Exception ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
            return c.setEquipos(equipos);
            
        }
        else
        {
            System.out.println("La ruta "+ f.toString()+" no existe");
            return false;
        }
    }

    private boolean cargarJugadoras() 
    {
        System.out.println("Se van a cargar los datos referentes a las jugadoras de cada equipo.");
        System.out.println("");
        return c.cargarJugadoras();
    }

    private String menuJugadoras() 
    {
        System.out.println("Ha introducido la opción de gestión de jugadoras");
        String opc=Esdia.readString("%n 1.-Modificar datos"
                                  + "%n 2.-Eliminar jugadora"
                                  + "%n 3.-Añadir jugadora"
                                  + "%n q.-Salir al menú principal"
                                  + "%n Introduce una opción por favor: ");
        return opc;
    }

    private void modificarDatos() 
    {
        boolean exit=false;
        System.out.println("Ha introducido la opcion de modificar una jugadora");
        System.out.println("");
        System.out.println("Los nombres de los equipos son los siguientes: ");
        for (String nombresEquipo : c.nombresEquipo()) {
            System.out.println(nombresEquipo);
        }
        String nombre=Esdia.readString("Introduce el equipo al que pertence la jugadora: ");
        nombre=nombre.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
        String nombre_J=Esdia.readString("Introduce el nombre de la jugadora que quieres modificar: ");
        nombre_J=nombre_J.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
        String [] opciones={"","","","","","",""};
        do
        {
            String opcion=Esdia.readString("%n 1-Posicion%n 2-Dorsal%n 3-Fecha de Nacimiento"
                                         + "%n 4-Ciudad de nacimiento%n 5-Provincia de Nacimiento"
                                         + "%n 6-Nacionalidad%n 7-Altura%n q-salir"
                                         + "%n Introduce la categoría que quieras modificar:");
            switch(opcion)
            {
                case "1":
                        {
                            opciones[0]=Esdia.readString("Introduce la posición nueva de la jugadora(con acentos): ");
                            opciones[0]=opciones[0].toLowerCase();
                            if(opciones[0].equals("ala-pívot")||opciones[0].equals("pívot")||opciones[0].equals("base")||opciones[0].equals("escolta")||opciones[0].equals("alero"))
                            {
                                System.out.println("Opción correcta y guardada");
                                break;
                            }
                            else
                            {
                                System.out.println("No es una posición correcta. ");
                                opciones[0]="";
                            }
                            break;
                        }
                case "2":
                        {
                            int num;
                            num=Esdia.readInt("Introduce el dorsal de la jugadora: ");
                            if(num>0)
                            {
                                opciones[1]=num+"";
                                System.out.println("Opción correcta y guardada");
                                break;
                            }
                            else
                            {
                                System.out.println("No es un dorsal correcto. ");
                                opciones[1]="";
                            }
                            break;
                        }
                case "3":
                        {
                            int dia,mes,ano;
                            dia=Esdia.readInt("Introduce el día de nacimiento: ", 1, 31);
                            mes=Esdia.readInt("Introduce el mes de nacimiento: ", 1, 12);
                            ano=Esdia.readInt("Introduce el año de nacimiento: ",1960,2020);
                            opciones[2]=dia+"/"+mes+"/"+ano;  
                            break;
                        }
                case "4":
                        {
                            boolean opc;
                            String ciudad=Esdia.readString("Introduce la ciudad de nacimiento: ");
                            opc=Esdia.yesOrNo("¿Es esa la ciudad que quieres("+ciudad+")? ");
                            if(opc)
                            {
                                opciones[3]=ciudad;
                            }
                            else
                            {
                                System.out.println("No se añadira esa ciudad");
                                opciones[3]="";
                            }
                            break;
                        }
                case "5":
                        {
                            boolean opc;
                            String provincia=Esdia.readString("Introduce la provincia de nacimiento: ");
                            opc=Esdia.yesOrNo("¿Es esa la ciudad que quieres("+provincia+")? ");
                            if(opc)
                            {
                                opciones[4]=provincia;
                            }
                            else
                            {
                                System.out.println("No se añadira esa provincia");
                                opciones[4]="";
                            }
                            break;
                        }
                case "6":
                        {
                            boolean opc;
                            String nacionalidad=Esdia.readString("Introduce la nacionalidad de nacimiento: ");
                            opc=Esdia.yesOrNo("¿Es esa la ciudad que quieres("+nacionalidad+")? ");
                            if(opc)
                            {
                                opciones[5]=nacionalidad;
                            }
                            else
                            {
                                System.out.println("No se añadira esa provincia");
                                opciones[5]="";
                            }
                            break;
                        }
                case "7":
                        {
                            int altura=Esdia.readInt("Introduce la altura de la jugadora: ",150,215);
                            opciones[6]=altura+"";
                        }
                case "q":
                        {
                            exit=Esdia.yesOrNo("¿Quiere acabar de modificar la jugadora?");
                            break;
                        }
                default:
                {
                    System.out.println("No ha introducido una opción valida");
                }
            }    
        }while(!exit);
        
    if(c.modJ(nombre,nombre_J,opciones)==false)
    {
        System.out.println("Se ha producido un error y no se ha modificado nada");
    }
    else{
        System.out.println("Los cambios se han hecho con exito");
    }
  }

    private void eliminarJugadora() 
    {
        String nombre;
        String nombre_J;
        boolean exit=true;
        System.out.println("Ha introducido la opcion de eliminar una jugadora");
        System.out.println("Los nombres de los equipos son los siguientes: ");
        for (String nombresEquipo : c.nombresEquipo()) {
            System.out.println(nombresEquipo);
        }
        do{
        nombre=Esdia.readString("Introduce el equipo al que pertence la jugadora: ");
        nombre=nombre.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
        nombre_J=Esdia.readString("Introduce el nombre de la jugadora que quieres modificar: ");
        nombre_J=nombre_J.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
        exit=Esdia.yesOrNo("¿Los datos "+nombre+" y "+nombre_J+" son correctos?");
        }while(!exit);
        if(c.delPlayer(nombre,nombre_J))
        {
            System.out.println("La jugadora "+nombre_J+" se ha eliminado correctamente del equipo "+nombre);
        }
        else
        {
            System.out.println("Se ha producido un error al intentar eliminar la jugadora");
        }
    }

    private void addPlayer() 
    {
        
        boolean exit=false;
        System.out.println("Ha introducido la opcion de añadir una jugadora a un equipo");
        System.out.println("");
        System.out.println("Los nombres de los equipos son los siguientes: ");
        for (String nombresEquipo : c.nombresEquipo()) {
            System.out.println(nombresEquipo);
        }
        String nombre=Esdia.readString("Introduce el equipo al que desea añadir la jugadora:  ");
        nombre=nombre.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
        String nombre_J=Esdia.readString("Introduce el nombre de la jugadora: ");
        nombre_J=nombre_J.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
        String [] opciones={"","","","","","",""};
        do
        {
            String opcion=Esdia.readString("%n 1-Posicion%n2-Dorsal%n 3-Fecha de Nacimiento"
                                         + "%n 4-Ciudad de nacimiento%n 5-Provincia de Nacimiento"
                                         + "%n 6-Nacionalidad%n 7-Altura%n q-salir"
                                         + "%n Introduce la categoría que quieras modificar:");
            switch(opcion)
            {
                case "1" ->                         {
                            opciones[0]=Esdia.readString("Introduce la posición nueva de la jugadora(con acentos): ");
                            opciones[0]=opciones[0].toLowerCase();
                            if(opciones[0].equals("ala-pívot")||opciones[0].equals("pívot")||opciones[0].equals("base")||opciones[0].equals("escolta")||opciones[0].equals("alero"))
                            {
                                System.out.println("Opción correcta y guardada");
                            }
                            else
                            {
                                System.out.println("No es una posición correcta. ");
                                opciones[0]="";
                            }
                        }
                case "2" ->                         {
                            int num;
                            num=Esdia.readInt("Introduce el dorsal de la jugadora: ");
                            if(num>0)
                            {
                                opciones[1]=num+"";
                                System.out.println("Opción correcta y guardada");
                            }
                            else
                            {
                                System.out.println("No es un dorsal correcto. ");
                                opciones[1]="";
                            }
                        }
                case "3" ->                         {
                            int dia,mes,ano;
                            dia=Esdia.readInt("Introduce el día de nacimiento: ", 1, 31);
                            mes=Esdia.readInt("Introduce el mes de nacimiento: ", 1, 12);
                            ano=Esdia.readInt("Introduce el año de nacimiento: ",1960,2020);
                            opciones[2]=dia+"/"+mes+"/"+ano;  
                        }
                case "4" ->                         {
                            boolean opc;
                            String ciudad=Esdia.readString("Introduce la ciudad de nacimiento: ");
                            opc=Esdia.yesOrNo("¿Es esa la ciudad que quieres("+ciudad+")? ");
                            if(opc)
                            {
                                opciones[3]=ciudad;
                            }
                            else
                            {
                                System.out.println("No se añadira esa ciudad");
                                opciones[3]="";
                            }
                        }
                case "5" ->                         {
                            boolean opc;
                            String provincia=Esdia.readString("Introduce la provincia de nacimiento: ");
                            opc=Esdia.yesOrNo("¿Es esa la provincia que quieres("+provincia+")? ");
                            if(opc)
                            {
                                opciones[4]=provincia;
                            }
                            else
                            {
                                System.out.println("No se añadira esa provincia");
                                opciones[4]="";
                            }
                        }
                case "6" ->                         {
                            boolean opc;
                            String nacionalidad=Esdia.readString("Introduce la nacionalidad de nacimiento: ");
                            opc=Esdia.yesOrNo("¿Es esa la ciudad que quieres("+nacionalidad+")? ");
                            if(opc)
                            {
                                opciones[5]=nacionalidad;
                            }
                            else
                            {
                                System.out.println("No se añadira esa provincia");
                                opciones[5]="";
                            }
                        }
                case "7" ->                         {
                            int altura=Esdia.readInt("Introduce la altura de la jugadora: ",150,215);
                            opciones[6]=altura+"";
                        }
                case "q" ->                         {
                            exit=Esdia.yesOrNo("¿Quiere acabar de modificar la jugadora?");
                        }
                default -> {
                    System.out.println("No ha introducido una opción valida");
                }
            }    
        }while(!exit);
    if(c.addJ(nombre,nombre_J,opciones)==false)
    {
        System.out.println("Se ha producido un error y no se ha modificado nada");
    }
    else{
        System.out.println("Los cambios se han hecho con exito");
    }
    }

    private String menuJornada() 
    {
        System.out.println("Ha introducido la opción de gestión de jornada");
        String opc=Esdia.readString("%n 1.-Leer resultados"
                                  + "%n 2.-Modificar la fecha de la jornada"
                                  + "%n 3.-Modificar fecha u hora de un partido"
                                  + "%n 4.-Mostrar resultados de la jornada"
                                  + "%n 5.-Mostrar la clasificación de jornada"
                                  + "%n q.- Salir al menú principal"
                                  + "%n Introduce una opción por favor: ");
        return opc;
    }

    private void leerResultados() 
    {
        int num=Esdia.readInt("Introduce el numero de jornada: ",1,c.rutaJ().length);
        if(c.leerRes(num))
        {
            c.actualizar(num);
            System.out.println("La lectura de los datos de la jornada "+num+" ha sido correcta");
        }
        else
        {
            System.out.println("No ha sido posible leer la jornada porque ya se encontraba cargada y actualizada");
        }
    }

    private void modificarFecha() 
    {
        boolean exit=false;
        String fecha;
        int num;
        do
        {
        num=Esdia.readInt("Introduce el numero de jornada: ",1,c.rutaJ().length);
        int dia=Esdia.readInt("Introduce el dia: ",1,31);
        int mes=Esdia.readInt("Introduce el mes: ",1,12);
        int ano=Esdia.readInt("Introduce el año: ",c.splitTemp(),c.splitTemp()+1);
        fecha=dia+"/"+mes+"/"+ano;
        exit=Esdia.yesOrNo("¿Seguro que esa es la fecha correcta("+fecha+")");
        }while(!exit);
        
        if(c.modFecha(num,fecha))
        {
            System.out.println("La fecha se ha modificado correctamente");
        }
        else
        {
            System.out.println("Se ha producido un error al modificar la fecha");
        }
    }

    private void modFH() 
    {
        boolean exit=false,quest1,quest2;
        int num;
        String nombre,fecha ="",hora ="";
        
            num=Esdia.readInt("Introduce el numero de jornada: ",1,c.rutaJ().length);
            nombre=Esdia.readString("Introduce el nombre del equipo: ");
            nombre=nombre.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
        do
        {
            if(quest1=Esdia.yesOrNo("¿Quieres cambiar la fecha del partido?"))
            {
                int dia=Esdia.readInt("Introduce el dia: ",1,31);
                int mes=Esdia.readInt("Introduce el mes: ",1,12);
                int ano=Esdia.readInt("Introduce el año: ",c.splitTemp(),c.splitTemp()+1);
                fecha=dia+"/"+mes+"/"+ano;
            }
            if(quest2=Esdia.yesOrNo("¿Quieres cambiar la hora del partido?"))
            {
                int h=Esdia.readInt("Introduce la hora: ", 0, 23);
                int m=Esdia.readInt("Introduce los minutos: ",0,59);
                hora=h+"/"+m;
            }
            exit=Esdia.yesOrNo("¿Los datos son correctos("+fecha+"//"+hora+")");
        }while(!exit);
        if(c.modFH(num,nombre,fecha,hora))
        {
            System.out.println("Se ha modificado correctamente la fecha y la hora del partido");
        }
        else
        {
            System.out.println("Se ha producido un error al modificar el partido");
        }
    }

    private void mostrarJornada() 
    {
        int num=Esdia.readInt("Introduce el numero de jornada: ",1,c.rutaJ().length);
        String [][] jornada=c.ShowJ(num);
        if(jornada!=null)
        {
            System.out.println("La jornada "+num+" tiene estos partidos y estos resultados");
            try {
                OpMat.printToScreen3(jornada);
            } catch (Exception ex) {
                System.err.println("Se ha producido un error al crear la matriz");
            }
        }
        else
        {
            System.out.println("La jornada no ha podido ser mostrada");
        }
        
    }

    private void mostrarClasificacion() 
    {
        int num=Esdia.readInt("Introduce el numero de jornada: ",1,c.rutaJ().length);
        String[] tmp=c.showClas(num);
        if(tmp!=null)
        {
        System.out.printf("%5s\t%50s\t%4s\t%4s\t%4s\t%4s\t%4s\t%4s\t%n","Puesto","Equipo","PJ","PG","PP","PF","PC","PTS");
        for(int i = 0;i<tmp.length;i++)
        {
            System.out.println(i+1+"\t"+tmp[i]);
        }
        }
        else
        {
            System.out.println("No se ha podido mostrar la clasificacion");
        }
    }

    private String menuVisualizar() 
    {
        System.out.println("Ha introducido la opción de visualización");
        String opc=Esdia.readString("%n 1.-Mostrar jugadoras ordenadas por altura y posición"
                                  + "%n 2.-Mostrar equipos"
                                  + "%n 3.-Mostrar todas las jugadoras por inicial ordenadas por la fecha de nacimiento"
                                  + "%n q.- Salir al menú principal"
                                  + "%n Introduce una opción por favor: ");
        return opc;
    }

    private void showJugadoras() 
    {
        System.out.println("Los nombres de los equipos son los siguientes: ");
        System.out.println("");
        for (String nombresEquipo : c.nombresEquipo()) {
            System.out.println(nombresEquipo);
        }
       String nombre=Esdia.readString("Introduce el nombre de un equipo: ");
       nombre=nombre.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
       String[][] tmp =c.showPlayers(nombre);
       if(tmp!=null)
       {
           try {
               OpMat.printToScreen3(tmp);
           } catch (Exception ex) {
               Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       else
       {
           System.out.println("No se ha podido mostrar el equipo");
       }
           
    }

    private void showEquipos() 
    {
        String[][] tmp=c.showEquipos();
        if(tmp!=null)
        {
            System.out.println("|\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tRELACIÓN DE EQUIPOS\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|");
            try {
                OpMat.printToScreen3(tmp);
            } catch (Exception ex) {
                Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            System.out.println("No se ha podido mostrar la relación de los equipos");
        }
    }

    private void showRelacion() 
    {
        String letra=Esdia.readString("Introduce una letra para buscar las jugadoras con esa inicial: ");
        letra=letra.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
        String[][]tmp=c.showRelacion(letra);
        if(tmp!=null)
        {
            System.out.println("Se ha calculado la relación de las jugadoras cuyo nombre empieza por "+letra+" y están ordenados por fecha de nacimiento");
            try {
                OpMat.printToScreen3(tmp);
            } catch (Exception ex) {
                Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            System.out.println("No se ha podido calcular la relación");
        }
    }

    private String menuAlmacenar() 
    {
        System.out.println("Ha introducido la opción de almacenar resultados");
        String opc=Esdia.readString("%n 1.-Almacenar datos de las jugadoras"
                                  + "%n 2.-Almacenar datos de los equipos"
                                  + "%n 3.-Fichero HTML con la clasificación de una jornada"
                                  + "%n q.- Salir al menú principal"
                                  + "%n Introduce una opción por favor: ");
        return opc;
    }

    private void printJugadoras() 
    {
        System.out.println("Los nombres de los equipos son los siguientes: ");
        for (String nombresEquipo : c.nombresEquipo()) {
            System.out.println(nombresEquipo);
        }
        String nombre=Esdia.readString("Introduce el nombre de un equipo: ");
        nombre=nombre.toUpperCase().replace("Á","A").replace("É", "E").replace("Í","I").replace("Ó","O").replace("Ú","U");
        if(c.printJugadoras(nombre))
        {
            System.out.println("El archivo "+nombre+".enc se ha creado satisfactoriamente en la carpeta fichsalida");
        }
        else
        {
            System.out.println("Ha habido un error en la escritura del fichero "+nombre+".enc");
        }
    }

    private void printEquipos() 
    {
        if(c.printEquipos())
        {
            System.out.println("El archivo equipos.enc se ha creado satisfactoriomente");
        }
        else
        {
             System.out.println("El archivo equipos.enc no se ha podido crear");
        }
    }

    private void printClas() 
    {
        int num=Esdia.readInt("Introduce el numero de jornada: ",1,c.rutaJ().length);
        if(c.printClas(num))
        {
            System.out.println("La clasificacion de la jornada "+num+" ha sido exportada al fichero fich_html_"+num+".html");
        }
        else
        {
            System.out.println("La clasificón de la jornada "+num+" no se ha podido crear porque la jornada no está cargada");
        }
    }

    private void guardarBinario() 
    {
        if(c.guardarBinario())
        {
            System.out.println("Se han guardado en formato binario todos los datos para que puedan ser recuperados");
        }
        else
        {
            System.out.println("No ha sido posible crear el fichero binario");
        }
    }

    public void comprobar() 
    {
        if(c.exist())
        {
            if(c.leerBinario())
                System.out.println("Si que hay datos por lo que se van a cargar para poder trabajar con ellos");
            else
                System.out.println("Se ha producido un error al cargar los datos");
        }
        else
        {
            System.out.println("No hay datos anteriores asique puede cargar la liga a partir de los archivos de texto");
        }
    }
    
}
