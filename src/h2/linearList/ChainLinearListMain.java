package h2.linearList;

public class ChainLinearListMain {

	public static void main(String[] args){
		ChainLinearList<Integer> linkList = new ChainLinearList<Integer>();
		
		try{
			linkList.add(0, 2);
			linkList.add(0, 1);
			linkList.add(2, 3);
			linkList.add(3, 4);
			System.out.println(linkList.output());
			linkList.add(4, 236134781);
			System.out.println(linkList.output());
			linkList.remove(4);
			System.out.println(linkList.get(0));
			System.out.println(linkList.get(1));
			System.out.println(linkList.get(2));
			System.out.println(linkList.get(3));
			System.out.println(linkList.size());
			System.out.println(linkList.output());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
