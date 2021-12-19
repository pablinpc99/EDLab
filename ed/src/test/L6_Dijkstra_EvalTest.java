package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import src.Graph;
 
public class L6_Dijkstra_EvalTest
{
    private static boolean[][] transformEdges(boolean[][] edges, int size)
    {
        boolean[][] result = new boolean[edges.length][];
        for (int i = 0; i < result.length; i++)
            result[i] = edges[i].clone();
 
        for (int i = 0; i < edges.length; i++)
            for (int j = 0; j < edges[i].length; j++)
                if ((i >= size) || (j >= size))
                    result[i][j] = false;
 
        return result;
    }
 
    private static double[][] transformWeights(boolean[][] edges, double[][] weights, int size)
    {
        double[][] result = new double[weights.length][];
        for (int i = 0; i < result.length; i++)
            result[i] = weights[i].clone();
 
        for (int i = 0; i < weights.length; i++)
            for (int j = 0; j < weights[i].length; j++)
                if ((i >= size) || (j >= size) || !edges[i][j])
                    result[i][j] = 0.0;
 
        return result;
    }
 
    @SuppressWarnings("unused")
	private static int[][] transformMatrix(int[][] matrix, int size)
    {
        int[][] result = new int[matrix.length][];
        for (int i = 0; i < result.length; i++)
            result[i] = matrix[i].clone();
 
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                if ((i >= size) || (j >= size))
                    result[i][j] = Graph.EMPTY;
 
        return result;
    }
 
    private static double[][] transformMatrix(double[][] matrix, int size)
    {
        double[][] result = new double[matrix.length][];
        for (int i = 0; i < result.length; i++)
            result[i] = matrix[i].clone();
 
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                if ((i >= size) || (j >= size))
                    result[i][j] = Graph.INFINITE;
 
        return result;
    }
 
    private static int[] transformArray(int[] array, int size)
    {
        int[] result = array.clone();
 
        for (int i = 0; i < array.length; i++)
            if (i >= size)
                result[i] = Graph.EMPTY;
 
        return result;
    }
 
    @Test
    public void edit()
    {
        Graph<Character> g = new Graph<Character>(6);
 
        assertEquals(0, g.getSize());
 
        // Basic node addition
        try
        {
            g.addNode('a');
            g.addNode('b');
            g.addNode('c');
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'addNode'");
        }
 
        assertEquals(3, g.getSize());
        assertEquals(0, g.getNode('a'));
        assertEquals(1, g.getNode('b'));
        assertEquals(2, g.getNode('c'));
 
        assertArrayEquals(new boolean[][] { { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        // Inexistent nodes
        assertEquals(-1, g.getNode('e'));
 
        // No repeated nodes allowed
        try
        {
            g.addNode('a');
            fail("FAIL: unexpected behavior of 'addNode'");
        }
        catch (Exception e) { }
 
        try
        {
            g.addNode('d');
            g.addNode('e');
            g.addNode('f');
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'addNode'");
        }
 
        assertEquals(6, g.getSize());
        assertEquals(0, g.getNode('a'));
        assertEquals(1, g.getNode('b'));
        assertEquals(2, g.getNode('c'));
        assertEquals(3, g.getNode('d'));
        assertEquals(4, g.getNode('e'));
        assertEquals(5, g.getNode('f'));
 
        assertArrayEquals(new boolean[][] { { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        // Testing edges
        try
        {
            assertEquals(false, g.existsEdge('b', 'g'));
            fail("FAIL: unexpected behavior of 'existsEdge'");
        }
        catch (Exception e) { }
 
        try
        {
            assertEquals(false, g.existsEdge('b', 'c'));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'existsEdge'");
        }
 
        assertArrayEquals(new boolean[][] { { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        try
        {
            g.addEdge('a', 'b', 3.0);
            g.addEdge('a', 'd', 2.0);
            g.addEdge('b', 'a', 5.0);
            g.addEdge('b', 'c', 6.0);
            g.addEdge('e', 'd', 1.0);
            g.addEdge('e', 'f', 7.0);
            assertEquals(true, g.existsEdge('b', 'a'));
            assertEquals(true, g.existsEdge('e', 'd'));
            assertEquals(false, g.existsEdge('d', 'e'));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised");
        }
 
        assertArrayEquals(new boolean[][] { { false, true, false, true, false, false }, { true, false, true, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, true, false, true }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0, 2.0, 0.0, 0.0 }, { 5.0, 0.0, 6.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0, 0.0, 7.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        // Adding a node when the graph is full
        try
        {
            g.addNode('z');
            fail("FAIL: unexpected behavior of 'addNode'");
        }
        catch (Exception e) { }
 
        assertArrayEquals(new boolean[][] { { false, true, false, true, false, false }, { true, false, true, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, true, false, true }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0, 2.0, 0.0, 0.0 }, { 5.0, 0.0, 6.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0, 0.0, 7.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        // Removal of nodes
        try
        {
            g.removeNode('a');
            g.removeNode('c');
            g.removeNode('d');
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'removeNode'");
        }
 
        assertEquals(3, g.getSize());
        assertEquals(1, g.getNode('b'));
        assertEquals(2, g.getNode('e'));
        assertEquals(0, g.getNode('f'));
 
        assertArrayEquals(new boolean[][] { { false, false, false, false, false, false }, { false, false, false, false, false, false }, { true, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 7.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        // Adding nodes back.
        try
        {
            g.addNode('c');
            g.addNode('d');
            g.addNode('a');
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'addNode'");
        }
 
        assertEquals(6, g.getSize());
        assertEquals(5, g.getNode('a'));
        assertEquals(1, g.getNode('b'));
        assertEquals(3, g.getNode('c'));
        assertEquals(4, g.getNode('d'));
        assertEquals(2, g.getNode('e'));
        assertEquals(0, g.getNode('f'));
 
        assertArrayEquals(new boolean[][] { { false, false, false, false, false, false }, { false, false, false, false, false, false }, { true, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 7.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
    }
 
    @Test
    public void dijkstraA() throws Exception
    {
        Graph<String> g = new Graph<String>(6);
 
        try
        {
            g.addNode("V1");
            g.addNode("V2");
            g.addNode("V3");
            g.addNode("V4");
            g.addNode("V5");
            g.addNode("V6");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'addNode'");
        }
 
        assertEquals(6, g.getSize());
        assertEquals(0, g.getNode("V1"));
        assertEquals(1, g.getNode("V2"));
        assertEquals(2, g.getNode("V3"));
        assertEquals(3, g.getNode("V4"));
        assertEquals(4, g.getNode("V5"));
        assertEquals(5, g.getNode("V6"));
 
        assertArrayEquals(new boolean[][] { { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        try
        {
            g.addEdge("V1", "V2", 3.0);
            g.addEdge("V1", "V3", 4.0);
            g.addEdge("V1", "V5", 8.0);
            g.addEdge("V2", "V5", 5.0);
            g.addEdge("V3", "V5", 3.0);
            g.addEdge("V5", "V6", 3.0);
            g.addEdge("V5", "V4", 7.0);
            g.addEdge("V6", "V4", 2.0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'addEdge'");
        }
 
        assertArrayEquals(new boolean[][] { { false, true, true, false, true, false }, { false, false, false, false, true, false }, { false, false, false, false, true, false }, { false, false, false, false, false, false }, { false, false, false, true, false, true }, { false, false, false, true, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 4.0, 0.0, 8.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 5.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 3.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 7.0, 0.0, 3.0 }, { 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        g.Dijkstra("V1");
 
        assertArrayEquals(new int[] { Graph.EMPTY, 0, 0, 5, 2, 4 }, transformArray(g.getPD(), g.getSize()));
        System.out.println(Arrays.toString(g.getD2()[0]));
        assertArrayEquals(new double[][] { { Graph.INFINITE, 3.0, 4.0, 12.0, 7.0, 10.0 } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("V2");
 
        assertArrayEquals(new int[] { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, 1, 4 }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 10.0, 5.0, 8.0 } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("V3");
 
        assertArrayEquals(new int[] { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, 2, 4 }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 8.0, 3.0, 6.0 } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("V4");
 
        assertArrayEquals(new int[] { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("V5");
 
        assertArrayEquals(new int[] { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, Graph.EMPTY, 4 }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 5.0, Graph.INFINITE, 3.0 } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("V6");
 
        assertArrayEquals(new int[] { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, Graph.EMPTY, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 2.0, Graph.INFINITE, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        assertArrayEquals(new boolean[][] { { false, true, true, false, true, false }, { false, false, false, false, true, false }, { false, false, false, false, true, false }, { false, false, false, false, false, false }, { false, false, false, true, false, true }, { false, false, false, true, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 4.0, 0.0, 8.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 5.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 3.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 7.0, 0.0, 3.0 }, { 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
    }
 
    @Test
    public void dijkstraB() throws Exception
    {
        Graph<String> g = new Graph<String>(6);
 
        try
        {
            g.addNode("Spain");
            g.addNode("Venezuela");
            g.addNode("UK");
            g.addNode("Poland");
            g.addNode("Greece");
            g.addNode("Japan");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'addNode'");
        }
 
        assertEquals(6, g.getSize());
        assertEquals(0, g.getNode("Spain"));
        assertEquals(1, g.getNode("Venezuela"));
        assertEquals(2, g.getNode("UK"));
        assertEquals(3, g.getNode("Poland"));
        assertEquals(4, g.getNode("Greece"));
        assertEquals(5, g.getNode("Japan"));
 
        assertArrayEquals(new boolean[][] { { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        // Check inexistent nodes
        assertEquals(Graph.INDEX_NOT_FOUND, g.getNode("Ecuador"));
 
        // No repeated nodes allowed
        try
        {
            g.addNode("Venezuela");
            fail("FAIL: unexpected behavior of 'addNode'");
        }
        catch (Exception e) { }
 
        // Testing edges
        try
        {
            g.existsEdge("Venezuela", "Ecuador");
            fail("FAIL: unexpected behavior of 'existsEdge'");
        }
        catch (Exception e) { }
 
        try
        {
            assertEquals(false, g.existsEdge("Greece", "Spain"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'existsEdge'");
        }
 
        try
        {
            g.addEdge("Spain", "Venezuela", 3.0);
            g.addEdge("Spain", "Greece", 2.0);
            g.addEdge("Venezuela", "Poland", 2.0);
            g.addEdge("Greece", "UK", 1.0);
            g.addEdge("UK", "Poland", 4.0);
            g.addEdge("Poland", "Spain", 1.0);
            g.addEdge("Poland", "Greece", 3.0);
            g.addEdge("Poland", "Japan", 1.0);
            g.addEdge("Japan", "Spain", 1.0);
            g.addEdge("Japan", "Poland", 2.0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'addEdge'");
        }
 
        assertArrayEquals(new boolean[][] { { false, true, false, false, true, false }, { false, false, false, true, false, false }, { false, false, false, true, false, false }, { true, false, false, false, true, true }, { false, false, true, false, false, false }, { true, false, false, true, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0, 0.0, 2.0, 0.0 }, { 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 4.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0, 3.0, 1.0 }, { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 2.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        g.Dijkstra("Spain");
        System.out.println(java.util.Arrays.toString(g.getPD()).replace("-1", "Graph.EMPTY"));
        //System.out.println(java.util.Arrays.toString(g.getD()[0]).replace("Infinity", "Graph.INFINITE"));
 
        assertArrayEquals(new int[] { Graph.EMPTY, 0, 4, 1, 0, 3 }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { Graph.INFINITE, 3.0, 3.0, 5.0, 2.0, 6.0 } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("Venezuela");
 
        assertArrayEquals(new int[] { 3, Graph.EMPTY, 4, 1, 3, 3 }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 3.0, Graph.INFINITE, 6.0, 2.0, 5.0, 3.0 } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("UK");
 
        assertArrayEquals(new int[] { 3, 0, Graph.EMPTY, 2, 3, 3 }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 5.0, 8.0, Graph.INFINITE, 4.0, 7.0, 5.0 } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("Poland");
 
        assertArrayEquals(new int[] { 3, 0, 4, Graph.EMPTY, 3, 3 }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 1.0, 4.0, 4.0, Graph.INFINITE, 3.0, 1.0 } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("Greece");
 
        assertArrayEquals(new int[] { 3, 0, 4, 2, Graph.EMPTY, 3 }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 6.0, 9.0, 1.0, 5.0, Graph.INFINITE, 6.0 } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("Japan");
 
        assertArrayEquals(new int[] { 5, 0, 4, 5, 0, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 1.0, 4.0, 4.0, 2.0, 3.0, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        assertArrayEquals(new boolean[][] { { false, true, false, false, true, false }, { false, false, false, true, false, false }, { false, false, false, true, false, false }, { true, false, false, false, true, true }, { false, false, true, false, false, false }, { true, false, false, true, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0, 0.0, 2.0, 0.0 }, { 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 4.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0, 3.0, 1.0 }, { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 2.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        try
        {
            g.removeNode("Greece");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'removeNode'");
        }
 
        assertArrayEquals(new boolean[][] { { false, true, false, false, false, false }, { false, false, false, true, false, false }, { false, false, false, true, false, false }, { true, false, false, false, true, false }, { true, false, false, true, false, false }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 4.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0, 1.0, 0.0 }, { 1.0, 0.0, 0.0, 2.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        g.Dijkstra("Spain");
 
        assertArrayEquals(new int[] { Graph.EMPTY, 0, Graph.EMPTY, 1, 3, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { Graph.INFINITE, 3.0, Graph.INFINITE, 5.0, 6.0, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("Venezuela");
 
        assertArrayEquals(new int[] { 3, Graph.EMPTY, Graph.EMPTY, 1, 3, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 3.0, Graph.INFINITE, Graph.INFINITE, 2.0, 3.0, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("UK");
 
        assertArrayEquals(new int[] { 3, 0, Graph.EMPTY, 2, 3, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 5.0, 8.0, Graph.INFINITE, 4.0, 5.0, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("Poland");
 
        assertArrayEquals(new int[] { 3, 0, Graph.EMPTY, Graph.EMPTY, 3, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 1.0, 4.0, Graph.INFINITE, Graph.INFINITE, 1.0, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("Japan");
 
        assertArrayEquals(new int[] { 4, 0, Graph.EMPTY, 4, Graph.EMPTY, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 1.0, 4.0, Graph.INFINITE, 2.0, Graph.INFINITE, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        assertArrayEquals(new boolean[][] { { false, true, false, false, false, false }, { false, false, false, true, false, false }, { false, false, false, true, false, false }, { true, false, false, false, true, false }, { true, false, false, true, false, false }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 4.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0, 1.0, 0.0 }, { 1.0, 0.0, 0.0, 2.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        try
        {
            g.removeEdge("Poland", "Japan");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'removeEdge'");
        }
 
        assertArrayEquals(new boolean[][] { { false, true, false, false, false, false }, { false, false, false, true, false, false }, { false, false, false, true, false, false }, { true, false, false, false, false, false }, { true, false, false, true, false, false }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 4.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 2.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        g.Dijkstra("Spain");
 
        assertArrayEquals(new int[] { Graph.EMPTY, 0, Graph.EMPTY, 1, Graph.EMPTY, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { Graph.INFINITE, 3.0, Graph.INFINITE, 5.0, Graph.INFINITE, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("Venezuela");
 
        assertArrayEquals(new int[] { 3, Graph.EMPTY, Graph.EMPTY, 1, Graph.EMPTY, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 3.0, Graph.INFINITE, Graph.INFINITE, 2.0, Graph.INFINITE, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("UK");
 
        assertArrayEquals(new int[] { 3, 0, Graph.EMPTY, 2, Graph.EMPTY, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 5.0, 8.0, Graph.INFINITE, 4.0, Graph.INFINITE, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("Poland");
 
        assertArrayEquals(new int[] { 3, 0, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 1.0, 4.0, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        g.Dijkstra("Japan");
 
        assertArrayEquals(new int[] { 4, 0, Graph.EMPTY, 4, Graph.EMPTY, Graph.EMPTY }, transformArray(g.getPD(), g.getSize()));
        assertArrayEquals(new double[][] { { 1.0, 4.0, Graph.INFINITE, 2.0, Graph.INFINITE, Graph.INFINITE } }, transformMatrix(g.getD2(), g.getSize()));
 
        assertArrayEquals(new boolean[][] { { false, true, false, false, false, false }, { false, false, false, true, false, false }, { false, false, false, true, false, false }, { true, false, false, false, false, false }, { true, false, false, true, false, false }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 2.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 4.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 2.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        // Inexistent nodes
        try
        {
            g.Dijkstra("Greece");
            fail("FAIL: unexpected behavior of 'Dijkstra'");
        }
        catch (Exception e) { }
    }
}