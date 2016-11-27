package projectDB;

/**
 * <b>class AddressTable</b>
 * <p>The purpose of this class is to store all the person entries of the Address Table (Table 1).
 * It uses a HashMap structure to store all the entries.
 * <br>Each entry has a key (The name of the person) and a value (The address of the person).
 * @author MiguelMontoya - ArturoFornes
 */
public class AddressTable {
	
	private int m;
	private int size;
	private Entry[] table;
	
	/**
	 * <b>AddresTable</b>
	 * <br><i>public AddressTable()</i>
	 * <p>Constructor for the class AVLTreeMod.
	 * <br>It initializes the table's size to the default value.
	 */
	public AddressTable(){
		this(101);
	}
	
	/**
	 * <b>AddresTable</b>
	 * <br><i>public AddressTable()</i>
	 * <p>Constructor for the class AVLTreeMod.
	 * <br>It initializes the table's size to the given capacity.
	 * @param capacity - (Int) Integer with the desired capacity for the table.
	 */
	public AddressTable(int capacity){
		this.m = capacity;
		this.size = 0;
		this.table = new Entry[capacity];
	}
	
	/**
	 * <b>hash</b>
	 * <br><i>private int hash(String k)</i>
	 * <p>Gives a hash value for the selected key
	 * @param k - (String) Key to hash
	 * @return Integer hash value for key
	 */
	private int hash(String k){
		return (k.hashCode() & 0x7FFFFFFF) % this.m;
	}
	
	/**
	 * <b>hash</b>
	 * <br><i>private int hash(String k, int m)</i>
	 * <p>Gives a hash value for the selected key
	 * @param k - (String) Key to hash
	 * @param m - (Integer) New size of the table
	 * @return Integer hash value for the key
	 */
	private int hash(String k, int m){
		return (k.hashCode() & 0x7FFFFFFF) % m;
	}
	
	/**
	 * <b>resize</b>
	 * <br><i>private void resize()</i>
	 * <p>Creates new table, if max capacity is reached
	 */
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
	
	/**
	 * <b>add</b>
	 * <br><i>public String add(String k, String item)</i>
	 * <p>Adds a node<k, item> to the table.
	 * If max is reached (Not capacity), rehashing is called before adding the new node<k, item>
	 * @param k - (String) Key to add into node. It's the name of person.
	 * @param item - (String) Item to add into node. It's the address of the person.
	 * @return String, item (Address) of node if replaced
	 */
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

	/**
	 * <b>remove</b>
	 * <br><i>public String remove(String k)</i>
	 * <p>Removes node with selected key (Person's name).
	 * @param k - (String) Key to remove of the table. It's the person's name.
	 * @return String, item of the removed entry. It's the person's address.
	 */
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

	/**
	 * <b>getValue</b>
	 * <br><i>public String getValue(String k)</i>
	 * <p>Removes node with selected key (Person's name).
	 * @param k - (String) Key of the entry to get value
	 * @return Value of the entry with the desired key. Returns null if no entry is found.
	 */
	public String getValue(String k) {
		int h = this.hash(k);
		for(Entry n = table[h]; n != null; n = table[++h%this.m]){
			if(n.key.equals(k)){
				return n.value;
			}
		}
		return null;
	}

	/**
	 * <b>contains</b>
	 * <br><i>public boolean contains(String k)</i>
	 * <p>Checks for the existence of a key in the table.
	 * @param k - (String) Key, person's name, to check if exists in the table.
	 * @return Boolean, true if the key exists.
	 */
	public boolean contains(String k) {
		return this.getValue(k) != null;
	}

	/**
	 * <b>isEmpty</b>
	 * <br><i>public boolean isEmpty()</i>
	 * <p>Checks if the table is empty.
	 * @return Boolean, true if table is empty.
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * <b>getSize</b>
	 * <br><i>public int getSize()</i>
	 * <p>Obtains the number of entries in the table.
	 * @return Integer, number of entries.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * <b>clear</b>
	 * <br><i>public void clear()</i>
	 * <p>Removes every entry of the table.
	 */
	public void clear() {
		for(int i = 0; i < this.m; i++){
			this.table[i] = null;
		}
	}
	
	/**
	 * <b>class Entry</b>
	 * <br>Inner Class of AddressTable
	 * <p>Each node (Entry) contains the name of the person as key for the node (Entry), and the address of the person.
	 * @author MiguelMontoya - ArturoFornes
	 */
	private static class Entry{
		String key;
		String value;
		
		/**
		 * <b>Entry</b>
		 * <br><i>private Entry(String key, String value)</i>
		 * <p>Constructor for the inner class Entry
		 * @param key - (String) Key of the node. It's the person's name.
		 * @param value - (String) Value of the node. It's the person's address.
		 */
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