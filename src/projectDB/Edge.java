package projectDB;

public class Edge {
	Vertex destination;
	double cost;
	
	public Edge(Vertex destination, double cost){
		this.destination = destination;
		this.cost = cost;
	}
	
	public String toString(){
		return this.destination+" "+this.cost;
	}
}