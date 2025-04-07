package main;

import java.util.HashMap;
import java.util.Map;

public class SlidingW_TwoPoint {

	public int lengthOfLongestSubstring(String s) {
		int maxlen = 0, l = 0, r = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		while (r < s.length()) {
			if (map.containsKey(s.charAt(r))) {
				if (map.get(s.charAt(r)) >= l) {
					l = map.get(s.charAt(r)) + 1;
				}
			} 
			maxlen = Math.max(maxlen, r - l + 1);
			map.put(s.charAt(r), r);
			r++;
		}
		return maxlen;
	}
	
	public int longestOnes(int[] nums, int k) {
        int maxlen=0,l=0,r=0,zeros=0;
        while(r<nums.length) {
        	if(nums[r]==0) zeros++;
        	while(zeros>k) {
        		if(nums[l]==0) zeros--;
        		l++;
        	}
        	if(zeros<=k) {
        		maxlen= Math.max(maxlen, r-l+1);
        	}
        	r++;
        }
        return maxlen;
    }
	
	public int totalElements(Integer[] arr) {
        // code here
		int maxlen=0,l=0,r=0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		while(r<arr.length) {
			if(!map.containsKey(arr[r])) {
				map.put(arr[r], 1);
			} else {
				map.replace(arr[r], map.get(arr[r])+1);
			}
			if(map.size()>2) {
				map.replace(arr[l], map.get(arr[l])-1);
				if(map.get(arr[l])==0) {
					map.remove(arr[l]);
				}
				l++;
			}
			if(map.size()<=2) {
				maxlen=Math.max(maxlen, r-l+1);
			}
			r++;
		}
		return maxlen;
    }
	
	public int numSubarraysWithSum(int[] nums, int goal) {
        int ans;
        ans = subarraywithgoal(nums, goal)-subarraywithgoal(nums, goal-1);
        return ans;
    }
	
	public int subarraywithgoal(int[] nums,int goal) {
		int cnt=0,l=0,r=0,sum=0;
        if(goal<0) return 0;
		while(r<nums.length) {
			sum=sum+nums[r];
			while(sum>goal) {
				sum=sum-nums[l];
				l++;
			}
			cnt=cnt+(r-l+1);
			r++;
		}
		return cnt;
	}
	
	public int characterReplacement(String s, int k) {
        int ml=0,mf=0,l=0,r=0;
        int[] hash = new int[26];
        while(r<s.length()) {
        	hash[s.charAt(r)-'A']++;
        	mf=Math.max(mf, hash[s.charAt(r)-'A']);
        	if((r-l+1)-mf>k) {
        		hash[s.charAt(l)-'A']--;
        		mf=Math.max(mf, hash[s.charAt(l)-'A']);
        		l++;
        	}
        	if((r-l+1)-mf<=k) {
        		ml=Math.max(ml, r-l+1);
        	}
        	r++;
        }
        return ml;
    }

	public static void main(String[] args) {
		SlidingW_TwoPoint s = new SlidingW_TwoPoint();
		int len = s.lengthOfLongestSubstring("bbbbb");
		System.out.println(len);
		int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
		int ans = s.longestOnes(nums, 2);
		System.out.println(ans);
		Integer[] arr = {2, 1, 2};
		int totalElements = s.totalElements(arr);
		System.out.println(totalElements);
		int[] nums1= {0,0,0,0,0};
		int res = s.numSubarraysWithSum(nums1, 0);
		System.out.println(res);
		System.out.println(s.characterReplacement("AABABBA", 1));
	}
}
