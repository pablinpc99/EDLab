package test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class TestBench {

	public static final long SLEEP_TIME = 2;

	public static void main(String[] args) {
//		testLinear("linear.csv", 3, 1, 50);
//		testQuadratic("quadratic.csv", 3, 1, 50);
//		testCubic("cubic.csv", 3, 1, 20);
//		testLogarithmic("logarithmic.csv", 3, 1, 50);

//		try {
//			test("test19.Algorithms", "linear", 50, "linear.csv", 3, 1, 50);
//			testAlgorithm("test19.Algorithms", "quadratic", 50);
//			testAlgorithm("test19.Algorithms", "cubic", 20);
//			testAlgorithm("test19.Algorithms", "logarithmic", 50);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//test("src.GraphPerformanceTest", "runDijkstra", "GraphDijkstra.csv", 3, 100, 300);
		//test("src.GraphPerformanceTest", "runFloyd", "GraphFloyd.csv", 3, 100, 300);
		test("src.GraphPerformanceTest", "initGraph", "GraphBuild.csv", 3, 100, 300);
		
	}
		

	/**
	 * 'doNOthing' method
	 * 
	 * @param i
	 */
	public static void doNothing(long i) {
		System.out.println("Iteration " + i);
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}

	public static void testAlgorithm(String className, String methodName, long n) throws Exception {
		Class<?> myClass = null;
		Object myObject = null;

		//Loads the class dynamically using reflection
		myClass = Class.forName(className);
		myObject = myClass.newInstance();

		//Gets a method instance
		Class<?>[] params = new Class[1];
		params[0] = Long.TYPE;

		Method m = myClass.getMethod(methodName, params);

		//Calls the method in java using reflection
		m.invoke(myObject, n);

	}
	
	//MAIN TEST
	public static void test(String className, String methodName, String outputFileName, long samples, long startN, long endN) {
		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter(outputFileName);
			pw = new PrintWriter(fw);
			for (long n = startN; n <= endN; n++) {

				long startTime;
				long endTime;
				long totalTime = 0;

				for (long j = samples; j > 0; j--) {
					
					startTime = System.currentTimeMillis();
					
					Class<?> myClass = null;
					Object myObject = null;
					
					//Loads the class dynamically using reflection
					myClass = Class.forName(className);
					myObject = myClass.newInstance();
					
					//Gets a method instance
					Class<?>[] params = new Class[1];
					params[0] = Long.TYPE;
					
					Method m = myClass.getMethod(methodName, params);
					
					//Calls the method in java using reflection
					m.invoke(myObject, n);
	
					endTime = System.currentTimeMillis();
					totalTime += endTime - startTime;
				}

				pw.println(totalTime / samples);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	

//	/**
//	 * 'test' method to estimate the algorithm execution time
//	 * 
//	 * @param n
//	 */
//	public static void test(long n) {
//		long startTime;
//		long endTime;
//
//		startTime = System.currentTimeMillis();
//		Algorithms.linear(n);
//		endTime = System.currentTimeMillis();
//
//		System.out.println("Time: " + (endTime - startTime) + " ms");
//	}
//
//	/**
//	 * 'test' method to estimate the algorithm execution time. And save the result
//	 * in a text file using a given range for the workload [startN, endN]
//	 * 
//	 * @param n
//	 */
//	public static void test(String outputFileName, long startN, long endN) {
//		FileWriter fw = null;
//		PrintWriter pw = null;
//
//		try {
//			fw = new FileWriter(outputFileName);
//			pw = new PrintWriter(fw);
//			for (long n = startN; n <= endN; n++) {
//				long startTime;
//				long endTime;
//
//				startTime = System.currentTimeMillis();
//				Algorithms.linear(n);
//				endTime = System.currentTimeMillis();
//
//				pw.println(endTime - startTime);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fw != null) {
//					fw.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 'test' method to estimate the algorithm execution time, save the result in a
//	 * text file using a given range for the workload [startN, endN] and record
//	 * several samples.
//	 * 
//	 * @param n
//	 */
//	public static void testLinear(String outputFileName, long samples, long startN, long endN) {
//		FileWriter fw = null;
//		PrintWriter pw = null;
//
//		try {
//			fw = new FileWriter(outputFileName);
//			pw = new PrintWriter(fw);
//			for (long n = startN; n <= endN; n++) {
//
//				long startTime;
//				long endTime;
//				long totalTime = 0;
//
//				for (long j = samples; j > 0; j--) {
//					startTime = System.currentTimeMillis();
//					//Algorithms.linear(n);
//					testAlgorithm(className, methodName, endTime);
//					endTime = System.currentTimeMillis();
//					totalTime += endTime - startTime;
//				}
//
//				pw.println(totalTime / samples);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fw != null) {
//					fw.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 'test' method to estimate the algorithm execution time, save the result in a
//	 * text file using a given range for the workload [startN, endN] and record
//	 * several samples.
//	 * 
//	 * @param n
//	 */
//	public static void testQuadratic(String outputFileName, long samples, long startN, long endN) {
//		FileWriter fw = null;
//		PrintWriter pw = null;
//
//		try {
//			fw = new FileWriter(outputFileName);
//			pw = new PrintWriter(fw);
//			for (long n = startN; n <= endN; n++) {
//
//				long startTime;
//				long endTime;
//				long totalTime = 0;
//
//				for (long j = samples; j > 0; j--) {
//					startTime = System.currentTimeMillis();
//					Algorithms.quadratic(n);
//					endTime = System.currentTimeMillis();
//					totalTime += endTime - startTime;
//				}
//
//				pw.println(totalTime / samples);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fw != null) {
//					fw.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 'test' method to estimate the algorithm execution time, save the result in a
//	 * text file using a given range for the workload [startN, endN] and record
//	 * several samples.
//	 * 
//	 * @param n
//	 */
//	public static void testCubic(String outputFileName, long samples, long startN, long endN) {
//		FileWriter fw = null;
//		PrintWriter pw = null;
//
//		try {
//			fw = new FileWriter(outputFileName);
//			pw = new PrintWriter(fw);
//			for (long n = startN; n <= endN; n++) {
//
//				long startTime;
//				long endTime;
//				long totalTime = 0;
//
//				for (long j = samples; j > 0; j--) {
//					startTime = System.currentTimeMillis();
//					Algorithms.cubic(n);
//					endTime = System.currentTimeMillis();
//					totalTime += endTime - startTime;
//				}
//
//				pw.println(totalTime / samples);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fw != null) {
//					fw.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 'test' method to estimate the algorithm execution time, save the result in a
//	 * text file using a given range for the workload [startN, endN] and record
//	 * several samples.
//	 * 
//	 * @param n
//	 */
//	public static void testLogarithmic(String outputFileName, long samples, long startN, long endN) {
//		FileWriter fw = null;
//		PrintWriter pw = null;
//
//		try {
//			fw = new FileWriter(outputFileName);
//			pw = new PrintWriter(fw);
//			for (long n = startN; n <= endN; n++) {
//
//				long startTime;
//				long endTime;
//				long totalTime = 0;
//
//				for (long j = samples; j > 0; j--) {
//					startTime = System.currentTimeMillis();
//					Algorithms.logarithmic(n);
//					endTime = System.currentTimeMillis();
//					totalTime += endTime - startTime;
//				}
//
//				pw.println(totalTime / samples);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fw != null) {
//					fw.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * Reflective method
//	 * 
//	 * @param className
//	 * @param methodName
//	 * @param n
//	 * @throws Exception
//	 */
//	public static void testAlgorithmAlternative(String className, String methodName, long n) throws Exception {
//		Class<?> algorithmClass = Class.forName(className);
//		Object algorithmObject = algorithmClass.getDeclaredConstructor().newInstance();
//		Method runAlgorithm = algorithmClass.getMethod(methodName, long.class);
//		runAlgorithm.invoke(algorithmObject, n);
//	}

}
