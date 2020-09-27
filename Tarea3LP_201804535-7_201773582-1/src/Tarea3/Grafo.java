package tarea3;
import java.util.ArrayList;

public interface Grafo{
//nNodes: cantidad de nodos en el grafo.
    int nNodes = 0;
//nEdges: cantidad de arcos en el grafo.
    int nEdges = 0;
//Las funciones estan comentadas en la clase Pais.java.
    public int edgeWeight(int id1, int id2, int [][] matrix);
    public ArrayList<Integer> shortestPath(int nodo1, int nodo2, int[][] matrix);
    public void addEdge(int id1, int id2, int dist, int [][] matrix);
    public void addNode(int id, int[][] matrix);
}
