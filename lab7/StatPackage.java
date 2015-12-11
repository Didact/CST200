//--
//Name: Dakota Smith
//Class: CST200
//Date: 11/6/2015
//Time: 1600
//--


import java.io.*;
import java.util.Arrays;

public class StatPackage {

	double[] scores;
	int count;

	public StatPackage() {
		scores = new double[500];
		count = 0;
	}

	public void insert(double value) {
		if(this.count == 500) {
			return;
		}
		this.scores[this.count++] = value;
	}

	public double Mean() {
		return sum(this.scores) / (double) this.count;
	}

	public double Median() {
		double[] sorted = sort(Arrays.copyOf(this.scores, this.count));
		int n = this.count/2;
		if(count % 2 == 1) {
			//length is odd
			return sorted[n];
		}
		//else we average the two middle values
		return (sorted[n-1]+sorted[n])/ (double) 2;
	}

	public double Variance() {
		double a = 0;
		double b = 0;
		for(int i = 0; i < this.count; i++) {
			a += this.scores[i] * this.scores[i];
		}
		double sum = sum(this.scores);
		b = sum*sum;
		return (a)/this.count - (b)/(this.count * this.count);
	}

	public double StdDev(double variance) {
		return Math.sqrt(variance);
	}

	public void Histogram() {
		int[] hist = new int[10];
		for(double d : this.scores) {
			if(d < 1) continue;	//5-6 of the numbers in the test program are less than 1
			int i = (((int) d) - 1)/10; // {1 .. 10} => 0, {11 .. 20} => 1, etc
			hist[i]++;
		}
		for(int i = 0; i < 10; i++) {
			System.out.printf("%2d - %d  | ", (i*10)+1, (i*10)+10);
			for(int j = 0; j < hist[i] / 5; j++) {
				System.out.printf("*");
			}
			System.out.println();
		}
	}


	//
	//Utilities
	//

	//Merge-sort that doesn't mutate the original
	//As it copies twice every recursion, it is not very time/space efficient
	//First wrote something like this for AP CS
	private static double[] sort(double[] arr) {
		double[] res = Arrays.copyOf(arr, arr.length);
		if(res.length == 0) {
			return new double[0];
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
		double[] left = sort(Arrays.copyOfRange(res, 0, n));
		double[] right = sort(Arrays.copyOfRange(res, n, res.length));
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
	private static void swap(double[] arr, int a, int b) {
		double temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	//Sum doesn't even need a count variable because the array will init to 0;
	private static double sum(double[] arr) {
		int sum = 0;
		for(double i : arr) {
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
