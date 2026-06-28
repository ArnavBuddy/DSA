import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		int[] arr = {15, 17, 20};
		int k = 2;
		if (arr.length < k) System.out.print(-1);
        System.out.println(solve(arr, k) + " is the ans");
	}
	public static int solve(int[] arr, int k){
	    int ans = -1;
	    int s = 0;
	    int e = 0;
	    for (int num : arr){
	        e += num;
	    }
	    while (s <= e){
	        System.out.println("start: " + s);
	        System.out.println("end: " + e);
	        int m = s + (e - s) / 2;
	        System.out.println("mid: " + m);
	        if (isValid(arr, m, k)){
	            System.out.println("Valid");
	            ans = m;
	            e = m - 1;
	        }
	        else {System.out.println("Invalid"); s = m + 1;}
	    }
	    return ans;
	}
	public static boolean isValid(int[] arr, int m, int k){
	    int n = arr.length;
	    int stud = 1;
	    int page = 0;
	    for (int i = 0; i < n; i++){
	        if (arr[i] > m) return false;
	        if (page + arr[i] <= m) page += arr[i];
	        else {
	            stud++;
	            page = arr[i];
	        }
	    }
	    if (stud <= k) return true;
	    else return false;
	}
}
