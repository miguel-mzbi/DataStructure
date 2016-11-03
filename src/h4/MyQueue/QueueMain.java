package h4.MyQueue;

public class QueueMain {
	public static void main(String[] args){
		MyScratchQueue<Integer> Q = new MyScratchQueue<Integer>();
		Q.Enqueue(0);
		Q.Enqueue(1);
		Q.Enqueue(1);
		Q.Enqueue(2);
		Q.Enqueue(3);
		Q.Enqueue(5);
		Q.Enqueue(8);
		Q.Enqueue(13);
		int s = Q.size();
		for(int i = 0; i < s; i++){
			Integer l = Q.Dequeue();
			System.out.println(l);
			Q.Enqueue(l);
		}
		


	}
}
