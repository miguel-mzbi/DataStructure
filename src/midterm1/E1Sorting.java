package midterm1;

public class E1Sorting<Item> implements LinearList<Item> {
	
	protected ChainNode<Item> firstNode;
	protected int size;
	
	//O(n)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean isSorted(){
		ChainNode<Item> temp = this.firstNode;
		for(int i = 0; i<this.size-1; i++){
			if (((Comparable) temp.element).compareTo(temp.next.element) > 0){
				return false;
			}
			temp = temp.next;
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, Item item) {
		if(index<0 || index>this.size){
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if(index == 0){
			ChainNode<Item> newNode = new ChainNode<Item>(item, this.firstNode);
			this.firstNode = newNode;
		}
		else{
			ChainNode<Item> prev, nxt;
			prev = this.firstNode;
			for(int i = 0; i<index-1;i++){
				prev=prev.next;
			}
			nxt=prev.next;
			ChainNode<Item> newNode = new ChainNode<Item>(item, nxt);
			prev.next= newNode;
		}
		this.size++;		
	}

	@Override
	public String output() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static class ChainNode<Item> {
		protected Item element;
		protected ChainNode<Item> next;
		
		@SuppressWarnings("unused")
		public ChainNode(){
			this.element = null;
			this.next = null;
		}
		@SuppressWarnings("unused")
		public ChainNode(Item element){
			this.element = element;
			this.next = null;
		}
		public ChainNode(Item element, ChainNode<Item> next){
			this.element = element;
			this.next = next;
		}
	}
	
	public static void main(String[] args){
		E1Sorting<Integer> chain = new E1Sorting<Integer>();
		chain.add(0,6);
		chain.add(0,5);
		chain.add(0,4);
		chain.add(0,3);
		System.out.println(chain.isSorted());
		
		E1Sorting<Integer> chain2 = new E1Sorting<Integer>();
		chain2.add(0,4);
		chain2.add(0,5);
		chain2.add(0,4);
		chain2.add(0,3);
		System.out.println(chain2.isSorted());
	}
}
