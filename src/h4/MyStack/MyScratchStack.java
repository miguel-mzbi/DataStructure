package h4.MyStack;

public class MyScratchStack<Item> implements Stack<Item>  {

	int top;
	Item[] stack;
	
	@SuppressWarnings("unchecked")
	public MyScratchStack(int initialCapacity){
		if(initialCapacity < 1)
			throw new IllegalArgumentException();
		this.stack = (Item[]) new Object[initialCapacity];
		this.top = -1;
	}
	
	public MyScratchStack(){
		this(10);
	}
	
	public boolean empty() {
		return this.top == -1;
	}

	public Item peek() {
		try{
			return this.stack[top];
		}
		catch(IndexOutOfBoundsException e){
			throw new IndexOutOfBoundsException();
		}
	}

	public Item pop() {
		try{
			return this.stack[top--];
		}
		catch(IndexOutOfBoundsException e){
			throw new IndexOutOfBoundsException();
		}
	}

	public void push(Item element) {
		this.stack[++top] = element;	
	}

}
