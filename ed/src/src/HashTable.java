package src;

import java.util.ArrayList;

public class HashTable<T> {
	
	public final static int LINEAR_PROBING    = 0;
	public final static int QUADRATIC_PROBING = 1;
	public final static int DOUBLE_HASHING = 2;
	public final static int MAX_TRIES = 20000;

	private int B = 7; 
	private int redispersionType = LINEAR_PROBING ;
	private double minLF = 0.5;
	private ArrayList<HashNode<T>> associativeArray;
	private double n;
	private int R = 5;

	
	
	public HashTable(int B, int redispersionType, double minLF) {
		if(B <= 0) {
			throw new IllegalArgumentException();
		}
		if(minLF<0) {
			throw new IllegalArgumentException();
		}
		this.B= isPrime(B)? B : getNextPrimeNumber(B);
		this.redispersionType=redispersionType;
		this.minLF=minLF;
		this.associativeArray = new ArrayList<HashNode<T>>(this.B);
		for (int i = 0; i < this.B; i++) {
			associativeArray.add(new HashNode<T>());
		}
		this.R = getPrevPrimeNumber(B);
	}
	
	/**
	* Hashing function
	 * @param <T>
	* 
	* @param element to be stored.
	* @param i Attempt number.
	* @return slot in the array where the element should be 
	* placed
	*/
	@SuppressWarnings("hiding")
	public <T> int f (T element, int i){
	    switch (redispersionType) {
	        case LINEAR_PROBING:  
	        	return (Math.abs(element.hashCode())+i)%this.B;
	        case QUADRATIC_PROBING:
	        	return (Math.abs(element.hashCode())+((int)Math.pow(i, 2)))%B;
	        case  DOUBLE_HASHING:
	        	return  (Math.abs(element.hashCode()+i*(R - Math.abs(element.hashCode() % R))))%B;
	        	
	    }
	    return 0;
	}
	
	public void print() {
		System.out.println(toString());
	}
	
	public String toString() {		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < associativeArray.size(); i++) {
			sb.append("[" + i +"]" + " ("+ associativeArray.get(i).getStatus()+ ")");
			try {
				sb.append(" = " + associativeArray.get(i).getElement().toString() + " - ");
			} catch (NullPointerException e) {
				sb.append(" = " + null + " - ");
			}
		}
		return sb.toString();
	}
	
	public double getLF() {
		return n/(double)B;
	}
	
	public void add (T element) throws Exception {
		if(n >= B) {
			throw new Exception();
		}
		else {
			for (int i = 0; i < MAX_TRIES; i++) {
				int pos = f(element,i);
				if(associativeArray.get(pos).getStatus()==HashNode.EMPTY
						|| associativeArray.get(pos).getStatus()==HashNode.DELETED) {
					this.n++;
					associativeArray.get(pos).setStatus(HashNode.VALID);
					associativeArray.get(pos).setElement(element);
					break;
				}
			}
			if (getLF() > minLF) {
				dynamicResize();
			}			
		}
	}
	
	public boolean search(T element) {
		for (int i = 0; i < MAX_TRIES; i++) {
			int pos = f(element,i);
			if(associativeArray.get(pos).getStatus()==HashNode.VALID) {
				if(associativeArray.get(pos).getElement().equals(element)) {
					return true;
				}
			}
			else if(associativeArray.get(pos).getStatus()==HashNode.EMPTY) {
				return false;
			}
		}
		return false;
	}
	
	public void remove (T element) {
		for (int i = 0; i < MAX_TRIES; i++) {
			int pos = f(element,i);
			if(associativeArray.get(pos).getStatus()==HashNode.VALID) {
				if(associativeArray.get(pos).getElement().equals(element)) {
					this.n--;
					associativeArray.get(pos).setStatus(HashNode.DELETED);
					break;
				}
			}
			else if(associativeArray.get(pos).getStatus()==HashNode.EMPTY) {
				break;
			}
		}
	}
	
	public boolean isPrime(int number) {
		for (int i = 2; i < number; i++) {
			if((number%i)==0) {
				return false;
			}
		}
		return true;
	}

	public int getNextPrimeNumber(int number) {
		if(isPrime(number+1)) {
			return number+1;
		}
		else {
			return getNextPrimeNumber(number+1);
		}
	}
	
	public int getPrevPrimeNumber(int number) {
		if(isPrime(number-1)) {
			return number-1;
		}
		else {
			return getPrevPrimeNumber(number-1);
		}
	}
	
	@SuppressWarnings("static-access")
	protected void dynamicResize (int newSize) throws Exception {
		ArrayList<HashNode<T>> aa = associativeArray;
		associativeArray = new ArrayList<HashNode<T>>(newSize);
		for (int i = 0; i < associativeArray.size(); i++) {
			associativeArray.add(new HashNode<>());
		}
		B = newSize;
		R = getPrevPrimeNumber(B);
		n = 0;
		for (HashNode<T> hashNode : aa) {
			if(hashNode.getStatus() == hashNode.VALID) {
				add(hashNode.getElement());
			}
		}
	}
	
	@SuppressWarnings("static-access")
	protected void dynamicResize () throws Exception {
		ArrayList<HashNode<T>> aa = associativeArray;
		associativeArray = new ArrayList<HashNode<T>>(getNextPrimeNumber(B*2));
		for (int i = 0; i < getNextPrimeNumber(B*2); i++) {
			associativeArray.add(new HashNode<>());
		}
		B = getNextPrimeNumber(B*2);
		R = getPrevPrimeNumber(B);
		n = 0;
		for (HashNode<T> hashNode : aa) {
			if(hashNode.getStatus() == hashNode.VALID) {
				add(hashNode.getElement());
			}
		}
	}


}
