package projectDB;

/**
 * <b>class AVLTree</b>
 * <p>
 * The purpose of this class is to store all the invoice entries of the Invoice Table that belong to a single person.
 * It uses an AVL Tree data structure to store all the entries of the table. 
 * @param <E> - Element that extends Comparable (Usually will be an integer). It will act as key for each entry (node) of the tree.
 * @author MiguelMontoya - ArturoFornes
 */
public class AVLTree<E extends Comparable<E>> {

	Node root;
	private final int ALLOWED_IMBALANCE = 1;
	
	/**
	 * <b>AVLTree</b>
	 * <p>
	 * Constructor for the class AVLTree.
	 * <br>It initializes the tree's root to null.
	 */
	public AVLTree(){
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
	 * <b>getInvoice</b>
	 * <br><i>public Invoice getInvoice(E invNo)</i>
	 * <p>Method to obtain the invoice of a node with a selected key (invNo).
	 * @param invNo (E) invoinceNo of the node with the desired invoice entry.
	 * @return Invoice of the selected invNo, or null, if no node with that key is found
	 */
	public Invoice getInvoice(E invNo){
		Node node = this.root;
		int result;
		while(node != null){
			result = invNo.compareTo(node.invoiceNo);
			if(result == 0)
				return node.invoiceValue;
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
	 * @param n - (Node) Node to check balance
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
	private Node rightRotate(Node t){
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
	private Node rightLeftRotate(Node t){
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
	private Node leftRightRotate(Node t){
		//Fix the left reference first, then do a simple right rotation.
		t.left = leftRotate(t.left);
		return rightRotate(t);
	}

	/**
	 * <b>insert</b>
	 * <br><i>public void insert(E element,Invoice value)</i>
	 * <p>Method to insert a new node with the selected invNo (key) and invoice entry. 
	 * In the recursive method, its decided the position for the new node. If a node with the same key (invNo) is found, it is replaced.
	 * @param invNo - (E) invoiceNumber (key) of the new node of the AVLTree
	 * @param invoice - (Invoice) Invoice entry that will be stored in the node
	 */
	public void insert(E invNo, Invoice invoice){
		this.root = insert(invNo,this.root, invoice);
	}
	private Node insert(E element,Node node,Invoice value){
		if(node == null)
			return new Node(element,null,null,value);
		int compareResult = element.compareTo(node.invoiceNo);
		if(compareResult < 0)
			node.left = insert(element,node.left,value);
		else if (compareResult > 0)
			node.right = insert(element,node.right,value);
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
	 * <br><i>public Invoice remove(E key)</i>
	 * <p>Method to remove a node of the tree with the selected key (invNo)
	 * @param key - (E) invoiceNo of the node to remove.
	 * @return Invoice of the selected node to remove. If no node is found with such key, null is returned.
	 */
	public Invoice remove(E key){
		Node node = this.root;
		Invoice temp;
		while(node != null){
			int cmp = key.compareTo(node.invoiceNo);
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
			temp = node.invoiceValue;
			treeDelete(node);
			balance(node);
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
			int cmp = node.invoiceNo.compareTo(temp.invoiceNo);
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
	 * <b>inOrderForExpenses</b>
	 * <br><i>public String inOrderForExpenses()</i>
	 * <p>Method for query 3.
	 * <br>Prints all of the expenses a person has made InOrder. 
	 * First going trough each invoice, and the trough all the expenses of the invoice.
	 * @return String of all expenses made by a person as: Item Name - Expense
	 */
	public String inOrderForExpenses(){
		if(root != null){
			return this.inOrderForExpenses(root);
		}
		return "";
	}
	private String inOrderForExpenses(Node node){
		if(node == null)
			return "";
		String output = this.inOrderForExpenses(node.left);
		AVLTreeMod<Integer> exp;
		int i = 0, j  = 0;
		for(exp = node.invoiceValue.expenses.table[i] ; j < node.invoiceValue.expenses.getSize() ; exp = node.invoiceValue.expenses.table[++i]){
			if(exp != null){
				output += exp.inOrderForSelects();
				j++;
			}
		}
		output += this.inOrderForExpenses(node.right);
		return output;
	}

	/**
	 * <b>inOrderForPayments</b>
	 * <br><i>public String inOrderForPayments()</i>
	 * <p>Method for query 4.
	 * <br>Prints all of the payments a person has made InOrder. 
	 * It goes trough each invoice, obtaining the payment for each one.
	 * @return String of all payments made by a person as: Invoice - Payment
	 */
	public String inOrderForPayments(){
		if(root != null){
			return this.inOrderForPayments(root);
		}
		return "";
	}
	private String inOrderForPayments(Node node){
		if(node == null)
			return "";
		String output = this.inOrderForPayments(node.left);
		output += "\n"+ node.invoiceValue.toStringWOName();
		output += this.inOrderForPayments(node.right);
		return output;
	}
	
	/**
	 * <b>getEarnings</b>
	 * <br><i>public String getEarnings()</i>
	 * <p>Method for query 5.
	 * <br>Prints the earnings after the expense for a person. 
	 * It obtains first the total payments, and the subtracts the total expenses.
	 * @return String as: Total Expenses, Total Payments, Total Earnings.
	 */
	public String getEarnings(){
		if(root != null){
			int totalExpenses, totalPayments;
			totalExpenses = this.getTotalExpenses(root);
			totalPayments = this.getTotalPayments(root);
			return ("Total Expenses: "+ totalExpenses+ "\nTotal Payments: "+ totalPayments+ "\nTotal Earnings: "+ (totalPayments-totalExpenses));
		}
		return "";
	}
	
	/**
	 * <b>getTotalPayments</b>
	 * <br><i>private int getTotalPayments(Node node)</i>
	 * <p>Method for query 5.
	 * <br>Obtains the total payments made by a person.
	 * @param node - (Node) Node (Invoice) to obtain the payment related to it.
	 * @return Integer with the total payments of a person.
	 */
	private int getTotalPayments(Node node){
		if(node == null)
			return 0;
		int totalPayments = this.getTotalPayments(node.left);
		totalPayments += node.invoiceValue.payment;
		totalPayments += this.getTotalPayments(node.right);
		return totalPayments;
	}
	
	/**
	 * <b>getTotalExpenses</b>
	 * <br><i>private int getTotalExpenses(Node node)</i>
	 * <p>Method for query 5 and 6.
	 * <br>Obtains the total expenses made by a person. 
	 * @param node - (Node) Node (Invoice) to obtain all of the expenses related to it.
	 * @return Integer with the total expenses of a person.
	 */
	private int getTotalExpenses(Node node){
		if(node == null)
			return 0;
		int totalExpenses = this.getTotalExpenses(node.left);
		AVLTreeMod<Integer> exp;
		int i = 0, j  = 0;
		for(exp = node.invoiceValue.expenses.table[i] ; j < node.invoiceValue.expenses.getSize() ; exp = node.invoiceValue.expenses.table[++i]){
			if(exp != null){
				totalExpenses += exp.getExpensesSum();
				j++;
			}
		}
		totalExpenses += this.getTotalExpenses(node.right);
		return totalExpenses;
	}
	/**
	 * <b>getTotalExpenses</b>
	 * <br><i>public int getTotalExpenses()</i>
	 * <p>Method for query 6.
	 * <br>Obtains the total expenses made by a person. 
	 * @return Integer with the total expenses of a person.
	 */
	public int getTotalExpenses(){
		if(root != null){
			return this.getTotalExpenses(root);
		}
		return 0;
	}
	
	/**
	 * <b>class Node</b>
	 * <br>Inner Class of AVLTree
	 * <p>
	 * Each node (Entry) contains the invoice number as key for the node, and the invoice entry.
	 * @author MiguelMontoya - ArturoFornes
	 */
	class Node{
		E invoiceNo;
		Invoice invoiceValue;
		Node left,right;
		int height;
		
		/**
		 * <b>Node</b>
		 * <br><i>public Node(E invoiceNo, Node left, Node right, Invoice invoice)</i>
		 * <p>
		 * Constructor for the inner class node
		 * @param invoiceNo - (E) Key of the node, stores the invoiceNo (Usually an integer).
		 * @param left - (Node) Stores the left child of the current node.
		 * @param right - (Node) Stores the right child of the current node.
		 * @param invoice - (Invoice) Stores the invoice entry.
		 */
		public Node(E invoiceNo, Node left, Node right, Invoice invoice){
			this.invoiceNo = invoiceNo;
			this.left = this.right = null; 
			this.height = 1;
			this.invoiceValue = invoice;
		}
		
		/**
		 * <b>toString</b>
		 * <br><i>public String toString()</i>
		 * <p>
		 * Provides String with the invoiceNo (Key) and the height of the node.
		 * @return String as: [invoiceNo - height]
		 */
		public String toString(){
			return "["+this.invoiceNo+"-"+this.height+"]";
		}
	}	
	
}