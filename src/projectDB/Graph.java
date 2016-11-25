package projectDB;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Graph {
	HashMap<String,Vertex> vertexMap;
	final double maxValue = Double.MAX_VALUE;
	
	public Graph(){
		vertexMap = new HashMap<String,Vertex>();
	}
	
	public Vertex getVertex(String name){
		Vertex toReturn = this.vertexMap.get(name);
		if(toReturn != null)
			return toReturn;
		Vertex toAdd = new Vertex(name);
		this.vertexMap.put(name, toAdd);
		return toAdd;
	}
	
	public void addEdge(String origin, String destination, double cost){
		Vertex v = this.getVertex(origin);
		Vertex w = this.getVertex(destination);
		v.adjList.add(new Edge(w,cost));
	}
	
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
	
	public void removeVertex(String name){
		for(Map.Entry<String, Vertex> u: this.vertexMap.entrySet()){
			if(!u.getValue().name.equals(name))
				this.removeEdge(u.getValue().name, name);	
		}
		vertexMap.remove(name);
	}
	
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
	
	public String breadthFirstSearch(String origin){
		String output = "";
		Vertex s = this.getVertex(origin);
		for(Map.Entry<String, Vertex> u: this.vertexMap.entrySet()){
			Vertex vertex = u.getValue();
			vertex.color = 1;
			vertex.pi = null;
			vertex.distance = this.maxValue;
		}
		s.color = 0;
		s.distance = 0;
		s.pi = null;
		
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(s);
		while(!q.isEmpty()){
			Vertex u = q.poll();
			output+=u.name;
			int size = u.adjList.size();
			for(int i = 0;i < size; i++){
				Vertex v = u.adjList.get(i).destination;
				if(v.color == 1){
					v.color = 0;
					v.distance = u.distance +1;
					v.pi = u;
					q.add(v);
				}
			}
			u.color = -1;
		}
		
		return output;
	}
	
	public String depthFirstSearch(String origin){
		String output = "";
		Vertex s = this.getVertex(origin);
		for(Map.Entry<String, Vertex> u: this.vertexMap.entrySet()){
			Vertex vertex = u.getValue();
			vertex.color = 1;
			vertex.pi = null;
			vertex.distance = this.maxValue;
		}
		int time = 0;
		output+=DFSVisit(time,s);
		return output;
	}
	
	public String DFSVisit(int time, Vertex u){
		String output = "";
		time = time+1;
		u.distance = time;
		u.color = 0;
		int size = u.adjList.size();
		for(int i = 0;i < size; i++){
			Vertex v = u.adjList.get(i).destination;
			if(v.color == 1){
				v.pi = u;
				output+=DFSVisit(time,v);
			}
		}
		u.color = -1;
		time = time+1;
		u.distance = time;
		output+=u.name;
		return output;
	}
}
