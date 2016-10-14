package abb;

public class ABB<Key extends Comparable<Key>, Value> {
	
	private ABBNode root;
	
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
		
	}
	
	public int cuantasHojas(){
		
	}
	
	public String hojas(){
		
	}
	
	public boolean contains(Key key){
		
	}
	
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
		ABBNode prevNode = null;
		int cmp = 0;
		if(temp == null){
			this.root = new ABBNode(key, val);
		}
		else{
			while(temp != null){
				cmp = key.compareTo(temp.key);
				prevNode = temp;
				if(cmp == 0){
					break;
				}
				else if(cmp > 0){
					temp = temp.right;
				}
				else if(cmp < 0){
					temp = temp.left;
				}
			}
			if(cmp == 0){
				prevNode.value = val;
			}
			else if(cmp > 0){
				prevNode.right = new ABBNode(key, val);
			}
			else if(cmp < 0){
				prevNode.left = new ABBNode(key, val);
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
		String output = "";
		if(temp.left != null){
			output += inOrder(temp.left) + " ";
		}
		output += temp;
		if(temp.right != null){
			output += " " + inOrder(temp.right);
		}
		return output;
	}
	
	public String preOrder(){
		
	}
	
	public String postorder(){
		
	}
	
	public String descendente(){
		
	}
	
	public String porNivel(){ //Iter
		
	}
	
	public String cualesPorNivel(int n){ //Iter
		
	}
	
	public String mayoresA(Key key){ //Iter
		
	}
	
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
