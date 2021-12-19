package src;

import java.util.ArrayList;
import java.util.Collections;

public class BinaryHeap<T extends Comparable<T>> {
	
	private ArrayList<T> heap;
	
	public BinaryHeap() {
		heap = new ArrayList<T>();
	}
	
	public BinaryHeap(T[]elements) {
		heap = new ArrayList<T>();
		for (T t : elements) {
			heap.add(t);
		}
		
		
		for (int i = heap.size()/2; i >= 0; i--) {
			filterDown(i);
		}
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	public void print() {
		System.out.println(toString());
	}
	
	public String toString() {
		return heap.toString();
	}
	
	private void filterUP(int pos) {
		while(pos>0) {
			int father = (pos-1)/2;
			if(heap.get(father).compareTo(heap.get(pos))>0) {
				Collections.swap(heap,father,pos);
				pos = father;
			}
			else {
				break;
			}
		}
	}
	
	public void add(T element) {
		heap.add(element);
		 filterUP(heap.indexOf(element));
		
	}
	
	private void filterDown(int pos) {
		while(pos<heap.size()/2) {
			int children = 2*pos+1;
			if(2*pos+2 < heap.size()) {
				if(heap.get(children).compareTo(heap.get(2*pos+2))>0) {
					children = 2*pos+2;
				}
			}
			if(heap.get(pos).compareTo(heap.get(children))>0) {
				Collections.swap(heap, pos, children);
				pos=children;
			}
			else {
				break;
			}
			
		}
	}
	
	public T getMin() {
		T root = heap.get(0);
		Collections.swap(heap, 0, heap.size()-1);
		heap.remove(heap.size()-1);
		filterDown(0);
		return root;
	}

}
