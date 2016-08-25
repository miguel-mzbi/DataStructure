package h1;

public class MainH1 {

	public static void main(String[] args) {
		
		System.out.println("Recursive String");
		int x = RecString.StrLen("");
		System.out.println(x);
		x = RecString.StrLen("hello");
		System.out.println(x);
		x = RecString.StrLen("siaodpdahfiuoehfa");
		System.out.println(x);
		
		System.out.println("Recursive 1D");
		int[] h = {7,67,67,77,87,97,97,98,100,90};
		System.out.println(Peak.OneDRecursive(h));
		int[] i = {90,111,98,97,97,87,77,67,67,7};
		System.out.println(Peak.OneDRecursive(i));
		int[] j = {87};
		System.out.println(Peak.OneDRecursive(j));
		int[] k = {};
		System.out.println(Peak.OneDRecursive(k));
		
		System.out.println("Recursive 2D");
		int[][] l = 
			{{1,2,3,4,2},
			{77,32,15,12,6},
			{73,200,15,650,7},
			{12,100,75,400,5},
			{11,50,150,200,1}};
		System.out.println(Peak.TwoDRecursive(l));
	}
}
