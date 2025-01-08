package main;

import java.util.LinkedHashSet;
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
}
