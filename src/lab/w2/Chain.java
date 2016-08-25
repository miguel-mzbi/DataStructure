package lab.w2;

public class Chain<Item> implements LinearList<Item>{
	protected ChainNode<Item> firstNode;
	protected int size;
	
	public Chain(){
		this.size = 0;
		this.firstNode = new ChainNode<Item>();
	}
	
	/**
	 * isEmpy - returns true if chain is empty
	 * @return boolean true if empty false if not empty
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}
	/**
	 * size - returns size of chain
	 * @return int with the size of the chain
	 */
	public int size() {
		return this.size;
	}

	/**
	 * get - gets element on selected index
	 * @return element inside selected index
	 */
	public Item get(int index) {
		if(index<0 || index>=this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		ChainNode<Item> temp = this.firstNode;
		for(int n = 0; n<index; n++){
			temp = temp.next;
		}
		return temp.element;
	}

	/**
	 * indexOf - return index of the node with that element
	 * @return index of node with element
	 */
	public int indexOf(Item item) {
		ChainNode<Item> temp = this.firstNode;
		for(int i= 0; i<this.size; i++){
			if(temp.element.equals(item)){
				return i;
			}
			temp = temp.next;
		}
		return -1;
	}

	@Override
	public Item remove(int index) {
		return null;
	}

	@Override
	public void add(int index, Item item) {
		if(index<0 || index>this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if(index == 0){
			ChainNode<Item> newNode = new ChainNode<Item>(item, this.firstNode);
			this.firstNode = newNode;
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
		}
		this.size++;
	}
	
	private static class ChainNode<Item>{
		Item element;
		ChainNode<Item> next;
		
		public ChainNode(){
			this.element = null;
			this.next = null;
		}
		public ChainNode(Item element){
			this.element = element;
			this.next = null;
		}
		public ChainNode(Item element, ChainNode<Item> next){
			this.element = element;
			this.next = next;
		}
		
	}
	
}
