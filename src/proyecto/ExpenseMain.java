package proyecto;

public class ExpenseMain {
	public static void main(String[] args){
		ExpenseTable table = new ExpenseTable(17);
		Expense beer = new Expense("Beer",10);
		Expense beer2 = new Expense("Beer",11);
		Expense beer3 = new Expense("Beer",10);
		
		table.insertExpense(beer3);
		table.insertExpense(beer2);
		table.insertExpense(beer);
		table.output();
		System.out.println();
		System.out.println(table.getExpense("Beer", 11));
		System.out.println();
		System.out.println(table.remove("Beer", 10));
		System.out.println();
		table.output();
		System.out.println();
		System.out.println(table.remove("Beer", 10));
		System.out.println();
		table.output();
		System.out.println(table.remove("Beer", 11));
		System.out.println();
		table.output();
	}
}
