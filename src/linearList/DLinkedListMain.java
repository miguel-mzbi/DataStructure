package linearList;

public class DLinkedListMain {

	public static void main(String[] args){
		DLinkedList<Integer> DLinkList = new DLinkedList<Integer>();
		
		try{
			DLinkList.addFirst(4);
			DLinkList.addLast(8);
			DLinkList.addFirst(10);
			System.out.println(DLinkList.output());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
