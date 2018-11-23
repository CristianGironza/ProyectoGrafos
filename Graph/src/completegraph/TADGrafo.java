package completegraph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class TADGrafo {

	private ArrayList<ArrayList<Arista>> grafo;
		
	public TADGrafo(){
		grafo = new ArrayList<>();
		
	}
	
	public void agregarNodo(){
		grafo.add(new ArrayList<>());
		
	}
	
	public void agregarArista(int ini, int fin,int peso){
		if(!grafo.get(ini).contains(fin)){
			grafo.get(ini).add(new Arista(fin,peso));
		}
	}
	
	public void prim(){
		boolean[] tomados = new boolean[grafo.size()];
		cmpPrim cmpP = new cmpPrim();
		PriorityQueue<Arista> pq = new PriorityQueue<>(cmpP);
		
		primAuxiliar(0, tomados, pq);
		int mstCost = 0;
		while(!pq.isEmpty()){
			Arista frnt = pq.poll();
			if(!tomados[frnt.getDestino()]){
				mstCost+=frnt.getPeso();
				primAuxiliar(frnt.getDestino(), tomados, pq);
			}
		}
		
		
	}
	
	private void primAuxiliar(int v, boolean[] tomados, PriorityQueue<Arista> pq){
		tomados[v]=true;
		for(int j = 0; j<grafo.get(v).size();j++){
			Arista aux = grafo.get(v).get(j);
			if(!tomados[aux.getDestino()]){
				pq.add(new Arista(aux.getDestino(), aux.getPeso()));
			}
		}
	}
	
	public int dikstra(int s, int f){
		ArrayList<Integer> dst = new ArrayList<>();
		for(int i = 0; i <grafo.size();i++){
			dst.add(Integer.MAX_VALUE);
		}
		dst.set(0, 0);
		
		cmp comp = new cmp();
		PriorityQueue<Arista> pq = new PriorityQueue<>(comp);
		pq.add(new Arista(s,0));
		
		while(!pq.isEmpty()){
			Arista frnt = pq.poll();
			
			if(frnt.getPeso()>dst.get(frnt.getDestino())){continue;}
			
			for(int j = 0; j < grafo.get(frnt.getDestino()).size();j++){
				Arista aux = grafo.get(frnt.getDestino()).get(j);
				if(dst.get(frnt.getDestino())+aux.getPeso()< dst.get(aux.getDestino())){
					dst.set(aux.getDestino(),dst.get(frnt.getDestino())+aux.getPeso());
					pq.add(new Arista(aux.getDestino(),dst.get(aux.getDestino())));
				}
			}
		}
		
		return dst.get(f);
	}
	
	/**
	 * retorna los caminos mas cortos entre cada pareja de nodos.
	 */
	public int[][] floydWarshall(){
		int[][] matFW = new int[grafo.size()][grafo.size()];
		for(int i = 0; i<matFW.length;i++){
			for(int j = 0; j< matFW.length;j++){
				if(i!=j){
					matFW[i][j]=99999;	
				}
			}
		}
		
		for(int i = 0; i<grafo.size();i++){
			for(int j = 0; j< grafo.get(i).size();j++){
				matFW[i][grafo.get(i).get(j).getDestino()]=grafo.get(i).get(j).getPeso();
				matFW[grafo.get(i).get(j).getDestino()][i]=grafo.get(i).get(j).getPeso();
			}
		}
		
		for(int k = 0; k < grafo.size(); k++){
			for(int i = 0; i < grafo.size();i++){
				for(int j = 0; j < grafo.size(); j++){
					matFW[i][j]=Math.min(matFW[i][j], matFW[i][k]+matFW[k][j]);
				}
			}
		}
		
		return matFW;
	}
	
	/**
	 * bfs que en visitados nos deja la capa a la que se visita.
	 * 
	 * @param ini
	 */
	public void bfs(int ini){
		ArrayList<Integer> visitados = new ArrayList<>();
		for(int i = 0; i < grafo.size();i++){
			visitados.add(Integer.MAX_VALUE);
		}
		visitados.set(ini,0);
		Queue<Integer> cola = new ArrayDeque<>();
		cola.add(ini);
		
		while(!cola.isEmpty()){
			int frnt = cola.poll();
			//Procedimiento durante la visita: Imprimir
			System.out.print(frnt);
			
			for(int j = 0; j < grafo.get(frnt).size();j++){
				Arista aux = grafo.get(frnt).get(j);
				if(visitados.get(aux.getDestino())==Integer.MAX_VALUE){
					visitados.set(aux.getDestino(), visitados.get(frnt)+1);
					cola.add(aux.getDestino());
				}
			}
		}
		
	}
	
	/**
	 * dfs para un grafo.
	 * @param ini
	 */
	public void dfs(int ini){
		ArrayList<Boolean> visitados = new ArrayList<>();
		for(int i = 0; i < grafo.size();i++){
			visitados.add(false);
		}
		dfsAuxiliar(ini, visitados);
	}
	
	private void dfsAuxiliar(int ini, ArrayList<Boolean> visitados){
		visitados.set(ini, true);
		//Procedimiento despues durante la visita: Imprimir
		System.out.print(ini);
		
		for(int j = 0; j < grafo.get(ini).size();j++){
			Arista aux = grafo.get(ini).get(j);
			if(visitados.get(aux.getDestino())==false){
				dfsAuxiliar(aux.getDestino(), visitados);
			}
		}
		
	}

	public ArrayList<ArrayList<Arista>> getGrafo() {
		return grafo;
	}

	public void setGrafo(ArrayList<ArrayList<Arista>> grafo) {
		this.grafo = grafo;
	}
	
	
	
}

class cmp implements Comparator<Arista>{

	@Override
	public int compare(Arista o1, Arista o2) {		
		return o1.getPeso()<o2.getPeso()?1:-1;
	}
	
}

class cmpPrim implements Comparator<Arista>{

	@Override
	public int compare(Arista o1, Arista o2) {	
		int w =  o1.getPeso()>o2.getPeso()?1:o1.getPeso()==o2.getPeso()?0:-1;
		if(w!=0){
		 return 0;
		}else{
			return o1.getDestino()>o2.getDestino()?1:o1.getDestino()==o2.getDestino()?0:-1;
		}
		
	}
	
}

class Arista{
	private int destino;
	private int peso;
	public Arista(int destino, int peso){
		this.destino = destino;
		this.peso = peso;
	}
	public int getDestino() {
		return destino;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
}
