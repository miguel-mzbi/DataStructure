package h4.MyQueue;

import java.util.NoSuchElementException;

public class MyScratchQueue<Item> implements QueueInterface<Item>{
	private int size;
	private Node rearNode, frontNode;
	
	public MyScratchQueue(){
		this.size = 0;
		this.frontNode = this.rearNode = null;
	}
	
	public boolean empty() {
		return this.size == 0;
	}

	public Item front() {
		try{
			return this.frontNode.element;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Item rear() {
		try{
			return this.rearNode.element;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public Item Dequeue() {
		if(this.empty()){
			throw new NoSuchElementException("Queue is empty");
		}
		Node toRemove = this.frontNode;
		this.frontNode= toRemove.next;
		this.size--;
		return toRemove.element;
	}

	public void Enqueue(Item item) {
		if(this.size == 0){
			Node newNode = new Node(item);
			this.frontNode = newNode;
			this.rearNode =this.frontNode;
		}
		else{
			Node prev;
			prev = this.frontNode;
			for(int i = 0; i<this.size-1;i++){
				prev=prev.next;
			}
			Node newNode = new Node(item);
			this.rearNode = newNode;
			prev.next= this.rearNode;
		}
		this.size++;
	}

	public int size() {
		return this.size;
	}
	
	private class Node {
		Item element;
		Node next;
		
		public Node(Item element){
			this.element = element;
			this.next = null;
		}
	}
}
