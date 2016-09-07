package linearList;

public class ChainStackMain {
	public static void main(String[] args){
		ChainStack<Integer> chainStack = new ChainStack<Integer>();
		try{
			for(int i = 0; i<10; i++){
				chainStack.push(i);
			}
			System.out.println(chainStack.peek());
			System.out.println(chainStack.pop());
			System.out.println(chainStack.pop());
			System.out.println(chainStack.pop());

		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}
