package h3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTableOpenAddressing<Key, Value> implements DictionaryInterface<Key, Value> {

	private int capacity, size;
	private Node<Key, Value>[] table;
	
	public HashTableOpenAddressing(){
		this(97);
	}

	@SuppressWarnings("unchecked")
	public HashTableOpenAddressing(int initCapacity){
		this.capacity = initCapacity;
		this.size = 0;
		this.table = new Node[this.capacity];
	}

	private int hash(Key key){
		return (key.hashCode() & 0x7FFFFFFF) % this.capacity;
	}
	
	private int reHash(Key key, int size){
		return (key.hashCode() & 0x7FFFFFFF) % size;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(){
		Node<Key, Value>[] tempTable = new Node[this.capacity*2+1];
		for(int i = 0; i < this.capacity; i++){
			if(this.table[i] != null){
				tempTable[this.reHash(this.table[i].key, this.capacity*2+1)] = this.table[i];
			}
			this.table[i] = null;
		}
		this.capacity = this.capacity*2+1;
		this.table = tempTable;
	}
	
	public Value add(Key k, Value v) {
		if(this.size == this.capacity){
			this.resize();
		}
		int pos = this.hash(k);
		for(int i = pos, path = 0; path < this.capacity; i = ++i%this.capacity, path++){
			if(this.table[i] == null){
				this.table[i] = new Node<Key, Value>(k, v);
				this.size++;
				break;
			}
			else if(this.table[i].key.equals(k)){
				Value toReturn = this.table[i].value;
				this.table[i].value = v;
				return toReturn;
			}
		}
		return null;
	}

	public Value remove(Key k) {
		int pos = this.hash(k);
		Value toRemove = null;
		int removedSpace = -1;
		for(int i = pos; this.table[i] != null; i = ++i%this.capacity){
			if(this.table[i].key.equals(k)){
				toRemove = this.table[i].value;
				this.table[i] = null;
				removedSpace = i;
			}
			else if(removedSpace != -1 && this.hash(this.table[i].key) == pos){
				this.table[removedSpace] = this.table[i];
				this.table[i] = null;
				removedSpace = i;
			}
//			else if()
		}
		for(int i = (1+removedSpace)%this.capacity; i != -1; i = ++i%this.capacity){
			if(this.table[i] != null && this.hash(this.table[i].key) == i){
				continue;
			}
			else if(this.table[i] == null){
				break;
			}
			else if(this.hash(this.table[i].key) <= removedSpace){
				this.table[removedSpace] = this.table[i];
				this.table[i] = null;
				removedSpace = i;
			}
			
		}
		this.size--;
		return toRemove;
	}

	public Value getValue(Key k) {
		int pos = this.hash(k);
		for(int i = pos, path = 0; path < this.capacity; i = ++i%this.capacity, path++){
			if(this.table[i].key.equals(k)){
				return this.table[i].value;
			}
		}
		return null;
	}

	public boolean contains(Key k) {
		return this.getValue(k) != null;
	}

	public Iterator<Key> getKeyIterator() {
		return new keyIterator<Key>();
	}

	public Iterator<Value> getValueIterator() {
		return new valueIterator<Value>();
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int getSize() {
		return this.size;
	}

	public void clear() {
		for(int i = 0; i < this.capacity; i++){
			this.table[i] = null;
		}
		this.size = 0;
	}
//	Method not valid, only for testing
	public void output(){
		for(int i = 0; i < this.capacity; i++){
			if(this.table[i] != null){
				System.out.print(this.table[i].value+"	");
			}
			else{
				System.out.print(this.table[i]+"	");
			}
		}
		System.out.println();
	}
	
	@SuppressWarnings("hiding")
	private class keyIterator<Key> implements Iterator<Key>{
		int nextIndex;
		
		public keyIterator(){
			this.nextIndex = 0;
		}
		
		public boolean hasNext(){
			return this.nextIndex < HashTableOpenAddressing.this.capacity;
		}

		@SuppressWarnings("unchecked")
		public Key next(){
			if(!this.hasNext()){
				throw new NoSuchElementException();
			}
			System.out.println("I:"+this.nextIndex);
			if(HashTableOpenAddressing.this.table[this.nextIndex] == null){
				this.nextIndex++;
				return null;
			}
			else{
				return (Key) HashTableOpenAddressing.this.table[nextIndex++].key;
			}
		}
		
	}
	
	@SuppressWarnings("hiding")
	private class valueIterator<Value> implements Iterator<Value>{
		int nextIndex;
		
		public valueIterator(){
			this.nextIndex = 0;
		}
		
		public boolean hasNext(){
			return this.nextIndex < HashTableOpenAddressing.this.capacity;
		}

		@SuppressWarnings("unchecked")
		public Value next(){
			if(!this.hasNext()){
				throw new NoSuchElementException();
			}
			System.out.println("I:"+this.nextIndex);
			if(HashTableOpenAddressing.this.table[this.nextIndex] == null){
				this.nextIndex++;
				return null;
			}
			else{
				return (Value) HashTableOpenAddressing.this.table[nextIndex++].value;
			}
		}
		
	}
	
	private static class Node<Key, Value>{
		Key key;
		Value value;
		
		public Node(Key k, Value v){
			key = k;
			value = v;
		}
	}
}
