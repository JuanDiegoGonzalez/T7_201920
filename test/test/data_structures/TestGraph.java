package test.data_structures;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Graph;

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
		
	}
	
	@Test
	public void testGetInfoVertex()
	{
		
	}
	@Test
	public void testGetcost()
	{
		
	}
	@Test
	public void testAdj()
	{
		
	}
	@Test
	public void testUncheck()
	{
		
	}
	@Test
	public void testdfs()
	{
		
	}
	@Test
	public void testCc()
	{
		
	}
}
