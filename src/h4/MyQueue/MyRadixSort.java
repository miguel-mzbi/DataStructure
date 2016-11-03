package h4.MyQueue;

public class MyRadixSort {
	public static int[] RadixSort(int[] array){
		return RadixSort(array, String.valueOf(array[0]).length());
	}
	
	@SuppressWarnings("unchecked")
	private static int[] RadixSort(int[] array, int l){
		int radix = 10;
		int power = 1;
		int [] indexes = new int[array.length];
		MyScratchQueue<Integer>[] digitQs = (MyScratchQueue<Integer>[]) new MyScratchQueue[10];
		MyScratchQueue<Integer>[] indexQs = (MyScratchQueue<Integer>[]) new MyScratchQueue[10];

		
		for(int i = 0; i < radix; i++){
			digitQs[i] = new MyScratchQueue<Integer>();
			indexQs[i] = new MyScratchQueue<Integer>();
		}
		
		for(int j = 0; j < l; j++){
			for(int i = 0; i < array.length; i++){
				digitQs[array[i]/power%10].Enqueue(array[i]);
				indexQs[array[i]/power%10].Enqueue(i);
			}
			for(int i = 0, k = 0; i < radix; i++){
				while(!digitQs[i].empty()){
					array[k] = digitQs[i].Dequeue();
					indexes[k] = indexQs[i].Dequeue();
					k++;
				}
			}
			power *= 10;
		}
		System.out.println("Is sorted? "+isSorted(array));
		return indexes;
	}
	
	public static boolean isSorted(int[] array){
		for(int i = 0; i < array.length; i++){
			if(i+1 < array.length && array[i] > array[i+1]){
				return false;
			}
		}
		return true;
	}
}
