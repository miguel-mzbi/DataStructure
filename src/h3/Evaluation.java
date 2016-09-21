package h3;

public class Evaluation {
	
	public static int Recursive(Chain<Character> c){
		Chain<Character> chainNum = new Chain<Character>();
		Chain<Character> chainSym = new Chain<Character>();
		int s = c.size();
		int l = c.size()/2;
		for(int i = 0; i < s; i++){
			if(i <= l-1){
				chainSym.add(i, c.remove(0));
			}
			else{
				chainNum.add(i-l, c.remove(0));
			}
		}
		return Recursive(chainSym, chainNum, 0);
	}
	public static int Recursive(Chain<Character> sym, Chain<Character> num, int res){
		if(sym.size() != num.size()){
			res += Character.getNumericValue(num.remove(0));
		}
		else if (!num.isEmpty()){
			if(sym.remove(0) == '+'){
				res += Character.getNumericValue(num.remove(0));
			}
			else{
				res -= Character.getNumericValue(num.remove(0));
			}
			return Recursive(sym, num, res);
		}
		return res;
	}
	
	public static int Iterative(Chain<Character> c){
		ChainStack<Character> stackNum = new ChainStack<Character>();
		ChainStack<Character> stackSym = new ChainStack<Character>();
		int s = c.size();
		for(int i = 1; i <= s; i++){
			if(i <= s/2){
				stackSym.push(c.remove(0));
			}
			else{
				stackNum.push(c.remove(0));
			}
		}
		int r = 0;
		while(!stackNum.isEmpty()){
			if(stackSym.isEmpty() || stackSym.pop() == '+'){
				r += Character.getNumericValue(stackNum.pop());
			}
			else{
				r -= Character.getNumericValue(stackNum.pop());
			}
		}
		return r;
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
