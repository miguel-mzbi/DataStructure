package linearList;

import java.util.Iterator;

public class HashtableMain {

	public static void main(String[] args) {
		HashTable<Integer, Integer> hashTable = new HashTable<Integer, Integer>();
		hashTable.add(1, 1);
		hashTable.add(2, 2);
		hashTable.add(3, 3);
		hashTable.add(11, 11);
		hashTable.add(12, 12);
		hashTable.add(14, 14);
		hashTable.add(27, 27);
		
		Iterator<Integer> iterator = hashTable.getKeyIterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
		Iterator<Integer> iterator2 = hashTable.getValueIterator();
		while(iterator2.hasNext()){
			System.out.println(iterator2.next());
		}
	}

}
