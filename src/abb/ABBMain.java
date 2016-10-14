package abb;

public class ABBMain {

	public static void main(String[] args) {
		ABB<Integer, Integer> ABB = new ABB<Integer, Integer>();
		ABB.add(37, 5);
		ABB.add(20, 3);
		ABB.add(59, 8);
		ABB.add(4, 2);
		ABB.add(21, 4);
		ABB.add(53, 6);
		ABB.add(85, 10);
		ABB.add(1, 1);
		ABB.add(57, 7);
		ABB.add(61, 9);
		ABB.add(94, 11);

		System.out.println(ABB.inOrder());
		
	}

}
