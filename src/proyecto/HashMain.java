package proyecto;

public class HashMain {
	public static void main(String[] args){
		AddressTable hash = 
				new AddressTable(17); //17

		hash.add("Arturo", "Royal Country");
		
		hash.output();
		
		hash.add("Arturo", "Paseo Royal Country");
		
		hash.output();
	}
}
