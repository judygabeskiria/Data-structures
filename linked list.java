import java.util*;

public class LinkedList
{
	// reference to the head node.
	private Node head;
	private int listCount;
	
	// LinkedList constructor
	public LinkedList()
	{
		// this is an empty list, so the head node would be a new node with no data
		head = new Node(null);
		listCount = 0;
	}
	
	public void add(Object data)
	// appends the specified element to the end of this list.
	{
		Node temp = new Node(data);
		Node current = head;
		// starting at the head node, go to the end of the list
		while(current.getNext() != null)
		{
			current = current.getNext();
		}
		// set the last node's next to new node
		current.setNext(temp);
		listCount++;// increment the number of elements variable
	}
	
	public void add(Object data, int index)
	// post: inserts the specified element at the specified position in this list.
	{
		Node temp = new Node(data);
		Node current = head;
		// go to the requested index or the last element in the list,
		// whichever of the two comes first
		for(int i = 1; i < index && current.getNext() != null; i++)
		{
			current = current.getNext();
		}
		// set the new node's next-node reference to this node's next-node reference
		temp.setNext(current.getNext());
		// now set this node's next-node reference to the new node
		current.setNext(temp);
		listCount++;// increment the number of elements variable
	}
	
	public Object get(int index)
	// returns the element at the specified position in this list.
	{
		// index must be 1 or higher
		if(index <= 0)
			return null;
		
		Node current = head.getNext();
		for(int i = 1; i < index; i++)
		{
			if(current.getNext() == null)
				return null;
			
			current = current.getNext();
		}
		return current.getData();
	}
	
	public boolean remove(int index)
	// removes the element at the specified position in this list.
	{
		// if the index is out of range, exit
		if(index < 1 || index > size())
			return false;
		
		Node current = head;
		for(int i = 1; i < index; i++)
		{
			if(current.getNext() == null)
				return false;
			
			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		listCount--; // decrement the number of elements variable
		return true;
	}
	
	public int size()
	// post: returns the number of elements in this list.
	{
		return listCount;
	}
	
	public String toString()
	{
		Node current = head.getNext();
		String output = "";
		while(current != null)
		{
			output += "[" + current.getData().toString() + "]";
			current = current.getNext();
		}
		return output;
	}
	
	private class Node
	{
		// reference to the next node in the chain,
		// or null if there isn't one.
		Node next;
		// data carried by this node.
		// could be of any type you need.
		Object data;
		

		// Node constructor
		public Node(Object _data)
		{
			next = null;
			data = _data;
		}
		
		// another Node constructor if we want to
		// specify the node to point to.
		public Node(Object _data, Node _next)
		{
			next = _next;
			data = _data;
		}
		
		
		public Object getData()
		{
			return data;
		}
		
		public void setData(Object _data)
		{
			data = _data;
		}
		
		public Node getNext()
		{
			return next;
		}
		
		public void setNext(Node _next)
		{
			next = _next;
		}
	}
}