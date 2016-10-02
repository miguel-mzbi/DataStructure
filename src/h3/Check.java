package h3;

public class Check {
	
	public static boolean Iterative(Chain<Character> binaryChain){
		ChainStack<Character> ones = new ChainStack<Character>();
		if(binaryChain.size()%2 == 1){
			return false;
		}
		for(int i = 0; i < binaryChain.size(); i++){
			if(binaryChain.get(i) == '1'){
				ones.push(binaryChain.remove(i));
				i--;
			}
		}
		int noOnes = 0;
		while(!ones.isEmpty()){
			ones.pop();
			noOnes++;
		}
		if(noOnes == binaryChain.size()){
			return true;
		}
		else{
			return false;
		}
	}

	public static void main(String[] args) {
		Chain<Character> binaryChain = new Chain<Character>();
		//10 zero, 7 one
		binaryChain.add(0,'0');
		binaryChain.add(0,'1');
		binaryChain.add(0,'0');
		binaryChain.add(0,'0');
		binaryChain.add(0,'0');
		binaryChain.add(0,'1');
		binaryChain.add(0,'1');
		binaryChain.add(0,'1');
		binaryChain.add(0,'0');
		binaryChain.add(0,'0');
		binaryChain.add(0,'1');
		binaryChain.add(0,'0');
		binaryChain.add(0,'0');
		binaryChain.add(0,'1');
		binaryChain.add(0,'0');
		binaryChain.add(0,'0');
		binaryChain.add(0,'1');
		
		System.out.println(Iterative(binaryChain));
	}
}
