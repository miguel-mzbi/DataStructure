package abb;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
	 * contains - Method that returns boolean for the presence of certain key in the ABB
	 * @param key - Key to search
	 * @return boolean representing the presence of the key in the ABB
	 */
	public boolean contains(Key key){
		return this.get(key) != null;
	}
	
	/**
	 * inOrder - Recursive method to go trough all the nodes InOrder
	 * @return String [key, value]
	 */
	public String inOrder(){
		return inOrder(this.root);
	}
	
	private String inOrder(ABBNode temp){
		if(temp == null){
			return "";
		}
		String output = this.inOrder(temp.left);
		output += temp;
		output += this.inOrder(temp.right);
		return output;
	}
	
	/**
	 * preOrder - Recursive method to go trough all the nodes in PreOrder form
	 * @return String [key, value]
	 */
	public String preOrder(){
		return preOrder(this.root);
	}

	private String preOrder(ABBNode temp){
		if(temp == null){
			return "";
		}
		String output = temp.toString();
		output += this.preOrder(temp.left);
		output += this.preOrder(temp.right);
		return output;
	}

	/**
	 * preOrderIt - Iterative method to go trough all the nodes in PreOrder form
	 * Uses a stack for storing future right nodes to traverse and a queue to store te correct order for the output
	 * @return String [key, value]
	 */
	public String preOrderIt(){
		ABBNode temp = this.root;
		if(temp == null){
			return "";
		}
		else{
			Stack<ABBNode> rightNodes = new Stack<ABBNode>();
			Queue<ABBNode> preOrdered = new LinkedList<ABBNode>();
			while(temp != null){
				preOrdered.add(temp);
				if(temp.right != null){
					rightNodes.push(temp.right);
				}
				if(temp.left != null){
					temp = temp.left;
				}
				else if(!rightNodes.isEmpty()){
					temp = rightNodes.pop();
				}
				else{
					break;
				}
			}
			String output = "";
			while(!preOrdered.isEmpty()){
				output += preOrdered.poll();
			}
			return output;
		}
	}
	
	/**
	 * postOrder - Recursive method to go trough all the nodes in PostOrder form
	 * @return String [key, value]
	 */
	public String postOrder(){
		return postOrder(this.root);
	}
	
	private String postOrder(ABBNode temp){
		if(temp == null){
			return "";
		}
		String output = this.postOrder(temp.left);
		output += this.postOrder(temp.right);
		output += temp;
		return output;
	}
	
	/**
	 * porNiveles - Method that traverses all levels and returns a string with all the nodes of the ABB. Ordered from left to right and up to down.
	 * Doesn't use FIFO structure. Uses FIFO concepts.
	 * @return String [key, value]
	 */
	public String porNiveles(){ //Rename to perLevel
		String output = "";
		if(this.root == null){
			return output;
		}
		else{
			for(int i = 1; i <= this.altura(); i++){
				output += this.cualesPorNivel(i);
			}
		}
		return output;
	}
	
	/**
	 * descendente - Recursive method that returns a string with the nodes in descending order (Right to left)
	 * @return String [key, value]
	 */
	public String descendente(){ //Rename to descendant
		return this.descedant(this.root);
	}
	
	private String descedant(ABBNode temp){
		if(temp == null){
			return "";
		}
		String output = this.descedant(temp.right);
		output += temp;
		output += this.descedant(temp.left);
		return output;
	}
	
	/**
	 * cualesPorNivel - Returns the nodes at a selected level of the ABB. Nodes are traversed from left to right.
	 * @param lvl - Int providing desired level
	 * @return String [key, value]
	 */
	public String cualesPorNivel(int lvl){ //Rename to inLevel
		return this.cualesPorNivel(this.root, lvl, 0);
	}
	
	private String cualesPorNivel(ABBNode temp, int lvl, int i){ //Rename to inLevel
		if(temp == null){
			return "";
		}
		i++;
		if(lvl == i){
			return temp.toString();
		}
		String output = this.cualesPorNivel(temp.left, lvl, i);
		output += this.cualesPorNivel(temp.right, lvl, i);
		return output;
	}
	
	/**
	 * mayoresA - Recursive method that returns the nodes where the key is bigger than the provided key.
	 * Uses comparable
	 * @param key - Key to compare
	 * @return String [key, value]
	 */
	public String mayoresA(Key key){ //Rename to biggerThan
		return this.biggerThan(this.root, key);
	}
	
	private String biggerThan(ABBNode temp, Key key){
		if(temp == null){
			return "";
		}
		String output = this.biggerThan(temp.left, key);
		if(key.compareTo(temp.key) < 0){
			output += temp;
		}
		output += this.biggerThan(temp.right, key);
		return output;
	}
	
	/**
	 * cuantasHojas - Recursive method that returns the number of nodes that are leaves (No children)
	 * @return int with the quantity of leaves
	 */
	public int cuantasHojas(){ //Rename to numberOfLeaves
		return numberOfLeaves(this.root, 0);
	}
	
	private int numberOfLeaves(ABBNode temp, int n){
		if(temp == null){
			return n;
		}
		n = numberOfLeaves(temp.left, n);
		if(temp.left == null && temp.right == null){
			n++;
		}
		return numberOfLeaves(temp.right, n);
	}
	
	/**
	 * hojas - Recursive method that returns the nodes that are leaves (No children)
	 * @return String String [key, value]
	 */
	public String hojas(){ //Rename to leaves
		return leaves(this.root, "");
	}
	
	private String leaves(ABBNode temp, String output){
		if(temp == null){
			return output;
		}
		output = leaves(temp.left, output);
		if(temp.left == null && temp.right == null){
			output += temp;
		}
		return leaves(temp.right, output);
	}
	
	/**
	 * altura - Recursive method that returns height from root
	 * @return int corresponding to height
	 */
	public int altura(){ //Rename to height
		return height(this.root, 0);
	}
		
	private int height(ABBNode temp, int i){
		if(temp != null){
			i++;
			int alturaL = height(temp.left, i);
			int alturaR = height(temp.right, i);
			return Math.max(alturaL, alturaR);
		}
		return i;
	}

	/**
	 * size - Recursive method that returns the number of nodes in ABB
	 * @return int corresponding to size
	 */
	public int size(){
		return this.size(this.root, 0);
	}
	
	private int size(ABBNode temp, int n){
		if(temp == null){
			return n;
		}
		n = this.size(temp.left, n);
		n++;
		return this.size(temp.right, n);
	}	
	
	public Value remove(Key key){
		if(this.root == null || this.contains(key) == false){
			return null;
		}
		ABBNode toRemove = this.removing(key);
		return toRemove.value;
	}
	
	private ABBNode removing(Key key){
		ABBNode temp = this.root;
		ABBNode parent = null;
		ABBNode toRemove = null;
		int dir = -1; //0-left, 1-right
		while(temp != null){
			int cmp = key.compareTo(temp.key);
			if(cmp == 0){
				toRemove = temp;
				break;
			}
			else if(cmp > 0){
				parent = temp;
				temp = temp.right;
				dir = 1;
			}
			else if(cmp < 0){
				parent = temp;
				temp = temp.left;
				dir = 0;
			}
		}
		if(temp.left == null && temp.right == null){
			if(dir == -1){
				this.root = null;
			}
			else if(dir == 0){
				parent.left = null;
			}
			else{
				parent.right = null;
			}
		}
		else if(temp.left == null){
			if(dir == -1){
				this.root = temp.right;
			}
			else if(dir == 0){
				parent.left = temp.right;
			}
			else{
				parent.right = temp.right;
			}
		}
		else if(temp.right == null){
			if(dir == -1){
				this.root = temp.left;
			}
			else if(dir == 0){
				parent.left = temp.left;
			}
			else{
				parent.right = temp.left;
			}
		}
		else if(temp.left != null && temp.right != null){
			ABBNode replacement = this.removing(this.getBiggerLeft(temp.left).key);
			if(dir == -1){
				replacement.left = temp.left;
				replacement.right = temp.right;
				this.root = replacement;
			}
			else if(dir == 0){
				replacement.left = temp.left;
				replacement.right = temp.right;
				parent.left = replacement;
			}
			else{
				replacement.left = temp.left;
				replacement.right = temp.right;
				parent.right = replacement;
			}
		}
		return toRemove;
	}
	
	private ABBNode getBiggerLeft(ABBNode temp){
		if(temp == null){
			return null;
		}
		ABBNode toReturn = getBiggerLeft(temp.right);
		if(toReturn != null){
			return toReturn;
		}
		return temp;
	}

	/**
	 * ABBNode class
	 * @author MiguelAngel
	 * Stores the key and value of each node of the tree
	 */
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
