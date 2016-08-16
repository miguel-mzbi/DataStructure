
public class RecString {

	public static int StrLen(String s){
		if(s.length()==1){
			return 1;
		}
		else if(s.length() == 0){
			return 0;
		}
		else{
			return 1 + StrLen(s.substring(0, s.length()-1));
		}
	}
}
