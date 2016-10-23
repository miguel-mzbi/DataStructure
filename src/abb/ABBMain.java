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
		
		ABB<Integer, Integer> ABB2 = new ABB<Integer, Integer>();
		ABB2.add(37, 5);
		ABB2.add(20, 3);
		ABB2.add(59, 8);
		ABB2.add(4, 2);
		ABB2.add(21, 4);
		ABB2.add(53, 6);
		ABB2.add(85, 10);
		ABB2.add(1, 1);
		ABB2.add(57, 7);
		ABB2.add(61, 9);
		ABB2.add(94, 11);

		System.out.println(ABB2.inOrder());
		
	}

}
