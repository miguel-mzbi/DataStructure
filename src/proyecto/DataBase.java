package proyecto;

public class DataBase {
	public InvoiceTable invoiceTable;
	public AddressTable addressTable;
	
	public DataBase(){
		invoiceTable = new InvoiceTable();
		addressTable = new AddressTable();
	}
	
	public void insertInvoice(String name, int noInvoice, int payment){
		invoiceTable.add(name, new Invoice(name,noInvoice,payment));
	}
	
	public Invoice getInvoice(String name, int noInvoice){
		return invoiceTable.getInvoice(name, noInvoice);
	}
	
	public Invoice deleteInvoice(String name, int noInvoice){
		return invoiceTable.remove(name,noInvoice);
	}
	
	public void insertAddress(String name, String address){
		addressTable.add(name, address);
	}
	
	public String getAddress(String name){
		return addressTable.getValue(name);
	}
	
	public String removeAddress(String name){
		return addressTable.remove(name);
	}
	
	public void insertExpense(String name, int noInvoice, String item, int expense){
		invoiceTable.getInvoice(name,noInvoice).expenses.add(item, new Expense(item,expense));
	}
	
	public Expense getExpense(String name, int noInvoice, String item, int expense){
		return invoiceTable.getInvoice(name, noInvoice).expenses.getExpense(item, expense);
	}
	
	public Expense removeExpense(String name, int noInvoice, String item, int expense){
		return invoiceTable.getInvoice(name, noInvoice).removeExpense(item, expense);
	}
	
	public static void main(String[] args){
		DataBase db = new DataBase();
		db.insertAddress("Arturo", "Royal Country");
		System.out.println(db.getAddress("Arturo"));
		System.out.println(db.removeAddress("Arturo"));
		db.insertInvoice("Arturo", 11, 100);
		System.out.println(db.getInvoice("Arturo", 11));
		db.insertExpense("Arturo", 11, "Beer", 10);
		System.out.println(db.removeExpense("Arturo", 11, "Beer", 10));
	}
}
