package abb;

public interface WinnerTree {
	public int getWinner();
	public void initialize(int[] players);
	public void play(int parent, int left, int right);
	public void replay(int p);
	public String toString();
}
