package midterm1;

public class NMatrixE1 {
	public int[] element;
	public static int n;
	
	public NMatrixE1(int no){
		element = new int[3*no-2];
		for(int i = 0; i < 3*no-2; i++){
			element[i] = i;
		}
		n = no;	
	}
	public void set(int i, int j, int value){
		i--;
		j--;
		
		
	}
}
