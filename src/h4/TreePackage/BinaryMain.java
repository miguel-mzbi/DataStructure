package h4.TreePackage;

public class BinaryMain {
	public static void main(String[] args){
		BinaryNode a = new BinaryNode(4);
		BinaryNode b = new BinaryNode(3);
		BinaryNode c = new BinaryNode(2);
		c.sLeft(b);
		c.sRight(a);
		BinaryNode d = new BinaryNode(1);
		d.sLeft(c);
		BinaryTree BT = new BinaryTree(d);
		System.out.println(BT.Size());
		System.out.println(BT.Count());

	}
}
