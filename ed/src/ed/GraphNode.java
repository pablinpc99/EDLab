package ed;

public class GraphNode<T> {
	private T element;
	private boolean visited;
	
	public GraphNode(T e) {
		element = e;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	public String toString() {
		return "GN (N:" + element + "/V:" + visited + ")";
	}
	
	public void print() {
		System.out.println(toString());
	}

}
