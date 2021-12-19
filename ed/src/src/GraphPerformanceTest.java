package src;

import java.util.Random;

public class GraphPerformanceTest {
	
	private final static int MAX_WEIGHT = 100;

	public static Graph<Integer> initGraph(long n) throws Exception{
		Graph<Integer> intGraph = new Graph<>((int)n);
		Random rand = new Random();
		
		//Create n nodes 
		for (int i = 0; i < n; i++) {
			intGraph.addNode(i);
		}
		
		//Join all edges
		for (int i = 0; i < intGraph.getSize(); i++) {
			for (int j = 0; j < intGraph.getSize(); j++) {
				intGraph.addEdge(i, j,rand.nextInt(MAX_WEIGHT)+1);
			}
		}
		return intGraph;
	}
	
	public static void runDijkstra(long n) throws Exception {
		Graph<Integer> intGraph = initGraph((int)n);
		intGraph.DijkstraQuadratic(0);
	}
	
	public static void runFloyd(long n) throws Exception {
		Graph<Integer> intGraph = initGraph((int)n);
		intGraph.floyd(intGraph.getSize());
	}
}
