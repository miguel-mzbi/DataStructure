package h4.MyQueue;

public class MyRadixSort {
	public static int[] RadixSort(int[] array){
		return RadixSort(array, String.valueOf(array[0]).length());
	}
	
	private static int[] RadixSort(int[] array, int l){
		int radix = 10;
		int power = 1;
		int digit;
		int[] temp;
		@SuppressWarnings("unchecked")
		MyScratchQueue<Integer>[] digitQs = (MyScratchQueue<Integer>[]) new MyScratchQueue[10];
		
		for(int i = 0; i < array.length; i++){
			digitQs[array[i]/power%10].Enqueue(i);
		}
		power *= 10;
		
		for(int j = 1; j < l; j++){
			for(int i = 0; i < array.length; i++){
				digitQs[array[i]/power%10].Enqueue(i);
			}
			power *= 10;
			for(int i = 0; i < radix -1; i++){
			}
		}
		return temp;
	}
}
