package projectDB;

import java.util.ArrayList;
import java.util.Map;

/**
 * <b>class DataBase</b>
 * <p>The purpose of this class is to store a table of addresses, a table of invoices and a table of expenses.
 * It also provides the methods to insert into each table and a number of queries.
 * @author MiguelMontoya - ArturoFornes
 */
public class DataBase {
	protected InvoiceTable invoiceTable;
	protected AddressTable addressTable;
	protected Graph g;
	protected ArrayList<String> names;
	
	/**
	 * <b>DataBase</b>
	 * <br><i>public DataBase()</i>
	 * <p>Constructor for the class DataBase.
	 */
	public DataBase(){
		invoiceTable = new InvoiceTable();
		addressTable = new AddressTable();
		g = new Graph();
		names = new ArrayList<String>();
	}
	
	/**
	 * <b>insertInvoice</b>
	 * <br><i>public void insertInvoice(String name, int noInvoice, int payment)</i>
	 * <p>Insert new Invoice to the table.
	 * @param name - (String) Name of the person who made the invoice.
	 * @param noInvoice - (int) Invoice number.
	 * @param payment - (int) Quantity of the payment.
	 */
	public void insertInvoice(String name, int noInvoice, int payment){
		if(!names.contains(name)){
			names.add(name);
		}
		invoiceTable.add(name, new Invoice(name,noInvoice,payment));
	}
	
	/**
	 * <b>getInvoice</b>
	 * <br><i>public Invoice getInvoice(String name, int noInvoice)</i>
	 * <p>Search for a specific invoice.
	 * @param name - (String) Search for Invoice with the specified name of the person who made the invoice.
	 * @param noInvoice - (int) Search for Invoice with the specified invoice number.
	 * @return Invoice with the specified parameters.
	 */
	public Invoice getInvoice(String name, int noInvoice){
		return invoiceTable.getInvoice(name, noInvoice);
	}
	
	/**
	 * <b>deleteInvoice</b>
	 * <br><i>public Invoice deleteInvoice(String name, int noInvoice)</i>
	 * <p>Removes Invoice of the table
	 * <br>Because an invoice contains expenses, the vertex of that name is removed from the graph, and then the graph is updated.
	 * @param name - (String) Removes the Invoice with the specified name of the person who made the invoice.
	 * @param noInvoice - (int) Removes the Invoice with the specified invoice number.
	 * @return Removes the Invoice with the specified parameters.
	 */
	public Invoice deleteInvoice(String name, int noInvoice){
		Invoice toReturn = invoiceTable.remove(name,noInvoice);
		this.removeFromGraph(name);
		this.updateGraph(name);

		return toReturn;
	}
	
	/**
	 * <b>insertAddress</b>
	 * <br><i>public void insertAddress(String name, String address)</i>
	 * <p>Insert new Address to the table.
	 * @param name - (String) Name of the person.
	 * @param address - (String) Address of the person.
	 */
	public void insertAddress(String name, String address){
		addressTable.add(name, address);
	}
	
	/**
	 * <b>getAddress</b>
	 * <br><i>public String getAddress(String name)</i>
	 * <p>Search for an specific address given a person's name.
	 * @param name - (String) Name of the person.
	 * @return String with the address of the requested person.
	 */
	public String getAddress(String name){
		return addressTable.getValue(name);
	}
	
	/**
	 * <b>removeInvoice</b>
	 * <br><i>public String removeAddress</i>
	 * <p>Removes an address of the table
	 * @param name - (String) Name of the person.
	 * @return String with the requested removed address.
	 */
	public String removeAddress(String name){
		return addressTable.remove(name);
	}
	
	/**
	 * <b>insertExpense</b>
	 * <br><i>public void insertExpense(String name, int noInvoice, String item, int expense)</i>
	 * <p>Insert new Expense to the table
	 * <br>Because an expense is added, the person's vertex of the graph is updated.
	 * @param name - (String) Name of the person.
	 * @param noInvoice - (int) Invoice Number
	 * @param item - (String) Item's name
	 * @param expense - (int) Expense of the item
	 */
	public void insertExpense(String name, int noInvoice, String item, int expense){
		invoiceTable.getInvoice(name,noInvoice).expenses.add(item, new Expense(item,expense));
		this.updateGraph(name);
	}
	
	/**
	 * <b>getExpense</b>
	 * <br><i>public Expense getExpense(String name, int noInvoice, String item, int expense)</i>
	 * <p>Search for Expense in table
	 * @param name - (String) Name of the person.
	 * @param noInvoice - (int) Invoice Number
	 * @param item - (String) Item's name
	 * @param expense - (int) Expense of the item
	 * @return Searched Expense
	 */
	public Expense getExpense(String name, int noInvoice, String item, int expense){
		return invoiceTable.getInvoice(name, noInvoice).expenses.getExpense(item, expense);
	}
	
	/**
	 * <b>removeExpense</b>
	 * <br><i>public Expense removeExpense(String name, int noInvoice, String item, int expense)</i>
	 * <p>Removes a specific Expense from the table.
	 * <br>Because an expense is removed, the person's vertex of the graph is updated.
	 * @param name - (String) Name of the person.
	 * @param noInvoice - (int) Invoice Number
	 * @param item - (String) Item's name
	 * @param expense - (int) Expense of the item
	 * @return Removed Expense
	 */
	public Expense removeExpense(String name, int noInvoice, String item, int expense){
		Expense toReturn = invoiceTable.getInvoice(name, noInvoice).removeExpense(item, expense);
		this.updateGraph(name);
		return toReturn;
	}
	
	/**
	 * <b>selectExpenses</b>
	 * <br><i>public void selectExpenses(String name)</i>
	 * <p>Prints all the expenses a person has made. First iterating through all of the person's invoices.
	 * @param name - (String) The person's name.
	 */
	public void selectExpenses(String name){
		AVLTree<Integer> invoices = this.invoiceTable.table[this.invoiceTable.hash(name)];
		System.out.print("Expenses of: " + name + "\nInvoice Expense");
		System.out.println(invoices.inOrderForExpenses());
	}
	
	/**
	 * <b>selectPayments</b>
	 * <br><i>public void selectPayments(String name)</i>
	 * <p>Prints all the Payments a person has made.
	 * @param name - (String) The person's name.
	 */
	public void selectPayments(String name){
		AVLTree<Integer> invoices = this.invoiceTable.table[this.invoiceTable.hash(name)];
		System.out.print("Payments of: " + name + "\nInvoice Payment");
		System.out.println(invoices.inOrderForPayments());
	}
	
	/**
	 * <b>getEarnings</b>
	 * <br><i>public void getEarnings(String name)</i>
	 * <p>Gets the payments minus the expenses
	 * @param name - (String) The person's name.
	 */
	public void getEarnings(String name){
		AVLTree<Integer> invoices = this.invoiceTable.table[this.invoiceTable.hash(name)];
		System.out.println("Earnings of: " + name);
		System.out.println(invoices.getEarnings());
	}
	
	/**
	 * <b>getDifference</b>
	 * <br><i>public void getDifference(String nameA, String nameB)</i>
	 * <p>Prints the difference of expenses of two people
	 * @param nameA - (String) The personA's name.
	 * @param nameB - (String) The personB's name.
	 */
	public void getDifference(String nameA, String nameB){
		AVLTree<Integer> invoicesA = this.invoiceTable.table[this.invoiceTable.hash(nameA)];
		AVLTree<Integer> invoicesB = this.invoiceTable.table[this.invoiceTable.hash(nameB)];
		int expA = invoicesA.getTotalExpenses();
		int expB = invoicesB.getTotalExpenses();
		int output = Math.abs(expA-expB);
		System.out.println("Expenses of " + nameA +": " + expA + 
				"\nExpenses of " + nameB +": " + expB +
				"\nSimilarity (|Subtraction|): " + output);
	}
	
	/**
	 * <b>getIntDifference</b>
	 * <br><i>public int getIntDifference(String nameA, String nameB)</i>
	 * <p>Gets the difference of expenses of two people
	 * @param nameA - (String) The personA's name.
	 * @param nameB - (String) The personB's name.
	 * @return Integer, the difference
	 */
	public int getIntDifference(String nameA, String nameB){
		AVLTree<Integer> invoicesA = this.invoiceTable.table[this.invoiceTable.hash(nameA)];
		AVLTree<Integer> invoicesB = this.invoiceTable.table[this.invoiceTable.hash(nameB)];
		int expA = invoicesA.getTotalExpenses();
		int expB = invoicesB.getTotalExpenses();
		int output = Math.abs(expA-expB);
		return output;
	}
	
	/**
	 * <b>updateEdge</b>
	 * <br><i>public void updateEdge(String origin, String destination)</i>
	 * <p>Updates the edges between two vertex.
	 * @param origin - (String) Name of the person A.
	 * @param destination - (String) Name of the person B.
	 */
	public void updateEdge(String origin, String destination){
		int cost = this.getIntDifference(origin, destination);
		g.changeEdge(origin, destination, cost);
		g.changeEdge(destination, origin, cost);
	}
	
	/**
	 * <b>updateGraph</b>
	 * <br><i>public void updateGraph(String origin)</i>
	 * <p>Updates the value of the edges that are connected to a defined vertex
	 * @param origin - (String) Name of the person whose expense values changed
	 */
	public void updateGraph(String origin){
		Vertex s = g.getVertex(origin);
		for(Map.Entry<String, Vertex> u: g.vertexMap.entrySet()){
			if(!u.getValue().equals(s))
				this.updateEdge(origin, u.getValue().name);
		}
	}
	
	/**
	 * <b>removeFromGraph</b>
	 * <br><i>public void removeFromGraph(String name)</i>
	 * <p>Removes a vertex from the graph
	 * @param name - (String) Name of the person to remove
	 */
	public void removeFromGraph(String name){
		if(!this.invoiceTable.contains(name)){
			g.removeVertex(name);
			for(int i = 0; i < names.size(); i++){
				if(names.get(i).equals(name))
					names.remove(i);
			}
		}
	}
	
	/**
	 * <b>getDifferenceGraph</b>
	 * <br><i>public int getDifferenceGraph(String nameA, String nameB)</i>
	 * <p>Gets the absolute difference of expenses of two people, through graphs
	 * @param nameA - (String) The personA's name.
	 * @param nameB - (String) The personB's name.
	 * @return Integer, difference.
	 */
	public int getDifferenceGraph(String nameA, String nameB){
		return (int) g.getCost(nameA, nameB);
	}
	
	public static void main(String[] args){
		DataBase db = new DataBase();
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
		db.insertInvoice("Miguel", 70, 100);
		db.insertExpense("Miguel", 68, "PC ", 100);
		db.insertExpense("Miguel", 70, "PC ", 98);
		
		db.selectExpenses("Arturo");
		System.out.println();
		db.selectPayments("Arturo");
		System.out.println();
		db.getEarnings("Arturo");
		System.out.println();
		db.getEarnings("Miguel");
		System.out.println();
		db.getDifference("Arturo", "Miguel");
		
		System.out.println("Get difference through Graph: "+db.getDifferenceGraph("Miguel", "Arturo"));
		
		System.out.println("Removed one invoice: "+db.deleteInvoice("Miguel", 70));
		System.out.println("Removed another invoice: "+db.deleteInvoice("Miguel", 68));
		
		System.out.println(db.getDifferenceGraph("Miguel", "Arturo"));
		db.getDifference("Arturo", "Miguel");

	}
}