package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Graph.Grafo;
import junit.framework.TestCase;

class TestGraph extends TestCase{

	private Grafo test;
	
	private void setUpCase1() {
		test = new Grafo(5);
	}
	
	private void setUpCase2() {
		test = new Grafo(4);
		test.agregar(1, 2);
		test.agregar(3, 2);
		test.agregar(2, 0);
	}
	
	@Test
	void testInicializar1() {
		setUpCase1();
		Grafo other = new Grafo(test.getSize());
		other.setSalidas(test.getSalidas());
		other.inicializar();
		boolean mistake = false;
		for(int i=0; i< test.getSize() && !mistake; i++){
            for(int j=0; j< test.getSize(); j++){
                if(test.getSalidas()[i][j]!=other.getSalidas()[i][j]) {
                	mistake = true;
                }
            }            
        }
		assertTrue(!mistake);
	}
	
	@Test
	void testInicializar2() {
		setUpCase2();
		Grafo other = new Grafo(test.getSize());
		other.agregar(1, 2);
		other.agregar(3, 2);
		other.agregar(2, 0);
		test.inicializar();
		boolean mistake = false;
		for(int i=0; i< test.getSize() && !mistake; i++){
            for(int j=0; j< test.getSize(); j++){
                if(test.getSalidas()[i][j]!=other.getSalidas()[i][j]) {
                	mistake = true;
                }
            }            
        }
		assertFalse(!mistake);
	}
	
	@Test
	void agregarTest1() {
		setUpCase1();
		test.agregar(0, 0);
		test.agregar(1, 2);
		boolean conection = false;
		for(int i=0; i< test.getSize() && !conection; i++){
            for(int j=0; j< test.getSize(); j++){
                if(test.getSalidas()[i][j]!=0) {
                	conection = true;
                }
            }            
        }
		assertFalse(!conection);
	}
	
	@Test
	void agregarTest2() {
		setUpCase2();
		test.agregar(1, 2);
		assertTrue(test.getSalidas()[1][2]==2);
	}
	
	@Test 
	void removerTest1() {
		setUpCase1();
		test.remover(1, 2);
		assertTrue(test.getSalidas()[1][2]==0);
	}
	
	@Test
	void removerTest2() {
		setUpCase2();
		test.remover(1, 2);
		assertTrue(test.getSalidas()[1][2]==0);
	}
	
	
}
