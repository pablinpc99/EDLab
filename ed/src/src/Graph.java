package src;

import java.util.ArrayList;

public class Graph<T> {
	
	public final static int INDEX_NOT_FOUND = -1;
	public final static double INFINITE = Double.MAX_VALUE;
	public final static int EMPTY = -1;
	
	private ArrayList<GraphNode<T>> nodes;
	protected boolean[][] edges;
	protected double[][] weight;
	private int size;
	
	protected double a[][];
	protected int p[][];
	protected double d[];
	protected int pd[];
	
	public Graph(int n) {
		nodes = new ArrayList<GraphNode<T>>(n);
		edges = new boolean[n][n];
		weight = new double[n][n];
		size = n;
	}

	public ArrayList<GraphNode<T>> getNodes() {
		return nodes;
	}

	public boolean[][] getEdges() {
		return edges;
	}

	public double[][] getWeights() {
		return weight;
	}
	
	/**
	 * Return the position of a node we looking for
	 * @param node we are looking for
	 * @return the position of the node if it exists. Otherwise,-1
	 */
	public int getNode(T element){
		int pos = INDEX_NOT_FOUND;
		for (int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).getElement().equals(element)) {
				pos = i;
			}
		}
		return pos;
	}
	
	public int getSize() {
		return nodes.size();
	}
	
	/**
	 * Given the element, add a new GraphNode to the list of nodes
	 * @param element
	 * @throws Exception if the element is null, the graph is full or
	 * the node doesn't exit
	 */
	public void addNode(T element) throws Exception{
		if(getNode(element)==INDEX_NOT_FOUND && getSize()<size && element!=null) {
			nodes.add(new GraphNode<T>(element));
			for (int i = 0; i < getSize(); i++) {
				weight[i][getSize()-1]=INFINITE;
				weight[getSize()-1][i]=INFINITE;
				edges[i][getSize()-1]=false;
				edges[getSize()-1][i]=false;
			}
		}
		else {
			throw new Exception();
		}
	}
	
	/**
	 * Check if an edge exist
	 * @param origin
	 * @param dest
	 * @return true if the edge exist. Otherwise, false
	 * @throws Exception if the element is null or the node doesn't exist
	 */
	public boolean existsEdge(T origin,T dest) throws Exception{
		int posOrigin = getNode(origin);
		int posDest = getNode(dest);
		if(posOrigin == -1 || posDest == -1 || origin == null || dest == null) {
			throw new Exception();
		}
		else {
			if(edges[posOrigin][posDest]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Add an edge, changing the value in the edges table and the weights table
	 * @param origin
	 * @param dest
	 * @param weight
	 * @throws Exception if the element is null, the node doesn't exist or
	 * the weight is lower than 0
	 */
	public void addEdge(T origin, T dest, double weight) throws Exception{
		int posOrigin = getNode(origin);
		int posDest = getNode(dest);
		if(posOrigin == -1 || posDest == -1 || origin == null || dest == null || weight<0) {
			throw new Exception();
		}
		else {
			edges[posOrigin][posDest] = true;
			this.weight[posOrigin][posDest] = weight;
		}
	}
	
	/** Removes a selected node from the graph
	 * 
	 * @param node
	 *            element to be removed
	 * @throws Exception
	 *             thrown if the node provided is null or doesn't exist
	 */
	public void removeNode(T node) throws Exception {

		if (node.equals(null) || getNode(node) == INDEX_NOT_FOUND) {
			throw new RuntimeException("The node provided is null or doesn't exist ");
		}

		int nodePosition = getNode(node);
		int lastElement = nodes.size() - 1;

		for (int i = 0; i < nodes.size(); i++) {

			// Copy on the row of the node to be deleted the last row
			edges[nodePosition][i] = edges[lastElement][i];
			weight[nodePosition][i] = weight[lastElement][i];

			// Copy on the column of node to be deleted the last column
			edges[i][nodePosition] = edges[i][lastElement];
			weight[i][nodePosition] = weight[i][lastElement];

		}

		// Copy last element to the upper left corner
		edges[nodePosition][nodePosition] = edges[lastElement][lastElement];
		weight[nodePosition][nodePosition] = weight[lastElement][lastElement];

		// Put last node in the position of the one that is going to be deleted
		nodes.set(getNode(node), nodes.get(lastElement));

		// Remove last node since it is already in the deleted node position
		nodes.remove(lastElement);
	}
	
	/**
	 * Remove an edge, changing the value in the edges table and the weights table
	 * @param origin
	 * @param dest
	 * @throws Exception if the element is null, the node doesn't exist
	 */
	public void removeEdge (T origin,T dest) throws Exception{
		int posOrigin = getNode(origin);
		int posDest = getNode(dest);
		if(posOrigin == -1 || posDest == -1 || origin == null || dest == null) {
			throw new Exception();
		}
		else {
			edges[posOrigin][posDest] = false;
			this.weight[posOrigin][posDest] = 0;
		}
	}
	
	/**
	 * Print the edges and weight tables
	 */
	public void print() {
		for (int i = 0; i < edges.length; i++) {
			for (int j = 0; j < edges[i].length; j++) {
				System.out.print(edges[i][j] + "\t");
			}
			System.out.println("");
		}
		
		for (int i = 0; i < weight.length; i++) {
			for (int j = 0; j < weight[i].length; j++) {
				System.out.print(weight[i][j] + "\t");
			}
			System.out.println("");
		}
	}
	
	public boolean isSourceNode(T element) {
		int posEle = getNode(element);
		int outputEdges = 0;
		int inputEdges = 0;
		boolean isSource = false;
		for (int i = 0; i < edges.length; i++) {
			for (int j = 0; j < edges[i].length; j++) {
				if(edges[i][posEle]) {
					inputEdges++;
				}
				if(edges[posEle][i]) {
					outputEdges++;
				}
			}
		}
		if(inputEdges == 0 && outputEdges>0) {
			isSource = true;
		}
		return isSource;
	}
	
	public boolean isDrainNode(T element) {
		int posEle = getNode(element);
		int outputEdges = 0;
		int inputEdges = 0;
		boolean isDrain = false;
		for (int i = 0; i < edges.length; i++) {
			for (int j = 0; j < edges[i].length; j++) {
				if(edges[i][posEle]) {
					inputEdges++;
				}
				if(edges[posEle][i]) {
					outputEdges++;
				}
			}
		}
		if(inputEdges > 0 && outputEdges == 0) {
			isDrain = true;
		}
		return isDrain;
	}
	
	public int countDrainNodes() {
		int drainNodes = 0;
		for (int i = 0; i < getSize(); i++) {
			if(isDrainNode(nodes.get(i).getElement())) {
				drainNodes++;
			}
		}
		return drainNodes;
	}
	
	public int countSourceNodes() {
		int sourceNodes = 0;
		for (int i = 0; i < getSize(); i++) {
			if(isSourceNode(nodes.get(i).getElement())) {
				sourceNodes++;
			}
		}
		return sourceNodes;
	}
	
	public String traverseGraphDF(T element) {
		for (GraphNode<T> node : nodes) {
			node.setVisited(false);
		}
		int v = getNode(element);
		if(v!=-1) {
			return DFPrint(v);
		}
		else {
			return null;
		}
	}

	private String DFPrint(int v) {
		nodes.get(v).setVisited(true);
		String aux = nodes.get(v).getElement().toString() + "-";
		for (int i = 0; i < edges.length; i++) {
			if(edges[v][i] == true && nodes.get(i).isVisited()==false){
				aux+=DFPrint(i);
			}
		}
		return aux;
	}
	
	public void initsFloyd() {
		a = new double[size][size];
		p = new int[size][size];
		
		for (int i = 0; i < weight.length; i++) {
			for (int j = 0; j < weight[i].length; j++) {
				if(i==j) {
					a[i][j]=0;
					p[i][j]=EMPTY;
				}
				else if(edges[i][j]) {
					a[i][j]=weight[i][j];
					p[i][j]=EMPTY;
				}
				else {
					a[i][j]=INFINITE;
					p[i][j]=EMPTY;
				}
			}
		}
	}
	
	public void floyd(int An) {
		initsFloyd();
		for (int k = 0; k < An; k++) {
			for (int i = 0; i < getSize(); i++) {
				for (int j = 0; j < getSize(); j++) {
					if(a[i][k] + a[k][j] < a[i][j]) {
						a[i][j] = a[i][k] + a[k][j];
						p[i][j] = k;
					}
				}
			}
		}
	}
	
	public String printFLoydPath (T departure,T arrival) throws Exception{
		String aux = "";
		int posOrigin = getNode(departure);
		int posDest = getNode(arrival);
		int nextIteration = p[posOrigin][posDest];
		if(posDest != INDEX_NOT_FOUND && posOrigin != INDEX_NOT_FOUND) {
			if(nextIteration!=EMPTY) {
				T auxNode = nodes.get(nextIteration).getElement();
				aux+=printFLoydPath(departure, auxNode);
				aux+=auxNode.toString();
				aux+=printFLoydPath(auxNode,arrival);
				//aux+="V" + arrival;
			}
//			else {
//				aux+= "V" + departure;
//			}
		}
		return aux;
	}

	public int[][] getP() {
		return p;
	}

	public double[][] getA() {
		return a;
	}
	
	public double[] getD() {
		return d;
	}
	public double[][] getD2(){
		double[][] auxD = new double[1][d.length];
		for (int i = 0; i < auxD[0].length; i++) {
			auxD[0][i] = d[i];
		}
		return auxD;
	}
	
	public int[] getPD() {
		return pd;
	}
	public int[][] getPD2() {
		int[][] auxPD = new int[1][nodes.size()];
		for (int i = 0; i < auxPD[0].length; i++) {
			auxPD[0][i] = pd[i];
		}
		return auxPD;
	}
	
	public void initDijkstra(T departure) throws Exception {
		for (int i = 0; i <nodes.size(); i++) {
			nodes.get(i).setVisited(false);
		}
		if(getNode(departure)!=INDEX_NOT_FOUND) {
			nodes.get(getNode(departure)).setVisited(true);
		}
		d = new double[size];
		pd = new int[size];
		for (int i = 0; i < getSize(); i++) {
			if(existsEdge(departure, nodes.get(i).getElement())) {
				d[i] = weight[getNode(departure)][i];
				pd[i]=getNode(departure);
			}
			else {
				d[i]=INFINITE;
				pd[i]=EMPTY;
			}
		}
	}
	
	public void Dijkstra(T departure) throws Exception {
		initDijkstra(departure);
		int pivot = EMPTY;
		double minimumCost  = INFINITE;
		for (int i = 0; i < getSize(); i++) {
			pivot = EMPTY;
			minimumCost  = INFINITE;
			for (int p = 0; p < getSize(); p++) {
				if(d[p]<minimumCost && nodes.get(p).isVisited()==false) {
					pivot = p;
					minimumCost = d[p];
				}
			}
			if(pivot!=EMPTY && minimumCost!=INFINITE) {
				nodes.get(pivot).setVisited(true);
				for (int j = 0; j < getSize(); j++) {
					for (int k = 0; k < getSize(); k++) {
						if(existsEdge(nodes.get(j).getElement(), nodes.get(k).getElement())) {
							if(nodes.get(j).isVisited() && !nodes.get(k).isVisited()) {
								if(d[j] + weight[j][k] < d[k]) {
									d[k] = d[j] + weight[j][k];
									pd[k] = j;
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void DijkstraQuadratic(T departure) throws Exception {
		initDijkstra(departure);
		int pivot = EMPTY;
		double minimumCost  = INFINITE;
		for (int i = 0; i < d.length; i++) {
			pivot = EMPTY;
			minimumCost  = INFINITE;
			for (int p = 0; p < d.length; p++) {
				if(d[p]<minimumCost && nodes.get(p).isVisited()==false) {
					pivot = p;
					minimumCost = d[p];
				}
			}
			if(pivot!=EMPTY && minimumCost!=INFINITE) {
				nodes.get(pivot).setVisited(true);
				for (int k = 0; k < edges[pivot].length; k++) {
					if(existsEdge(nodes.get(pivot).getElement(), nodes.get(k).getElement())) {
						if(nodes.get(pivot).isVisited() && !nodes.get(k).isVisited()) {
							if(d[pivot] + weight[pivot][k] < d[k]) {
								d[k] = d[pivot] + weight[pivot][k];
								pd[k] = pivot;
							}
						}
					}
				}
			}
		}
	}
	
	
}
