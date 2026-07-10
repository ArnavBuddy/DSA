import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		int[] arr = {2,3};
		ArrayList<Integer> ans = subsetSums(arr);
		System.out.println(ans);

	}
	public static ArrayList<Integer> subsetSums(int[] arr) {
        // code here
        ArrayList<Integer> res = new ArrayList<>();
        int sum = 0;
        int idx = 0;
        solve(arr, res, idx, sum);
        return res;
    }
    public static void solve(int[] arr, ArrayList<Integer> res, int idx, int sum){
        System.out.println("at index " + idx);
        System.out.println("sum is " + sum);
        if (idx == arr.length){
            System.out.println("base case reached");
            System.out.println("final sum is " + sum);
            res.add(sum);
            return;
        }
        solve(arr, res, idx + 1, sum + arr[idx]);
        solve(arr, res, idx + 1, sum);
        
    }
}
