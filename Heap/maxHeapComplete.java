import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int[] heap;
    static int heapSize;
    static int capacity;
    Codechef(int[] arr) {
        heap = arr;
        heapSize = arr.length;
        capacity = arr.length;
    }
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        int[] arr = new int[]{2,4,5,8,1};
        Codechef myHeap = new Codechef(arr);
        myHeap.print();
        myHeap.heapSort();
        myHeap.print();
        myHeap.buildHeap();
        myHeap.print();
        System.out.println(myHeap.extractMaxElement());
        myHeap.print();
        myHeap.insert(12);
        myHeap.print();
        myHeap.incKey(3, 7);
        myHeap.print();
        myHeap.decKey(0, 1);
        myHeap.print();
        
	}
	static void print(){
	    for (int i = 0; i < heapSize; i++){
	        System.out.print(heap[i] + " ");
	    }
	    System.out.println();
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
	static void buildHeap(){
	    for (int i = (heapSize / 2) - 1; i >= 0; i--){
	        heapify(i);
	    }
	}
	static void heapify(int idx){
	    int largest = idx;
	    int lc = 2 * idx + 1;
	    int rc = 2 * idx + 2;
	    if (lc < heapSize && heap[largest] < heap[lc]) largest = lc;
	    if (rc < heapSize && heap[largest] < heap[rc]) largest = rc;
	    if (largest != idx){
	        swap (largest, idx);
	        heapify(largest);
	    }
	}
	static int extractMaxElement(){
	    int max = heap[0];
	    heap[0] = heap[heapSize - 1];
	    heapSize--;
	    heapify(0);
	    return max;
	}
	static void incKey(int idx, int val){
	    if (idx < 0 || idx >= heapSize || val < heap[idx]) return;
	    heap[idx] = val;
	    while (idx > 0 && heap[idx] > heap[(idx - 1) / 2]){
	        swap(idx, (idx - 1) / 2);
	        idx = (idx - 1) / 2;
	    }
 	}
 	static void decKey(int idx, int val){
 	    if (idx < 0 || idx >= heapSize || val > heap[idx]) return;
 	    heap[idx] = val;
 	    heapify(idx);
 	}
 	static void insert(int val){
 	    if (heapSize + 1 > capacity) incCapacity();
 	    heapSize += 1;
 	    heap[heapSize - 1] = val;
 	    int idx = heapSize - 1;
 	    while (idx > 0 && heap[idx] > heap[(idx - 1) / 2]){
	        swap(idx, (idx - 1) / 2);
	        idx = (idx - 1) / 2;
	    }
 	}
 	static void heapSort(){
 	    int size = heapSize;
 	    buildHeap();
 	    while (heapSize > 1){
 	        swap(0, heapSize - 1);
 	        heapSize--;
 	        heapify(0);
 	    }
 	    heapSize = size;
 	}
}
