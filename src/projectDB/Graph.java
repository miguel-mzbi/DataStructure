package projectDB;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>class Graph</b>
 * <p>The purpose of this class is to store the difference of expense a person has with the rest of people.
 * The graph data structure is utilized.
 * @author MiguelMontoya - ArturoFornes
 */
public class Graph {
	HashMap<String,Vertex> vertexMap;
	final double maxValue = Double.MAX_VALUE;
	
	/**
	 * <b>Graph</b>
	 * <br><i>public Graph()</i>
	 * <p>Constructor for the class Graph.
	 * <br>The HashMap for all the vertex is initialized.
	 */
	public Graph(){
		vertexMap = new HashMap<String,Vertex>();
	}
	
	
	/**
	 * <b>getVertex</b>
	 * <br><i>public Vertex getVertex(String name)</i>
	 * <p>Gets the vertex, given a name.
	 * <br>If no vertex is found, a new vertex is created.
	 * @param name - (String) Name of the person, whose vertex is requested.
	 * @return Vertex with the requested name.
	 */
	public Vertex getVertex(String name){
		Vertex toReturn = this.vertexMap.get(name);
		if(toReturn != null)
			return toReturn;
		Vertex toAdd = new Vertex(name);
		this.vertexMap.put(name, toAdd);
		return toAdd;
	}
	
	/**
	 * <b>addEdge</b>
	 * <br><i>public void addEdge(String origin, String destination, double cost)</i>
	 * <p>Adds an edge between two vertex.
	 * @param origin - (String) Name of the vertex's origin.
	 * @param destination - (String) Name of the vertex's destination.
	 * @param cost - (Double) The expense difference between two people.
	 */
	public void addEdge(String origin, String destination, double cost){
		Vertex v = this.getVertex(origin);
		Vertex w = this.getVertex(destination);
		v.adjList.add(new Edge(w,cost));
	}
	
	/**
	 * <b>changeEdge</b>
	 * <br><i>public void changeEdge(String origin, String destination, double cost)</i>
	 * <p>Changes the value of an existing edge.
	 * @param origin - (String) Name of the vertex's origin.
	 * @param destination - (String) Name of the vertex's destination.
	 * @param cost - (Double) The expense difference between two people.
	 */
	public void changeEdge(String origin, String destination, double cost){
		Vertex v = this.getVertex(origin);
		Vertex w = this.getVertex(destination);
		int size = v.adjList.size();
		for(int i = 0; i < size; i++){
			if(v.adjList.get(i).destination.equals(w)){
				v.adjList.get(i).cost = cost;
				break;
			}
		}
		this.addEdge(origin, destination, cost);
	}
	
	/**
	 * <b>removeEdge</b>
	 * <br><i>public void removeEdge(String origin, String destination)</i>
	 * <p>Removes an edge between two vertex.
	 * @param origin - (String) Name of the vertex's origin.
	 * @param destination - (String) Name of the vertex's destination.
	 */
	public void removeEdge(String origin, String destination){
		Vertex v = this.getVertex(origin);
		Vertex w = this.getVertex(destination);
		int size = v.adjList.size();
		for(int i = 0; i < size; i++){
			if(v.adjList.get(i).destination.equals(w)){
				v.adjList.remove(i);
				break;
			}
		}
	}
	
	/**
	 * <b>removeVertex</b>
	 * <br><i>public void removeVertex(String name)</i>
	 * <p>Removes the given vertex.
	 * @param name - (String) Name of the vertex to remove
	 */
	public void removeVertex(String name){
		for(Map.Entry<String, Vertex> u: this.vertexMap.entrySet()){
			if(!u.getValue().name.equals(name))
				this.removeEdge(u.getValue().name, name);	
		}
		vertexMap.remove(name);
	}
	
	/**
	 * <b>getCost</b>
	 * <br><i>public double getCost(String origin, String destination)</i>
	 * <p>Gets the cost (Expense difference) between two vertex.
	 * @param origin - (String) Name of the vertex's origin.
	 * @param destination - (String) Name of the vertex's destination.
	 * @return Cost (Expense difference) between two vertex.
	 */
	public double getCost(String origin, String destination){
		Vertex v = this.getVertex(origin);
		Vertex w = this.getVertex(destination);
		int size = v.adjList.size();
		for(int i = 0; i < size; i++){
			if(v.adjList.get(i).destination.equals(w)){
				return v.adjList.get(i).cost;
			}
		}
		return 0;
	}
}
