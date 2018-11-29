package aleatorio;

import javax.swing.*;

import grafo.Grafo;
import grafo.Vertice;
import grafo.matriz.GrafoMatriz;

import java.awt.*;
import java.util.ArrayList;


public class Principal extends JFrame{
	
	final static int paredes = 1;
	static int[][] laberinto;
	static int columnas = 41;
	static int filas = 31;
	static boolean existe = false;
	final static int vacio = 3;
	final static int camino = 2;
	final static int visitado = 4;
	static PanelLaberinto pl;
	static PanelBotones pb;
	public ArrayList<Vertice> vertices;
	public Grafo grafo;
	
	public Principal()
	{ 	
		vertices=new ArrayList<Vertice>();
		crearLaberinto();	
		extraerEtiqueta();
		crearGrafo();
		pb=new PanelBotones(this);
	}
	
	public void conectar()
	{
		ArrayList<Integer> ids =new ArrayList<Integer>();
		for (int i = 0; i < vertices.size(); i++) {
			ids.add(vertices.get(i).getEtiqueta());
		}
	}
	
	public void compararID()
	{
		int contador=0;
		for (int i = 0; i < laberinto.length; i++) {
			for (int j = 0; j < laberinto[0].length; j++) {
				if(laberinto[i][j]==1)
				{
					contador++;
				}
				if(laberinto[i][j]==3)
				{
					llenarVertices(contador);
					contador++;
				}
			}
		}
	}
	
	public void crearGrafo()
	{
		grafo = new GrafoMatriz<>(vertices);
	}
	
	public void extraerEtiqueta()
	{
		int contador=0;
		for (int i = 0; i < laberinto.length; i++) {
			for (int j = 0; j < laberinto[0].length; j++) {
				if(laberinto[i][j]==1)
				{
					contador++;
				}
				if(laberinto[i][j]==3)
				{
					llenarVertices(contador);
					contador++;
				}
			}
		}
	}
	
	public void llenarVertices(int etiqueta)
	{	
		String e = String.valueOf(etiqueta);
		Vertice v = null;
		v = new Vertice(e);
		System.out.println(v.getEtiqueta());
		vertices.add(v);
		System.out.println(vertices.size());
	}
	

	
	public void resolver()
	{
		resolverLaberintoEJ(1, 1);
		

		
		pl.resolver(laberinto);
	}
	
	   static void crearLaberinto() {
		   
       if (laberinto == null) {
    	   laberinto = new int[filas][columnas];
       }
           
       int i,j;
       int vacioc = 0; 
       int paredesc = 0;  
       int[] paredFila = new int[(filas*columnas)/2]; 
       int[] paredColumna = new int[(filas*columnas)/2];
       for (i = 0; i<filas; i++)  
           for (j = 0; j < columnas; j++)
               laberinto[i][j] = paredes;
       for (i = 1; i<filas-1; i += 2)  
           for (j = 1; j<columnas-1; j += 2) 
           {
               vacioc++;
               laberinto[i][j] = -vacioc;  
               if (i < filas-2) {  
                   paredFila[paredesc] = i+1;
                   paredColumna[paredesc] = j;
                   paredesc++;
               }
               if (j < columnas-2) { 
                   paredFila[paredesc] = i;
                   paredColumna[paredesc] = j+1;
                   paredesc++;
               }
           }
       existe = true;
       int r;
       for (i=paredesc-1; i>0; i--) {
           r = (int)(Math.random() * i); 
           quitarPared(paredFila[r],paredColumna[r]);
           paredFila[r] = paredFila[i];
           paredColumna[r] = paredColumna[i];
       }
       for (i=1; i<filas-1; i++)  
           for (j=1; j<columnas-1; j++)
               if (laberinto[i][j] < 0)
                   laberinto[i][j] = vacio;
   }
	
    synchronized static void quitarPared(int fila, int columna) 
    {
 
        if (fila % 2 == 1 && laberinto[fila][columna-1] != laberinto[fila][columna+1]) 
        {
            
            llenar(fila, columna-1, laberinto[fila][columna-1], laberinto[fila][columna+1]);
            laberinto[fila][columna] = laberinto[fila][columna+1];
        }
        else if (fila % 2 == 0 && laberinto[fila-1][columna] != laberinto[fila+1][columna]) 
        {
            
            llenar(fila-1, columna, laberinto[fila-1][columna], laberinto[fila+1][columna]);
            laberinto[fila][columna] = laberinto[fila+1][columna];

        }
}
	
    static void llenar(int fila, int columna, int reemplazar, int reemplazarcon) 
    {
        
        if (laberinto[fila][columna] == reemplazar) 
        {
            laberinto[fila][columna] = reemplazarcon;
            llenar(fila+1,columna,reemplazar,reemplazarcon);
            llenar(fila-1,columna,reemplazar,reemplazarcon);
            llenar(fila,columna+1,reemplazar,reemplazarcon);
            llenar(fila,columna-1,reemplazar,reemplazarcon);
        }
    }
    
    static boolean resolverLaberintoEJ(int fila, int columna) {

    if (laberinto[fila][columna] == vacio) {
        laberinto[fila][columna] = camino;    
        if (fila == filas-2 && columna == columnas-2)
            return true;  

        if ( resolverLaberintoEJ(fila-1,columna)  ||     
                resolverLaberintoEJ(fila,columna-1)  ||    
                resolverLaberintoEJ(fila+1,columna)  ||
                resolverLaberintoEJ(fila,columna+1) )
            return true;
        
        laberinto[fila][columna] = visitado; 


    }
    return false;
}

	public static void main(String[] args) {
		
//		int filas = Integer.parseInt(JOptionPane.showInputDialog("ingrse las filas ")); 
//		int colun = Integer.parseInt(JOptionPane.showInputDialog("ingrse las columnas "));
		
		Principal t = new Principal();
		t.pack(); 
		
		t.setSize(800,800);
		t.setLayout(new BorderLayout());
		pl=new PanelLaberinto(laberinto);
		t.add(pl, BorderLayout.CENTER);
		t.add(pb, BorderLayout.SOUTH);
		t.setVisible(true);

	}
    
    
}
