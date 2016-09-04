package midterm1;

import java.util.EmptyStackException;

public class E4StackMultiPop<Item>{
	
	protected Item stack[];
	protected int top;
	
	@SuppressWarnings("unchecked")
	public E4StackMultiPop(int initialCapacity){
		if(initialCapacity < 1){
			throw new IllegalArgumentException("initialCapacity must be >= 1");
		}
		this.stack = (Item[]) new Object[initialCapacity];
		this.top = -1;
	}
	public E4StackMultiPop(){ 
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
	public void multiPop(int k) throws Exception{
		if(k+1>this.top){
			throw new Exception("K must be of the same size at maximum");			
		}
		for(int i = 0; i < k; i++){
			this.pop();
		}
	}
	private void resize(){
		@SuppressWarnings("unchecked")
		Item[] newStack = (Item[]) new Object[this.stack.length*2];
		System.arraycopy(this.stack, 0, newStack, 0, this.top);
		this.stack=newStack;
	}
}
