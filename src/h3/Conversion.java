package h3;

public class Conversion {
	
	public static ArrayLinearList<Character> Recursive(ArrayLinearList<Character> prefix, ArrayLinearList<Character> infix){
		ArrayLinearList<Character> operators = new ArrayLinearList<Character>();
		ArrayLinearList<Character> postfix = new ArrayLinearList<Character>();
		ArrayLinearList<Character> prefixCopy = new ArrayLinearList<Character>();
		for(int i = 0; i < prefix.size(); i++){
			prefixCopy.add(i, prefix.get(i));
		}
		boolean firstSign = false;
		int operatorsQty = 0;
		for(int i = 0; i < prefixCopy.size(); i++){
			if(prefixCopy.get(i) == '+' || prefixCopy.get(i) == '-'){
				operators.add(operators.size(), prefixCopy.remove(i));
				i--;
				operatorsQty++;
			}
		}
		if(prefixCopy.size() == operatorsQty){
			firstSign = true;
		}
		return Recursive(prefixCopy, postfix, operators, firstSign);
	}
	public static ArrayLinearList<Character> Recursive(ArrayLinearList<Character> prefix, ArrayLinearList<Character> postfix, ArrayLinearList<Character> operators, boolean firstSign){
		Character operator = operators.remove(operators.size()-1);
		if((postfix.size() == 0 && !firstSign) || (postfix.size() !=0 && Character.isDigit(postfix.get(postfix.size()-1)))){
			postfix.add(postfix.size(), prefix.remove(0));
		}
		postfix.add(postfix.size(), prefix.remove(0));
		postfix.add(postfix.size(), operator);
		if(!operators.isEmpty()){
			return Recursive(prefix, postfix, operators, firstSign);
		}
		else{
			return postfix;
		}
	}
	
	public static ArrayLinearList<Character> Iterative(ArrayLinearList<Character> prefix, ArrayLinearList<Character> infix){
		ChainStack<Character> operators = new ChainStack<Character>();
		ArrayLinearList<Character> postfix = new ArrayLinearList<Character>();
		boolean firstSign = false;
		int operatorsQty = 0;
		ArrayLinearList<Character> prefixCopy = new ArrayLinearList<Character>();
		for(int i = 0; i < prefix.size(); i++){
			prefixCopy.add(i, prefix.get(i));
		}
		for(int i = 0; i < prefixCopy.size(); i++){
			if(prefixCopy.get(i) == '+' || prefixCopy.get(i) == '-'){
				operators.push(prefixCopy.remove(i));
				i--;
				operatorsQty++;
			}
		}
		if(prefixCopy.size() == operatorsQty){
			firstSign = true;
		}
		while(!operators.isEmpty()){
			Character operator = operators.pop();
			if((postfix.size() == 0 && !firstSign) || (postfix.size() !=0 && Character.isDigit(postfix.get(postfix.size()-1)))){
				postfix.add(postfix.size(), prefixCopy.remove(0));
			}
			postfix.add(postfix.size(), prefixCopy.remove(0));
			postfix.add(postfix.size(), operator);
		}
		return postfix;
	}
	
	public static void main(String[] args){
		ArrayLinearList<Character> prefix = new ArrayLinearList<Character>();
		ArrayLinearList<Character> infix = new ArrayLinearList<Character>();
		String prefixStatement = "-+++ABCD";
		String infixStatement = "+A+B+C-D";
		
		for(int i = 0; i < prefixStatement.length(); i++){
			prefix.add(i, prefixStatement.charAt(i));
			infix.add(i, infixStatement.charAt(i));
		}

		
		System.out.println(prefix.output());
		System.out.println(infix.output());
		ArrayLinearList<Character> newArrayR = Recursive(prefix, infix);
		System.out.println("Recursive:\n"+newArrayR.output());
		ArrayLinearList<Character> newArrayI = Iterative(prefix, infix);
		System.out.println("Iterative:\n"+newArrayI.output());
	}
}
