import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        int[] arr = {8,5,1,3,2,10};
        System.out.println("Before sorting: ");
        printArray(arr);
        System.out.println();
        mergreSort(arr, 0, 5);
        System.out.println("After sorting: ");
        printArray(arr);
	}
	private static void bubbleSort(int[] arr){
	    int n = arr.length;
	    for (int i = 0; i <= n - 2; i++){
	        boolean flag = false;
	        System.out.println("Pass " + i);
	        for (int j = 0; j <= n - 2 - i; j++){
	            if (arr[j] > arr[j + 1]){
	                swap(arr, j, j + 1);
	                flag = true;
	            } 
	        }
	        printArray(arr);
	        System.out.println();
	        if (!flag){
	            System.out.println("no sorting done so sorted");
	            break;
	        } 
	    }
	}
	private static void swap(int[] arr, int i, int j){
	    int temp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = temp;
	}
	private static void printArray(int[] arr){
	    for (int num : arr) System.out.print(num + " ");
	}
	private static void brickSort(int[] arr){
	    int n = arr.length;
	    boolean isSort = false;
	    while (!isSort){
	        boolean flag = false;
	        for (int i = 0; i <= n - 2; i += 2){
	            if (arr[i] > arr[i + 1]){
	                swap(arr, i, i + 1);
	                flag = true;
	            }
	        }
	        printArray(arr);
	        System.out.println();
	        for (int i = 1; i <= n - 2; i += 2){
	            if (arr[i] > arr[i + 1]){
	                swap(arr, i, i + 1);
	                flag = true;
	            }
	        }
	        printArray(arr);
	        System.out.println();
	        if (!flag){
	            System.out.println("no sorting done so array is sorted");
	            isSort = true;
	            break;
	        }
	    }
	}
	private static void mergreSort(int[] arr, int s, int e){
	    if (s >= e){
	        return;
	    }
	    int m = s + (e - s) / 2;
	    mergreSort(arr, s, m);
	    mergreSort(arr, m + 1, e);
	    merge(arr, s, m, e);
	}
	private static void merge(int[] arr, int s, int m, int e){
	    int n1 = m - s + 1;
	    int n2 = e - m;
	    int[] a1 = new int[n1];
	    int[] a2 = new int[n2];
	    for (int i = 0; i < n1; i++){
	        a1[i] = arr[s + i];
	    }
	    for (int i = 0; i < n2; i++){
	        a2[i] = arr[m + 1 + i];
	    }
	    int i = 0, j = 0, k = s;
	    while (i < n1 && j < n2){
	        if (a1[i] <= a2[j]){
	            arr[k] = a1[i];
	            i++;
	        }
	        else{
	            arr[k] = a2[j];
	            j++;
	        }
	        k++;
	    }
	    while (i < n1){
            arr[k++] = a1[i++];
        }

        while (j < n2){
            arr[k++] = a2[j++];
        }
	}
}
