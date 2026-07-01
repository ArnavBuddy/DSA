import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
    public static void main(String[] args){
        int[] stalls = {1,2,8,4,9};
        int k = 3;
        System.out.println(aggressiveCows(stalls, k) + " is the ans");
    }
    public static int aggressiveCows(int[] stalls, int k) {
        // code here
        if (stalls.length < k) return -1;
        return solve(stalls, k);
    }
    public static int solve(int[] arr, int k){
        int ans = -1;
        int s = 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++){
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        int e = max - min;
        while (s <= e){
            System.out.println("start: " + s);
            System.out.println("end: " + e);
            int m = s + (e - s) / 2;
            System.out.println("mid: " + m);
            if (isValid(arr, k, m)) {
                System.out.println("Valid");
                ans = m; 
                s = m + 1;
            }
            else {
                System.out.println("Invalid");
                e = m - 1;
            }
        }
        return ans;
    }
    public static boolean isValid(int[] arr, int k, int m){
        Arrays.sort(arr);
        int cow = 1;
        int lastStall = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] - lastStall >= m){
                cow++;
                lastStall = arr[i];
            }
            if (cow == k) return true;
        }
        return false;
    }
}
