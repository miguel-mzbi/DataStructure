package h1;
public class RecString {

	public static int StrLen(String s){
		if(s.equals("")){
			return 0;
		}
		else{
			return 1+ StrLen(s.substring(1));
		}
	}
}
