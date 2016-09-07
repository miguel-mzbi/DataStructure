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
	}

}
