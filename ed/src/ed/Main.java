package ed;

public class Main {

	public static void main(String[] args) {
		Graph<Integer> floydGraph = new Graph<>(5); 
		try{
			floydExample1(floydGraph);
			
			
			floydGraph.initsFloyd();
			
			printFloydTables(floydGraph);
			
			floydGraph.floyd(floydGraph.getSize());
			
			printFloydTables(floydGraph);
			
			System.out.println(floydGraph.printFLoydPath(1, 5));
		}
		catch (Exception e)
		{
			System.out.println ("No repeated nodes are allowed" + e);
		}
		

	}

	private static void floydExample1(Graph<Integer> floydGraph) throws Exception {
		floydGraph.addNode(1);
		floydGraph.addNode(2);
		floydGraph.addNode(3);
		floydGraph.addNode(4);
		floydGraph.addNode(5);
		 
		floydGraph.addEdge (1, 2, 1.0);
		floydGraph.addEdge (2, 3, 5.0);
		floydGraph.addEdge (3, 5, 1.0);
		floydGraph.addEdge (1, 5, 10.0);
		floydGraph.addEdge (1, 4, 3.0);
		floydGraph.addEdge (4, 5, 6.0);
		floydGraph.addEdge (4, 3, 2.0);
	}

	private static void printFloydTables(Graph<Integer> floydGraph) {
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
