package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Trees {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
		
	}
	
//	public static void main(String[] args) {
//		TreeNode tree = new TreeNode(1);
//		tree.left = new TreeNode(2);
//		tree.right = new TreeNode(3);
//		tree.left.left = new TreeNode(4);
//		tree.left.right = new TreeNode(5);
//		tree.right.left = new TreeNode(6);
//		tree.right.right = new TreeNode(7);
//	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new LinkedList<List<Integer>>();
		Queue<TreeNode> queue= new LinkedList<TreeNode>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int level = queue.size();
			List<Integer> sublist = new LinkedList<Integer>();
			for(int i=0;i<level;i++) {
				if(queue.peek().left !=null) {
					queue.offer(queue.peek().left);
				}
				if(queue.peek().right !=null) {
					queue.offer(queue.peek().right);
				}
				sublist.add(queue.poll().val);
			}
			res.add(sublist);
		}
		return res;

	}
	
	public List<Integer> preorderiter(TreeNode root){
		List<Integer> res= new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(root==null) {
			return res;
		}
		stack.push(root);
		while(!stack.isEmpty()) {
			root = stack.pop();
			res.add(root.val);
			if(root.right !=null) {
				stack.push(root.right);
			}
			if(root.left !=null) {
				stack.push(root.left);
			}
		}
		return res;
	}
	
	public List<Integer> inorderiter(TreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		if(root==null) {
			return res;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode node = root;
		while(true) {
			if(node!=null) {
				st.push(node);
				node=node.left;
			}else {
				if(st.empty()) {
					break;
				}
				node = st.pop();
				res.add(node.val);
				node=node.right;
			}
		}
		return res;
	}
	
	public List<Integer> postorder2stack(TreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		if(root == null) {
			return res;
		}
		Stack<TreeNode> st1 = new Stack<TreeNode>();
		Stack<TreeNode> st2 = new Stack<TreeNode>();
		st1.push(root);
		while(!st1.isEmpty()) {
			root = st1.pop();
			st2.push(root);
			if(root.left!=null) {
				st1.push(root.left);
			}
			if(root.right!=null) {
				st1.push(root.right);
			}
		}
		while(!st2.isEmpty()) {
			res.add(st2.pop().val);
		}
		return res;
	}
	
	public List<Integer> postorder1stack(TreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		if(root==null) {
			return res;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode curr=root,temp;
		while(curr!=null || !st.isEmpty()) {
			if(curr!=null) {
				st.push(curr);
				curr = curr.left;
			} else {
				temp=st.peek().right;
				if(temp==null) {
					temp=st.peek();
					st.pop();
					res.add(temp.val);
					while(!st.isEmpty() && temp==st.peek().right) {
						temp=st.peek();
						st.pop();
						res.add(temp.val);
					}
				} else {
					curr=temp;
				}
			}
		}
		return res;
	}
	
	public void pre_in_post_order_onetraversal(TreeNode root) {
		List<Integer> pre = new ArrayList<Integer>();
		List<Integer> in = new ArrayList<Integer>();
		List<Integer> post = new ArrayList<Integer>();
		Stack<Pair> st = new Stack<Pair>();
		st.push(new Pair(root,1));
		while(!st.isEmpty()) {
			Pair temp = st.pop();
			if(temp.getNum()==1) {
				pre.add(temp.getNode().val);
				temp.num++;
				st.push(temp);
				if(temp.node.left!=null) {
					st.push(new Pair(temp.node.left,1));
				}
			}
			else if(temp.getNum()==2) {
				in.add(temp.getNode().val);
				temp.num++;
				st.push(temp);
				if(temp.node.right!=null) {
					st.push(new Pair(temp.node.right,1));
				}
			}
			else {
				post.add(temp.getNode().val);
			}
		}
	}
	
	public class Pair{
		TreeNode node;
		public TreeNode getNode() {
			return node;
		}
		public void setNode(TreeNode node) {
			this.node = node;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		int num;
		Pair(TreeNode node,int num){
			this.node=node;
			this.num=num;
		}
	}
	
	public int maxDepth(TreeNode root) {
        if(root== null){
            return 0;
        }
        int lt=maxDepth(root.left);
        int rt=maxDepth(root.right);
        return 1+Math.max(0, 0);
        
    }
	
	// 257. Binary Tree Paths
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res=new ArrayList<String>();
		if(root==null) {
			return res;
		}
		if(root.left==null || root.right==null) {
			res.add(""+root.val);
			return res;
		}
		if(root.left!=null) {
			TreeNode curr=root;
			String lt=""+root.val;
			preorder(curr.left,lt);
			res.add(lt);
		}
		if(root.right!=null) {
			String rt=""+root.val;
			preorder(root.right,rt);
			res.add(rt);
		}
		return res;
	}
	
	public void preorder(TreeNode root,String temp) {
		if(root==null) {
			return;
		}
		temp="->"+root.val;
		preorder(root.left,temp);
		preorder(root.right,temp);
	}
	
	static boolean isBSTTraversal(int arr[]) {
        // code here
        for(int i=1;i<arr.length;i++) {
        	if(arr[i-1]>=arr[i]) {
        		return false;
        	}
        }
        return true;
    }
	
	public TreeNode searchBST(TreeNode root, int val) {
		TreeNode res=null;
		if(root==null) {
			return null;
		}
		if (root.val == val) {
			return res;
		} else if (val < root.val) {
			res=searchBST(root.left, val);
		} else {
			res=searchBST(root.right, val);
		}
		return res;
	}
	
	class Node {
	    int data;
	    Node left;
	    Node right;
	    Node(int data) {
	        this.data = data;
	        left = null;
	        right = null;
	    }
	}
	 // Function to find the minimum element in the given BST.
    int minValue(Node root) {
        // code here
    	int min=100001;
    	while(root!=null) {
    		if(root.data<min) {
    			min=root.data;
    			root=root.left;
    		}
    	}
    	return min;
    }
    
 // Function to return the ceil of given number in BST.
    int findCeil(Node root, int key) {
        if (root == null) return -1;
        // Code here
        int ceil=-1;
        while(root!=null) {
        	if(root.data==key) {
        		return root.data;
        	}
        	if(key>root.data) {
        		root=root.right;
        	} else {
        		ceil=root.data;
        		root=root.left;
        	}
        }
        return ceil;
    }
    
    public static int floor(Node root, int x) {
        // Code here
    	int floor=-1;
    	while(root!=null) {
    		if(root.data==x) {
    			return root.data;
    		}
    		if(x>root.data) {
    			floor=root.data;
    			root=root.right;
    		} else {
    			root=root.left;
    		}
    	}
    	return floor;
    }
    
    //Insert into BST
    public TreeNode insertIntoBST(TreeNode root, int val) {
    	if(root==null) {
    		return new TreeNode(val);
    	}
    	TreeNode curr=root;
    	while(curr!=null) {
    		if(val < curr.val) {
    			if(curr.left !=null) {
    				curr=curr.left;
    			}else {
    				curr.left= new TreeNode(val);
    				break;
    			}
    		} else {
    			if(curr.right !=null) {
    				curr=curr.right;
    			}else {
    				curr.right= new TreeNode(val);
    				break;
    			}
    		}
    	}
        return root;
    }
    
    //Delete Node form BST
	public TreeNode deleteNode(TreeNode root, int key) {
		if(root==null) {
			return root;
		}
		if(root.val==key) {
			return helper(root);
		}
		TreeNode tree=root;
		while(root!=null) {
			if(key<root.val) {
				if(root.left!=null && root.left.val==key) {
					root.left=helper(root.left);
					break;
				} else {
					root=root.left;
				}
			} else {
				if (root.right != null && root.right.val == key) {
					root.right=helper(root.right);
					break;
				} else {
					root = root.right;
				}
			}
		}
		return tree;
	}
	
	public TreeNode helper(TreeNode root) {
		if(root.right==null) {
			return root.left;
		}
		if(root.left==null) {
			return root.right;
		}
		TreeNode rightChild = root.right;
		TreeNode lastRightofLeftTree = findLastRight(root.left);
		lastRightofLeftTree.right=rightChild;
		return root.left;
	}
	
	public TreeNode findLastRight(TreeNode root) {
		if(root.right==null) {
			return root;
		}
		return findLastRight(root.right);
	}
	
	//Brute Approach TO(N) SO(N)
	public int kthSmallest(TreeNode root, int k) {
		int ksmall,klar;
		List arr=new ArrayList<>();
		inorder(root,arr);
		ksmall= (int) arr.get(k);
		klar= (int)arr.get(arr.size()-k);
		return ksmall;
	}
	
	public void inorder(TreeNode root,List<Integer> arr) {
		if(root==null) {
			return;
		}
		inorder(root.left,arr);
		arr.add(root.val);
		inorder(root.right, arr);
	}
	
	//Brute approach
	public boolean isValidBST(TreeNode root) {
		//Brute approach
//        List arr=new ArrayList<>();
//		inorder(root,arr);
//		for(int i=0;i<arr.size()-1;i++) {
//			if((int)arr.get(i) >= (int) arr.get(i+1)) {
//				return false;
//			}
//		}
//		return true;
		//Optimiza using recursion
		return isvalidBst(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
	
	public boolean isvalidBst(TreeNode root, long min,long max) {
		if(root==null) {
			return true;
		}
		if(root.val>=max || root.val<=min) {
			return false;
		}
		return isvalidBst(root.left, min, root.val) && isvalidBst(root.right, root.val, max);
	}
	
	//LCA
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }
        return recursiveLCA(root,p,q);
    }
	
	public TreeNode recursiveLCA(TreeNode root,TreeNode p,TreeNode q) {
		int curr=root.val;
		if(curr<p.val && curr<q.val) {
			return recursiveLCA(root.right, p, q);
		}
		if(curr>p.val && curr>q.val) {
			return recursiveLCA(root.left, p, q);
		}
		return root;
	}
	
	public TreeNode iterativeLCA(TreeNode root,TreeNode p,TreeNode q) {
		TreeNode res=null;
		while(root!=null) {
			int curr=root.val;
			if(curr<p.val && curr<q.val) {
				root=root.right;
			}
			else if(curr>p.val && curr>q.val) {
				root=root.left;
			} else {
			res=root;
			break;
			}
		}
		return res;
	}
	
	public TreeNode bstFromPreorder(int[] preorder) {
		return preorderBST(preorder,Integer.MAX_VALUE,new int[]{0});
	}
	
	public TreeNode preorderBST(int[] arr, int bound, int[] i) {
		if(i[0]==arr.length || arr[i[0]]>bound) {
			return null;
		}
		TreeNode root=new TreeNode(arr[i[0]++]);
		root.left=preorderBST(arr, root.val, i);
		root.right=preorderBST(arr, bound, i);
		return root;
	}
	
	public static void findPreSuc(Node root, Node[] pre, Node[] suc, int key) {
        // code here.
        // update pre[0] with the predecessor of the key
        // update suc[0] with the successor of the key
		Node successor = successor(root,key);
		Node predessor = predessor(root, key);
		pre[0]=predessor;
		suc[0]=successor;
    }
	
	public static Node successor(Node root,int key) {
		Node suc=null;
		while(root!=null) {
			if(key >= root.data) {
				root=root.right;
			} else {
				suc=root;
				root=root.left;
			}
		}
		return suc;
	}
	
	public static Node predessor(Node root,int key) {
		Node pre=null;
		while(root!=null) {
			if(root.data >= key) {
				root=root.left;
			} else {
				pre=root;
				root=root.right;
			}
		}
		return pre;
	}
	
	//653. Two Sum IV - Input is a BST
	//Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.
	public boolean findTarget(TreeNode root, int k) {
		return bruteTwoSum(root,k);
	}
	
	public boolean bruteTwoSum(TreeNode root,int k) {
		//Get inorder travesal which willbe sorted array
		//Use two pointer for array traversal
		List<Integer> lt = inorderiter(root);
		int i=0,j=lt.size()-1;
		while(i<j) {
			if((lt.get(i)+lt.get(j))==k) {
				return true;
			} else if((lt.get(i)+lt.get(j))>k) {
				j--;
			} else {
				i++;
			}
		}
		return false;
	}
}

