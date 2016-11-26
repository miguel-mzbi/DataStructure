package projectDB;

public class Invoice {
	//Node that stores the information needed for an entry in the invoice table.
	//Each invoice has a unique number, a person to which it belongs and a payment.
	//Because each person can have many invoices, all of their invoices are stored in an AVL Tree.
	//Each person has an AVL Tree for all the invoices.
	
	//Because each entry in the expense table (Table 3) belongs to a invoice entry, the table of expenses is added as an attribute.
	//Each table is unique for each invoice number, that's why the object can be instantiated inside the invoice class.
	//The methods to insert and remove from the invoice's expenses where created.
	String name;
	int invoiceNo;
	int payment;
	ExpenseTable expenses;
	
	public Invoice(String name, int invoice, int payment){
		this.name = name;
		this.invoiceNo = invoice;
		this.payment = payment;
		this.expenses = new ExpenseTable();
	}
	
	public String toString(){
		return this.name+" "+this.invoiceNo+" "+this.payment;
	}
	public String toStringWOName(){
		return this.invoiceNo+" - "+this.payment;
	}
	
	public void insertExpense(Expense expense){
		expenses.add(expense.item, expense);
	}
	
	public Expense removeExpense(String item, int expense){
		return expenses.remove(item, expense);
	}
}