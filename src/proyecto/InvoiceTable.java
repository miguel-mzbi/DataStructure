package proyecto;

public class InvoiceTable {
	//Data structure for the invoice table (Table 2)
	//Each entry on the hash map has the name of the invoice number owner as a key.
	//Because every person can have many invoice numbers, each entry is in reality an AVL tree. 
	//Each person has an AVL tree that stores all the invoice numbers and the payment where he is involved.
	int m;
	private int size;
	AVLTree<Integer>[] table;
	
	public InvoiceTable(){
		this(101);
	}
	
	@SuppressWarnings("unchecked")
	public InvoiceTable(int capacity){
		this.m = capacity;
		this.size = 0;
		this.table = new AVLTree[capacity];
	}
	
	int hash(String k){
		return (k.hashCode() & 0x7FFFFFFF) % this.m;
	}
	
	private int hash(String k, int m){
		return (k.hashCode() & 0x7FFFFFFF) % m;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(){
		AVLTree<Integer>[] newTable = new AVLTree[this.m*2+1];
		for(int i = 0; i < this.m; i++){
			if(this.table[i] != null){
				newTable[hash(this.table[i].root.value.name, this.m*2+1)] = this.table[i];
			}
		}
		this.m = this.m*2+1;
		this.table = newTable;
	}
	
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
			else if(this.table[i].root.value.name.equals(k)){
				this.table[i].insert(item.invoiceNo, item);
				break;
			}
		}
		
		return null;
	}

	public Invoice remove(String k,Integer no) {
		int h = this.hash(k);
		int freeSpace = -1;
		Invoice saved = null;
		for(int i = h; this.table[i] != null; i = ++i%this.m){
			if(this.table[i].root.value.name.equals(k)){
				AVLTree<Integer> temp = this.table[i];
				saved = temp.remove(no);
				this.table[i] = temp;
				freeSpace = i;
				this.size--;
			}
			else if(freeSpace != -1 && this.hash(this.table[i].root.value.name) == h){
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
			else if(this.table[i].root!=null && this.hash(this.table[i].root.value.name) < freeSpace){
				this.table[freeSpace] = this.table[i];
				this.table[i] = null;
				freeSpace = i;
				}
		}
		return saved;
	}

	public Invoice getValue(String k) {
		int h = this.hash(k);
		for(AVLTree<Integer> n = table[h]; n != null; n = table[++h%this.m]){
			if(n.root.element.equals(k)){
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
	
	public Invoice getInvoice(String k,Integer i) {
		int h = this.hash(k);
		for(AVLTree<Integer> n = this.table[h]; n != null; n = this.table[++h%this.m]){
			
			if(n.root.value.name.equals(k)){
				return n.getValue(i);
			}
		}
		return null;
	}
	
	public void insertInvoice(Invoice invoice){
		this.add(invoice.name, invoice);
	}
	
}
