package abb;

public class TournamentMinMain {

	public static void main(String[] args) {
		TournamentMin TM = new TournamentMin();
		int[] p = {0, 4, 6, 5};//, 9, 8, 2, 3, 7, 1, 82392382, 4, -10, -40};
		TM.initialize(p);
		System.out.println(TM.toString());
		System.out.println(TM.getWinner());
		TM.change(1, 9);
		System.out.println(TM.toString());
		System.out.println(TM.getWinner());
		
		int [] organized = TM.organizeArray(p);
		String out = "";
		for(int i = 1; i < p.length; i++){
			out += "[" + p[i] + "]";
		}
		out+= "\n";
		for(int i = 1; i < organized.length; i++){
			out += "[" + organized[i] + "]";
		}
		System.out.println(out);
	}
}
