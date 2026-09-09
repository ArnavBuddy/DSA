import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // System.out.println(isPrime(n));
        // primeFactor(n);
        // System.out.println(GCD(48, 18));
        // System.out.println(LCM(48, 18));
        // permuteString("ABC", "");
        permutation(5, 2);
	}
	public static boolean isPrime(int n){
	    for (int i = 2; i < n; i++){
            if (n % i == 0) return false;
        }
        return true;
	}
	public static void primeFactor(int n){
	    for (int i = 2; i <= n; i++){
	        while (n % i == 0){
	            System.out.print(i + " * ");
	            n /= i;
	        }
	    }
	}
	public static int GCD(int a, int b){
	    while (b != 0){
	        int rem = a % b;
	        a = b;
	        b = rem;
	    }
	    return a;
	}
	public static int LCM(int a, int b){
	    int gcd = GCD(a, b);
	    return (a * b) / gcd;
	}
	public static void permuteString(String s, String res){
	    if (s.length() == 0){
	        System.out.println(res);
	        return;
	    } 
	    for (int i = 0; i < s.length(); i++){
	        char ch = s.charAt(i);
	        String rem = s.substring(0, i) + s.substring(i + 1);
	        permuteString(rem, res + ch);
	    }
	}
	public static void permutation(int n, int r){

        int result = 1;

        for (int i = 0; i < r; i++) {
            result = result * (n - i);
        }

        System.out.println("Permutation = " + result);
	}
}
