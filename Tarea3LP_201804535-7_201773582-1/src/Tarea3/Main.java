package tarea3;
import tarea3.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public class Main{
    public static void main(String[] args) throws IOException{
    //pais: objeto pais que representa el mapa completo.
        Pais pais = new Pais();
    //ciudad: objeto ciudad, usado para transportar datos dentro del codigo.
        Ciudad ciudad = new Ciudad();
    //empresa: objeto empresa, guarda informacion del archivo empresa.txt.
        Empresa empresa = new Empresa();
    //vehiculo: objeto vehiculo, guarda informacion de los archivos.
        Vehiculo vehiculo = new Vehiculo();
        
    //Nombre de los archivos a leer.
        String nombreArchivo = "mapa.txt";
        String nombreArchivo2 = "edificaciones.txt";
        String nombreArchivo3 = "empresa.txt";
    //linea: linea nula usada para recorrer los archivos.
        String linea = null;
    //i: contador
        int i = 0;
    //lista: lista de enteros usada para recibir el resultado de shortestPath.
        ArrayList<Integer> lista = new ArrayList<Integer>();
    //Lectura de archivo, solo la primera linea para guardar el tamaño de la ciudad.
        try{
            FileReader reader = new FileReader(nombreArchivo);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while((linea = bufferedReader.readLine())!=null){
                if(i == 0){
                    int lon = Integer.parseInt(linea);
                    pais.setmaxVertices(lon);
                }else{
                    break;
                }
                i++;
            }
            bufferedReader.close();
        }catch(IOException ex){
            System.out.println("Error de lectura");
        }
    //Inicializa la matriz.
        int[][] matriz = pais.initMatriz(pais.getmaxVertices());
    //Arreglos: se crean arreglos para guardar en cada espacio un objeto respectivo.
        Ciudad[] arregloCiudad = new Ciudad[pais.getmaxVertices()];
        Cisterna[] arregloCisterna = new Cisterna[pais.getmaxVertices()];
        Camioneta[] arregloCamioneta = new Camioneta[pais.getmaxVertices()];
        Casa[] arregloCasa = new Casa[pais.getmaxVertices()];
        Edificio[] arregloEdificio = new Edificio[pais.getmaxVertices()];
    //Crea un objeto en cada espacio.
        for(i = 0; i<pais.getmaxVertices(); i++){
            arregloCiudad[i] = new Ciudad();
            arregloCisterna[i] = new Cisterna();
            arregloCamioneta[i] = new Camioneta();
            arregloCasa[i] = new Casa();
            arregloEdificio[i] = new Edificio();
        }
    
     
//***********************Lectura de primer archivo mapa.txt********************************************************
    //Se agregan los nodos a la matriz y los arcos entre ellos formando el pais.  
        i = 0;
        try{
            FileReader reader = new FileReader(nombreArchivo);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while((linea = bufferedReader.readLine())!=null){
        
                if(i == 1){
                    int line = Integer.parseInt(linea);
                    pais.setmaxEdges(line);
                }

                else if(i>1){
                    String[] valores = linea.split(" ");
                    int[] valores2 = new int[3];
                    for(int j = 0; j<valores.length; j++){
                        valores2[j] = Integer.parseInt(valores[j]);
                    }

                    pais.addNode(valores2[0], matriz);
                    pais.addNode(valores2[1], matriz);
                    pais.addEdge(valores2[0], valores2[1], valores2[2], matriz);                    
                    
                }

                i++; 
            }
            bufferedReader.close();
        }catch(IOException ex){
            System.out.println("error");
        }
//*******************************Lectura del segundo archivo empresa.txt************************************************************************
        i=0;
    //Se guardan los precios dados por el archivo empresa.txt.
        try{
            FileReader reader = new FileReader(nombreArchivo3);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while((linea = bufferedReader.readLine())!=null){
                if(i == 0){
                    empresa.setPrecioBalon(Integer.parseInt(linea));
                }
                if(i == 1){
                    empresa.setPrecioLitro(Integer.parseInt(linea));
                }
                if(i == 2){
                    vehiculo.setPKm(Integer.parseInt(linea));
                    
                }

                i++; 
            }
            bufferedReader.close();
        }catch(IOException ex){
            System.out.println("error");
        }
//*******************************Lectura del tercer archivo edificaciones.txt********************************************************************
        i=0;
        int id = 0;
    //En los arreglos se van guardando las especificaciones de cada ciudad.

        try{
            FileReader reader = new FileReader(nombreArchivo2);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            while((linea = bufferedReader.readLine())!=null){
                
                int gastoCasas = 0;
                int gastoEdificio = 0;
                

                if(i%3 == 0){
                    String[] val = linea.split(" ");
                    int[] val2 = new int[3];

                    for(int j = 0; j<val2.length; j++){
                        val2[j] = Integer.parseInt(val[j]);
                    }

                    
                    arregloCiudad[val2[0]].setId(val2[0]);                  
                    
                    
                    ciudad.setCasas(val2[1]);
                    
                    ciudad.setEdificios(val2[2]);

                                      
                    arregloCisterna[val2[0]].setCantCamiones(val2[2]);                 
                    

                    if(val2[1]>0){
                        arregloCamioneta[val2[0]].setCantCamioneta(1);
                    }
                    
                    if(ciudad.getCasas()==0){
                        i++;
                    }
                    
                }
                else if(i%3==1){
                    int j;
                    String[] valCasas = linea.split(" ");
                    int[] valCasas2 = new int[ciudad.getCasas()];
                    
                    for(j = 0; j<valCasas.length; j++){
                        valCasas2[j] = Integer.parseInt(valCasas[j]);
                    }                 

                    for(j = 0; j<valCasas2.length; j++){
                        gastoCasas += valCasas2[j]*empresa.getPrecioBalon();
                    }
                    

                    arregloCasa[id].setConsumo(gastoCasas);              
                    
                    if(ciudad.getEdificios()==0){
                        i++;
                    }

                }
                else if(i%3==2){
                    String [] valEdi = linea.split(" ");
                    int[] valEdi2 = new int[ciudad.getEdificios()];
                    int j;

                    for(j = 0; j<valEdi.length; j++){
                        valEdi2[j] = Integer.parseInt(valEdi[j]);
                    }

                    

                    for(j = 0; j<valEdi2.length; j++){
                        gastoEdificio += valEdi2[j]*empresa.getPrecioLitro();
                    }

                    arregloEdificio[id].setConsumo(gastoEdificio);

                    
                    id++;
                }
                
                
                i++;

            }
            bufferedReader.close();
        }catch(IOException ex){
            System.out.println("error");
        }    

//*****************************************************************************************************
    //Se hace un doble for para recorrer todos los caminos de todos los nodos hacia todos los nodos,
    // mientras se recorren, se va actualizando los valores de utilidad de un nodo hacia los otros.
    //Una vez terminado el recorrido, se comparan las utilidades y se guarda la menor de ellas.
    //Para los print, se vuelve a recorrer los caminos mas cortos y asi se van calculando las utilidades
    // entre el nodo menor con el resto del mapa.
    //Para acceder a la informacion de cuantos camiones cisterna y camionetas se usaron, se vuelve a los 
    //arreglos de cada objeto y se imprimen.
        
        for(i = 0; i<pais.getmaxVertices(); i++){
            int cantKm = 0;
            int gastoCamiones = 0;
            int consumoCiudad = arregloCasa[i].getConsumo()+arregloEdificio[i].getConsumo();
            arregloCiudad[i].setUtilidad(consumoCiudad);
            arregloCiudad[i].setUtilidadLocal(consumoCiudad);
            for(int l = 0; l<pais.getmaxVertices(); l++){
                if(i == l){
                    continue;
                }
                lista = pais.shortestPath(i, l, matriz);
                int a = 0;
                int k = 1;
                
                while(a<lista.size() && k<lista.size()){
                    cantKm+=pais.edgeWeight(lista.get(a), lista.get(k), matriz);
                    a++;
                    k++;
                }
                gastoCamiones = cantKm*vehiculo.getPKm()*(arregloCisterna[l].getCantCamiones()+arregloCamioneta[l].getCantCamioneta());
                arregloCiudad[i].actUtilidad(-gastoCamiones);
                
            }

            ciudad.setCantKm(cantKm);
            

        }
        int utilidadMenor = 10000000;
        int idMenor = 0;
        for(i = 0; i<pais.getmaxVertices(); i++){
            if(arregloCiudad[i].getUtilidad()<utilidadMenor){
                utilidadMenor = arregloCiudad[i].getUtilidad();
                idMenor = arregloCiudad[i].getId();
            }
        }

        System.out.println("La ciudad optima es: "+idMenor);
        for(i = 0; i<pais.getmaxVertices(); i++){
            System.out.println("ciudad "+i+":");
            if(i == idMenor){
                System.out.println("-Utilidad: "+arregloCiudad[i].getUtilidadLocal());
                System.out.println("-Se utilizaron "+arregloCisterna[i].getCantCamiones()+" camiones cisterna y "+arregloCamioneta[i].getCantCamioneta()+" camionetas");
            }
            else{
                int cantKm = 0;

                
                lista = pais.shortestPath(idMenor, i, matriz);
                int a = 0;
                int k = 1;
                
                while(a<lista.size() && k<lista.size()){
                    cantKm+=pais.edgeWeight(lista.get(a), lista.get(k), matriz);
                    a++;
                    k++;
                }
                int nuevaUtilidad = arregloCiudad[i].getUtilidadLocal()-cantKm*vehiculo.getPKm();
                arregloCiudad[i].setUtilidad(nuevaUtilidad);
                System.out.println("-Utilidad: "+arregloCiudad[i].getUtilidad());
                System.out.println("-Se utilizaron "+arregloCisterna[i].getCantCamiones()+" camiones cisterna y "+arregloCamioneta[i].getCantCamioneta()+" camionetas");
                }
            }
        }
           

}
