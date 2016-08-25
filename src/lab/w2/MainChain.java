package lab.w2;

public class MainChain {

	public static void main(String[] args){
		Chain<Integer> linkList = new Chain<Integer>();
		try{
			linkList.add(0, 2);
			linkList.add(0, 1);
			linkList.add(2, 3);
			linkList.add(3, 4);
			linkList.add(4, 236134781);
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
