package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import src.AVLTree;

class AVLTreeTest {
	
	@Test
	public void testAdd() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		assertEquals ("b(0)--", a.toString());
		a.add('a');
		assertEquals ("b(1)a(0)---", a.toString());
		a.add('d');
		assertEquals ("b(1)a(0)--d(0)--", a.toString());
		a.add('c');
		assertEquals ("b(2)a(0)--d(1)c(0)---", a.toString());
		a.add('g');
		assertEquals ("b(2)a(0)--d(1)c(0)--g(0)--", a.toString());
		a.add('i');
		assertEquals ("b(3)a(0)--d(2)c(0)--g(1)-i(0)--", a.toString());
		a.add('h');
		assertEquals ("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", a.toString());
	}
	
	@Test
	public void search() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals (true, a.search('i'));
		assertEquals (false, a.search('f'));
		assertEquals (false, a.search('j'));
	}
	
	@Test
	public void testMax() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals ('i', (char) a.getMax(a.getRoot()));
	}
	
	@Test
	public void testRemove() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals ("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", a.toString());
		a.remove('b');
		assertEquals ("a(4)-d(3)c(0)--g(2)-i(1)h(0)---", a.toString());
		a.remove('g');
		assertEquals ("a(3)-d(2)c(0)--i(1)h(0)---", a.toString());
	}
	
	@Test 
	public void testUpdateHeight() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals ("b(4)a(0)--d(3)c(0)--g(2)-i(1)h(0)---", a.toString());

	}
	
	@Test
	public void testJoin() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		AVLTree<Character> b = new AVLTree<Character>();
		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');
		assertEquals ("b(3)a(0)--d(2)c(0)--g(1)-i(0)--", a.joins(b).toString());
	}
	
	@Test
	public void testIntersection() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		AVLTree<Character> b = new AVLTree<Character>();
		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');
		assertEquals ("d(0)--", a.intersection(b).toString());

	}
	
	@Test
	public void testGetBF() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals ("b(3)a(0)--d(2)c(0)--g(2)-i(-1)h(0)---", a.toString());

	}
	
	@Test
	public void TestSingleRotation() {
	AVLTree<Character> a = new AVLTree<Character>();
	a.add('a');
	a.add('b');
	assertEquals ("a(1)-b(0)--", a.toString());
	a.add('c');
	assertEquals ("b(0)a(0)--c(0)--", a.toString());
	a.add('d');
	assertEquals ("b(1)a(0)--c(1)-d(0)--", a.toString());
	a.add('e');
	assertEquals ("b(1)a(0)--d(0)c(0)--e(0)--", a.toString());
	a.add('f');
	assertEquals ("d(0)b(0)a(0)--c(0)--e(1)-f(0)--", a.toString());
	}
	
	@Test
	public void testDoubleRotation() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('a');
		a.add('b');
		a.add('d');
		assertEquals ("b(0)a(0)--d(0)--", a.toString());

		AVLTree<Character> b = new AVLTree<Character>();
		b.add('c');
		b.add('g');
		b.add('i');
		b.add('d');
		assertEquals ("g(-1)c(1)-d(0)--i(0)--", b.toString());

		assertEquals ("d(0)b(0)a(0)--c(0)--g(1)-i(0)--", a.joins(b).toString());

	}
	
	@Test
	public void testHeight1() {
		AVLTree<Character> a = new AVLTree<Character>();
		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		a.add('g');
		a.add('i');
		a.add('h');
		assertEquals (3, a.getHeight());
	}
	
	@Test
	public void testHeight2() {
		AVLTree<Integer> a = new AVLTree<Integer>();
		a.add(10);
		a.add(5);
		a.add(15);
		a.add(3);
		a.add(7);
		a.add(13);
		a.add(17);
		a.add(1);
		a.add(6);
		a.add(11);
		a.add(16);
		assertEquals (4, a.getHeight());
	}

	@Test
	public void testBFS() {
		AVLTree<Integer> a = new AVLTree<Integer>();
		a.add(100);
		a.add(50);
		a.add(150);
		a.add(25);
		a.add(125);
		a.add(75);
		a.add(175);
		a.add(65);
		a.add(85);
		String expectedBF = "100 - 50 - 150 - 25 - 75 - 125 - 175 - 65 - 85 - ";
		assertEquals(expectedBF,a.printBFS(a.getRoot()));
	}

}
