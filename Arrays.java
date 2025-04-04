package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class Arrays {

	public static int largest(int[] arr) {
        // code here
		int largest = arr[0];
		for(int i=1;i<arr.length;i++) {
			if(arr[i]>largest) {
				largest=arr[i];
			}
		}
		return largest;
    }
	
	//Get second largest and second smallest element
	//1. Brute approach will be sort the array and return
	//2. Below one is better
	public static int[] getSecondOrderElementsBrute(int n, int []a) {
        // Write your code here.
		int largest = a[0];
		int smallest = a[0];
		int ans[] = new int[2];
		//Largest
		for(int i=1;i<n;i++) {
			if(a[i]>largest) {
				largest=a[i];
			}
			if(a[i]<smallest) {
				smallest=a[i];
			}
		}
		//second largest
		int secondL=smallest;
		int secondS=largest;
		for(int i=0;i<n;i++) {
			if(secondL<a[i] && a[i]<largest) {
				secondL=a[i];
			}
			if(secondS>a[i] && a[i]>smallest) {
				secondS=a[i];
			}
		}
		ans[0]=secondL;
		ans[1]=secondS;
		return ans;
    }
	
	//Optimal
	public static int[] getSecondOrderElements(int n, int []a) {
		int ans[]=new int[2];
		int slargest = secondLargest(n,a);
		int ssmallest = secondSmallest(n,a);
		a[0]=slargest;
		a[1]=ssmallest;
		return ans;
	}
	
	public static int secondLargest(int n,int[] a) {
		int largest = a[0];
		int slargest = -1;
		for(int i=0;i<n;i++) {
			if(a[i]>largest) {
				slargest=largest;
				largest=a[i];
			} else if(a[i]<largest && a[i]>slargest) {
				slargest=a[i];
			}
		}
		return slargest;
	}
	
	public static int secondSmallest(int n,int[] a) {
		int smallest = a[0];
		int ssmallest = Integer.MAX_VALUE;
		for(int i=0;i<n;i++) {
			if(a[i]<smallest) {
				ssmallest=smallest;
				smallest=a[i];
			} else if(a[i]>smallest && a[i]<ssmallest) {
				ssmallest=a[i];
			}
		}
		return ssmallest;
	}
	
	//Check if array is sorted
	public boolean check(int[] nums) {
        for(int i=1;i<nums.length;i++) {
        	if(nums[i]<nums[i-1]) {
        		return false;
        	}
        }
        return true;
    }
	
	//26. Remove Duplicates from Sorted Array
	//Brute using set since it stores unique elements
	//TC:nlog(n)[To insert] + O(n)[for next loop)
	//SC:O(n)
	public int removeDuplicatesBrute(int[] nums) {
		Set<Integer> st = new LinkedHashSet<Integer>();
		for(int i=0;i<nums.length;i++) {
			st.add(nums[i]);
		}
		int index=0;
		for(Integer it:st) {
			nums[index]=it;
			index++;
		}
		return index;
	}
	
	//Optimal using two pointers
	public int removeDuplicates(int[] nums) {
		int i=0,j=1;
		while(j<nums.length) {
			if(nums[i]!=nums[j]) {
				nums[i+1]=nums[j];
				i++;
			} 
			j++;
		}
		return i+1;
	}
	//167. Two Sum II - Input Array Is Sorted
	//Brute
	public int[] twoSumBrute(int[] numbers, int target) {
		int[] ans=new int[2];
		for(int i=0;i<numbers.length;i++) {
			for(int j=i+1;j<numbers.length;j++) {
				if(numbers[i]+numbers[j]==target) {
					ans[0]=i;
					ans[1]=j;
				}
			}
		}
		return ans;
	}
	
	//Optimal Using two pointers
    public int[] twoSum(int[] numbers, int target) {
		int[] ans=new int[2];
		int left=0,right=numbers.length-1;
		while(left<right) {
			if(numbers[left]+numbers[right]==target) {
				ans[0]=left+1;
				ans[1]=right+1;
                break;
			} else if(numbers[left]+numbers[right]>target) {
				right--;
			} else {
				left++;
			}
		}
		return ans;
	}
    
    //11. Container With Most Water
    //Brute approach try all possibel containers from starting
	public int maxAreaBrute(int[] height) {
		int max_area=0;
		for(int i=0;i<height.length;i++) {
			for(int j=i;j<height.length;j++) {
				int H = Math.min(height[i],height[j]);
				int W = j-i;
				max_area = Math.max(max_area, H*W);
			}
		}
		return max_area;
	}
	
	//Optimal approach using two pointers move the pointers based on hieght 
	// If HL < HR -> move left else move right
	public int maxArea(int[] height) {
		int max_area = 0;
		int l=0;
		int r=height.length-1;
		while(l<r){
			if(l>r) {
				break;
			}
			int H = Math.min(height[l], height[r]);
			int W = r - l;
			max_area = Math.max(max_area, H * W);
			if(height[l]<height[r]) {
				l++;
			} else {
				r--;
			}
		}
		return max_area;
	}
	//15. 3Sum
	//Brute approach try all possible
    public List<List<Integer>> threeSumBrute(int[] nums) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	Set<List<Integer>> st = new HashSet<List<Integer>>();
    	for(int i=0;i<nums.length;i++) {
    		for(int j=i+1;j<nums.length;j++) {
    			for(int k=j+1;k<nums.length;k++) {
    				if(nums[i]+nums[j]+nums[k]==0) {
    					List<Integer> triplets = new ArrayList<Integer>();
    					triplets.add(nums[i]);
    					triplets.add(nums[j]);
    					triplets.add(nums[k]);
    					triplets.sort(null);
    					st.add(triplets);
    				}
    			}
    		}
    	}
    	for(List<Integer> ls:st) {
    		res.add(ls);
    	}
    	return res;
    }
    
    //Better approach using hashset
    public List<List<Integer>> threeSumBetter(int[] nums) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	Set<List<Integer>> set = new HashSet<List<Integer>>();
    	for(int i=0;i<nums.length;i++) {
    		Set<Integer> st = new HashSet<Integer>();
    		for(int j=i+1;j<nums.length;j++) {
    			int var = -(nums[i]+nums[j]);
    			if(st.contains(var)) {
    				List<Integer> triplets = new ArrayList<Integer>();
					triplets.add(nums[i]);
					triplets.add(nums[j]);
					triplets.add(var);
					triplets.sort(null);
					set.add(triplets);
    			}
    			st.add(nums[j]);
    		}
    	}
    	for(List<Integer> ls:set) {
    		res.add(ls);
    	}
    	return res;
    }
    
    //Optimal approach using sort array and two pointers
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	java.util.Arrays.sort(nums);
    	for(int i=0;i<nums.length;i++){
    		if(i>0 && nums[i]==nums[i-1]) continue;
    		int j=i+1,k=nums.length-1;
    		while(j<k) {
    			int val=nums[i]+nums[j]+nums[k];
    			if(val<0) {
    				j++;
    			} else if (val>0) {
    				k--;
    			} else {
    				List<Integer> triplets = new ArrayList<Integer>();
					triplets.add(nums[i]);
					triplets.add(nums[j]);
					triplets.add(nums[k]);
					res.add(triplets);
					j++;
					k--;
					while(j<k && nums[j]==nums[j-1]) {
						j++;
					}
					while(k>j && nums[k]==nums[k+1]) {
						k--;
					}
    			}
    		}
    	}
    	return res;
    }
    
    
    //Binary Search
    public int search(int[] nums, int target) {
        //int ans = iterativeBS(nums,target);
    	int ans = recursiveBS(nums,target,0,nums.length-1);
        return ans;
    }
    
    //Iterative approach
    public int iterativeBS(int[] nums,int target) {
    	int low=0,high=nums.length-1;
    	while(low<high) {
    		int mid=(low+high)/2;
    		if(nums[mid]==target) {
    			return mid;
    		} else if(target > nums[mid]) {
    			low=mid+1;
    		} else {
    			high=mid-1;
    		}
    	}
    	return -1;
    }
    
    //Recursive approach
    public int recursiveBS(int[] nums,int target,int low,int high) {
    	if(low>high) {
    		return -1;
    	}
    	int mid=(low+high)/2;
    	if(nums[mid]==target) {
    		return mid;
    	} else if(target > nums[mid]) {
    		return recursiveBS(nums, target, mid +1, high);
    	}
    	return recursiveBS(nums, target, low, mid-1);
    }
    
    //Floor in a Sorted Array
    // Same logic can be used for 35. Search Insert Position
    static int findFloor(int[] arr, int k) {
        // write code here
    	int ans = arr.length;
    	if(k< arr[0]) {
    		return -1;
    	}
    	int low=0,high=arr.length-1;
    	while(low<=high) {
    		int mid = (low+high)/2;
    		if(arr[mid]<=k) {
    			ans=mid;
    			low=mid+1;
    		} else {
    			high=mid-1;
    		}
    	}
    	return ans;
    }
    
    public int[] getFloorAndCeil(int x, int[] arr) {
        // code here
    	int[] ans = new int[2];
    	java.util.Arrays.sort(arr);
    	int floor = getFloor(x,arr);
    	int ceil = getCeil(x,arr);
    	ans[0]=floor;
    	ans[1]=ceil;
    	return ans;
    }
    
    public int getFloor(int x,int[] arr) {
    	if(x< arr[0]) {
    		return -1;
    	}
    	int floor=arr.length;
    	int low=0,high=arr.length-1;
    	while(low<=high) {
    		int mid = (low+high)/2;
    		if(arr[mid]<=x) {
    			floor=arr[mid];
    			low=mid+1;
    		} else {
    			high=mid-1;
    		}
    	}
    	return floor;
    }
    
    public int getCeil(int x,int[] arr) {
    	if(x> arr[arr.length-1]) {
    		return -1;
    	}
    	int ceil=0;
    	int low=0,high=arr.length-1;
    	while(low<=high) {
    		int mid = (low+high)/2;
    		if(arr[mid]>=x) {
    			ceil=arr[mid];
    			high=mid-1;
    		} else {
    			low=mid+1;
    		}
    	}
    	return ceil;
    }
    
    //35. Search Insert Position
    public int searchInsert(int[] nums, int target) {
        int ans = nums.length;
    	if(target < nums[0]) {
    		return -1;
    	}
    	int low=0,high=nums.length-1;
    	while(low<=high) {
    		int mid = (low+high)/2;
    		if(nums[mid]>=target) {
    			ans=mid;
                high=mid-1;
    		} else {
    			low=mid+1;
    		}
    	}
    	return ans;
    }    
    
    //34. Find First and Last Position of Element in Sorted Array
    //Brute Approach
    public int[] searchRangeBrute(int[] nums, int target) {
        int[] res = new int[2];
        int start=-1,end=-1;
        int i=0,j=nums.length-1;
        while(i < j){
            if(nums[i] !=target){
                i++;
            }
            if(nums[j]!=target){
                
                j--;
            }
            if(nums[i]==target){
                start=i;
            }
            if(nums[j]==target){
                end=j;
            }
        }
        res[0]=start;
        res[1]=end;
        return res;
    }
    
    //Find lower and upper bound optimal approach
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int lb = getLowerBound(nums, target);
        int ub = getUpperbound(nums, target);
        if(lb<0 || lb>nums.length || nums[lb]!=target) {
        	lb=-1;
        }
        if(ub<0 || ub>nums.length || nums[ub]!=target) {
        	ub=-1;
        }
        res[0]=lb;
        res[1]=ub;
        return res;
    }
    
    public int getLowerBound(int[] nums,int target) {
    	if(target<nums[0]) {
    		return -1;
    	}
    	int ans=nums.length-1;
    	int low=0,high=nums.length-1;
    	while(low<=high) {
    		int mid = (low+high)/2;
    		if(nums[mid]<=target) {
    			ans=mid;
    			high=mid-1;
    		} else {
    			low=mid+1;
    		}
    	}
    	return ans;
    }
    
    public int getUpperbound(int[] nums,int target) {
    	int ans=nums.length-1;
    	if(target>nums[nums.length-1]) {
    		return -1;
    	}
    	int low=0,high=nums.length-1;
    	while(low<=high) {
    		int mid=(low+high)/2;
    		if(nums[mid]>target) {
    			ans=mid;
    			high=mid-1;
    		} else {
    			low=mid+1;
    		}
    	}
    	return ans;
    }
    
    //189. Rotate Array Brute approach
    public void rotateBrute(int[] nums, int k) {
    	k=k%nums.length;
		for(int i=0;i<k;i++) {
			int temp=nums[nums.length-1];
			for(int j=nums.length-1;j>0;j--) {
				nums[j]=nums[j-1];
			}
			nums[0]=temp;
		}
	}
    
    //189. Rotate Array Optimal approach
    //Rotate first d element , then next remain element and at last
    //rotate complete array
    public void rotate(int[] nums, int k) {
    	k=k%nums.length;
    	reverse(nums, 0, k-1);
    	reverse(nums, k, nums.length-1);
    	reverse(nums, 0, nums.length-1);
	}
    
    public void reverse(int[] nums, int i,int j) {
    	while(i!=j) {
    		int temp=nums[i];
    		nums[i]=nums[j];
    		nums[j]=temp;
    		i++;
    		j--;
    	}
    }
    
    //Right rotate above one is left rotate
    
//    void rotate(vector<int> arr, int d)
//    {
//    	if(d>size)
//    	d = d % size;
//
//        reverse(arr.begin(),arr.end());
//        reverse(arr.begin()+d,arr.end());
//        reverse(arr.begin(),arr.begin()+d);
//    }


}
