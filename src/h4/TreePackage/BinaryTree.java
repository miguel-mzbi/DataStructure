package h4.TreePackage;

import h4.TreePackage.BTInterfaces.BTNode;
import h4.TreePackage.BTInterfaces.BTree;

public class BinaryTree implements BTree {

	BTNode root;
	
	public BinaryTree(BTNode node){
		this.root = node;
	}
	
	public boolean IsEmpty() {
		return this.root == null;
	}

	public BTNode gRoot() {
		return this.root;
	}

	public int Count() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int Size(BTNode ATree) {
		return this.Size(ATree, 0);
	}

	private int Size(BTNode temp, int n) {
		if(temp == null){
			return n;
		}
		n = this.Size(temp.gLeft(), n);
		n++;
		return this.Size(temp.gRight(), n);
	}

	public int Height(BTNode ATree) {
		return height(this.root, 0);
	}
		
	private int height(BTNode temp, int i){
		if(temp != null){
			i++;
			int alturaL = height(temp.gLeft(), i);
			int alturaR = height(temp.gRight(), i);
			return Math.max(alturaL, alturaR);
		}
		return i;
	}
	
	public int Size(){
		return this.Size(this.root);
	}
	
}
