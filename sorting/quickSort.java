import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		int[] arr = {15,9,7,13,12,16,4,18,11};
        int low = 0;
        int high = 8;
        QuickSort qs = new QuickSort();
        qs.quickSort(arr, low, high);
        for (int num : arr){
            System.out.print(num + " ");
        }
	}
}
class QuickSort{
    public static int partition(int[] arr, int low, int high){
        int pivot = (low + high) / 2;
        while (low <= high){
            while (arr[low] < arr[pivot]) low++;
            while (arr[high] > arr[pivot]) high--;
            if (low <= high){
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
                low++;
                high--;
            }
        }
        return low;
    }
    public static void quickSort(int[] arr, int low, int high){
        int pi = partition(arr, low, high);
        if (low < pi - 1) quickSort(arr, low, pi - 1);
        if (high > pi) quickSort(arr, pi, high);
    }
}
