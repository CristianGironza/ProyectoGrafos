package grafo.lista;

import java.util.ArrayList;

import grafo.Vertice;
import grafo.*;

public class AristasLista<T> {

	private AristaLista<T>[] rutas;
	
	private double[] peso;

	public AristasLista(AristaLista<T>[] rutas, double[] peso) {
		this.rutas = rutas;
		setPeso(peso);
	}

	/**
	 * @return the rutas
	 */
	public AristaLista<T>[] getRutas() {
		return rutas;
	}

	/**
	 * @param rutas the rutas to set
	 */
	public void setRutas(AristaLista<T>[] rutas) {
		this.rutas = rutas;
	}

	/**
	 * @return the peso
	 */
	public double[] getPeso() {
		double[] pesos = new double[rutas.length];
		int i = 0;
		while(i< rutas.length){
			pesos[i] = rutas[i].getPeso();
		}
		return pesos;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double[] peso) {
		int i = 0;
		while(i< rutas.length){
			rutas[i].setPeso(peso[i]);
			i++;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = "";
		for (int i = 0; i < 1; i++) {
			s += rutas[i].toString() + "\n";
		}
		return s;
	}
	

}
