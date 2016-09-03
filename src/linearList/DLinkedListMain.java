package linearList;

public class DLinkedListMain {

	public static void main(String[] args){
		DLinkedList<Integer> DLinkList = new DLinkedList<Integer>();
		
		try{
			DLinkList.addFirst(2);
			DLinkList.addLast(4);
			DLinkList.addFirst(1);
			DLinkList.add(2, 3);
			DLinkList.addFirst(0);
			System.out.println(DLinkList.output());
			System.out.println(DLinkList.size()+"\n");

			DLinkList.remove(2);
			System.out.println(DLinkList.output());
			System.out.println(DLinkList.size()+"\n");
			
			DLinkedList<Integer>.ListItr DLLIter = DLinkList.new ListItr();
			while(DLLIter.hasNext()){
				System.out.println(DLLIter.next());
			}
			while(DLLIter.hasPrevious()){
				System.out.println(DLLIter.previous());
			}




		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
