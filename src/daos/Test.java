package daos;

import java.util.Scanner;

public class Test {
	
	public static String[] swapElement(String[] ar) {
		
		String ans = new String();
		
		for(int i = 0; i < ar.length; i= i + 2 ) {
			ans = ans + ar[i];
		}
		if(ar.length % 2 == 1) {
			for(int i = ar.length - 2; i >= 0; i= i -2 ) {
				ans = ar[i] + ans;
			}	
		}
		else {
			for(int i = ar.length - 1; i >= 0; i= i -2) {
				ans = ans + ar[i];
			}
		}
		for(int i = 0; i < ar.length; i= i + 2 ) {
			ans = ans + ar[i];
		}
		return ans.split("");
		
	}
	
	
	public static void main(String args[]) {
		/*Scanner s = new Scanner(System.in) ;
		int swap = s.nextInt();
		s.nextLine();
		String line = s.nextLine();
		String a[] = line.split("");
		
		for(int i = 0; i< swap; i++) {
			a = swapElement(a);
			System.out.println(a[0]);
		}
		for(int i = 0; i < a.length; i++) {
			
			System.out.println(a[i]);
		}*/
		
		
	}
	
}
