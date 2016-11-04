package h4;

import java.util.Iterator;

public class PatientDataBaseMain {
	public static void main(String[] args){
		PatientDataBase DB = new PatientDataBase();
		DB.add("Miguel", "7-7-77", "El Diablo!", "El Diablo!");
		
		DB.add("Poncho", "2-2-16", "Hemorroides", "Crema Cannabis");
		DB.add("Poncho", "2-3-16", "Hemorroides... Otra vez", "Crema Cannabis *2");
		DB.add("Poncho", "2-6-16", "Chequeo", "No más drogas");

		
		DB.add("Arturo", "2-1-16", "Hemorroides... Paciente 0", "Crema Cannabis");
		DB.add("Arturo", "2-4-16", "Chequeo", "Nada");
		
		System.out.println("Razón Arturo 2-1-16: "+DB.getReasonVisit("Arturo", "2-1-16"));
		System.out.println("Tratamiento Arturo 2-4-16: "+DB.getTreatmentVisit("Arturo", "2-4-16"));
		System.out.println("Fechas Poncho: "+ DB.getDatePerName("Poncho"));
		System.out.println();
		
		Iterator<String> iterator = DB.getKeyIterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		System.out.println();
		
		Iterator<String> iterator2 = DB.getValueIterator();
		while(iterator2.hasNext()){
			System.out.println(iterator2.next());
		}
	}
}
