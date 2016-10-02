package h2.matrix;

import java.util.NoSuchElementException;

public class SparseMatrix<Item> {
	private int columns, rows;
	public SMNode<Item>[] startNodes;
	private Integer zero = 0;
	
	@SuppressWarnings("unchecked")
	public SparseMatrix(Item[][] dArray){
		this.rows = dArray.length;
		this.columns = dArray[0].length;
		this.startNodes = new SMNode[this.rows];
		
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				if(dArray[i][j] != this.zero){
					if(this.startNodes[i] == null){
						this.startNodes[i] = new SMNode<Item>(dArray[i][j], i+1, j+1, null);
					}
					else{
						SMNode<Item> temp = this.startNodes[i];
						while(temp.next != null){
							temp = temp.next;
						}
						temp.next = new SMNode<Item>(dArray[i][j], i+1, j+1, null);
					}
				}
			}
		}
	}

	public static class SMNode<Item>{
		public Item item;
		@SuppressWarnings("unused")
		private int i,j;
		private SMNode<Item> next;
		
		public SMNode(Item item, int i, int j, SMNode<Item> next){
			this.item = item;
			this.i = i;
			this.j = j;
			this.next = next;
		}
	}
	
	public void checkBounds(int i, int j){
		if (i < 1 || i > this.rows|| j < 1 || j > this.columns){
			throw new IndexOutOfBoundsException("Index must be in range");
		}
	}
	
	public void add(int i, int j, Item value){
		checkBounds(i, j);
		SMNode<Item> sNode = this.startNodes[i-1];
		if(sNode == null){ //If no initial node is found, create new
			this.startNodes[i-1] = new SMNode<Item>(value, i, j, null);
		}
		else{
			if(sNode.j == j){ //Will become the new firstNode (First existed with other value)
				sNode.item = value;
			}
			else if(sNode.j > j){ //Will become the new firstNode (firstNode was a bigger j)
				this.startNodes[i-1] = new SMNode<Item>(value, i, j, sNode);
			}
			else{
				while(sNode.next != null && sNode.next.j < j){ //If next node is not null (We are on the end) and the next j is bigger or equal, change to next node
					sNode = sNode.next;
				}
				if(sNode.next != null && sNode.next.j == j){ //If next node has the same j and it's not null, change the value of that node
					sNode.next.item = value;
				}
				else{ //If none of above, means next j is not smaller(Bigger or null) and current j is smaller. Node to be added on the middle
					SMNode<Item> nxt = sNode.next;
					sNode.next = new SMNode<Item>(value, i, j, nxt);
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Item get(int i, int j){
		checkBounds(i, j);
		SMNode<Item> sNode = this.startNodes[i-1];
		
		if(sNode == null){ //If no initial node is found, only zeroes on row
			return (Item) zero;
		}
		else{
			while(sNode.next != null && sNode.j != j){ //If next node is not null (We are on the end) and this node hasn't the needed j, change to next node
				sNode = sNode.next;
			}
			if(sNode.j == j){ //The j was found on current node and returned (Wasn't a zero)
				return sNode.item;
			}
			else{ //If next node is null, means the value is a zero (The j was not found on the nodes)
				return (Item) zero;
			}
		}
	}
	
	public Item remove(int i, int j){
		checkBounds(i, j);
		SMNode<Item> sNode = this.startNodes[i-1];
		
		if(sNode == null){ //If no initial node is not found, means only zeros on row. Nothing to remove
			throw new NoSuchElementException("No item in index to remove");
		}
		else{
			SMNode<Item> prv = null;
			while(sNode.next != null && sNode.j != j){ //If next node is not null (We are on the end) and this node hasn't the needed j, change to next node
				prv = sNode;
				sNode = sNode.next;
			}
			
			if(sNode.j == j){ //If the j was found on current node
				if(prv == null){ //If the node is the first node
					if(sNode.next == null){ //If the first node was the only on the row
						this.startNodes[i-1] = null;
					}
					else{ //If the first node wasn't the only of the row
						this.startNodes[i-1] = sNode.next;
					}
				}
				else{ //If the node wasn't the first node on the row (Still it was found and is the current node), correct previous node's next
					prv.next = sNode.next;
				}
				return sNode.item; //Return current node's item
			}
			else{
				throw new NoSuchElementException("No item in index to remove");
			}
		}
	}
	
	public String output(){
		StringBuilder op = new StringBuilder();
		SMNode<Item> temp;
		for(int i = 0; i < this.rows; i++){
			temp = this.startNodes[i];
			
			for(int j = 1; j <= this.columns; j++){
				if(temp != null){
					if(temp.j == j){
						op.append(temp.item);
						temp = temp.next;
					}
					else{
						op.append(this.zero);
					}
				}
				else{
					op.append(this.zero);
				}
			}
			op.append("\n");
		}
		return op.toString();
	}
}
