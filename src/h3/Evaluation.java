package h3;

public class Evaluation {
	
	public static int Recursive(Chain<Character> prefix){
		Chain<Character> chainNum = new Chain<Character>();
		Chain<Character> chainSym = new Chain<Character>();
		int size = prefix.size();
		int lenght = prefix.size()/2;
		for(int i = 0; i < size; i++){
			if(i <= lenght-1){
				chainSym.add(i, prefix.remove(0));
			}
			else{
				chainNum.add(i-lenght, prefix.remove(0));
			}
		}
		return Recursive(chainSym, chainNum, 0);
	}
	public static int Recursive(Chain<Character> sym, Chain<Character> num, int result){
		if(sym.size() != num.size()){
			result += Character.getNumericValue(num.remove(0));
		}
		else if (!num.isEmpty()){
			if(sym.remove(0) == '+'){
				result += Character.getNumericValue(num.remove(0));
			}
			else{
				result -= Character.getNumericValue(num.remove(0));
			}
			return Recursive(sym, num, result);
		}
		return result;
	}
	
	public static int Iterative(Chain<Character> prefix){
		ChainStack<Character> stackNum = new ChainStack<Character>();
		ChainStack<Character> stackSym = new ChainStack<Character>();
		int size = prefix.size();
		for(int i = 1; i <= size; i++){
			if(i <= size/2){
				stackSym.push(prefix.remove(0));
			}
			else{
				stackNum.push(prefix.remove(0));
			}
		}
		int result = 0;
		while(!stackNum.isEmpty()){
			if(stackSym.isEmpty() || stackSym.pop() == '+'){
				result += Character.getNumericValue(stackNum.pop());
			}
			else{
				result -= Character.getNumericValue(stackNum.pop());
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		Chain<Character> chain = new Chain<Character>();
		Chain<Character> chain2 = new Chain<Character>();
		System.out.println("1-2-3+4-5+6 = 1\n");
		//--+-+123456
		chain.add(0, '6');
		chain.add(0, '5');
		chain.add(0, '4');
		chain.add(0, '3');
		chain.add(0, '2');
		chain.add(0, '1');
		chain.add(0, '+');
		chain.add(0, '-');
		chain.add(0, '+');
		chain.add(0, '-');
		chain.add(0, '-');
		System.out.println(chain.output());
		System.out.println("Recursive result: "+Recursive(chain));
		
		chain2.add(0, '6');
		chain2.add(0, '5');
		chain2.add(0, '4');
		chain2.add(0, '3');
		chain2.add(0, '2');
		chain2.add(0, '1');
		chain2.add(0, '+');
		chain2.add(0, '-');
		chain2.add(0, '+');
		chain2.add(0, '-');
		chain2.add(0, '-');
		System.out.println(chain2.output());
		System.out.println("Iterative result: "+Iterative(chain2));

	}
}
