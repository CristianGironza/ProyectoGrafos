package grafo.matriz;

import java.util.ArrayList;
import grafo.*;

public class AristasMatriz<T>{
	
	private AristaMatriz<T>[] rutas;

	public AristasMatriz(AristaMatriz<T>[] rutas, double[] peso) {
		this.rutas = rutas;
		setPeso(peso);
	}
	
	/**
	 * @return the rutas
	 */
	public AristaMatriz<T>[] getRutas() {
		return rutas;
	}

	/**
	 * @param rutas the rutas to set
	 */
	public void setRutas(AristaMatriz<T>[] rutas) {
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
	
	

}
