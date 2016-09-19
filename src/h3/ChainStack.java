package h3;

import java.util.EmptyStackException;

public class ChainStack<Item>{
	
	private ChainNode<Item> topNode;
	private int top;

	public ChainStack(int initialCapacity){
		if(initialCapacity < 1){
			throw new IllegalArgumentException("initialCapacity must be >= 1");
		}
		this.topNode = new ChainNode<Item>();
		this.top = -1;
	}
	public ChainStack(){ 
		this (10);
	}
	
	public boolean isEmpty(){
		return this.top == -1;
	}
	public Item peek(){
		if(this.isEmpty()){
			throw new EmptyStackException();
		}
		return this.topNode.element;
	}
	public void push(Item element){
		ChainNode<Item> newTop = new ChainNode<Item>(element, this.topNode);
		this.topNode = newTop;
		this.top++;
	}
	public Item pop(){
		if(this.isEmpty()){
			throw new EmptyStackException();			
		}
		ChainNode<Item> nodeRemove = this.topNode;
		this.topNode= nodeRemove.next;
		this.top--;
		return nodeRemove.element;
	}
	
	private static class ChainNode<Item>{
		Item element;
		ChainNode<Item> next;
		
		public ChainNode(){
			this.element = null;
			this.next = null;
		}
		@SuppressWarnings("unused")
		public ChainNode(Item element){
			this.element = element;
			this.next = null;
		}
		public ChainNode(Item element, ChainNode<Item> next){
			this.element = element;
			this.next = next;
		}	
	}
}
