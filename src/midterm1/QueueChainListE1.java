package midterm1;

public class QueueChainListE1<I> {
	protected int size;
	protected Node<I> rearNode, frontNode;
	
	public QueueChainListE1(){
		this.size=0;
		this.frontNode= new Node<I>();
		this.rearNode= new Node<I>();
	}

	public boolean isEmpty() {
		return this.size==0;
	}
	public int size() {
		return this.size;
	}
	public I front(){
		if(isEmpty()){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		else{
			return this.frontNode.element;
		}
	}
	public I rear(){
		if(isEmpty()){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		else{
			return this.rearNode.element;
		}
	}
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
	public I dequeue(){
		if(this.isEmpty()){
			throw new IndexOutOfBoundsException("EmptyList");
		}
		Node<I> toRemove = this.frontNode;
		this.frontNode= toRemove.next;
		this.size--;
		return toRemove.element;
	}
	public void reverse(){
		this.dequeueAddPopEnqueue();
	}
	
//	Method for reverse, using regular chain characteristics
	private void dequeueAddPopEnqueue(){
		QueueChainListE1<I> temp = new QueueChainListE1<I>();
		int s = this.size;
		for(int i= 0; i<s; i++){
			Node<I> newFirstNode = new Node<I>(this.dequeue(), temp.frontNode);
			temp.frontNode = newFirstNode;
			temp.size++;
		}
		for(int i= 0; i<temp.size; i++){
			this.enqueue(temp.frontNode.element);
			temp.frontNode=temp.frontNode.next;
		}
	}
	
//	Not valid method useful for debugging
	private String output() {
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
		public Node(I element, Node<I> next){
			this.element = element;
			this.next = next;
		}
	}
	
	public static void main(String[] args) {
		QueueChainListE1<Integer> linkList = new QueueChainListE1<Integer>();
		try{
			linkList.enqueue(0);
			linkList.enqueue(1);
			linkList.enqueue(2);
			linkList.enqueue(3);
			linkList.enqueue(4);
			System.out.println(linkList.output());
			System.out.println("Reversing");
			linkList.reverse();
			System.out.println("Reversed");
			System.out.println("\n");
			System.out.println(linkList.output());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}