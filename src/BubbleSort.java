
public class BubbleSort {
	
	public static void sort(int a[]) {
		
		for(int i = 0; i < a.length; i++) {
			
			for(int j = 0 ; j < a.length; j++) {
				if(j < a.length - 1) {
					if(a[j] > a[j + 1]) {
						a[j] = a[j] + a[j+1];
						a[j+1] = a[j] - a[j+1];
						a[j] = a[j] - a[j+1];
					}
				}
			}
		}
		
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	public static void main(String args[]) {
		
		int a[] = {1,5,3,87,2,4};
		
		sort(a);
	}
}
