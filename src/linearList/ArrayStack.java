package linearList;

import java.util.EmptyStackException;

public class ArrayStack<Item>{
	
	protected Item stack[];
	protected int top;
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int initialCapacity){
		if(initialCapacity < 1){
			throw new IllegalArgumentException("initialCapacity must be >= 1");
		}
		this.stack = (Item[]) new Object[initialCapacity];
		this.top = -1;
	}
	public ArrayStack(){ 
		this (10);
	}
	
	public boolean empty(){
		return this.top == -1;
	}
	public Item peek(){
		return this.stack[this.top];
	}
	public void push(Item element){
		if(this.top == this.stack.length-1){
			this.resize();
		}
		this.stack[this.top++] = element;
	}
	public Item pop(){
		if(this.empty()){
			throw new EmptyStackException();			
		}
		Item topElement = this.stack[top];
		this.stack[this.top] = null;
		this.top--;
		return topElement;
	}
	private void resize(){
		@SuppressWarnings("unchecked")
		Item[] newStack = (Item[]) new Object[this.stack.length*2];
		System.arraycopy(this.stack, 0, newStack, 0, this.top);
		this.stack=newStack;
	}
}
