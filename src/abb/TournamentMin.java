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
		this.lowExt = 2*(n-s);
		this.offset = 2*s -1;
		for(i = 2; i <= this.lowExt; i += 2){
			this.play((i + this.offset)/2, i - 1, i);
		}
		while(i < this.players.length){
			this.play((i - this.lowExt + n -1)/2, i -1, i);
			i += 2;
		}
	}

	public void play(int parent, int left, int right) {
		tree[parent] = players[left] < players[right]? left: right;
//		while(parent%2 == 1 && parent > 1){
//			this.tree[parent/2] = this.players[this.tree[parent-1]] < 
//					this.players[this.tree[parent]]? 
//							this.tree[parent-1]: tree[parent];
//			parent /= 2;
//		}
		if(parent%2 == 1 && parent > 1){
			this.play(parent/2, this.tree[parent-1], this.tree[parent]);
		}
	}

	public void replay(int p) {
		
	}
	
	public String toString(){
		String toReturn = "[LowExt: " + this.lowExt + ", OffSet: " + this.offset + "]\n";
		for(int i = 1; i < this.tree.length; i++){
			toReturn += "[" + this.players[this.tree[i]] + "]";
		}
		return toReturn;
	}
	
}
