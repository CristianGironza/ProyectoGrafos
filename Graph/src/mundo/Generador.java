package mundo;

import java.awt.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import grafo.Grafo;
import grafo.matriz.GrafoMatriz;
import gui.Principal;

/**
 * Clase encargada de generar laberintos de forma semi-aleatoria.
 * 
 * @author José Rafael Carrero León <a href="mailto:josercl@gmail.com">&lt;josercl@gmail.com&gt;</a>
 * @since 1.6 
 *
 */
public class Generador {

	/**
	 * Numero de filas del laberinto
	 */
	private int filas;
	
	/**
	 * Numero de columnas del laberinto
	 */
	private int columnas;
	
	private Casilla[][] casillas;
	private Principal parent;
	
	private final int ARRIBA=1;
	private final int ABAJO=2;
	private final int IZQUIERDA=3;
	private final int DERECHA=4;
	
	private GrafoMatriz grafo ; 
	
	private static MessageFormat formatter = new MessageFormat("");
	public static ResourceBundle resource = ResourceBundle.getBundle("resources.textos", Locale.getDefault()); 
	
	/**
	 * Crea el objeto generador
	 * @param parent Ventana principal donde se muestra los laberintos a resolver
	 * @param filas El número de filas del laberinto
	 * @param columnas El número de columnas del laberinto
	 */
	public Generador(Principal  parent,int filas,int columnas){
		this.parent=parent;
		this.filas=filas;
		this.columnas=columnas;
		
		init();
	}
	
//	public ResourceBundle getResource() {
//		return resource;
//	}
//
//	public void setResource(ResourceBundle resource) {
//		this.resource = resource;
//	}

	
	
	
	/**
	 * Inicializa el arreglo que mantiene las casillas del laberinto
	 */
	public final void init(){
		this.casillas=new Casilla[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				casillas[i][j] = new Casilla(i * columnas + j);
			}
		}
	}
	
	/**
	 * Genera un laberinto de forma semi-aleatoria.
	 * 
	 * El algoritmo hace uso de DFS (Depth First Search) con backtracking,
	 * asegurando que se puede llegar a cualquier casilla del laberinto sin importar la casilla de inicio
	 */
	@SuppressWarnings("unchecked")
	public void generar(){

		//Conjuntos usados en el algoritmo de generacion
		TreeSet<Casilla> visitados=new TreeSet<Casilla>();
		List<Casilla> porVisitar=new ArrayList<Casilla>();
		
		porVisitar.add(0,casillas[0][0]); //el primer nodo a visitar es la casilla[0][0]
		
		int filaActual=0,columnaActual=0;
		Casilla vecino=null;
		
		//El arreglo direcciones contiene las posibles direcciones que se pueden tomar a partir de cualquier casilla
		//cada lista de direcciones tendrá a lo sumo 4 valores: arriba,abajo,izquierda y derecha
		List<Integer> direcciones[]=new ArrayList[filas*columnas];
		for(int i=0;i<filas;i++){
			for(int j=0;j<columnas;j++){
				int numero=i*columnas+j;
				direcciones[numero]=new ArrayList<Integer>();
				
				if(i!=0){ //si no estoy en la primera fila, puedo ir hacia arriba
					direcciones[numero].add(ARRIBA);
				}
				if(i!=filas-1){ //si no estoy en la ultima fila, puedo ir hacia abajo
					direcciones[numero].add(ABAJO);
				}
				if(j!=0){ //si no estoy en la primera columna, puedo ir hacia la izquierda
					direcciones[numero].add(IZQUIERDA);
				}
				if(j!=columnas-1){ //si no estoy en la ultima columna, puedo ir hacia la derecha
					direcciones[numero].add(DERECHA);
				}
				//Desordenamos el arreglo de posiciones para tener aleatoriedad en las direcciones tomadas en el recorrido
				Collections.shuffle(direcciones[numero]);
			}
		}
		
		//Mientras queden nodos por visitar en la pila seguimos el recorrido
		while(porVisitar.size()!=0){
			Casilla actual=porVisitar.get(0); //Obtenemos la primera casilla de la pila para empezar el recorrido
			visitados.add(actual); //marcamos la casilla actual como visitada
			
			int numero=actual.getNumero();
			filaActual=numero/columnas;
			columnaActual=numero%columnas;
			
			vecino=actual;
			while(visitados.contains(vecino) && direcciones[numero].size()!=0){
				int direccionElegida=direcciones[numero].get(0); //obtenemos la direccion del primer vecino de la casilla actual
				direcciones[numero].remove(0); //eliminamos la direccion elegida para no elegirla de nuevo en iteraciones siguientes
				switch(direccionElegida){
					case ARRIBA:
						vecino=casillas[filaActual-1][columnaActual];
						if(!visitados.contains(vecino)){
							actual.setVecinoSup(true);
							vecino.setVecinoInf(true);
							porVisitar.add(0,vecino); //agregamos el vecino elegido a la lista de nodos por visitar
						}
						break;
					case ABAJO:
						vecino=casillas[filaActual+1][columnaActual];
						if(!visitados.contains(vecino)){
							actual.setVecinoInf(true);
							vecino.setVecinoSup(true);
							porVisitar.add(0,vecino);
						}
						break;
					case IZQUIERDA:
						vecino=casillas[filaActual][columnaActual-1];
						if(!visitados.contains(vecino)){
							actual.setVecinoIzq(true);
							vecino.setVecinoDer(true);
							porVisitar.add(0,vecino);
						}
						break;
					case DERECHA:
						vecino=casillas[filaActual][columnaActual+1];
						if(!visitados.contains(vecino)){
							actual.setVecinoDer(true);
							vecino.setVecinoIzq(true);
							porVisitar.add(0,vecino);
						}
						break;
				}
			}
			//si la casilla actual no tiene mas vecinos sin recorrer
			//la eliminamos de la lista de nodos por visitar
			if(direcciones[actual.getNumero()].size()==0){ 
				porVisitar.remove(actual);
			}
		}
		
		//guardamos el laberinto generado en un archivo temporal y lo cargamos en la pantalla principal
		File archivo=new File(System.getProperty("java.io.tmpdir")+File.separator+"lab.txt");
		guardar(archivo, casillas, 0, filas*columnas-1);
		parent.leerArchivo(archivo,false);
	}
	
	
	
	public  void guardar(File archivo, Casilla[][] casillas, int inicio,
			int fin) {
		int filas = casillas.length;
		int columnas = casillas[0].length;
		
		if (!archivo.isFile()) {
			try {
				if (!archivo.createNewFile()) {
					mostrarMensaje(null, mensajes("mensajes.error.archivo_no_crear", new Object[]{archivo.getName()}));
				}
			} catch (IOException e) {
				mostrarMensaje(null, mensajes("mensajes.error.archivo_no_crear",new Object[]{archivo.getName()}));
			}
		}
		
		if (archivo.canWrite()) {
			Casilla c = null;
			try {
				PrintStream ps = new PrintStream(new FileOutputStream(archivo));
				ps.println(filas);
				ps.println(columnas);
				ps.println(inicio);
				ps.println(fin);
				String str = null;
				for (int i = 0; i < filas; i++) {
					for (int j = 0; j < columnas; j++) {
						c = casillas[i][j];
						str = c.getNumero() + "";
						if (c.tieneVecinoSup()) {
							str += " " + casillas[i - 1][j].getNumero();
						}
						if (c.tieneVecinoIzq()) {
							str += " " + casillas[i][j - 1].getNumero();
						}
						if (c.tieneVecinoDer()) {
							str += " " + casillas[i][j + 1].getNumero();
						}
						if (c.tieneVecinoInf()) {
							str += " " + casillas[i + 1][j].getNumero();
						}
						ps.println(str);
					}
				}

			} catch (IOException e) {
				mostrarMensaje(null, mensajes(
						"mensajes.error.archivo_no_guardar",
						new Object[] { archivo.getName() }));
			}
		} else {
			mostrarMensaje(null, mensajes(
					"mensajes.error.archivo_no_permisos",
					new Object[] { archivo.getName() }));
		}
	}


	
	public static void mostrarMensaje(Component parent, String s) {
		JOptionPane.showMessageDialog(parent, s, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public static String mensajes(String key, Object[] argumentos) {
		formatter.applyPattern(resource.getString(key));
		return formatter.format(argumentos);
	}
	
	
	
	
	
	/**
	 * Retorna el numero de filas que tendrá el laberinto generado
	 * @return cantidad filas que tendrá el laberinto a generar
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * Establece el número de filas del laberinto
	 * @param filas Cantidad de filas que tendrá el laberinto generado
	 */
	public void setFilas(int filas) {
		this.filas = filas;
	}

	/**
	 * Retorna el numero de columnas que tendrá el laberinto generado
	 * @return cantidad de columnas que tendrá el laberinto generado
	 */
	public int getColumnas() {
		return columnas;
	}

	/**
	 * Establece el número de columnas del laberinto
	 * @param columnas cantidad de columnas que tendrá el laberinto generado
	 */
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
	
	
}
