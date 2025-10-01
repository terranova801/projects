
public class LinkedList {

	private Node head;
	private Node tail;
	
	public void add(String item) {
		
		Node newItem = new Node(item);
		
		// handles the case where the new item 
		// is the only thing in the list
		if (head == null) {
			head = newItem;
			tail = newItem;
			return;
		}
		
		tail.next = newItem;
		tail = newItem;
	}
	
	
	public void print() {
		Node current = head;
		while (current != null) {
			System.out.println(current.item);
			current = current.next;
		}
	}
	
	
	public String getPenultimate() {
	    if (head == null || head.next == null) {	//checks first and second positions
			return null;
		}
		//Move through list to second to last position
		Node traverse = head;
		while (traverse.next != tail) {
			traverse = traverse.next;
		}
		return traverse.item;	//returns second to last item value
		
		// TODO fix me
	    //return "Not Implemented";
	}

	//good to go
	//fixed, returns null now if head item doesn't exist
	public String getFirst() {
	  if (head == null) {
		return null;
	  }
		return head.item;
	}

	//also good
	//fixed, returns null now if tail item doesn't exist
	public String getLast() {
	    if (tail == null) {
			return null;
		}
		return tail.item;
	}

	
	class Node {
		String item;
		Node next;
		
		public Node(String item) {
			this.item = item;
			this.next = null;
		}
	}
}

