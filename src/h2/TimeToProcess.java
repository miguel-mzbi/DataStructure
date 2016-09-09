package h2;

public class TimeToProcess {
	static LinearListArray<Integer> AL = new LinearListArray<Integer>();
	static ChainLinearList<Integer> CL = new ChainLinearList<Integer>();
	
	public static void main(String[] args){
		
		//The method to test is put between the longs for time.
		int n = 10000;
		
		//To fill the lists
		for(int i = 0; i < n; i++)
			CL.add(i, i);
		for(int i = 0; i < n; i++)
			AL.add(i, i);
		
		//Methods to remove
//		CL.remove(n-1);
//		AL.remove(0);

		//Time calculator.
		long iTime = System.nanoTime();
		
		AL.remove(0);

		//Put methods here, manually change the sizes and indexes.
		
		long fTime = System.nanoTime();
		System.out.println((fTime-iTime)/1000000.0);
	}
}
