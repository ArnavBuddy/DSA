import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int heapSize;
    static int capacity;
    static int[] heap;
	public static void main (String[] args) throws java.lang.Exception
	{
		int[] arr = new int[]{7,1,2,5,8,4};
        buildTree(arr);
        print();
	}
	public static void buildTree(int[] arr){
	    heap = arr;
	    heapSize = arr.length;
	    capacity = arr.length;
	    for (int i = (heapSize / 2) - 1; i >= 0; i--){
	        heapify(i);
	    }
	}
	public static void heapify(int i){
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
	public static void swap(int i, int j){
	    int temp = heap[i];
	    heap[i] = heap[j];
	    heap[j] = temp;
	}
	public static void print(){
	    for (int i = 0; i < heap.length; i++){
	        System.out.print(heap[i] + " ");
	    }
	}
}
