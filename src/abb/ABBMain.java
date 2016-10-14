package abb;

public class ABBMain {

	public static void main(String[] args) {
		ABB<Integer, Integer> ABB = new ABB<Integer, Integer>();
		ABB.put(37, 5);
		ABB.put(20, 3);
		ABB.put(59, 8);
		ABB.put(4, 2);
		ABB.put(21, 4);
		ABB.put(53, 6);
		ABB.put(85, 10);
		ABB.put(1, 1);
		ABB.put(57, 7);
		ABB.put(61, 9);
		ABB.put(94, 11);

		System.out.println(ABB.inOrder());
	}

}
