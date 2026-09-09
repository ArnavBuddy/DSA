import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        int[] arr = {48,36,13,52,19,94,21};
        // BubbleSort bs = new BubbleSort();
        // bs.bubbleSort(arr);
        // MergeSort ms = new MergeSort();
        // ms.sort(arr);
        QuickSort qs = new QuickSort();
        qs.quickSort(arr, 0, arr.length - 1);
        for (int num : arr){
            System.out.print(num + " ");
        }
	}
}
class BubbleSort{
    public static void bubbleSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - i - 1; j++){
                if (arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
class MergeSort{
    static int[] orig;
    static int[] temp;
    static int length;
    public static void sort(int[] arr){
        orig = arr;
        length = arr.length;
        temp = new int[length];
        divide(0, length - 1);
    }
    public static void divide(int l, int h){
        if (l >= h) return;
        int m = l + (h - l) / 2;
        divide(l, m);
        divide(m + 1, h);
        merge(l, m, h);
    }
    public static void merge(int l, int m, int h){
        for (int i = l; i <= h; i++){
            temp[i] = orig[i];
        }
        int i = l;
        int j = m + 1;
        int k = l;
        while (i <= m && j <= h){
            if (temp[i] <= temp[j]){
                orig[k] = temp[i];
                i++;
            }
            else{
                orig[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= m){
            orig[k] = temp[i];
            i++;
            k++;
        }
        while(j <= h){
            orig[k] = temp[j];
            j++;
            k++;
        }
    }
}
class QuickSort{
    public static int partition(int[] arr, int low, int high){
        int pivot = arr[low + (high - low) / 2];
        int i = low;
         int j = high;
         while (i <= j){
             while (arr[i] < pivot) i++;
             while (arr[j] > pivot) j--;
             if (i <= j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
             }
         }
         return i;
    }
    public static void quickSort(int[] arr, int l, int h){
        int pi = partition(arr, l, h);
        if (l < pi - 1){
            quickSort(arr, l, pi - 1);
        }
        if (pi < h){
            quickSort(arr, pi, h);
        }
    }
}
