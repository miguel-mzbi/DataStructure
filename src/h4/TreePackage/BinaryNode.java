package h4.TreePackage;

import h4.TreePackage.BTInterfaces.BTNode;

public class BinaryNode implements BTNode{

	int key;
	BTNode left, right;
	
	public BinaryNode(int key){
		this.key = key;
	}
	
	public int gKey() {
		return this.key;
	}

	public BTNode gLeft() {
		return this.left;
	}

	public BTNode gRight() {
		return this.right;
	}

	public void sKey(int AValue) {
		this.key = AValue;
	}

	public void sLeft(BTNode AValue) {
		this.left = AValue;
	}

	@Override
	public void sRight(BTNode AValue) {
		this.right = AValue;
	}

}
