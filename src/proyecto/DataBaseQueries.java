package proyecto;

public class DataBaseQueries extends DataBase{
	//Queries for insertion and deletion are established in the DataBase class
	public DataBaseQueries(){
		super();
	}
	public void selectExpenses(String name){
		AVLTree<Integer> invoices = this.invoiceTable.table[this.invoiceTable.hash(name)];
		System.out.print("Expenses of: " + name + "\nInvoice Expense");
		System.out.println(invoices.inOrderForExpenses());
	}
	
	public void selectPayments(String name){
		AVLTree<Integer> invoices = this.invoiceTable.table[this.invoiceTable.hash(name)];
		System.out.print("Payments of: " + name + "\nInvoice Payment");
		System.out.println(invoices.inOrderForPayments());
	}
	
	public void getEarnings(String name){
		AVLTree<Integer> invoices = this.invoiceTable.table[this.invoiceTable.hash(name)];
		System.out.println("Earnings of: " + name);
		System.out.println(invoices.getEarnings());
	}
	
	public void getDifference(String nameA, String nameB){
		AVLTree<Integer> invoicesA = this.invoiceTable.table[this.invoiceTable.hash(nameA)];
		AVLTree<Integer> invoicesB = this.invoiceTable.table[this.invoiceTable.hash(nameB)];
		int expA = invoicesA.getTotalExpenses();
		int expB = invoicesB.getTotalExpenses();
		int output = Math.abs(expA-expB);
		System.out.println("Expenses of " + nameA +": " + expA + 
				"\nExpenses of " + nameB +": " + expB +
				"\nSimilarity (|Substraction|): " + output);
	}
	
	public static void main(String[] args){
		DataBaseQueries db = new DataBaseQueries();
		db.insertAddress("Arturo", "Royal Country");
		db.insertInvoice("Arturo", 11, 100);
		db.insertExpense("Arturo", 11, "Beer", 10);
		db.insertExpense("Arturo", 11, "Beer", 10);
		db.insertExpense("Arturo", 11, "Beer", 15);
		db.insertExpense("Arturo", 11, "Bread", 30);
		db.insertInvoice("Arturo", 16, 600);
		db.insertExpense("Arturo", 16, "Beer", 11);
		
		db.insertAddress("Miguel", "Arauca 2");
		db.insertInvoice("Miguel", 68, 400);
		db.insertExpense("Miguel", 68, "PC ", 100);
		
		db.selectExpenses("Arturo");
		System.out.println();
		db.selectPayments("Arturo");
		System.out.println();
		db.getEarnings("Arturo");
		System.out.println();
		db.getDifference("Arturo", "Miguel");
		

	}
}
