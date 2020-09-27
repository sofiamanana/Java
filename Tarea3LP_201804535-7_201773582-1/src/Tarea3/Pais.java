package tarea3;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Pais implements Grafo{
    int nNodes = 0, nEdges = 0;
    int matrix[][];

    public int getmaxVertices(){
        return nNodes;
    }

    public void setmaxVertices(int nNodes){
        this.nNodes = nNodes;
    }
    
    public int getmaxEdges(){
        return nEdges;
    }

    public void setmaxEdges(int nEdges){
        this.nEdges = nEdges;
    }
/*
Nombre: initMatriz.
Input: int lon.
Output: matriz[lon+1][lon+1].
Descripcion: guarda espacio en memoria para la matriz que se usara como base del grafo que representa la ciudad.
guarda una columna y una fila mas para poner el numero del nodo en esta.
*/
    public int[][] initMatriz(int lon){
        int i, j;
        int lon2 = lon+1;
        int matrix[][] = new int [lon2][lon2];
        for (i = 0; i < lon2; i++){
            for (j = 0; j < lon2; j++){
            matrix[i][j] = 0;
            }
        }
        return matrix;
    }
/*
Nombre: addNode.
Input: int id, int[][ matriz].
Output: void.
Descripcion: agrega el id del nodo a la matriz.
*/

    public void addNode(int id, int[][] matrix){
        matrix[0][id+1] = id;
        matrix[id+1][0] = id;       

    }
/*
Nombre: addEdge.
Input: int id1, ,int id2, int dist, int[][ matriz].
Output: void.
Descripcion: agrega el arco de distancia dist entre los nodos id e id2, en la matriz. 
*/
    public void addEdge(int id1, int id2, int dist, int [][] matrix){
        if(id1>=getmaxVertices()|| id2>=getmaxVertices()){
            System.out.println("indices fuera de rango****");
        }else{
            matrix[id1+1][id2+1] = dist;
            matrix[id2+1][id1+1] = dist;
        }
    }
/*
Nombre: edgeWeight.
Input: int id1, int id2, int[][ matriz].
Output: int.
Descripcion: retorna el peso del arco entre los nodos id1 e id2. 
*/
    public int edgeWeight(int id1, int id2, int [][] matrix){
        return matrix[id1+1][id2+1];
    }
/*
Nombre: findMinNodo.
Input: int[] distance, boolean visited[].
Output: int.
Descripcion: retorna el nodo con menor distancia (en el arreglo distance) y que no este visitado (en el arreglo visited). 
*/
    public int findMinNodo(int[] distance, boolean visited[]){
        int minNodo = -1;
        for(int i=0; i<distance.length; i++){
            if(!visited[i] &&(minNodo==-1 || distance[i]<distance[minNodo])){
                minNodo=i;
            }
        }
        return minNodo;
    }
/*
Nombre: shortestPath.
Input: int nodo1, int nodo2, int[][ matriz].
Output: ArrayList<Integer>.
Descripcion: retorna una lista con el camino mas corto entre los nodos nodo1 y nodo 2.
*/
    
    public ArrayList<Integer> shortestPath(int nodo1, int nodo2, int[][] matrix){
        int v = matrix.length;
        ArrayList<Integer> lista = new ArrayList<Integer>();
        lista.add(nodo1);
        boolean visited[] = new boolean[v-1];
        int distance[] = new int [v-1];
        distance[nodo1] = 0;

        for(int i=0; i<v-1; i++){
            if(i==nodo1){
                continue;
            }else{
                distance[i]=1000000000;
                visited[i] = false;
            }

        }
        for(int i=0; i<v-1; i++){

            int minNodo=findMinNodo(distance, visited);
            visited[minNodo]=true;
            

            for(int j=1; j<v; j++){

                if(matrix[minNodo+1][j]!=0 && !visited[j-1] && distance[minNodo]!=1000000000){
                    int newDist = distance[minNodo]+matrix[minNodo+1][j];
                    
                    if(newDist<distance[j-1]){

                        distance[j-1]=newDist;
                        if(lista.get(lista.size()-1)==nodo2){
                            break;
                        }
                        if((matrix[lista.get(lista.size()-1)+1][j])!=0){
                            
                            lista.add(j-1);

                        }else{   
                                       
                            lista.remove(lista.get(lista.size()-1));
                            lista.add(j-1);

                        }
                    }

                }
            }
            if(minNodo==nodo2){
                break;
            }
        }
        if(lista.contains(nodo2)){
            return lista;
        }else{
            lista.add(nodo2);
            return lista;
        }
    }
 
    

}