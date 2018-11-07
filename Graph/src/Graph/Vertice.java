package Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
	

    private String salida;
    private List<Arista> aristas;
 
    public Vertice(String salida) {
        this.salida = salida;
    }
 
    public String darSalida() {
        return salida;
    }
 
    public void asignarSalida(String salida) {
        this.salida = salida;
    }
 
    public List<Arista> darAristas() {
        return aristas;
    }
 
    public void agregarArista(Arista arista) {
        if (aristas == null) {
            aristas = new ArrayList<>();
        }
        aristas.add(arista);
    }
 

}
