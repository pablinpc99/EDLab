package src;

import test.TestBench;

public class Algorithms {

	/**
	 * O(n) method called 'linear', for a given workload called 'n'
	 * 
	 * @param n
	 */
	public static void linear(long n) {
		for (long i = 0; i <= n; i++)
			TestBench.doNothing(n);
	}

	/**
	 * O(n^2) method called 'quadratic', for a given workload called 'n'
	 * 
	 * @param n
	 */
	public static void quadratic(long n) {
		int counter = 0;
		for (long i = 0; i < n; i++) {
			for (long j = 0; j < n; j++) {
				TestBench.doNothing(counter++);
			}
		}
	}

	/**
	 * O(n^3) method called 'cubic', for a given workload called 'n'
	 * 
	 * @param n
	 */
	public static void cubic(long n) {
		int counter = 0;
		for (long i = 0; i < n; i++) {
			for (long j = 0; j < n; j++) {
				for (long k = 0; k < n; k++) {
					TestBench.doNothing(counter++);
				}
			}
		}
	}

	/**
	 * Logarithmic method
	 * 
	 * @param n
	 */
	public static void logarithmic(long n) {
		int i = 0;
		while (n > 0) {
			TestBench.doNothing(i++);
			n = n / 2;
		}

	}
	
	public static long factorial(long n) {
		int result = 1;
		for(int i = 2; i <=n; i++ ) {
			result*=i;
		}
		return result;
		
	}
	
	public static long factorialRec(long n) {
		if((n==0) || (n==1)) {
			return 1;
		} else {
			return n * factorial(n-1);
		}
		
	}

	public static long pow(long n) {// n complexity
		long result = 1;
		for (long i = 0; i < n; i++) {
			TestBench.doNothing(i);
			result *= 2;
		}
		return result;
	}

	public static long powRec1(long n) {// exponentional complexity(2^n)
		if (n == 0) {
			return 1;
		} else {
			return powRec1(n - 1) + powRec1(n - 1);
		}

	}

	public static long powRec2(long n) {// n complexity
		if (n == 0) {
			return 1;
		} else {
			long subresult = powRec2(n - 1);
			return subresult + subresult;
		}

	}

	public static long powRec3(long n) {// complexity????
		if (n == 0)
			return 1;
		if (n % 2 != 0)
			return 2 * powRec3(n / 2) * powRec3(n / 2);
		else
			return powRec3(n / 2) * powRec3(n / 2);

	}
	
	
	public static long powRec4(long n) {// complexity log
		if (n == 0)
			return 1;
		
		long subresult = powRec4(n/2);
		if (n % 2 != 0)
			return 2 * subresult * subresult;
		else
			return subresult * subresult;

	}
}
