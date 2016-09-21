Recursive method

public static ArrayLinearList<Character> Recursive(ArrayLinearList<Character> a){
	int size = a.size();																			1
	return Recursive(a, size);																		n (n=size)
}
public static ArrayLinearList<Character> Recursive(ArrayLinearList<Character> a, int size){
	if(size != 0){																					n
		a.add(size-1, a.remove(0));																	n
		size--;																						n
		return Recursive(a, size);																	n
	}
	else{
		return a;																					1
	}
}

Complexity of Recursive method: O(n)


Iterative method

public static ArrayLinearList<Character> Iterative(ArrayLinearList<Character> a){					
	int size = a.size();																			1
	for(int i = size; i != 0; i--){																	i (i=size)
		a.add(size-1, a.remove(0));																	i
	}
	return a;																						1
}

Complexity of Iterative method: O(i) -> O(n)