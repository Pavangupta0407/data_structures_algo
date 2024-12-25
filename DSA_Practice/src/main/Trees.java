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
}

