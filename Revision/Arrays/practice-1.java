import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
    public static void main(String[] args){
        // int[] nums = {1,4,5,3,2};
        // nextPermutation(nums);
        String str = "-9";
        System.out.println(toDig(str));
    }
    public static int toDig(String str){
        int res = 0;
        int num = 0;
        boolean isNeg = false;
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == '-'){
                isNeg = true;
                continue;
            }
            num += arr[i] - '0';
        }
        res = num;
        if (isNeg){
            res = -res;
        }
        return res;
    } 
    // public static void nextPermutation(int[] nums) {
    //     int i = nums.length - 2;
    //     while (i >= 0 && nums[i] > nums[i + 1]){
    //         i--;
    //     }
    //     System.out.println(i);
    //     int j = nums.length - 1;
    //     while (j >= i && nums[j] < nums[i]){
    //         j--;
    //     }
    //     System.out.println(j);
    //     swap(nums, i, j);
    //     rev(nums, i + 1, nums.length - 1);
    //     for (int num : nums){
    //         System.out.print(num + " ");
    //     }
    // }
    // public static void swap(int[] arr, int i, int j){
    //     int temp = arr[i];
    //     arr[i] = arr[j];
    //     arr[j] = temp;
    // }
    // public static void rev(int[] arr, int i, int j){
    //     while (j >= i){
    //         swap(arr, i, j);
    //         j--;
    //         i++;
    //     }
    // }
}
