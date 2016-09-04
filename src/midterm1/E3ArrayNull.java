package midterm1;

public class E3ArrayNull<Item> implements LinearList<Item>{
	
	protected Item element[];
	protected int size;
	protected final static int defaultSize = 100;

	@SuppressWarnings("unchecked")
	public E3ArrayNull(){
		this.size = 0;
		this.element = (Item[]) new Object[defaultSize];
	}
	@SuppressWarnings("unchecked")
	public E3ArrayNull(int newSize){
		this.size = 0;
		this.element = (Item[]) new Object[newSize];
	}
	
	public boolean isEmpty() {
		return false;
	}
	public int size() {
		return this.size;
	}
	public Item get(int index) {
		return null;
	}
	public int indexOf(Item item) {
		return 0;
	}
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
	public void add(int index, Item item) {
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
	public String output() {
		String op = "";
		for(int i = 0; i<this.size; i++){
			if(this.element[i]==null){
				op = op + "[null]";
			}
			else{
				op = op + "[" + this.element[i].toString() + "]";
			}
		}
		return op;
	}
	public void removeNull(){
		for(int i= 0; i<this.size;i++){
			if(this.element[i]==null){
				this.remove(i);
				i--;
			}
		}
	}
	
	public static void main(String[] args){
		E3ArrayNull<Integer> arr = new E3ArrayNull<Integer>();
		arr.add(0, 8);
		arr.add(0, 7);
		arr.add(0, 6);
		arr.add(0, null);
		arr.add(0, null);
		arr.add(0, 3);
		arr.add(0, null);
		arr.add(0, 1);
		System.out.println(arr.size());
		System.out.println(arr.output());
		arr.removeNull();
		System.out.println(arr.size());
		System.out.println(arr.output());

	}
	
}
