package midterm2;

public class E2LinkedBinaryTree {
	
	BinaryTreeNode root ; // root node
	
	public E2LinkedBinaryTree(){
		this.root = new BinaryTreeNode(0, null) ;
	}
	
	//Answer to question 1A. Design a method to get a node in a level. Recursive.
	public BinaryTreeNode elementAtLevel(int theLevel){
		return elementAtLevel(root, theLevel, 0);
	}
		
	private BinaryTreeNode elementAtLevel(BinaryTreeNode temp, int theLevel, int i){
		i++;
		if(i == theLevel && temp != null){
			return temp;
		}
		else if(temp != null){
			
			BinaryTreeNode tempt = elementAtLevel(temp.leftChild, i, theLevel);
			if(tempt != null) return tempt;
			tempt = elementAtLevel(temp.rightChild, i, theLevel);
			if(tempt != null) return tempt;
		}
		return null;
	}
	//Complexity of O(2n) (n equals number of nodes)
	
	//Not necessary to answer. Necessary for testing
	public void put(int key, Object val){
		BinaryTreeNode temp = this.root;
		if(temp.element == null){
			this.root = new BinaryTreeNode(key, val);
		}
		else{
			this.put(key, val, temp);
		}
	}
	private void put(int key, Object val, BinaryTreeNode temp){
		if(key > temp.key && temp.rightChild != null){ //If cmp is bigger go to next right node (If not null)
			put(key, val, temp.rightChild);
		}
		else if(key < temp.key && temp.leftChild != null){//If cmp is smaller go to next left node (If not null)
			put(key, val, temp.leftChild);
		}
		if(key == temp.key){ //If already on current node
			temp.element = val;
		}
		else if(key > temp.key && temp.rightChild == null){ //If the new node goes on the right (Right is null)
			temp.rightChild = new BinaryTreeNode(key, val);
		}
		else if(key < temp.key && temp.leftChild == null){ //If the new node goes on the left (Left is null)
			temp.leftChild = new BinaryTreeNode(key, val);
		}
	}
	
	//Not necessary to answer. Necessary for testing
	public String inOrder(){
		if(this.root != null){
			return inOrder(this.root);
		}
		else{
			return "";
		}
	}
	private String inOrder(BinaryTreeNode temp){
		String output = "";
		if(temp.leftChild != null){
			output += inOrder(temp.leftChild) + " ";
		}
		output += temp;
		if(temp.rightChild != null){
			output += " " + inOrder(temp.rightChild);
		}
		return output;
	}
	
	public class BinaryTreeNode{
		Object element;
		int key;
		BinaryTreeNode leftChild ; // left subtree
		BinaryTreeNode rightChild ; // right subtree
		
		//Not necessary to answer. Necessary for testing
		public BinaryTreeNode(int key, Object val){
			this.key = key;
			this.element = val;
		}
		
		//Not necessary to answer. Necessary for testing
		public String toString(){
			return "["+this.key+"-"+this.element+"]";
		}
	}
	
	public static void main(String[] args) {
		E2LinkedBinaryTree E2 = new E2LinkedBinaryTree();
		E2.put(37, 5);
		E2.put(20, 3);
		E2.put(59, 8);
		E2.put(4, 2);
		E2.put(21, 4);
		E2.put(53, 6);
		E2.put(85, 10);
		E2.put(1, 1);
		E2.put(57, 7);
		E2.put(61, 9);
		E2.put(94, 11);
		
		System.out.println(E2.elementAtLevel(7));

	}
	
}
