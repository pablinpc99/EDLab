package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.Graph;

public class TestGraph {
	
	Graph<Integer> graph;

	@Before
	public void setUp() throws Exception {
		graph = new Graph<Integer>(10);
	}

	@Test
	public void testSizeEmpty() {
		assertEquals(0,graph.getSize());
	}
	
	@Test
	public void testSizeNotEmpty() {
		try {
			graph.addNode(1);
			graph.addNode(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(2,graph.getSize());
	}
	
	@Test
	public void testIsSourceNode() {
		try {
			for (int i = 0; i < 10; i++) {
				graph.addNode(i);
			}
			graph.addEdge(1, 2, 10);
			graph.addEdge(1, 3, 5);
			graph.addEdge(1, 4, 4);
			graph.addEdge(1, 5, 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(true,graph.isSourceNode(1));
		assertEquals(false,graph.isSourceNode(3));
	}
	
	@Test
	public void testIsDrainNode() {
		try {
			for (int i = 0; i < 10; i++) {
				graph.addNode(i);
			}
			graph.addEdge(1, 3, 10);
			graph.addEdge(2, 3, 5);
			graph.addEdge(4, 3, 4);
			graph.addEdge(5, 3, 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(false,graph.isDrainNode(1));
		assertEquals(true,graph.isDrainNode(3));
	}
	
	@Test
	public void testSourceNoEdges() {
		try {
			for (int i = 0; i < 10; i++) {
				graph.addNode(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(false,graph.isSourceNode(1));
		assertEquals(false,graph.isSourceNode(3));
	}
	
	@Test
	public void testDrainNoEdges() {
		try {
			for (int i = 0; i < 10; i++) {
				graph.addNode(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(false,graph.isDrainNode(1));
		assertEquals(false,graph.isDrainNode(3));
	}
	
	@Test
	public void testNoDrainNoSource() {
		try {
			for (int i = 0; i < 10; i++) {
				graph.addNode(i);
			}
			graph.addEdge(1, 3, 10);
			graph.addEdge(3, 4, 5);
			graph.addEdge(4, 2, 4);
			graph.addEdge(2, 1, 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(false,graph.isSourceNode(1));
		assertEquals(false,graph.isSourceNode(3));
		assertEquals(false,graph.isDrainNode(1));
		assertEquals(false,graph.isDrainNode(3));
	}
	
	@Test
	public void testCountSourceNodes() {
		try {
			for (int i = 0; i < 10; i++) {
				graph.addNode(i);
			}
			graph.addEdge(1, 3, 2);
			graph.addEdge(1, 5, 3);
			graph.addEdge(1, 7, 4);
			graph.addEdge(1, 9, 5);
			graph.addEdge(2, 4, 6);
			graph.addEdge(2, 6, 7);
			graph.addEdge(2, 8, 8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(2,graph.countSourceNodes());
	}
	
	@Test
	public void testCountDrainNodes() {
		try {
			for (int i = 0; i < 10; i++) {
				graph.addNode(i);
			}
			graph.addEdge(1, 3, 2);
			graph.addEdge(1, 5, 3);
			graph.addEdge(1, 7, 4);
			graph.addEdge(1, 9, 5);
			graph.addEdge(2, 4, 6);
			graph.addEdge(2, 6, 7);
			graph.addEdge(2, 8, 8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(7,graph.countDrainNodes());
	}
	
	@Test
	public void testDFSearch() {
		Graph<Character> graph1 = new Graph<>(10); 
		try
         {
             graph1.addNode('a');
             graph1.addNode('b');
             graph1.addNode('c');
             graph1.addNode('d');
             
             graph1.addEdge ('a', 'b', 3.0);
             graph1.addEdge ('b', 'c', 1.0);
             graph1.addEdge ('b', 'd', 3.0);
             graph1.addEdge ('c', 'd', 1.0);
             graph1.addEdge ('d', 'a', 3.0);
             
          // TRAVERSE
             assertEquals("a-b-c-d-", graph1.traverseGraphDF('a'));
             assertEquals("b-c-d-a-", graph1.traverseGraphDF('b'));
             assertEquals("c-d-a-b-", graph1.traverseGraphDF('c'));
             assertEquals("d-a-b-c-", graph1.traverseGraphDF('d'));
         }
         catch (Exception e)
         {
             System.out.println ("No repeated nodes are allowed" + e);
         }
	}
	
	@Test
	public void testFloyd1() {
		Graph<Integer> floydGraph = new Graph<>(10); 
		try{
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
			
			floydGraph.initsFloyd();
			floydGraph.floyd(floydGraph.getSize());
			String expected = "43";
			assertEquals(expected,floydGraph.printFLoydPath(1, 5));
		}
		catch (Exception e)
		{
			System.out.println ("No repeated nodes are allowed" + e);
		}
	}
	
	@Test
	public void testFloyd2() {
		Graph<Integer> floydGraph = new Graph<>(6); 
		try{
			floydGraph.addNode(1);
			floydGraph.addNode(2);
			floydGraph.addNode(3);
			floydGraph.addNode(4);
			floydGraph.addNode(5);
			floydGraph.addNode(6);
             
			floydGraph.addEdge (1, 3, 4.0);
			floydGraph.addEdge (1, 2, 3.0);
			floydGraph.addEdge (1, 5, 8.0);
			floydGraph.addEdge (2, 5, 5.0);
			floydGraph.addEdge (3, 5, 3.0);
			floydGraph.addEdge (5, 4, 7.0);
			floydGraph.addEdge (5, 6, 3.0);
			floydGraph.addEdge (6, 4, 2.0);
			
			floydGraph.initsFloyd();
			floydGraph.floyd(floydGraph.getSize());
			String expected = "35";
			assertEquals(expected,floydGraph.printFLoydPath(1, 6));
		}
		catch (Exception e)
		{
			System.out.println ("No repeated nodes are allowed" + e);
		}
	}
	
	@Test
	public void testDijkstra1() {
		Graph<Integer> dijkstraGraph = new Graph<>(5); 
		try{
			dijkstraGraph.addNode(1);
			dijkstraGraph.addNode(2);
			dijkstraGraph.addNode(3);
			dijkstraGraph.addNode(4);
			dijkstraGraph.addNode(5);
             
			dijkstraGraph.addEdge (1, 2, 1.0);
			dijkstraGraph.addEdge (2, 3, 5.0);
			dijkstraGraph.addEdge (3, 5, 1.0);
			dijkstraGraph.addEdge (1, 5, 10.0);
			dijkstraGraph.addEdge (1, 4, 3.0);
			dijkstraGraph.addEdge (4, 5, 6.0);
			dijkstraGraph.addEdge (4, 3, 2.0);
			
			dijkstraGraph.initDijkstra(1);
			dijkstraGraph.Dijkstra(1);
			
		}
		catch (Exception e)
		{
			System.out.println ("No repeated nodes are allowed" + e);
		}
	}
		
		@Test
		public void testDijkstra2() {
			Graph<Integer> dijkstraGraph = new Graph<>(6); 
			try{
				dijkstraGraph.addNode(1);
				dijkstraGraph.addNode(2);
				dijkstraGraph.addNode(3);
				dijkstraGraph.addNode(4);
				dijkstraGraph.addNode(5);
				dijkstraGraph.addNode(6);
	             
				dijkstraGraph.addEdge (1, 3, 4.0);
				dijkstraGraph.addEdge (1, 2, 3.0);
				dijkstraGraph.addEdge (1, 5, 8.0);
				dijkstraGraph.addEdge (2, 5, 5.0);
				dijkstraGraph.addEdge (3, 5, 3.0);
				dijkstraGraph.addEdge (5, 4, 7.0);
				dijkstraGraph.addEdge (5, 6, 3.0);
				dijkstraGraph.addEdge (6, 4, 2.0);
				
				dijkstraGraph.initDijkstra(1);
				dijkstraGraph.Dijkstra(1);
				Integer[] expectedD = {0,3,4,12,7,10};
				Integer[] expectedPD = {0,0,0,5,2,4};
				for (int i = 0; i < expectedD.length; i++) {
					assertTrue(expectedD[i]==dijkstraGraph.getD()[i]);
					assertTrue(expectedPD[i]==dijkstraGraph.getPD()[i]);
				}
			}
			catch (Exception e)
			{
				System.out.println ("No repeated nodes are allowed" + e);
			}
	}
		
		@Test
		public void testDijkstra1Quadratic() {
			Graph<Integer> dijkstraGraph = new Graph<>(5); 
			try{
				dijkstraGraph.addNode(1);
				dijkstraGraph.addNode(2);
				dijkstraGraph.addNode(3);
				dijkstraGraph.addNode(4);
				dijkstraGraph.addNode(5);
	             
				dijkstraGraph.addEdge (1, 2, 1.0);
				dijkstraGraph.addEdge (2, 3, 5.0);
				dijkstraGraph.addEdge (3, 5, 1.0);
				dijkstraGraph.addEdge (1, 5, 10.0);
				dijkstraGraph.addEdge (1, 4, 3.0);
				dijkstraGraph.addEdge (4, 5, 6.0);
				dijkstraGraph.addEdge (4, 3, 2.0);
				
				dijkstraGraph.initDijkstra(1);
				dijkstraGraph.DijkstraQuadratic(1);
				Integer[] expectedD = {0,1,5,3,6};
				Integer[] expectedPD = {0,0,3,0,2};
				for (int i = 0; i < expectedD.length; i++) {
					assertTrue(expectedD[i]==dijkstraGraph.getD()[i]);
					assertTrue(expectedPD[i]==dijkstraGraph.getPD()[i]);
				}
			}
			catch (Exception e)
			{
				System.out.println ("No repeated nodes are allowed" + e);
			}
		}
			
			@Test
			public void testDijkstra2Quadratic() {
				Graph<Integer> dijkstraGraph = new Graph<>(6); 
				try{
					dijkstraGraph.addNode(1);
					dijkstraGraph.addNode(2);
					dijkstraGraph.addNode(3);
					dijkstraGraph.addNode(4);
					dijkstraGraph.addNode(5);
					dijkstraGraph.addNode(6);
		             
					dijkstraGraph.addEdge (1, 3, 4.0);
					dijkstraGraph.addEdge (1, 2, 3.0);
					dijkstraGraph.addEdge (1, 5, 8.0);
					dijkstraGraph.addEdge (2, 5, 5.0);
					dijkstraGraph.addEdge (3, 5, 3.0);
					dijkstraGraph.addEdge (5, 4, 7.0);
					dijkstraGraph.addEdge (5, 6, 3.0);
					dijkstraGraph.addEdge (6, 4, 2.0);
					
					dijkstraGraph.initDijkstra(1);
					dijkstraGraph.DijkstraQuadratic(1);
					Integer[] expectedD = {0,3,4,12,7,10};
					Integer[] expectedPD = {0,0,0,5,2,4};
					for (int i = 0; i < expectedD.length; i++) {
						assertTrue(expectedD[i]==dijkstraGraph.getD()[i]);
						assertTrue(expectedPD[i]==dijkstraGraph.getPD()[i]);
					}
				}
				catch (Exception e)
				{
					System.out.println ("No repeated nodes are allowed" + e);
				}
		}

}
