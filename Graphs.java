package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;
import main.Trees.TreeNode;

public class Graphs {
	
	//BFS traversal
	public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
		ArrayList<Integer> bfs=new ArrayList<Integer>();
		boolean[] visited = new boolean[V];
		Queue<Integer> q = new LinkedList<Integer>();
		visited[0]=true;
		q.add(0);
		while(!q.isEmpty()) {
			Integer node = q.poll();
			bfs.add(node);
			for(Integer it: adj.get(node)) {
				if(!visited[it]) {
					visited[it]=true;
					q.add(it);
				}
			}
		}
		return bfs;
    }
	
	// Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        // Code here
    	ArrayList<Integer> dfs=new ArrayList<Integer>();
    	boolean[] visited = new boolean[adj.size()];
    	dfs(adj,visited,dfs,0);
    	return dfs;
    }
    
    public void dfs(ArrayList<ArrayList<Integer>> adj,boolean[] visited,ArrayList<Integer> dfs,int node) {
    	visited[node]=true;
    	dfs.add(node);
    	for(Integer it: adj.get(node)) {
    		if(!visited[it]) {
    			dfs(adj,visited,dfs,it);
    		}
    	}
    }
    
    //547. Number of Provinces
    public int findCircleNum(int[][] isConnected) {
    	int cnt=0;
    	boolean[] visited = new boolean[isConnected.length];
    	for(int i=0;i<isConnected.length;i++) {
    		if(!visited[i]) {
    			dfs(visited,isConnected,i);
    			cnt++;
    		}
    	}
    	return cnt;
        
    }
    
    //DFS traversal using adjcaency matrix
    public void dfs(boolean[] visited,int[][] adjm,int node) {
    	visited[node]=true;
    	for(int i=0;i<adjm[node].length;i++) {
    		if(adjm[node][i]==1 && !visited[i]) {
    			dfs(visited,adjm,i);
    		}
    	}
    }
    
    //Number of Provinces GFG
    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        // code here
    	int cnt=0;
    	boolean[] visited = new boolean[V];
    	for(int i=0;i<V;i++) {
    		if(!visited[i]) {
    			dfs(visited,adj,i);
    		}
    	}
    	return cnt;
    }
    
    public static void dfs(boolean[] visited,ArrayList<ArrayList<Integer>> adjl,int node) {
    	visited[node]=true;
    	for(int i=0;i< adjl.get(node).size();i++) {
    		if(adjl.get(node).get(i)==1 && !visited[i]) {
    			dfs(visited,adjl,i);
    		}
    	}
    }
    
    public int orangesRotting(int[][] grid) {
    	int max_time=0;
    	int n = grid.length;
    	int m = grid[0].length;
    	Queue<Pair> q= new LinkedList<Pair>();
    	boolean[][] visited = new boolean[n][m];
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<m;j++) {
    			if(grid[i][j]==2 && !visited[i][j]) {
    				q.add(new Pair(new Node(i,j),0));
    				visited[i][j]=true;
    			}
    		}
    	}
    	int drow[]= {0,-1,0,+1};
    	int dcol[]= {-1,0,+1,0};
    	while(!q.isEmpty()) {
    		Pair pair = q.poll();
    		Node node = pair.node;
    		int time = pair.time;
    		max_time=Math.max(max_time, time);
    		for(int i=0;i<4;i++) {
    			int nrow = node.i+drow[i];
    			int ncol = node.j+dcol[i];
    			if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && !visited[nrow][ncol] && grid[nrow][ncol]==1) {
    				q.add(new Pair(new Node(nrow,ncol),time+1));
    				visited[nrow][ncol]=true;
    				grid[nrow][ncol]=2;
    			}
    		}
    	}
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<m;j++) {
    			if(grid[i][j]==1 && !visited[i][j]) {
    				return -1;
    			}
    		}
    	}
    	return max_time;  
    }
    
    public class Pair{
    	Node node;
		public Pair(Node node, int time) {
			super();
			this.node = node;
			this.time = time;
		}
		int time;
	}
    
    public class Node{
    	int i;
		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		int j;
    }
    
    //733. Flood Fill using BFS
//	public int[][] floodFill(int[][] image, int sr, int sc, int color) {
//		int n=image.length;
//		int m=image[0].length;
//		boolean[][] visited = new boolean[n][m];
//		Queue<Node> q= new LinkedList<Node>();
//		q.add(new Node(sr,sc));
//		visited[sr][sc]=true;
//		int drow[]= {0,-1,0,+1};
//		int dcol[]= {-1,0,+1,0};
//		while(!q.isEmpty()) {
//			Node node = q.peek();
//			for(int i=0;i<4;i++) {
//				int nrow=node.i+drow[i];
//				int ncol=node.j+dcol[i];
//				if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && !visited[nrow][ncol] && image[node.i][node.j]==image[nrow][ncol]) {
//					q.add(new Node(nrow,ncol));
//					visited[nrow][ncol]=true;
//				}
//			}
//			q.poll();
//			image[node.i][node.j]=color;
//		}
//		return image;
//	}
	
	//Using DFS recurrsion
	public int[][] floodFill(int[][] image, int sr, int sc, int color) {
		int n=image.length;
		int m=image[0].length;
		boolean[][] visited = new boolean[n][m];
		dfsfill(image,visited,sr,sc,color,n,m);
		return image;
	}
	
	public void dfsfill(int[][] image,boolean[][] visited,int sr,int sc,int color,int n,int m) {
		visited[sr][sc]=true;
		int drow[]= {0,-1,0,+1};
		int dcol[]= {-1,0,+1,0};
		for(int i=0;i<4;i++) {
			int nrow=sr+drow[i];
			int ncol=sc+dcol[i];
			if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && !visited[nrow][ncol] && image[sr][sc]==image[nrow][ncol]) {
				dfsfill(image,visited,nrow,ncol,color,n,m);
			}
		}
		image[sr][sc]=color;
	}
	
	//Undirected Graph Cycle
	public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        // Code here
		int[] visited=new int[adj.size()];
		Queue<Integer> q= new LinkedList<Integer>();
		for(int i=0;i<adj.size();i++) {
			
		}
    }

}
