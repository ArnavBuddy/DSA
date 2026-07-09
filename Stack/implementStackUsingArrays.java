import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    int[] target = {1,3,5};
	    int n = 5;
	    System.out.println(buildArray(target, n));

	}
	public static List<String> buildArray(int[] target, int n) {
        List<String> res = new ArrayList<>();
        int[] stack = new int[n];
        int top = -1;
        int j = 0;
        for (int i = 1; i <= n && j < target.length; i++){
            stack[++top] = i;
            res.add("Push");
            System.out.println("Checking target index = " + (j));
            if (i != target[j]){
                top--;
                res.add("Pop");
            }
            else{
                j++;
            }
        }
        return res;
    }
}
