package graphs;

public class GraphMain {
	public static void main(String[] args){
		Graph g = new Graph();
		g.addEdge("1", "3", 0);
		g.addEdge("3", "1", 0);

		g.addEdge("4", "3", 0);
		g.addEdge("3", "4", 0);

		g.addEdge("3", "2", 0);
		g.addEdge("2", "3", 0);

		g.addEdge("3", "5", 0);
		g.addEdge("5", "3", 0);

		g.addEdge("5", "6", 0);
		g.addEdge("6", "5", 0);

		g.addEdge("6", "7", 0);
		g.addEdge("7", "6", 0);

		g.addEdge("2", "7", 0);
		g.addEdge("7", "2", 0);

		System.out.println("BP");
		System.out.println(g.breadthPath("3"));
		System.out.println(g.breadthPath("2"));
		
		System.out.println("DP");
		System.out.println(g.depthPath("3"));
		System.out.println(g.depthPath("2"));


	}
}
