//--
//Name: Dakota Smith
//Class: CST200
//Date: 10/30/2015
//Time: 4:45
//--


import java.io.*;
import java.util.Arrays;

public class lab5 {

	public static double calcMean(int[] scores, int count) {
		return sum(scores) / (double) count;
	}

	public static double calcMedian(int[] scores, int count) {
		int[] sorted = sort(Arrays.copyOf(scores, count));
		int n = count/2;
		if(count % 2 == 1) {
			//length is odd
			return (double) sorted[n];
		}
		//else we average the two middle values
		return (sorted[n-1]+sorted[n])/ (double) 2;
	}

	public static double calcVariance(int[] scores, int count) {
		int a = 0;
		int b = 0;
		for(int i = 0; i < count; i++) {
			a += scores[i] * scores[i];
		}
		int sum = sum(scores);
		b = sum*sum;
		return ((double)a)/count - ((double) b)/(count * count);
	}

	public static double calcStdDev(int[] scores, int count) {
		return Math.sqrt(calcVariance(scores, count));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String line;
		int[] arr = new int[500];
		int i = 0;
		while((line = stdin.readLine()) != null && i < arr.length) {
			arr[i] = Integer.parseInt(line);
			i++;
		}
		if(i != arr.length) {
			arr = Arrays.copyOf(arr, i);
		}
		System.out.printf("Mean: %.02f%n", calcMean(arr, arr.length));
		System.out.printf("Median: %.02f%n", calcMedian(arr, arr.length));
		System.out.printf("Variance: %.02f%n", calcVariance(arr, arr.length));
		System.out.printf("Standard Deviation: %.02f%n", calcStdDev(arr, arr.length));
	}


	//
	//Utilities
	//

	//Merge-sort that doesn't mutate the original
	//As it copies twice every recursion, it is not very time/space efficient
	//First wrote something like this for AP CS
	private static int[] sort(int[] arr) {
		int[] res = Arrays.copyOf(arr, arr.length);
		if(res.length == 0) {
			return new int[0];
		}
		if(res.length == 1) {
			return res;
		}
		if(res.length == 2) {
			if(res[0] > res[1]) {
				swap(res, 0, 1);
				return res;
			}
			return res;
		}
		int n = arr.length / 2;
		int[] left = sort(Arrays.copyOfRange(res, 0, n));
		int[] right = sort(Arrays.copyOfRange(res, n, res.length));
		int i = 0, j = 0;
		while(i < left.length && j < right.length) {
			if(left[i] < right[j]) {
				res[i+j] = left[i];
				i++;
			} else {
				res[i+j] = right[j];
				j++;
			}
		}
		if(i+j != res.length) {
			if(i == left.length) {
				for(; j < right.length; j++) {
					res[i+j] = right[j];
				}
			} else if(j == right.length) {
				for(; i < left.length; i++) {
					res[i+j] = left[i];
				}
			}
		}
		return res;
	}

	//Swaps two values in-place
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	private static String toString(int[] arr) {
		String str = "";
		for(int i : arr) {
			str += i + " ";
		}
		return str;
	}

	//Sum doesn't even need a count variable because the array will init to 0;
	private static int sum(int[] arr) {
		int sum = 0;
		for(int i : arr) {
			sum += i;
		}
		return sum;
	}

	private static String toString(double[] arr) {
		String str = "";
		for(double d : arr) {
			str += String.format("%.02f ", d);
		}
		return str;
	}
}
