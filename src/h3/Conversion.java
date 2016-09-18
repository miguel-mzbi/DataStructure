package h3;

public class Conversion {
	
	public static ArrayLinearList<Character> Recursive(ArrayLinearList<Character> a){
		int size = a.size();
		return Recursive(a, size);
	}
	public static ArrayLinearList<Character> Recursive(ArrayLinearList<Character> a, int size){
		if(size != 0){
			a.add(size-1, a.remove(0));
			size--;
			return Recursive(a, size);
		}
		else{
			return a;
		}
		
	}
	
	public static ArrayLinearList<Character> Iterative(ArrayLinearList<Character> a){
		int size = a.size();
		for(int i = size; i != 0; i--){
			a.add(size-1, a.remove(0));
		}
		return a;
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
