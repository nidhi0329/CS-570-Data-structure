//*************************//
//Name : Nidhi Chovatiya
//CWID : 10457344
//Lab_2 : Complexity
//Subject : CS-570-A
//*************************//

package lab2;

public class Complexity {
	
//	Method1 : time complexity O(n^2)
	public static void method1(int n) {
		int counter = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
					System.out.println("Operation " + counter);
					counter++;
			}
		}
		System.out.println("Time complexity of O(n^2) : " + counter);
	}
	
//	Method2 : time complexity O(n^3)
	public static void method2(int n) {
		int counter = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				for(int k=1; k<=n; k++) {
						System.out.println("Operation " + counter);
						counter++;
				}
			}
		}
		System.out.println("Time complexity of O(n^3) : " + counter);
	}

//	Method3 : time complexity O(log n)
	public static void method3(int n) {
		int counter = 0;
		for(int i = 1; i < n; i *= 2) {
				System.out.println("Operation " + counter);
				counter++;
		}
		System.out.println("Time complexity of O(log n) : " + counter);
	}

//	Method4 : time complexity O(n log n)
	public static void method4(int n) {
		int counter = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j < n; j *= 2) {
					System.out.println("Operation " + counter);
					counter++;
			}
		}
		System.out.println("Time complexity of O(n log n) : " + counter);
	}
	
//	Method5 : time complexity O(log log n)
	public static void method5(int n) {
		int counter = 0;
		for(double i = 2; i < n; i *= i) {
				   System.out.println("Operation " + counter);
				   counter++;
		}
		System.out.println("Time complexity of O(log log n) : " + counter);
	}
	
//	Method6 : time complexity O(2^n)
//	fibonacci(using recursion) which has time complexity O(2^n)
//	Digit at given index
	static int count=0;
	public static int method6(int n) {
		if (n == 0 || n == 1) {
	        return n;
		}
	    else {
		    	System.out.println("Operation " + count);
				count++;
		    	return method6(n-1) + method6(n-2);
	    	}
		
	}

	public static void main(String[] args) {
		
		System.out.println("Complexity for method1 : ");
		
		Complexity.method1(2);
		
		System.out.println("Complexity for method2 : ");
		
		Complexity.method2(2);
		
		System.out.println("Complexity for method3 : ");
		
		Complexity.method3(9);
		
		System.out.println("Complexity for method4 : ");
		
		Complexity.method4(16);
		
		System.out.println("Complexity for method5 : ");
		
		Complexity.method5(25);
		
		System.out.println("Complexity for method6 : ");
		int Ans=Complexity.method6(4);
		
		System.out.println("digit at given position =" + Ans);
		System.out.println("Time complexity of O(n^2) : " + count);
		
		}

}
