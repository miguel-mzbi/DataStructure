Worst case time complexities for methods in DataBase.java

insertInvoice() - O(n)
O(n) for the insertion in the HashTable and O(log n) for the insertion in the AVL.

getInvoice() - O(n)
O(n) for the search in the HashTable and O(log n) for the search in the AVL.

deleteInvoice() - O(n²)
O(n) for the search in the HashTable, O(log n) for the removal in the AVL, 
O(n²) for the removal of the vertex in the graph and O(n²) for the update of the graph.

insertAddress() - O(n)
O(n) for the insertion the HashTable.

getAddress() - O(n)
O(n) for the search in the HashTable.

removeAddress() - O(n)
O(n) for the delete in the HashTable.

insertExpense() - O(n²)
O(n) for the search in the Invoice HashTable, O(log n) for the search in the Invoice AVL,
O(n) for the insertion in the Expense HashTable and O(log n) for the insertion in the Expense AVL,
and O(n²) for the update of the graph.

getExpense() - O(n)
O(n) for the search in the Invoice HashTable, O(log n) for the search in the Invoice AVL,
O(n) for the search in the Expense HashTable and O(log n) for the search in the Expense AVL.

removeExpense() - O(n²)
O(n) for the search in the Invoice HashTable, O(log n) for the search in the Invoice AVL,
O(n) for the search in the Expense HashTable and O(log n) for the removal in the Expense AVL,
and O(n²) for the update of the graph.

selectExpenses() - O(n²)
O(n) for the search in the Invoice HashTable, O(log n) for the search in the Invoice AVL,
O(n) for the search in the Expense HashTable
and O(n²) for the sum of all expenses.

selectPayments() - O(n)
O(n) for the search in the Invoice HashTable
and O(n) for the sum of invoices.

getEarnings() - O(n²)
O(n) for the search in the Invoice HashTable, O(log n) for the search in the Invoice AVL,
O(n) for the search in the Expense HashTable,
O(n²) for the sum of all expenses,
O(n) for the search in the Invoice HashTable,
O(n) for the sum of invoices
and O(1) for the difference.

getDifference() - O(n²)
Twice (1 for each person):
O(n) for the search in the Invoice HashTable, O(log n) for the search in the Invoice AVL,
O(n) for the search in the Expense HashTable
and O(n²) for the sum of all expenses,
and O(1) for the difference.

getIntDifference() - O(n²)
Twice (1 for each person):
O(n) for the search in the Invoice HashTable, O(log n) for the search in the Invoice AVL,
O(n) for the search in the Expense HashTable
and O(n²) for the sum of all expenses,
and O(1) for the difference.

updateEdge() - O(n+E)
Twice (Alternating origin and destination):
O(n) for the search in the vertex table, O(E) for the search of the edge.

updateGraph() - O(n²)
Going through all vertices and all edges.

removeFromGraph() - (n²)
Removing vertex of all edges of all vertices.

getDifferenceGraph() - O(n+E)
O(n) for the search of the vertex and O(E) for the search of the edge.
