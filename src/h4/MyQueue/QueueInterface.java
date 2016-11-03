package h4.MyQueue;

public interface QueueInterface<Item> {
	public boolean empty();
	public Item front();
	public Item rear();
	public Item Dequeue();
	public void Enqueue(Item item);
	public int size();
}
