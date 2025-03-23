package main;

import java.util.HashSet;
import java.util.Stack;

public class Random {

	public boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		int res = 0, temp = x;
		while (x != 0) {
			int rem = x % 10;
			res = res * 10 + rem;
			x = x / 10;
		}
		if (res != temp) {
			return false;
		}
		return true;
	}

	public String longestCommonPrefix(String[] strs) {
		String res = "";
		for (int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			boolean temp = true;
			for (int j = 1; j < strs.length; j++) {
				if (i < strs[j].length()) {
					if (strs[j].charAt(i) != c) {
						temp = false;
					}
				} else {
					temp = false;
				}
			}
			if (temp == false) {
				return res;
			}
			res = res + c;
		}
		return res;
	}

	public boolean isValid(String s) {
		Stack<Character> st = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(' || c == '{' || c == '[') {
				st.push(c);
			} else {
				if (st.isEmpty() || (c == ')' && st.peek() != '(') || (c == '}' && st.peek() != '{')
						|| (c == ']' && st.peek() != '[')) {
					return false;
				}
				if (c == ')' && st.peek() == '(') {
					st.pop();
				} else if (c == '}' && st.peek() == '{') {
					st.pop();
				} else if (c == ']' && st.peek() == '[') {
					st.pop();
				}
			}
		}
		if (st.isEmpty()) {
			return true;
		}
		return false;
	}

	public static void main(String args[]) {
		Random r = new Random();
		System.out.println(r.isPalindrome(-121));
		String in[] = { "dog", "racecar", "car" };
		System.out.println(r.longestCommonPrefix(in));
		System.out.println(r.isValid("]"));
	}
}
