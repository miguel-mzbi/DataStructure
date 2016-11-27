package projectDB;

/**
 * <b>class ExpenseTable</b>
 * <p>The purpose of this class is to store all the expense entries of a unique invoice (Table 3).
 * It uses a HashMap structure to store all the entries. Each entry is an AVL Tree (AVLTreeMod class) that stores all the expenses of a unique item name.
 * <br>Each entry has a key (The name of the item) and a value (The complete expense entry).
 * @author MiguelMontoya - ArturoFornes
 */
public class ExpenseTable {

	int m;
	private int size;
	AVLTreeMod<Integer>[] table;
	
	/**
	 * <b>ExpenseTable</b>
	 * <br><i>public ExpenseTable()</i>
	 * <p>Constructor for the class ExpenseTable.
	 * <br>It initializes the table's size to the default value.
	 */
	public ExpenseTable(){
		this(101);
	}
	
	/**
	 * <b>ExpenseTable</b>
	 * <br><i>public AddressTable()</i>
	 * <p>Constructor for the class ExpenseTable.
	 * <br>It initializes the table's size to the given capacity.
	 * @param capacity - (Int) Integer with the desired capacity for the table.
	 */
	@SuppressWarnings("unchecked")
	public ExpenseTable(int capacity){
		this.m = capacity;
		this.size = 0;
		this.table = new AVLTreeMod[capacity];
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
	@SuppressWarnings("unchecked")
	private void resize(){
		AVLTreeMod<Integer>[] newTable = new AVLTreeMod[this.m*2+1];
		for(int i = 0; i < this.m; i++){
			if(this.table[i] != null){
				newTable[hash(this.table[i].root.expense.item, this.m*2+1)] = this.table[i];
			}
		}
		this.m = this.m*2+1;
		this.table = newTable;
	}
	
	/**
	 * <b>add</b>
	 * <br><i>public Expense add(String k, Expense item)</i>
	 * <p>Adds a node<k, item> to the table.
	 * If max is reached (Not capacity), rehashing is called before adding the new node<k, item>
	 * @param k - (String) Key to add into node. It's the name of the item.
	 * @param item - (Expense) Item to add into node. It's the full expense entry.
	 * @return Expense, always returns null, because if the new entry is repeated, a counter is incremented. The entry is not replaced.
	 */
	public Expense add(String k, Expense item) {
		if(this.size >= this.m-1 || (this.size+0.0)/(this.m) >= 0.75){
			this.resize();
		}
		int h = this.hash(k);
		for(int i = h, count = 0; count < this.m; i = ++i%this.m, count++){
			if(this.table[i] == null){
				this.table[i] = new AVLTreeMod<Integer>();
				this.table[i].insert(item.expense, item);
				this.size++;
				break;
			}
			else if(this.table[i].root.expense.item.equals(k)){
				this.table[i].insert(item.expense, item);
				break;
			}
		}
		return null;
	}

	/**
	 * <b>remove</b>
	 * <br><i>public Expense remove(String k, Integer no)</i>
	 * <p>Removes node with selected key (Item name) and expense value.
	 * @param k - (String) Key to remove of the table. It's the item's name.
	 * @param no - (Integer) Integer, expense value
	 * @return Full removed expense entry. Null if not found.
	 */
	public Expense remove(String k, Integer no) {
		int h = this.hash(k);
		int freeSpace = -1;
		Expense saved = null;
		for(int i = h; this.table[i] != null; i = ++i%this.m){
			if(this.table[i].root != null && this.table[i].root.expense.item.equals(k)){
				AVLTreeMod<Integer> temp = this.table[i];
				saved = temp.remove(no);
				this.table[i] = temp;
				freeSpace = i;
				this.size--;
			}
			else if(freeSpace != -1 && this.hash(this.table[i].root.expense.item)==(h)){
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
			else if(this.table[i].root!=null && this.hash(this.table[i].root.expense.item) < freeSpace){
				this.table[freeSpace] = this.table[i];
				this.table[i] = null;
				freeSpace = i;
				}
		}
		return saved;
	}

	/**
	 * <b>getValue</b>
	 * <br><i>public Expense getValue(String k)</i>
	 * <p>Obtains node with selected key (Item name). This methods provides the root. Not a specific expense.
	 * @param k - (String) Key of the entry to get value
	 * @return Obtained expense entry. Null if not found.
	 */
	public Expense getValue(String k) {
		int h = this.hash(k);
		for(AVLTreeMod<Integer> n = table[h]; n != null; n = table[++h%this.m]){
			if(n.root.expense.item.equals(k)){
				return n.root.expense;
			}
		}
		return null;
	}

	/**
	 * <b>contains</b>
	 * <br><i>public boolean contains(String k)</i>
	 * <p>Checks for the existence of a key in the table.
	 * @param k - (String) Key, item's name, to check if exists in the table.
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
	 * <b>getExpense</b>
	 * <br><i>public Expense getExpense(String k, Integer i)</i>
	 * <p>Obtains node with selected key (Item name) and selected expense value.
	 * @param k - (String) Desired item name
	 * @param i - (Integer) Desired expense value
	 * @return Full expense entry with desired values. Null if not found.
	 */
	public Expense getExpense(String k, Integer i) {
		int h = this.hash(k);
		for(AVLTreeMod<Integer> n = this.table[h]; n != null; n = this.table[++h%this.m]){
			if(n.root.expense.item.equals(k)){
				return n.getExpense(i);
			}
		}
		return null;
	}
	
	/**
	 * <b>insertExpense</b>
	 * <br><i>public void insertExpense(Expense expense)</i>
	 * <p>Inserts new Expense to the Hash Table
	 * @param expense - (Expense) New Expense entry to add.
	 */
	public void insertExpense(Expense expense){
		this.add(expense.item, expense);
	}
	
	//Just for debugging purpose.
	public void output(){
		for(int i = 0; i < this.m; i++){
			if(this.table[i]!=null){
				System.out.print(this.table[i].root.expense.toString()+"   ");
			}
			else{
				System.out.print(this.table[i]+"   ");
			}
		}
		System.out.println();
	}
}