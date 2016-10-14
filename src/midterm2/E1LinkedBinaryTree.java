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
	public void put(int val, int key){
		BinaryTreeNode temp = this.root;
		if(temp.element == null){
			this.root.element = val;
			this.root.key = key;
		}
		else{
			while(temp != null){
				if(key > temp.key && temp.rightChild != null){ //If cmp is bigger go to next right node (If not null)
					temp = temp.rightChild;
				}
				else if(key < temp.key && temp.leftChild != null){//If cmp is smaller go to next left node (If not null)
					temp = temp.leftChild;
				}
				if(key == temp.key){ //If already on current node
					temp.element = val;
					break;
				}
				else if(key > temp.key && temp.rightChild == null){ //If the new node goes on the right (Right is null)
					temp.rightChild = new BinaryTreeNode(key, val);
					break;
				}
				else if(key < temp.key && temp.leftChild == null){ //If the new node goes on the left (Left is null)
					temp.leftChild = new BinaryTreeNode(key, val);
					break;
				}
			}
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
		E1LinkedBinaryTree ABB = new E1LinkedBinaryTree();
		ABB.put(37, 5);
		ABB.put(20, 3);
		ABB.put(59, 8);
		ABB.put(4, 2);
		ABB.put(21, 4);
		ABB.put(53, 6);
		ABB.put(85, 10);
		ABB.put(1, 1);
		ABB.put(57, 7);
		ABB.put(61, 9);
		ABB.put(94, 11);
		
		System.out.println(ABB.inOrder());
		ABB.swapChildren();
		System.out.println(ABB.inOrder());

	}
	
}
