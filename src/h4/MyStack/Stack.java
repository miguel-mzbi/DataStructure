package h4.MyStack;

public interface Stack<Item> {
	public boolean empty();
	public Item peek();
	public Item pop();
	public void push(Item element);
}
