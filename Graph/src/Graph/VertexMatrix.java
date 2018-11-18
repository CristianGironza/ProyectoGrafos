package Graph;

public class VertexMatrix {
	public int label; // to hold the name / number of the vertex
	VertexMatrix[] neighbors; // array of neighbors, adjacency list
	int[] walls; //array of walls, -1 edge of maze, 0 broken wall, 1 intact wall, 4 entry/exit
	int color; // white 0, grey 1, black 2
	VertexMatrix pi; // parent
	int startTime; // found time when it turns grey
	int endTime; // when it turns black
	int distance; // ADDED
	boolean inPath; //true if the Vertex is in the solution path and false if it is not
	int traverseOrder; //the order in which the vertex is traversed while solving

	
	public VertexMatrix(int lab) {
		label = lab;
		/*
		 * index correspondence for neighbors 0 = up 1 = right 2 = down 3 = left
		 */
		neighbors = new VertexMatrix[4];
		walls = new int[4];
		/*
		 * index correspondence for walls 0 = up wall;  1 = right wall; 
		 * 2 = down wall;  3 = left wall
		 */
		setAllWallsIntact();
		startTime = Integer.MAX_VALUE; //start time = infinity
		endTime = Integer.MAX_VALUE; //end time = infinity
		pi = null;
		distance = 0; 
		inPath = false;
		traverseOrder = 0;
	}

	/*
	 * checks if all walls intact
	 */
	public boolean allWallsIntact() {
		for (int i = 0; i < walls.length; i++) {
			if (walls[i] == 0) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * sets all walls intact
	 */
	public void setAllWallsIntact() {
		for (int i = 0; i < walls.length; i++) {
			walls[i] = 1;
		}
	}

	
	/*
	 * Methods to break walls
	 */
	public void breakUpWall() {
		if (walls[0] != -1)
			walls[0] = 0;
	}

	public void breakRightWall() {
		if (walls[1] != -1)
			walls[1] = 0;
	}

	public void breakDownWall() {
		if (walls[2] != -1)
			walls[2] = 0;
	}

	public void breakLeftWall() {
		if (walls[3] != -1)
			walls[3] = 0;
	}

	
	/*
	 * Methods to set neighbors 
	 */
	public void setLeft(VertexMatrix v) {
		neighbors[3] = v;
	}

	public void setRight(VertexMatrix v) {
		neighbors[1] = v;
	}

	public void setUp(VertexMatrix v) {
		neighbors[0] = v;
	}

	public void setDown(VertexMatrix v) {
		neighbors[2] = v;
	}

	
	/*
	 * Methods to get neighbors 
	 */
	public VertexMatrix getLeft() {
		return this.neighbors[3];
	}

	public VertexMatrix getRight() {
		return this.neighbors[1];
	}

	public VertexMatrix getUp() {
		return this.neighbors[0];
	}

	public VertexMatrix getDown() {
		return this.neighbors[2];
	}

	/*
	 * finds the relationship between this vertex and vertex v
	 */
	public int vertexRelationship(VertexMatrix v) {
		if (getUp() != null && getUp().equals(v)) {
			return 0;
		} else if (getRight() != null && getRight().equals(v)) {
			return 1;
		} else if (getDown() != null && getDown().equals(v)) {
			return 2;
		} else { // if (getLeft().equals(v)){
			return 3;
		}
	}
}
