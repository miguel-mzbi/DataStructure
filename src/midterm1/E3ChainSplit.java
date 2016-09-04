package midterm1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class E3ChainSplit<Item> {
	
	protected ChainNode<Item> firstNode;
	protected int size;
	
	public E3ChainSplit(){
		this.size = 0;
		this.firstNode = new ChainNode<Item>();
	}
	
	public void append(Item item){
		if(this.size == 0){
			this.firstNode = new ChainNode<Item>(item);
		}
		else{
			ChainNode<Item> prev;
			prev = this.firstNode;
			for(int i = 0; i < this.size -1; i++){
				prev = prev.next;
			}
			prev.next = new ChainNode<Item>(item);
		}
		this.size++;
	}
	
	public void clear(){
		this.firstNode = null;
		this.size = 0;
	}
	
	public Itr iterator(){
		return new Itr();
		
	}
	
	public String output() {
		StringBuilder out = new StringBuilder("[");
		ChainNode<Item> temp = this.firstNode;
		for(int i = 0; i<this.size; i++){
			out.append("[");
			out.append(temp.element);
			out.append("]");
			temp = temp.next;
		}
		out.append("]");
		return (out.toString());
	}
	
	private static class ChainNode<Item> {
		protected Item element;
		protected ChainNode<Item> next;
		
		public ChainNode(){
			this(null,null);
		}
		public ChainNode(Item element){
			this(element,null);
		}
		public ChainNode(Item element, ChainNode<Item> next){
			this.element = element;
			this.next = next;
		}
	}
	
	protected class Itr implements Iterator<Item>{
		private int nextIndex;
		private ChainNode<Item> nextNode;
		
		public Itr(){
			this.nextIndex = 0;
			this.nextNode = firstNode;
		}
		public boolean hasNext() {
			return(this.nextIndex < size);
		}

		public Item next() {
			if(!hasNext()){
				throw new NoSuchElementException("No more elements");
			}
			ChainNode<Item> toReturn = this.nextNode;
			this.nextNode = this.nextNode.next;
			this.nextIndex++;
			return toReturn.element;
		}
	}
	
//	O(n) because every method used takes O(1), 
//	the biggest rate of grow its the while inside this method, that repeats for every element on he list
	public static void split(E3ChainSplit<Character> a, E3ChainSplit<Character> b, E3ChainSplit<Character> c){
		E3ChainSplit<Character>.Itr it = a.iterator();
		b.clear();
		c.clear();
		
		while(it.hasNext()){
			b.append(it.next());
			if(it.hasNext()){
				c.append(it.next());
			}
		}
	}
	
	public static void main(String[] args) {
		E3ChainSplit<Character> a = new E3ChainSplit<Character>();
		E3ChainSplit<Character> b = new E3ChainSplit<Character>();
		E3ChainSplit<Character> c = new E3ChainSplit<Character>();
		a.append('a');
		a.append('b');
		a.append('c');
		a.append('d');
		a.append('e');
		
		b.append('x');
		c.append('x');
		
		System.out.println(a.output());
		System.out.println(b.output());
		System.out.println(c.output());
		
		split(a,b,c);
		
		System.out.println(a.output());
		System.out.println(b.output());
		System.out.println(c.output());
	}
}
