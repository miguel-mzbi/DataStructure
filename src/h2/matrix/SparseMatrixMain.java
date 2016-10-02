package h2.matrix;

public class SparseMatrixMain {
	public static void main(String[] args) {
		Integer[][] intArray = {
			{1,2,3,4,5,6,7,8,9},
			{1,2,0,4,5,0,7,8,0},
			{0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0},
			{1,2,0,0,0,0,0,0,0},
			{1,2,3,0,0,0,0,0,0},
			{0,0,3,4,0,0,0,8,9}
			};
		SparseMatrix<Integer> SM = new SparseMatrix<Integer>(intArray);
		
		for(int j = 1; j <= 7; j++){
			for(int i = 1; i <= 9; i++){
				System.out.print(SM.get(j, i));
			}
			System.out.println();
		}
		System.out.println();

		System.out.println(SM.remove(1, 1));
		System.out.println(SM.remove(1, 2));
		System.out.println(SM.remove(1, 3));
		System.out.println(SM.output());
		
		for(int j = 1; j <= 7; j++){
			for(int i = 1; i <= 9; i++){
				SM.add(j, i, 5);
			}
		}
		System.out.println(SM.output());
		
		for(int j = 1; j <= 7; j++){
			for(int i = 1; i <= 9; i++){
				SM.remove(j, i);
			}
		}
		System.out.println(SM.output());
		
		for(int j = 1; j <= 7; j++){
			for(int i = 1; i <= 9; i++){
				SM.add(j, i, 9);
			}
		}

		for(int j = 1; j <= 7; j++){
			SM.remove(j, j);
		}
		SM.remove(1, 8);
		SM.remove(2, 9);
		System.out.println(SM.output());
		
		try{
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
