package linearList;

public class QueueCircularArrayListMain {

	public static void main(String[] args) {
		QueueCircularArrayList<Integer> QC = new QueueCircularArrayList<Integer>(10);
		try{
			for(int i = 1; i<10; i++){
				QC.enqueue(i);
			}
			System.out.println(QC.output());
			for(int i = 0; i<5; i++){
				System.out.println(QC.dequeue());
			}
			System.out.println(QC.output());
			for(int i = 10; i<16; i++){
				QC.enqueue(i);
			}
			System.out.println(QC.output());
			for(int i = 0; i<5; i++){
				System.out.println(QC.dequeue());
			}
			System.out.println(QC.output());
			for(int i = 16; i<21; i++){
				QC.enqueue(i);
			}
			System.out.println(QC.output());

			QC.enqueue(21);
			System.out.println(QC.output());
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
//		try {
//			
//			QC.enqueue(1);
//			QC.enqueue(2);
//			QC.enqueue(3);
//			QC.enqueue(4);
//			QC.enqueue(5);
//			QC.enqueue(6);
//			QC.enqueue(7);
//			QC.enqueue(8);
//			
//			System.out.println(QC.output());
//			
//			System.out.println(QC.dequeue());
//			System.out.println(QC.dequeue());
//			System.out.println(QC.dequeue());
//			System.out.println(QC.dequeue());
//			
//			
//		}catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}

}
