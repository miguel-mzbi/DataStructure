package proyecto;

public class AddressTable {

	//Data structure for table 1 Name - Address
	//Each entry has a key (The name of the person) and a value (The address of the person)
	private int m;
	private int size;
	private Entry[] table;
	
	public AddressTable(){
		this(101);
	}
	
	public AddressTable(int capacity){
		this.m = capacity;
		this.size = 0;
		this.table = new Entry[capacity];
	}
	
	private int hash(String k){
		return (k.hashCode() & 0x7FFFFFFF) % this.m;
	}
	
	private int hash(String k, int m){
		return (k.hashCode() & 0x7FFFFFFF) % m;
	}
	
	private void resize(){
		Entry[] newTable = new Entry[this.m*2+1];
		for(int i = 0; i < this.m; i++){
			if(this.table[i] != null){
				newTable[hash(this.table[i].key, this.m*2+1)] = this.table[i];
			}
		}
		this.m = this.m*2+1;
		this.table = newTable;
	}
	
	public String add(String k, String item) {
		if(this.size >= this.m-1 || (this.size+0.0)/(this.m) >= 0.75){
			this.resize();
		}
		int h = this.hash(k);
		for(int i = h, count = 0; count < this.m; i = ++i%this.m, count++){
			if(this.table[i] == null){
				this.table[i] = new Entry(k,item);
				this.size++;
				break;
			}
			else if(this.table[i].key.equals(k)){
				String saved = this.table[i].value;
				this.table[i].value = item;
				return saved;
			}
		}
		
		return null;
	}

	public String remove(String k) {
		int h = this.hash(k);
		int freeSpace = -1;
		String saved = null;
		for(int i = h; this.table[i] != null; i = ++i%this.m){
			if(this.table[i].key.equals(k)){
				saved = this.table[i].value;
				this.table[i] = null;
				freeSpace = i;
				this.size--;
			}
			else if(freeSpace != -1 && this.hash(this.table[i].key) == h){
					this.table[freeSpace] = this.table[i];
					this.table[i] = null;
					freeSpace = i;
			}
			
		}
		for(int i = freeSpace; freeSpace != -1; i = ++i%this.m){
			if(i != freeSpace && this.table[i] == null){
				break;
			}
			else if(i == freeSpace && this.table[i] == null){
				continue;
			}
			else if(this.hash(this.table[i].key) < freeSpace){
				this.table[freeSpace] = this.table[i];
				this.table[i] = null;
				freeSpace = i;
				}
		}
		return saved;
	}

	public String getValue(String k) {
		int h = this.hash(k);
		for(Entry n = table[h]; n != null; n = table[++h%this.m]){
			if(n.key.equals(k)){
				return n.value;
			}
		}
		return null;
	}

	public boolean contains(String k) {
		return this.getValue(k) != null;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int getSize() {
		return this.size;
	}

	public void clear() {
		for(int i = 0; i < this.m; i++){
			this.table[i] = null;
		}
	}
	
	private static class Entry{
		String key;
		String value;
		
		private Entry(String key, String value){
			this.key = key;
			this.value = value;
		}
	}
	
	//Just for debugging purpose.
	public void output(){
		for(int i = 0; i < this.m; i++){
			if(this.table[i]!=null){
				System.out.print(this.table[i].value+"   ");
			}
			else{
				System.out.print(this.table[i]+"   ");
			}
		}
		System.out.println();
	}
	
}
