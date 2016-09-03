package linearList;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DLinkedList<Item> implements LinearList<Item> {
	protected DNode<Item> firstNode, lastNode;
	protected  int size;
	
	public DLinkedList(){
		this.firstNode = null;
		this.lastNode = null;
		this.size = 0;
	}
	
	/**
	 * isEmpy - Returns true if chain is empty
	 * @return boolean true if empty false if not empty
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	/**
	 * size - Returns size
	 * @return integer corresponding to size of DNose
	 */
	public int size() {
		return this.size;
	}

	/**
	 * get - Calls method to get the selected node. First checks for valid index
	 * @return Value of selected node
	 */
	public Item get(int index) {
		if(index<0 || index>=this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		return this.getNode(index).element;
	}
	
	/**
	 * getNode - Returns node at index
	 * @return DNode at index
	 */
	private DNode<Item> getNode(int index){
		DNode<Item> temp = this.firstNode;
		for(int i = 0; i<index; i++){
			temp = temp.next;
		}
		return temp;
	}
	
	/**
	 * addFirst - Adds a node with the selected Item at the start
	 */
	public void addFirst(Item a){
		DNode<Item> temp = this.firstNode;
		DNode<Item> newFirstNode = new DNode<Item>(null, a, this.firstNode);
		this.firstNode = newFirstNode;
		if(size == 0){
			this.lastNode = newFirstNode;
		}
		else{
			temp.prev = newFirstNode;
		}
		this.size++;
	}
	
	/**
	 * addLast - Adds a node with the selected Item at the end
	 */
	public void addLast(Item a){
		DNode<Item> temp = this.lastNode;
		DNode<Item> newLastNode = new DNode<Item>(this.lastNode, a, null);
		this.lastNode = newLastNode;
		if(size == 0){
			this.firstNode = newLastNode;
		}
		else{
			temp.next = newLastNode;
		}
		this.size++;
	}

	/**
	 * indexOf - Returns the index of selected node
	 * @return integer corresponding to the node's index
	 */
	public int indexOf(Item item) {
		DNode<Item> temp = this.lastNode;
		for(int i = 0; i<this.size; i++){
			if(temp.element.equals(item)){
				return i;
			}
			temp = temp.next;
		}
		return -1;
	}

	/**
	 * remove - Returns the element of the selected node to remove
	 * @return element of removed node
	 */
	public Item remove(int index) {
		if(index<0 || index>this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		DNode<Item> nodeRemove = this.firstNode;
		if(index == 0){
			this.firstNode = nodeRemove.next;
			this.firstNode.prev = null;
		}
		else{
			for(int i = 0; i<index;i++){
				nodeRemove = nodeRemove.next;
			}
			DNode<Item> prv = nodeRemove.prev;
			DNode<Item> nxt = nodeRemove.next;
			prv.next = nxt;
			nxt.prev = prv;
		}
		this.size--;
		return nodeRemove.element;
	}

	public void add(int index, Item item) {
		if(index<0 || index>this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if(index == 0){
			addFirst(item);
		}
		else if(index == this.size){
			addLast(item);
		}
		else{
			DNode<Item> prv, nxt;
			prv = this.firstNode;
			for(int i = 0; i<index-1;i++){
				prv=prv.next;
			}
			nxt=prv.next;
			DNode<Item> newNode = new DNode<Item>(prv, item, nxt);
			prv.next = newNode;
			nxt.prev = newNode;
			this.size++;
		}
	}

	public String output() {
		StringBuilder out = new StringBuilder("[");
		DNode<Item> temp = this.firstNode;
		for(int i = 0; i<this.size; i++){
			out.append("[");
			out.append(temp.element);
			out.append("]");
			temp = temp.next;
		}
		out.append("]");
		
		return out.toString();
	}
	
	private static class DNode<Item>{
		DNode<Item> prev, next;
		Item element;
		
		public DNode(DNode<Item> prev, Item element, DNode<Item> next){
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
	}

	protected class ListItr implements ListIterator<Item>{
		private DNode<Item> lastVisitedNode;
		private DNode<Item> nxt;
		private int nextIndex;
		
		public ListItr(){
			this.lastVisitedNode = null;
			this.nxt = DLinkedList.this.firstNode;
			this.nextIndex = 0;
		}
		
		public ListItr(int index){
			if(index==size){
				this.nxt = null;
			}
			else{
				this.nxt=getNode(index);
			}
			this.nextIndex = index;
		}

		public boolean hasNext() {
			return this.nextIndex<size;
		}

		public boolean hasPrevious() {
			return nextIndex>0;
		}

		public Item next() {
			if(!hasNext()){
				throw new NoSuchElementException("No elements");
			}
			this.lastVisitedNode = this.nxt;
			this.nxt = this.nxt.next;
			this.nextIndex++;
			return this.lastVisitedNode.element;
		}

		public int nextIndex() {
			if(!hasNext()){
				return size;
			}
			return this.nextIndex++;
		}

		public Item previous() {
			if(!hasPrevious()){
				throw new NoSuchElementException("No elements");
			}
			if(this.nxt==null){
				this.nxt = this.lastVisitedNode;
			}
			else{
				this.nxt = this.nxt.prev;
			}
			this.lastVisitedNode=this.nxt;
			this.nextIndex--;
			return this.lastVisitedNode.element;
		}

		public int previousIndex() {
			if(!hasPrevious()){
				return -1;
			}
			return this.previousIndex();
		}

		public void remove() {
			// TODO Auto-generated method stub
			
		}
		public void set(Item arg0) {
			// TODO Auto-generated method stub
			
		}
		public void add(Item arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
