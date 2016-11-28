package projectDB;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>class Vertex/b>
 * <p>The purpose of this class is to create vertexes for a graph.
 * Each vertex has always a name (The person name) and a list of edges to other vertex.
 * @author MiguelMontoya - ArturoFornes
 */
public class Vertex {
	String name;
	List<Edge> adjList;
	short color;
	double distance;
	Vertex pi;
	
	/**
	 * <b>Vertex</b>
	 * <br><i>public Vertex(String name)</i>
	 * <p>Constructor for the class Vertex.
	 * @param name - (String) Name of the person
	 */
	public Vertex(String name){
		this.name = name;
		adjList = new ArrayList<Edge>();
	}
	
	
	public String toString(){
		return this.name;
	}
}
