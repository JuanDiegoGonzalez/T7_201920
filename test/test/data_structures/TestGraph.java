package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Graph;
import model.data_structures.Stack;

public class TestGraph 
{
	private Graph<Integer,String> grafo ;
	@Before
	public void setUp()
	{
		grafo = new Graph<>(1);
	}
	//  1----------2 
	//  |          |
	//  |          |
    //  3----------4
	// 1 = a 
	// 2 = b
	// 3 = c
	// 4 = d 
	// ------  costo 10
	// |       costo 5
	public void setUp1()
	{
		grafo.addVertex(1,"a");
		grafo.addVertex(2, "b");
		grafo.addVertex(3, "c");
		grafo.addVertex(4, "d");
		grafo.addEdge(1, 2, 10.0);
		grafo.addEdge(3, 4, 10.0);
		grafo.addEdge(1, 3, 5.0);
		grafo.addEdge(2, 4,5.0);
	}
	@Test
	public void testConstructor()
	{
		assertNotNull(grafo.V());
		assertNotNull(grafo.E());
		assertEquals(grafo.V(), 4);
		assertEquals(grafo.E(), 4);
	}
	@Test
	public void testGetInfoVertex()
	{
		assertEquals(grafo.getInfoVertex(1), "a");
		assertEquals(grafo.getInfoVertex(2), "b");
		assertEquals(grafo.getInfoVertex(3), "c");
		assertEquals(grafo.getInfoVertex(4), "d");
	}
	@Test
	public void testGetcost()
	{
		assertEquals(grafo.getCostArc(1, 2), 10.0);
		assertEquals(grafo.getCostArc(3, 4), 10.0);
		assertEquals(grafo.getCostArc(1, 3), 5.0);
		assertEquals(grafo.getCostArc(2, 4), 5.0);
		assertNull(grafo.getCostArc(1, 4));
		assertNull(grafo.getCostArc(3, 2));
	}
	@Test
	public void testAdj()
	{
		assertNotNull(grafo.adj(1));
		Stack  x = new  Stack<>();
		x = (Stack) grafo.adj(1);
		assertEquals(x.size(), 4);
		
		
	}
	@Test
	public void testUncheck()
	{
		for(int i = 0; i <4; i++)
		{
			assertFalse(grafo.Marked[i]);
		}
	}
	
	@Test
	public void testCc()
	{
	
		
		assertEquals(grafo.cc(), 4);
	}
}
