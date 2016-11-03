package abb;

public class AVLTreeMain {

	public static void main(String[] args) {
		AVLTree<Integer> AVL = new AVLTree<Integer>();
		AVL.insert(84);
		AVL.insert(10);
		AVL.insert(8);
		AVL.insert(92);
		AVL.insert(66);
		AVL.insert(88);
		AVL.insert(29);
		AVL.insert(27);
		AVL.insert(75);
		AVL.insert(72);
		AVL.insert(68);
		AVL.insert(62);
		AVL.insert(18);
		AVL.insert(80);
		AVL.insert(36);
		AVL.insert(1);
		AVL.insert(40);
		System.out.println("InOrder: " + AVL.inOrder());
	}

}
