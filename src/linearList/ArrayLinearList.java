package linearList;

public class ArrayLinearList<Item> implements LinearList<Item>{
	
	protected Item element[];
	protected int size;
	protected final static int defaultSize = 100;
	private int selectedSize;

	@SuppressWarnings("unchecked")
	public ArrayLinearList(){
		this.size = 0;
		this.selectedSize = defaultSize;
		this.element = (Item[]) new Object[defaultSize];
	}
	@SuppressWarnings("unchecked")
	public ArrayLinearList(int newSize){
		this.size = 0;
		this.selectedSize = newSize;
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
		if(this.size == this.selectedSize){
			resize();
		}
		if(index<0 || index>this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		else if(this.size==0){
			this.element[0]=item;
		}
		else if(this.size==index){
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

	/**
	 * Returns all as a string
	 * @return items of all elements inside element as string
	 */
	public String output() {
		String op = "";
		for(int i = 0; i<this.size; i++){
			op = op + "[" + this.element[i].toString() + "]";
		}
		return op;
	}
	
	private void resize(){
		@SuppressWarnings("unchecked")
		Item[] newArray = (Item[]) new Object[this.selectedSize*2];
		System.arraycopy(this.element, 0, newArray, 0, this.size);
		this.element=newArray;
		this.selectedSize=this.selectedSize*2;
	}

}
