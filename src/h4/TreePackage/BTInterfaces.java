package h4.TreePackage;

public interface BTInterfaces {
	
	public interface BTNode {
		public int gKey();
		public BTNode gLeft();
		public BTNode gRight();
		public void sKey(int AValue);
		public void sLeft(BTNode AValue);
		public void sRight(BTNode AValue);
	}
	
	public interface BTree {
		public boolean IsEmpty();
		public BTNode gRoot();
		public int Count();
		public int Size(BTNode ATree);
		public int Height(BTNode ATree);
		//private int Size();
	}
}
