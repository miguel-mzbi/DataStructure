package midterm1;

public class E1QueueChainList<I> {
	protected int size;
	protected Node<I> rearNode, frontNode;
	
	public E1QueueChainList(){
		this.size=0;
		this.frontNode= new Node<I>();
		this.rearNode= new Node<I>();
	}

	public boolean isEmpty() {
		return false;
	}
	public int size() {
		return 0;
	}
	public I front(){
		return null;
	}
	public I rear(){
		return null;
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
//	O(n)
	private void dequeueAddPopEnqueue(){
		E1QueueChainList<I> temp = new E1QueueChainList<I>();
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
		public Node(I element, Node<I> next){
			this.element = element;
			this.next = next;
		}
	}
	
	public static void main(String[] args) {
		E1QueueChainList<Integer> linkList = new E1QueueChainList<Integer>();
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
