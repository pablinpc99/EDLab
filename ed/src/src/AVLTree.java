package src;

import java.util.ArrayList;

public class AVLTree <T extends Comparable<T>>{

	private AVLNode<T> root;
	
	public AVLTree(){
		this.root = null;
	}
	
	public AVLNode<T> getRoot(){
		return this.root;
	}
	
	public void add(T element){
		root = add(root,element); //Set the root 
	}
	
	private AVLNode<T> add(AVLNode<T> root, T element) {
		if(root == null) {
			return new AVLNode<>(element);
		}
		else if(element.compareTo(root.getElement())<0) {
			root.setLeft(add(root.getLeft(),element));
		}
		else if(element.compareTo(root.getElement())>0) {
			root.setRight(add(root.getRight(),element));
		}
		else {
			throw new RuntimeException("Element alredy exists!");
		}
		return updateBF(root);
	}
	
	public String toString() {
		return toString(root);
	}

	private String toString(AVLNode<T> root) {
		if(root !=null) {
			return (root.toString() + toString(root.getLeft()) + toString(root.getRight()));
		}
		else {
			return ("-");
		}
	}
	
	public boolean search(T element) {
		return search(root,element);
	}

	private boolean search(AVLNode<T> root, T element) {
		if(root == null) {
			return false;
		}
		else if (element.compareTo(root.getElement())<0) {
			return search(root.getLeft(),element);
		}
		else if(element.compareTo(root.getElement())>0){
			return search(root.getRight(),element);
		}
		else if(element.compareTo(root.getElement())==0){
			return true;
		}
		return false;
	}
	
	public T getMax (AVLNode<T> root) {
		if(root == null) {
			return null;
		}
		else {
			return getMaxRec(root);
		}
	}

	private T getMaxRec(AVLNode<T> root) {
		if(root.getRight() == null) {
			return root.getElement();
		}
		else {
			return getMaxRec(root.getRight());
		}
	}
	
	public void remove (T element) {
		root = remove(root,element);
	}

	private AVLNode<T> remove(AVLNode<T> root, T element) {
		if(root == null) {
			throw new RuntimeException("Element does not exist");
		}
		else if(element.compareTo(root.getElement())<0) {
			root.setLeft(remove(root.getLeft(),element));
		}
		else if(element.compareTo(root.getElement())>0) {
			root.setRight(remove(root.getRight(),element));
		}
		else {
			if(root.getLeft() == null){
				return root.getRight();
			}
			else if(root.getRight() == null){
				return root.getLeft();
			}
			else {
				root.setElement(getMax(root.getLeft()));
				root.setLeft(remove(root.getLeft(),root.getElement()));
			}
			
		}
		return updateBF(root);
	}

	private void updateHeight(AVLNode<T> root) {
		if(root.getLeft() == null && root.getRight() == null) {
			root.setHeight(0);
		}
		if(root.getLeft() != null && root.getRight() != null) {
			updateHeight(root.getLeft());
			updateHeight(root.getRight());
			root.setHeight(Math.max(root.getLeft().getHeight(), root.getRight().getHeight())+1);

		}
		else if((root.getLeft() == null || root.getRight() == null) && (root.getLeft() != null || root.getRight() != null))
			if(root.getLeft() == null) {
				updateHeight(root.getRight());
				root.setHeight(root.getRight().getHeight()+1);

			}
			else {
				updateHeight(root.getLeft());
				root.setHeight(root.getLeft().getHeight()+1);
			}
	}
	
	public AVLTree<T> joins (AVLTree<T> tree){
		AVLTree<T> joinedTree = new AVLTree<>();
		joinedTree = copyTree(root,joinedTree);
		joinedTree = joinTreeWithTree(joinedTree , tree);
		joinedTree.updateHeight(joinedTree.getRoot());
		return joinedTree;
		
	}

	private AVLTree<T> copyTree(AVLNode<T> root, AVLTree<T> joinedTree) {
		try{
			if(!joinedTree.search(root.getElement())) {
				joinedTree.add(root.getElement());
			}
			
		if(root.getLeft() != null) {
			copyTree(root.getLeft(),joinedTree);
		}
		if(root.getRight() != null) {
			copyTree(root.getRight(),joinedTree);
		}
		} catch ( RuntimeException e) {
			e.getMessage();
		}
	return joinedTree;
	}

	private AVLTree<T> joinTreeWithTree(AVLTree<T> joinedTree, AVLTree<T> tree) {
		joinedTree.copyTree(tree.getRoot(),joinedTree);
		return joinedTree;
	}
	
	public AVLTree<T> intersection (AVLTree<T> tree){
		AVLTree<T> joinedTree = new AVLTree<>();
		AVLTree<T> intersectionTree = new AVLTree<>();
		joinedTree = copyTree(tree.root,joinedTree);
		intersectionTree = commomElementsTree(joinedTree,root,intersectionTree);
		return intersectionTree;
		
	}

	private AVLTree<T> commomElementsTree(AVLTree<T> joinedTree, AVLNode<T> avlNode, AVLTree<T> intersectionTree) {
		if(joinedTree.search(avlNode.getElement())){
			intersectionTree.add(avlNode.getElement());
		}

		if(avlNode.getLeft() != null) {
			commomElementsTree(joinedTree, avlNode.getLeft(), intersectionTree);
		}
		if (avlNode.getRight() != null) {
			commomElementsTree(joinedTree, avlNode.getRight(), intersectionTree);
		}
		return intersectionTree;
	}
	

	
	private AVLNode<T> updateBF(AVLNode<T> root){
		updateHeight(root);
		if (root.getBF() == -2) 
		{ 	
			if (root.getLeft().getBF() <= 0) {
				root = singleLeftRotation (root);
			}
			else {
				root = doubleLeftRotation (root);	
			}
		}
		else
			if (root.getBF() == 2) 
			{ 
				if (root.getRight().getBF() >= 0) {
					root = (singleRightRotation (root));
				}
				else {
				root = (doubleRightRotation (root));
				}
			}
		updateHeight(root);
		return root;
	}
		
	private AVLNode<T> singleLeftRotation(AVLNode<T> root) {
		AVLNode<T> leftRoot = root.getLeft();
		root.setLeft(leftRoot.getRight());
		leftRoot.setRight(root);
		return leftRoot;
	}
	
	private AVLNode<T> singleRightRotation(AVLNode<T> root) {
		AVLNode<T> rightRoot = root.getRight();
		root.setRight(rightRoot.getLeft());
		rightRoot.setLeft(root);
		return rightRoot;
	}
	
	private AVLNode<T> doubleLeftRotation(AVLNode<T> root) {
		root.setLeft(singleRightRotation(root.getLeft()));
		return singleLeftRotation(root);
	}
	
	private AVLNode<T> doubleRightRotation(AVLNode<T> root) {
		root.setRight(singleLeftRotation(root.getRight()));
		return singleRightRotation(root);
	}
	
	public int getHeight() {
		if(this.getRoot() == null) {
			return 0;
		}
			
		if(root.getLeft() == null && root.getRight() == null) {
			return 1;
		}
		else {
			return getHeight(root,1);
		}
}

	private int getHeight(AVLNode<T> root,int height) {
		if(root.getLeft() != null && root.getRight() == null){
			height = getHeight(root.getLeft(),height+1);
		}
		if(root.getRight() !=null &&root.getLeft() == null) {
			height = getHeight(root.getRight(),height+1);
		}
		if(root.getLeft() != null && root.getRight() != null) {
			height = Math.max(getHeight(root.getLeft(),height+1),getHeight(root.getRight(),height+1));
		}
		return height;
	}
	
	public String printBFS(AVLNode<T> root) {
		StringBuilder sb = new StringBuilder();
		ArrayList<AVLNode<T>> auxArray = new ArrayList<AVLNode<T>>();
		auxArray.add(root);
		while(!auxArray.isEmpty()) {
			sb.append(auxArray.get(0).getElement().toString() + " - ");
			if(auxArray.get(0).getLeft() != null) {
				auxArray.add(auxArray.get(0).getLeft());
			}
			if(auxArray.get(0).getRight() != null){
				auxArray.add(auxArray.get(0).getRight());
			}
			auxArray.remove(0);
		}
		return sb.toString();
		
	}
		
}
