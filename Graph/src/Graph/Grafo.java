package Graph;

import java.util.ArrayList;
import java.util.List;
 

public class Grafo {
	private int [][] salidas;
	private int size;
    private List<Vertice> vertices;
    
    public Grafo(int size)
    {
    	this.size=size;
    	this.salidas = new int[size][size];
    }
 
    public void inicializar()
    {
        for(int i=0; i< size; i++){
            for(int j=0; j< size; j++){
                salidas[i][j] = 0;
            }            
        }
    }
 
    
    public void agregar(int i, int j){
        salidas[i][j] += 1;
    }
    
    public void remover(int i, int j){
        if(salidas[i][j]>0)
            salidas[i][j] -= 1;
    }
 
}