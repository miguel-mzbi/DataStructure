package midterm2;

public class E3LinkedBinaryTree {
	
	BinaryTreeNode root ; // root node
	
	public E3LinkedBinaryTree(){
		this.root = new BinaryTreeNode(0, null) ;
	}
	
	//Answer to question 1A. Design a method to get a . Recursive.
	public int maxHeightDifference(){
		if(this.root.key == 0){
			return 0;
		}
		int[] start = {0,0};
		int[] dif = maxHeightDifference(root, 0, start);
		return dif[0]-dif[1];
	}
		
	private int[] maxHeightDifference(BinaryTreeNode temp, int i, int[] dif){
		if(temp != null){
			i++;
			if(i > dif[0]){
				dif[0] = i;
			}
			if(temp.leftChild == null && temp.rightChild == null && (i < dif[1] || dif[1] == 0)){
				dif[1] = i;
			}
			dif = maxHeightDifference(temp.leftChild, i, dif);
			dif = maxHeightDifference(temp.rightChild, i, dif);
			return dif;
		}
		return dif;
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
		E3LinkedBinaryTree E2 = new E3LinkedBinaryTree();
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
		E2.put(22, 11);
		E2.put(-1, 11);
		E2.put(-100, 11);
		
		System.out.println(E2.maxHeightDifference());

	}
	
}
