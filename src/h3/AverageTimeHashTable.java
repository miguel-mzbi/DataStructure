package h3;

public class AverageTimeHashTable {
	
	public static void main(String[] args){
		//Test of time needed to process add and remove methods to a HashTable
		//Using computer with W10, Intel i7-4770 CPU @3.40GHz and 8.00 RAM
		HashTableOpenAddressing<Integer, Integer> HT = new HashTableOpenAddressing<Integer, Integer>();
		
		//Number of entries to be added
		int n = 100000;
		
		//Random key to remove
		int keyToRemove = (int)(Math.random() * n + 1);
				
		//Add n elements
//		for(int i = 0; i < n; i++){
//			HT.add(i, i);
//		}
		
				
		//Remove a random key of the hashtable
//		HT.remove(keyToRemove);

		//Time calculator to add n elements.
		long iTimeAdd = System.nanoTime();
		
		for(int i = 0; i < n; i++){
			HT.add(i, i);
		}
		
		long fTimeAdd = System.nanoTime();
		
		//Time calculator to remove random key.
		long iTimeRemove = System.nanoTime();
		
		HT.remove(keyToRemove);
		
		long fTimeRemove = System.nanoTime();
		
		System.out.println("Time to add "+n+" elements: "+(fTimeAdd-iTimeAdd)/1000000.0);
		System.out.println("Time to remove the key "+keyToRemove+": "+(fTimeRemove-iTimeRemove)/1000000.0);

	}
}
