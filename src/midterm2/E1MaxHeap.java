package midterm2;

public class E1MaxHeap {
	@SuppressWarnings("rawtypes")
	Comparable[] heap;
	int size;
	
	public E1MaxHeap(int initialCapacity){
		if(initialCapacity < 1){
			throw new IllegalArgumentException("initialCapacity >= 1");
		}
		heap = new Comparable[initialCapacity +1];
		size = 0;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void put(Comparable theElement){
		if(size == 0){
			heap[1] = theElement;
		}
		else{
			heap[size+1] = theElement;
			boolean heaped = false;
			int child = size+1;
			while(!heaped && child != 0){
				int parent = child/2;
				int cmp = heap[child].compareTo(heap[parent]);
				if(cmp >= 0){
					heap[child] = heap[parent];
					child = parent;
				}
				else{
					heaped = true;
				}
			}
		}
		size++;
	}
	
	public static void main(String[] args){
		E1MaxHeap max = new E1MaxHeap(30);
		max.put(37);
		max.put(20);
		max.put(59);
		max.put(4);
		max.put(21);
		max.put(53);
		max.put(85);
		max.put(1);
		max.put(57);
		max.put(61);
		max.put(94);
	}
	
}
