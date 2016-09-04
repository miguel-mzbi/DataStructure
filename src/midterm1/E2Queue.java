package midterm1;

public class E2Queue<I> {
	protected int size;
	protected Node<I> rearNode, frontNode;
	
	public E2Queue(){
		this.size=0;
		this.frontNode= new Node<I>();
		this.rearNode= new Node<I>();
	}

	public boolean isEmpty() {
		return false;
	}
	public int size() {
		return this.size;
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
	
	public static E2Queue<Integer> interleave(E2Queue<Integer> q){
		E2Queue<Integer> tempM = new E2Queue<Integer>();
		E2Queue<Integer> tempm = new E2Queue<Integer>();
		int s = q.size();
		for(int i = q.size(); i>s/2;i--){
			tempm.enqueue(q.dequeue());
		}
		for(int i = 0; i<s/2;i++){
			tempM.enqueue(q.dequeue());
		}
		while(tempm.size()!=0){
			q.enqueue(tempm.dequeue());
			if(tempM.size()!=0){
				q.enqueue(tempM.dequeue());
			}
		}
		return q;
		
	}
	
	public static void main(String[] args) {
		E2Queue<Integer> linkList = new E2Queue<Integer>();
		try{
			linkList.enqueue(1);
			linkList.enqueue(2);
			linkList.enqueue(3);
			linkList.enqueue(4);
			linkList.enqueue(5);
			linkList.enqueue(6);
			linkList.enqueue(7);
			linkList.enqueue(8);
			linkList.enqueue(9);
			linkList.enqueue(10);

			System.out.println(linkList.output());
			E2Queue<Integer> linkList2 = interleave(linkList);
			System.out.println(linkList2.output()+"F");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
