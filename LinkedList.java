package main;

class Node {
    int data;
    Node next;

    Node() { data = 0; }
    Node(int d) { data = d; }  //constructor to create a new node
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class LinkedList {
	
	static Node constructLL(int arr[]) {
        // code here
		Node head=new Node(arr[0]);
		Node mover=head;
		for(int i=1;i<arr.length;i++) {
			Node temp = new Node(arr[i]);
			mover.next = temp;
			mover = mover.next;
		}
		return head;
    }
	
	static void traversalLL(Node head) {
		Node temp = head;
		while(temp!=null) {
			System.out.print(temp.data+" ");
			temp=temp.next;
		}
	}
	
	public Node deleteHead(Node head) {
		if(head==null) return head;
		Node temp = head;
		head=head.next;
		return head;
	}
	
	public Node deleteTail(Node head) {
		if(head==null || head.next==null) return null;
		Node temp=head;
		while(temp.next.next!=null) {
			temp=temp.next;
		}
		temp.next=null;
		return head;
	}
	
	public Node deleteKthElement(Node head,int k) {
		if(head==null) return head;
		if(k==1) {
			head=head.next;
			return head;
		}
		int cnt=0;
		Node temp=head,prev=null;
		while(temp!=null) {
			cnt++;
			if(cnt==k) {
				prev.next=prev.next.next;
				break;
			}
			prev=temp;
			temp=temp.next;
		}
		return head;
	}
	
	Node insertAtEnd(Node head, int x) {
        // code here
		Node temp=head;
		while(temp!=null) {
			temp=temp.next;
		}
		Node node = new Node(x);
		temp.next=node;
		return head;
    }

}
