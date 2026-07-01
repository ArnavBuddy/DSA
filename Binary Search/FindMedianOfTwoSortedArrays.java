import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
    public static void main(String[] args){
        int[] nums1 = {7,8,9,12};
        int[] nums2 = {1,2,3};
        System.out.print("ans is: " + findMedianSortedArrays(nums1, nums2));
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length){
            System.out.println("nums1 is bigger so swap nums1 and nums2");
            return findMedianSortedArrays(nums2, nums1);
        } 
        System.out.println("nums1 is smaller than nums2, so apply binary search on nums1");
        int n1 = nums1.length;
        int n2 = nums2.length;
        int N = n1 + n2;
        int s = 0;
        int e = n1;
        while (s <= e){
            System.out.println("start: " + s);
            System.out.println("end: " + e);
            int cut1 = s + (e - s) / 2;
            int cut2 = N/2 - cut1;
            System.out.println("cut1: " + cut1);
            System.out.println("cut2: " + cut2);
            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int r1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];
            System.out.println("l1: " + l1);
            System.out.println("l2: " + l2);
            System.out.println("r1: " + r1);
            System.out.println("r2: " + r2);
            if (l1 <= r2 && l2 <= r1){
                System.out.println("Cut is valid");
                if (N % 2 != 0){
                    System.out.println("Odd elements");
                    return (double) Math.min(r1, r2);
                }
                else {
                    System.out.println("Even elements");
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }
            }
            else if (l1 > r2) {
                System.out.println("l1 is bigger than r2 so make it smaller");
                e = cut1 - 1;
            }
            else{
                System.out.println("l2 is bigger than r1 so make it smaller");
                s = cut1 + 1;
            } 
        }
        return 0.0;
    }
}
