package projectDB;

/**
 * <b>class Invoice</b>
 * <p>The purpose of this class is to store the values of each invoice.
 * <br>Each invoice is stored in the class AVLTree, and each AVLTree contains only the invoices of a single person.
 * <br>Note: Because each entry in the expense table (Table 3) belongs to a invoice entry, the table of expenses is added as an attribute.
 * Each table is unique for each invoice number, that's why the object can be instantiated inside the invoice class.
 * The methods to insert and remove from the invoice's expenses where created.
 * @author MiguelMontoya - ArturoFornes
 */
public class Invoice {

	String name;
	int invoiceNo;
	int payment;
	ExpenseTable expenses;
	
	/**
	 * <b>Invoice</b>
	 * <br><i>public Invoice(String name, int invoice, int payment)</i>
	 * <p>Constructor for the class Invoice.
	 * <p>The table of expenses for each invoice is created.
	 * @param name - (String) Name of the person, the invoice belongs to.
	 * @param invoice - (int) Invoice number
	 * @param payment - (int) Payment value of the invoice
	 */
	public Invoice(String name, int invoice, int payment){
		this.name = name;
		this.invoiceNo = invoice;
		this.payment = payment;
		this.expenses = new ExpenseTable();
	}
	
	/**
	 * <b>toString</b>
	 * <br><i>public String toString()</i>
	 * <p>Provides String with the person's name (name), invoice number (invoiceNo) and the payment value (payment).
	 * @return String as: [name - invoiceNo - payment]
	 */
	public String toString(){
		return this.name+" - "+this.invoiceNo+" - "+this.payment;
	}
	
	/**
	 * <b>toStringWOName</b>
	 * <br><i>public String toStringWOName()</i>
	 * <p>Provides String with the person's name (name) and the payment value (payment).
	 * @return String as: [name - payment]
	 */
	public String toStringWOName(){
		return this.invoiceNo+" - "+this.payment;
	}
	
	/**
	 * <b>insertExpense</b>
	 * <br><i>public void insertExpense(Expense expense)</i>
	 * <p>Inserts new expense to the expense table of the invoice.
	 * @param expense - (Expense) New expense to add
	 */
	public void insertExpense(Expense expense){
		expenses.add(expense.item, expense);
	}
	
	/**
	 * <b>removeExpense</b>
	 * <br><i>public Expense removeExpense(String item, int expense)</i>
	 * <p>Removes expense of the expense table of the invoice.
	 * @param item - (String) Item name of the expense to remove.
	 * @param expense - (int) Expense value of the item.
	 * @return Expense removed of the expense table of the invoice.
	 */
	public Expense removeExpense(String item, int expense){
		return expenses.remove(item, expense);
	}
}