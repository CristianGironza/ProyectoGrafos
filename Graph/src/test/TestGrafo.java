package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Graph.GraphAdjacentList;
import Graph.Vertice;

class TestGrafo {

	private GraphAdjacentList graph; 
	
	private void setUp1() {
		ArrayList<Vertice> vertices = new ArrayList<>(); 
	
		vertices.add(new Vertice("1"));
	
		
		
		graph = new GraphAdjacentList(); 
		
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	
	
	

}
