package abb;

public class ABB<Key extends Comparable<Key>, Value> {
	
	ABBNode root;
	
	public ABB(){
		this.root = null;
	}
	
	/**
	 * isEmpty
	 * @return boolean determining if tree is empty
	 */
	public boolean isEmpty(){
		return this.root == null;
	}
	
	public int altura(){
		return altura(this.root, 0);
	}
		
	private int altura(ABBNode temp, int i){
		if(temp != null){
			i++;
			int alturaL = altura(temp.left, i);
			int alturaR = altura(temp.right, i);
			return Math.max(alturaL, alturaR);
		}
		return i;
	}
//	
//	public int cuantasHojas(){
//		
//	}
//	
//	public String hojas(){
//		
//	}
//	
//	public boolean contains(Key key){
//		
//	}
	
	/**
	 * get - Iterative method to get the value of desired key
	 * @param key - Key to search
	 * @return Value of selected key. Returns null if key wasn't found
	 */
	public Value get(Key key){
		ABBNode temp = this.root;
		while(temp != null){
			int cmp = key.compareTo(temp.key);
			if(cmp == 0){
				return temp.value;
			}
			else if(cmp > 0){
				temp = temp.right;
			}
			else if(cmp < 0){
				temp = temp.left;
			}
		}
		return null;
	}
	
	/**
	 * put - Iterative method to insert selected key and value to the tree.
	 * @param key - Key representing the entry.
	 * @param val - Value of the entry
	 */
	public void put(Key key, Value val){
		ABBNode temp = this.root;
		ABBNode parent = null;
		if(temp == null){
			this.root = new ABBNode(key,val);
		}
		else{
			int result = 0;
			while(temp != null){
				parent = temp;
				result = key.compareTo(temp.key);
				if(result == 0)
					break;
				else if(result < 0)	
					temp = temp.left;
				else if(result > 0) 
					temp = temp.right;
			}
			
			if(result == 0){
				temp.value = val;
			}
			else if(result > 0){
				parent.right = new ABBNode(key,val);
			}
			else if(result < 0){
				parent.left = new ABBNode(key,val);
			}
		}
	}
	
	/**
	 * add - Recursive method to insert selected key and value to the tree.
	 * @param key - Key representing the entry.
	 * @param val - Value of the entry
	 */
	public void add(Key key, Value val){
		ABBNode temp = this.root;
		if(temp == null){
			this.root = new ABBNode(key, val);
		}
		else{
			this.add(key, val, temp);
		}
	}
	
	private void add(Key key, Value val, ABBNode temp){
		int cmp = key.compareTo(temp.key); //Value of comparable
		if(cmp > 0 && temp.right != null){ //If cmp is bigger go to next right node (If not null)
			this.add(key, val, temp.right);
		}
		else if(cmp < 0 && temp.left != null){
			this.add(key, val, temp.left); //If cmp is smaller go to next left node (If not null)
		}
		if(cmp == 0){ //If already on current node
			temp.value = val;
		}
		else if(cmp > 0 && temp.right == null){ //If the new node goes on the right (Right is null)
			temp.right = new ABBNode(key, val);
		}
		else if(cmp < 0 && temp.left == null){ //If the new node goes on the left (Left is null)
			temp.left = new ABBNode(key, val);
		}
	}
	
	/**
	 * inOrder - Recursive method to go trough all the nodes InOrder
	 * @return String [key, value]
	 */
	public String inOrder(){
		if(this.root != null){
			return inOrder(this.root);
		}
		else{
			return "";
		}
	}
	
	private String inOrder(ABBNode temp){
		if(temp == null){
			return "";
		}
		String output = this.inOrder(temp.left);
		output += "[" + temp.key.toString() +","+ temp.value.toString()+"]";
		output += this.inOrder(temp.right);
		return output;
	}
	
//	public String preOrder(){
//		
//	}
//	
//	public String postorder(){
//		
//	}
//	
//	public String descendente(){
//		
//	}
//	
//	public String porNivel(){ //Iter
//		
//	}
//	
//	public String cualesPorNivel(int n){ //Iter
//		
//	}
//	
//	public String mayoresA(Key key){ //Iter
//		
//	}
	
	private class ABBNode{
		Key key;
		Value value;
		ABBNode left, right;
		
		public ABBNode(Key key, Value val){
			this.key = key;
			this.value = val;
		}
		
		public String toString(){
			return "["+this.key+"-"+this.value+"]";
		}
	}

}
