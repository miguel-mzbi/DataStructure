package h4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class PatientDataBase {
	
	private int capacity, size;
	double max;
	private PatientRecord[] table;
	private final static int DEFAULT_CAPACITY = 97;
	private final static double CARGA = 0.75;
	
	public PatientDataBase(){
		this(DEFAULT_CAPACITY);
	}
	
	public PatientDataBase(int initCapacity){
		this.capacity = initCapacity;
		this.size = 0;
		this.table = new PatientRecord[this.capacity];
		this.max = this.capacity * CARGA;
	}
	
	//First Query
	public String getReasonVisit(String name, String date){
		int pos = this.hash(name);
		for(PatientRecord n = this.table[pos]; n != null; n = n.next){
			if(n.date.equals(date)){
				return n.reason;
			}
		}
		return null;
	}
	
	//Second Query
	public String getTreatmentVisit(String name, String date){
		int pos = this.hash(name);
		for(PatientRecord n = this.table[pos]; n != null; n = n.next){
			if(n.date.equals(date)){
				return n.treatment;
			}
		}
		return null;
	}
	
	//Third Query
	public LinkedList<String> getDatePerName(String name){
		int pos = this.hash(name);
		LinkedList<String> toReturn = null;
		if(this.table[pos] != null){
			PatientRecord temp = this.table[pos];
			toReturn = new LinkedList<String>();
			for(PatientRecord n = temp; n != null; n = n.next){
				toReturn.add(n.date);
			}
		}
		return toReturn;
	}
	
	private int hash(String nameK){
		return (nameK.hashCode() & 0x7FFFFFFF) % this.capacity;
	}
	
	public void rehash(){
		Queue<PatientRecord> queue = (Queue<PatientRecord>) this.nodes();
		this.clear();
		this.capacity = this.capacity*2 +1;
		this.max = this.capacity * CARGA;
		this.table = new PatientRecord[this.capacity];
		while(queue.peek() != null){
			this.add(queue.peek().nameK, queue.peek().date, queue.peek().reason, queue.peek().treatment);
			queue.remove();
		}
	}
	
	public String add(String nameK, String date, String reason, String treatment) {
		if(this.size >= this.max){
			this.rehash();
		}
		int pos = this.hash(nameK);
		PatientRecord temp = this.table[pos];
		if(temp == null){
			this.table[pos] = new PatientRecord(nameK, date, reason, treatment);
			this.size++;
			return null;
		}
		else{
			while(temp.next != null){
				temp = temp.next;
			}
			temp.next = new PatientRecord(nameK, date, reason, treatment);
			this.size++;
		}
		return null;		
	}
	
	public String remove(String nameK, String date) {
		int pos = this.hash(nameK);
		String toReturn;
		PatientRecord n = this.table[pos];
		if(n.nameK.equals(nameK) && n.date.equals(date)){
			toReturn = n.date;
			this.table[pos] = n.next;
			return toReturn;
		}
		for(PatientRecord i = n; i.next != null; i = i.next){
			if (n.nameK.equals(nameK) && n.date.equals(date)){
				toReturn = n.next.date;
				n.next = n.next.next;
				return toReturn;
			}
		}
		return null;
	}
	
	public Iterator<String> getKeyIterator() {
		return new keyIterator();
	}
	
	public Iterator<String> getValueIterator() {
		return new valueIterator();
	}
	
	public void clear() {
		for(int i = 0; i < this.table.length; i++){
			this.table[i] = null;
		}
		this.size = 0;
	}
	
	private Iterable<PatientRecord> nodes(){
		Queue<PatientRecord> queue = new LinkedList<PatientRecord>();
		for(int i = 0; i < this.capacity; i++){
			if(this.table[i] != null){
				for(PatientRecord x = this.table[i]; x != null; x = x.next){
					queue.add(x);
				}
			}
		}
		return queue;
	}
	
	private static class PatientRecord{
		String nameK, date, reason, treatment; //Name is the hashed key
		PatientRecord next;
		
		public PatientRecord(String n, String d, String r, String t){
			this.nameK = n;
			this.date = d;
			this.reason = r;
			this.treatment = t;
			this.next = null;
		}
		
		public String toString(){
			return "Name: "+this.nameK+
					"\nDate: "+this.date+
					"\nReason: "+this.reason+
					"\nTreatment: "+this.treatment + "\n";
		}
	}
	
	private abstract class hashIterator implements Iterator<String>{
		Queue<PatientRecord> queue;
		
		public hashIterator(){
			if (PatientDataBase.this.size > 0){
				this.queue = (Queue<PatientRecord>) PatientDataBase.this.nodes();
				this.queue.peek();
			}
		}
		
		public boolean hasNext(){
			return this.queue.peek() != null;
		}
		
		public PatientRecord nextnode(){
			if(this.hasNext()){
				PatientRecord toReturn = this.queue.remove();
				return toReturn;
			}
			return null;
		}
	}

	private class keyIterator extends hashIterator{

		public keyIterator(){
			super();
		}
		public String next() {
			if(!this.hasNext()){
				throw new NoSuchElementException("No elements");
			}
			return this.nextnode().nameK;
		}
	}
	
	private class valueIterator extends hashIterator{

		public valueIterator(){
			super();
		}
		public String next() {
			if(!this.hasNext()){
				throw new NoSuchElementException("No elements");
			}
			return this.nextnode().toString();
		}
	}
}
