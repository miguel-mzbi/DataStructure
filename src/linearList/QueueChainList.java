package linearList;

import java.util.NoSuchElementException;

public class QueueChainList<I> {
	protected int size;
	protected Node<I> rearNode, frontNode;
	
	public QueueChainList(){
		this.size=0;
		this.frontNode= new Node<I>();
		this.rearNode= new Node<I>();
	}
	
	/**
	 * isEmpty - returns true if chain is empty
	 * @return boolean true if empty, false if not empty
	 */
	public boolean isEmpty() {
		return this.size==0;
	}
	
	/**
	 * size - returns size of chain
	 * @return integer with the size of the chain
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * front - returns node at the front(left) of the queue
	 * @return returns first node's element
	 */
	public I front(){
		if(isEmpty()){
			throw new NoSuchElementException("Queue is empty");
		}
		else{
			return this.frontNode.element;
		}
	}
	
	/**
	 * rear - returns node at the rear(right) of the queue
	 * @return returns last node's element
	 */
	public I rear(){
		if(isEmpty()){
			throw new NoSuchElementException("Queue is empty");
		}
		else{
			return this.rearNode.element;
		}
	}
	
	/**
	 * enqueue - adds a node to the rear
	 */
	public void enqueue(I element){
		if(this.size == 0){
			Node<I> newNode = new Node<I>(element);
			this.frontNode = newNode;
			this.rearNode =this.frontNode;
		}
		else{
			Node<I> prev;
			prev = this.frontNode;
			for(int i = 0; i<this.size-1;i++){
				prev=prev.next;
			}
			Node<I> newNode = new Node<I>(element);
			this.rearNode = newNode;
			prev.next= this.rearNode;
		}
		this.size++;		
	}
	
	/**
	 * dequeue - removes node at the front
	 * @return returns the removed node's element
	 */
	public I dequeue(){
		if(this.isEmpty()){
			throw new NoSuchElementException("Queue is empty");
		}
		Node<I> toRemove = this.frontNode;
		this.frontNode= toRemove.next;
		this.size--;
		return toRemove.element;
	}
	
//	Not valid method useful for debugging
	protected String output() {
		String op = "";
		Node<I> temp = this.frontNode;
		for(int i = 1; i<=this.size; i++){
			op = op + "[" + temp.element.toString()+ "]";
			temp = temp.next;
		}
		return op;
	}
	
	private static class Node<I> {
		protected I element;
		protected Node<I> next;
		
		public Node(){
			this.element = null;
			this.next = null;
		}
		public Node(I element){
			this.element = element;
			this.next = null;
		}
		@SuppressWarnings("unused")
		public Node(I element, Node<I> next){
			this.element = element;
			this.next = next;
		}
	}
}
