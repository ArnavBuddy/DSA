import java.util.*;
import java.lang.*;
import java.io.*;
class Node implements Comparable<Node>{
    int sum;
    int i;
    int j;
    Node(int sum, int i, int j){
        this.sum = sum;
        this.i = i;
        this.j = j;
    }
    @Override
    public int compareTo(Node that){
        return that.sum - this.sum;
    }
}
class Codechef
{
    public static void print(int[] a){
        for (int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
	public static void main (String[] args) throws java.lang.Exception
	{

		int[] a = {5,8,6,1};
		int[] b = {3,7,2,5};
		int k = 4;
		System.out.println("ans is " + kMaximumSumCombination(a, b, k));

	}
	public static ArrayList<Integer> kMaximumSumCombination(int[] a, int[] b, int k){
	    ArrayList<Integer> res = new ArrayList<>();
	    System.out.println("Before sorting: ");
	    print(a);
	    Arrays.sort(a);
	    int l = 0;
	    int r = a.length - 1;
	    while (l < r){
	        int temp = a[l];
	        a[l] = a[r];
	        a[r] = temp;
	        l++;
	        r--;
	    }
	    System.out.println("After sorting: ");
	    print(a);
	    System.out.println("Before sorting: ");
	    print(b);
	    Arrays.sort(b);
	    l = 0;
	    r = b.length - 1;
	    while (l < r){
	        int temp = b[l];
	        b[l] = b[r];
	        b[r] = temp;
	        l++;
	        r--;
	    }
	    System.out.println("After sorting: ");
	    print(b);
	    PriorityQueue<Node> pq = new PriorityQueue<>();
	    HashSet<String> vis = new HashSet<>();
	    pq.offer(new Node (a[0] + b[0], 0, 0));
	    System.out.println("Max sum added which is " + (a[0] + b[0]));
	    vis.add("0,0");
	    System.out.println("added (0,0) to the vis set");
	    while (k-- > 0 && !pq.isEmpty()){
	        System.out.println("Remaining sums: " + k);
	        Node curr = pq.poll();
	        System.out.println("Removed max sum from heap " + curr.sum);
	        int i = curr.i;
	        int j = curr.j;
	        res.add(curr.sum);
	        System.out.println("added " + curr.sum + " to the result");
	        if (i + 1 < a.length){
	            System.out.println("i + 1 index is valid");
	            String key = (i + 1) + "," + j;
	            if (!vis.contains(key)){
	                System.out.println("not visited");
	                vis.add(key);
	                System.out.println("added " + key + " to the vis set");
	                pq.offer(new Node(a[i + 1] + b[j], i + 1, j));
	                System.out.println("added " + (a[i + 1] + b[j]) + " to the heap");
	            }
	        }
	        if (j + 1 < b.length){
	            System.out.println("j + 1 index is valid");
	            String key = i + "," + (j + 1);
	            if (!vis.contains(key)){
	                System.out.println("not visited");
	                vis.add(key);
	                System.out.println("added " + key + " to the vis set");
	                pq.offer(new Node(a[i] + b[j + 1], i, j + 1));
	                System.out.println("added " + (a[i] + b[j + 1]) + " to the heap");
	            }
	        }
	    }
	    return res;
	}
}
