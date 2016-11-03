package abb;

public class TournamentMinMain {

	public static void main(String[] args) {
		TournamentMin TM = new TournamentMin();
		int[] p = {0, 4, 6, 5, 9, 8, 2, 3, 7, 1, 82392382, 4, -10};
		TM.initialize(p);
		System.out.println(TM.toString());
		System.out.println(TM.getWinner());
	}

}
