package proyecto;

public class AVLTreeMod<E extends Comparable<E>, Value > {
	//AVL Tree that belongs to the Expenses Table.
	//It stores all the expenses where the item name is the same. 
	//Remember that the AVL Tree is stores in a hash map for the expenses of only one invoice.
	//Because an exact expense can be repeated, instead of inserting a new node in the tree, a counter for the node is increased or decreased (Depending on the situation)
	//Because all the nodes of the tree are of the same unique item name, it is useful to consider the expense quantity as the key.
	class Node{
		int count;
		E element;
		Value value;
		Node left,right;
		int height;
		
		private Node(E element, Node left, Node right,Value value){
			this.element = element;
			this.left = this.right = null; 
			this.height = 1;
			this.value = value;
			this.count = 1;
		}
		public String toString(){
			return "["+this.element.toString()+"-"+this.height+"]";
		}
	}	
	
	Node root;
	private final int ALLOWED_IMBALANCE = 1;
	
	public AVLTreeMod(){
		this.root = null;
	}
	
	private int height(Node node){
		if(node == null)
			return 0;
		return node.height;
	}
	
	public Value getValue(E key){
		Node node = this.root;
		int result;
		while(node != null){
			result = key.compareTo(node.element);
			if(result == 0)
				return node.value;
			else if(result < 0)
				node = node.left;
			else if(result > 0)
				node = node.right;
		}
		return null;
	}
	
	public Node balance(Node t){
		if(t == null)
			return t;
		//When the left child's height is greater.
		else if (height(t.left)-height(t.right)>this.ALLOWED_IMBALANCE){
			//LeftLeft Case
			if(height(t.left.left)>=height(t.left.right))
				t = rightRotate(t);
			//LeftRight Case
			else
				t = leftRightRotate(t);
		}
		//When the right child's height is greater.
		else if (height(t.right)-height(t.left)>this.ALLOWED_IMBALANCE){
			//RightRight Case
			if(height(t.right.right)>=height(t.right.left))
				t = leftRotate(t);
			//RightLeft Case
			else
				t = rightLeftRotate(t);
		}
		t.height = Math.max(height(t.left),height(t.right))+1;
		return t;
	}
	private Node rightRotate(Node t) {
		if(t == null){
			return null;
		}
		//Save reference to the left child of the given node
		//and the right child of the left child.
		Node left = t.left;
		Node futureLeft = left.right;
		//Make the left child the new root of the subtree.
		left.right = t;
		t.left = futureLeft;
		
		//Change the height of the node.
		t.height = Math.max(height(t.left),height(t.right))+1;
		left.height = Math.max(height(left.left),height(left.right))+1;
		return left;
	}
	private Node leftRotate(Node t) {
		if(t == null){
			return null;
		}
		//Save reference to the right child of the given node
		//and the left child of the right child.
		Node right = t.right;
		Node futureRight = right.left;
		//Make the right child the new root of the subtree.
		right.left = t;
		t.right = futureRight;
		
		//Change the height of the node.
		t.height = Math.max(height(t.left),height(t.right))+1;
		right.height = Math.max(height(right.left),height(right.right))+1;
		return right;
	}
	private Node rightLeftRotate(Node t) {
		//Fix the right reference first, then do a simple left rotation.
		t.right = rightRotate(t.right);
		return leftRotate(t);
	}
	private Node leftRightRotate(Node t) {
		//Fix the left reference first, then do a simple right rotation.
		t.left = leftRotate(t.left);
		return rightRotate(t);
	}

	public void insert(E element,Value value){
		this.root = insert(element,this.root, value);
	}
	private Node insert(E element,Node node,Value value){
		if(node == null)
			return new Node(element,null,null,value);
		int compareResult = element.compareTo(node.element);
		if(compareResult < 0)
			node.left = insert(element,node.left,value);
		else if (compareResult > 0)
			node.right = insert(element,node.right,value);
		else{
			node.count++;
		}
		return balance(node);
	}
	
	public String preOrder(){
		if(this.root != null){
			return this.preOrder(this.root);
		}
		return "";
	}
	private String preOrder(Node node){
		if(node == null)
			return "";
		String output = node.toString();
		output += this.preOrder(node.left);
		output += this.preOrder(node.right);
		return output;
	}
	public String inOrder(){
		if(root != null){
			return this.inOrder(root);
		}
		return "";
	}
	private String inOrder(Node node){
		if(node == null)
			return "";
		String output = this.inOrder(node.left);
		output += node.toString();
		output += this.inOrder(node.right);
		return output;
	}
	
	public Value remove(E key){
		Node node = this.root;
		Value temp;
		while(node != null){
			int cmp = key.compareTo(node.element);
			if(cmp == 0)
				break;
			else if(cmp > 0)
				node = node.right;
			else if(cmp < 0)
				node = node.left;
		}
		if(node == null)
			return null;
		else{
			temp = node.value;
			node.count--;
			if(node.count <= 0){
				treeDelete(node);
				balance(node);
			}
		}
		return temp;
	}
	private void treeDelete(Node node){
		if(node.left == null) transplant(node,node.right);
		else if(node.right == null) transplant(node,node.left);
		else{
			Node max = maximum(node.left);
			if(!findParent(max).equals(node)){
				transplant(max,max.right);
				max.right = node.right;
			}
			transplant(node,max);
			Node replace = null;
			if(max.left != null) replace = max.left;
			max.left = node.left;
			while(node.left != null){
				node = node.left;
			}
			node.right = replace;
		}
	}
	private void transplant(Node u, Node v){
		Node uParent = findParent(u);
		
		if(uParent == null){
			this.root = v;
		}
		else if(u == uParent.left){
			uParent.left = v;
		}
		else{
			uParent.right = v;
		}
	}
	private Node findParent(Node node){
		if(node.equals(this.root)){
			return null;
		}
		Node temp = this.root;
		Node parent = temp;
		while(temp != null){
			int cmp = node.element.compareTo(temp.element);
			if(cmp == 0)
				break;
			else if(cmp > 0){
				parent = temp;
				temp = temp.right;
			}
			else if(cmp < 0){
				parent = temp;
				temp = temp.left;
			}
		}
		return parent;
	}
	private Node maximum(Node node){
		if(node == null)
			return null;
		else if(node.right == null)
			return node;
		while(node.right != null)
			node = node.right;
		return node;
	}

}
