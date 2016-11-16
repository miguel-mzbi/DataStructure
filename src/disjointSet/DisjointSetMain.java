package disjointSet;

public class DisjointSetMain {
	public static void main(String[] args){
		DisjointSet DS = new DisjointSet();
		DS.makeSet(10);
		DS.Union(0, 1);
		System.out.println(DS.numDisjointSets());
		System.out.println(DS.Find(0));
		System.out.println(DS.Find(1));
		System.out.println(DS.sizeOfSet(1));
		DS.Union(2, 0);
		DS.Union(8, 6);
		DS.Union(1, 6);
		
		System.out.println(DS.numDisjointSets());
		System.out.println(DS.Find(6));
		System.out.println(DS.Find(8));
		System.out.println(DS.Find(0));
		System.out.println(DS.sizeOfSet(1));
		
	}
}
