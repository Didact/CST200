//--
//Names: Dakota Smith, Eric Wilyat, Edwin Upshaw,Petros Petros
//--

import java.io.*;
import java.text.*;

public class hackathon {
	static double acc;
	public static void main(String[] args) throws IOException {

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(4);
		nf.setMaximumFractionDigits(4);
		nf.setGroupingUsed(false);

		double n;
		char op = 'q';

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

		while(op != 'T' && op != 't') {
			String input = stdin.readLine();
			String[] tokens = input.split("(\\s)+");
			n = Double.parseDouble(tokens[0]);
			op = tokens[1].charAt(0);
			switch(op) {
				case 'e':
				case 'E':
					acc = n;
					break;
				case 'a':
				case 'A':
					acc += n;
					break;
				case 's':
				case 'S':
					acc -= n;
					break;
				case 'm':
				case 'M':
					acc *= n;
					break;
				case 'd':
				case 'D':
					if(n == 0) {
						System.out.println("attempt to divide by zero - please reenter");
						break;
					}
					acc /= n;
					break;
				case 'p':
				case 'P':
					if((int) n < 0) {
						System.out.println("negative powers are not allowed - please reenter");
						break;
					}
					if((int) n == 0) {
						acc = 1;
						break;
					}
					double tacc = acc;
					for(int i = 0; i < (int) n-1; i++) {
						acc *= tacc;
					}
					break;
				case 't':
				case 'T':
					//NOP
					break;
				default:
					System.out.println("an invalid operator was entered - please reenter");
					break;
					
			}
			System.out.println("= " + nf.format(acc));
		}
	}
}
