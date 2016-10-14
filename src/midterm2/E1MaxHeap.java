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
		if(size == 0){												//1
			heap[1] = theElement;									//1
		}
		else{
			heap[size+1] = theElement;								//1
			boolean heaped = false;									//1
			int child = size+1;										//1
			while(!heaped && child > 1){							//l+1 (l = parents to inserted value. In the worst case, inserted value must become heap)
				int parent = child/2;								//l
				int cmp = heap[child].compareTo(heap[parent]);		//l
				if(cmp >= 0){										//l
					Comparable toParent = heap[child];				//l
					heap[child] = heap[parent];						//l
					heap[parent]= toParent;							//l
					child = parent;									//l
				}
				else{
					heaped = true;									//l
				}
			}
		}
		size++;														//1
	}
	//Time complexity O(l) -> O(n)
	
	//Not necessary to answer. Necessary for testing
	public void printHeap(){
		for(int i = 1; i<= size; i++){
			System.out.print(heap[i] +"-");
		}
		System.out.println("");
	}
	
	public static void main(String[] args){
		E1MaxHeap max = new E1MaxHeap(15);
		max.put(37);
		max.printHeap();
		max.put(20);
		max.printHeap();
		max.put(59);
		max.printHeap();
		max.put(4);
		max.printHeap();
		max.put(21);
		max.printHeap();
		max.put(53);
		max.printHeap();
		max.put(85);
		max.printHeap();
		max.put(1);
		max.printHeap();
		max.put(57);
		max.printHeap();
		max.put(61);
		max.printHeap();
		max.put(94);
		max.printHeap();
	}
	
}
