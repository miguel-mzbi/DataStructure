package h1;

public class Peak {

	public static int OneDRecursive(int[] a){
		int n=a.length;
		return OneDRecursive(a, n);
	}
	public static int getMidIndex(int n){
		int i = (n%2==0)?(n/2):(n+1)/2;
		return i;
	}
	public static int OneDRecursive(int[] a, int n){
		return OneDRecursive(a, n, getMidIndex(n));
	}
	public static int OneDRecursive(int[] a, int n, int i){
		if(i == n-1){
			i=1;
		}
		if(i == 0){
			i = n-2;
		}
		if(n==1){
			return a[0];
		}
		else if(n==0){
			return 0;
		}
		else if(a[i-1]<=a[i] && a[i]<=a[i+1]){
//			while(a[i]==a[i-1] && a[i]==a[i+1]){
//				if(i == n-1){
//					return OneDRecursive(a, n, 1);
//				}
//				i++;
//			}
			if(a[i+1]== getMidIndex(n)){
				return a[i];
			}
			return OneDRecursive(a, n, i+1);
		}
		else if(a[i-1]>=a[i] && a[i]>=a[i+1]){
//			while(a[i]==a[i-1] && a[i]==a[i+1]){
//				if(i == 0){
//					return OneDRecursive(a, n, n-2);
//				}
//				i--;
//			}
			return OneDRecursive(a, n, i-1);
		}
		else{
		//else if(a[i-1]<=a[i] && a[i]>=a[i+1]){
			return a[i];
		}
	}
}
