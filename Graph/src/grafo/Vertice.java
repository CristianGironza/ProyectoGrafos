package grafo;

import java.util.TreeSet;

public class Vertice<T> implements IVertice<T> {

	private boolean marca;

	private int llave; 
	
	private T elemento;

	private IVertice<T> relacion;

	private Vertice<T> predecesor;
	
	private TreeSet<Vertice<T>> vecinos;
	
	public Vertice(T elemento) {
		super();
		this.elemento = elemento;
		this.vecinos = new TreeSet<Vertice<T>>();
	}

	/**
	 * @return the marca
	 */
	public boolean isMarca() {
		return marca;
	}

	/**
	 * @param marca
	 *            the marca to set
	 */
	public void setMarca(boolean marca) {
		this.marca = marca;
	}

	public int getLlave() {
		return llave;
	}

	public void setLlave(int llave) {
		this.llave = llave;
	}

	/**
	 * @return the elemento
	 */
	public T getElemento() {
		return elemento;
	}

	/**
	 * @param elemento
	 *            the elemento to set
	 */
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	/**
	 * @return the relacion
	 */
	public IVertice<T> getRelacion() {
		return relacion;
	}

	/**
	 * @param relacion
	 *            the relacion to set
	 */
	public void setRelacion(IVertice<T> relacion) {
		this.relacion = relacion;
	}

	public Vertice<T> getPredecesor() {
		return predecesor;
	}

	public void setPredecesor(Vertice<T> predecesor) {
		this.predecesor = predecesor;
	}

	@Override
	public boolean marcado() {
		// TODO Auto-generated method stub
		return marca;
	}

	@Override
	public void marcar() {
		// TODO Auto-generated method stub
		marca = !marca;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return elemento.toString();
	}

	/**
	 * Devuelve un conjunto de los nodos adyacentes en el grafo.
	 * El número máximo de vecinos para un nodo dentro de un laberinto siempre será 4
	 * @return Un conjunto de los nodos adyacentes en el grafo
	 */
	public TreeSet<Vertice<T>> getVecinos() {
		return vecinos;
	}

	/**
	 * <p>Compara 2 nodos para ver si son iguales.</p>
	 * <p>Un nodo se considera igual a otro nodo si el número que los representa es el mismo</p>
	 * @param nodo2
	 * @return true si los numeros representativos de los nodos son iguales, false en caso contrario
	 */
	public boolean equals(Vertice<T> nodo2){
		return this.llave==nodo2.getLlave();
	}
	
	/**
	 * Compara 2 nodos para establecer un orden entre los mismos
	 * @param otroNodo El nodo con el que se realiza la comparación
	 * @return <ul><li>&lt;1 si el nodo es menor que otroNodo</li>
	 * <li>0 si son iguales</li>
	 * <li>&gt1 si otroNodo es mayor 
	 */
	public int compareTo(Vertice<T> otroNodo) {
		return llave-otroNodo.getLlave();
	}

	
}
