package midterm1;

public class E1RightShift{
	
	//O(n);
	public static int[] rightShift(int[] prevArray, int shift){
		int[] newArray = new int[prevArray.length+shift];
		for(int i = 0; i < shift; i++){
			newArray[i] = 0;
		}
		System.arraycopy(prevArray, 0, newArray, shift, prevArray.length);
		return newArray;
	}
	
	public static void main(String[] args){
		int[] array = {1,2,3,4,5,6};
		array = rightShift(array, 3);
		for(int i = 0; i < array.length; i++){
			System.out.println(array[i]);
		}
	}
}
