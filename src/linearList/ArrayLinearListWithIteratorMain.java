package linearList;

public class ArrayLinearListWithIteratorMain {

	public static void main(String[] args) {
		ArrayLinearListWithIterator<Integer> a = new ArrayLinearListWithIterator<Integer>(9);
		ArrayLinearListWithIterator<Integer>.IteratorArray iterate = a.new IteratorArray();
		try{
			a.add(0, 0);
			a.add(1, 1);
			a.add(2, 2);
			a.add(3, 3);
			a.add(4, 4);
			a.add(5, 236134781);
			while(iterate.hasNext()){
				System.out.println(iterate.next());
			}
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
