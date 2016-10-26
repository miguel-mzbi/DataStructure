package h4.MyStack;

public class MainTest {

	public static void main(String[] args) {
		MyDerivedStack<Integer> stackD = new MyDerivedStack<Integer>();
		System.out.println(stackD.empty());
		stackD.push(1);
		System.out.println(stackD.peek());
		stackD.push(2);
		stackD.push(3);
		System.out.println(stackD.peek());
		System.out.println(stackD.empty());
		System.out.println(stackD.pop());
		System.out.println(stackD.peek());
		
		System.out.println("");
		MyScratchStack<Integer> stackS = new MyScratchStack<Integer>();
		System.out.println(stackS.empty());
		stackS.push(1);
		System.out.println(stackS.peek());
		stackS.push(2);
		stackS.push(3);
		System.out.println(stackS.peek());
		System.out.println(stackS.empty());
		System.out.println(stackS.pop());
		System.out.println(stackS.peek());

	}

}
