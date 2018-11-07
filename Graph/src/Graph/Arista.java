package Graph;

public class Arista {
    private Vertice origen;
    private Vertice destino;
    private double distancia;
 
    public Arista(Vertice origen, Vertice destino, double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }
 
    public Vertice darOrigen() {
        return origen;
    }
 
    public void asignarOrigen(Vertice origen) {
        this.origen = origen;
    }
 
    public Vertice darDestino() {
        return destino;
    }
 
    public void asignarDestino(Vertice destino) {
        this.destino = destino;
    }
 
    public double darDistancia() {
        return distancia;
    }
 
    public void asignarDistancia(double distancia) {
        this.distancia = distancia;
    }
 

 
}