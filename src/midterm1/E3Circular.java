package midterm1;

public class E3Circular<Item extends Number> {

	protected ChainNode<Item> firstNode, lastNode, headerNode;
	protected int size;
	
	public E3Circular(){
		this.size = 0;
		this.firstNode = new ChainNode<Item>();
		this.lastNode = new ChainNode<Item>();
		this.headerNode = new ChainNode<Item>(null, this.firstNode);
	}
	
	public boolean isEmpty() {
		return this.size==0;
	}

	public int size() {
		return this.size;
	}

	public Integer get(int index) {
		if(index<0 || index>=this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		ChainNode<Item> temp = this.firstNode;
		for(int n = 0; n<index; n++){
			temp = temp.next;
		}
		return temp.element;
	}

	public int indexOf(Item item) {
		return 0;
	}

	public Integer remove(int index) {
		if(index<0 || index>=this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		ChainNode<Item> nodeRemove;
		if(index == 0){
			nodeRemove = this.firstNode;
			this.firstNode = nodeRemove.next;
		}
		else{
			ChainNode<Item> prev, nxt;
			prev = this.firstNode;
			for(int i = 0; i<index-1;i++){
				prev=prev.next;
			}
			nodeRemove = prev.next;
			nxt = nodeRemove.next;
			prev.next=nxt;
			if(index==this.size-1){
				this.lastNode=prev;
			}
		}
		this.size--;
		return nodeRemove.element;
	}

	public void add(int index, Integer item) {
		if(index<0 || index>this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if(index == 0){
			ChainNode<Item> newNode = new ChainNode<Item>(item, this.firstNode);
			this.firstNode = newNode;
			if(this.size==0){
				this.lastNode = newNode;
				this.headerNode.element = item.intValue()+1;
			}
		}
		else{
			ChainNode<Item> prev, nxt;
			prev = this.firstNode;
			for(int i = 0; i<index-1;i++){
				prev=prev.next;
			}
			nxt=prev.next;
			ChainNode<Item> newNode = new ChainNode<Item>(item, nxt);
			prev.next= newNode;
			if(index == this.size){
				this.lastNode=newNode;
			}
			if(this.headerNode.element<item){
				this.headerNode.element = item.intValue()+1;
			}
		}
		this.size++;
	}

	public String output() {
		String op = "";
		ChainNode<Item> temp = this.firstNode;
		for(int i = 0; i<this.size; i++){
			op = op + "[" + temp.element.toString()+ "]";
			temp = temp.next;
		}
		return op;
	}
	
	public Integer getHead(){
		return this.headerNode.element;
	}
	
	public static E3Circular<Integer> merge(E3Circular<Integer> a, E3Circular<Integer> b){
		E3Circular<Integer> newChain = new E3Circular<Integer>();
		int i = 0;
		while(!a.isEmpty() || !b.isEmpty()){
			if(a.firstNode.element<=b.firstNode.element){
				newChain.add(i, a.remove(0));
			}
			else if(a.firstNode.element>b.firstNode.element){
				newChain.add(i, b.remove(0));
			}
			i++;
			if(a.isEmpty()){
				while(!b.isEmpty()){
					newChain.add(i, b.remove(0));
					i++;
				}
			}
			else if(b.isEmpty()){
				while(!a.isEmpty()){
					newChain.add(i, a.remove(0));
					i++;
				}
			}
		}
		return newChain;
	}

	private static class ChainNode<Item extends Number>{
		Integer element;
		ChainNode<Item> next;
		
		public ChainNode(){
			this.element = null;
			this.next = null;
		}
		@SuppressWarnings("unused")
		public ChainNode(Integer element){
			this.element = element;
			this.next = null;
		}
		public ChainNode(Integer element, ChainNode<Item> next){
			this.element = element;
			this.next = next;
		}	
	}
	
	public static void main(String[] args){
		E3Circular<Integer> circle1 = new E3Circular<Integer>();
		E3Circular<Integer> circle2 = new E3Circular<Integer>();
		circle1.add(0, 9);
		circle1.add(0, 5);
		circle1.add(0, 3);
		circle1.add(0, 1);
		System.out.println(circle1.output());
		System.out.println(circle1.getHead());
		circle2.add(0, 4);
		circle2.add(0, 2);
		System.out.println(circle2.output());
		System.out.println(circle2.getHead());
		
		System.out.println(merge(circle1, circle2).output());
	}
}
