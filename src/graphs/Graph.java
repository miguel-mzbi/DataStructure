package graphs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;

public class Graph {
	HashMap<String, Vertex> vertexMap; //All vertex
	
	public Graph(){
		this.vertexMap = new HashMap<String, Vertex>();
	}
	
	private Vertex getVertex(String name){
		if(this.vertexMap.get(name) != null)
			return this.vertexMap.get(name);
		else{
			Vertex nVertex = new Vertex(name);
			this.vertexMap.put(name, nVertex);
			return nVertex;
		}
	}
	
	public void addEdge(String origin, String destiny, double cost){
		Vertex vertexA = this.getVertex(origin);
		Vertex vertexB = this.getVertex(destiny);
		
		vertexA.adyacents.add(new Edge(vertexB, cost));
	}
	
	public String breadthPath(String origin){
		Vertex vertexOrigin = this.getVertex(origin);
		Iterator<Entry<String, Vertex>> it = this.vertexMap.entrySet().iterator();
		while(it.hasNext()){
			it.next().getValue().color = 0;
		}
		return origin + this.breadthPath(vertexOrigin);
	}
	
	private String breadthPath(Vertex vertexOrigin){
		Queue<Vertex> adjacentVertex = new LinkedList<Vertex>();
		String output = "";
		vertexOrigin.color = 2;
		for(int i = 0; i < vertexOrigin.adyacents.size(); i++){
			Vertex vertexToGo = vertexOrigin.adyacents.get(i).destiny;
			if(vertexToGo.color == 0){
				output += vertexToGo.name;
				vertexToGo.color = 1;
				adjacentVertex.add(vertexToGo);
			}
		}
		Vertex nextVertex;
		while(!adjacentVertex.isEmpty()){
			nextVertex = adjacentVertex.poll();
			if(nextVertex.color == 1){
				nextVertex.color = 2;
				output += this.breadthPath(nextVertex);
			}
			else
				nextVertex.color = 2;
		}
		return output;
	}
	
	public String depthPath(String origin){
		Vertex vertexOrigin = this.getVertex(origin);
		Iterator<Entry<String, Vertex>> it = this.vertexMap.entrySet().iterator();
		while(it.hasNext()){
			it.next().getValue().color = 0;
		}
		return this.depthPath(vertexOrigin);
	}
	
	private String depthPath(Vertex vertexOrigin){
		String output = "";
		vertexOrigin.color = 1;
		for(int i = 0; i < vertexOrigin.adyacents.size(); i++){
			Vertex vertexToGo = vertexOrigin.adyacents.get(i).destiny;
			if(vertexToGo.color == 0){
				output += this.depthPath(vertexToGo);
			}
		}
		output += vertexOrigin.name;
		vertexOrigin.color = 2;
		return output;
	}
	
}
