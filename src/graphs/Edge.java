package graphs;

public class Edge {
	Vertex destiny;//Vertex to which it directs
	double weight;//Weight of the edge (For weighed graphs)
	
	public Edge(Vertex v, double w){
		this.destiny = v;
		this.weight = w;
	}
}
