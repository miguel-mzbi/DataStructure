package abb;

public class TournamentMin implements WinnerTree{
	int[] players, tree;
	int offset, lowExt;

	public int getWinner() {
		return tree == null? -1: this.players[tree[1]];
	}

	public void initialize(int[] p) {
		int n = p.length -1;
		if(n < 2) throw new IllegalArgumentException();
		this.players = p;
		this.tree = new int[n];
		int s, i;
		for(s = 1; s*2 <= n -1; s += s);
		this.lowExt = 2*(n-s); //Jugadores más bajos
		this.offset = 2*s -1; //Jugador más a la derecha
		
		//Creación del arbol. Solamente nodos externos. Base 2
		for(i = 2; i <= this.lowExt; i += 2){
			this.play((i + this.offset)/2, i - 1, i);
		}
		//Si el numeros de jugadores es impar
		if(n % 2 == 1){ //Modulo para checar si es impar
			this.play(n/2, this.tree[n - 1], this.lowExt +1);//Jugar uno impar
			i = this.lowExt +3; //Avanzar un paso en la alocación de jugadores y uno extra por impar
		}
		else{//Si todavía falta un númeor par de jugadores que acomodar, que no es potencia de 2.
			i = this.lowExt +2; //Avanzar un paso en la alocación de jugadores
		}
		//Comienza el acomodo de jugadores extras
		for(; i <= n; i+= 2){
			this.play((i - this.lowExt + n -1)/2, i -1, i);
		}
	}

	public void play(int parent, int left, int right) {
		tree[parent] = players[left] < players[right]? left: right;
		if(parent%2 == 1 && parent > 1){
			this.play(parent/2, this.tree[parent-1], this.tree[parent]);
		}
	}

	public void replay(int p) {
		int n = this.players.length -1;
		if(p <= 0 || p > n) throw new IndexOutOfBoundsException("Invalid");
		int matchNode, right, left;
		//If location is in the exterior lowest level
		if(p <= this.lowExt){
			matchNode = (this.offset + p)/2;
			left = 2*matchNode - this.offset;
			right = left + 1;
		}
		//If location is on a inner low level
		else{
			matchNode = (p- this.lowExt + n -1)/ 2 ;
			//If changed value is odd
			if(2 * matchNode == n -1){
				left = this.tree[2 * matchNode];
				right = left + 1;
			}
			//Regular even selection
			else{
				left = 2 * matchNode - n + 1 + this.lowExt;
				right = left + 1;
			}
		}
		this.tree[matchNode] = this.players[left] < this.players[right]? left: right; //Change parent value
		//If odd number of players
		if(matchNode == n - 1 &&  n%2 == 1){
			matchNode /= 2;
			this.tree[matchNode] = this.players[this.tree[n - 1]]<(this.players[this.lowExt + 1])?
					this.tree[n - 1] : this.lowExt + 1;
		}
		//Parents reorganization
		matchNode /= 2;
		for(; matchNode >= 1; matchNode /= 2) 
			this.tree[matchNode] = 
			this.players[this.tree[2 * matchNode]]<(this.players[this.tree[2 * matchNode + 1]])?
					this.tree[2 * matchNode] : this.tree[2 * matchNode + 1];
		
	}
	
	public void change(int p, int value){
		this.players[p] = value;
		this.replay(p);
	}
	
	public String toString(){
		String toReturn = "[LowExt: " + this.lowExt + ", OffSet: " + this.offset + "]\n";
		for(int i = 1; i < this.tree.length; i++){
			toReturn += "[" + this.players[this.tree[i]] + "]";
		}
		return toReturn;
	}
	
	public int[] organizeArray(int[] array){
		int [] copy = new int[array.length];
		System.arraycopy(array, 0, copy, 0, array.length);
		this.initialize(copy);
		int[] toReturn = new int[array.length];
		for(int i = 1; i < array.length; i++){
			toReturn[i] = this.getWinner();
			this.change(this.tree[1], 999999999);
		}
		return toReturn;
	}
	
}
