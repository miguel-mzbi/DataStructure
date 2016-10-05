package linearList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


public class HashTable<Key, Value> implements DictionaryInterface<Key, Value>{

	private int capacity, size;
	double max;
	private Node<Key, Value>[] table;
	private final static int DEFAULT_CAPACITY = 97;
	private final static double CARGA = 0.75;
	
	public HashTable(){
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public HashTable(int initCapacity){
		this.capacity = initCapacity;
		this.size = 0;
		this.table = new Node[this.capacity];
		this.max = this.capacity * CARGA;
	}
	
	/**
	 * hash - Gives a hash value for the selected key
	 * @param key Key to hash
	 * @return hash value for key
	 */
	private int hash(Key key){
		return (key.hashCode() & 0x7FFFFFFF) % this.capacity;
	}
	
	/**
	 * rehash - Inserts every node<Key, Value> into a queue, clears the
	 * hastable, increases the capacity and adds every node on the queue.
	 */
	@SuppressWarnings("unchecked")
	public void rehash(){
		Queue<Node<Key, Value>> queue = (Queue<Node<Key, Value>>) this.nodes();
		this.clear();
		this.capacity = this.capacity*2 +1;
		this.max = this.capacity * CARGA;
		this.table = new Node[this.capacity];
		while(queue.peek() != null){
			this.add(queue.peek().key, queue.peek().value);
			queue.remove();
		}
	}
	
	/**
	 * add - Adds a node<Key, Value> to the table.
	 * If max is reached (Not capacity), rehashing is called before adding the new node<Key, Value>
	 * @param k Key to add into node
	 * @param v Value to add into node
	 * @return Value of node if replaced
	 */
	public Value add(Key k, Value v) {
		if(this.size >= this.max){
			this.rehash();
		}
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

	/**
	 * remove - Removes node with selected key
	 * @param k Key to remove
	 * @return Value of the removed key. Null if key wasn't found.
	 */
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

	/**
	 * getValue - Gets the value of the node with the selected key
	 * @param k Key to get value from
	 * @return Value of selected key. Null if key wasn't found.
	 */
	public Value getValue(Key k) {
		int pos = this.hash(k);
		for(Node<Key, Value> n = this.table[pos]; n != null; n = n.next){
			if(n.key.equals(k)){
				return n.value;
			}
		}
		return null;
	}
	
	/**
	 * contains
	 * @return Returns true if key is found in table
	 */
	public boolean contains(Key k) {
		return this.getValue(k) != null;
	}
	
	/**
	 * getKeyIterator - Provides iterator for all keys in the hashtable
	 * @return Returns new keyIterator
	 */
	public Iterator<Key> getKeyIterator() {
		return new keyIterator<Key>();
	}

	/**
	 * getValueIterator - Provides iterator for all values in the hashtable
	 * @return Returns new valueIterator
	 */
	public Iterator<Value> getValueIterator() {
		return new valueIterator<Value>();
	}

	/**
	 * isEmpty
	 * @return Returns boolean. True is hashtable is empty
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * getSize
	 * @return Returns size of the hashtable
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * clear - Removes every node on the hashtable
	 */
	public void clear() {
		for(int i = 0; i < this.table.length; i++){
			this.table[i] = null;
		}
		this.size = 0;
	}
	
	/**
	 * nodes - Provides every node on the hashtable as a queue
	 * @return queue with every node
	 */
	private Iterable<Node<Key, Value>> nodes(){
		Queue<Node<Key, Value>> queue = new LinkedList<Node<Key, Value>>();
		for(int i = 0; i < this.capacity; i++){
			if(this.table[i] != null){
				for(Node<Key, Value> x = this.table[i]; x != null; x = x.next){
					queue.add(x);
				}
			}
		}
		return queue;
	}
	
	/**
	 * Node - Class for the nodes of the hashtable
	 * @author miguel
	 *
	 * @param <Key> key for the entry of the hashtable
	 * @param <Value> value for the entry of the hashtable
	 */
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
	
	/**
	 * hashIterator - Generic iterator for the hashtable
	 * @author miguel
	 *
	 * @param <Item> Value or key
	 */
	private abstract class hashIterator<Item> implements Iterator<Item>{
		Queue<Node<Key, Value>> queue;
		
		public hashIterator(){
			if (HashTable.this.size > 0){
				this.queue = (Queue<Node<Key, Value>>) HashTable.this.nodes();
				this.queue.peek();
			}
		}
		
		public boolean hasNext(){
			return this.queue.peek() != null;
		}
		
		public Node<Key, Value> nextnode(){
			this.queue.peek();
			Node<Key, Value> toReturn = this.queue.remove();
			return toReturn;
		}
	}
	
	/**
	 * keyIterator - iterator for keys in the hashtable
	 * @author miguel
	 *
	 * @param <Key> keys in the hashtable
	 */
	@SuppressWarnings("hiding")
	private class keyIterator<Key> extends hashIterator<Key>{

		public keyIterator(){
			super();
		}
		@SuppressWarnings("unchecked")
		public Key next() {
			if(!this.hasNext()){
				throw new NoSuchElementException("No elements");
			}
			return (Key) this.nextnode().key;
		}
	}
	
	/**
	 * valueIterator - iterator for values in the hashtable
	 * @author miguel
	 *
	 * @param <Key> values in the hashtable
	 */
	@SuppressWarnings("hiding")
	private class valueIterator<Value> extends hashIterator<Value>{

		public valueIterator(){
			super();
		}
		@SuppressWarnings("unchecked")
		public Value next() {
			if(!this.hasNext()){
				throw new NoSuchElementException("No elements");
			}
			return (Value) this.nextnode().value;
		}
	}

}
