public class RecString {

	public static int StrLen(String s){
		if(s.equals("")||s==null){
			return 0;
		}
		else{
			return 1+ StrLen(s.substring(1));
		}
	}
}
