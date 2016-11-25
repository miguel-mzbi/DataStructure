package projectDB;

public class Expense {
	//Node for the entries on the expense's AVL tree.
	//Each AVL Tree contains only nodes with the same item name
	String item;
	int expense;
	
	public Expense(String item, int expense){
		this.item = item;
		this.expense = expense;
	}
	
	public String toString(){
		return this.item+" "+this.expense;
	}
}
