package grafo;

import java.util.Stack;

import java.util.TreeSet;
import java.util.Vector;


public abstract class Grafo {

	private Vector<Vertice> nodos;
	
	
	/**
	 * Revisa si el nodo representado por <code>nodoNumero</code> ya existe en el grafo.
	 * 
	 * En caso de que el grafo no contenga el nodo especificado, se agrega a la lista de nodos del grafo
	 * @param nodoNumero El número del nodo a agregar 
	 * @return El nodo que fue agregado al grafo
	 */
	public Vertice procesarAgregar(int nodoNumero){
		if(existe(nodoNumero)){
			return obtenerNodo(nodoNumero);
		}else{
			Vertice nuevo=new Vertice(nodoNumero);
			add(nuevo);
			return nuevo;
		}
	}

	/**
	 * Revisa la existencia de un nodo dentro del grafo
	 * @param numeroNodo El número del nodo a buscar
	 * @return true si el nodo existe, false en caso contrario 
	 */
	public boolean existe(int numeroNodo){
		return obtenerNodo(numeroNodo)!=null;
	}

	/*
	 * Agregar un nodo al grado
	 * @param n El nodo que ser� agregado al grafo
	 */
	public void add(Vertice n){
		this.nodos.add(n);
	}
	
	
	/**
	 * Contruye un camino entre el nodo inicial y el nodo final usando un algoritmo de DFS y backtracking
	 * @param inicio El nodo inicial del recorrido
	 * @param fin El nodo final del recorrido
	 * @return Un camino entre el nodo inicial y el nodo final, en caso de no poder conseguir un camino
	 * entre los dos nodos, devuelve null;
	 */
	public Vector<Vertice> DFS(int inicio,int fin){
		
		TreeSet<Vertice> visitados=new TreeSet<Vertice>();
		Stack<Vertice> porVisitar=new Stack<Vertice>();
		
		Vector<Vertice> resultado=null;
		
		Vertice nodoInicial = obtenerNodo(inicio);
		porVisitar.push(nodoInicial); //el primer nodo a visitar es inicio
		
		boolean listo=false;
		Vertice nodofin=null;
		while(porVisitar.size()!=0 && !listo){
			Vertice actual=porVisitar.pop();
			visitados.add(actual);
			TreeSet<Vertice> vecinos=actual.getVecinos(); //obtenemos los vecinos del nodo
			
			for(Vertice n:vecinos){
				if(!visitados.contains(n)){ //si el vecino no ha sido visitado lo encolamos
					n.setPredecesor(actual);
					if(n.getLlave()==fin){
						listo=true;
						nodofin=n;
						break;
					}
					porVisitar.add(0,n);
				}
			}
		}
		
		if(listo){
			Vertice nodoActual=nodofin;
			resultado=new Vector<Vertice>();
			while(nodoActual!=null){
				resultado.add(nodoActual);
				nodoActual=nodoActual.getPredecesor();
			}
		}
		return resultado;
		
	}

	public Vertice obtenerNodo(int nodoNumero){
		for(Vertice nodo:nodos){
			if(nodo.getLlave()==nodoNumero){
				return nodo;
			}
		}
		return null;
	}
	
	
	
}
