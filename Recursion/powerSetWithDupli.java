import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		int[] arr = {2,3};
		List<List<Integer>> ans = subsetsWithDup(arr);
		System.out.println(ans);
	}
	public static List<List<Integer>> subsetsWithDup(int[] nums){
	    Arrays.sort(nums);
	    int idx = 0;
	    List<Integer> set = new ArrayList<>();
	    List<List<Integer>> res = new ArrayList<>();
        solve(nums, idx, set, res);
	    return res;
	}
	public static void solve(int[] arr, int idx, List<Integer> set, List<List<Integer>> res){
	    if (idx == arr.length){
	        res.add(new ArrayList<>(set));
	        return;
	    }
	    set.add(arr[idx]);
	    solve(arr, idx + 1, set, res);
	    set.remove(set.size() - 1);
	    int i = idx + 1;
	    while (i < arr.length && arr[i] == arr[i - 1]) i++;
	    solve(arr, i, set, res);
	}
}
