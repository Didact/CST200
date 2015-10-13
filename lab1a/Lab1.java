//--
//Name: Dakota Smith
//CST: 200
//Time:
//Date:
//Assignment:
//--

import java.io.*;
import java.text.NumberFormat;

public class Lab1 {
	public static void main(String[] args){

		String[][] tokens = new String[3][];
		int[] ints = new int[4];
		double[] doubles = new double[4];
		int[] chars = new int[3];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);

		//Get the three lines of input from stdin and parse it into tokens
		for(int i = 0; i < 3; i++){
			try {
				tokens[i] = br.readLine().split("\\s+");
				if(tokens[i].length != 4  && !(i == 2 && tokens[i].length == 3)){
					System.out.printf("invalid input. length %d%n", tokens[i].length);
					return;
				}
			}
			catch(IOException ex) {
				ex.printStackTrace();
				return;
			}
			
		}

		//Parse the ints
		for(int i = 0; i < 4; i++){
			try {
				ints[i] = Integer.parseInt(tokens[0][i]);
			}
			catch(NumberFormatException ex){
				System.out.printf("%dth argument not a number: %s%n", i, tokens[0][i]);
				ex.printStackTrace();
				return;
			}
		}

		//Parse the floats
		for(int i = 0; i < 4; i++){
			try {
				doubles[i] = Double.parseDouble(tokens[1][i]);
			}
			catch(NumberFormatException ex){
				System.out.printf("%dth argument not a number: %s%n", i, tokens[1][i]);
				ex.printStackTrace();
				return;
			}
		}

		//Get the chars, store them as ints for better manipulation
		for(int i = 0; i < 3; i++){
			try {
				chars[i] = (int) tokens[2][i].charAt(0);
			}
			catch(NumberFormatException ex){
				System.out.printf("%dth argument not a number: %s%n", i, tokens[0][i]);
				ex.printStackTrace();
				return;
			}
		}

		int isum = ints[0] + ints[1] + ints[2] + ints[3];
		System.out.printf("%d %d %d %d%n", ints[0], ints[1], ints[2], ints[3]);	//1
		System.out.println(isum);	//2
		System.out.println((ints[0] + ints[2]) % (ints[1] + ints[3]));	//3
		System.out.println(isum/4);	//4
		System.out.printf("%s%n", nf.format(((double) isum)/4.0));	//5

		//Originally used printf's %.02f to print the floats
		//But it rounds, leading to a different answer than required
		double dsum = doubles[0] + doubles[1] + doubles[2] + doubles[3];
		System.out.printf("%s %s %s %s%n", nf.format(doubles[3]), nf.format(doubles[2]), nf.format(doubles[1]), nf.format(doubles[0]));	//6
		System.out.printf("%s%n", nf.format((doubles[1] + doubles[2]) - (doubles[0] + doubles[3])));	//7
		System.out.printf("%s%n", nf.format(dsum/4));	//8
		System.out.println(((int)dsum)/4);	//9
		System.out.printf("%s%n", nf.format(dsum/4 - ((long)dsum/4)));	//10
		System.out.printf("%s%n", nf.format((isum + dsum) /8.0));	//11

		System.out.printf("%c %c %c%n", chars[0], chars[1], chars[2]);	//12
		System.out.printf("%d %d %d%n", chars[0], chars[1], chars[2]);	//13
		System.out.printf("%c %c %c%n", chars[0]+1, chars[1]+1, chars[2]+1); 	//14
		System.out.printf("%c %c %c%n", chars[0]-1, chars[1]-1, chars[2]-1);	//15
	}
}
