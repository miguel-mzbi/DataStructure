package h1;

public class Peak {

	public static int OneDRecursive(int[] a){	
		return OneDRecursive(a, a.length, 0, a.length); //Start recursion
	}
	public static int OneDRecursive(int[] a, int l, int leftI, int rightI){
		int i =((rightI+leftI)%2==0)?((rightI+leftI)/2):(((rightI+leftI)+1)/2);
		if(l==0){ //Return 0 if array is empty
			return -1;
		}
		else if(l==1){ //Return only value if array is of length one
			return a[0];
		}
		else if(i==l-1){ //Return right limit if already on right limit
			return a[l-1];
		}
		else if(i==0){ //Return left limit if already on left limit
			return a[0];
		}
		else if(a[i-1]<=a[i] && a[i]<=a[i+1]){ //Move to the right
			return OneDRecursive(a, l, i+1, rightI);
		}
		else if(a[i-1]>=a[i] && a[i]>=a[i+1]){ //Move to the left
			return OneDRecursive(a, l, leftI, i-1);
		}
		else{ //Return peak
			return a[i];
		}
	}

	public static int TwoDRecursive(int[][] a){
		int cRow = 0; //First row to analyze
		int nRows = a.length; //Numbers of rows to analyze
		int fin = 0; //Largest number found
		return TwoDRecursive(a, cRow, nRows, fin);//Method to compare peaks
	}
	public static int TwoDRecursive(int[][] a, int cRow, int nRows, int fin){
		int check=OneDRecursive(TwoDRecursive(a, cRow, new int [a[cRow].length], 0)); //Obtain biggest peak per column,
		if(check>fin){																  //first making the row from the column
			fin = check;//Make new biggest peak, if column peak is bigger
		}
		if(cRow < nRows-1){
			return TwoDRecursive(a,cRow+1, nRows, fin);//Start analysis of next column if available
		}
		return fin; //Return biggest peak
		
	}
	public static int[] TwoDRecursive(int[][] a, int cRow, int[] b, int cCol){
		if(cCol==b.length-1){ //If on the last column
			b[cCol]=a[cRow][cCol]; //Add value of last index to the list to be analyzed
		}
		else{ //Only of not on last column
			b[cCol]=a[cRow][cCol]; //Add index value to the list
			TwoDRecursive(a,cRow,b,cCol+1); //Obtain value of next index
		}
		return b; //Return list to analyze for peaks with the OneD method
	}
}
