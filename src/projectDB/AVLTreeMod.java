package projectDB;

/**
 * <b>class AVLTreeMod</b>
 * <p>The purpose of this class is to store all the expense entries of the Expense Table where the item name is the same.
 * It uses an AVL Tree data structure to store all the entries with the same item name.
 * <br>Because all the nodes of the tree are of the same unique item name, it is useful to consider the expense quantity as the key for each node.
 * <br>Remember that the rest of expenses of other items, are stored in the hash map, where this AVLTree belongs to. 
 * @param <E> - Element that extends Comparable (Usually will be an integer). It will act as key for each entry (node) of the tree.
 * @author MiguelMontoya - ArturoFornes
 */
public class AVLTreeMod<E extends Comparable<E>> {
	//AVL Tree that belongs to the Expenses Table.
	//It stores all the expenses where the item name is the same. 
	//Remember that the AVL Tree is stores in a hash map for the expenses of only one invoice.
	//Because an exact expense can be repeated, instead of inserting a new node in the tree, a counter for the node is increased or decreased (Depending on the situation)
	//Because all the nodes of the tree are of the same unique item name, it is useful to consider the expense quantity as the key.
	Node root;
	private final int ALLOWED_IMBALANCE = 1;
	
	/**
	 * <b>AVLTreeMod</b>
	 * <br><i>public AVLTreeMod()</i>
	 * <p>Constructor for the class AVLTreeMod.
	 * <br>It initializes the tree's root to null.
	 */
	public AVLTreeMod(){
		this.root = null;
	}
	
	/**
	 * <b>node</b>
	 * <br><i>private int height(Node node)</i>
	 * <p>Method to obtain the height of a node
	 * @param node - (Node) Node of which the height will be obtained
	 * @return Integer for the height value 
	 */
	private int height(Node node){
		if(node == null)
			return 0;
		return node.height;
	}
	
	/**
	 * <b>getExpense</b>
	 * <br><i>public Expense getExpense(E exp)</i>
	 * <p>Method to obtain the expense of a node with a selected key (exp).
	 * @param exp (E) expenseQty of the node with the desired expense entry.
	 * @return Expense of the selected expenseQty, or null, if no node with that key is found
	 */
	public Expense getExpense(E exp){
		Node node = this.root;
		int result;
		while(node != null){
			result = exp.compareTo(node.expenseQty);
			if(result == 0)
				return node.expense;
			else if(result < 0)
				node = node.left;
			else if(result > 0)
				node = node.right;
		}
		return null;
	}
	
	/**
	 * <b>balance</b>
	 * <br><i>public Node balance()</i>
	 * <p>Checks if AVL's property of balance is broken. Selects proper rotations.
	 * @param t - (Node) Node to check balance
	 * @return Node that will take place the of the given node. If there is no imbalance (or the node is null) the provided node is returned.
	 */
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
	
	/**
	 * <b>rightRotate</b>
	 * <br><i>private Node rightRotate(Node t)</i>
	 * <p>Method to make a right rotation at the selected node.
	 * @param t - (Node) Parent node with imbalance.
	 * @return New balanced parent.
	 */
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
	
	/**
	 * <b>leftRotate</b>
	 * <br><i>private Node leftRotate(Node t)</i>
	 * <p>Method to make a left rotation at the selected node.
	 * @param t - (Node) Parent node with imbalance.
	 * @return New balanced parent.
	 */
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
	
	/**
	 * <b>rightLeftRotate</b>
	 * <br><i>private Node rightLeftRotate(Node t)</i>
	 * <p>Method to make a right, then left rotation at the selected node.
	 * Left node is sent first to left rotation.
	 * @param t - (Node) Parent node with imbalance
	 * @return New balanced parent
	 */
	private Node rightLeftRotate(Node t) {
		//Fix the right reference first, then do a simple left rotation.
		t.right = rightRotate(t.right);
		return leftRotate(t);
	}
	
	/**
	 * <b>leftRightRotate</b>
	 * <br><i>private Node leftRightRotate(Node t)</i>
	 * <p>Method to make a left, then right rotation at the selected node.
	 * Left node is sent first to left rotation.
	 * @param t - (Node) Parent node with imbalance
	 * @return New balanced parent
	 */
	private Node leftRightRotate(Node t) {
		//Fix the left reference first, then do a simple right rotation.
		t.left = leftRotate(t.left);
		return rightRotate(t);
	}

	/**
	 * <b>insert</b>
	 * <br><i>public void insert(E expQty, Expense expense)</i>
	 * <p>Method to insert a new node with the selected expenseQty (key) and expense entry. 
	 * In the recursive method, its decided the position for the new node. If a node with the same key (expQty) is found, it is replaced.
	 * @param expQty - (E) expenseQuantity (key) of the new node of the AVLTree
	 * @param invoice - (Expense) Expense entry that will be stored in the node
	 */
	public void insert(E expQty, Expense expense){
		this.root = insert(expQty, this.root, expense);
	}
	private Node insert(E element, Node node, Expense value){
		if(node == null)
			return new Node(element,null,null,value);
		int compareResult = element.compareTo(node.expenseQty);
		if(compareResult < 0)
			node.left = insert(element,node.left,value);
		else if (compareResult > 0)
			node.right = insert(element,node.right,value);
		else{
			node.count++;
		}
		return balance(node);
	}
	
	/**
	 * <b>preOrder</b>
	 * <br><i>public String preOrder()</i>
	 * <p>Method to go trough all the nodes PreOrder
	 * @return String as: [Node][Node]...
	 */
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
	
	/**
	 * <b>inOrder</b>
	 * <br><i>public String inOrder()</i>
	 * <p>Method to go trough all the nodes InOrder
	 * @return String as: [Node][Node]...
	 */
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
	
	/**
	 * <b>remove</b>
	 * <br><i>public Expense remove(E key)</i>
	 * <p>Method to remove a node of the tree with the selected key (expQty)
	 * @param key - (E) expenseQty of the node to remove.
	 * @return Expense of the selected node to remove. If no node is found with such key, null is returned.
	 */
	public Expense remove(E key){
		Node node = this.root;
		Expense temp;
		while(node != null){
			int cmp = key.compareTo(node.expenseQty);
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
			temp = node.expense;
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
	
	/**
	 * <b>findParent</b>
	 * <br><i>private Node findParent(Node node)</i>
	 * <p>Method to find the parent of a selected node of the AVL Tree
	 * @param node - (Node) Node which parent is needed.
	 * @return Parent node of selected node.
	 */
	private Node findParent(Node node){
		if(node.equals(this.root)){
			return null;
		}
		Node temp = this.root;
		Node parent = temp;
		while(temp != null){
			int cmp = node.expenseQty.compareTo(temp.expenseQty);
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
	
	/**
	 * <b>maximum</b>
	 * <br><i>private Node maximum(Node node)</i>
	 * <p>Method to find the maximum child of the selected node. 
	 * The maximum child of a node, is the child node located to the right, as far as possible.
	 * @param node - (Node) Parent node to analyze.
	 * @return Maximum node.
	 */
	private Node maximum(Node node){
		if(node == null)
			return null;
		else if(node.right == null)
			return node;
		while(node.right != null)
			node = node.right;
		return node;
	}
	
	/**
	 * <b>inOrderForSelects</b>
	 * <br><i>public String inOrderForSelects()</i>
	 * <p>Method for query 3.
	 * <br>Prints all of the expenses inside an item name, InOrder. 
	 * To access this method. Other methods 'iterate' trough all of the invoices, and then trough all the distinct item names of the invoice.
	 * @return String of all expenses made by a person as: Item Name - Expense
	 */
	public String inOrderForSelects(){
		if(root != null){
			return this.inOrderForSelects(root);
		}
		return "";
	}
	private String inOrderForSelects(Node node){
		if(node == null)
			return "";
		String output = this.inOrderForSelects(node.left);
		for(int i = 1; i <= node.count; i++){
			output += "\n"+node.expense.toString();
		}
		output += this.inOrderForSelects(node.right);
		return output;
	}
	
	//Get total expenses for this item from the invoice
	/**
	 * <b>getExpensesSum</b>
	 * <br><i>public int getExpensesSum()</i>
	 * <br>Obtains the sum of all the expenses
	 * @return Integer of the sum of expenses
	 */
	public int getExpensesSum(){
		if(root != null){
			return this.getExpensesSum(root);
		}
		return 0;
	}
	private int getExpensesSum(Node node){
		if(node == null){
			return 0;
		}
		int output = this.getExpensesSum(node.left);
		for(int i = 1; i <= node.count; i++){
			output += node.expense.expense;
		}
		output += this.getExpensesSum(node.right);
		return output;

	}
	
	/**
	 * <b>class Node</b>
	 * <br>Inner Class of AVLTreeMod
	 * <p>
	 * Each node (Entry) contains the expense quantity as key for the node, and the expense entry.
	 * @author MiguelMontoya - ArturoFornes
	 */
	class Node{
		int count;
		E expenseQty;
		Expense expense;
		Node left,right;
		int height;
		
		/**
		 * <b>Node</b>
		 * <br><i>public Node(E invoiceNo, Node left, Node right, Invoice invoice)</i>
		 * <p>
		 * Constructor for the inner class node
		 * @param quantity - (E) Key of the node, stores the expense quantity
		 * @param left - (Node) Stores the left child of the current node.
		 * @param right - (Node) Stores the right child of the current node.
		 * @param exp - (Expense) Stores the expense entry
		 */
		public Node(E quantity, Node left, Node right, Expense exp){
			this.expenseQty = quantity;
			this.left = this.right = null; 
			this.height = 1;
			this.expense = exp;
			this.count = 1;
		}
		
		/**
		 * <b>toString</b>
		 * <br><i>public String toString()</i>
		 * <p>
		 * Provides String with the expenseQty (Key) and the height of the node.
		 * @return String as: [expenseQty - height]
		 */
		public String toString(){
			return "["+this.expenseQty.toString()+"-"+this.height+"]";
		}
	}
}