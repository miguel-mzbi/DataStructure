package midterm1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class E2ChainSplit<Item> {
	
	protected ChainNode<Item> firstNode;
	protected int size;
	
	public E2ChainSplit(){
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
	
	public void makeEmpty(){
		this.firstNode = null;
		this.size = 0;
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
	
//	O(n)
	public static void ThreeWaySplit(E2ChainSplit<Integer> a, E2ChainSplit<Integer> b, E2ChainSplit<Integer> c, E2ChainSplit<Integer> d){
		E2ChainSplit<Integer>.Itr it = a.new Itr();
		int i = 0;
		b.makeEmpty();
		c.makeEmpty();
		d.makeEmpty();
		
		while(it.hasNext()){
			if(i%3 == 0){
				b.append(it.next());
			}
			else if(i%3 == 1){
				c.append(it.next());
			}
			else if(i%3 == 2){
				d.append(it.next());
			}
			i++;
		}
	}
	
	public static void main(String[] args) {
		E2ChainSplit<Integer> a = new E2ChainSplit<Integer>();
		E2ChainSplit<Integer> b = new E2ChainSplit<Integer>();
		E2ChainSplit<Integer> c = new E2ChainSplit<Integer>();
		E2ChainSplit<Integer> d = new E2ChainSplit<Integer>();
		a.append(1);
		a.append(2);
		a.append(3);
		a.append(4);
		a.append(5);
		a.append(6);
		
		b.append(2);
		c.append(3);
		d.append(4);
		
		System.out.println(a.output());
		System.out.println(b.output());
		System.out.println(c.output());
		System.out.println(d.output());
		
		ThreeWaySplit(a,b,c,d);
		
		System.out.println(a.output());
		System.out.println(b.output());
		System.out.println(c.output());
		System.out.println(d.output());
	}
}
