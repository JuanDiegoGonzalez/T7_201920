package test.data_structures;

import static org.junit.Assert.*;

import java.util.Iterator;

import javax.swing.JComboBox.KeySelectionManager;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Queue;
import model.data_structures.RedBlackBST;

public class TestRedBlack 
{
	private RedBlackBST<Integer, String> rb;
	private RedBlackBST<Integer, String> rbn;
	
	@Before
	public void setup1()
	{
		rb = new RedBlackBST<>();
		rbn = new RedBlackBST<>();
		rb.put(1, "a");
		rb.put(2, "b");
		rb.put(3, "c");
		rb.put(4, "d");
		rb.put(5, "e");
		rb.put(6, "f");
		rb.put(7, "h");
		rb.put(8, "i");
		rb.put(9, "j");
		rb.put(10, "k");
	}
	
	@Test
	public void constructorTest()
	{
		assertEquals(rb.isEmpty(), false);
		assertEquals(rb.size(), 10);
		assertEquals(rb.height(), 3);
		assertEquals(rb.getHeight(rb.darRaiz()), 3);
		assertEquals(rbn.height(), -1);
		assertTrue(rb.contains(1));
	}
	
	@Test
	public void getTest()
	{
		assertEquals(rb.get(1), "a");
		assertEquals(rb.get(2), "b");
		assertEquals(rb.get(3), "c");
		assertEquals(rb.get(4), "d");
		assertEquals(rb.get(5), "e");
		assertNull(rbn.get(1));
	}
	
	@Test
	public void putTest()
	{
		rbn.put(1, "a");
		assertEquals(rbn.isEmpty(), false);
		assertEquals(rbn.size(), 1);

		rbn.put(2, "b");
		assertEquals(rbn.isEmpty(), false);
		assertEquals(rbn.size(), 2);

		rb.put(11, "z");
		assertEquals(rb.size(), 11);
		assertEquals(rb.get(11), "z");

		rb.put(1, "x");
		assertEquals(rb.size(), 11);
		assertEquals(rb.get(1), "x");
	}
	
	@Test
	public void minTest()
	{
		try {
			rbn.min();
			fail("Debería mandar ecepción");
		} catch (Exception e) {			
		}
		Integer a = 1;
		try {
			assertEquals(rb.min(), a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rb.delete(1);
		Integer b = 2;
		try {
			assertEquals(rb.min(), b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void maxTest()
	{
		try {
			rbn.max();
			fail("Debería mandar ecepción");
		} catch (Exception e) {
		}
		Integer a = 10;
		try {
			assertEquals(rb.max(), a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rb.delete(10);
		Integer b = 9;
		try {
			assertEquals(rb.max(), b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkTest()
	{
		assertTrue(rb.check());
	}
	
	@Test
	public void keysTest() throws Exception
	{
		Iterator<Integer> respuesta = rb.keys();

		assertTrue(respuesta.hasNext());

		for (int i = 0; i < 10; i++)
		{
			respuesta.next();
		}
		assertFalse(respuesta.hasNext());
	}
	
	@Test
	public void rangeTest()
	{
		Queue llaves = (Queue) rb.keysInRange(1,4 );
		assertEquals(llaves.darNumeroElementos(),4);
		Queue values = (Queue) rb.valuesInRange(1,4 );
		assertEquals(values.darNumeroElementos(),4);
	}
}