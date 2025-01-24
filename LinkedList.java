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
	
	public Node deleteValue(Node head, int ele) {
		if(head==null) return head;
		if(head.data==ele) {
			head=head.next;
			return head;
		}
		Node temp=head,prev=null;
		while(temp!=null) {
			if(temp.data==ele) {
				prev.next=prev.next.next;
				break;
			}
			prev=temp;
			temp=temp.next;
		}
		return head;
	}
	
	public Node insertBegin(Node head, int ele) {
		if(head==null) {
			return new Node(ele);
		}
		Node temp = new Node(ele);
		temp.next=head;
		return temp;
	}
	
	Node insertAtEnd(Node head, int x) {
        // code here
		if(head==null) {
			return new Node(x);
		}
		Node temp=head;
		while(temp.next!=null) {
			temp=temp.next;
		}
		Node node = new Node(x);
		temp.next=node;
		return head;
    }
	
	Node insertAtKPos(Node head, int k,int ele) {
		if(head==null) {
			if(k==1) return new Node(ele);
		}
		if(k==1) {
			Node temp = new Node(ele);
			temp.next=head;
			return temp;
		}
		Node temp=head;
		int cnt=0;
		while(temp!=null) {
			cnt++;
			if(cnt==k-1) {
				Node node = new Node(ele);
				node.next = temp.next;
				temp.next = node;
				return head;
			}
			temp=temp.next;
		}
		return head;
	}
	
	Node insertBeforeValue(Node head,int ele, int x) {
		if(head==null) return null;
		if(head.data==x) {
			Node node = new Node(ele);
			node.next = head;
			return node;
		}
		Node temp=head;
		while(temp.next!=null) {
			if(temp.next.data == x) {
				Node node = new Node(ele);
				node.next=temp.next;
				temp.next=node;
				break;
			}
			temp=temp.next;
		}
		return head;
	}
	
	//237. Delete Node in a Linked List
	public void deleteNode(ListNode node) {
		node.val=node.next.val;
		node.next=node.next.next;
	}
	
	public int getCount(Node head) {
        // code here
		Node temp=head;
		int cnt=0;
		while(temp!=null) {
			cnt++;
			temp=temp.next;
		}
		return cnt;
    }
	
	static boolean searchKey(int n, Node head, int key) {
        // Code here
		Node temp=head;
		while(temp!=null) {
			if(temp.data==key) {
				return true;
			}
			temp=temp.next;
		}
		return false;
    }
	
	//876. Middle of the Linked List Brute
	public ListNode middleNodeBrute(ListNode head) {
		int n=0;
		ListNode temp=head;
		while(temp!=null) {
			n++;
			temp=temp.next;
		}
		int middle=(n/2)+1;
		temp=head;
		while(temp!=null) {
			middle--;
			if(middle==0) {
				break;
			}
			temp=temp.next;
		}
		return temp;
	}
	
	//206. Reverse Linked List
	public ListNode reverseListIterative(ListNode head) {
		ListNode temp=head,prev=null;
		while(temp!=null) {
			ListNode front=temp.next;
			temp.next=prev;
			prev=temp;
			temp=front;
		}
		return head;
	}
	
	//Recursion
	public ListNode reverseList(ListNode head) {
		if(head==null || head.next==null) {
			return head;
		}
		ListNode newhead = reverseList(head.next);
		ListNode front = head.next;
		front.next=head;
		head.next=null;
		return newhead;
	}
		
	//876. Middle of the Linked List Optimal using slow and fast pointer
		public ListNode middleNode(ListNode head) {
			ListNode slow=head,fast=head;
			while(fast!=null && fast.next!=null) {
				slow=slow.next;
				fast=fast.next.next;
			}
			return slow;
		}

}
