package grafo.lista;

import java.util.*;
import grafo.*;

public interface IGrafoLista<T> extends IGrafo<T> {

	AristaLista<T> dijsktraParcial(T inicio, T fin);
	
	AristasLista<T> dijsktra(VerticeLista<T> inicio);

	HashMap<T, AristasLista<T>> floydWarshall();

	Arista<T> getArista(T inicio, T fin);
		
	VerticeLista<T> darVertice(T llave);

}
