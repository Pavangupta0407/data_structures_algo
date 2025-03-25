package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Recurssion {

	public int myAtoi(String s) {
		int result = 0;
		int sign = 1;
		int index = 0;
		if (s.charAt(index) == ' ') {
			while (s.charAt(index) == ' ') {
				index++;
			}

		}
		if (index == s.length()) {
			return result;
		}
		if (s.charAt(index) == '+' || s.charAt(index) == '-') {
			if (s.charAt(index) == '-') {
				sign = -1;
			}
		}

		return result;
	}

	public double myPow(double x, int n) {
		long nn=n;
		double ans =1.0;
		if(n<0) nn=nn*(-1);
		while(nn>0) {
			if(nn%2==0) {
				x=x*x;
				nn=nn/2;
			} else {
				ans=ans*x;
				nn=nn-1;
			}
		}
		if(n<0) ans=1/ans;
		return ans;
	}
	
	//1922. Count Good Numbers
	public int countGoodNumbers(long n) {
        long nn=n/2;
        long res = (n%2==0)?1:5;
        long x=20;
        int MOD = 1000000007;
        for(long i=n/2;i>0;i/=2) {
        	if(i%2!=0) res=res*x%MOD;
        	x=x*x%MOD;
        }
        return (int)res;
    }
	
	public long pow(int x,long nn) {
		long ans=1;
		int MOD = 1000000007;
		while(nn>0) {
			if(nn%2==0) {
				x=x*x%MOD;
				nn=nn/2;
			} else {
				ans=ans*x%MOD;
				nn=nn-1;
			}
		}
		return ans;
	}
	
	public double pow(double x, long nn,double ans) {
		if(nn<0) {
			return ans;
		}
		if(nn%2==0) {
			x=x*x;
			nn=nn/2;
		} else {
			ans=ans*x;
			nn=nn-1;
		}
		return pow(x,nn,ans);
	}
	
	public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        Stack<Character> s= new Stack<>();
        backtrack(0,0,res,n,s);
        return res;
    }
	
	public void backtrack(int open, int close, List<String> res,int n,Stack<Character> st){
		if(open==close && open==n && close==n) {
			String ans="";
			for(Character c:st) {
				ans=ans+c;
			}
			res.add(ans);
			return;
		}
		if(open<n) {
			st.push('(');
			backtrack(open+1, close, res, n,st);
			st.pop();
		}
		if(close<open) {
			st.push(')');
			backtrack(open, close+1, res, n,st);
			st.pop();
		}
	}
	
	public static void main(String args[]) {
		Recurssion r = new Recurssion();
		System.out.println(r.myPow(2.0, 10));
		System.out.println(r.countGoodNumbers(50));
		System.out.println(r.generateParenthesis(2));
	}

}
