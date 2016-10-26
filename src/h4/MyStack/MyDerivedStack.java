package h4.MyStack;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class MyDerivedStack<Item> extends LinkedList<Item> implements Stack<Item>  {

	//Inherited methods should be protected 
	private static final long serialVersionUID = 4372135109257848415L;

	public MyDerivedStack(int initialCapacity){
		super();
	}
	
	public MyDerivedStack(){
		this(10);
	}
	
	public boolean empty() {
		return this.isEmpty();
	}

	public Item peek() {
		if(this.empty())
			throw new EmptyStackException();
		return this.get(0);
	}

	public Item pop() {
		if(this.empty())
			throw new EmptyStackException();
		return this.remove(0);
	}

	public void push(Item element) {
		this.add(0, element);		
	}

}
