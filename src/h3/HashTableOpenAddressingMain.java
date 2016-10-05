package h3;

import java.util.Iterator;

public class HashTableOpenAddressingMain {

	public static void main(String[] args) {
		HashTableOpenAddressing<Integer, Integer> hash = new HashTableOpenAddressing<Integer, Integer>(1);
		
		System.out.println(hash.isEmpty());
		hash.add(5, 5);
		hash.output();
		System.out.println(hash.getValue(5));
		hash.add(22, 6);
		System.out.println(hash.getValue(22));
		hash.output();
		hash.add(39, 2);
		System.out.println(hash.getValue(39));
		hash.output();
		System.out.println(hash.getSize());
		System.out.println(hash.contains(5));
		System.out.println(hash.contains(22));
		System.out.println(hash.contains(39));
		System.out.println(hash.contains(56));
		System.out.println(hash.isEmpty());
		
		System.out.println();
		
		hash.clear();
		hash.output();
		hash.add(5, 5);
		hash.add(22, 6);
		hash.add(39, 2);
		hash.output();

		System.out.println("Removed: "+hash.remove(22));
		System.out.println(hash.getSize());
		hash.output();

		System.out.println();
		
		System.out.println("Took out: "+hash.remove(39));
		System.out.println(hash.getSize());
		hash.output();

		System.out.println("Took out: "+hash.remove(5));
		System.out.println(hash.getSize());
		hash.output();

		hash.add(4, 4);
		hash.add(5, 5);
		hash.add(14, 14);
		hash.add(7, 7);
		hash.add(1, 1);
		hash.output();
		
		System.out.println(hash.remove(5));
		System.out.println(hash.remove(14));
		System.out.println(hash.remove(7));
		hash.output();

		System.out.println("\nIterator");
		Iterator<Integer> iterator = hash.getKeyIterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		System.out.println("End of Iterator\n");
		
		System.out.println(hash.remove(5));
		System.out.println(hash.remove(14));
		System.out.println(hash.remove(7));
		System.out.println(hash.remove(4));
		hash.output();

	}

}
