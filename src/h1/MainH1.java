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
			{77,32,15,12,1},
			{2,8,15,65,3},
			{1,100,5,2,1},
			{30,50,150,200,23}};
		System.out.println(Peak.TwoDRecursive(l));
	}
}
