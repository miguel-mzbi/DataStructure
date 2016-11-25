package disjointSet;

public class DisjointSet {
	int[] p, rank, size;
	int numSets;
	
	public DisjointSet(){
		
	}
	
	public void makeSet(int n){
		this.p = new int[n];
		this.rank = new int[n];
		this.size = new int[n];

		for(int i = 0; i < n; i++){
			this.p[i] = i;
		}
		for(int i = 0; i < n; i++){
			this.rank[i] = 0;
			this.size[i] = 1;
		}
		this.numSets = n;
	}
	
	public int Find(int x){
		if(p[x] != x){
			p[x] = this.Find(p[x]);
		}
		return p[x];
	}
	
	public boolean isSameSet(int i, int j){
		int x = this.Find(i);
		int y = this.Find(j);
		return x == y;
	}
	
	public void Union(int i, int j){
		int x = this.Find(i);
		int y = this.Find(j);
		if(x != y){
			if(this.rank[x] > this.rank[y]){
				this.p[y] = x;
				this.size[x] = this.size[x] + this.size[y];
			}
			else{
				this.p[x] = y;
				this.size[y] = this.size[x] + this.size[y];
			}
			if(this.rank[x] == this.rank[y]){
				this.rank[y] = this.rank[y] + 1;
			}
			this.numSets--;
		}
	}
	
	public int numDisjointSets(){
		return this.numSets;
	}
	
	public int sizeOfSet(int i){
		int x = this.Find(i);
		return this.size[x];
	}
}
