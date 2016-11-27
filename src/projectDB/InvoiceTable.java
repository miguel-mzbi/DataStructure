package projectDB;

/**
 * <b>class InvoiceTable</b>
 * <p>The purpose of this class is to store all the invoice entries of all people (Table 2).
 * It uses a HashMap structure to store all the entries. Each entry is an AVL Tree (AVLTree class) that stores all the invoices of a unique person.
 * <br>Each entry has a key (The name of the person) and a value (The complete invoice entry).
 * @author MiguelMontoya - ArturoFornes
 */
public class InvoiceTable {
	
	int m;
	private int size;
	AVLTree<Integer>[] table;
	
	/**
	 * <b>InvoiceTable</b>
	 * <br><i>public InvoiceTable()</i>
	 * <p>Constructor for the class InvoiceTable.
	 * <br>It initializes the table's size to the default value.
	 */
	public InvoiceTable(){
		this(101);
	}
	
	/**
	 * <b>InvoiceTable</b>
	 * <br><i>public InvoiceTable()</i>
	 * <p>Constructor for the class InvoiceTable.
	 * <br>It initializes the table's size to the given capacity.
	 * @param capacity - (Int) Integer with the desired capacity for the table.
	 */
	@SuppressWarnings("unchecked")
	public InvoiceTable(int capacity){
		this.m = capacity;
		this.size = 0;
		this.table = new AVLTree[capacity];
	}
	
	/**
	 * <b>hash</b>
	 * <br><i>int hash(String k)</i>
	 * <p>Gives a hash value for the selected key
	 * @param k - (String) Key to hash
	 * @return Integer hash value for key
	 */
	int hash(String k){
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
	@SuppressWarnings("unchecked")
	private void resize(){
		AVLTree<Integer>[] newTable = new AVLTree[this.m*2+1];
		for(int i = 0; i < this.m; i++){
			if(this.table[i] != null){
				newTable[hash(this.table[i].root.invoiceValue.name, this.m*2+1)] = this.table[i];
			}
		}
		this.m = this.m*2+1;
		this.table = newTable;
	}
	
	/**
	 * <b>add</b>
	 * <br><i>public Invoice add(String k, Invoice item)</i>
	 * <p>Adds a node<k, item> to the table.
	 * If max is reached (Not capacity), rehashing is called before adding the new node<k, item>
	 * @param k - (String) Key to add into node. It's the name of the person.
	 * @param item - (Invoice) Item to add into node. It's the full invoice entry.
	 * @return Invoice, always returns null.
	 */
	public Invoice add(String k, Invoice item) {
		if(this.size >= this.m-1 || (this.size+0.0)/(this.m) >= 0.75){
			this.resize();
		}
		int h = this.hash(k);
		for(int i = h, count = 0; count < this.m; i = ++i%this.m, count++){
			if(this.table[i] == null){
				this.table[i] = new AVLTree<Integer>();
				this.table[i].insert(item.invoiceNo, item);
				this.size++;
				break;
			}
			else if(this.table[i].root.invoiceValue.name.equals(k)){
				this.table[i].insert(item.invoiceNo, item);
				break;
			}
		}
		
		return null;
	}

	/**
	 * <b>remove</b>
	 * <br><i>public Invoice remove(String k, Integer no)</i>
	 * <p>Removes node with selected key (Person name) and payment value.
	 * @param k - (String) Key to remove of the table. It's the person's name.
	 * @param no - (Integer) Integer, payment value
	 * @return Full removed invoice entry. Null if not found.
	 */
	public Invoice remove(String k, Integer no) {
		int h = this.hash(k);
		int freeSpace = -1;
		Invoice saved = null;
		for(int i = h; this.table[i] != null; i = ++i%this.m){
			if(this.table[i].root.invoiceValue.name.equals(k)){
				AVLTree<Integer> temp = this.table[i];
				saved = temp.remove(no);
				this.table[i] = temp;
				freeSpace = i;
				this.size--;
			}
			else if(freeSpace != -1 && this.hash(this.table[i].root.invoiceValue.name) == h){
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
			else if(this.table[i].root!=null && this.hash(this.table[i].root.invoiceValue.name) < freeSpace){
				this.table[freeSpace] = this.table[i];
				this.table[i] = null;
				freeSpace = i;
				}
		}
		return saved;
	}

	/**
	 * <b>getValue</b>
	 * <br><i>public Invoice getValue(String k)</i>
	 * <p>Obtains node with selected key (Person name). This methods provides the root. Not a specific expense.
	 * @param k - (String) Key of the entry to get value
	 * @return Obtained expense entry. Null if not found.
	 */
	public Invoice getValue(String k) {
		int h = this.hash(k);
		for(AVLTree<Integer> n = table[h]; n != null; n = table[++h%this.m]){
			if(n.root != null && n.root.invoiceValue.name.equals(k)){
				return n.root.invoiceValue;
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
	 * <b>getInvoice</b>
	 * <br><i>public Invoice getInvoice(String k, Integer i)</i>
	 * <p>Obtains node with selected key (Person's name) and selected payment value.
	 * @param k - (String) Desired person's name.
	 * @param i - (Integer) Desired payment value.
	 * @return Full invoice entry with desired values. Null if not found.
	 */
	public Invoice getInvoice(String k, Integer i) {
		int h = this.hash(k);
		for(AVLTree<Integer> n = this.table[h]; n != null; n = this.table[++h%this.m]){
			if(n != null && n.root.invoiceValue.name.equals(k)){
				return n.getInvoice(i);
			}
		}
		return null;
	}
	
	/**
	 * <b>insertInvoice</b>
	 * <br><i>public void insertInvoice(Invoice invoice)</i>
	 * <p>Inserts new Invoice to the Hash Table
	 * @param invoice - (Invoice) New Invoice entry to add.
	 */
	public void insertInvoice(Invoice invoice){
		this.add(invoice.name, invoice);
	}
	
	//Just for debugging purpose.
	public void output(){
		for(int i = 0; i < this.m; i++){
			if(this.table[i]!=null){
				System.out.print(this.table[i].root.invoiceValue.toString()+"   ");
			}
			else{
				System.out.print(this.table[i]+"   ");
			}
		}
		System.out.println();
	}
}