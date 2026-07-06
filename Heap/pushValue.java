import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int heapSize;
    static int capacity;
    static int[] heap;
    Codechef(int[] arr){
        heap = arr;
        heapSize = arr.length;
        capacity = arr.length;
    }
	public static void main (String[] args) throws java.lang.Exception
	{
		int[] arr = new int[]{15,11,10,6,4};
		Codechef Heap = new Codechef(arr);
        Heap.print();
        Heap.push(18);
        Heap.print();
	}
	static void print(){
	    for (int i = 0; i < heapSize; i++){
	        System.out.print(heap[i] + " ");
	    }
	    System.out.println();
	}
	static void push(int val){
	    if (heapSize + 1 > capacity){
	        incCapacity();
	    }
	    heapSize += 1;
	    heap[heapSize - 1] = val;
	    int idx = heapSize - 1;
	    while(idx > 0 && heap[idx] > heap[(int) Math.ceil(idx / 2.0) - 1]){
	        swap(idx, (int) Math.ceil(idx / 2.0) - 1);
	        idx = (int) Math.ceil(idx / 2.0) - 1;
	    }
	}
	static void swap(int i, int j){
	    int temp = heap[i];
	    heap[i] = heap[j];
	    heap[j] = temp;
	}
	static void incCapacity(){
	    capacity *= 2;
	    int[] newHeap = new int[capacity];
	    for (int i = 0; i < heapSize; i++){
	        newHeap[i] = heap[i];
	    }
	    heap = newHeap;
	}
}
