Worst case time complexities for methods in DataBase.java

insertInvoice() - O(n)
O(n) for the insertion the HashTable and O(log n) for the insertion in the AVL.

getInvoice() - O(n)
O(n) for the search in the HashTable and O(log n) for the search in the AVL.

deleteInvoice() - O(n²)
O(n) for the search in the HashTable, O(log n) for the removal in the AVL, 
O(n²) for the removal of the vertex in the graph and O(n²) for the update of the graph.
