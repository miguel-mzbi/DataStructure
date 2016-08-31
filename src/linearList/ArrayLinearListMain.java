package linearList;

public class ArrayLinearListMain {

	public static void main(String[] args) {
		ArrayLinearList<Integer> a = new ArrayLinearList<Integer>();
		ArrayLinearList<Integer> b = new ArrayLinearList<Integer>(2);
		try{
			a.add(0, 2);
			a.add(0, 1);
			a.add(2, 3);
			a.add(3, 4);
			a.add(4, 236134781);
			a.remove(4);
			System.out.println(a.get(0));
			System.out.println(a.get(1));
			System.out.println(a.get(2));
			System.out.println(a.get(3));
			System.out.println(a.size());
			System.out.println(a.output());
			
			b.add(0, 2);
			b.add(0, 1);
			b.add(2, 3);
			b.add(3, 4);
			b.add(4, 236134781);
			b.remove(4);
			System.out.println(b.get(0));
			System.out.println(b.get(1));
			System.out.println(b.get(2));
			System.out.println(b.get(3));
			System.out.println(b.size());
			System.out.println(b.output());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
