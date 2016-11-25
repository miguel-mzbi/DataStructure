package graphs;

import java.util.LinkedList;

public class Vertex {
	String name; //Vertex name
	LinkedList<Edge> adyacents; //Vertex adjacent to a vertex (Adjacency is established with edges)
	int color; //0 = WHITE, 1 = GRAY, 2 = BLACK
	
	public Vertex(String n){
		this.name = n;
		this.adyacents = new LinkedList<Edge>();
//		this.color = 0;
	}
	
	public String toString(){
		return "N:" + name + " C:" + color;
		
	}
}
