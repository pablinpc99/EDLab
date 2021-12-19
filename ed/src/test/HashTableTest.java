package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.HashTable;

class HashTableTest {

	@Test
	public void testF1() {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(2, a.f(7, 0));
		assertEquals(3, a.f(7, 1));
		assertEquals(4, a.f(7, 2));
		assertEquals(0, a.f(7, 3));
	}
	
	@Test
	public void testF2() {
		HashTable<Character> a = new HashTable<Character>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(0, a.f('A', 0));
		assertEquals(1, a.f('A', 1));
		assertEquals(2, a.f('A', 2));
		assertEquals(3, a.f('A', 3));
	}
	
	@Test
	public void testQuadraticProbing() {
		HashTable<Character> b = new HashTable<Character>(5, HashTable.QUADRATIC_PROBING, 0.5);
		assertEquals(0, b.f('A', 0));
		assertEquals(1, b.f('A', 1));
		assertEquals(4, b.f('A', 2));
		assertEquals(4, b.f('A', 3));
	}
	
	@Test
	public void testPrime() {
		HashTable<Character> a = new HashTable<Character>(5, HashTable.LINEAR_PROBING, 0.5);
		assertTrue(a.isPrime(2));
		assertTrue(a.isPrime(7));
		assertTrue(!a.isPrime(6));
	}
	
	@Test
	public void testNextPrime() {
		HashTable<Character> a = new HashTable<Character>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(7,a.getNextPrimeNumber(5));
		assertEquals(13,a.getNextPrimeNumber(11));
	}
	
	@Test
	public void testPrevPrime() {
		HashTable<Character> a = new HashTable<Character>(5, HashTable.LINEAR_PROBING, 0.5);
		assertEquals(5,a.getPrevPrimeNumber(7));
		assertEquals(11,a.getPrevPrimeNumber(13));
	}
	
	@Test
	public void testAddSearch() throws Exception {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);
		
		assertEquals("[0] (1) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		assertEquals(true, a.search(3));
		assertEquals(false, a.search(12));
	}
	
	@Test
	public void testAddSearchQuadraticProbing() throws Exception {
		HashTable<Integer> b = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 1.0);
		b.add(4);
		b.add(13);
		b.add(24);
		b.add(3);
		
		assertEquals("[0] (1) = 24 - [1] (0) = null - [2] (1) = 3 - [3] (1) = 13 - [4] (1) = 4 - ", b.toString());
		assertEquals(true, b.search(3));
		assertEquals(false, b.search(12));
	}
	
	@Test 
	public void testRemove() throws Exception {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 1.0);
		a.add(4);
		a.add(13);
		a.add(24);
		a.add(3);
		a.remove(24);
		assertEquals(true, a.search(3));
		assertEquals("[0] (2) = 24 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());

		a.add(15);
		assertEquals(true, a.search(3));
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());

	}
	
	@Test
	public void testDoubleHashing() {
		HashTable<Integer> c = new HashTable<Integer>(5, HashTable.DOUBLE_HASHING, 0.5);
		assertEquals(2, c.f(7, 0));
		assertEquals(4, c.f(7, 1));
		assertEquals(1, c.f(7, 2));
		assertEquals(3, c.f(7, 3));
		assertEquals(0, c.f(7, 4));

		assertEquals(0, c.f(0, 0));
		assertEquals(1, c.f(2, 4));
		assertEquals(2, c.f(3, 3));
		assertEquals(3, c.f(32, 1));
		assertEquals(4, c.f(1045, 2));

	}
	
	@Test 
	public void testDoubleHashingV2() {
		HashTable<Character> c = new HashTable<Character>(5, HashTable.DOUBLE_HASHING, 0.5);
		assertEquals(0, c.f('A', 0));
		assertEquals(1, c.f('A', 1));
		assertEquals(2, c.f('A', 2));
		assertEquals(3, c.f('A', 3));
		assertEquals(4, c.f('A', 4));

		assertEquals(2, c.f('a', 0));
		assertEquals(4, c.f('a', 1));
		assertEquals(1, c.f('a', 2));
		assertEquals(3, c.f('a', 3));
		assertEquals(0, c.f('a', 4));
	}
	
	@Test 
	public void testAddSearchRemoveDoubleHashing() throws Exception {
		HashTable<Integer> c = new HashTable<Integer>(5, HashTable.DOUBLE_HASHING, 1.0);
		c.add(4);
		c.add(13);
		c.add(24);
		c.add(3);		
		assertEquals("[0] (0) = null - [1] (1) = 3 - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		c.remove(24);
		assertEquals("[0] (0) = null - [1] (1) = 3 - [2] (2) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());
		assertEquals(true, c.search(3));
		c.add(15);
		assertEquals(true, c.search(3));
		assertEquals("[0] (1) = 15 - [1] (1) = 3 - [2] (2) = 24 - [3] (1) = 13 - [4] (1) = 4 - ", c.toString());	
	}
	
	@Test
	public void testDynamicResizingLinearProbing() throws Exception {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.LINEAR_PROBING, 0.5);
		a.add(4);
		assertEquals (0.2, a.getLF(), 0.1);
		a.add(13);
		assertEquals (0.4, a.getLF(), 0.1);
		assertEquals ("[0] (0) = null - [1] (0) = null - [2] (0) = null - [3] (1) = 13 - [4] (1) = 4 - ", a.toString());
		 
		a.add(24); // DYNAMIC RESIZING!
		assertEquals (0.27, a.getLF(), 0.1);
		assertEquals("[0] (0) = null - [1] (0) = null - [2] (1) = 24 - [3] (1) = 13 - [4] (1) = 4 - [5] (0) = null - [6] (0) = null - [7] (0) = null - [8] (0) = null - [9] (0) = null - [10] (0) = null - ", a.toString());

	}
	
	@Test
	public void testDynamicResizingQuadraticProbing() throws Exception {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.QUADRATIC_PROBING, 0.5);
		a.add(4);
		assertEquals (0.2, a.getLF(), 0.1);
		a.add(13);
		assertEquals (0.4, a.getLF(), 0.1);
		 
		a.add(24); // DYNAMIC RESIZING!
		assertEquals (0.27, a.getLF(), 0.1);

	}
	
	@Test
	public void testDynamicResizingDoubleHashing() throws Exception {
		HashTable<Integer> a = new HashTable<Integer>(5, HashTable.DOUBLE_HASHING, 0.5);
		a.add(4);
		assertEquals (0.2, a.getLF(), 0.1);
		a.add(13);
		assertEquals (0.4, a.getLF(), 0.1);
		 
		a.add(24); // DYNAMIC RESIZING!
		assertEquals (0.27, a.getLF(), 0.1);

	}


}
