package midterm1;

public class E1NMatrix {
	private int[] element;
	private int n;
	private int zero = 0;
	
	/* 	   i=0 1 2  3	
	 *	j=0[[1,0,0 ,5]
	 * 	  1 [2,9,0 ,6]
	 * 	  2 [3,0,10,7]
	 * 	  3 [4,0,0 ,8]]
	 * 
	 * index 0 1 2 3 4 5 6 7 8 9
	 * 		[1,2,3,4,5,6,7,8,9,10]
	 *		   i=0  |  i=n  | i=j
	 *			j	   n+j	 2n+i-1
	 */
	public E1NMatrix(int lenght){
		this.element = new int[3*lenght-2];
		for(int i = 0; i < 3*lenght-2; i++){
			this.element[i] = zero;
		}
		this.n = lenght;	
	}
	public int[] get1DNMatrix(){
		return this.element;
	}
	public void set(int i, int j, int value){
		if(i<1 || i>this.n || j<1 || j>this.n){
			throw new IndexOutOfBoundsException("Coord out of range");
		}
		i--;
		j--;
		if(i==0){
			this.element[j]=value;
		}
		else if(i==this.n-1){
			this.element[this.n+j]=value;
		}
		else if(i==j){
			this.element[2*this.n+j-1]=value;
		}
		else{
			throw new IndexOutOfBoundsException("Coord should be 0");
		}
	}
	public static void main(String[] args){
		E1NMatrix nMatrix = new E1NMatrix(4);
		nMatrix.set(4, 4, 8);
		nMatrix.set(1, 1, 1);
		nMatrix.set(2, 2, 9);
		nMatrix.set(1, 4, 4);
		int[] out = nMatrix.get1DNMatrix();
		for(int i: out){
			System.out.println(i);
		}
	}
}
