 package src;

public class Main {

	public static void main(String[] args) throws Exception {
		Graph<Character> floydGraph = new Graph<>(8); 
//		floydExample(floydGraph);
		
//		Graph<Integer> dijkstraGraph = new Graph<>(6);
//		dijkstraExample(dijkstraGraph);
		
		//exercise1_seminar();
		//exercise3_seminar();
		//exercise5_seminar();
		floydExample(floydGraph);
	}

	@SuppressWarnings("unused")
	private static void exercise1_seminar() throws Exception {
		//Create the graph
		Graph<Character> dijkstraGraph = new Graph<Character>(8);
		
		//Add the nodes to the graph
		dijkstraGraph.addNode('A');
		dijkstraGraph.addNode('B');
		dijkstraGraph.addNode('C');
		dijkstraGraph.addNode('D');
		dijkstraGraph.addNode('E');
		dijkstraGraph.addNode('F');
		dijkstraGraph.addNode('G');
		dijkstraGraph.addNode('H');
		
		//Set the edges 
		dijkstraGraph.addEdge('A', 'B', 3);
		dijkstraGraph.addEdge('A', 'H', 2);
		dijkstraGraph.addEdge('B', 'A', 2);
		dijkstraGraph.addEdge('B', 'D', 5);
		dijkstraGraph.addEdge('C', 'B', 1);
		dijkstraGraph.addEdge('C', 'H', 2);
		dijkstraGraph.addEdge('C', 'G', 6);
		dijkstraGraph.addEdge('D', 'F', 5);
		dijkstraGraph.addEdge('D', 'E', 3);
		dijkstraGraph.addEdge('F', 'E', 1);
		dijkstraGraph.addEdge('F', 'C', 3);
		dijkstraGraph.addEdge('G', 'F', 2);
		dijkstraGraph.addEdge('H', 'G', 1);
		dijkstraGraph.addEdge('H', 'A', 4);
		
		//Print the initial state of vector D and P
		dijkstraGraph.initDijkstra('C');
		printDijkstraMatrix(dijkstraGraph);
		
		//Apply Dijkstra with origin C
		dijkstraGraph.Dijkstra('C');
		
		//Print the final state of vector D and P
		printDijkstraMatrix(dijkstraGraph);
		
	}
	
	@SuppressWarnings("unused")
	private static void exercise5_seminar() throws Exception {
		//Create the graph
				Graph<Character> dijkstraGraph = new Graph<Character>(8);
				
				//Add the nodes to the graph
				dijkstraGraph.addNode('A');
				dijkstraGraph.addNode('B');
				dijkstraGraph.addNode('C');
				dijkstraGraph.addNode('D');
				dijkstraGraph.addNode('E');
				dijkstraGraph.addNode('F');
				dijkstraGraph.addNode('G');
				dijkstraGraph.addNode('H');
				
				//Set the edges 
				dijkstraGraph.addEdge('A', 'B', 3);
				dijkstraGraph.addEdge('A', 'H', 2);
				dijkstraGraph.addEdge('B', 'A', 2);
				dijkstraGraph.addEdge('B', 'H', 2);
				dijkstraGraph.addEdge('B', 'G', 2);
				dijkstraGraph.addEdge('C', 'A', 1);
				dijkstraGraph.addEdge('C', 'G', 2);
				dijkstraGraph.addEdge('C', 'D', 6);
				dijkstraGraph.addEdge('D', 'F', 5);
				dijkstraGraph.addEdge('D', 'E', 3);
				dijkstraGraph.addEdge('D', 'B', 2);
				dijkstraGraph.addEdge('E', 'G', 2);
				dijkstraGraph.addEdge('E', 'F', 1);
				dijkstraGraph.addEdge('G', 'F', 2);
				dijkstraGraph.addEdge('H', 'G', 1);
				dijkstraGraph.addEdge('H', 'A', 4);
				
				System.out.println(dijkstraGraph.traverseGraphDF('A'));
				System.out.println(dijkstraGraph.traverseGraphDF('C'));
				System.out.println(dijkstraGraph.traverseGraphDF('D'));
				System.out.println(dijkstraGraph.traverseGraphDF('G'));
	}
	
	@SuppressWarnings("unused")
	private static void exercise3_seminar() throws Exception {
		//Create the graph
				Graph<Character> floydGraph = new Graph<Character>(8);
				
				//Add the nodes to the graph
				floydGraph.addNode('A');
				floydGraph.addNode('B');
				floydGraph.addNode('C');
				floydGraph.addNode('D');
				floydGraph.addNode('E');
				floydGraph.addNode('F');
				floydGraph.addNode('G');
				floydGraph.addNode('H');
				
				//Set the edges
				floydGraph.addEdge('A', 'B', 1);
				floydGraph.addEdge('A', 'H', 2);
				floydGraph.addEdge('B', 'A', 1);
				floydGraph.addEdge('B', 'C', 8);
				floydGraph.addEdge('C', 'A', 1);
				floydGraph.addEdge('C', 'D', 4);
				floydGraph.addEdge('C', 'F', 1);
				floydGraph.addEdge('D', 'B', 1);
				floydGraph.addEdge('D', 'C', 3);
				floydGraph.addEdge('E', 'D', 9);
				floydGraph.addEdge('E', 'G', 3);
				floydGraph.addEdge('E', 'F', 6);
				floydGraph.addEdge('G', 'C', 1);
				floydGraph.addEdge('H', 'A', 2);
				floydGraph.addEdge('H', 'B', 4);
				floydGraph.addEdge('H', 'G', 1);
				
				//Print the initial state of edges and weight
				floydGraph.initsFloyd();
				printFloydMatrix(floydGraph);
				
				//Apply floyd
				floydGraph.floyd(floydGraph.getSize());
				
				//Print the final state of edges and weight
				printFloydMatrix(floydGraph);
	}

	@SuppressWarnings("unused")
	private static void dijkstraExample(Graph<Integer> dijkstraGraph) throws Exception {
		dijkstraNodes(dijkstraGraph);
		//dijkstraGraph.initDijkstra(2);
		//printDijkstraMatrix(dijkstraGraph);
		//printWeightMatrix(dijkstraGraph);
		//printEdgesMatrix(dijkstraGraph);
		dijkstraGraph.Dijkstra(1);
		dijkstraGraph.initDijkstra(2);
		printDijkstraMatrix(dijkstraGraph);
		dijkstraGraph.Dijkstra(2);
		printDijkstraMatrix(dijkstraGraph);
		
	}

	@SuppressWarnings("unused")
	private static void printEdgesMatrix(@SuppressWarnings("rawtypes") Graph dijkstraGraph) {
		System.out.println("EDGES MATRIX");
		for (int i = 0; i < dijkstraGraph.getEdges().length; i++) {
			for (int j = 0; j < dijkstraGraph.getEdges().length; j++) {
				System.out.print(dijkstraGraph.getEdges()[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("");
		
	}

	@SuppressWarnings("unused")
	private static <T> void printWeightMatrix(Graph<T> dijkstraGraph) {
		System.out.println("WEIGHTS MATRIX");
		for (int i = 0; i < dijkstraGraph.getWeights().length; i++) {
			for (int j = 0; j < dijkstraGraph.getWeights().length; j++) {
				System.out.print(dijkstraGraph.getWeights()[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	@SuppressWarnings("rawtypes")
	private static  void printDijkstraMatrix(Graph dijkstraGraph) {
		System.out.println("D MATRIX");
		for (int i = 0; i < dijkstraGraph.getD().length; i++) {
			System.out.print(dijkstraGraph.d[i] + "\t");
		}
		System.out.println("");
		System.out.println("PD MATRIX");
		for (int i = 0; i < dijkstraGraph.getPD().length; i++) {
			System.out.print(dijkstraGraph.pd[i] + "\t");
		}
		
		System.out.println("");
	}
	
	private static void printFloydMatrix(Graph<Character> dijkstraGraph) {
		System.out.println("A MATRIX");
		for (int i = 0; i < dijkstraGraph.getA().length; i++) {
			System.out.print(dijkstraGraph.a[i] + "\t");
		}
		System.out.println("");
		System.out.println("P MATRIX");
		for (int i = 0; i < dijkstraGraph.getP().length; i++) {
			System.out.print(dijkstraGraph.p[i] + "\t");
		}
		
		System.out.println("");
	}
	
	private static void dijkstraNodes(Graph<Integer> dijkstraGraph) throws Exception {
		dijkstraGraph.addNode(1);
		dijkstraGraph.addNode(2);
		dijkstraGraph.addNode(3);
		dijkstraGraph.addNode(4);
		dijkstraGraph.addNode(5);
		dijkstraGraph.addNode(6);
         
		dijkstraGraph.addEdge(1,2, 3.0);
		dijkstraGraph.addEdge(1,3, 4.0);
		dijkstraGraph.addEdge(1,5, 8.0);
		dijkstraGraph.addEdge(2,5, 5.0);
		dijkstraGraph.addEdge(3,5, 3.0);
		dijkstraGraph.addEdge(5,6, 3.0);
		dijkstraGraph.addEdge(5,4, 7.0);
		dijkstraGraph.addEdge(6,4, 2.0);
	}

	private static void floydExample(Graph<Character> floydGraph) {
		try{
			floydNodes(floydGraph);
			
			
			floydGraph.initsFloyd();
			
			printFloydTables(floydGraph);
			
			floydGraph.floyd(floydGraph.getSize());
			
			printFloydTables(floydGraph);
			
			//System.out.println(floydGraph.printFLoydPath(1, 5));
		}
		catch (Exception e)
		{
			System.out.println ("No repeated nodes are allowed" + e);
		}
	}

	private static void floydNodes(Graph<Character> floydGraph) throws Exception {
		
		//Add the nodes to the graph
		floydGraph.addNode('A');
		floydGraph.addNode('B');
		floydGraph.addNode('C');
		floydGraph.addNode('D');
		floydGraph.addNode('E');
		floydGraph.addNode('F');
		floydGraph.addNode('G');
		floydGraph.addNode('H');
		
		//Set the edges
		floydGraph.addEdge('A', 'B', 1);
		floydGraph.addEdge('A', 'H', 2);
		floydGraph.addEdge('B', 'A', 1);
		floydGraph.addEdge('B', 'C', 8);
		floydGraph.addEdge('C', 'A', 1);
		floydGraph.addEdge('C', 'D', 4);
		floydGraph.addEdge('C', 'F', 1);
		floydGraph.addEdge('D', 'B', 1);
		floydGraph.addEdge('D', 'C', 3);
		floydGraph.addEdge('E', 'D', 9);
		floydGraph.addEdge('E', 'G', 3);
		floydGraph.addEdge('E', 'F', 6);
		floydGraph.addEdge('G', 'C', 1);
		floydGraph.addEdge('H', 'A', 2);
		floydGraph.addEdge('H', 'B', 4);
		floydGraph.addEdge('H', 'G', 1);
	}

	private static void printFloydTables(Graph<Character> floydGraph) {
		for (int i = 0; i < floydGraph.a.length; i++) {
			for (int j = 0; j < floydGraph.a[i].length; j++) {
				System.out.print(floydGraph.a[i][j] + "\t");
			}
			System.out.println("");
		}
		
		for (int i = 0; i < floydGraph.p.length; i++) {
			for (int j = 0; j < floydGraph.p[i].length; j++) {
				System.out.print(floydGraph.p[i][j] + "\t");
			}
			System.out.println("");
		}
	}

}
