package midterm1;

public class E2Compress<I> implements LinearList<I>{
	
	protected I element[];
	protected int size;
	protected final static int defaultSize = 100;
	private int selectedSize;
	
	@SuppressWarnings("unchecked")
	public E2Compress(){
		this.size = 0;
		this.selectedSize = defaultSize;
		this.element = (I[]) new Object[defaultSize];
	}
	@SuppressWarnings("unchecked")
	public E2Compress(int newSize){
		this.size = 0;
		this.selectedSize = newSize;
		this.element = (I[]) new Object[newSize];
	}

	public boolean isEmpty() {
		return false;
	}

	public int size() {
		return 0;
	}

	public I get(int index) {
		return null;
	}

	public int indexOf(I item) {
		return 0;
	}

	public I remove(int index) {
		if(index<0 || index>=this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		I toRemove = this.element[index];
		for(int i = index; i<this.size-1; i++){
			this.element[i]=this.element[i+1];
		}
		this.element[this.size-1]=null;
		this.size--;
		return toRemove;
	}

	public void add(int index, I item) {
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

	public String output() {
		String op = "";
		for(int i = 0; i<this.size; i++){
			op = op + "[" + this.element[i].toString() + "]";
		}
		return op;
	}
	
	private void resize(){
		@SuppressWarnings("unchecked")
		I[] newArray = (I[]) new Object[this.selectedSize*2];
		System.arraycopy(this.element, 0, newArray, 0, this.size);
		this.element=newArray;
		this.selectedSize=this.selectedSize*2;
	}
	
	/**
	 * compress - because the removal takes place before the index, the next index to remove will actually skip a index in between
	 */
	public void compress(){
		for(int i = 0; i <= (this.size+1)/2; i++){
			this.remove(i+1);
		}
	}
	
	public static void main(String[] args) {
		E2Compress<Integer> a = new E2Compress<Integer>(6);
		try{
			a.add(0, 0);
			a.add(1, 1);
			a.add(2, 2);
			a.add(3, 3);
			a.add(4, 4);
			a.add(5, 5);
			a.add(6, 6);
			a.add(7, 7);
			System.out.println(a.output());
			a.compress();
			System.out.println(a.output());

		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
