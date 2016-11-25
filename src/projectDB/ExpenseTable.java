package projectDB;

public class ExpenseTable {
	//Data Structure for the table of expenses (Table 3)
	//A table for expenses is created for each invoice number. This means that all the entries on a expense table will belong to the same invoice number.
	//Each entry on the hash map has the item name as key.
	//Because each item can be registered multiple times with varying price, each entry in the map is in reality an AVL Tree
	//The AVL contains the item name and the expense.
	//A special AVL was constructed to consider the case in which a expense is inserted with the same expense ($) for a unique item. 
	//Instead of replacing it, it adds to a counter of repetitions.
	int m;
	private int size;
	AVLTreeMod<Integer>[] table;
	
	public ExpenseTable(){
		this(101);
	}
	
	@SuppressWarnings("unchecked")
	public ExpenseTable(int capacity){
		this.m = capacity;
		this.size = 0;
		this.table = new AVLTreeMod[capacity];
	}
	
	int hash(String k){
		return (k.hashCode() & 0x7FFFFFFF) % this.m;
	}
	
	private int hash(String k, int m){
		return (k.hashCode() & 0x7FFFFFFF) % m;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(){
		AVLTreeMod<Integer>[] newTable = new AVLTreeMod[this.m*2+1];
		for(int i = 0; i < this.m; i++){
			if(this.table[i] != null){
				newTable[hash(this.table[i].root.value.item, this.m*2+1)] = this.table[i];
			}
		}
		this.m = this.m*2+1;
		this.table = newTable;
	}
	
	public Invoice add(String k, Expense item) {
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
			else if(this.table[i].root.value.item.equals(k)){
				this.table[i].insert(item.expense, item);
				break;
			}
		}
		
		return null;
	}

	public Expense remove(String k,Integer no) {
		int h = this.hash(k);
		int freeSpace = -1;
		Expense saved = null;
		for(int i = h; this.table[i] != null; i = ++i%this.m){
			if(this.table[i].root != null && this.table[i].root.value.item.equals(k)){
				AVLTreeMod<Integer> temp = this.table[i];
				saved = temp.remove(no);
				this.table[i] = temp;
				freeSpace = i;
				this.size--;
			}
			else if(freeSpace != -1 && this.hash(this.table[i].root.value.item)==(h)){
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
			else if(this.table[i].root!=null && this.hash(this.table[i].root.value.item) < freeSpace){
				this.table[freeSpace] = this.table[i];
				this.table[i] = null;
				freeSpace = i;
				}
		}
		return saved;
	}

	public Expense getValue(String k) {
		int h = this.hash(k);
		for(AVLTreeMod<Integer> n = table[h]; n != null; n = table[++h%this.m]){
			if(n.root.value.item.equals(k)){
				return n.root.value;
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
	//Just for debugging purpose.
	public void output(){
		for(int i = 0; i < this.m; i++){
			if(this.table[i]!=null){
				System.out.print(this.table[i].root.value.toString()+"   ");
			}
			else{
				System.out.print(this.table[i]+"   ");
			}
		}
		System.out.println();
	}
	
	public Expense getExpense(String k,Integer i) {
		int h = this.hash(k);
		for(AVLTreeMod<Integer> n = this.table[h]; n != null; n = this.table[++h%this.m]){
			if(n.root.value.item.equals(k)){
				return n.getValue(i);
			}
		}
		return null;
	}
	
	public void insertExpense(Expense expense){
		this.add(expense.item, expense);
	}
}