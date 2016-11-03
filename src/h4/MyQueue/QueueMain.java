package h4.MyQueue;

import java.util.Random;

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
//		int[] a = {10,60,90,70,20,60,97,16,23};
//		for(int i = 0; i < a.length; i++){
//			System.out.print("["+a[i]+"]");
//		}
//		System.out.println("");
		int[] a = new int[20000];
		Random r = new Random();
		for(int i = 0; i < 20000; i++){
			a[i] = r.nextInt(1999999999 - 1000000000) +1000000000;
		}
		a = MyRadixSort.RadixSort(a);
//		for(int i = 0; i < a.length; i++){
//			System.out.print("["+a[i]+"]");
//		}


	}
}
