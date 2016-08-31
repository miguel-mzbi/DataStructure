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
	
	public boolean isEmpty() {
		return this.size == 0;
	}

	public int size() {
		return this.size;
	}

	public Item get(int index) {
		if(index<0 || index>=this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		return this.getNode(index).element;
	}
	
	private DNode<Item> getNode(int index){
		DNode<Item> temp = this.firstNode;
		for(int i = 0; i<index; i++){
			temp = temp.next;
		}
		return temp;
	}
	
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

	@Override
	public int indexOf(Object item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, Object item) {
		// TODO Auto-generated method stub
		
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

	private class ListItr implements ListIterator<Item>{
		private DNode<Item> lastVisitedNode = null;
		private DNode<Item> next;
		private int nextIndex;
		
		public ListItr(){
			this.next = firstNode;
			this.nextIndex = 0;
		}
		
		public ListItr(int index){
			if(index==size){
				this.next = null;
			}
			else{
				this.next=getNode(index);
			}
			this.nextIndex = index;
		}
		
		public void add(Item arg0) {
			// TODO Auto-generated method stub
			
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
			this.lastVisitedNode = next;
			this.next = this.next.next;
			this.nextIndex++;
			return this.lastVisitedNode.element;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		public Item previous() {
			if(!hasPrevious()){
				throw new NoSuchElementException("No elements");
			}
			if(this.next==null){
				this.next = this.lastVisitedNode;
			}
			else{
				this.next = this.next.prev;
			}
			this.lastVisitedNode=this.next;
			this.nextIndex--;
			return this.lastVisitedNode.element;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		public void remove() {
			// TODO Auto-generated method stub
			
		}

		public void set(Item arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
