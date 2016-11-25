package projectDB;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	String name;
	List<Edge> adjList;
	short color;
	double distance;
	Vertex pi;
	
	public Vertex(String name){
		this.name = name;
		adjList = new ArrayList<Edge>();
	}
	
	public String toString(){
		return this.name;
	}
}
