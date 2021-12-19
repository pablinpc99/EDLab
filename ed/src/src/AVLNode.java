package src;

public class AVLNode<T> {
	
	private T element;
	private AVLNode<T> right;
	private AVLNode<T> left;
	private int height;
	
	public AVLNode(T element) {
		this.element = element;
		this.right=null;
		this.left=null;
		this.height=0;
	}

	public AVLNode(T element, AVLNode<T> right, AVLNode<T> left) {
		this.element = element;
		this.right = right;
		this.left = left;
	}

	protected T getElement() {
		return element;
	}
	
	protected void setElement(T element) {
		this.element = element;
	}
	
	protected AVLNode<T> getRight() {
		return right;
	}
	
	protected void setRight(AVLNode<T> right) {
		this.right = right;
	}
	
	protected AVLNode<T> getLeft() {
		return left;
	}
	
	protected void setLeft(AVLNode<T> left) {
		this.left = left;
	}

	protected int getHeight() {
		return height;
	}

	protected void setHeight(int height) {
		this.height = height;
	}

	public String toString() {
		return this.element.toString()+ "(" + getBF() + ")";
	}

	public void print() {
		System.out.println(toString());
	}
	
	int getBF() {
		if(right == null && left == null) {
			return 0;
		}
		else if(right == null) {
			return -getHeight();
		}
		else if( left == null) {
			return getHeight();
		}
		else{
			return right.getHeight()-left.getHeight();
		}
	}
}
