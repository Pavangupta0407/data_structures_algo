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
}

