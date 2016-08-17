package lab.w1;

public class Lab1 {
//	Enumeración ascendiente
	public static void enAscendente(int n){
		if(n>=0){
			enAscendente(n-1);
			System.out.println(n);
		}
	}
	
//	Enumeración descendiente
	public static void enDescendente(int n){
		if(n>=0){
			System.out.println(n);
			enDescendente(n-2);
		}
	}
	
//	Al reves
	public static int alReves(int n){
		String s = Integer.toString(n);
		if(s.length()==1){
			return Integer.parseInt(s.substring(0,1)); //Caso base
		}
		else{
			return Integer.parseInt(alReves(Integer.parseInt(s.substring(1))) + s.substring(0,1)); //Caso recursivo
		}
		
	}
	
//	Cuenta Cuantas
	public static int cuentaCuantas(String s, char c){
		int n;
		if(s.substring(0, 1).equals(Character.toString(c))){
			n=1;
		}
		else{
			n=0;
		}
		if(s.length()==1){
			return n; //Caso base
		}
		else{
			return n + cuentaCuantas(s.substring(1), c); //Caso recursivo
		}
	}
	
//	Suma Harmonica
	public static double sumaHarmonica(double n){
		if(n==1){
			return 1; //Caso base
		}
		else{
			return 1/n +sumaHarmonica(n-1); //Caso recursivo
		}
	}
}
