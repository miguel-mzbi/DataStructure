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
		
		System.out.println("Recursive 2D");
		int[] h = {7,7,7,7,7,7,5,4,3};
		int y = Peak.OneDRecursive(h);
		System.out.println(y);
		int[] i = {3,94,87,2,2,1};
		y = Peak.OneDRecursive(i);
		System.out.println(y);
		int[] j = {87};
		y = Peak.OneDRecursive(j);
		System.out.println(y);
		int[] k = {};
		y = Peak.OneDRecursive(k);
		System.out.println(y);
	}

}
