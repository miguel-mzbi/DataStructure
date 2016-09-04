package linearList;

public class QueueChainList<I> {
	protected int size;
	protected Node<I> rearNode, frontNode;
	
	public QueueChainList(){
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
