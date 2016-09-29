package h3;

public class Conversion {
	
	public static ArrayLinearList<Character> Recursive(ArrayLinearList<Character> a){
		
	}
	public static ArrayLinearList<Character> Recursive(ArrayLinearList<Character> a, int size){
		
	}
	
	public static ArrayLinearList<Character> Iterative(ArrayLinearList<Character> a){
		
	}
	
	public static void main(String[] args){
		ArrayLinearList<Character> array = new ArrayLinearList<Character>();
		array.add(0, 'D');
		array.add(0, 'C');
		array.add(0, 'B');
		array.add(0, 'A');
		array.add(0, '+');
		array.add(0, '+');
		array.add(0, '-');
		
		System.out.println(array.output());
		ArrayLinearList<Character> newArrayR = Recursive(array);
		System.out.println("Recursive:\n"+newArrayR.output());
		ArrayLinearList<Character> newArrayI = Iterative(array);
		System.out.println("Iterative:\n"+newArrayI.output());
	}
}
