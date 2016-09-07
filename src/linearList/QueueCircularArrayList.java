package linearList;

import java.util.NoSuchElementException;

public class QueueCircularArrayList<Item> {
	private Item element[];
	private int size, front, rear;
	private final static int defaultSize = 10;

	public QueueCircularArrayList(){
		this (defaultSize);
	}
	@SuppressWarnings("unchecked")
	public QueueCircularArrayList(int newSize){
		this.front = this.rear = this.size = 0;
		this.element = (Item[]) new Object[newSize];
	}
	
	/**
	 * isEmpty - returns true if array is empty
	 * @return boolean true if empty, false if not empty
	 */
	public boolean isEmpty() {
		return this.size==0;
	}
	
	/**
	 * front - returns element at the front(left) of the queue
	 * @return returns first element
	 */
	public Item front(){
		if(isEmpty()){
			throw new NoSuchElementException("Queue is empty");
		}
		else{
			return this.element[this.front];
		}
	}
	
	/**
	 * rear - returns element at the rear(right) of the queue
	 * @return returns last element
	 */
	public Item rear(){
		if(isEmpty()){
			throw new NoSuchElementException("Queue is empty");
		}
		else{
			return this.element[this.rear];
		}
	}
	
	/**
	 * enqueue - adds a element to the rear
	 */
	public void enqueue(Item element){
		if(this.size == this.element.length){
			this.resize();
		}
		this.element[rear] = element;
		this.rear = (this.rear+1)%this.element.length;
		this.size++;
	}
	
	/**
	 * dequeue - removes element at the front
	 * @return returns the removed element
	 */
	public Item dequeue(){
		if(this.isEmpty()){
			throw new NoSuchElementException("Queue is empty");
		}
		Item toRemove = this.element[this.front];
		this.element[this.front] = null;
		this.front = (this.front+1) % this.element.length;
		this.size--;
		return toRemove;
	}
	
	/**
	 * Doubles the maximum size
	 */
	@SuppressWarnings("unchecked")
	private void resize(){
		Item[] newArray = (Item[]) new Object[this.element.length*2];
		for(int i = 0; i < this.element.length; i++){
			newArray[i]=this.dequeue();
//			newArray[i]=this.element[this.front];
//			this.front = (this.front+1) % this.element.length;
		}
		this.front = 0;
		this.rear = this.element.length;
		this.element=newArray;		
	}
	
//	Not valid method useful for debugging and testing
	public String output() {
		String op = "";
		for(int i = 0; i<this.element.length; i++){
			if(this.element[i]!=null){
				op = op + "[" + this.element[i]+ "]";
			}
		}
		return op;
	}
	
}
