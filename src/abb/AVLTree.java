package abb;

public class AVLTree<E extends Comparable<E>> {

	private AVLNode root;
	private static final int ALLOWED_IMBALANCE = 1;
	
	public AVLTree(){
		this.root = null;
	}
	
	/**
	 * insert - Recursive method to add new AVLNode to the AVLTree with given value
	 * @param element - E with node's value
	 */
	public void insert(E element){
		this.root = this.insert(element, this.root);
	}
	
	/**
	 * insert - Recursive method to add new AVLNode to the AVLTree with given value
	 * @param element - element to add
	 * @param n - Node to compare and decide route of insertion
	 * @return Current level node after balance
	 */
	private AVLNode insert(E element, AVLNode n){
		if(n == null)
			return new AVLNode(element);
		int cmp = element.compareTo(n.element);
		if(cmp < 0)
			n.left = this.insert(element, n.left); //Next left
		else if(cmp > 0)
			n.right = this.insert(element, n.right); //Next right
		return this.balance(n); //Check for imbalance
	}
	
	/**
	 * height - Obtain height of given node
	 * @param n - AVLNode to analyze
	 * @return height of n, or 0 if null
	 */
	private int height(AVLNode n){
		return n == null? 0: n.height;
	}
	
	/**
	 * balance - Checks if property of balance is broken. Selects proper rotations
	 * @param n - Node to check balance
	 * @return Node that will take place the of the given node. If there is no imbalance the provided node is returned.
	 */
	private AVLNode balance(AVLNode n){
		if(n == null)
			return n;
		if(this.height(n.left) - this.height(n.right) > ALLOWED_IMBALANCE){
			if(this.height(n.left.left) >= this.height(n.left.right))
				n = rotateWithLeftChild(n); //LL - Rotate right
			else
				n = doubleWithLeftChild(n); //LR - Rotate left (With right child), then right
		}
		else if(this.height(n.right) - this.height(n.left) > ALLOWED_IMBALANCE){
			if(this.height(n.right.right) >= this.height(n.right.left))
				n = rotateWithRightChild(n); //RR - Rotate left
			else
				n = doubleWithRightChild(n); //RL - Rotate right (With left child), then left
		}
		n.height = Math.max(this.height(n.left), this.height(n.right)) + 1;
		return n;
	}
	
	/**
	 * rotateWithLeftChild - (LL) Equivalent to Simple Right Rotation
	 * @param n - Parent node with imbalance
	 * @return new balanced parent
	 */
	private AVLNode rotateWithLeftChild(AVLNode n){ //LL
		AVLNode futureParent = n.left;
		AVLNode newLeftOfPrevParent = n.left.right;
		futureParent.right = n;
		n.left = newLeftOfPrevParent;
		
		n.height = Math.max(this.height(n.left), this.height(n.right))+1;
		futureParent.height = Math.max(this.height(futureParent.left), this.height(futureParent.right))+1;
		
		return futureParent;
	}
	
	/**
	 * rotateWithRightChild - (RR) Equivalent to Simple Left Rotation
	 * @param n - Parent node with imbalance
	 * @return new balanced parent
	 */
	private AVLNode rotateWithRightChild(AVLNode n){ //LL
		AVLNode futureParent = n.right;
		AVLNode newRightOfPrevParent = n.right.left;
		futureParent.left = n;
		n.right = newRightOfPrevParent;
		
		n.height = Math.max(this.height(n.left), this.height(n.right))+1;
		futureParent.height = Math.max(this.height(futureParent.left), this.height(futureParent.right))+1;
		
		return futureParent;
	}
	
	/**
	 * doubleWithLeftChild - (LR) Equivalent to Left then Right Rotation
	 * Left node is sent first to left rotation
	 * @param n - Parent node with imbalance
	 * @return new balanced parent
	 */
	private AVLNode doubleWithLeftChild(AVLNode n){ //LR
		n.left = this.rotateWithRightChild(n.left); //RR of left child
		return this.rotateWithLeftChild(n); //LL
	}
	
	/**
	 * doubleWithRightChild - (RL) Equivalent to Right then Left Rotation
	 * Right node is sent first to right rotation
	 * @param n - Parent node with imbalance
	 * @return new balanced parent
	 */
	private AVLNode doubleWithRightChild(AVLNode n){ //RL
		n.right = this.rotateWithLeftChild(n.right); //LL of right child
		return this.rotateWithRightChild(n); //RR
	}
	
	/**
	 * inOrder - Recursive method to go trough all the nodes InOrder
	 * @return String [key, value]
	 */
	public String inOrder(){
		return inOrder(this.root);
	}
	
	private String inOrder(AVLNode temp){
		if(temp == null){
			return "";
		}
		String output = this.inOrder(temp.left);
		output += temp;
		output += this.inOrder(temp.right);
		return output;
	}
	
	/**
	 * AVLNode class
	 * @author MiguelAngel
	 * Stores the element and height of each node of the AVL Tree alongside its children
	 */
	private class AVLNode{
		E element;
		int height;
		AVLNode left, right;
		
		public AVLNode(E element){
			this.element = element;
			this.left = this.right = null;
			this.height = 1;
		}
		
		public String toString(){
			return "["+this.element+"-"+this.height+"]";
		}
	}
}
