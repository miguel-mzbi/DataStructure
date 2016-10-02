Conversion

Recursive method

public static ArrayLinearList<Character> Recursive(ArrayLinearList<Character> prefix, ArrayLinearList<Character> infix){
	ArrayLinearList<Character> operators = new ArrayLinearList<Character>();													1	
	ArrayLinearList<Character> postfix = new ArrayLinearList<Character>();														1
	ArrayLinearList<Character> prefixCopy = new ArrayLinearList<Character>();													1
	for(int i = 0; i < prefix.size(); i++){																						i+1 (i=size of prefix)
		prefixCopy.add(i, prefix.get(i));																						i
	}
	boolean firstSign = false;																									1
	for(int i = 0; i < prefixCopy.size(); i++){																					j+1 (j=size of prefixCopy)
		if(prefixCopy.get(i) == '+' || prefixCopy.get(i) == '-'){																j
			operators.add(operators.size(), prefixCopy.remove(i));																j
			i--;																												j
		}
	}
	if(prefixCopy.size() == operators.size()){																					1
		firstSign = true;																										1
	}
	return Recursive(prefixCopy, postfix, operators, firstSign);																1
}
public static ArrayLinearList<Character> Recursive(ArrayLinearList<Character> prefix, ArrayLinearList<Character> postfix, ArrayLinearList<Character> operators, boolean firstSign){
	Character operator = operators.remove(operators.size()-1);																	1
	if((postfix.size() == 0 && !firstSign) || (postfix.size() !=0 && Character.isDigit(postfix.get(postfix.size()-1)))){		1
		postfix.add(postfix.size(), prefix.remove(0));																			1
	}
	postfix.add(postfix.size(), prefix.remove(0));																				1
	postfix.add(postfix.size(), operator);																						1
	if(!operators.isEmpty()){																									1
		return Recursive(prefix, postfix, operators, firstSign);																n-1 (n=size of operators)
	}
	else{
		return postfix;																											1
	}
}

Complexity of Recursive method: O(i+j+n) -> O(n)

Iterative method

public static ArrayLinearList<Character> Iterative(ArrayLinearList<Character> prefix, ArrayLinearList<Character> infix){
	ChainStack<Character> operators = new ChainStack<Character>();																1
	ArrayLinearList<Character> postfix = new ArrayLinearList<Character>();														1
	boolean firstSign = false;																									1
	int operatorsQty = 0;																										1
	ArrayLinearList<Character> prefixCopy = new ArrayLinearList<Character>();													1
	for(int i = 0; i < prefix.size(); i++){																						i+1 (i=size of prefix)
		prefixCopy.add(i, prefix.get(i));																						i
	}
	for(int i = 0; i < prefixCopy.size(); i++){																					j+1 (j=size of prefixCopy)
		if(prefixCopy.get(i) == '+' || prefixCopy.get(i) == '-'){																j
			operators.push(prefixCopy.remove(i));																				j
			i--;																												j
			operatorsQty++;																										j
		}
	}
	if(prefixCopy.size() == operatorsQty){																						1
		firstSign = true;																										1
	}
	while(!operators.isEmpty()){																								n+1 (n=elements inside stack operators)
		Character operator = operators.pop();																					n
		if((postfix.size() == 0 && !firstSign) || (postfix.size() !=0 && Character.isDigit(postfix.get(postfix.size()-1)))){	n
			postfix.add(postfix.size(), prefixCopy.remove(0));
		}
		postfix.add(postfix.size(), prefixCopy.remove(0));																		n
		postfix.add(postfix.size(), operator);																					n
	}
	return postfix;																												1
}

Complexity of Iterative method: O(i+j+n) -> O(n)
