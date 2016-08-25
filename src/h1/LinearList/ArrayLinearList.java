package h1.LinearList;

public class ArrayLinearList<Item> implements LinearList<Item>{
	
	protected Item element[];
	protected int size;
	protected final static int defaultSize = 100;

	public ArrayLinearList(){
		this.size = 0;
		this.element = (Item[]) new Object[this.defaultSize];
	}
	public ArrayLinearList(int newSize){
		this.size = 0;
		this.element = (Item[]) new Object[newSize];
	}
	
	/**
	 * isEmpty - returns true if chain is empty
	 * @return boolean true if empty, false if not empty
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * size - returns size of chain
	 * @return integer with the size of the chain
	 */
	public int size() {
		return this.size;
	}

	/**
	 * get - gets element on selected index
	 * @return element inside selected index
	 */
	public Item get(int index) {
		if(this.size == 0)
			return null;
		else if(index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException("Index out of range");
		return this.element[index];
	}

	/**
	 * indexOf - return the index of the first occurrence of element x in the list, return -1 if x is not in the list
	 * @return index of occurrence with element
	 */
	public int indexOf(Item item) {
		if(this.size != 0){
			for(int i = 0; i<this.size; i++){
				if(this.element[i].equals(item)){
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * remove - remove and return the indexed element, and elements with higher index have their index reduced by 1
	 * @return returns content of eliminated element
	 */
	public Item remove(int index) {
		if(index<0 || index>=this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		Item toRemove = this.element[index];
		for(int i = index; i<this.size-1; i++){
			this.element[i]=this.element[i+1];
		}
		this.element[this.size-1]=null;
		this.size--;
		return toRemove;
	}

	/**
	 * add - insert x at the position index , and elements with theIndex ≥ index have their index increased by 1.
	 */
	public void add(int index, Item item) {
		if(index<0 || index>this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if(this.size==index){
			this.element[index]=item;
		}
		else{
			for(int i=this.size; i>index;i--){
				this.element[i]=this.element[i-1];
			}
			this.element[index]=item;
		}
		this.size++;
	}

	@Override
	public String output() {
		// TODO Auto-generated method stub
		return null;
	}

}
