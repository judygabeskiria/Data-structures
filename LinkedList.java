import java.util*;

class Node {
Node next = null;
int data;
public Node(int d) { data = d; }
void appendToTail(int d) {
Node end = new Node(d);
Node n = this;
while (n.next != null) { n = n.next; }
n.next = end;
}
}

Node deleteNode(Node head, int d) {
Node n = head;
if (n.data == d) {
return head.next; /* moved head */
}
while (n.next != null) {
  if (n.next.data == d) {
    n.next = n.next.next; //we skip pointing to the node we are about to delete
    return head; /* head didn’t change */
    }
  n = n.next;
  }
  }
  
  //using a buffer
  public static void deleteDuplicates(LinkedListNode n) {
  Hashtable table = new Hashtable();
  LinkedListNode previous = null;
  while (n != null) {
  if (table.containsKey(n.data)) previous.next = n.next;
  else {
  table.put(n.data, true);
  previous = n;
 }
  n = n.next;
  }
  }
  //without a buffer
  //iterate with two pointers: current(normal iteration) and a runner(goes through all prior nodes)
  public static void deleteDuplicates2(LinkedListNode head) {
  if (head == null) return;
  LinkedListNode previous = head;
  LinkedListNode current = previous.next;
  while (current != null) {
  LinkedListNode runner = head;
  while (runner != current) { // Check for earlier dups
  if (runner.data == current.data) {
  LinkedListNode tmp = current.next; // remove current
   previous.next = tmp;
   current = tmp; // update current to next node
   break; // all other dups have already been removed
   }
   runner = runner.next;
   }
   if (runner == current) { // current not updated - update now
   previous = current;
   current = current.next;
   }
   }
   }
   //find the nth to last element
   LinkedListNode nthToLast(LinkedListNode head, int n) {
    if (head == null || n < 1) {
    return null;
    }
    LinkedListNode p1 = head;
    LinkedListNode p2 = head;
    for (int j = 0; j < n - 1; ++j) { // skip n-1 steps ahead
    if (p2 == null) {
    return null; // not found since list size < n
    }
    p2 = p2.next;
    }
    while (p2.next != null) {
    p1 = p1.next;
    p2 = p2.next;
    }
    return p1;
    }
    //delete a node in the middle of the list given only access to this node
    //simply copy data from next node into this node and delete next node
    public static boolean deleteNode(LinkedListNode n) {
    if (n == null || n.next == null) {
    return false; // Failure
    }
    LinkedListNode next = n.next;
    n.data = next.data;
    n.next = next.next;
    return true;
    }
