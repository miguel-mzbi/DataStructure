package linearList;

import java.util.Iterator;

public class HashTable<Key, Value> implements DictionaryInterface<Key, Value>{

	private int capacity, size;
	private Node<Key, Value>[] table;
	
	public HashTable(){
		this(97);
	}
	
	@SuppressWarnings("unchecked")
	public HashTable(int initCapacity){
		this.capacity = initCapacity;
		this.size = 0;
		this.table = new Node[this.capacity];
	}
	
	private int hash(Key key){
		return (key.hashCode() & 0x7FFFFFFF) % this.capacity;
	}
	
	public Value add(Key k, Value v) {
		int pos = this.hash(k);
		for(Node<Key, Value> n = this.table[pos]; n != null; n = n.next){
			if(n.key.equals(k)){
				Value toReturn = n.value;
				n.value = v;
				return toReturn;
			}
		}
		this.table[pos] = new Node<Key, Value>(k, v, this.table[pos]);
		this.size++;
		return null;
	}

	public Value remove(Key k) {
		int pos = this.hash(k);
		Value toReturn;
		Node<Key, Value> n = this.table[pos];
		if(n.key.equals(k)){
			toReturn = n.value;
			this.table[pos] = n.next;
			return toReturn;
		}
		for(Node<Key, Value> i = n; i.next != null; i = i.next){
			if (n.next.key.equals(k)){
				toReturn = n.next.value;
				n.next = n.next.next;
				return toReturn;
			}
		}
		return null;
	}

	public Value getValue(Key k) {
		int pos = this.hash(k);
		for(Node<Key, Value> n = this.table[pos]; n != null; n = n.next){
			if(n.key.equals(k)){
				return n.value;
			}
		}
		return null;
	}

	public boolean contains(Key k) {
		return this.getValue(k) != null;
	}

	public Iterator<Key> getKeyIterator() {
		return null;
	}

	public Iterator<Value> getValueIterator() {
		return null;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int getSize() {
		return this.size;
	}

	public void clear() {
		for(int i = 0; i < this.table.length; i++){
			this.table[i] = null;
		}
	}
	
	private static class Node<Key, Value>{
		Key key;
		Value value;
		Node<Key, Value> next;
		
		public Node(Key k, Value v, Node<Key, Value> n){
			key = k;
			value = v;
			next = n;
		}
	}

}
