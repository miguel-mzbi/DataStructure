package linearList;

public class QueueChainListMain {
	public static void main(String[] args) {
		QueueChainList<Integer> linkList = new QueueChainList<Integer>();
		try{
			System.out.println("Front + Enqueue + rear");
			linkList.enqueue(0);
			System.out.println(linkList.rear()+"\n");
			System.out.println(linkList.front());
			linkList.enqueue(1);
			System.out.println(linkList.rear()+"\n");
			System.out.println(linkList.front());
			linkList.enqueue(2);
			System.out.println(linkList.rear()+"\n");
			System.out.println(linkList.front());
			linkList.enqueue(3);
			System.out.println(linkList.rear()+"\n");
			System.out.println(linkList.front());
			linkList.enqueue(4);
			System.out.println(linkList.rear()+"\n");
			System.out.println("Size"+linkList.size()+"\n");
			
			System.out.println("Fron + Dequeue + rear");
			System.out.println(linkList.front());
			linkList.dequeue();
			System.out.println(linkList.rear()+"\n");
			System.out.println(linkList.front());
			linkList.dequeue();
			System.out.println(linkList.rear()+"\n");
			System.out.println(linkList.front());
			linkList.dequeue();
			System.out.println(linkList.rear()+"\n");
			System.out.println(linkList.front());
			linkList.dequeue();
			System.out.println(linkList.rear()+"\n");
			System.out.println(linkList.front());
			linkList.dequeue();
			System.out.println(linkList.rear());
			System.out.println("Size"+linkList.size());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
