import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int heap[];
    static int heapSize;
	public static void main (String[] args) throws java.lang.Exception
	{
		int arr[] = new int[]{20, 11, 10, 6, 5};
		for (int i = 0; i < arr.length; i++){
		    System.out.print(arr[i] + " ");
		}
		System.out.println();
        System.out.println(pop(arr));
        System.out.println();
        print();
	}
	static int pop(int[] arr){
	    heap = arr;
	    heapSize = arr.length;
	    if (heapSize == 0) return -1;
	    int max = heap[0];
	    heap[0] = heap[heapSize - 1];
	    heapSize--;
	    heapify(0);
	    return max;
	}
	static void heapify(int i){
	    int largest = i;
	    int lc = 2 * i + 1;
	    int rc = 2 * i + 2;
	    if (lc < heapSize && heap[lc] > heap[largest]){
	        largest = lc;
	    }
	    if (rc < heapSize && heap[rc] > heap[largest]){
	        largest = rc;
	    }
	    if (largest != i){
	        swap(largest, i);
	        heapify(largest);
	    }
	}
	static void swap(int i, int j){
	    int temp = heap[i];
	    heap[i] = heap[j];
	    heap[j] = temp;
	}
	static void print(){
	    for (int i = 0; i < heapSize; i++){
	        System.out.print(heap[i] + " ");
	    }
	}
}