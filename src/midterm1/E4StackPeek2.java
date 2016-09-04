package midterm1;

import java.util.EmptyStackException;

public class E4StackPeek2<Item extends Comparable<Item>>{

	protected ChainNode<Item> topNode;
	protected int top;
	
	public E4StackPeek2(){
		this.topNode = new ChainNode<Item>();
		this.top = -1;
	}
	
	public boolean empty(){
		return this.top == -1;
	}
	public Item peek(){
		return this.topNode.element;
	}
	public void push(Item element){
		this.top++;
		ChainNode<Item> newTopNode = new ChainNode<Item>(element, this.topNode);
		this.topNode = newTopNode;
	}
	public Item pop(){
		if(this.empty()){
			throw new EmptyStackException();			
		}
		ChainNode<Item> toRemove = this.topNode;
		this.topNode = toRemove.next;
		this.top--;
		return toRemove.element;
	}
	
	public Item pop2() throws Exception{
		if(top<2){
			throw new Exception("More than 2 elements must exist");			
		}
		Item tempTop = this.pop();
		Item tempItemSecond = this.peek();
		this.push(tempTop);
		return tempItemSecond;
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
