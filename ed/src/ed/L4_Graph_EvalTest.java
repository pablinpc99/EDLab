package ed;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.Test;
 
class L4_Graph_EvalTest
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
 
    @Test
    public void editSmall()
    {
        Graph<Character> g = new Graph<Character>(3);
 
        assertEquals(0, g.getSize());
 
        // Basic node addition
        try
        {
            g.addNode('a');
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'addNode'");
        }
 
        assertEquals(1, g.getSize());
        assertEquals(0, g.getNode('a'));
 
        assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        // Inexistent nodes
        assertEquals(-1, g.getNode('b'));
 
        // No repeated nodes allowed
        try
        {
            g.addNode('a');
            fail("FAIL: unexpected behavior of 'addNode'");
        }
        catch (Exception e) { }
 
        try
        {
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
 
        assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        // Testing edges
        try
        {
            assertEquals(false, g.existsEdge('b', 'd'));
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
 
        assertArrayEquals(new boolean[][] { { false, false, false }, { false, false, false }, { false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        try
        {
            g.addEdge('a', 'b', 3.0);
            g.addEdge('b', 'c', 5.0);
            assertEquals(true, g.existsEdge('b', 'c'));
            assertEquals(true, g.existsEdge('a', 'b'));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised");
        }
 
        assertArrayEquals(new boolean[][] { { false, true, false }, { false, false, true }, { false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0 }, { 0.0, 0.0, 5.0 }, { 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        // Adding a node when the graph is full
        try
        {
            g.addNode('d');
            fail("FAIL: unexpected behavior of 'addNode'");
        }
        catch (Exception e) { }
 
        assertArrayEquals(new boolean[][] { { false, true, false }, { false, false, true }, { false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0 }, { 0.0, 0.0, 5.0 }, { 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        // Removal of nodes
        try
        {
            g.removeNode('a');
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'removeNode'");
        }
 
        assertEquals(2, g.getSize());
        assertEquals(1, g.getNode('b'));
        assertEquals(0, g.getNode('c'));
 
        assertArrayEquals(new boolean[][] { { false, false, false }, { true, false, false }, { false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 5.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        try
        {
            g.removeNode('a');
            fail("FAIL: unexpected behavior of 'removeNode'");
        }
        catch (Exception e) { }
 
        assertEquals(2, g.getSize());
        assertEquals(1, g.getNode('b'));
        assertEquals(0, g.getNode('c'));
 
        assertArrayEquals(new boolean[][] { { false, false, false }, { true, false, false }, { false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 5.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
    }
 
    @Test
    public void editLarge()
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
    }
 
    @Test
    public void sourceAndDrainNodesSmall()
    {
        Graph<Character> g = new Graph<Character>(3);
 
        try
        {
            g.addNode('a');
            g.addNode('b');
            g.addNode('c');
            g.addEdge('a', 'b', 3.0);
            g.addEdge('b', 'c', 5.0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised");
        }
 
        assertEquals(3, g.getSize());
        assertEquals(0, g.getNode('a'));
        assertEquals(1, g.getNode('b'));
        assertEquals(2, g.getNode('c'));
        assertArrayEquals(new boolean[][] { { false, true, false }, { false, false, true }, { false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0 }, { 0.0, 0.0, 5.0 }, { 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        try
        {
            assertEquals(true, g.isSourceNode('a'));
            assertEquals(false, g.isSourceNode('b'));
            assertEquals(false, g.isSourceNode('c'));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'isSourceNode'");
        }
 
        assertEquals(1, g.countSourceNodes());
 
        try
        {
            assertEquals(false, g.isDrainNode('a'));
            assertEquals(false, g.isDrainNode('b'));
            assertEquals(true, g.isDrainNode('c'));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'isDrainNode'");
        }
 
        assertEquals(1, g.countDrainNodes());
 
        try
        {
            g.removeNode('a');
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'removeNode'");
        }
 
        assertEquals(2, g.getSize());
        assertEquals(1, g.getNode('b'));
        assertEquals(0, g.getNode('c'));
 
        assertArrayEquals(new boolean[][] { { false, false, false }, { true, false, false }, { false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 0.0, 0.0 }, { 5.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        try
        {
            assertEquals(true, g.isSourceNode('b'));
            assertEquals(false, g.isSourceNode('c'));
 
            assertEquals(false, g.isDrainNode('b'));
            assertEquals(true, g.isDrainNode('c'));
 
            assertEquals(1, g.countDrainNodes());
            assertEquals(1, g.countSourceNodes());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised");
        }
    }
 
    @Test
    public void sourceAndDrainNodesLarge()
    {
        Graph<Character> g = new Graph<Character>(6);
 
        try
        {
            g.addNode('a');
            g.addNode('b');
            g.addNode('c');
            g.addNode('d');
            g.addNode('e');
            g.addNode('f');
            g.addEdge('a', 'b', 3.0);
            g.addEdge('a', 'd', 2.0);
            g.addEdge('b', 'a', 5.0);
            g.addEdge('b', 'c', 6.0);
            g.addEdge('e', 'd', 1.0);
            g.addEdge('e', 'f', 7.0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised");
        }
 
        assertEquals(6, g.getSize());
        assertEquals(0, g.getNode('a'));
        assertEquals(1, g.getNode('b'));
        assertEquals(2, g.getNode('c'));
        assertEquals(3, g.getNode('d'));
        assertEquals(4, g.getNode('e'));
        assertEquals(5, g.getNode('f'));
        assertArrayEquals(new boolean[][] { { false, true, false, true, false, false }, { true, false, true, false, false, false }, { false, false, false, false, false, false }, { false, false, false, false, false, false }, { false, false, false, true, false, true }, { false, false, false, false, false, false } }, transformEdges(g.getEdges(), g.getSize()));
        assertArrayEquals(new double[][] { { 0.0, 3.0, 0.0, 2.0, 0.0, 0.0 }, { 5.0, 0.0, 6.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0, 0.0, 7.0 }, { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 } }, transformWeights(g.getEdges(), g.getWeights(), g.getSize()));
 
        try
        {
            assertEquals(false, g.isSourceNode('a'));
            assertEquals(false, g.isSourceNode('b'));
            assertEquals(false, g.isSourceNode('c'));
            assertEquals(false, g.isSourceNode('d'));
            assertEquals(true, g.isSourceNode('e'));
            assertEquals(false, g.isSourceNode('f'));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'isSourceNode'");
        }
 
        assertEquals(1, g.countSourceNodes());
 
        try
        {
            assertEquals(false, g.isDrainNode('a'));
            assertEquals(false, g.isDrainNode('b'));
            assertEquals(true, g.isDrainNode('c'));
            assertEquals(true, g.isDrainNode('d'));
            assertEquals(false, g.isDrainNode('e'));
            assertEquals(true, g.isDrainNode('f'));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised in 'isDrainNode'");
        }
 
        assertEquals(3, g.countDrainNodes());
 
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
 
        try
        {
            assertEquals(false, g.isSourceNode('b'));
            assertEquals(true, g.isSourceNode('e'));
            assertEquals(false, g.isSourceNode('f'));
 
            assertEquals(false, g.isDrainNode('b'));
            assertEquals(false, g.isDrainNode('e'));
            assertEquals(true, g.isDrainNode('f'));
 
            assertEquals(1, g.countDrainNodes());
            assertEquals(1, g.countSourceNodes());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("FAIL: unexpected exception raised");
        }
    }
}