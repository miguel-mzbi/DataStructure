Evaluation

Recursive method

public static int Recursive(Chain<Character> c){
	Chain<Character> chainNum = new Chain<Character>();							1
	Chain<Character> chainSym = new Chain<Character>();							1
	int s = c.size();															1
	int l = c.size()/2;															1
	for(int i = 0; i < s; i++){													i (i=size of c)
		if(i <= l-1){															1
			chainSym.add(i, c.remove(0));										1
		}																			
		else{																	1	
			chainNum.add(i-l, c.remove(0));										1
		}
	}
	return Recursive(chainSym, chainNum, 0);									1 
}

public static int Recursive(Chain<Character> sym, Chain<Character> num, int res){
	if(sym.size() != num.size()){												1
		res += Character.getNumericValue(num.remove(0));						1
	}
	else if (!num.isEmpty()){													1
		if(sym.remove(0) == '+'){												1
			res += Character.getNumericValue(num.remove(0));					1
		}
		else{
			res -= Character.getNumericValue(num.remove(0));					1
		}
		return Recursive(sym, num, res);										n-1 (n=size of chainNum)
	}
	return res;																	1
}

Complexity of Recursive method: O(n)


Iterative method

public static int Iterative(Chain<Character> c){
	ChainStack<Character> stackNum = new ChainStack<Character>();				1
	ChainStack<Character> stackSym = new ChainStack<Character>();				1
	int s = c.size();															1
	for(int i = 1; i <= s; i++){												s (s=size of c)
		if(i <= s/2){															1
			stackSym.push(c.remove(0));											1
		}
		else{																	1
			stackNum.push(c.remove(0));											1
		}
	}
	int r = 0;																	1
	while(!stackNum.isEmpty()){													n (n=size of stackNum)
		if(stackSym.isEmpty() || stackSym.pop() == '+'){						1
			r += Character.getNumericValue(stackNum.pop());
		}
		else{
			r -= Character.getNumericValue(stackNum.pop());						1
		}
	}
	return r;																	1
}

Complexity of Iterative method: O(s) -> O(n)
