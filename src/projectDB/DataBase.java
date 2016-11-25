package projectDB;

import java.util.ArrayList;
import java.util.Map;

public class DataBase {
	protected InvoiceTable invoiceTable;
	protected AddressTable addressTable;
	protected Graph g;
	protected ArrayList<String> names;
	
	public DataBase(){
		invoiceTable = new InvoiceTable();
		addressTable = new AddressTable();
		g = new Graph();
		names = new ArrayList<String>();
	}
	
	public void insertInvoice(String name, int noInvoice, int payment){
		if(!names.contains(name)){
			names.add(name);
		}
		invoiceTable.add(name, new Invoice(name,noInvoice,payment));
	}
	
	public Invoice getInvoice(String name, int noInvoice){
		return invoiceTable.getInvoice(name, noInvoice);
	}
	
	public Invoice deleteInvoice(String name, int noInvoice){
		Invoice toReturn = invoiceTable.remove(name,noInvoice);
		this.updateGraph(name);
		this.removeFromGraph(name);
		return toReturn;
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
		this.updateGraph(name);
	}
	
	public Expense getExpense(String name, int noInvoice, String item, int expense){
		return invoiceTable.getInvoice(name, noInvoice).expenses.getExpense(item, expense);
	}
	
	public Expense removeExpense(String name, int noInvoice, String item, int expense){
		Expense toReturn = invoiceTable.getInvoice(name, noInvoice).removeExpense(item, expense);
		this.updateGraph(name);
		return toReturn;
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
				"\nSimilarity (|Subtraction|): " + output);
	}
	
	public int getIntDifference(String nameA, String nameB){
		AVLTree<Integer> invoicesA = this.invoiceTable.table[this.invoiceTable.hash(nameA)];
		AVLTree<Integer> invoicesB = this.invoiceTable.table[this.invoiceTable.hash(nameB)];
		int expA = invoicesA.getTotalExpenses();
		int expB = invoicesB.getTotalExpenses();
		int output = Math.abs(expA-expB);
		return output;
	}
	
	public void updateEdge(String origin, String destination){
		int cost = this.getIntDifference(origin, destination);
		g.changeEdge(origin, destination, cost);
		g.changeEdge(destination, origin, cost);
	}
	
	public void updateGraph(String origin){
		Vertex s = g.getVertex(origin);
		for(Map.Entry<String, Vertex> u: g.vertexMap.entrySet()){
			if(!u.getValue().equals(s))
				this.updateEdge(origin, u.getValue().name);
		}
	}
	
	public void removeFromGraph(String name){
		if(!this.invoiceTable.contains(name)){
			g.removeVertex(name);
			for(int i = 0; i < names.size(); i++){
				if(names.get(i).equals(name))
					names.remove(i);
			}
		}
	}
	
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
	}
}