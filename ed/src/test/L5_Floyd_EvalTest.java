package test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import src.Graph;
 
public class L5_Floyd_EvalTest
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
    public void dfs()
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
 
        try
        {
            assertEquals("V1-V2-V5-V4-V6-V3-", g.traverseGraphDF("V1"));
            assertEquals("V2-V5-V4-V6-", g.traverseGraphDF("V2"));
            assertEquals("V3-V5-V4-V6-", g.traverseGraphDF("V3"));
            assertEquals("V4-", g.traverseGraphDF("V4"));
            assertEquals("V5-V4-V6-", g.traverseGraphDF("V5"));
            assertEquals("V6-V4-", g.traverseGraphDF("V6"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'traverseGraphDF'");
        }
    }
 
    @Test
    public void floydA()
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
 
        g.floyd(g.getSize());
 
        assertArrayEquals(new int[][] { { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, 2, 4 }, { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, Graph.EMPTY, 4 }, { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, Graph.EMPTY, 4 }, { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY }, { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 5, Graph.EMPTY, Graph.EMPTY }, { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY } }, transformMatrix(g.getP(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 4.0, 12.0, 7.0, 10.0 }, { Graph.INFINITE, 0.0, Graph.INFINITE, 10.0, 5.0, 8.0 }, { Graph.INFINITE, Graph.INFINITE, 0.0, 8.0, 3.0, 6.0 }, { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 00.0, Graph.INFINITE, Graph.INFINITE }, { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 5.0, 0.0, 3.0 }, { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, 2.0, Graph.INFINITE, 0.0 } }, transformMatrix(g.getA(), g.getSize()));
 
        try
        {
            assertEquals("V1V3V5V6", "V1" + g.printFLoydPath("V1", "V6") + "V6");
            assertEquals("V2V5V6V4", "V2" + g.printFLoydPath("V2", "V4") + "V4");
            assertEquals("V5V6", "V5" + g.printFLoydPath("V5", "V6") + "V6");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'printFloydPath'");
        }
 
        // Inexistent nodes.
        try
        {
            g.printFLoydPath("V1", "V7");
            fail("FAIL: unexpected behavior of 'printFloydPath'");
        }
        catch (Exception e) { }
 
        try
        {
            g.printFLoydPath("V7", "V3");
            fail("FAIL: unexpected behavior of 'printFloydPath'");
        }
        catch (Exception e) { }
    }
 
    @Test
    public void floydB()
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
 
        g.floyd(g.getSize());
 
        assertArrayEquals(new double[][]{ { 0.0, 3.0, 3.0, 5.0, 2.0, 6.0 }, { 3.0, 0.0, 6.0, 2.0, 5.0, 3.0 }, { 5.0, 8.0, 0.0, 4.0, 7.0, 5.0 }, { 1.0, 4.0, 4.0, 0.0, 3.0, 1.0 }, { 6.0, 9.0, 1.0, 5.0, 0.0, 6.0 }, { 1.0, 4.0, 4.0, 2.0, 3.0, 0.0 } }, transformMatrix(g.getA(), g.getSize()));
        assertArrayEquals(new int[][] { { Graph.EMPTY, Graph.EMPTY, 4, 1, Graph.EMPTY, 3 }, { 3, Graph.EMPTY, 4, Graph.EMPTY, 3, 3 }, { 3, 3, Graph.EMPTY, Graph.EMPTY, 3, 3 }, { Graph.EMPTY, 0, 4, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY }, { 3, 3, Graph.EMPTY, 2, Graph.EMPTY, 3 }, { Graph.EMPTY, 0, 4, Graph.EMPTY, 0, Graph.EMPTY } }, transformMatrix(g.getP(), g.getSize()));
 
        try
        {
            assertEquals("SpainVenezuelaPolandJapan", "Spain" + g.printFLoydPath("Spain", "Japan") + "Japan");
            assertEquals("SpainGreeceUK", "Spain" + g.printFLoydPath("Spain", "UK") + "UK");
            assertEquals("SpainVenezuelaPoland", "Spain" + g.printFLoydPath("Spain", "Poland") + "Poland");
            assertEquals("PolandSpainVenezuela", "Poland" + g.printFLoydPath("Poland", "Venezuela") + "Venezuela");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'printFloydPath'");
        }
 
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
 
        g.floyd(g.getSize());
 
        assertArrayEquals(new double[][] { { 0.0, 3.0, Graph.INFINITE, 5.0, 6.0, Graph.INFINITE }, { 3.0, 0.0, Graph.INFINITE, 2.0, 3.0, Graph.INFINITE }, { 5.0, 8.0, 0.0, 4.0, 5.0, Graph.INFINITE }, { 1.0, 4.0, Graph.INFINITE, 0.0, 1.0, Graph.INFINITE }, { 1.0, 4.0, Graph.INFINITE, 2.0, 0.0, Graph.INFINITE }, { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE } }, transformMatrix(g.getA(), g.getSize()));
        assertArrayEquals(new int[][] { { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 1, 3, Graph.EMPTY }, { 3, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 3, Graph.EMPTY }, { 3, 3, Graph.EMPTY, Graph.EMPTY, 3, Graph.EMPTY }, { Graph.EMPTY, 0, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY }, { Graph.EMPTY, 0, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY }, { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY } }, transformMatrix(g.getP(), g.getSize()));
 
        try
        {
            assertEquals("SpainVenezuelaPolandJapan", "Spain" + g.printFLoydPath("Spain", "Japan") + "Japan");
            assertEquals("SpainUK", "Spain" + g.printFLoydPath("Spain", "UK") + "UK");
            assertEquals("SpainVenezuelaPoland", "Spain" + g.printFLoydPath("Spain", "Poland") + "Poland");
            assertEquals("PolandSpainVenezuela", "Poland" + g.printFLoydPath("Poland", "Venezuela") + "Venezuela");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'printFloydPath'");
        }
 
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
 
        g.floyd(g.getSize());
 
        assertArrayEquals(new double[][] { { 0.0, 3.0, Graph.INFINITE, 5.0, Graph.INFINITE, Graph.INFINITE }, { 3.0, 0.0, Graph.INFINITE, 2.0, Graph.INFINITE, Graph.INFINITE }, { 5.0, 8.0, 0.0, 4.0, Graph.INFINITE, Graph.INFINITE }, { 1.0, 4.0, Graph.INFINITE, 0.0, Graph.INFINITE, Graph.INFINITE }, { 1.0, 4.0, Graph.INFINITE, 2.0, 0.0, Graph.INFINITE }, { Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE, Graph.INFINITE } }, transformMatrix(g.getA(), g.getSize()));
        assertArrayEquals(new int[][] { { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, 1, Graph.EMPTY, Graph.EMPTY }, { 3, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY }, { 3, 3, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY }, { Graph.EMPTY, 0, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY }, { Graph.EMPTY, 0, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY }, { Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY, Graph.EMPTY } }, transformMatrix(g.getP(), g.getSize()));
 
        try
        {
            assertEquals("SpainJapan", "Spain" + g.printFLoydPath("Spain", "Japan") + "Japan");
            assertEquals("SpainUK", "Spain" + g.printFLoydPath("Spain", "UK") + "UK");
            assertEquals("SpainVenezuelaPoland", "Spain" + g.printFLoydPath("Spain", "Poland") + "Poland");
            assertEquals("PolandSpainVenezuela", "Poland" + g.printFLoydPath("Poland", "Venezuela") + "Venezuela");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'printFloydPath'");
        }
    }
}