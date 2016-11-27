package projectDB;

/**
 * <b>class Expense</b>
 * <p>The purpose of this class is to store the values of each expense.
 * Each expense is stored in the class AVLTreeMod.
 * <br>Because every AVLTree is stored in a HashTable, for only one invoice, it's not necesary to store the invoice value.
 * @author MiguelMontoya - ArturoFornes
 */
public class Expense {
	String item;
	int expense;
	
	/**
	 * <b>Expense</b>
	 * <br><i>public Expense(String item, int expense)</i>
	 * <p>Constructor for the class Expense.
	 * @param item - (String) Item, Item name of the product 
	 * @param expense - (Integer) Expense value of the item
	 */
	public Expense(String item, int expense){
		this.item = item;
		this.expense = expense;
	}
	
	/**
	 * <b>toString</b>
	 * <br><i>public String toString()</i>
	 * <p>Provides String with the item name (Item) and the expense value (Expense).
	 * @return String as: [Item - Expense]
	 */
	public String toString(){
		return this.item+" - "+this.expense;
	}
}