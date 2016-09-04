package midterm1;

public class E2SMatrix {
	private int[] element;
	private int n;
	private int zero = 0;
	
	/* 	   i= 1  2  3  4  5	
	 *	j=1[[ 1, 2, 3, 4, 5]
	 * 	  2 [16, 0, 0, 0, 0]
	 * 	  3 [ 6, 7, 8, 9,10]
	 * 	  4 [ 0, 0, 0, 0,17]
	 * 	  5 [11,12,13,14,15]]
	 * 
	 * index 0 1 2 3 4 5 6 7 8  9 10 11 12 13 14 15 16
	 * 		[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17]
	 *		    j=1   | j=(n+1)/2|      j=n     |i=1|i=n
	 *			 i		   n+i		    2n+i	3n+j-1 3n+j-2	
	 */
	public E2SMatrix(int lenght){
		this.element = new int[4*lenght-3];
		for(int i = 0; i < 4*lenght-3; i++){
			this.element[i] = i+1;
		}
		this.n = lenght;
	}
	public int get(int i, int j){
		if(i<1 || i>this.n || j<1 || j>this.n){
			throw new IndexOutOfBoundsException("Coord out of range");
		}
		if(j==1){
			return this.element[i-1];
		}
		else if(j==(this.n+1)/2){
			return this.element[this.n+i-1];
		}
		else if(j==this.n){
			return this.element[2*this.n+i-1];
		}
		else if(i==1 && j<(this.n+1)/2){
			return this.element[3*this.n+j-2];
		}
		else if(i==this.n && j>(this.n+1)/2){
			return this.element[3*this.n+j-3];
		}
		else{
			return this.zero;
		}
	}
	public static void main(String[] args){
		E2SMatrix sMatrix = new E2SMatrix(5);
		System.out.println(sMatrix.get(1, 2));
		System.out.println(sMatrix.get(1, 4));
		System.out.println(sMatrix.get(5, 2));
		System.out.println(sMatrix.get(5, 4));
		System.out.println(sMatrix.get(3, 1));
		System.out.println(sMatrix.get(3, 3));
		System.out.println(sMatrix.get(3, 5));
		System.out.println(sMatrix.get(2, 4));
	}
}
