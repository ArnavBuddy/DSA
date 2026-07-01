import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		int[] a = {7,8,9,10,11};
		int[] b = {1,2,3};
		int k = 3;
		System.out.print("ans: " + solve(a, b, k)); 
	}
	public static int solve(int[] a, int[] b, int k){
	    int n1 = a.length;
	    int n2 = b.length;
	    if (n1 > n2) return solve(b, a, k);
	    System.out.println("n1: " + n1);
	    System.out.println("n2: " + n2);
	    int s = Math.max(0, k - n2);
	    int e = Math.min(k, n1);
	    while (s <= e){
	        System.out.println("start: " + s);
	        System.out.println("end: " + e);
	        int m = s + (e - s) / 2;
	        System.out.println("mid: " + m);
	        int c1 = m;
	        int c2 = k - c1;
	        int l1 = (c1 == 0) ? Integer.MIN_VALUE : a[c1 - 1];
	        int l2 = (c2 == 0) ? Integer.MIN_VALUE : b[c1];
	        int r1 = (c1 == n1) ? Integer.MAX_VALUE : a[c2 - 1];
	        int r2 = (c2 == n2) ? Integer.MAX_VALUE : b[c2];
	        System.out.println("c1: " + c1);
	        System.out.println("c2: " + c2);
	        System.out.println("l1: " + l1);
	        System.out.println("l2: " + l2);
	        System.out.println("r1: " + r1);
	        System.out.println("r2: " + r2);
	        if (l1 <= r2 && l2 <= r1){
	            System.out.println("Valid");
	            return Math.max(l1, l2);
	        }
	        else{
	            System.out.println("Not Valid");
	            if (l1 > r2){
	                System.out.println("search left");
	                e = m - 1;
	            }
	            else{
	                System.out.println("search right");
	                s = m + 1;
	            }
	        }
	    }
	    return -1;
	}
}
