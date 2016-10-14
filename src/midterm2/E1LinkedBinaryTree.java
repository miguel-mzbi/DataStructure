package midterm2;

public class E1LinkedBinaryTree {
	
	BinaryTreeNode root ; // root node
	
	public E1LinkedBinaryTree(){
		this.root = new BinaryTreeNode(0, null) ;
	}
	
	//Answer to question 1A. Design a method to swap children. Recursive.
	public void swapChildren(){													
		this.root = this.swapChildren(this.root);									//1
	}
	
	//Answer to question 1A. Design a method to swap children. Recursive
	public BinaryTreeNode swapChildren(BinaryTreeNode temp){
		if(temp == null){															//2n (n=number of nodes. Each node calls the methods twice, even for nulls)
			return temp;															//2n
		}
		BinaryTreeNode left = this.swapChildren(temp.leftChild);					//2n
		BinaryTreeNode right = this.swapChildren(temp.rightChild);					//2n
		temp.leftChild = right;														//2n
		temp.rightChild = left;														//2n
		return temp;																//2n
	}
	//Complexity of O(n)
	
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
		E1LinkedBinaryTree E1 = new E1LinkedBinaryTree();
		E1.put(37, 5);
		E1.put(20, 3);
		E1.put(59, 8);
		E1.put(4, 2);
		E1.put(21, 4);
		E1.put(53, 6);
		E1.put(85, 10);
		E1.put(1, 1);
		E1.put(57, 7);
		E1.put(61, 9);
		E1.put(94, 11);
		
		System.out.println(E1.inOrder());
		E1.swapChildren();
		System.out.println(E1.inOrder());

	}
	
}
