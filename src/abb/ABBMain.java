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

		System.out.println("InOrder: " + ABB.inOrder());
		System.out.println("PreOrder:" + ABB.preOrder());
		System.out.println("PreOrder It:" + ABB.preOrderIt());
		System.out.println("PostOrder:" + ABB.postOrder());
		System.out.println("PorNiveles:" + ABB.porNiveles());
		System.out.println("CualesPorNivel 1:" + ABB.cualesPorNivel(1));
		System.out.println("CualesPorNivel 2:" + ABB.cualesPorNivel(2));
		System.out.println("CualesPorNivel 3:" + ABB.cualesPorNivel(3));
		System.out.println("CualesPorNivel 4:" + ABB.cualesPorNivel(4));
		System.out.println("CualesPorNivel 5:" + ABB.cualesPorNivel(5));
		System.out.println("Descendente:" + ABB.descendente());
		System.out.println("MayoresA 30:" + ABB.mayoresA(30));
		System.out.println("CuantasHojas:" + ABB.cuantasHojas());
		System.out.println("Hojas:" + ABB.hojas());
		System.out.println("Size:" + ABB.size());
		
		System.out.println("");
		System.out.println("Removed 10 (null): " + ABB.remove(10));
		System.out.println("InOrder: " + ABB.inOrder());
		System.out.println("Size:" + ABB.size());
		
		System.out.println("");
		System.out.println("Removed 1: " + ABB.remove(1));
		System.out.println("InOrder: " + ABB.inOrder());
		System.out.println("Size:" + ABB.size());
		
		System.out.println("");
		System.out.println("Removed 53: " + ABB.remove(53));
		System.out.println("InOrder: " + ABB.inOrder());
		System.out.println("Size:" + ABB.size());
		
		System.out.println("");
		System.out.println("Removed 37: " + ABB.remove(37));
		System.out.println("InOrder: " + ABB.inOrder());
		System.out.println("Size:" + ABB.size());
		
		System.out.println("");
		System.out.println("Removed 59: " + ABB.remove(59));
		System.out.println("InOrder: " + ABB.inOrder());
		System.out.println("Size:" + ABB.size());
		
		System.out.println("");
		System.out.println("CualesPorNivel 1:" + ABB.cualesPorNivel(1));
		System.out.println("CualesPorNivel 2:" + ABB.cualesPorNivel(2));
		System.out.println("CualesPorNivel 3:" + ABB.cualesPorNivel(3));
		System.out.println("CualesPorNivel 4:" + ABB.cualesPorNivel(4));
		System.out.println("CualesPorNivel 5:" + ABB.cualesPorNivel(5));


	}

}
