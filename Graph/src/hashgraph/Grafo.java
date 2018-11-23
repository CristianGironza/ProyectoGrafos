package hashgraph;

import java.awt.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Grafo<K> {
	HashMap<K, HashMap<K, Integer>>grafo;
	
	public Grafo() {
		grafo = new HashMap<K,HashMap<K, Integer>>();
	}
	
	public void crearVertice(K vertice, HashMap<K, Integer> relacion){
		Set<K> llaves = relacion.keySet();
		if(grafo.containsKey(vertice)) {
			for(K llave:llaves) {
				grafo.get(vertice).put(llave, relacion.get(llave));
			}
			for(K llave:llaves) {
				crearVertice(llave, new HashMap<K,Integer>());
			}
		}else {
			grafo.put(vertice, relacion);
			for(K llave:llaves) {
				crearVertice(llave, new HashMap<K,Integer>());
			}
		}
	}
	
	public void eliminarVertice(K vertice) {
		if(grafo.containsKey(vertice)) {
			Set<K> llaves = grafo.keySet();
			for(K llave:llaves) {
				if(grafo.get(llave).containsKey(vertice)) {
					grafo.get(llave).remove(vertice);
				}
			}
			grafo.remove(vertice);
		}
	}
	
	public void BFS(K vertice) {
		if(grafo.containsKey(vertice)) {
			Set<K> llaves = grafo.keySet();
			HashMap<K, Integer> aux = new HashMap<K, Integer>();
			for(K llave:llaves) {
				aux.put(llave, 0);
			}
			Queue<K> cola = new LinkedList<K>();
			cola.offer(vertice);
			aux.put(vertice, 1);
			while(!cola.isEmpty()){
				K valor = cola.remove();
				Set<K> llaves2 = grafo.get(valor).keySet();
				for(K llave2:llaves2) {
					if(aux.get(llave2)==0) {
						cola.offer(llave2);
						aux.put(llave2, 1);
					}
				}
			}
		}
	}
	
	public void DFS(K vertice) {
		if(grafo.containsKey(vertice)) {
			Set<K> llaves = grafo.keySet();
			HashMap<K, Integer> aux = new HashMap<K, Integer>();
			for(K llave:llaves) {
				aux.put(llave, 0);
			}
			Stack<K> pila = new Stack<K>();
			pila.push(vertice);
			aux.put(vertice, 1);
			while(!pila.isEmpty()){
				K valor = pila.pop();
				Set<K> llaves2 = grafo.get(valor).keySet();
				for(K llave2:llaves2) {
					if(aux.get(llave2)==0) {
						pila.push(llave2);
						aux.put(llave2, 1);
					}
				}
			}
		}
	}
	
	public void BellmanFords(K vertice) {
		Set<K> llaves = grafo.keySet();
		HashMap<Integer, K> aux = new HashMap<Integer, K>();
		int x = 0;
		int inicio =0;
		for(K llave:llaves) {
			if(llave.equals(vertice)) {
				inicio = x;
			}
			aux.put(x,llave);
			x++;
		}
		int[][] matriz = new int[grafo.size()][grafo.size()];
		for(int i=0; i<matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = Integer.MAX_VALUE;
			}
		}
		matriz[inicio][inicio]=0;
		Queue<Integer> cola = new LinkedList<Integer>();
		cola.offer(inicio);
		while(!cola.isEmpty()) {
			int proviene = cola.remove();
			Set<K> llaves2 = grafo.get(aux.get(inicio)).keySet();
			for(K llave2:llaves2) {
				
			}
		}
		
		
	} 
	
	public static void main(String[] args) {
		Grafo<Character> myg = new Grafo<>();
		HashMap<Character, Integer> aux = new HashMap<Character, Integer>();
		aux.put('5', 4);
		aux.put('9',8);
		myg.crearVertice('1', aux);
		myg.DFS('1');
	}
}
