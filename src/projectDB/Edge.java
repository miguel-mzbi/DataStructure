package projectDB;

/**
 * <b>class Edge</b>
 * <p>The purpose of this class is to create edges for a graph.
 * Each edge has always a destination (The personB's name) 
 * and a cost (The expense difference between the person who owns the edge and the personB).
 * @author MiguelMontoya - ArturoFornes
 */
public class Edge {
	Vertex destination;
	double cost;
	
	/**
	 * <b>Edge</b>
	 * <br><i>public Edge(Vertex destination, double cost)</i>
	 * <p>Constructor for the class Edge.
	 * @param destination - (Vertex) Destination Vertex
	 * @param cost - (Double) Cost, expense difference between the people.
	 */
	public Edge(Vertex destination, double cost){
		this.destination = destination;
		this.cost = cost;
	}
	
	public String toString(){
		return this.destination+" "+this.cost;
	}
}