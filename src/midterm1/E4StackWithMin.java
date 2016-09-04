package midterm1;

import java.util.EmptyStackException;

public class E4StackWithMin<Item extends Comparable<Item>>{

	protected Item stack[];
	protected int top;
	@SuppressWarnings("rawtypes")
	private static E4StackWithMin stackMin;
	
	@SuppressWarnings("unchecked")
	public E4StackWithMin(int initialCapacity){
		if(initialCapacity < 1){
			throw new IllegalArgumentException("initialCapacity must be >= 1");
		}
		this.stack = (Item[]) new Object[initialCapacity];
		this.top = -1;
		stackMin.stack = (Item[]) new Object[initialCapacity];
	}
	public E4StackWithMin(){ 
		this (10);
	}
	
	public boolean empty(){
		return this.top == -1;
	}
	public Item peek(){
		return this.stack[this.top];
	}
	@SuppressWarnings("unchecked")
	public void push(Item element){
		if(this.top == this.stack.length-1){
			this.resize();
		}
		this.top++;
		if(this.top==0){
			stackMin.push(element);
		}
		else{
			if(stackMin.peek().compareTo(element)<=0){
				stackMin.push(element);
			}
		}
		this.stack[this.top] = element;
		
	}
	@SuppressWarnings("unchecked")
	public Item pop(){
		if(this.empty()){
			throw new EmptyStackException();			
		}
		Item topElement = this.stack[top];
		this.stack[this.top] = null;
		this.top--;
		if(stackMin.peek().compareTo(topElement)<=0){
			stackMin.pop();
		}
		return topElement;
	}
	private void resize(){
		@SuppressWarnings("unchecked")
		Item[] newStack = (Item[]) new Object[this.stack.length*2];
		System.arraycopy(this.stack, 0, newStack, 0, this.top);
		this.stack=newStack;
	}
}
