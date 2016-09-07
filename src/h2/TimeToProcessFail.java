package h2;

import java.util.Arrays;

public class TimeToProcessFail {

	public static void main(String[] args) {
		int[] qnt = {1,10,100,1000,10000};
		double[] addC = new double[qnt.length];
		double[] removeC = new double[qnt.length];
		double[] addA = new double[qnt.length];
		double[] removeA = new double[qnt.length];
		long startTime, endTime;
		
		//How long does it take to add n element to the n'th position (Worst Case). Using Chain.
		for(int i = 0; i < qnt.length; i++){
			ChainLinearList<Integer> chain = new ChainLinearList<Integer>();

			if(i==0){
				startTime = System.nanoTime();
				chain.add(0, 0);
				endTime = System.nanoTime();
			}
			else{
				startTime = System.nanoTime();
				for(int j = 0; j < qnt[i]; j++){
					chain.add(j, j);
				}
				endTime = System.nanoTime();
			}

			addC[i]=(endTime - startTime)/1000000.0;
			
		//How long does it take to remove an element in the n'th position (Worst Case). Using Chain.

			startTime = System.nanoTime();
			chain.remove(qnt[i]-1);
			endTime = System.nanoTime();

			removeC[i]=(endTime - startTime)/1000000.0;
		}
		
		//How long does it take to add n element the first position (Worst Case). Using Array.
		for(int i = 0; i < qnt.length; i++){
			LinearListArray<Integer> arrayList = new LinearListArray<Integer>();

			if(i==0){
				startTime = System.nanoTime();
				arrayList.add(0, 0);
				endTime = System.nanoTime();
			}
			else{
				startTime = System.nanoTime();
				for(int j = 0; j<qnt[i]; j++){
					arrayList.add(0, j);
				}
				endTime = System.nanoTime();
			}
			
			addA[i]=(endTime - startTime)/1000000.0;
			
		//How long does it take to remove an element in the first position (Worst Case). Using Chain.
			startTime = System.nanoTime();
			arrayList.remove(0);
			endTime = System.nanoTime();

			removeA[i]=(endTime - startTime)/1000000.0;
		}
		
		System.out.println("Quantity      " + Arrays.toString(qnt));
		System.out.println("Add Chain     " + Arrays.toString(addC));
		System.out.println("Add Array     " + Arrays.toString(addA));
		System.out.println("Remove Chain  " + Arrays.toString(removeC));
		System.out.println("Remove Array  " + Arrays.toString(removeA));


	}
}
