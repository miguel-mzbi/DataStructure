package h2;

public class AverageTimeChainArray {
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		//Test of time needed to process add and remove methods to a Chain List and an Array List
		//Using computer with W10, Intel i7-4770 CPU @3.40GHz and 8.00 RAM
		//The method to test is put between the longs for time.
		LinearListArray<Integer> AL = new LinearListArray<Integer>();
		ChainLinearList<Integer> CL = new ChainLinearList<Integer>();
		int n = 10000;
		
		//Add n elements to a chain (Worst Case)
//		for(int i = 0; i < n; i++){
//			CL.add(i, i);
//		}
		
		//Add n elements to the start of an array (Worst Case)
//		for(int i = 0; i < n; i++){
//			AL.add(0, i);
//		}
		
		//To remove de-commentate the corresponding fill method
		
		//Remove the n'th element of a chain (Worst Case)
//		CL.remove(n-1);
		
		//Remove the first element of an array (Worst Case)
//		AL.remove(0);

		//Time calculator.
		long iTime = System.nanoTime();
		//Put methods here, change size of list above.
		long fTime = System.nanoTime();
		
		System.out.println((fTime-iTime)/1000000.0);
		System.out.println("CL size: " + CL.size());
		System.out.println("AL size: " + AL.size());

	}
}
