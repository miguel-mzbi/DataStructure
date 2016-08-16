
public class Peak {

	public static int OneDRecursive(int[] a){
		int n=a.length;
		return OneDRecursive(a, n);
	}
	public static int OneDRecursive(int[] a, int n){
		int i = (n%2==0)?(n/2):(n+1)/2;
		return OneDRecursive(a, n, i);
	}
	public static int OneDRecursive(int[] a, int n, int i){
		if(n==1){
			return a[0];
		}
		else if(n==0){
			return 0;
		}
		else if(a[i-1]<=a[i] && a[i]<=a[i+1]){
			return OneDRecursive(a, n, i+1);
		}
		else if(a[i-1]>=a[i] && a[i]>=a[i+1]){
			return OneDRecursive(a, n, i-1);
		}
		else{
		//else if(a[i-1]<=a[i] && a[i]>=a[i+1]){
			return a[i];
		}
	}
}
